package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.io.parsing.Parser;
import com.flurry.org.apache.avro.io.parsing.SkipParser;
import com.flurry.org.apache.avro.io.parsing.Symbol;

/* loaded from: classes.dex */
public abstract class ParsingDecoder extends Decoder implements Parser.ActionHandler, SkipParser.SkipHandler {
    protected final SkipParser parser;

    /* JADX INFO: Access modifiers changed from: protected */
    public ParsingDecoder(Symbol symbol) {
        this.parser = new SkipParser(symbol, this, this);
    }

    @Override // com.flurry.org.apache.avro.io.parsing.SkipParser.SkipHandler
    public void skipAction() {
        this.parser.popSymbol();
    }

    protected abstract void skipFixed();

    @Override // com.flurry.org.apache.avro.io.parsing.SkipParser.SkipHandler
    public void skipTopSymbol() {
        Symbol symbol = this.parser.topSymbol();
        if (symbol == Symbol.NULL) {
            readNull();
        }
        if (symbol == Symbol.BOOLEAN) {
            readBoolean();
        } else if (symbol == Symbol.INT) {
            readInt();
        } else if (symbol == Symbol.LONG) {
            readLong();
        } else if (symbol == Symbol.FLOAT) {
            readFloat();
        } else if (symbol == Symbol.DOUBLE) {
            readDouble();
        } else if (symbol == Symbol.STRING) {
            skipString();
        } else if (symbol == Symbol.BYTES) {
            skipBytes();
        } else if (symbol == Symbol.ENUM) {
            readEnum();
        } else if (symbol == Symbol.FIXED) {
            skipFixed();
        } else if (symbol == Symbol.UNION) {
            readIndex();
        } else if (symbol == Symbol.ARRAY_START) {
            skipArray();
        } else if (symbol == Symbol.MAP_START) {
            skipMap();
        }
    }
}
