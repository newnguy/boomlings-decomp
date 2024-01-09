package com.flurry.org.codehaus.jackson.map.deser.impl;

import com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty;
import java.util.Iterator;
import java.util.NoSuchElementException;

final class BeanPropertyMap$IteratorImpl implements Iterator {
  private final BeanPropertyMap$Bucket[] _buckets;
  
  private BeanPropertyMap$Bucket _currentBucket;
  
  private int _nextBucketIndex;
  
  public BeanPropertyMap$IteratorImpl(BeanPropertyMap$Bucket[] paramArrayOfBeanPropertyMap$Bucket) {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial <init> : ()V
    //   4: aload_0
    //   5: aload_1
    //   6: putfield _buckets : [Lcom/flurry/org/codehaus/jackson/map/deser/impl/BeanPropertyMap$Bucket;
    //   9: iconst_0
    //   10: istore_2
    //   11: aload_0
    //   12: getfield _buckets : [Lcom/flurry/org/codehaus/jackson/map/deser/impl/BeanPropertyMap$Bucket;
    //   15: arraylength
    //   16: istore #4
    //   18: iload_2
    //   19: iload #4
    //   21: if_icmpge -> 57
    //   24: aload_0
    //   25: getfield _buckets : [Lcom/flurry/org/codehaus/jackson/map/deser/impl/BeanPropertyMap$Bucket;
    //   28: astore_1
    //   29: iload_2
    //   30: iconst_1
    //   31: iadd
    //   32: istore_3
    //   33: aload_1
    //   34: iload_2
    //   35: aaload
    //   36: astore_1
    //   37: aload_1
    //   38: ifnull -> 52
    //   41: aload_0
    //   42: aload_1
    //   43: putfield _currentBucket : Lcom/flurry/org/codehaus/jackson/map/deser/impl/BeanPropertyMap$Bucket;
    //   46: aload_0
    //   47: iload_3
    //   48: putfield _nextBucketIndex : I
    //   51: return
    //   52: iload_3
    //   53: istore_2
    //   54: goto -> 18
    //   57: iload_2
    //   58: istore_3
    //   59: goto -> 46
  }
  
  public boolean hasNext() {
    return (this._currentBucket != null);
  }
  
  public SettableBeanProperty next() {
    BeanPropertyMap$Bucket beanPropertyMap$Bucket2 = this._currentBucket;
    if (beanPropertyMap$Bucket2 == null)
      throw new NoSuchElementException(); 
    BeanPropertyMap$Bucket beanPropertyMap$Bucket1 = beanPropertyMap$Bucket2.next;
    while (beanPropertyMap$Bucket1 == null && this._nextBucketIndex < this._buckets.length) {
      BeanPropertyMap$Bucket[] arrayOfBeanPropertyMap$Bucket = this._buckets;
      int i = this._nextBucketIndex;
      this._nextBucketIndex = i + 1;
      beanPropertyMap$Bucket1 = arrayOfBeanPropertyMap$Bucket[i];
    } 
    this._currentBucket = beanPropertyMap$Bucket1;
    return beanPropertyMap$Bucket2.value;
  }
  
  public void remove() {
    throw new UnsupportedOperationException();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\impl\BeanPropertyMap$IteratorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */