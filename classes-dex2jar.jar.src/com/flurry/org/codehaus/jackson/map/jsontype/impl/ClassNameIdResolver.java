package com.flurry.org.codehaus.jackson.map.jsontype.impl;

import com.flurry.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.flurry.org.codehaus.jackson.map.type.TypeFactory;
import com.flurry.org.codehaus.jackson.type.JavaType;

public class ClassNameIdResolver extends TypeIdResolverBase {
  public ClassNameIdResolver(JavaType paramJavaType, TypeFactory paramTypeFactory) {
    super(paramJavaType, paramTypeFactory);
  }
  
  protected final String _idFrom(Object paramObject, Class<?> paramClass) {
    // Byte code:
    //   0: aload_2
    //   1: astore_3
    //   2: ldc java/lang/Enum
    //   4: aload_2
    //   5: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
    //   8: ifeq -> 25
    //   11: aload_2
    //   12: astore_3
    //   13: aload_2
    //   14: invokevirtual isEnum : ()Z
    //   17: ifne -> 25
    //   20: aload_2
    //   21: invokevirtual getSuperclass : ()Ljava/lang/Class;
    //   24: astore_3
    //   25: aload_3
    //   26: invokevirtual getName : ()Ljava/lang/String;
    //   29: astore_2
    //   30: aload_2
    //   31: ldc 'java.util'
    //   33: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   36: ifeq -> 146
    //   39: aload_1
    //   40: instanceof java/util/EnumSet
    //   43: ifeq -> 69
    //   46: aload_1
    //   47: checkcast java/util/EnumSet
    //   50: invokestatic findEnumType : (Ljava/util/EnumSet;)Ljava/lang/Class;
    //   53: astore_1
    //   54: invokestatic defaultInstance : ()Lcom/flurry/org/codehaus/jackson/map/type/TypeFactory;
    //   57: ldc java/util/EnumSet
    //   59: aload_1
    //   60: invokevirtual constructCollectionType : (Ljava/lang/Class;Ljava/lang/Class;)Lcom/flurry/org/codehaus/jackson/map/type/CollectionType;
    //   63: invokevirtual toCanonical : ()Ljava/lang/String;
    //   66: astore_1
    //   67: aload_1
    //   68: areturn
    //   69: aload_1
    //   70: instanceof java/util/EnumMap
    //   73: ifeq -> 102
    //   76: aload_1
    //   77: checkcast java/util/EnumMap
    //   80: invokestatic findEnumType : (Ljava/util/EnumMap;)Ljava/lang/Class;
    //   83: astore_1
    //   84: invokestatic defaultInstance : ()Lcom/flurry/org/codehaus/jackson/map/type/TypeFactory;
    //   87: ldc java/util/EnumMap
    //   89: aload_1
    //   90: ldc java/lang/Object
    //   92: invokevirtual constructMapType : (Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;)Lcom/flurry/org/codehaus/jackson/map/type/MapType;
    //   95: invokevirtual toCanonical : ()Ljava/lang/String;
    //   98: astore_1
    //   99: goto -> 67
    //   102: aload_2
    //   103: bipush #9
    //   105: invokevirtual substring : (I)Ljava/lang/String;
    //   108: astore_3
    //   109: aload_3
    //   110: ldc '.Arrays$'
    //   112: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   115: ifne -> 129
    //   118: aload_2
    //   119: astore_1
    //   120: aload_3
    //   121: ldc '.Collections$'
    //   123: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   126: ifeq -> 67
    //   129: aload_2
    //   130: astore_1
    //   131: aload_2
    //   132: ldc 'List'
    //   134: invokevirtual indexOf : (Ljava/lang/String;)I
    //   137: iflt -> 67
    //   140: ldc 'java.util.ArrayList'
    //   142: astore_1
    //   143: goto -> 67
    //   146: aload_2
    //   147: astore_1
    //   148: aload_2
    //   149: bipush #36
    //   151: invokevirtual indexOf : (I)I
    //   154: iflt -> 67
    //   157: aload_2
    //   158: astore_1
    //   159: aload_3
    //   160: invokestatic getOuterClass : (Ljava/lang/Class;)Ljava/lang/Class;
    //   163: ifnull -> 67
    //   166: aload_2
    //   167: astore_1
    //   168: aload_0
    //   169: getfield _baseType : Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   172: invokevirtual getRawClass : ()Ljava/lang/Class;
    //   175: invokestatic getOuterClass : (Ljava/lang/Class;)Ljava/lang/Class;
    //   178: ifnonnull -> 67
    //   181: aload_0
    //   182: getfield _baseType : Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   185: invokevirtual getRawClass : ()Ljava/lang/Class;
    //   188: invokevirtual getName : ()Ljava/lang/String;
    //   191: astore_1
    //   192: goto -> 67
  }
  
  public JsonTypeInfo.Id getMechanism() {
    return JsonTypeInfo.Id.CLASS;
  }
  
  public String idFromValue(Object paramObject) {
    return _idFrom(paramObject, paramObject.getClass());
  }
  
  public String idFromValueAndType(Object paramObject, Class paramClass) {
    return _idFrom(paramObject, paramClass);
  }
  
  public void registerSubtype(Class paramClass, String paramString) {}
  
  public JavaType typeFromId(String paramString) {
    if (paramString.indexOf('<') > 0)
      return TypeFactory.fromCanonical(paramString); 
    try {
      Class<?> clazz = Class.forName(paramString, true, Thread.currentThread().getContextClassLoader());
      return this._typeFactory.constructSpecializedType(this._baseType, clazz);
    } catch (ClassNotFoundException classNotFoundException) {
      throw new IllegalArgumentException("Invalid type id '" + paramString + "' (for id type 'Id.class'): no such class found");
    } catch (Exception exception) {
      throw new IllegalArgumentException("Invalid type id '" + paramString + "' (for id type 'Id.class'): " + exception.getMessage(), exception);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\jsontype\impl\ClassNameIdResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */