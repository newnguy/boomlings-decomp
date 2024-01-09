package com.chartboost.sdk.impl;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class ag {
  private static boolean a;
  
  private static boolean b;
  
  static final Logger f = Logger.getLogger("org.bson.BSON");
  
  static bd g;
  
  static bd h;
  
  protected static Charset i;
  
  static ThreadLocal j;
  
  static ThreadLocal k;
  
  static {
    a = false;
    b = false;
    g = new bd();
    h = new bd();
    i = Charset.forName("UTF-8");
    j = new ag$1();
    k = new ag$2();
  }
  
  public static Object a(Object paramObject) {
    if (!a())
      return paramObject; 
    Object object = paramObject;
    if (g.a() != 0) {
      object = paramObject;
      if (paramObject != null) {
        List list = (List)g.a(paramObject.getClass());
        object = paramObject;
        if (list != null) {
          Iterator<an> iterator = list.iterator();
          while (true) {
            object = paramObject;
            if (iterator.hasNext()) {
              paramObject = ((an)iterator.next()).a(paramObject);
              continue;
            } 
            return object;
          } 
        } 
      } 
    } 
    return object;
  }
  
  public static String a(int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    ag$a[] arrayOfAg$a = ag$a.values();
    int k = arrayOfAg$a.length;
    int i = 0;
    int j = paramInt;
    paramInt = i;
    while (paramInt < k) {
      ag$a ag$a = arrayOfAg$a[paramInt];
      i = j;
      if ((ag$a.j & j) > 0) {
        stringBuilder.append(ag$a.k);
        i = j - ag$a.j;
      } 
      paramInt++;
      j = i;
    } 
    if (j > 0)
      throw new IllegalArgumentException("some flags could not be recognized."); 
    return stringBuilder.toString();
  }
  
  private static boolean a() {
    return (a || b);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\ag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */