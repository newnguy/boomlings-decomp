package com.flurry.org.codehaus.jackson.map.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class ArrayBuilders$ArrayIterator implements Iterable, Iterator {
  private final Object[] _array;
  
  private int _index;
  
  public ArrayBuilders$ArrayIterator(Object[] paramArrayOfObject) {
    this._array = paramArrayOfObject;
    this._index = 0;
  }
  
  public boolean hasNext() {
    return (this._index < this._array.length);
  }
  
  public Iterator iterator() {
    return this;
  }
  
  public Object next() {
    if (this._index >= this._array.length)
      throw new NoSuchElementException(); 
    Object[] arrayOfObject = this._array;
    int i = this._index;
    this._index = i + 1;
    return arrayOfObject[i];
  }
  
  public void remove() {
    throw new UnsupportedOperationException();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\ma\\util\ArrayBuilders$ArrayIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */