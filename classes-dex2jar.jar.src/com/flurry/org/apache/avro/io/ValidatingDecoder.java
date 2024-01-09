package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.AvroTypeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.io.parsing.Parser;
import com.flurry.org.apache.avro.io.parsing.Symbol;
import com.flurry.org.apache.avro.io.parsing.ValidatingGrammarGenerator;
import com.flurry.org.apache.avro.util.Utf8;
import java.nio.ByteBuffer;

public class ValidatingDecoder extends ParsingDecoder implements Parser.ActionHandler {
  protected Decoder in;
  
  ValidatingDecoder(Schema paramSchema, Decoder paramDecoder) {
    this(getSymbol(paramSchema), paramDecoder);
  }
  
  ValidatingDecoder(Symbol paramSymbol, Decoder paramDecoder) {
    super(paramSymbol);
    configure(paramDecoder);
  }
  
  private void checkFixed(int paramInt) {
    this.parser.advance(Symbol.FIXED);
    Symbol.IntCheckAction intCheckAction = (Symbol.IntCheckAction)this.parser.popSymbol();
    if (paramInt != intCheckAction.size)
      throw new AvroTypeException("Incorrect length for fixed binary: expected " + intCheckAction.size + " but received " + paramInt + " bytes."); 
  }
  
  private static Symbol getSymbol(Schema paramSchema) {
    if (paramSchema == null)
      throw new NullPointerException("Schema cannot be null"); 
    return (new ValidatingGrammarGenerator()).generate(paramSchema);
  }
  
  public long arrayNext() {
    this.parser.processTrailingImplicitActions();
    long l = this.in.arrayNext();
    if (l == 0L)
      this.parser.advance(Symbol.ARRAY_END); 
    return l;
  }
  
  public ValidatingDecoder configure(Decoder paramDecoder) {
    this.parser.reset();
    this.in = paramDecoder;
    return this;
  }
  
  public Symbol doAction(Symbol paramSymbol1, Symbol paramSymbol2) {
    return null;
  }
  
  public long mapNext() {
    this.parser.processTrailingImplicitActions();
    long l = this.in.mapNext();
    if (l == 0L)
      this.parser.advance(Symbol.MAP_END); 
    return l;
  }
  
  public long readArrayStart() {
    this.parser.advance(Symbol.ARRAY_START);
    long l = this.in.readArrayStart();
    if (l == 0L)
      this.parser.advance(Symbol.ARRAY_END); 
    return l;
  }
  
  public boolean readBoolean() {
    this.parser.advance(Symbol.BOOLEAN);
    return this.in.readBoolean();
  }
  
  public ByteBuffer readBytes(ByteBuffer paramByteBuffer) {
    this.parser.advance(Symbol.BYTES);
    return this.in.readBytes(paramByteBuffer);
  }
  
  public double readDouble() {
    this.parser.advance(Symbol.DOUBLE);
    return this.in.readDouble();
  }
  
  public int readEnum() {
    this.parser.advance(Symbol.ENUM);
    Symbol.IntCheckAction intCheckAction = (Symbol.IntCheckAction)this.parser.popSymbol();
    int i = this.in.readEnum();
    if (i < 0 || i >= intCheckAction.size)
      throw new AvroTypeException("Enumeration out of range: max is " + intCheckAction.size + " but received " + i); 
    return i;
  }
  
  public void readFixed(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    checkFixed(paramInt2);
    this.in.readFixed(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public float readFloat() {
    this.parser.advance(Symbol.FLOAT);
    return this.in.readFloat();
  }
  
  public int readIndex() {
    this.parser.advance(Symbol.UNION);
    Symbol.Alternative alternative = (Symbol.Alternative)this.parser.popSymbol();
    int i = this.in.readIndex();
    this.parser.pushSymbol(alternative.getSymbol(i));
    return i;
  }
  
  public int readInt() {
    this.parser.advance(Symbol.INT);
    return this.in.readInt();
  }
  
  public long readLong() {
    this.parser.advance(Symbol.LONG);
    return this.in.readLong();
  }
  
  public long readMapStart() {
    this.parser.advance(Symbol.MAP_START);
    long l = this.in.readMapStart();
    if (l == 0L)
      this.parser.advance(Symbol.MAP_END); 
    return l;
  }
  
  public void readNull() {
    this.parser.advance(Symbol.NULL);
    this.in.readNull();
  }
  
  public Utf8 readString(Utf8 paramUtf8) {
    this.parser.advance(Symbol.STRING);
    return this.in.readString(paramUtf8);
  }
  
  public String readString() {
    this.parser.advance(Symbol.STRING);
    return this.in.readString();
  }
  
  public long skipArray() {
    this.parser.advance(Symbol.ARRAY_START);
    for (long l = this.in.skipArray(); l != 0L; l = this.in.skipArray()) {
      while (l > 0L) {
        this.parser.skipRepeater();
        l--;
      } 
    } 
    this.parser.advance(Symbol.ARRAY_END);
    return 0L;
  }
  
  public void skipBytes() {
    this.parser.advance(Symbol.BYTES);
    this.in.skipBytes();
  }
  
  protected void skipFixed() {
    this.parser.advance(Symbol.FIXED);
    Symbol.IntCheckAction intCheckAction = (Symbol.IntCheckAction)this.parser.popSymbol();
    this.in.skipFixed(intCheckAction.size);
  }
  
  public void skipFixed(int paramInt) {
    checkFixed(paramInt);
    this.in.skipFixed(paramInt);
  }
  
  public long skipMap() {
    this.parser.advance(Symbol.MAP_START);
    for (long l = this.in.skipMap(); l != 0L; l = this.in.skipMap()) {
      while (l > 0L) {
        this.parser.skipRepeater();
        l--;
      } 
    } 
    this.parser.advance(Symbol.MAP_END);
    return 0L;
  }
  
  public void skipString() {
    this.parser.advance(Symbol.STRING);
    this.in.skipString();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\ValidatingDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */