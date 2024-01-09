package com.chartboost.sdk.impl;

import java.io.Serializable;
import java.util.Date;

public class as implements Serializable, Comparable {
  static final boolean a = Boolean.getBoolean("DEBUG.DBTIMESTAMP");
  
  final int b = 0;
  
  final Date c = null;
  
  public int a() {
    return (this.c == null) ? 0 : (int)(this.c.getTime() / 1000L);
  }
  
  public int a(as paramas) {
    return (a() != paramas.a()) ? (a() - paramas.a()) : (b() - paramas.b());
  }
  
  public int b() {
    return this.b;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject != this) {
      if (paramObject instanceof as) {
        paramObject = paramObject;
        if (a() != paramObject.a() || b() != paramObject.b())
          bool = false; 
        return bool;
      } 
      bool = false;
    } 
    return bool;
  }
  
  public int hashCode() {
    return (this.b + 31) * 31 + a();
  }
  
  public String toString() {
    return "TS time:" + this.c + " inc:" + this.b;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\as.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */