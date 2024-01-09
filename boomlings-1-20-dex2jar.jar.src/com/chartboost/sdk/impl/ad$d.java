package com.chartboost.sdk.impl;

import java.util.Iterator;

class ad$d extends ad$c {
  ad$d(af paramaf) {
    super(paramaf);
  }
  
  public void a(Object paramObject, StringBuilder paramStringBuilder) {
    paramStringBuilder.append("{ ");
    paramObject = paramObject;
    Iterator<String> iterator = paramObject.keySet().iterator();
    boolean bool = true;
    while (iterator.hasNext()) {
      String str = iterator.next();
      if (bool) {
        bool = false;
      } else {
        paramStringBuilder.append(" , ");
      } 
      ac.a(paramStringBuilder, str);
      paramStringBuilder.append(" : ");
      this.a.a(paramObject.a(str), paramStringBuilder);
    } 
    paramStringBuilder.append("}");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\ad$d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */