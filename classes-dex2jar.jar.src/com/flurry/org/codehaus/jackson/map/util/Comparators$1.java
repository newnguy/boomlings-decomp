package com.flurry.org.codehaus.jackson.map.util;

import java.lang.reflect.Array;

final class Comparators$1 {
  final Object val$defaultValue;
  
  final int val$length;
  
  public boolean equals(Object paramObject) {
    boolean bool2 = false;
    if (paramObject == this)
      return true; 
    boolean bool1 = bool2;
    if (paramObject != null) {
      bool1 = bool2;
      if (paramObject.getClass() == defaultValue.getClass()) {
        bool1 = bool2;
        if (Array.getLength(paramObject) == length) {
          for (byte b = 0; b < length; b++) {
            Object object2 = Array.get(defaultValue, b);
            Object object1 = Array.get(paramObject, b);
            if (object2 != object1 && object2 != null && !object2.equals(object1)) {
              bool1 = bool2;
              // Byte code: goto -> 10
            } 
          } 
          bool1 = true;
        } 
      } 
    } 
    return bool1;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\ma\\util\Comparators$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */