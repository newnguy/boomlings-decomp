package com.chartboost.sdk.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

final class ba$c extends ba$h implements Serializable {
  final ba a;
  
  ba$c(ba paramba) {}
  
  public Set a() {
    return Collections.unmodifiableSet(ba.a(this.a).keySet());
  }
  
  public Set b() {
    return Collections.unmodifiableSet(ba.a(this.a).entrySet());
  }
  
  public Collection c() {
    return Collections.unmodifiableCollection(ba.a(this.a).values());
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\ba$c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */