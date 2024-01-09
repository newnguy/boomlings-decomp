package com.chartboost.sdk.impl;

import java.util.regex.Pattern;

class ad$o extends ad$c {
  ad$o(af paramaf) {
    super(paramaf);
  }
  
  public void a(Object paramObject, StringBuilder paramStringBuilder) {
    w w = new w();
    w.a("$regex", paramObject.toString());
    if (((Pattern)paramObject).flags() != 0)
      w.a("$options", x.a(((Pattern)paramObject).flags())); 
    this.a.a(w, paramStringBuilder);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\ad$o.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */