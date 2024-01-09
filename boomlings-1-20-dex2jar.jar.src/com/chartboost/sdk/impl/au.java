package com.chartboost.sdk.impl;

import java.io.Serializable;

public class au implements Serializable {
  final String a;
  
  public String a() {
    return this.a;
  }
  
  public boolean equals(Object paramObject) {
    if (!(paramObject instanceof au))
      return false; 
    paramObject = paramObject;
    return this.a.equals(((au)paramObject).a);
  }
  
  public int hashCode() {
    return this.a.hashCode();
  }
  
  public String toString() {
    return a();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\au.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */