package com.chartboost.sdk.impl;

import java.util.Collection;
import java.util.Map;

final class ba$g extends ba$a {
  final ba a;
  
  private ba$g(ba paramba) {}
  
  Collection a() {
    return ba.a(this.a).values();
  }
  
  public void clear() {
    ba.b(this.a).lock();
    try {
      Map map = this.a.a();
      map.values().clear();
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


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\ba$g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */