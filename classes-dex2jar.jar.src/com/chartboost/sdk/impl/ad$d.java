package com.chartboost.sdk.impl;

class ad$d extends ad$c {
  ad$d(af paramaf) {
    super(paramaf);
  }
  
  public void a(Object paramObject, StringBuilder paramStringBuilder) {
    paramStringBuilder.append("{ ");
    y y = (y)paramObject;
    paramObject = y.keySet().iterator();
    boolean bool = true;
    while (paramObject.hasNext()) {
      String str = paramObject.next();
      if (bool) {
        bool = false;
      } else {
        paramStringBuilder.append(" , ");
      } 
      ac.a(paramStringBuilder, str);
      paramStringBuilder.append(" : ");
      this.a.a(y.a(str), paramStringBuilder);
    } 
    paramStringBuilder.append("}");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\ad$d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */