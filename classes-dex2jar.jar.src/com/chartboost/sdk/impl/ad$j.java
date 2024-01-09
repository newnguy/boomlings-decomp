package com.chartboost.sdk.impl;

import java.util.Iterator;
import java.util.Map;

class ad$j extends ad$c {
  ad$j(af paramaf) {
    super(paramaf);
  }
  
  public void a(Object paramObject, StringBuilder paramStringBuilder) {
    paramStringBuilder.append("{ ");
    Iterator<Map.Entry> iterator = ((Map)paramObject).entrySet().iterator();
    boolean bool = true;
    while (iterator.hasNext()) {
      paramObject = iterator.next();
      if (bool) {
        bool = false;
      } else {
        paramStringBuilder.append(" , ");
      } 
      ac.a(paramStringBuilder, paramObject.getKey().toString());
      paramStringBuilder.append(" : ");
      this.a.a(paramObject.getValue(), paramStringBuilder);
    } 
    paramStringBuilder.append("}");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\ad$j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */