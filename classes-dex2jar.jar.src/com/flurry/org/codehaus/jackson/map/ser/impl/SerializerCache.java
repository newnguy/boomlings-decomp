package com.flurry.org.codehaus.jackson.map.ser.impl;

import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.HashMap;

public final class SerializerCache {
  private ReadOnlyClassToSerializerMap _readOnlyMap = null;
  
  private HashMap _sharedMap = new HashMap<Object, Object>(64);
  
  public void addAndResolveNonTypedSerializer(JavaType paramJavaType, JsonSerializer paramJsonSerializer, SerializerProvider paramSerializerProvider) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield _sharedMap : Ljava/util/HashMap;
    //   6: astore #5
    //   8: new com/flurry/org/codehaus/jackson/map/ser/impl/SerializerCache$TypeKey
    //   11: astore #4
    //   13: aload #4
    //   15: aload_1
    //   16: iconst_0
    //   17: invokespecial <init> : (Lcom/flurry/org/codehaus/jackson/type/JavaType;Z)V
    //   20: aload #5
    //   22: aload #4
    //   24: aload_2
    //   25: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   28: ifnonnull -> 36
    //   31: aload_0
    //   32: aconst_null
    //   33: putfield _readOnlyMap : Lcom/flurry/org/codehaus/jackson/map/ser/impl/ReadOnlyClassToSerializerMap;
    //   36: aload_2
    //   37: instanceof com/flurry/org/codehaus/jackson/map/ResolvableSerializer
    //   40: ifeq -> 53
    //   43: aload_2
    //   44: checkcast com/flurry/org/codehaus/jackson/map/ResolvableSerializer
    //   47: aload_3
    //   48: invokeinterface resolve : (Lcom/flurry/org/codehaus/jackson/map/SerializerProvider;)V
    //   53: aload_0
    //   54: monitorexit
    //   55: return
    //   56: astore_1
    //   57: aload_0
    //   58: monitorexit
    //   59: aload_1
    //   60: athrow
    // Exception table:
    //   from	to	target	type
    //   2	36	56	finally
    //   36	53	56	finally
    //   53	55	56	finally
    //   57	59	56	finally
  }
  
  public void addAndResolveNonTypedSerializer(Class paramClass, JsonSerializer paramJsonSerializer, SerializerProvider paramSerializerProvider) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield _sharedMap : Ljava/util/HashMap;
    //   6: astore #4
    //   8: new com/flurry/org/codehaus/jackson/map/ser/impl/SerializerCache$TypeKey
    //   11: astore #5
    //   13: aload #5
    //   15: aload_1
    //   16: iconst_0
    //   17: invokespecial <init> : (Ljava/lang/Class;Z)V
    //   20: aload #4
    //   22: aload #5
    //   24: aload_2
    //   25: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   28: ifnonnull -> 36
    //   31: aload_0
    //   32: aconst_null
    //   33: putfield _readOnlyMap : Lcom/flurry/org/codehaus/jackson/map/ser/impl/ReadOnlyClassToSerializerMap;
    //   36: aload_2
    //   37: instanceof com/flurry/org/codehaus/jackson/map/ResolvableSerializer
    //   40: ifeq -> 53
    //   43: aload_2
    //   44: checkcast com/flurry/org/codehaus/jackson/map/ResolvableSerializer
    //   47: aload_3
    //   48: invokeinterface resolve : (Lcom/flurry/org/codehaus/jackson/map/SerializerProvider;)V
    //   53: aload_0
    //   54: monitorexit
    //   55: return
    //   56: astore_1
    //   57: aload_0
    //   58: monitorexit
    //   59: aload_1
    //   60: athrow
    // Exception table:
    //   from	to	target	type
    //   2	36	56	finally
    //   36	53	56	finally
    //   53	55	56	finally
    //   57	59	56	finally
  }
  
  public void addTypedSerializer(JavaType paramJavaType, JsonSerializer paramJsonSerializer) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield _sharedMap : Ljava/util/HashMap;
    //   6: astore #4
    //   8: new com/flurry/org/codehaus/jackson/map/ser/impl/SerializerCache$TypeKey
    //   11: astore_3
    //   12: aload_3
    //   13: aload_1
    //   14: iconst_1
    //   15: invokespecial <init> : (Lcom/flurry/org/codehaus/jackson/type/JavaType;Z)V
    //   18: aload #4
    //   20: aload_3
    //   21: aload_2
    //   22: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   25: ifnonnull -> 33
    //   28: aload_0
    //   29: aconst_null
    //   30: putfield _readOnlyMap : Lcom/flurry/org/codehaus/jackson/map/ser/impl/ReadOnlyClassToSerializerMap;
    //   33: aload_0
    //   34: monitorexit
    //   35: return
    //   36: astore_1
    //   37: aload_0
    //   38: monitorexit
    //   39: aload_1
    //   40: athrow
    // Exception table:
    //   from	to	target	type
    //   2	33	36	finally
    //   33	35	36	finally
    //   37	39	36	finally
  }
  
  public void addTypedSerializer(Class paramClass, JsonSerializer paramJsonSerializer) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield _sharedMap : Ljava/util/HashMap;
    //   6: astore #4
    //   8: new com/flurry/org/codehaus/jackson/map/ser/impl/SerializerCache$TypeKey
    //   11: astore_3
    //   12: aload_3
    //   13: aload_1
    //   14: iconst_1
    //   15: invokespecial <init> : (Ljava/lang/Class;Z)V
    //   18: aload #4
    //   20: aload_3
    //   21: aload_2
    //   22: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   25: ifnonnull -> 33
    //   28: aload_0
    //   29: aconst_null
    //   30: putfield _readOnlyMap : Lcom/flurry/org/codehaus/jackson/map/ser/impl/ReadOnlyClassToSerializerMap;
    //   33: aload_0
    //   34: monitorexit
    //   35: return
    //   36: astore_1
    //   37: aload_0
    //   38: monitorexit
    //   39: aload_1
    //   40: athrow
    // Exception table:
    //   from	to	target	type
    //   2	33	36	finally
    //   33	35	36	finally
    //   37	39	36	finally
  }
  
  public void flush() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield _sharedMap : Ljava/util/HashMap;
    //   6: invokevirtual clear : ()V
    //   9: aload_0
    //   10: monitorexit
    //   11: return
    //   12: astore_1
    //   13: aload_0
    //   14: monitorexit
    //   15: aload_1
    //   16: athrow
    // Exception table:
    //   from	to	target	type
    //   2	9	12	finally
  }
  
  public ReadOnlyClassToSerializerMap getReadOnlyLookupMap() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield _readOnlyMap : Lcom/flurry/org/codehaus/jackson/map/ser/impl/ReadOnlyClassToSerializerMap;
    //   6: astore_2
    //   7: aload_2
    //   8: astore_1
    //   9: aload_2
    //   10: ifnonnull -> 26
    //   13: aload_0
    //   14: getfield _sharedMap : Ljava/util/HashMap;
    //   17: invokestatic from : (Ljava/util/HashMap;)Lcom/flurry/org/codehaus/jackson/map/ser/impl/ReadOnlyClassToSerializerMap;
    //   20: astore_1
    //   21: aload_0
    //   22: aload_1
    //   23: putfield _readOnlyMap : Lcom/flurry/org/codehaus/jackson/map/ser/impl/ReadOnlyClassToSerializerMap;
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_1
    //   29: invokevirtual instance : ()Lcom/flurry/org/codehaus/jackson/map/ser/impl/ReadOnlyClassToSerializerMap;
    //   32: areturn
    //   33: astore_1
    //   34: aload_0
    //   35: monitorexit
    //   36: aload_1
    //   37: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	33	finally
    //   13	26	33	finally
    //   26	28	33	finally
    //   34	36	33	finally
  }
  
  public int size() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield _sharedMap : Ljava/util/HashMap;
    //   6: invokevirtual size : ()I
    //   9: istore_1
    //   10: aload_0
    //   11: monitorexit
    //   12: iload_1
    //   13: ireturn
    //   14: astore_2
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_2
    //   18: athrow
    // Exception table:
    //   from	to	target	type
    //   2	10	14	finally
  }
  
  public JsonSerializer typedValueSerializer(JavaType paramJavaType) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield _sharedMap : Ljava/util/HashMap;
    //   6: astore_2
    //   7: new com/flurry/org/codehaus/jackson/map/ser/impl/SerializerCache$TypeKey
    //   10: astore_3
    //   11: aload_3
    //   12: aload_1
    //   13: iconst_1
    //   14: invokespecial <init> : (Lcom/flurry/org/codehaus/jackson/type/JavaType;Z)V
    //   17: aload_2
    //   18: aload_3
    //   19: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   22: checkcast com/flurry/org/codehaus/jackson/map/JsonSerializer
    //   25: astore_1
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_1
    //   29: areturn
    //   30: astore_1
    //   31: aload_0
    //   32: monitorexit
    //   33: aload_1
    //   34: athrow
    // Exception table:
    //   from	to	target	type
    //   2	28	30	finally
    //   31	33	30	finally
  }
  
  public JsonSerializer typedValueSerializer(Class paramClass) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield _sharedMap : Ljava/util/HashMap;
    //   6: astore_2
    //   7: new com/flurry/org/codehaus/jackson/map/ser/impl/SerializerCache$TypeKey
    //   10: astore_3
    //   11: aload_3
    //   12: aload_1
    //   13: iconst_1
    //   14: invokespecial <init> : (Ljava/lang/Class;Z)V
    //   17: aload_2
    //   18: aload_3
    //   19: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   22: checkcast com/flurry/org/codehaus/jackson/map/JsonSerializer
    //   25: astore_1
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_1
    //   29: areturn
    //   30: astore_1
    //   31: aload_0
    //   32: monitorexit
    //   33: aload_1
    //   34: athrow
    // Exception table:
    //   from	to	target	type
    //   2	28	30	finally
    //   31	33	30	finally
  }
  
  public JsonSerializer untypedValueSerializer(JavaType paramJavaType) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield _sharedMap : Ljava/util/HashMap;
    //   6: astore_2
    //   7: new com/flurry/org/codehaus/jackson/map/ser/impl/SerializerCache$TypeKey
    //   10: astore_3
    //   11: aload_3
    //   12: aload_1
    //   13: iconst_0
    //   14: invokespecial <init> : (Lcom/flurry/org/codehaus/jackson/type/JavaType;Z)V
    //   17: aload_2
    //   18: aload_3
    //   19: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   22: checkcast com/flurry/org/codehaus/jackson/map/JsonSerializer
    //   25: astore_1
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_1
    //   29: areturn
    //   30: astore_1
    //   31: aload_0
    //   32: monitorexit
    //   33: aload_1
    //   34: athrow
    // Exception table:
    //   from	to	target	type
    //   2	28	30	finally
    //   31	33	30	finally
  }
  
  public JsonSerializer untypedValueSerializer(Class paramClass) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield _sharedMap : Ljava/util/HashMap;
    //   6: astore_3
    //   7: new com/flurry/org/codehaus/jackson/map/ser/impl/SerializerCache$TypeKey
    //   10: astore_2
    //   11: aload_2
    //   12: aload_1
    //   13: iconst_0
    //   14: invokespecial <init> : (Ljava/lang/Class;Z)V
    //   17: aload_3
    //   18: aload_2
    //   19: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   22: checkcast com/flurry/org/codehaus/jackson/map/JsonSerializer
    //   25: astore_1
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_1
    //   29: areturn
    //   30: astore_1
    //   31: aload_0
    //   32: monitorexit
    //   33: aload_1
    //   34: athrow
    // Exception table:
    //   from	to	target	type
    //   2	28	30	finally
    //   31	33	30	finally
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\impl\SerializerCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */