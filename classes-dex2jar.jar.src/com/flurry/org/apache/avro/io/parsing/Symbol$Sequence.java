package com.flurry.org.apache.avro.io.parsing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Symbol$Sequence extends Symbol implements Iterable {
  private Symbol$Sequence(Symbol[] paramArrayOfSymbol) {
    super(Symbol$Kind.SEQUENCE, paramArrayOfSymbol);
  }
  
  public Symbol$Sequence flatten(Map<Symbol$Sequence, Symbol$Sequence> paramMap1, Map<Symbol$Sequence, ArrayList> paramMap2) {
    Symbol$Sequence symbol$Sequence2 = (Symbol$Sequence)paramMap1.get(this);
    Symbol$Sequence symbol$Sequence1 = symbol$Sequence2;
    if (symbol$Sequence2 == null) {
      symbol$Sequence1 = new Symbol$Sequence(new Symbol[flattenedSize()]);
      paramMap1.put(this, symbol$Sequence1);
      ArrayList arrayList = new ArrayList();
      paramMap2.put(symbol$Sequence1, arrayList);
      flatten(this.production, 0, symbol$Sequence1.production, 0, paramMap1, paramMap2);
      for (Symbol$Fixup symbol$Fixup : arrayList)
        System.arraycopy(symbol$Sequence1.production, 0, symbol$Fixup.symbols, symbol$Fixup.pos, symbol$Sequence1.production.length); 
      paramMap2.remove(symbol$Sequence1);
    } 
    return symbol$Sequence1;
  }
  
  public final int flattenedSize() {
    return flattenedSize(this.production, 0);
  }
  
  public Symbol get(int paramInt) {
    return this.production[paramInt];
  }
  
  public Iterator iterator() {
    return new Symbol$Sequence$1(this);
  }
  
  public int size() {
    return this.production.length;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\parsing\Symbol$Sequence.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */