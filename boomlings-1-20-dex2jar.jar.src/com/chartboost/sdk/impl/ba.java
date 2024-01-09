package com.chartboost.sdk.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

abstract class ba implements Serializable, ConcurrentMap {
  private volatile Map a;
  
  private final transient Lock b = new ReentrantLock();
  
  private final ba$h c;
  
  protected ba(Map paramMap, ba$h$a paramba$h$a) {
    this.a = (Map)bb.a("delegate", a((Map)bb.a("map", paramMap)));
    this.c = ((ba$h$a)bb.a("viewType", paramba$h$a)).a(this);
  }
  
  private boolean a(Object paramObject1, Object paramObject2) {
    return (paramObject1 == null) ? ((paramObject2 == null)) : paramObject1.equals(paramObject2);
  }
  
  protected Map a() {
    this.b.lock();
    try {
      return a(this.a);
    } finally {
      this.b.unlock();
    } 
  }
  
  abstract Map a(Map paramMap);
  
  protected void b(Map paramMap) {
    this.a = paramMap;
  }
  
  public final void clear() {
    this.b.lock();
    try {
      b(a(Collections.emptyMap()));
      return;
    } finally {
      this.b.unlock();
    } 
  }
  
  public final boolean containsKey(Object paramObject) {
    return this.a.containsKey(paramObject);
  }
  
  public final boolean containsValue(Object paramObject) {
    return this.a.containsValue(paramObject);
  }
  
  public final Set entrySet() {
    return this.c.b();
  }
  
  public final boolean equals(Object paramObject) {
    return this.a.equals(paramObject);
  }
  
  public final Object get(Object paramObject) {
    return this.a.get(paramObject);
  }
  
  public final int hashCode() {
    return this.a.hashCode();
  }
  
  public final boolean isEmpty() {
    return this.a.isEmpty();
  }
  
  public final Set keySet() {
    return this.c.a();
  }
  
  public final Object put(Object paramObject1, Object paramObject2) {
    this.b.lock();
    try {
      Map<Object, Object> map = a();
    } finally {
      this.b.unlock();
    } 
  }
  
  public final void putAll(Map paramMap) {
    this.b.lock();
    try {
      Map map = a();
      map.putAll(paramMap);
      b(map);
      return;
    } finally {
      this.b.unlock();
    } 
  }
  
  public Object putIfAbsent(Object paramObject1, Object paramObject2) {
    this.b.lock();
    try {
      if (!this.a.containsKey(paramObject1)) {
        Map<Object, Object> map = a();
        try {
          paramObject1 = map.put(paramObject1, paramObject2);
          return paramObject1;
        } finally {
          b(map);
        } 
      } 
    } finally {
      this.b.unlock();
    } 
    this.b.unlock();
    return paramObject1;
  }
  
  public final Object remove(Object paramObject) {
    this.b.lock();
    try {
      boolean bool = this.a.containsKey(paramObject);
      if (!bool) {
        paramObject = null;
        return paramObject;
      } 
      Map map = a();
      return paramObject;
    } finally {
      this.b.unlock();
    } 
  }
  
  public boolean remove(Object paramObject1, Object paramObject2) {
    this.b.lock();
    try {
      if (this.a.containsKey(paramObject1) && a(paramObject2, this.a.get(paramObject1))) {
        paramObject2 = a();
        paramObject2.remove(paramObject1);
        b((Map)paramObject2);
        return true;
      } 
      return false;
    } finally {
      this.b.unlock();
    } 
  }
  
  public Object replace(Object paramObject1, Object paramObject2) {
    this.b.lock();
    try {
      boolean bool = this.a.containsKey(paramObject1);
      if (!bool) {
        paramObject1 = null;
        return paramObject1;
      } 
      Map<Object, Object> map = a();
      return paramObject1;
    } finally {
      this.b.unlock();
    } 
  }
  
  public boolean replace(Object paramObject1, Object paramObject2, Object paramObject3) {
    this.b.lock();
    try {
      if (this.a.containsKey(paramObject1)) {
        boolean bool = a(paramObject2, this.a.get(paramObject1));
        if (!bool) {
          bool = false;
          return bool;
        } 
        paramObject2 = a();
        paramObject2.put(paramObject1, paramObject3);
        b((Map)paramObject2);
        bool = true;
        return bool;
      } 
      return false;
    } finally {
      this.b.unlock();
    } 
  }
  
  public final int size() {
    return this.a.size();
  }
  
  public String toString() {
    return this.a.toString();
  }
  
  public final Collection values() {
    return this.c.c();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\ba.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */