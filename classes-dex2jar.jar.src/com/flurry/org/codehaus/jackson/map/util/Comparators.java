package com.flurry.org.codehaus.jackson.map.util;

import java.lang.reflect.Array;

public class Comparators {
  public static Object getArrayComparator(Object paramObject) {
    return new Comparators$1(paramObject, Array.getLength(paramObject));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\ma\\util\Comparators.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */