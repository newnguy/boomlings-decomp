package com.chartboost.sdk.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

final class be implements bg, Map {
  private final ConcurrentMap a;
  
  private final bg b;
  
  be(ConcurrentMap paramConcurrentMap, bg parambg) {
    this.a = (ConcurrentMap)bb.a("map", paramConcurrentMap);
    this.b = (bg)bb.a("function", parambg);
  }
  
  public static Map a(bg parambg) {
    return new be(bf.c(), parambg);
  }
  
  public Object a(Object paramObject) {
    return get(paramObject);
  }
  
  public void clear() {
    this.a.clear();
  }
  
  public boolean containsKey(Object paramObject) {
    return this.a.containsKey(paramObject);
  }
  
  public boolean containsValue(Object paramObject) {
    return this.a.containsValue(paramObject);
  }
  
  public Set entrySet() {
    return this.a.entrySet();
  }
  
  public boolean equals(Object paramObject) {
    return this.a.equals(paramObject);
  }
  
  public Object get(Object paramObject) {
    while (true) {
      Object object = this.a.get(paramObject);
      if (object != null)
        return object; 
      object = this.b.a(paramObject);
      if (object == null)
        return null; 
      this.a.putIfAbsent(paramObject, object);
    } 
  }
  
  public int hashCode() {
    return this.a.hashCode();
  }
  
  public boolean isEmpty() {
    return this.a.isEmpty();
  }
  
  public Set keySet() {
    return this.a.keySet();
  }
  
  public Object put(Object paramObject1, Object paramObject2) {
    return this.a.put(paramObject1, paramObject2);
  }
  
  public void putAll(Map paramMap) {
    this.a.putAll(paramMap);
  }
  
  public Object remove(Object paramObject) {
    return this.a.remove(paramObject);
  }
  
  public int size() {
    return this.a.size();
  }
  
  public Collection values() {
    return this.a.values();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\be.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */