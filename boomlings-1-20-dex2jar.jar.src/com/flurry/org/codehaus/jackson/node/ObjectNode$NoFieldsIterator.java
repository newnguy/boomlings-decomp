package com.flurry.org.codehaus.jackson.node;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class ObjectNode$NoFieldsIterator implements Iterator {
  static final ObjectNode$NoFieldsIterator instance = new ObjectNode$NoFieldsIterator();
  
  public boolean hasNext() {
    return false;
  }
  
  public Map.Entry next() {
    throw new NoSuchElementException();
  }
  
  public void remove() {
    throw new IllegalStateException();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\node\ObjectNode$NoFieldsIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */