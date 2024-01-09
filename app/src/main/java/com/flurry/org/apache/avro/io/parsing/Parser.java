package com.flurry.org.apache.avro.io.parsing;

import com.flurry.org.apache.avro.AvroTypeException;
import com.flurry.org.apache.avro.io.parsing.Symbol;

/* loaded from: classes.dex */
public class Parser {
    protected int pos;
    protected Symbol[] stack = new Symbol[5];
    protected final ActionHandler symbolHandler;

    /* loaded from: classes.dex */
    public interface ActionHandler {
        Symbol doAction(Symbol symbol, Symbol symbol2);
    }

    public Parser(Symbol symbol, ActionHandler actionHandler) {
        this.symbolHandler = actionHandler;
        this.stack[0] = symbol;
        this.pos = 1;
    }

    private void expandStack() {
        Symbol[] symbolArr = new Symbol[this.stack.length + Math.max(this.stack.length, 1024)];
        System.arraycopy(this.stack, 0, symbolArr, 0, this.stack.length);
        this.stack = symbolArr;
    }

    public final Symbol advance(Symbol symbol) {
        while (true) {
            Symbol[] symbolArr = this.stack;
            int i = this.pos - 1;
            this.pos = i;
            Symbol symbol2 = symbolArr[i];
            if (symbol2 == symbol) {
                return symbol2;
            }
            Symbol.Kind kind = symbol2.kind;
            if (kind == Symbol.Kind.IMPLICIT_ACTION) {
                Symbol doAction = this.symbolHandler.doAction(symbol, symbol2);
                if (doAction != null) {
                    return doAction;
                }
            } else if (kind == Symbol.Kind.TERMINAL) {
                throw new AvroTypeException("Attempt to process a " + symbol + " when a " + symbol2 + " was expected.");
            } else {
                if (kind == Symbol.Kind.REPEATER && symbol == ((Symbol.Repeater) symbol2).end) {
                    return symbol;
                }
                pushProduction(symbol2);
            }
        }
    }

    public int depth() {
        return this.pos;
    }

    public Symbol popSymbol() {
        Symbol[] symbolArr = this.stack;
        int i = this.pos - 1;
        this.pos = i;
        return symbolArr[i];
    }

    public final void processImplicitActions() {
        while (this.pos > 1) {
            Symbol symbol = this.stack[this.pos - 1];
            if (symbol.kind == Symbol.Kind.IMPLICIT_ACTION) {
                this.pos--;
                this.symbolHandler.doAction(null, symbol);
            } else if (symbol.kind == Symbol.Kind.TERMINAL) {
                return;
            } else {
                this.pos--;
                pushProduction(symbol);
            }
        }
    }

    public final void processTrailingImplicitActions() {
        while (this.pos >= 1) {
            Symbol symbol = this.stack[this.pos - 1];
            if (symbol.kind != Symbol.Kind.IMPLICIT_ACTION || !((Symbol.ImplicitAction) symbol).isTrailing) {
                return;
            }
            this.pos--;
            this.symbolHandler.doAction(null, symbol);
        }
    }

    public final void pushProduction(Symbol symbol) {
        Symbol[] symbolArr = symbol.production;
        while (this.pos + symbolArr.length > this.stack.length) {
            expandStack();
        }
        System.arraycopy(symbolArr, 0, this.stack, this.pos, symbolArr.length);
        this.pos = symbolArr.length + this.pos;
    }

    public void pushSymbol(Symbol symbol) {
        if (this.pos == this.stack.length) {
            expandStack();
        }
        Symbol[] symbolArr = this.stack;
        int i = this.pos;
        this.pos = i + 1;
        symbolArr[i] = symbol;
    }

    public void reset() {
        this.pos = 1;
    }

    public Symbol topSymbol() {
        return this.stack[this.pos - 1];
    }
}
