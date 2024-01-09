package com.flurry.org.apache.avro.io.parsing;

import com.flurry.org.apache.avro.io.parsing.Parser;
import com.flurry.org.apache.avro.io.parsing.Symbol;

/* loaded from: classes.dex */
public class SkipParser extends Parser {
    static final /* synthetic */ boolean $assertionsDisabled;
    private final SkipHandler skipHandler;

    /* loaded from: classes.dex */
    public interface SkipHandler {
        void skipAction();

        void skipTopSymbol();
    }

    static {
        $assertionsDisabled = !SkipParser.class.desiredAssertionStatus();
    }

    public SkipParser(Symbol symbol, Parser.ActionHandler actionHandler, SkipHandler skipHandler) {
        super(symbol, actionHandler);
        this.skipHandler = skipHandler;
    }

    public final void skipRepeater() {
        int i = this.pos;
        Symbol[] symbolArr = this.stack;
        int i2 = this.pos - 1;
        this.pos = i2;
        Symbol symbol = symbolArr[i2];
        if (!$assertionsDisabled && symbol.kind != Symbol.Kind.REPEATER) {
            throw new AssertionError();
        }
        pushProduction(symbol);
        skipTo(i);
    }

    public final void skipSymbol(Symbol symbol) {
        int i = this.pos;
        pushSymbol(symbol);
        skipTo(i);
    }

    public final void skipTo(int i) {
        while (i < this.pos) {
            Symbol symbol = this.stack[this.pos - 1];
            if (symbol.kind == Symbol.Kind.TERMINAL) {
                this.skipHandler.skipTopSymbol();
            } else if (symbol.kind == Symbol.Kind.IMPLICIT_ACTION || symbol.kind == Symbol.Kind.EXPLICIT_ACTION) {
                this.skipHandler.skipAction();
            } else {
                this.pos--;
                pushProduction(symbol);
            }
        }
    }
}
