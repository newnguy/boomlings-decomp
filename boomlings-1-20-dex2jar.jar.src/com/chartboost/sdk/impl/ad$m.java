package com.chartboost.sdk.impl;

import java.lang.reflect.Array;

class ad$m extends ad$c {
  ad$m(af paramaf) {
    super(paramaf);
  }
  
  public void a(Object paramObject, StringBuilder paramStringBuilder) {
    paramStringBuilder.append("[ ");
    for (byte b = 0; b < Array.getLength(paramObject); b++) {
      if (b > 0)
        paramStringBuilder.append(" , "); 
      this.a.a(Array.get(paramObject, b), paramStringBuilder);
    } 
    paramStringBuilder.append("]");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\ad$m.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */