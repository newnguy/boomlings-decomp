package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.AvroTypeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.io.parsing.JsonGrammarGenerator;
import com.flurry.org.apache.avro.io.parsing.Parser;
import com.flurry.org.apache.avro.io.parsing.Symbol;
import com.flurry.org.apache.avro.util.Utf8;
import com.flurry.org.codehaus.jackson.JsonEncoding;
import com.flurry.org.codehaus.jackson.JsonFactory;
import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.util.MinimalPrettyPrinter;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.BitSet;

/* loaded from: classes.dex */
public class JsonEncoder extends ParsingEncoder implements Parser.ActionHandler {
    protected BitSet isEmpty;
    private JsonGenerator out;
    final Parser parser;

    public JsonEncoder(Schema schema, JsonGenerator jsonGenerator) {
        this.isEmpty = new BitSet();
        configure(jsonGenerator);
        this.parser = new Parser(new JsonGrammarGenerator().generate(schema), this);
    }

    public JsonEncoder(Schema schema, OutputStream outputStream) {
        this(schema, getJsonGenerator(outputStream));
    }

    private static JsonGenerator getJsonGenerator(OutputStream outputStream) {
        if (outputStream == null) {
            throw new NullPointerException("OutputStream cannot be null");
        }
        JsonGenerator createJsonGenerator = new JsonFactory().createJsonGenerator(outputStream, JsonEncoding.UTF8);
        MinimalPrettyPrinter minimalPrettyPrinter = new MinimalPrettyPrinter();
        minimalPrettyPrinter.setRootValueSeparator(System.getProperty("line.separator"));
        createJsonGenerator.setPrettyPrinter(minimalPrettyPrinter);
        return createJsonGenerator;
    }

    private void writeByteArray(byte[] bArr, int i, int i2) {
        this.out.writeString(new String(bArr, i, i2, "ISO-8859-1"));
    }

    public JsonEncoder configure(JsonGenerator jsonGenerator) {
        if (jsonGenerator == null) {
            throw new NullPointerException("JsonGenerator cannot be null");
        }
        if (this.parser != null) {
            flush();
        }
        this.out = jsonGenerator;
        return this;
    }

    public JsonEncoder configure(OutputStream outputStream) {
        configure(getJsonGenerator(outputStream));
        return this;
    }

    @Override // com.flurry.org.apache.avro.io.parsing.Parser.ActionHandler
    public Symbol doAction(Symbol symbol, Symbol symbol2) {
        if (symbol2 instanceof Symbol.FieldAdjustAction) {
            this.out.writeFieldName(((Symbol.FieldAdjustAction) symbol2).fname);
            return null;
        } else if (symbol2 == Symbol.RECORD_START) {
            this.out.writeStartObject();
            return null;
        } else if (symbol2 == Symbol.RECORD_END || symbol2 == Symbol.UNION_END) {
            this.out.writeEndObject();
            return null;
        } else if (symbol2 != Symbol.FIELD_END) {
            throw new AvroTypeException("Unknown action symbol " + symbol2);
        } else {
            return null;
        }
    }

    @Override // java.io.Flushable
    public void flush() {
        this.parser.processImplicitActions();
        if (this.out != null) {
            this.out.flush();
        }
    }

    @Override // com.flurry.org.apache.avro.io.ParsingEncoder, com.flurry.org.apache.avro.io.Encoder
    public void startItem() {
        if (!this.isEmpty.get(this.pos)) {
            this.parser.advance(Symbol.ITEM_END);
        }
        super.startItem();
        this.isEmpty.clear(depth());
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeArrayEnd() {
        if (!this.isEmpty.get(this.pos)) {
            this.parser.advance(Symbol.ITEM_END);
        }
        pop();
        this.parser.advance(Symbol.ARRAY_END);
        this.out.writeEndArray();
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeArrayStart() {
        this.parser.advance(Symbol.ARRAY_START);
        this.out.writeStartArray();
        push();
        this.isEmpty.set(depth());
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeBoolean(boolean z) {
        this.parser.advance(Symbol.BOOLEAN);
        this.out.writeBoolean(z);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeBytes(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            writeBytes(byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining());
            return;
        }
        byte[] bArr = new byte[byteBuffer.remaining()];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = byteBuffer.get();
        }
        writeBytes(bArr);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeBytes(byte[] bArr, int i, int i2) {
        this.parser.advance(Symbol.BYTES);
        writeByteArray(bArr, i, i2);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeDouble(double d) {
        this.parser.advance(Symbol.DOUBLE);
        this.out.writeNumber(d);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeEnum(int i) {
        this.parser.advance(Symbol.ENUM);
        Symbol.EnumLabelsAction enumLabelsAction = (Symbol.EnumLabelsAction) this.parser.popSymbol();
        if (i < 0 || i >= enumLabelsAction.size) {
            throw new AvroTypeException("Enumeration out of range: max is " + enumLabelsAction.size + " but received " + i);
        }
        this.out.writeString(enumLabelsAction.getLabel(i));
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeFixed(byte[] bArr, int i, int i2) {
        this.parser.advance(Symbol.FIXED);
        Symbol.IntCheckAction intCheckAction = (Symbol.IntCheckAction) this.parser.popSymbol();
        if (i2 != intCheckAction.size) {
            throw new AvroTypeException("Incorrect length for fixed binary: expected " + intCheckAction.size + " but received " + i2 + " bytes.");
        }
        writeByteArray(bArr, i, i2);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeFloat(float f) {
        this.parser.advance(Symbol.FLOAT);
        this.out.writeNumber(f);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeIndex(int i) {
        this.parser.advance(Symbol.UNION);
        Symbol.Alternative alternative = (Symbol.Alternative) this.parser.popSymbol();
        Symbol symbol = alternative.getSymbol(i);
        if (symbol != Symbol.NULL) {
            this.out.writeStartObject();
            this.out.writeFieldName(alternative.getLabel(i));
            this.parser.pushSymbol(Symbol.UNION_END);
        }
        this.parser.pushSymbol(symbol);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeInt(int i) {
        this.parser.advance(Symbol.INT);
        this.out.writeNumber(i);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeLong(long j) {
        this.parser.advance(Symbol.LONG);
        this.out.writeNumber(j);
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeMapEnd() {
        if (!this.isEmpty.get(this.pos)) {
            this.parser.advance(Symbol.ITEM_END);
        }
        pop();
        this.parser.advance(Symbol.MAP_END);
        this.out.writeEndObject();
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeMapStart() {
        push();
        this.isEmpty.set(depth());
        this.parser.advance(Symbol.MAP_START);
        this.out.writeStartObject();
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeNull() {
        this.parser.advance(Symbol.NULL);
        this.out.writeNull();
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeString(Utf8 utf8) {
        writeString(utf8.toString());
    }

    @Override // com.flurry.org.apache.avro.io.Encoder
    public void writeString(String str) {
        this.parser.advance(Symbol.STRING);
        if (this.parser.topSymbol() != Symbol.MAP_KEY_MARKER) {
            this.out.writeString(str);
            return;
        }
        this.parser.advance(Symbol.MAP_KEY_MARKER);
        this.out.writeFieldName(str);
    }
}
