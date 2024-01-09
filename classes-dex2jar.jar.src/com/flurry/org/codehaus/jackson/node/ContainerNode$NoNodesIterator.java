package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonNode;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ContainerNode$NoNodesIterator implements Iterator {
  static final ContainerNode$NoNodesIterator instance = new ContainerNode$NoNodesIterator();
  
  public static ContainerNode$NoNodesIterator instance() {
    return instance;
  }
  
  public boolean hasNext() {
    return false;
  }
  
  public JsonNode next() {
    throw new NoSuchElementException();
  }
  
  public void remove() {
    throw new IllegalStateException();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\node\ContainerNode$NoNodesIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */