package com.google.ads.util;

import java.io.UnsupportedEncodingException;

public class c {
  static final boolean a;
  
  static {
    boolean bool;
    if (!c.class.desiredAssertionStatus()) {
      bool = true;
    } else {
      bool = false;
    } 
    a = bool;
  }
  
  public static byte[] a(String paramString) {
    return a(paramString.getBytes(), 0);
  }
  
  public static byte[] a(byte[] paramArrayOfbyte, int paramInt) {
    return a(paramArrayOfbyte, 0, paramArrayOfbyte.length, paramInt);
  }
  
  public static byte[] a(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3) {
    c$b c$b = new c$b(paramInt3, new byte[paramInt2 * 3 / 4]);
    if (!c$b.a(paramArrayOfbyte, paramInt1, paramInt2, true))
      throw new IllegalArgumentException("bad base-64"); 
    if (c$b.b == c$b.a.length)
      return c$b.a; 
    paramArrayOfbyte = new byte[c$b.b];
    System.arraycopy(c$b.a, 0, paramArrayOfbyte, 0, c$b.b);
    return paramArrayOfbyte;
  }
  
  public static String b(byte[] paramArrayOfbyte, int paramInt) {
    try {
      return new String(c(paramArrayOfbyte, paramInt), "US-ASCII");
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      throw new AssertionError(unsupportedEncodingException);
    } 
  }
  
  public static byte[] b(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, int paramInt3) {
    c$c c$c = new c$c(paramInt3, null);
    int i = paramInt2 / 3 * 4;
    if (c$c.d) {
      paramInt3 = i;
      if (paramInt2 % 3 > 0)
        paramInt3 = i + 4; 
    } 
    paramInt3 = i;
    switch (paramInt2 % 3) {
      case 0:
        i = paramInt3;
        if (c$c.e) {
          i = paramInt3;
          if (paramInt2 > 0) {
            int j = (paramInt2 - 1) / 57;
            if (c$c.f) {
              i = 2;
            } else {
              break;
            } 
            i = paramInt3 + i * (j + 1);
          } 
        } 
        c$c.a = new byte[i];
        c$c.a(paramArrayOfbyte, paramInt1, paramInt2, true);
        if (!a && c$c.b != i)
          throw new AssertionError(); 
        return c$c.a;
      default:
        paramInt3 = i;
      case 1:
        paramInt3 = i + 2;
      case 2:
        paramInt3 = i + 3;
    } 
    i = 1;
  }
  
  public static byte[] c(byte[] paramArrayOfbyte, int paramInt) {
    return b(paramArrayOfbyte, 0, paramArrayOfbyte.length, paramInt);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ad\\util\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */