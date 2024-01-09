package com.flurry.org.codehaus.jackson.map.introspect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

@Deprecated
public class BasicClassIntrospector$SetterMethodFilter implements MethodFilter {
  public boolean includeMethod(Method paramMethod) {
    boolean bool = false;
    if (Modifier.isStatic(paramMethod.getModifiers()));
    switch ((paramMethod.getParameterTypes()).length) {
      default:
        return bool;
      case 1:
        bool = true;
      case 2:
        break;
    } 
    bool = true;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\introspect\BasicClassIntrospector$SetterMethodFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */