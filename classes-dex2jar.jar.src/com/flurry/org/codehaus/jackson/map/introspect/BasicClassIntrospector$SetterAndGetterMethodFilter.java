package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

@Deprecated
public final class BasicClassIntrospector$SetterAndGetterMethodFilter extends BasicClassIntrospector$SetterMethodFilter {
  public boolean includeMethod(Method paramMethod) {
    boolean bool2 = true;
    if (super.includeMethod(paramMethod))
      return bool2; 
    if (!ClassUtil.hasGetterSignature(paramMethod))
      return false; 
    Class<?> clazz = paramMethod.getReturnType();
    boolean bool1 = bool2;
    if (!Collection.class.isAssignableFrom(clazz)) {
      bool1 = bool2;
      if (!Map.class.isAssignableFrom(clazz))
        bool1 = false; 
    } 
    return bool1;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\introspect\BasicClassIntrospector$SetterAndGetterMethodFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */