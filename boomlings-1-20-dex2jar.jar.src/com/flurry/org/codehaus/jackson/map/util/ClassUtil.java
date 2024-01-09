package com.flurry.org.codehaus.jackson.map.util;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

public final class ClassUtil {
  private static void _addSuperTypes(Class<Object> paramClass1, Class paramClass2, Collection<Class<Object>> paramCollection, boolean paramBoolean) {
    if (paramClass1 != paramClass2 && paramClass1 != null && paramClass1 != Object.class) {
      if (paramBoolean)
        if (!paramCollection.contains(paramClass1)) {
          paramCollection.add(paramClass1);
        } else {
          return;
        }  
      Class[] arrayOfClass = paramClass1.getInterfaces();
      int i = arrayOfClass.length;
      for (byte b = 0; b < i; b++)
        _addSuperTypes(arrayOfClass[b], paramClass2, paramCollection, true); 
      _addSuperTypes(paramClass1.getSuperclass(), paramClass2, paramCollection, true);
    } 
  }
  
  public static String canBeABeanType(Class paramClass) {
    return paramClass.isAnnotation() ? "annotation" : (paramClass.isArray() ? "array" : (paramClass.isEnum() ? "enum" : (paramClass.isPrimitive() ? "primitive" : null)));
  }
  
  public static void checkAndFixAccess(Member paramMember) {
    AccessibleObject accessibleObject = (AccessibleObject)paramMember;
    try {
      accessibleObject.setAccessible(true);
    } catch (SecurityException securityException) {}
  }
  
  public static Object createInstance(Class paramClass, boolean paramBoolean) {
    Constructor<Constructor> constructor1;
    Constructor<Constructor> constructor2 = findConstructor(paramClass, paramBoolean);
    if (constructor2 == null)
      throw new IllegalArgumentException("Class " + paramClass.getName() + " has no default (no arg) constructor"); 
    try {
      constructor2 = constructor2.newInstance(new Object[0]);
      constructor1 = constructor2;
    } catch (Exception exception) {
      unwrapAndThrowAsIAE(exception, "Failed to instantiate class " + constructor1.getName() + ", problem: " + exception.getMessage());
      constructor1 = null;
    } 
    return constructor1;
  }
  
  public static Object defaultValue(Class<int> paramClass) {
    if (paramClass == int.class)
      return Integer.valueOf(0); 
    if (paramClass == long.class)
      return Long.valueOf(0L); 
    if (paramClass == boolean.class)
      return Boolean.FALSE; 
    if (paramClass == double.class)
      return Double.valueOf(0.0D); 
    if (paramClass == float.class)
      return Float.valueOf(0.0F); 
    if (paramClass == byte.class)
      return Byte.valueOf((byte)0); 
    if (paramClass == short.class)
      return Short.valueOf((short)0); 
    if (paramClass == char.class)
      return Character.valueOf(false); 
    throw new IllegalArgumentException("Class " + paramClass.getName() + " is not a primitive type");
  }
  
  public static Constructor findConstructor(Class paramClass, boolean paramBoolean) {
    try {
      StringBuilder stringBuilder;
      Constructor constructor2 = paramClass.getDeclaredConstructor(new Class[0]);
      if (paramBoolean) {
        checkAndFixAccess(constructor2);
        return constructor2;
      } 
      Constructor constructor1 = constructor2;
      if (!Modifier.isPublic(constructor2.getModifiers())) {
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
        stringBuilder = new StringBuilder();
        this();
        this(stringBuilder.append("Default constructor for ").append(paramClass.getName()).append(" is not accessible (non-public?): not allowed to try modify access via Reflection: can not instantiate type").toString());
        throw illegalArgumentException;
      } 
      return (Constructor)stringBuilder;
    } catch (NoSuchMethodException noSuchMethodException) {
    
    } catch (Exception exception) {
      unwrapAndThrowAsIAE(exception, "Failed to find default constructor of class " + noSuchMethodException.getName() + ", problem: " + exception.getMessage());
    } 
    return null;
  }
  
  public static Class findEnumType(Class<Enum> paramClass) {
    Class<Enum> clazz = paramClass;
    if (paramClass.getSuperclass() != Enum.class)
      clazz = (Class)paramClass.getSuperclass(); 
    return clazz;
  }
  
  public static Class findEnumType(Enum paramEnum) {
    Class<?> clazz2 = paramEnum.getClass();
    Class<?> clazz1 = clazz2;
    if (clazz2.getSuperclass() != Enum.class)
      clazz1 = clazz2.getSuperclass(); 
    return clazz1;
  }
  
  public static Class findEnumType(EnumMap paramEnumMap) {
    return !paramEnumMap.isEmpty() ? findEnumType(paramEnumMap.keySet().iterator().next()) : ClassUtil$EnumTypeLocator.instance.enumTypeFor(paramEnumMap);
  }
  
  public static Class findEnumType(EnumSet<Enum> paramEnumSet) {
    return !paramEnumSet.isEmpty() ? findEnumType(paramEnumSet.iterator().next()) : ClassUtil$EnumTypeLocator.instance.enumTypeFor(paramEnumSet);
  }
  
  public static List findSuperTypes(Class paramClass1, Class paramClass2) {
    return findSuperTypes(paramClass1, paramClass2, new ArrayList(8));
  }
  
  public static List findSuperTypes(Class paramClass1, Class paramClass2, List paramList) {
    _addSuperTypes(paramClass1, paramClass2, paramList, false);
    return paramList;
  }
  
  public static String getClassDescription(Object<?> paramObject) {
    if (paramObject == null)
      return "unknown"; 
    if (paramObject instanceof Class) {
      paramObject = paramObject;
    } else {
      paramObject = (Object<?>)paramObject.getClass();
    } 
    return paramObject.getName();
  }
  
  public static Class getOuterClass(Class paramClass) {
    Class<?> clazz1;
    Class<?> clazz2 = null;
    try {
      if (paramClass.getEnclosingMethod() != null)
        return clazz2; 
      clazz1 = clazz2;
      if (!Modifier.isStatic(paramClass.getModifiers()))
        clazz1 = paramClass.getEnclosingClass(); 
    } catch (SecurityException securityException) {
      clazz1 = clazz2;
    } catch (NullPointerException nullPointerException) {
      clazz1 = clazz2;
    } 
    return clazz1;
  }
  
  public static Throwable getRootCause(Throwable paramThrowable) {
    while (paramThrowable.getCause() != null)
      paramThrowable = paramThrowable.getCause(); 
    return paramThrowable;
  }
  
  public static boolean hasGetterSignature(Method paramMethod) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aload_0
    //   3: invokevirtual getModifiers : ()I
    //   6: invokestatic isStatic : (I)Z
    //   9: ifeq -> 16
    //   12: iload_2
    //   13: istore_1
    //   14: iload_1
    //   15: ireturn
    //   16: aload_0
    //   17: invokevirtual getParameterTypes : ()[Ljava/lang/Class;
    //   20: astore_3
    //   21: aload_3
    //   22: ifnull -> 32
    //   25: iload_2
    //   26: istore_1
    //   27: aload_3
    //   28: arraylength
    //   29: ifne -> 14
    //   32: iload_2
    //   33: istore_1
    //   34: getstatic java/lang/Void.TYPE : Ljava/lang/Class;
    //   37: aload_0
    //   38: invokevirtual getReturnType : ()Ljava/lang/Class;
    //   41: if_acmpeq -> 14
    //   44: iconst_1
    //   45: istore_1
    //   46: goto -> 14
  }
  
  public static boolean isCollectionMapOrArray(Class<?> paramClass) {
    boolean bool2 = true;
    if (paramClass.isArray())
      return bool2; 
    boolean bool1 = bool2;
    if (!Collection.class.isAssignableFrom(paramClass)) {
      bool1 = bool2;
      if (!Map.class.isAssignableFrom(paramClass))
        bool1 = false; 
    } 
    return bool1;
  }
  
  public static boolean isConcrete(Class paramClass) {
    return ((paramClass.getModifiers() & 0x600) == 0);
  }
  
  public static boolean isConcrete(Member paramMember) {
    return ((paramMember.getModifiers() & 0x600) == 0);
  }
  
  @Deprecated
  public static String isLocalType(Class paramClass) {
    return isLocalType(paramClass, false);
  }
  
  public static String isLocalType(Class paramClass, boolean paramBoolean) {
    try {
      if (paramClass.getEnclosingMethod() != null)
        return "local/anonymous"; 
      if (!paramBoolean && paramClass.getEnclosingClass() != null && !Modifier.isStatic(paramClass.getModifiers()))
        return "non-static member class"; 
    } catch (SecurityException securityException) {
    
    } catch (NullPointerException nullPointerException) {}
    return null;
  }
  
  public static boolean isProxyType(Class<?> paramClass) {
    boolean bool2 = true;
    if (Proxy.isProxyClass(paramClass))
      return bool2; 
    String str = paramClass.getName();
    boolean bool1 = bool2;
    if (!str.startsWith("net.sf.cglib.proxy.")) {
      bool1 = bool2;
      if (!str.startsWith("org.hibernate.proxy."))
        bool1 = false; 
    } 
    return bool1;
  }
  
  public static void throwAsIAE(Throwable paramThrowable) {
    throwAsIAE(paramThrowable, paramThrowable.getMessage());
  }
  
  public static void throwAsIAE(Throwable paramThrowable, String paramString) {
    if (paramThrowable instanceof RuntimeException)
      throw (RuntimeException)paramThrowable; 
    if (paramThrowable instanceof Error)
      throw (Error)paramThrowable; 
    throw new IllegalArgumentException(paramString, paramThrowable);
  }
  
  public static void throwRootCause(Throwable paramThrowable) {
    paramThrowable = getRootCause(paramThrowable);
    if (paramThrowable instanceof Exception)
      throw (Exception)paramThrowable; 
    throw (Error)paramThrowable;
  }
  
  public static void unwrapAndThrowAsIAE(Throwable paramThrowable) {
    throwAsIAE(getRootCause(paramThrowable));
  }
  
  public static void unwrapAndThrowAsIAE(Throwable paramThrowable, String paramString) {
    throwAsIAE(getRootCause(paramThrowable), paramString);
  }
  
  public static Class wrapperType(Class<int> paramClass) {
    if (paramClass == int.class)
      return Integer.class; 
    if (paramClass == long.class)
      return Long.class; 
    if (paramClass == boolean.class)
      return Boolean.class; 
    if (paramClass == double.class)
      return Double.class; 
    if (paramClass == float.class)
      return Float.class; 
    if (paramClass == byte.class)
      return Byte.class; 
    if (paramClass == short.class)
      return Short.class; 
    if (paramClass == char.class)
      return Character.class; 
    throw new IllegalArgumentException("Class " + paramClass.getName() + " is not a primitive type");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\ma\\util\ClassUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */