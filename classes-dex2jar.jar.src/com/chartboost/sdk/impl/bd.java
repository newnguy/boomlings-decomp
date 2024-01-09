package com.chartboost.sdk.impl;

import java.util.List;
import java.util.Map;

public class bd {
  private final Map a = bf.c();
  
  private final Map b = be.a(new bd$a(this, null));
  
  public static List a(Class paramClass) {
    return bc.a(paramClass);
  }
  
  public int a() {
    return this.a.size();
  }
  
  public Object a(Class<?> paramClass, Object paramObject) {
    try {
      paramClass = (Class<?>)this.a.put(paramClass, paramObject);
      return paramClass;
    } finally {
      this.b.clear();
    } 
  }
  
  public Object a(Object paramObject) {
    return this.b.get(paramObject);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\bd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */