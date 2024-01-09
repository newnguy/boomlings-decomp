package com.chartboost.sdk.impl;

import java.util.HashMap;
import java.util.Map;

enum ag$a {
  a(128, 'c', "Pattern.CANON_EQ"),
  b(1, 'd', "Pattern.UNIX_LINES"),
  c(256, 'g', null),
  d(2, 'i', null),
  e(8, 'm', null),
  f(32, 's', "Pattern.DOTALL"),
  g(16, 't', "Pattern.LITERAL"),
  h(64, 'u', "Pattern.UNICODE_CASE"),
  i(4, 'x', null);
  
  private static final Map m;
  
  private static final ag$a[] n = new ag$a[] { a, b, c, d, e, f, g, h, i };
  
  public final int j;
  
  public final char k;
  
  public final String l;
  
  static {
    m = new HashMap<Object, Object>();
    ag$a[] arrayOfAg$a = values();
    int i = arrayOfAg$a.length;
    while (b < i) {
      ag$a ag$a1 = arrayOfAg$a[b];
      m.put(Character.valueOf(ag$a1.k), ag$a1);
      b++;
    } 
  }
  
  ag$a(int paramInt1, char paramChar, String paramString1) {
    this.j = paramInt1;
    this.k = paramChar;
    this.l = paramString1;
  }
  
  static {
    byte b = 0;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\ag$a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */