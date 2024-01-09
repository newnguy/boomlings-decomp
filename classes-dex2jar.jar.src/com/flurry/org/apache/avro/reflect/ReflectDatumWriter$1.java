package com.flurry.org.apache.avro.reflect;

import java.lang.reflect.Array;
import java.util.Iterator;

class ReflectDatumWriter$1 implements Iterator {
  private int i = 0;
  
  private final int length = Array.getLength(array);
  
  final ReflectDatumWriter this$0;
  
  final Object val$array;
  
  public boolean hasNext() {
    return (this.i < this.length);
  }
  
  public Object next() {
    Object object = array;
    int i = this.i;
    this.i = i + 1;
    return Array.get(object, i);
  }
  
  public void remove() {
    throw new UnsupportedOperationException();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\reflect\ReflectDatumWriter$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */