package com.flurry.org.apache.avro.io.parsing;

import java.util.Map;

public class Symbol$Repeater extends Symbol {
  public final Symbol end;
  
  private Symbol$Repeater(Symbol paramSymbol, Symbol... paramVarArgs) {
    super(Symbol$Kind.REPEATER, makeProduction(paramVarArgs));
    this.end = paramSymbol;
    this.production[0] = this;
  }
  
  private static Symbol[] makeProduction(Symbol[] paramArrayOfSymbol) {
    Symbol[] arrayOfSymbol = new Symbol[paramArrayOfSymbol.length + 1];
    System.arraycopy(paramArrayOfSymbol, 0, arrayOfSymbol, 1, paramArrayOfSymbol.length);
    return arrayOfSymbol;
  }
  
  public Symbol$Repeater flatten(Map paramMap1, Map paramMap2) {
    Symbol$Repeater symbol$Repeater = new Symbol$Repeater(this.end, new Symbol[flattenedSize(this.production, 1)]);
    flatten(this.production, 1, symbol$Repeater.production, 1, paramMap1, paramMap2);
    return symbol$Repeater;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\parsing\Symbol$Repeater.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */