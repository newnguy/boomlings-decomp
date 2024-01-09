package com.flurry.org.codehaus.jackson.map.util;

import java.lang.reflect.Field;
import java.util.EnumMap;
import java.util.EnumSet;

class ClassUtil$EnumTypeLocator {
  static final ClassUtil$EnumTypeLocator instance = new ClassUtil$EnumTypeLocator();
  
  private final Field enumMapTypeField = locateField(EnumMap.class, "elementType", Class.class);
  
  private final Field enumSetTypeField = locateField(EnumSet.class, "elementType", Class.class);
  
  private Object get(Object paramObject, Field paramField) {
    try {
      return paramField.get(paramObject);
    } catch (Exception exception) {
      throw new IllegalArgumentException(exception);
    } 
  }
  
  private static Field locateField(Class paramClass1, String paramString, Class<?> paramClass2) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getDeclaredFields : ()[Ljava/lang/reflect/Field;
    //   4: astore #5
    //   6: aload #5
    //   8: arraylength
    //   9: istore #4
    //   11: iconst_0
    //   12: istore_3
    //   13: iload_3
    //   14: iload #4
    //   16: if_icmpge -> 123
    //   19: aload #5
    //   21: iload_3
    //   22: aaload
    //   23: astore_0
    //   24: aload_1
    //   25: aload_0
    //   26: invokevirtual getName : ()Ljava/lang/String;
    //   29: invokevirtual equals : (Ljava/lang/Object;)Z
    //   32: ifeq -> 83
    //   35: aload_0
    //   36: invokevirtual getType : ()Ljava/lang/Class;
    //   39: aload_2
    //   40: if_acmpne -> 83
    //   43: aload_0
    //   44: astore_1
    //   45: aload_0
    //   46: ifnonnull -> 101
    //   49: aload #5
    //   51: arraylength
    //   52: istore #4
    //   54: iconst_0
    //   55: istore_3
    //   56: iload_3
    //   57: iload #4
    //   59: if_icmpge -> 99
    //   62: aload #5
    //   64: iload_3
    //   65: aaload
    //   66: astore_1
    //   67: aload_1
    //   68: invokevirtual getType : ()Ljava/lang/Class;
    //   71: aload_2
    //   72: if_acmpne -> 89
    //   75: aload_0
    //   76: ifnull -> 91
    //   79: aconst_null
    //   80: astore_0
    //   81: aload_0
    //   82: areturn
    //   83: iinc #3, 1
    //   86: goto -> 13
    //   89: aload_0
    //   90: astore_1
    //   91: iinc #3, 1
    //   94: aload_1
    //   95: astore_0
    //   96: goto -> 56
    //   99: aload_0
    //   100: astore_1
    //   101: aload_1
    //   102: astore_0
    //   103: aload_1
    //   104: ifnull -> 81
    //   107: aload_1
    //   108: iconst_1
    //   109: invokevirtual setAccessible : (Z)V
    //   112: aload_1
    //   113: astore_0
    //   114: goto -> 81
    //   117: astore_0
    //   118: aload_1
    //   119: astore_0
    //   120: goto -> 81
    //   123: aconst_null
    //   124: astore_0
    //   125: goto -> 43
    // Exception table:
    //   from	to	target	type
    //   107	112	117	java/lang/Throwable
  }
  
  public Class enumTypeFor(EnumMap paramEnumMap) {
    if (this.enumMapTypeField != null)
      return (Class)get(paramEnumMap, this.enumMapTypeField); 
    throw new IllegalStateException("Can not figure out type for EnumMap (odd JDK platform?)");
  }
  
  public Class enumTypeFor(EnumSet paramEnumSet) {
    if (this.enumSetTypeField != null)
      return (Class)get(paramEnumSet, this.enumSetTypeField); 
    throw new IllegalStateException("Can not figure out type for EnumSet (odd JDK platform?)");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\ma\\util\ClassUtil$EnumTypeLocator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */