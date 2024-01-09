package com.flurry.org.apache.avro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class Schema$LockableArrayList extends ArrayList {
  private static final long serialVersionUID = 1L;
  
  private boolean locked = false;
  
  public Schema$LockableArrayList() {}
  
  public Schema$LockableArrayList(int paramInt) {
    super(paramInt);
  }
  
  public Schema$LockableArrayList(List<? extends E> paramList) {
    super(paramList);
  }
  
  private void ensureUnlocked() {
    if (this.locked)
      throw new IllegalStateException(); 
  }
  
  public boolean add(Object paramObject) {
    ensureUnlocked();
    return super.add(paramObject);
  }
  
  public boolean addAll(int paramInt, Collection<?> paramCollection) {
    ensureUnlocked();
    return super.addAll(paramInt, paramCollection);
  }
  
  public boolean addAll(Collection<?> paramCollection) {
    ensureUnlocked();
    return super.addAll(paramCollection);
  }
  
  public void clear() {
    ensureUnlocked();
    super.clear();
  }
  
  public List lock() {
    this.locked = true;
    return this;
  }
  
  public Object remove(int paramInt) {
    ensureUnlocked();
    return super.remove(paramInt);
  }
  
  public boolean remove(Object paramObject) {
    ensureUnlocked();
    return super.remove(paramObject);
  }
  
  public boolean removeAll(Collection<?> paramCollection) {
    ensureUnlocked();
    return super.removeAll(paramCollection);
  }
  
  public boolean retainAll(Collection<?> paramCollection) {
    ensureUnlocked();
    return super.retainAll(paramCollection);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\Schema$LockableArrayList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */