package com.flurry.org.apache.avro.util;

import java.lang.ref.ReferenceQueue;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class WeakIdentityHashMap implements Map {
  private Map backingStore = new HashMap<Object, Object>();
  
  private final ReferenceQueue queue = new ReferenceQueue();
  
  private void reap() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield queue : Ljava/lang/ref/ReferenceQueue;
    //   6: invokevirtual poll : ()Ljava/lang/ref/Reference;
    //   9: astore_1
    //   10: aload_1
    //   11: ifnull -> 41
    //   14: aload_1
    //   15: checkcast com/flurry/org/apache/avro/util/WeakIdentityHashMap$IdentityWeakReference
    //   18: astore_1
    //   19: aload_0
    //   20: getfield backingStore : Ljava/util/Map;
    //   23: aload_1
    //   24: invokeinterface remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   29: pop
    //   30: aload_0
    //   31: getfield queue : Ljava/lang/ref/ReferenceQueue;
    //   34: invokevirtual poll : ()Ljava/lang/ref/Reference;
    //   37: astore_1
    //   38: goto -> 10
    //   41: aload_0
    //   42: monitorexit
    //   43: return
    //   44: astore_1
    //   45: aload_0
    //   46: monitorexit
    //   47: aload_1
    //   48: athrow
    // Exception table:
    //   from	to	target	type
    //   2	10	44	finally
    //   14	38	44	finally
  }
  
  public void clear() {
    this.backingStore.clear();
    reap();
  }
  
  public boolean containsKey(Object paramObject) {
    reap();
    return this.backingStore.containsKey(new WeakIdentityHashMap$IdentityWeakReference(this, paramObject));
  }
  
  public boolean containsValue(Object paramObject) {
    reap();
    return this.backingStore.containsValue(paramObject);
  }
  
  public Set entrySet() {
    reap();
    HashSet<WeakIdentityHashMap$1> hashSet = new HashSet();
    for (Map.Entry entry : this.backingStore.entrySet())
      hashSet.add(new WeakIdentityHashMap$1(this, ((WeakIdentityHashMap$IdentityWeakReference)entry.getKey()).get(), entry.getValue())); 
    return Collections.unmodifiableSet(hashSet);
  }
  
  public boolean equals(Object paramObject) {
    return this.backingStore.equals(((WeakIdentityHashMap)paramObject).backingStore);
  }
  
  public Object get(Object paramObject) {
    reap();
    return this.backingStore.get(new WeakIdentityHashMap$IdentityWeakReference(this, paramObject));
  }
  
  public int hashCode() {
    reap();
    return this.backingStore.hashCode();
  }
  
  public boolean isEmpty() {
    reap();
    return this.backingStore.isEmpty();
  }
  
  public Set keySet() {
    reap();
    HashSet<T> hashSet = new HashSet();
    Iterator<WeakIdentityHashMap$IdentityWeakReference> iterator = this.backingStore.keySet().iterator();
    while (iterator.hasNext())
      hashSet.add(((WeakIdentityHashMap$IdentityWeakReference)iterator.next()).get()); 
    return Collections.unmodifiableSet(hashSet);
  }
  
  public Object put(Object paramObject1, Object paramObject2) {
    reap();
    return this.backingStore.put(new WeakIdentityHashMap$IdentityWeakReference(this, paramObject1), paramObject2);
  }
  
  public void putAll(Map paramMap) {
    throw new UnsupportedOperationException();
  }
  
  public Object remove(Object paramObject) {
    reap();
    return this.backingStore.remove(new WeakIdentityHashMap$IdentityWeakReference(this, paramObject));
  }
  
  public int size() {
    reap();
    return this.backingStore.size();
  }
  
  public Collection values() {
    reap();
    return this.backingStore.values();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avr\\util\WeakIdentityHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */