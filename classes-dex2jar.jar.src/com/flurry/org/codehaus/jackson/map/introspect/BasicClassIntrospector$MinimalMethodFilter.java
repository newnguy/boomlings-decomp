package com.flurry.org.codehaus.jackson.map.introspect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

class BasicClassIntrospector$MinimalMethodFilter implements MethodFilter {
  private BasicClassIntrospector$MinimalMethodFilter() {}
  
  public boolean includeMethod(Method paramMethod) {
    boolean bool = false;
    if (!Modifier.isStatic(paramMethod.getModifiers()) && (paramMethod.getParameterTypes()).length <= 2)
      bool = true; 
    return bool;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\introspect\BasicClassIntrospector$MinimalMethodFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */