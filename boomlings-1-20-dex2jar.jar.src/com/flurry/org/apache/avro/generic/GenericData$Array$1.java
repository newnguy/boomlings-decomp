package com.flurry.org.apache.avro.generic;

import java.util.Iterator;

class GenericData$Array$1 implements Iterator {
  private int position = 0;
  
  final GenericData$Array this$0;
  
  public boolean hasNext() {
    return (this.position < GenericData$Array.access$100(GenericData$Array.this));
  }
  
  public Object next() {
    Object[] arrayOfObject = GenericData$Array.access$200(GenericData$Array.this);
    int i = this.position;
    this.position = i + 1;
    return arrayOfObject[i];
  }
  
  public void remove() {
    throw new UnsupportedOperationException();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\generic\GenericData$Array$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */