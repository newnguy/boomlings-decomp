package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.AvroTypeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.io.parsing.Parser;
import com.flurry.org.apache.avro.io.parsing.Symbol;
import com.flurry.org.apache.avro.io.parsing.ValidatingGrammarGenerator;
import com.flurry.org.apache.avro.util.Utf8;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class ValidatingDecoder extends ParsingDecoder implements Parser.ActionHandler {
    protected Decoder in;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ValidatingDecoder(Schema schema, Decoder decoder) {
        this(getSymbol(schema), decoder);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ValidatingDecoder(Symbol symbol, Decoder decoder) {
        super(symbol);
        configure(decoder);
    }

    private void checkFixed(int i) {
        this.parser.advance(Symbol.FIXED);
        Symbol.IntCheckAction intCheckAction = (Symbol.IntCheckAction) this.parser.popSymbol();
        if (i != intCheckAction.size) {
            throw new AvroTypeException("Incorrect length for fixed binary: expected " + intCheckAction.size + " but received " + i + " bytes.");
        }
    }

    private static Symbol getSymbol(Schema schema) {
        if (schema == null) {
            throw new NullPointerException("Schema cannot be null");
        }
        return new ValidatingGrammarGenerator().generate(schema);
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public long arrayNext() {
        this.parser.processTrailingImplicitActions();
        long arrayNext = this.in.arrayNext();
        if (arrayNext == 0) {
            this.parser.advance(Symbol.ARRAY_END);
        }
        return arrayNext;
    }

    public ValidatingDecoder configure(Decoder decoder) {
        this.parser.reset();
        this.in = decoder;
        return this;
    }

    public Symbol doAction(Symbol symbol, Symbol symbol2) {
        return null;
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public long mapNext() {
        this.parser.processTrailingImplicitActions();
        long mapNext = this.in.mapNext();
        if (mapNext == 0) {
            this.parser.advance(Symbol.MAP_END);
        }
        return mapNext;
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public long readArrayStart() {
        this.parser.advance(Symbol.ARRAY_START);
        long readArrayStart = this.in.readArrayStart();
        if (readArrayStart == 0) {
            this.parser.advance(Symbol.ARRAY_END);
        }
        return readArrayStart;
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public boolean readBoolean() {
        this.parser.advance(Symbol.BOOLEAN);
        return this.in.readBoolean();
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public ByteBuffer readBytes(ByteBuffer byteBuffer) {
        this.parser.advance(Symbol.BYTES);
        return this.in.readBytes(byteBuffer);
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public double readDouble() {
        this.parser.advance(Symbol.DOUBLE);
        return this.in.readDouble();
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public int readEnum() {
        this.parser.advance(Symbol.ENUM);
        Symbol.IntCheckAction intCheckAction = (Symbol.IntCheckAction) this.parser.popSymbol();
        int readEnum = this.in.readEnum();
        if (readEnum < 0 || readEnum >= intCheckAction.size) {
            throw new AvroTypeException("Enumeration out of range: max is " + intCheckAction.size + " but received " + readEnum);
        }
        return readEnum;
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public void readFixed(byte[] bArr, int i, int i2) {
        checkFixed(i2);
        this.in.readFixed(bArr, i, i2);
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public float readFloat() {
        this.parser.advance(Symbol.FLOAT);
        return this.in.readFloat();
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public int readIndex() {
        this.parser.advance(Symbol.UNION);
        int readIndex = this.in.readIndex();
        this.parser.pushSymbol(((Symbol.Alternative) this.parser.popSymbol()).getSymbol(readIndex));
        return readIndex;
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public int readInt() {
        this.parser.advance(Symbol.INT);
        return this.in.readInt();
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public long readLong() {
        this.parser.advance(Symbol.LONG);
        return this.in.readLong();
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public long readMapStart() {
        this.parser.advance(Symbol.MAP_START);
        long readMapStart = this.in.readMapStart();
        if (readMapStart == 0) {
            this.parser.advance(Symbol.MAP_END);
        }
        return readMapStart;
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public void readNull() {
        this.parser.advance(Symbol.NULL);
        this.in.readNull();
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public Utf8 readString(Utf8 utf8) {
        this.parser.advance(Symbol.STRING);
        return this.in.readString(utf8);
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public String readString() {
        this.parser.advance(Symbol.STRING);
        return this.in.readString();
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public long skipArray() {
        this.parser.advance(Symbol.ARRAY_START);
        long skipArray = this.in.skipArray();
        while (skipArray != 0) {
            while (true) {
                long j = skipArray - 1;
                if (skipArray > 0) {
                    this.parser.skipRepeater();
                    skipArray = j;
                }
            }
            skipArray = this.in.skipArray();
        }
        this.parser.advance(Symbol.ARRAY_END);
        return 0L;
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public void skipBytes() {
        this.parser.advance(Symbol.BYTES);
        this.in.skipBytes();
    }

    @Override // com.flurry.org.apache.avro.io.ParsingDecoder
    protected void skipFixed() {
        this.parser.advance(Symbol.FIXED);
        this.in.skipFixed(((Symbol.IntCheckAction) this.parser.popSymbol()).size);
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public void skipFixed(int i) {
        checkFixed(i);
        this.in.skipFixed(i);
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public long skipMap() {
        this.parser.advance(Symbol.MAP_START);
        long skipMap = this.in.skipMap();
        while (skipMap != 0) {
            while (true) {
                long j = skipMap - 1;
                if (skipMap > 0) {
                    this.parser.skipRepeater();
                    skipMap = j;
                }
            }
            skipMap = this.in.skipMap();
        }
        this.parser.advance(Symbol.MAP_END);
        return 0L;
    }

    @Override // com.flurry.org.apache.avro.io.Decoder
    public void skipString() {
        this.parser.advance(Symbol.STRING);
        this.in.skipString();
    }
}
