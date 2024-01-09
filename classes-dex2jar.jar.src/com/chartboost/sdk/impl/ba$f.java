package com.chartboost.sdk.impl;

import java.util.Iterator;

class ba$f implements Iterator {
  private final Iterator a;
  
  public ba$f(Iterator paramIterator) {
    this.a = paramIterator;
  }
  
  public boolean hasNext() {
    return this.a.hasNext();
  }
  
  public Object next() {
    return this.a.next();
  }
  
  public void remove() {
    throw new UnsupportedOperationException();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\ba$f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */