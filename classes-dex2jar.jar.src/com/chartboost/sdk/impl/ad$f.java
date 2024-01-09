package com.chartboost.sdk.impl;

class ad$f extends ad$c {
  ad$f(af paramaf) {
    super(paramaf);
  }
  
  public void a(Object paramObject, StringBuilder paramStringBuilder) {
    boolean bool = true;
    paramStringBuilder.append("[ ");
    for (Object paramObject : paramObject) {
      if (bool) {
        bool = false;
      } else {
        paramStringBuilder.append(" , ");
      } 
      this.a.a(paramObject, paramStringBuilder);
    } 
    paramStringBuilder.append("]");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\ad$f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */