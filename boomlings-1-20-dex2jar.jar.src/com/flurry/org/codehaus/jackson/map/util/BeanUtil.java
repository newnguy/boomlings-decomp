package com.flurry.org.codehaus.jackson.map.util;

import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod;

public class BeanUtil {
  protected static boolean isCglibGetCallbacks(AnnotatedMethod paramAnnotatedMethod) {
    boolean bool = false;
    Class clazz = paramAnnotatedMethod.getRawType();
    null = bool;
    if (clazz != null) {
      if (!clazz.isArray())
        return bool; 
    } else {
      return null;
    } 
    Package package_ = clazz.getComponentType().getPackage();
    null = bool;
    if (package_ != null) {
      String str = package_.getName();
      if (!str.startsWith("net.sf.cglib")) {
        null = bool;
        return str.startsWith("org.hibernate.repackage.cglib") ? true : null;
      } 
    } else {
      return null;
    } 
    return true;
  }
  
  protected static boolean isGroovyMetaClassGetter(AnnotatedMethod paramAnnotatedMethod) {
    boolean bool2 = false;
    Class clazz = paramAnnotatedMethod.getRawType();
    boolean bool1 = bool2;
    if (clazz != null) {
      if (clazz.isArray())
        return bool2; 
    } else {
      return bool1;
    } 
    Package package_ = clazz.getPackage();
    bool1 = bool2;
    if (package_ != null) {
      bool1 = bool2;
      if (package_.getName().startsWith("groovy.lang"))
        bool1 = true; 
    } 
    return bool1;
  }
  
  protected static boolean isGroovyMetaClassSetter(AnnotatedMethod paramAnnotatedMethod) {
    boolean bool2 = false;
    Package package_ = paramAnnotatedMethod.getParameterClass(0).getPackage();
    boolean bool1 = bool2;
    if (package_ != null) {
      bool1 = bool2;
      if (package_.getName().startsWith("groovy.lang"))
        bool1 = true; 
    } 
    return bool1;
  }
  
  protected static String manglePropertyName(String paramString) {
    StringBuilder stringBuilder = null;
    int i = paramString.length();
    if (i == 0)
      return null; 
    byte b = 0;
    while (true) {
      if (b < i) {
        char c1 = paramString.charAt(b);
        char c2 = Character.toLowerCase(c1);
        if (c1 != c2) {
          StringBuilder stringBuilder1 = stringBuilder;
          if (stringBuilder == null)
            stringBuilder1 = new StringBuilder(paramString); 
          stringBuilder1.setCharAt(b, c2);
          b++;
          stringBuilder = stringBuilder1;
          continue;
        } 
      } 
      if (stringBuilder != null)
        paramString = stringBuilder.toString(); 
      return paramString;
    } 
  }
  
  public static String okNameForGetter(AnnotatedMethod paramAnnotatedMethod) {
    String str3 = paramAnnotatedMethod.getName();
    String str2 = okNameForIsGetter(paramAnnotatedMethod, str3);
    String str1 = str2;
    if (str2 == null)
      str1 = okNameForRegularGetter(paramAnnotatedMethod, str3); 
    return str1;
  }
  
  public static String okNameForIsGetter(AnnotatedMethod paramAnnotatedMethod, String paramString) {
    String str = null;
    null = str;
    if (paramString.startsWith("is")) {
      Class<Boolean> clazz = paramAnnotatedMethod.getRawType();
      if (clazz != Boolean.class && clazz != boolean.class)
        return str; 
    } else {
      return null;
    } 
    return manglePropertyName(paramString.substring(2));
  }
  
  public static String okNameForRegularGetter(AnnotatedMethod paramAnnotatedMethod, String paramString) {
    String str = null;
    null = str;
    if (paramString.startsWith("get")) {
      if ("getCallbacks".equals(paramString)) {
        if (isCglibGetCallbacks(paramAnnotatedMethod))
          return str; 
      } else if ("getMetaClass".equals(paramString)) {
        null = str;
        return !isGroovyMetaClassGetter(paramAnnotatedMethod) ? manglePropertyName(paramString.substring(3)) : null;
      } 
    } else {
      return null;
    } 
    return manglePropertyName(paramString.substring(3));
  }
  
  public static String okNameForSetter(AnnotatedMethod paramAnnotatedMethod) {
    String str1 = null;
    String str2 = paramAnnotatedMethod.getName();
    null = str1;
    if (str2.startsWith("set")) {
      str2 = manglePropertyName(str2.substring(3));
      if (str2 == null)
        return str1; 
    } else {
      return null;
    } 
    if ("metaClass".equals(str2)) {
      null = str1;
      return !isGroovyMetaClassSetter(paramAnnotatedMethod) ? str2 : null;
    } 
    return str2;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\ma\\util\BeanUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */