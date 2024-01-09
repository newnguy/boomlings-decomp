package com.chartboost.sdk.impl;

import java.io.Serializable;

public class az implements Serializable {
  private final String a;
  
  public String a() {
    return this.a;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this != paramObject) {
      if (paramObject == null)
        return false; 
      if (paramObject instanceof az) {
        paramObject = ((az)paramObject).a;
      } else if (paramObject instanceof String) {
        paramObject = paramObject;
      } else {
        return false;
      } 
      if ((this.a != null) ? !this.a.equals(paramObject) : (paramObject != null))
        bool = false; 
    } 
    return bool;
  }
  
  public int hashCode() {
    return (this.a != null) ? this.a.hashCode() : 0;
  }
  
  public String toString() {
    return this.a;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\az.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */