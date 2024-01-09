package com.flurry.org.apache.avro.io.parsing;

import java.util.Map;

public class Symbol$Alternative extends Symbol {
  public final String[] labels;
  
  public final Symbol[] symbols;
  
  private Symbol$Alternative(Symbol[] paramArrayOfSymbol, String[] paramArrayOfString) {
    super(Symbol$Kind.ALTERNATIVE);
    this.symbols = paramArrayOfSymbol;
    this.labels = paramArrayOfString;
  }
  
  public int findLabel(String paramString) {
    if (paramString != null) {
      byte b1 = 0;
      while (b1 < this.labels.length) {
        if (!paramString.equals(this.labels[b1])) {
          b1++;
          continue;
        } 
        return b1;
      } 
    } 
    byte b = -1;
    while (b < this.labels.length) {
      if (!paramString.equals(this.labels[b])) {
        b++;
        continue;
      } 
      return b;
    } 
  }
  
  public Symbol$Alternative flatten(Map paramMap1, Map paramMap2) {
    Symbol[] arrayOfSymbol = new Symbol[this.symbols.length];
    for (byte b = 0; b < arrayOfSymbol.length; b++)
      arrayOfSymbol[b] = this.symbols[b].flatten(paramMap1, paramMap2); 
    return new Symbol$Alternative(arrayOfSymbol, this.labels);
  }
  
  public String getLabel(int paramInt) {
    return this.labels[paramInt];
  }
  
  public Symbol getSymbol(int paramInt) {
    return this.symbols[paramInt];
  }
  
  public int size() {
    return this.symbols.length;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\parsing\Symbol$Alternative.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */