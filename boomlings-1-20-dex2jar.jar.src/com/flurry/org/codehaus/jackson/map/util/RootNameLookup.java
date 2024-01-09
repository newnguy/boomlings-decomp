package com.flurry.org.codehaus.jackson.map.util;

import com.flurry.org.codehaus.jackson.io.SerializedString;
import com.flurry.org.codehaus.jackson.map.MapperConfig;
import com.flurry.org.codehaus.jackson.type.JavaType;

public class RootNameLookup {
  protected LRUMap _rootNames;
  
  public SerializedString findRootName(JavaType paramJavaType, MapperConfig paramMapperConfig) {
    return findRootName(paramJavaType.getRawClass(), paramMapperConfig);
  }
  
  public SerializedString findRootName(Class paramClass, MapperConfig paramMapperConfig) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new com/flurry/org/codehaus/jackson/map/type/ClassKey
    //   5: astore #4
    //   7: aload #4
    //   9: aload_1
    //   10: invokespecial <init> : (Ljava/lang/Class;)V
    //   13: aload_0
    //   14: getfield _rootNames : Lcom/flurry/org/codehaus/jackson/map/util/LRUMap;
    //   17: ifnonnull -> 94
    //   20: new com/flurry/org/codehaus/jackson/map/util/LRUMap
    //   23: astore_3
    //   24: aload_3
    //   25: bipush #20
    //   27: sipush #200
    //   30: invokespecial <init> : (II)V
    //   33: aload_0
    //   34: aload_3
    //   35: putfield _rootNames : Lcom/flurry/org/codehaus/jackson/map/util/LRUMap;
    //   38: aload_2
    //   39: aload_1
    //   40: invokevirtual introspectClassAnnotations : (Ljava/lang/Class;)Lcom/flurry/org/codehaus/jackson/map/BeanDescription;
    //   43: checkcast com/flurry/org/codehaus/jackson/map/introspect/BasicBeanDescription
    //   46: astore_3
    //   47: aload_2
    //   48: invokevirtual getAnnotationIntrospector : ()Lcom/flurry/org/codehaus/jackson/map/AnnotationIntrospector;
    //   51: aload_3
    //   52: invokevirtual getClassInfo : ()Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotatedClass;
    //   55: invokevirtual findRootName : (Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotatedClass;)Ljava/lang/String;
    //   58: astore_2
    //   59: aload_2
    //   60: ifnonnull -> 121
    //   63: aload_1
    //   64: invokevirtual getSimpleName : ()Ljava/lang/String;
    //   67: astore_1
    //   68: new com/flurry/org/codehaus/jackson/io/SerializedString
    //   71: astore_2
    //   72: aload_2
    //   73: aload_1
    //   74: invokespecial <init> : (Ljava/lang/String;)V
    //   77: aload_0
    //   78: getfield _rootNames : Lcom/flurry/org/codehaus/jackson/map/util/LRUMap;
    //   81: aload #4
    //   83: aload_2
    //   84: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   87: pop
    //   88: aload_2
    //   89: astore_1
    //   90: aload_0
    //   91: monitorexit
    //   92: aload_1
    //   93: areturn
    //   94: aload_0
    //   95: getfield _rootNames : Lcom/flurry/org/codehaus/jackson/map/util/LRUMap;
    //   98: aload #4
    //   100: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   103: checkcast com/flurry/org/codehaus/jackson/io/SerializedString
    //   106: astore_3
    //   107: aload_3
    //   108: ifnull -> 38
    //   111: aload_3
    //   112: astore_1
    //   113: goto -> 90
    //   116: astore_1
    //   117: aload_0
    //   118: monitorexit
    //   119: aload_1
    //   120: athrow
    //   121: aload_2
    //   122: astore_1
    //   123: goto -> 68
    // Exception table:
    //   from	to	target	type
    //   2	38	116	finally
    //   38	59	116	finally
    //   63	68	116	finally
    //   68	88	116	finally
    //   94	107	116	finally
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\ma\\util\RootNameLookup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */