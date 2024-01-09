package com.chartboost.sdk.impl;

import java.util.Collection;
import java.util.Iterator;

public abstract class ba$a implements Collection {
  abstract Collection a();
  
  public final boolean add(Object paramObject) {
    throw new UnsupportedOperationException();
  }
  
  public final boolean addAll(Collection paramCollection) {
    throw new UnsupportedOperationException();
  }
  
  public final boolean contains(Object paramObject) {
    return a().contains(paramObject);
  }
  
  public final boolean containsAll(Collection<?> paramCollection) {
    return a().containsAll(paramCollection);
  }
  
  public boolean equals(Object paramObject) {
    return a().equals(paramObject);
  }
  
  public int hashCode() {
    return a().hashCode();
  }
  
  public final boolean isEmpty() {
    return a().isEmpty();
  }
  
  public final Iterator iterator() {
    return new ba$f(a().iterator());
  }
  
  public final int size() {
    return a().size();
  }
  
  public final Object[] toArray() {
    return a().toArray();
  }
  
  public final Object[] toArray(Object[] paramArrayOfObject) {
    return a().toArray(paramArrayOfObject);
  }
  
  public String toString() {
    return a().toString();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\ba$a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */