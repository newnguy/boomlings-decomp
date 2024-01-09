package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.AvroTypeException;
import com.flurry.org.apache.avro.Schema;
import com.flurry.org.apache.avro.io.parsing.ResolvingGrammarGenerator;
import com.flurry.org.apache.avro.io.parsing.Symbol;

public class ResolvingDecoder extends ValidatingDecoder {
  static final boolean $assertionsDisabled;
  
  private Decoder backup;
  
  static {
    boolean bool;
    if (!ResolvingDecoder.class.desiredAssertionStatus()) {
      bool = true;
    } else {
      bool = false;
    } 
    $assertionsDisabled = bool;
  }
  
  ResolvingDecoder(Schema paramSchema1, Schema paramSchema2, Decoder paramDecoder) {
    this(resolve(paramSchema1, paramSchema2), paramDecoder);
  }
  
  private ResolvingDecoder(Object paramObject, Decoder paramDecoder) {
    super((Symbol)paramObject, paramDecoder);
  }
  
  public static Object resolve(Schema paramSchema1, Schema paramSchema2) {
    if (paramSchema1 == null)
      throw new NullPointerException("writer cannot be null!"); 
    if (paramSchema2 == null)
      throw new NullPointerException("reader cannot be null!"); 
    return (new ResolvingGrammarGenerator()).generate(paramSchema1, paramSchema2);
  }
  
  public Symbol doAction(Symbol paramSymbol1, Symbol paramSymbol2) {
    if (paramSymbol2 instanceof Symbol.FieldOrderAction) {
      if (paramSymbol1 != Symbol.FIELD_ACTION)
        paramSymbol2 = null; 
      return paramSymbol2;
    } 
    if (paramSymbol2 instanceof Symbol.ResolvingAction) {
      Symbol.ResolvingAction resolvingAction = (Symbol.ResolvingAction)paramSymbol2;
      if (resolvingAction.reader != paramSymbol1)
        throw new AvroTypeException("Found " + resolvingAction.reader + " while looking for " + paramSymbol1); 
      return resolvingAction.writer;
    } 
    if (paramSymbol2 instanceof Symbol.SkipAction) {
      paramSymbol1 = ((Symbol.SkipAction)paramSymbol2).symToSkip;
      this.parser.skipSymbol(paramSymbol1);
    } else if (paramSymbol2 instanceof Symbol.WriterUnionAction) {
      Symbol.Alternative alternative = (Symbol.Alternative)this.parser.popSymbol();
      this.parser.pushSymbol(alternative.getSymbol(this.in.readIndex()));
    } else {
      if (paramSymbol2 instanceof Symbol.ErrorAction)
        throw new AvroTypeException(((Symbol.ErrorAction)paramSymbol2).msg); 
      if (paramSymbol2 instanceof Symbol.DefaultStartAction) {
        Symbol.DefaultStartAction defaultStartAction = (Symbol.DefaultStartAction)paramSymbol2;
        this.backup = this.in;
        this.in = DecoderFactory.get().binaryDecoder(defaultStartAction.contents, (BinaryDecoder)null);
      } else if (paramSymbol2 == Symbol.DEFAULT_END_ACTION) {
        this.in = this.backup;
      } else {
        throw new AvroTypeException("Unknown action: " + paramSymbol2);
      } 
    } 
    return null;
  }
  
  public final void drain() {
    this.parser.processImplicitActions();
  }
  
  public double readDouble() {
    Symbol symbol = this.parser.advance(Symbol.DOUBLE);
    if (symbol == Symbol.INT)
      return this.in.readInt(); 
    if (symbol == Symbol.LONG)
      return this.in.readLong(); 
    if (symbol == Symbol.FLOAT)
      return this.in.readFloat(); 
    assert symbol == Symbol.DOUBLE;
    return this.in.readDouble();
  }
  
  public int readEnum() {
    this.parser.advance(Symbol.ENUM);
    Symbol.EnumAdjustAction enumAdjustAction = (Symbol.EnumAdjustAction)this.parser.popSymbol();
    int i = this.in.readEnum();
    Object object = enumAdjustAction.adjustments[i];
    if (object instanceof Integer)
      return ((Integer)object).intValue(); 
    throw new AvroTypeException((String)object);
  }
  
  public final Schema.Field[] readFieldOrder() {
    return ((Symbol.FieldOrderAction)this.parser.advance(Symbol.FIELD_ACTION)).fields;
  }
  
  public float readFloat() {
    Symbol symbol = this.parser.advance(Symbol.FLOAT);
    if (symbol == Symbol.INT)
      return this.in.readInt(); 
    if (symbol == Symbol.LONG)
      return (float)this.in.readLong(); 
    assert symbol == Symbol.FLOAT;
    return this.in.readFloat();
  }
  
  public int readIndex() {
    this.parser.advance(Symbol.UNION);
    Symbol.UnionAdjustAction unionAdjustAction = (Symbol.UnionAdjustAction)this.parser.popSymbol();
    this.parser.pushSymbol(unionAdjustAction.symToParse);
    return unionAdjustAction.rindex;
  }
  
  public long readLong() {
    Symbol symbol = this.parser.advance(Symbol.LONG);
    if (symbol == Symbol.INT)
      return this.in.readInt(); 
    if (symbol == Symbol.DOUBLE)
      return (long)this.in.readDouble(); 
    assert symbol == Symbol.LONG;
    return this.in.readLong();
  }
  
  public void skipAction() {
    Symbol.Alternative alternative;
    Symbol.DefaultStartAction defaultStartAction;
    Symbol symbol = this.parser.popSymbol();
    if (symbol instanceof Symbol.ResolvingAction) {
      this.parser.pushSymbol(((Symbol.ResolvingAction)symbol).writer);
      return;
    } 
    if (symbol instanceof Symbol.SkipAction) {
      this.parser.pushSymbol(((Symbol.SkipAction)symbol).symToSkip);
      return;
    } 
    if (symbol instanceof Symbol.WriterUnionAction) {
      alternative = (Symbol.Alternative)this.parser.popSymbol();
      this.parser.pushSymbol(alternative.getSymbol(this.in.readIndex()));
      return;
    } 
    if (alternative instanceof Symbol.ErrorAction)
      throw new AvroTypeException(((Symbol.ErrorAction)alternative).msg); 
    if (alternative instanceof Symbol.DefaultStartAction) {
      defaultStartAction = (Symbol.DefaultStartAction)alternative;
      this.backup = this.in;
      this.in = DecoderFactory.get().binaryDecoder(defaultStartAction.contents, (BinaryDecoder)null);
      return;
    } 
    if (defaultStartAction == Symbol.DEFAULT_END_ACTION)
      this.in = this.backup; 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\io\ResolvingDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */