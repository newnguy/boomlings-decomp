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
import com.flurry.org.codehaus.jackson.PrettyPrinter;
import com.flurry.org.codehaus.jackson.util.MinimalPrettyPrinter;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.BitSet;

public class JsonEncoder extends ParsingEncoder implements Parser.ActionHandler {
  protected BitSet isEmpty = new BitSet();
  
  private JsonGenerator out;
  
  final Parser parser;
  
  JsonEncoder(Schema paramSchema, JsonGenerator paramJsonGenerator) {
    configure(paramJsonGenerator);
    this.parser = new Parser((new JsonGrammarGenerator()).generate(paramSchema), this);
  }
  
  JsonEncoder(Schema paramSchema, OutputStream paramOutputStream) {
    this(paramSchema, getJsonGenerator(paramOutputStream));
  }
  
  private static JsonGenerator getJsonGenerator(OutputStream paramOutputStream) {
    if (paramOutputStream == null)
      throw new NullPointerException("OutputStream cannot be null"); 
    JsonGenerator jsonGenerator = (new JsonFactory()).createJsonGenerator(paramOutputStream, JsonEncoding.UTF8);
    MinimalPrettyPrinter minimalPrettyPrinter = new MinimalPrettyPrinter();
    minimalPrettyPrinter.setRootValueSeparator(System.getProperty("line.separator"));
    jsonGenerator.setPrettyPrinter((PrettyPrinter)minimalPrettyPrinter);
    return jsonGenerator;
  }
  
  private void writeByteArray(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    this.out.writeString(new String(paramArrayOfbyte, paramInt1, paramInt2, "ISO-8859-1"));
  }
  
  public JsonEncoder configure(JsonGenerator paramJsonGenerator) {
    if (paramJsonGenerator == null)
      throw new NullPointerException("JsonGenerator cannot be null"); 
    if (this.parser != null)
      flush(); 
    this.out = paramJsonGenerator;
    return this;
  }
  
  public JsonEncoder configure(OutputStream paramOutputStream) {
    configure(getJsonGenerator(paramOutputStream));
    return this;
  }
  
  public Symbol doAction(Symbol paramSymbol1, Symbol paramSymbol2) {
    if (paramSymbol2 instanceof Symbol.FieldAdjustAction) {
      Symbol.FieldAdjustAction fieldAdjustAction = (Symbol.FieldAdjustAction)paramSymbol2;
      this.out.writeFieldName(fieldAdjustAction.fname);
      return null;
    } 
    if (paramSymbol2 == Symbol.RECORD_START) {
      this.out.writeStartObject();
      return null;
    } 
    if (paramSymbol2 == Symbol.RECORD_END || paramSymbol2 == Symbol.UNION_END) {
      this.out.writeEndObject();
      return null;
    } 
    if (paramSymbol2 != Symbol.FIELD_END)
      throw new AvroTypeException("Unknown action symbol " + paramSymbol2); 
    return null;
  }
  
  public void flush() {
    this.parser.processImplicitActions();
    if (this.out != null)
      this.out.flush(); 
  }
  
  public void startItem() {
    if (!this.isEmpty.get(this.pos))
      this.parser.advance(Symbol.ITEM_END); 
    super.startItem();
    this.isEmpty.clear(depth());
  }
  
  public void writeArrayEnd() {
    if (!this.isEmpty.get(this.pos))
      this.parser.advance(Symbol.ITEM_END); 
    pop();
    this.parser.advance(Symbol.ARRAY_END);
    this.out.writeEndArray();
  }
  
  public void writeArrayStart() {
    this.parser.advance(Symbol.ARRAY_START);
    this.out.writeStartArray();
    push();
    this.isEmpty.set(depth());
  }
  
  public void writeBoolean(boolean paramBoolean) {
    this.parser.advance(Symbol.BOOLEAN);
    this.out.writeBoolean(paramBoolean);
  }
  
  public void writeBytes(ByteBuffer paramByteBuffer) {
    if (paramByteBuffer.hasArray()) {
      writeBytes(paramByteBuffer.array(), paramByteBuffer.position(), paramByteBuffer.remaining());
      return;
    } 
    byte[] arrayOfByte = new byte[paramByteBuffer.remaining()];
    for (byte b = 0; b < arrayOfByte.length; b++)
      arrayOfByte[b] = paramByteBuffer.get(); 
    writeBytes(arrayOfByte);
  }
  
  public void writeBytes(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    this.parser.advance(Symbol.BYTES);
    writeByteArray(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public void writeDouble(double paramDouble) {
    this.parser.advance(Symbol.DOUBLE);
    this.out.writeNumber(paramDouble);
  }
  
  public void writeEnum(int paramInt) {
    this.parser.advance(Symbol.ENUM);
    Symbol.EnumLabelsAction enumLabelsAction = (Symbol.EnumLabelsAction)this.parser.popSymbol();
    if (paramInt < 0 || paramInt >= enumLabelsAction.size)
      throw new AvroTypeException("Enumeration out of range: max is " + enumLabelsAction.size + " but received " + paramInt); 
    this.out.writeString(enumLabelsAction.getLabel(paramInt));
  }
  
  public void writeFixed(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    this.parser.advance(Symbol.FIXED);
    Symbol.IntCheckAction intCheckAction = (Symbol.IntCheckAction)this.parser.popSymbol();
    if (paramInt2 != intCheckAction.size)
      throw new AvroTypeException("Incorrect length for fixed binary: expected " + intCheckAction.size + " but received " + paramInt2 + " bytes."); 
    writeByteArray(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public void writeFloat(float paramFloat) {
    this.parser.advance(Symbol.FLOAT);
    this.out.writeNumber(paramFloat);
  }
  
  public void writeIndex(int paramInt) {
    this.parser.advance(Symbol.UNION);
    Symbol.Alternative alternative = (Symbol.Alternative)this.parser.popSymbol();
    Symbol symbol = alternative.getSymbol(paramInt);
    if (symbol != Symbol.NULL) {
      this.out.writeStartObject();
      this.out.writeFieldName(alternative.getLabel(paramInt));
      this.parser.pushSymbol(Symbol.UNION_END);
    } 
    this.parser.pushSymbol(symbol);
  }
  
  public void writeInt(int paramInt) {
    this.parser.advance(Symbol.INT);
    this.out.writeNumber(paramInt);
  }
  
  public void writeLong(long paramLong) {
    this.parser.advance(Symbol.LONG);
    this.out.writeNumber(paramLong);
  }
  
  public void writeMapEnd() {
    if (!this.isEmpty.get(this.pos))
      this.parser.advance(Symbol.ITEM_END); 
    pop();
    this.parser.advance(Symbol.MAP_END);
    this.out.writeEndObject();
  }
  
  public void writeMapStart() {
    push();
    this.isEmpty.set(depth());
    this.parser.advance(Symbol.MAP_START);
    this.out.writeStartObject();
  }
  
  public void writeNull() {
    this.parser.advance(Symbol.NULL);
    this.out.writeNull();
  }
  
  public void writeString(Utf8 paramUtf8) {
    writeString(paramUtf8.toString());
  }
  
  public void writeString(String paramString) {
    this.parser.advance(Symbol.STRING);
    if (this.parser.topSymbol() == Symbol.MAP_KEY_MARKER) {
      this.parser.advance(Symbol.MAP_KEY_MARKER);
      this.out.writeFieldName(paramString);
      return;
    } 
    this.out.writeString(paramString);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\JsonEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */