package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializerProvider;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.type.JavaType;

public class OptionalHandlerFactory {
  private static final String CLASS_NAME_DOM_DOCUMENT = "org.w3c.dom.Node";
  
  private static final String CLASS_NAME_DOM_NODE = "org.w3c.dom.Node";
  
  private static final String DESERIALIZERS_FOR_JAVAX_XML = "com.flurry.org.codehaus.jackson.map.ext.CoreXMLDeserializers";
  
  private static final String DESERIALIZERS_FOR_JODA_DATETIME = "com.flurry.org.codehaus.jackson.map.ext.JodaDeserializers";
  
  private static final String DESERIALIZER_FOR_DOM_DOCUMENT = "com.flurry.org.codehaus.jackson.map.ext.DOMDeserializer$DocumentDeserializer";
  
  private static final String DESERIALIZER_FOR_DOM_NODE = "com.flurry.org.codehaus.jackson.map.ext.DOMDeserializer$NodeDeserializer";
  
  private static final String PACKAGE_PREFIX_JAVAX_XML = "javax.xml.";
  
  private static final String PACKAGE_PREFIX_JODA_DATETIME = "org.joda.time.";
  
  private static final String SERIALIZERS_FOR_JAVAX_XML = "com.flurry.org.codehaus.jackson.map.ext.CoreXMLSerializers";
  
  private static final String SERIALIZERS_FOR_JODA_DATETIME = "com.flurry.org.codehaus.jackson.map.ext.JodaSerializers";
  
  private static final String SERIALIZER_FOR_DOM_NODE = "com.flurry.org.codehaus.jackson.map.ext.DOMSerializer";
  
  public static final OptionalHandlerFactory instance = new OptionalHandlerFactory();
  
  private boolean doesImplement(Class paramClass, String paramString) {
    // Byte code:
    //   0: iconst_1
    //   1: istore #4
    //   3: aload_1
    //   4: ifnull -> 43
    //   7: aload_1
    //   8: invokevirtual getName : ()Ljava/lang/String;
    //   11: aload_2
    //   12: invokevirtual equals : (Ljava/lang/Object;)Z
    //   15: ifeq -> 23
    //   18: iload #4
    //   20: istore_3
    //   21: iload_3
    //   22: ireturn
    //   23: iload #4
    //   25: istore_3
    //   26: aload_0
    //   27: aload_1
    //   28: aload_2
    //   29: invokespecial hasInterface : (Ljava/lang/Class;Ljava/lang/String;)Z
    //   32: ifne -> 21
    //   35: aload_1
    //   36: invokevirtual getSuperclass : ()Ljava/lang/Class;
    //   39: astore_1
    //   40: goto -> 3
    //   43: iconst_0
    //   44: istore_3
    //   45: goto -> 21
  }
  
  private boolean hasInterface(Class paramClass, String paramString) {
    // Byte code:
    //   0: iconst_1
    //   1: istore #6
    //   3: aload_1
    //   4: invokevirtual getInterfaces : ()[Ljava/lang/Class;
    //   7: astore_1
    //   8: aload_1
    //   9: arraylength
    //   10: istore #4
    //   12: iconst_0
    //   13: istore_3
    //   14: iload_3
    //   15: iload #4
    //   17: if_icmpge -> 46
    //   20: aload_1
    //   21: iload_3
    //   22: aaload
    //   23: invokevirtual getName : ()Ljava/lang/String;
    //   26: aload_2
    //   27: invokevirtual equals : (Ljava/lang/Object;)Z
    //   30: ifeq -> 40
    //   33: iload #6
    //   35: istore #5
    //   37: iload #5
    //   39: ireturn
    //   40: iinc #3, 1
    //   43: goto -> 14
    //   46: aload_1
    //   47: arraylength
    //   48: istore #4
    //   50: iconst_0
    //   51: istore_3
    //   52: iload_3
    //   53: iload #4
    //   55: if_icmpge -> 79
    //   58: iload #6
    //   60: istore #5
    //   62: aload_0
    //   63: aload_1
    //   64: iload_3
    //   65: aaload
    //   66: aload_2
    //   67: invokespecial hasInterface : (Ljava/lang/Class;Ljava/lang/String;)Z
    //   70: ifne -> 37
    //   73: iinc #3, 1
    //   76: goto -> 52
    //   79: iconst_0
    //   80: istore #5
    //   82: goto -> 37
  }
  
  private boolean hasInterfaceStartingWith(Class paramClass, String paramString) {
    // Byte code:
    //   0: iconst_1
    //   1: istore #6
    //   3: aload_1
    //   4: invokevirtual getInterfaces : ()[Ljava/lang/Class;
    //   7: astore_1
    //   8: aload_1
    //   9: arraylength
    //   10: istore #4
    //   12: iconst_0
    //   13: istore_3
    //   14: iload_3
    //   15: iload #4
    //   17: if_icmpge -> 46
    //   20: aload_1
    //   21: iload_3
    //   22: aaload
    //   23: invokevirtual getName : ()Ljava/lang/String;
    //   26: aload_2
    //   27: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   30: ifeq -> 40
    //   33: iload #6
    //   35: istore #5
    //   37: iload #5
    //   39: ireturn
    //   40: iinc #3, 1
    //   43: goto -> 14
    //   46: aload_1
    //   47: arraylength
    //   48: istore #4
    //   50: iconst_0
    //   51: istore_3
    //   52: iload_3
    //   53: iload #4
    //   55: if_icmpge -> 79
    //   58: iload #6
    //   60: istore #5
    //   62: aload_0
    //   63: aload_1
    //   64: iload_3
    //   65: aaload
    //   66: aload_2
    //   67: invokespecial hasInterfaceStartingWith : (Ljava/lang/Class;Ljava/lang/String;)Z
    //   70: ifne -> 37
    //   73: iinc #3, 1
    //   76: goto -> 52
    //   79: iconst_0
    //   80: istore #5
    //   82: goto -> 37
  }
  
  private boolean hasSupertypeStartingWith(Class paramClass, String paramString) {
    // Byte code:
    //   0: iconst_1
    //   1: istore_3
    //   2: aload_1
    //   3: invokevirtual getSuperclass : ()Ljava/lang/Class;
    //   6: astore #4
    //   8: aload_1
    //   9: astore #5
    //   11: aload #4
    //   13: ifnull -> 47
    //   16: aload #4
    //   18: invokevirtual getName : ()Ljava/lang/String;
    //   21: aload_2
    //   22: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   25: ifeq -> 30
    //   28: iload_3
    //   29: ireturn
    //   30: aload #4
    //   32: invokevirtual getSuperclass : ()Ljava/lang/Class;
    //   35: astore #4
    //   37: goto -> 8
    //   40: aload #5
    //   42: invokevirtual getSuperclass : ()Ljava/lang/Class;
    //   45: astore #5
    //   47: aload #5
    //   49: ifnull -> 65
    //   52: aload_0
    //   53: aload #5
    //   55: aload_2
    //   56: invokespecial hasInterfaceStartingWith : (Ljava/lang/Class;Ljava/lang/String;)Z
    //   59: ifeq -> 40
    //   62: goto -> 28
    //   65: iconst_0
    //   66: istore_3
    //   67: goto -> 28
  }
  
  private Object instantiate(String paramString) {
    try {
      return Class.forName(paramString).newInstance();
    } catch (LinkageError linkageError) {
    
    } catch (Exception exception) {}
    return null;
  }
  
  public JsonDeserializer findDeserializer(JavaType paramJavaType, DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getRawClass : ()Ljava/lang/Class;
    //   4: astore_2
    //   5: aload_2
    //   6: invokevirtual getName : ()Ljava/lang/String;
    //   9: astore_1
    //   10: aload_1
    //   11: ldc 'org.joda.time.'
    //   13: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   16: ifeq -> 36
    //   19: ldc 'com.flurry.org.codehaus.jackson.map.ext.JodaDeserializers'
    //   21: astore_1
    //   22: aload_0
    //   23: aload_1
    //   24: invokespecial instantiate : (Ljava/lang/String;)Ljava/lang/Object;
    //   27: astore_1
    //   28: aload_1
    //   29: ifnonnull -> 112
    //   32: aconst_null
    //   33: astore_1
    //   34: aload_1
    //   35: areturn
    //   36: aload_1
    //   37: ldc 'javax.xml.'
    //   39: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   42: ifne -> 55
    //   45: aload_0
    //   46: aload_2
    //   47: ldc 'javax.xml.'
    //   49: invokespecial hasSupertypeStartingWith : (Ljava/lang/Class;Ljava/lang/String;)Z
    //   52: ifeq -> 61
    //   55: ldc 'com.flurry.org.codehaus.jackson.map.ext.CoreXMLDeserializers'
    //   57: astore_1
    //   58: goto -> 22
    //   61: aload_0
    //   62: aload_2
    //   63: ldc 'org.w3c.dom.Node'
    //   65: invokespecial doesImplement : (Ljava/lang/Class;Ljava/lang/String;)Z
    //   68: ifeq -> 84
    //   71: aload_0
    //   72: ldc 'com.flurry.org.codehaus.jackson.map.ext.DOMDeserializer$DocumentDeserializer'
    //   74: invokespecial instantiate : (Ljava/lang/String;)Ljava/lang/Object;
    //   77: checkcast com/flurry/org/codehaus/jackson/map/JsonDeserializer
    //   80: astore_1
    //   81: goto -> 34
    //   84: aload_0
    //   85: aload_2
    //   86: ldc 'org.w3c.dom.Node'
    //   88: invokespecial doesImplement : (Ljava/lang/Class;Ljava/lang/String;)Z
    //   91: ifeq -> 107
    //   94: aload_0
    //   95: ldc 'com.flurry.org.codehaus.jackson.map.ext.DOMDeserializer$NodeDeserializer'
    //   97: invokespecial instantiate : (Ljava/lang/String;)Ljava/lang/Object;
    //   100: checkcast com/flurry/org/codehaus/jackson/map/JsonDeserializer
    //   103: astore_1
    //   104: goto -> 34
    //   107: aconst_null
    //   108: astore_1
    //   109: goto -> 34
    //   112: aload_1
    //   113: checkcast com/flurry/org/codehaus/jackson/map/util/Provider
    //   116: invokeinterface provide : ()Ljava/util/Collection;
    //   121: astore #4
    //   123: aload #4
    //   125: invokeinterface iterator : ()Ljava/util/Iterator;
    //   130: astore_3
    //   131: aload_3
    //   132: invokeinterface hasNext : ()Z
    //   137: ifeq -> 161
    //   140: aload_3
    //   141: invokeinterface next : ()Ljava/lang/Object;
    //   146: checkcast com/flurry/org/codehaus/jackson/map/deser/std/StdDeserializer
    //   149: astore_1
    //   150: aload_2
    //   151: aload_1
    //   152: invokevirtual getValueClass : ()Ljava/lang/Class;
    //   155: if_acmpne -> 131
    //   158: goto -> 34
    //   161: aload #4
    //   163: invokeinterface iterator : ()Ljava/util/Iterator;
    //   168: astore_3
    //   169: aload_3
    //   170: invokeinterface hasNext : ()Z
    //   175: ifeq -> 202
    //   178: aload_3
    //   179: invokeinterface next : ()Ljava/lang/Object;
    //   184: checkcast com/flurry/org/codehaus/jackson/map/deser/std/StdDeserializer
    //   187: astore_1
    //   188: aload_1
    //   189: invokevirtual getValueClass : ()Ljava/lang/Class;
    //   192: aload_2
    //   193: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
    //   196: ifeq -> 169
    //   199: goto -> 34
    //   202: aconst_null
    //   203: astore_1
    //   204: goto -> 34
  }
  
  public JsonSerializer findSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType) {
    // Byte code:
    //   0: aload_2
    //   1: invokevirtual getRawClass : ()Ljava/lang/Class;
    //   4: astore_2
    //   5: aload_2
    //   6: invokevirtual getName : ()Ljava/lang/String;
    //   9: astore_1
    //   10: aload_1
    //   11: ldc 'org.joda.time.'
    //   13: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   16: ifeq -> 36
    //   19: ldc 'com.flurry.org.codehaus.jackson.map.ext.JodaSerializers'
    //   21: astore_1
    //   22: aload_0
    //   23: aload_1
    //   24: invokespecial instantiate : (Ljava/lang/String;)Ljava/lang/Object;
    //   27: astore_1
    //   28: aload_1
    //   29: ifnonnull -> 89
    //   32: aconst_null
    //   33: astore_1
    //   34: aload_1
    //   35: areturn
    //   36: aload_1
    //   37: ldc 'javax.xml.'
    //   39: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   42: ifne -> 55
    //   45: aload_0
    //   46: aload_2
    //   47: ldc 'javax.xml.'
    //   49: invokespecial hasSupertypeStartingWith : (Ljava/lang/Class;Ljava/lang/String;)Z
    //   52: ifeq -> 61
    //   55: ldc 'com.flurry.org.codehaus.jackson.map.ext.CoreXMLSerializers'
    //   57: astore_1
    //   58: goto -> 22
    //   61: aload_0
    //   62: aload_2
    //   63: ldc 'org.w3c.dom.Node'
    //   65: invokespecial doesImplement : (Ljava/lang/Class;Ljava/lang/String;)Z
    //   68: ifeq -> 84
    //   71: aload_0
    //   72: ldc 'com.flurry.org.codehaus.jackson.map.ext.DOMSerializer'
    //   74: invokespecial instantiate : (Ljava/lang/String;)Ljava/lang/Object;
    //   77: checkcast com/flurry/org/codehaus/jackson/map/JsonSerializer
    //   80: astore_1
    //   81: goto -> 34
    //   84: aconst_null
    //   85: astore_1
    //   86: goto -> 34
    //   89: aload_1
    //   90: checkcast com/flurry/org/codehaus/jackson/map/util/Provider
    //   93: invokeinterface provide : ()Ljava/util/Collection;
    //   98: astore #4
    //   100: aload #4
    //   102: invokeinterface iterator : ()Ljava/util/Iterator;
    //   107: astore_3
    //   108: aload_3
    //   109: invokeinterface hasNext : ()Z
    //   114: ifeq -> 150
    //   117: aload_3
    //   118: invokeinterface next : ()Ljava/lang/Object;
    //   123: checkcast java/util/Map$Entry
    //   126: astore_1
    //   127: aload_2
    //   128: aload_1
    //   129: invokeinterface getKey : ()Ljava/lang/Object;
    //   134: if_acmpne -> 108
    //   137: aload_1
    //   138: invokeinterface getValue : ()Ljava/lang/Object;
    //   143: checkcast com/flurry/org/codehaus/jackson/map/JsonSerializer
    //   146: astore_1
    //   147: goto -> 34
    //   150: aload #4
    //   152: invokeinterface iterator : ()Ljava/util/Iterator;
    //   157: astore_3
    //   158: aload_3
    //   159: invokeinterface hasNext : ()Z
    //   164: ifeq -> 206
    //   167: aload_3
    //   168: invokeinterface next : ()Ljava/lang/Object;
    //   173: checkcast java/util/Map$Entry
    //   176: astore_1
    //   177: aload_1
    //   178: invokeinterface getKey : ()Ljava/lang/Object;
    //   183: checkcast java/lang/Class
    //   186: aload_2
    //   187: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
    //   190: ifeq -> 158
    //   193: aload_1
    //   194: invokeinterface getValue : ()Ljava/lang/Object;
    //   199: checkcast com/flurry/org/codehaus/jackson/map/JsonSerializer
    //   202: astore_1
    //   203: goto -> 34
    //   206: aconst_null
    //   207: astore_1
    //   208: goto -> 34
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ext\OptionalHandlerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */