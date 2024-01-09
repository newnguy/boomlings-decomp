package com.flurry.org.apache.avro.util;

import java.lang.ref.WeakReference;

class WeakIdentityHashMap$IdentityWeakReference extends WeakReference {
  int hash;
  
  final WeakIdentityHashMap this$0;
  
  WeakIdentityHashMap$IdentityWeakReference(Object paramObject) {
    super((T)paramObject, WeakIdentityHashMap.access$000(paramWeakIdentityHashMap));
    this.hash = System.identityHashCode(paramObject);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this != paramObject) {
      paramObject = paramObject;
      if (get() != paramObject.get())
        bool = false; 
    } 
    return bool;
  }
  
  public int hashCode() {
    return this.hash;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avr\\util\WeakIdentityHashMap$IdentityWeakReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */