package com.flurry.org.apache.avro.io;

import com.flurry.org.apache.avro.io.parsing.Parser;
import com.flurry.org.apache.avro.io.parsing.SkipParser;
import com.flurry.org.apache.avro.io.parsing.Symbol;

public abstract class ParsingDecoder extends Decoder implements Parser.ActionHandler, SkipParser.SkipHandler {
  protected final SkipParser parser;
  
  protected ParsingDecoder(Symbol paramSymbol) {
    this.parser = new SkipParser(paramSymbol, this, this);
  }
  
  public void skipAction() {
    this.parser.popSymbol();
  }
  
  protected abstract void skipFixed();
  
  public void skipTopSymbol() {
    Symbol symbol = this.parser.topSymbol();
    if (symbol == Symbol.NULL)
      readNull(); 
    if (symbol == Symbol.BOOLEAN) {
      readBoolean();
      return;
    } 
    if (symbol == Symbol.INT) {
      readInt();
      return;
    } 
    if (symbol == Symbol.LONG) {
      readLong();
      return;
    } 
    if (symbol == Symbol.FLOAT) {
      readFloat();
      return;
    } 
    if (symbol == Symbol.DOUBLE) {
      readDouble();
      return;
    } 
    if (symbol == Symbol.STRING) {
      skipString();
      return;
    } 
    if (symbol == Symbol.BYTES) {
      skipBytes();
      return;
    } 
    if (symbol == Symbol.ENUM) {
      readEnum();
      return;
    } 
    if (symbol == Symbol.FIXED) {
      skipFixed();
      return;
    } 
    if (symbol == Symbol.UNION) {
      readIndex();
      return;
    } 
    if (symbol == Symbol.ARRAY_START) {
      skipArray();
      return;
    } 
    if (symbol == Symbol.MAP_START)
      skipMap(); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\ParsingDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */