package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.JsonSerializable;
import com.flurry.org.codehaus.jackson.map.JsonSerializableWithType;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.ser.SerializerBase;
import java.lang.reflect.Type;

@JacksonStdImpl
public class SerializableSerializer extends SerializerBase {
  public static final SerializableSerializer instance = new SerializableSerializer();
  
  protected SerializableSerializer() {
    super(JsonSerializable.class);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_0
    //   3: invokevirtual createObjectNode : ()Lcom/flurry/org/codehaus/jackson/node/ObjectNode;
    //   6: astore #5
    //   8: ldc 'any'
    //   10: astore_1
    //   11: aload_2
    //   12: ifnull -> 196
    //   15: aload_2
    //   16: invokestatic type : (Ljava/lang/reflect/Type;)Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   19: invokevirtual getRawClass : ()Ljava/lang/Class;
    //   22: astore_2
    //   23: aload_2
    //   24: ldc com/flurry/org/codehaus/jackson/schema/JsonSerializableSchema
    //   26: invokevirtual isAnnotationPresent : (Ljava/lang/Class;)Z
    //   29: ifeq -> 196
    //   32: aload_2
    //   33: ldc com/flurry/org/codehaus/jackson/schema/JsonSerializableSchema
    //   35: invokevirtual getAnnotation : (Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
    //   38: checkcast com/flurry/org/codehaus/jackson/schema/JsonSerializableSchema
    //   41: astore #4
    //   43: aload #4
    //   45: invokeinterface schemaType : ()Ljava/lang/String;
    //   50: astore_2
    //   51: ldc '##irrelevant'
    //   53: aload #4
    //   55: invokeinterface schemaObjectPropertiesDefinition : ()Ljava/lang/String;
    //   60: invokevirtual equals : (Ljava/lang/Object;)Z
    //   63: ifne -> 191
    //   66: aload #4
    //   68: invokeinterface schemaObjectPropertiesDefinition : ()Ljava/lang/String;
    //   73: astore_1
    //   74: ldc '##irrelevant'
    //   76: aload #4
    //   78: invokeinterface schemaItemDefinition : ()Ljava/lang/String;
    //   83: invokevirtual equals : (Ljava/lang/Object;)Z
    //   86: ifne -> 188
    //   89: aload #4
    //   91: invokeinterface schemaItemDefinition : ()Ljava/lang/String;
    //   96: astore_3
    //   97: aload #5
    //   99: ldc 'type'
    //   101: aload_2
    //   102: invokevirtual put : (Ljava/lang/String;Ljava/lang/String;)V
    //   105: aload_1
    //   106: ifnull -> 135
    //   109: new com/flurry/org/codehaus/jackson/map/ObjectMapper
    //   112: astore_2
    //   113: aload_2
    //   114: invokespecial <init> : ()V
    //   117: aload #5
    //   119: ldc 'properties'
    //   121: aload_2
    //   122: aload_1
    //   123: ldc com/flurry/org/codehaus/jackson/JsonNode
    //   125: invokevirtual readValue : (Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   128: checkcast com/flurry/org/codehaus/jackson/JsonNode
    //   131: invokevirtual put : (Ljava/lang/String;Lcom/flurry/org/codehaus/jackson/JsonNode;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   134: pop
    //   135: aload_3
    //   136: ifnull -> 165
    //   139: new com/flurry/org/codehaus/jackson/map/ObjectMapper
    //   142: astore_1
    //   143: aload_1
    //   144: invokespecial <init> : ()V
    //   147: aload #5
    //   149: ldc 'items'
    //   151: aload_1
    //   152: aload_3
    //   153: ldc com/flurry/org/codehaus/jackson/JsonNode
    //   155: invokevirtual readValue : (Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   158: checkcast com/flurry/org/codehaus/jackson/JsonNode
    //   161: invokevirtual put : (Ljava/lang/String;Lcom/flurry/org/codehaus/jackson/JsonNode;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   164: pop
    //   165: aload #5
    //   167: areturn
    //   168: astore_1
    //   169: new java/lang/IllegalStateException
    //   172: dup
    //   173: aload_1
    //   174: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   177: athrow
    //   178: astore_1
    //   179: new java/lang/IllegalStateException
    //   182: dup
    //   183: aload_1
    //   184: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   187: athrow
    //   188: goto -> 97
    //   191: aconst_null
    //   192: astore_1
    //   193: goto -> 74
    //   196: aconst_null
    //   197: astore #4
    //   199: aload_1
    //   200: astore_2
    //   201: aload #4
    //   203: astore_1
    //   204: goto -> 97
    // Exception table:
    //   from	to	target	type
    //   109	135	168	java/io/IOException
    //   139	165	178	java/io/IOException
  }
  
  public void serialize(JsonSerializable paramJsonSerializable, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonSerializable.serialize(paramJsonGenerator, paramSerializerProvider);
  }
  
  public final void serializeWithType(JsonSerializable paramJsonSerializable, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer) {
    if (paramJsonSerializable instanceof JsonSerializableWithType) {
      ((JsonSerializableWithType)paramJsonSerializable).serializeWithType(paramJsonGenerator, paramSerializerProvider, paramTypeSerializer);
      return;
    } 
    serialize(paramJsonSerializable, paramJsonGenerator, paramSerializerProvider);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\SerializableSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */