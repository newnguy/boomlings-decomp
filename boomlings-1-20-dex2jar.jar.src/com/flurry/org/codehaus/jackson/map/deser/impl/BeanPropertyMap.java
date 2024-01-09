package com.flurry.org.codehaus.jackson.map.deser.impl;

import com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class BeanPropertyMap {
  private final BeanPropertyMap$Bucket[] _buckets;
  
  private final int _hashMask;
  
  private final int _size;
  
  public BeanPropertyMap(Collection paramCollection) {
    this._size = paramCollection.size();
    int i = findSize(this._size);
    this._hashMask = i - 1;
    BeanPropertyMap$Bucket[] arrayOfBeanPropertyMap$Bucket = new BeanPropertyMap$Bucket[i];
    for (SettableBeanProperty settableBeanProperty : paramCollection) {
      String str = settableBeanProperty.getName();
      i = str.hashCode() & this._hashMask;
      arrayOfBeanPropertyMap$Bucket[i] = new BeanPropertyMap$Bucket(arrayOfBeanPropertyMap$Bucket[i], str, settableBeanProperty);
    } 
    this._buckets = arrayOfBeanPropertyMap$Bucket;
  }
  
  private SettableBeanProperty _findWithEquals(String paramString, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: getfield _buckets : [Lcom/flurry/org/codehaus/jackson/map/deser/impl/BeanPropertyMap$Bucket;
    //   4: iload_2
    //   5: aaload
    //   6: astore_3
    //   7: aload_3
    //   8: ifnull -> 37
    //   11: aload_1
    //   12: aload_3
    //   13: getfield key : Ljava/lang/String;
    //   16: invokevirtual equals : (Ljava/lang/Object;)Z
    //   19: ifeq -> 29
    //   22: aload_3
    //   23: getfield value : Lcom/flurry/org/codehaus/jackson/map/deser/SettableBeanProperty;
    //   26: astore_1
    //   27: aload_1
    //   28: areturn
    //   29: aload_3
    //   30: getfield next : Lcom/flurry/org/codehaus/jackson/map/deser/impl/BeanPropertyMap$Bucket;
    //   33: astore_3
    //   34: goto -> 7
    //   37: aconst_null
    //   38: astore_1
    //   39: goto -> 27
  }
  
  private static final int findSize(int paramInt) {
    if (paramInt <= 32) {
      paramInt += paramInt;
    } else {
      paramInt = (paramInt >> 2) + paramInt;
    } 
    int i;
    for (i = 2; i < paramInt; i += i);
    return i;
  }
  
  public Iterator allProperties() {
    return new BeanPropertyMap$IteratorImpl(this._buckets);
  }
  
  public void assignIndexes() {
    BeanPropertyMap$Bucket[] arrayOfBeanPropertyMap$Bucket = this._buckets;
    int i = arrayOfBeanPropertyMap$Bucket.length;
    byte b1 = 0;
    byte b2 = 0;
    while (b1 < i) {
      BeanPropertyMap$Bucket beanPropertyMap$Bucket = arrayOfBeanPropertyMap$Bucket[b1];
      while (beanPropertyMap$Bucket != null) {
        beanPropertyMap$Bucket.value.assignIndex(b2);
        beanPropertyMap$Bucket = beanPropertyMap$Bucket.next;
        b2++;
      } 
      b1++;
    } 
  }
  
  public SettableBeanProperty find(String paramString) {
    int i = paramString.hashCode();
    i = this._hashMask & i;
    BeanPropertyMap$Bucket beanPropertyMap$Bucket2 = this._buckets[i];
    if (beanPropertyMap$Bucket2 == null)
      return null; 
    BeanPropertyMap$Bucket beanPropertyMap$Bucket1 = beanPropertyMap$Bucket2;
    if (beanPropertyMap$Bucket2.key == paramString)
      return beanPropertyMap$Bucket2.value; 
    while (true) {
      beanPropertyMap$Bucket2 = beanPropertyMap$Bucket1.next;
      if (beanPropertyMap$Bucket2 != null) {
        beanPropertyMap$Bucket1 = beanPropertyMap$Bucket2;
        if (beanPropertyMap$Bucket2.key == paramString)
          return beanPropertyMap$Bucket2.value; 
        continue;
      } 
      return _findWithEquals(paramString, i);
    } 
  }
  
  public void remove(SettableBeanProperty paramSettableBeanProperty) {
    String str = paramSettableBeanProperty.getName();
    int i = str.hashCode() & this._buckets.length - 1;
    BeanPropertyMap$Bucket beanPropertyMap$Bucket1 = this._buckets[i];
    boolean bool = false;
    BeanPropertyMap$Bucket beanPropertyMap$Bucket2 = null;
    while (beanPropertyMap$Bucket1 != null) {
      if (!bool && beanPropertyMap$Bucket1.key.equals(str)) {
        bool = true;
      } else {
        beanPropertyMap$Bucket2 = new BeanPropertyMap$Bucket(beanPropertyMap$Bucket2, beanPropertyMap$Bucket1.key, beanPropertyMap$Bucket1.value);
      } 
      beanPropertyMap$Bucket1 = beanPropertyMap$Bucket1.next;
    } 
    if (!bool)
      throw new NoSuchElementException("No entry '" + paramSettableBeanProperty + "' found, can't remove"); 
    this._buckets[i] = beanPropertyMap$Bucket2;
  }
  
  public void replace(SettableBeanProperty paramSettableBeanProperty) {
    String str = paramSettableBeanProperty.getName();
    int i = str.hashCode() & this._buckets.length - 1;
    BeanPropertyMap$Bucket beanPropertyMap$Bucket1 = this._buckets[i];
    boolean bool = false;
    BeanPropertyMap$Bucket beanPropertyMap$Bucket2 = null;
    while (beanPropertyMap$Bucket1 != null) {
      if (!bool && beanPropertyMap$Bucket1.key.equals(str)) {
        bool = true;
      } else {
        beanPropertyMap$Bucket2 = new BeanPropertyMap$Bucket(beanPropertyMap$Bucket2, beanPropertyMap$Bucket1.key, beanPropertyMap$Bucket1.value);
      } 
      beanPropertyMap$Bucket1 = beanPropertyMap$Bucket1.next;
    } 
    if (!bool)
      throw new NoSuchElementException("No entry '" + paramSettableBeanProperty + "' found, can't replace"); 
    this._buckets[i] = new BeanPropertyMap$Bucket(beanPropertyMap$Bucket2, str, paramSettableBeanProperty);
  }
  
  public int size() {
    return this._size;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\impl\BeanPropertyMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */