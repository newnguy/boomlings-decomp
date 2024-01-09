package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import java.lang.reflect.Method;

@Deprecated
public class BasicClassIntrospector$GetterMethodFilter implements MethodFilter {
  private BasicClassIntrospector$GetterMethodFilter() {}
  
  public boolean includeMethod(Method paramMethod) {
    return ClassUtil.hasGetterSignature(paramMethod);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\introspect\BasicClassIntrospector$GetterMethodFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */