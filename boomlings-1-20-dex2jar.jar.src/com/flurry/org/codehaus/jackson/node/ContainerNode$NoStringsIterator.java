package com.flurry.org.codehaus.jackson.node;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ContainerNode$NoStringsIterator implements Iterator {
  static final ContainerNode$NoStringsIterator instance = new ContainerNode$NoStringsIterator();
  
  public static ContainerNode$NoStringsIterator instance() {
    return instance;
  }
  
  public boolean hasNext() {
    return false;
  }
  
  public String next() {
    throw new NoSuchElementException();
  }
  
  public void remove() {
    throw new IllegalStateException();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\node\ContainerNode$NoStringsIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */