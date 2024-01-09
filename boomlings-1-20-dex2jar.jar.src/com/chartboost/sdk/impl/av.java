package com.chartboost.sdk.impl;

public class av extends au {
  final aj b;
  
  public aj b() {
    return this.b;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool2 = false;
    if (!(paramObject instanceof av))
      return bool2; 
    paramObject = paramObject;
    boolean bool1 = bool2;
    if (this.a.equals(((av)paramObject).a)) {
      bool1 = bool2;
      if (this.b.equals(((av)paramObject).b))
        bool1 = true; 
    } 
    return bool1;
  }
  
  public int hashCode() {
    return this.a.hashCode() ^ this.b.hashCode();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\av.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */