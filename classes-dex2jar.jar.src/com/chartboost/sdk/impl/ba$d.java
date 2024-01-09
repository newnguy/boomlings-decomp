package com.chartboost.sdk.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

class ba$d extends ba$a implements Set {
  final ba a;
  
  private ba$d(ba paramba) {}
  
  Collection a() {
    return ba.a(this.a).keySet();
  }
  
  public void clear() {
    ba.b(this.a).lock();
    try {
      Map map = this.a.a();
      map.keySet().clear();
      this.a.b(map);
      return;
    } finally {
      ba.b(this.a).unlock();
    } 
  }
  
  public boolean remove(Object paramObject) {
    return (this.a.remove(paramObject) != null);
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


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\ba$d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */