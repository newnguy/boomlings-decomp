package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.AvroTypeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.io.parsing.Parser;
import com.flurry.org.apache.avro.io.parsing.Symbol;
import com.flurry.org.apache.avro.io.parsing.ValidatingGrammarGenerator;
import com.flurry.org.apache.avro.util.Utf8;
import java.nio.ByteBuffer;

public class ValidatingEncoder extends ParsingEncoder implements Parser.ActionHandler {
  protected Encoder out;
  
  protected final Parser parser;
  
  ValidatingEncoder(Schema paramSchema, Encoder paramEncoder) {
    this((new ValidatingGrammarGenerator()).generate(paramSchema), paramEncoder);
  }
  
  ValidatingEncoder(Symbol paramSymbol, Encoder paramEncoder) {
    this.out = paramEncoder;
    this.parser = new Parser(paramSymbol, this);
  }
  
  public ValidatingEncoder configure(Encoder paramEncoder) {
    this.parser.reset();
    this.out = paramEncoder;
    return this;
  }
  
  public Symbol doAction(Symbol paramSymbol1, Symbol paramSymbol2) {
    return null;
  }
  
  public void flush() {
    this.out.flush();
  }
  
  public void setItemCount(long paramLong) {
    super.setItemCount(paramLong);
    this.out.setItemCount(paramLong);
  }
  
  public void startItem() {
    super.startItem();
    this.out.startItem();
  }
  
  public void writeArrayEnd() {
    this.parser.advance(Symbol.ARRAY_END);
    this.out.writeArrayEnd();
    pop();
  }
  
  public void writeArrayStart() {
    push();
    this.parser.advance(Symbol.ARRAY_START);
    this.out.writeArrayStart();
  }
  
  public void writeBoolean(boolean paramBoolean) {
    this.parser.advance(Symbol.BOOLEAN);
    this.out.writeBoolean(paramBoolean);
  }
  
  public void writeBytes(ByteBuffer paramByteBuffer) {
    this.parser.advance(Symbol.BYTES);
    this.out.writeBytes(paramByteBuffer);
  }
  
  public void writeBytes(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    this.parser.advance(Symbol.BYTES);
    this.out.writeBytes(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public void writeDouble(double paramDouble) {
    this.parser.advance(Symbol.DOUBLE);
    this.out.writeDouble(paramDouble);
  }
  
  public void writeEnum(int paramInt) {
    this.parser.advance(Symbol.ENUM);
    Symbol.IntCheckAction intCheckAction = (Symbol.IntCheckAction)this.parser.popSymbol();
    if (paramInt < 0 || paramInt >= intCheckAction.size)
      throw new AvroTypeException("Enumeration out of range: max is " + intCheckAction.size + " but received " + paramInt); 
    this.out.writeEnum(paramInt);
  }
  
  public void writeFixed(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    this.parser.advance(Symbol.FIXED);
    Symbol.IntCheckAction intCheckAction = (Symbol.IntCheckAction)this.parser.popSymbol();
    if (paramInt2 != intCheckAction.size)
      throw new AvroTypeException("Incorrect length for fixed binary: expected " + intCheckAction.size + " but received " + paramInt2 + " bytes."); 
    this.out.writeFixed(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public void writeFloat(float paramFloat) {
    this.parser.advance(Symbol.FLOAT);
    this.out.writeFloat(paramFloat);
  }
  
  public void writeIndex(int paramInt) {
    this.parser.advance(Symbol.UNION);
    Symbol.Alternative alternative = (Symbol.Alternative)this.parser.popSymbol();
    this.parser.pushSymbol(alternative.getSymbol(paramInt));
    this.out.writeIndex(paramInt);
  }
  
  public void writeInt(int paramInt) {
    this.parser.advance(Symbol.INT);
    this.out.writeInt(paramInt);
  }
  
  public void writeLong(long paramLong) {
    this.parser.advance(Symbol.LONG);
    this.out.writeLong(paramLong);
  }
  
  public void writeMapEnd() {
    this.parser.advance(Symbol.MAP_END);
    this.out.writeMapEnd();
    pop();
  }
  
  public void writeMapStart() {
    push();
    this.parser.advance(Symbol.MAP_START);
    this.out.writeMapStart();
  }
  
  public void writeNull() {
    this.parser.advance(Symbol.NULL);
    this.out.writeNull();
  }
  
  public void writeString(Utf8 paramUtf8) {
    this.parser.advance(Symbol.STRING);
    this.out.writeString(paramUtf8);
  }
  
  public void writeString(CharSequence paramCharSequence) {
    this.parser.advance(Symbol.STRING);
    this.out.writeString(paramCharSequence);
  }
  
  public void writeString(String paramString) {
    this.parser.advance(Symbol.STRING);
    this.out.writeString(paramString);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\ValidatingEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */