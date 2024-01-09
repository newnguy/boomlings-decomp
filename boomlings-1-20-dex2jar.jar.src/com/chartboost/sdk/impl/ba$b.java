package com.chartboost.sdk.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

class ba$b extends ba$a implements Set {
  final ba a;
  
  private ba$b(ba paramba) {}
  
  Collection a() {
    return ba.a(this.a).entrySet();
  }
  
  public void clear() {
    ba.b(this.a).lock();
    try {
      Map map = this.a.a();
      map.entrySet().clear();
      this.a.b(map);
      return;
    } finally {
      ba.b(this.a).unlock();
    } 
  }
  
  public boolean remove(Object paramObject) {
    ba.b(this.a).lock();
    try {
      boolean bool = contains(paramObject);
      if (!bool) {
        bool = false;
        return bool;
      } 
      Map map = this.a.a();
      return bool;
    } finally {
      ba.b(this.a).unlock();
    } 
  }
  
  public boolean removeAll(Collection<?> paramCollection) {
    ba.b(this.a).lock();
    try {
      Map map = this.a.a();
    } finally {
      ba.b(this.a).unlock();
    } 
  }
  
  public boolean retainAll(Collection<?> paramCollection) {
    ba.b(this.a).lock();
    try {
      Map map = this.a.a();
    } finally {
      ba.b(this.a).unlock();
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\ba$b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */