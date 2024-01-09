package com.flurry.org.apache.avro.io.parsing;

import java.util.Iterator;
import java.util.NoSuchElementException;

class Symbol$Sequence$1 implements Iterator {
  private int pos = Symbol$Sequence.this.production.length;
  
  final Symbol$Sequence this$0;
  
  public boolean hasNext() {
    return (this.pos > 0);
  }
  
  public Symbol next() {
    if (this.pos > 0) {
      Symbol[] arrayOfSymbol = Symbol$Sequence.this.production;
      int i = this.pos - 1;
      this.pos = i;
      return arrayOfSymbol[i];
    } 
    throw new NoSuchElementException();
  }
  
  public void remove() {
    throw new UnsupportedOperationException();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\io\parsing\Symbol$Sequence$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */