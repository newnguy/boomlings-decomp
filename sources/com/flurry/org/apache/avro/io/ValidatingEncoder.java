package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.AvroTypeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.io.parsing.Parser;
import com.flurry.org.apache.avro.io.parsing.Symbol;
import com.flurry.org.apache.avro.io.parsing.ValidatingGrammarGenerator;
import com.flurry.org.apache.avro.util.Utf8;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class ValidatingEncoder extends ParsingEncoder implements Parser.ActionHandler {
    protected Encoder out;
    protected final Parser parser;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ValidatingEncoder(Schema schema, Encoder encoder) {
        this(new ValidatingGrammarGenerator().generate(schema), encoder);
    }

    ValidatingEncoder(Symbol symbol, Encoder encoder) {
        this.out = encoder;
        this.parser = new Parser(symbol, this);
    }

    public ValidatingEncoder configure(Encoder encoder) {
        this.parser.reset();
        this.out = encoder;
        return this;
    }

    @Override // com.flurry.org.apache.avro.io.parsing.Parser.ActionHandler
    public Symbol doAction(Symbol symbol, Symbol symbol2) {
        return null;
    }

    @Override // java.io.Flushable
    public void flush() {
        this.out.flush();
    }

    @Override // com.flurry.org.apache.avro.io.ParsingEncoder, com.flurry.org.apache.avro.io.Encoder
    public void setItemCount(long j) {
        super.setItemCount(j);
        this.out.setItemCount(j);
    }

    @Override // com.flurry.org.apache.avro.io.ParsingEncoder, com.flurry.org.apache.avro.io.Encoder
    public void startItem() {
        super.startItem();
        this.out.startItem();
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeArrayEnd() {
        this.parser.advance(Symbol.ARRAY_END);
        this.out.writeArrayEnd();
        pop();
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeArrayStart() {
        push();
        this.parser.advance(Symbol.ARRAY_START);
        this.out.writeArrayStart();
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeBoolean(boolean z) {
        this.parser.advance(Symbol.BOOLEAN);
        this.out.writeBoolean(z);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeBytes(ByteBuffer byteBuffer) {
        this.parser.advance(Symbol.BYTES);
        this.out.writeBytes(byteBuffer);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeBytes(byte[] bArr, int i, int i2) {
        this.parser.advance(Symbol.BYTES);
        this.out.writeBytes(bArr, i, i2);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeDouble(double d) {
        this.parser.advance(Symbol.DOUBLE);
        this.out.writeDouble(d);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeEnum(int i) {
        this.parser.advance(Symbol.ENUM);
        Symbol.IntCheckAction intCheckAction = (Symbol.IntCheckAction) this.parser.popSymbol();
        if (i < 0 || i >= intCheckAction.size) {
            throw new AvroTypeException("Enumeration out of range: max is " + intCheckAction.size + " but received " + i);
        }
        this.out.writeEnum(i);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeFixed(byte[] bArr, int i, int i2) {
        this.parser.advance(Symbol.FIXED);
        Symbol.IntCheckAction intCheckAction = (Symbol.IntCheckAction) this.parser.popSymbol();
        if (i2 != intCheckAction.size) {
            throw new AvroTypeException("Incorrect length for fixed binary: expected " + intCheckAction.size + " but received " + i2 + " bytes.");
        }
        this.out.writeFixed(bArr, i, i2);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeFloat(float f) {
        this.parser.advance(Symbol.FLOAT);
        this.out.writeFloat(f);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeIndex(int i) {
        this.parser.advance(Symbol.UNION);
        this.parser.pushSymbol(((Symbol.Alternative) this.parser.popSymbol()).getSymbol(i));
        this.out.writeIndex(i);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeInt(int i) {
        this.parser.advance(Symbol.INT);
        this.out.writeInt(i);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeLong(long j) {
        this.parser.advance(Symbol.LONG);
        this.out.writeLong(j);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeMapEnd() {
        this.parser.advance(Symbol.MAP_END);
        this.out.writeMapEnd();
        pop();
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeMapStart() {
        push();
        this.parser.advance(Symbol.MAP_START);
        this.out.writeMapStart();
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeNull() {
        this.parser.advance(Symbol.NULL);
        this.out.writeNull();
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeString(Utf8 utf8) {
        this.parser.advance(Symbol.STRING);
        this.out.writeString(utf8);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeString(CharSequence charSequence) {
        this.parser.advance(Symbol.STRING);
        this.out.writeString(charSequence);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeString(String str) {
        this.parser.advance(Symbol.STRING);
        this.out.writeString(str);
    }
}
