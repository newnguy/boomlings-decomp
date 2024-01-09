package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.JsonSerializableWithType;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import java.lang.reflect.Type;

@JacksonStdImpl
public class SerializableWithTypeSerializer extends SerializerBase {
  public static final SerializableWithTypeSerializer instance = new SerializableWithTypeSerializer();
  
  protected SerializableWithTypeSerializer() {
    super(JsonSerializableWithType.class);
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
    //   12: ifnull -> 193
    //   15: aload_2
    //   16: invokestatic rawClass : (Ljava/lang/reflect/Type;)Ljava/lang/Class;
    //   19: astore_2
    //   20: aload_2
    //   21: ldc com/flurry/org/codehaus/jackson/schema/JsonSerializableSchema
    //   23: invokevirtual isAnnotationPresent : (Ljava/lang/Class;)Z
    //   26: ifeq -> 193
    //   29: aload_2
    //   30: ldc com/flurry/org/codehaus/jackson/schema/JsonSerializableSchema
    //   32: invokevirtual getAnnotation : (Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
    //   35: checkcast com/flurry/org/codehaus/jackson/schema/JsonSerializableSchema
    //   38: astore #4
    //   40: aload #4
    //   42: invokeinterface schemaType : ()Ljava/lang/String;
    //   47: astore_2
    //   48: ldc '##irrelevant'
    //   50: aload #4
    //   52: invokeinterface schemaObjectPropertiesDefinition : ()Ljava/lang/String;
    //   57: invokevirtual equals : (Ljava/lang/Object;)Z
    //   60: ifne -> 188
    //   63: aload #4
    //   65: invokeinterface schemaObjectPropertiesDefinition : ()Ljava/lang/String;
    //   70: astore_1
    //   71: ldc '##irrelevant'
    //   73: aload #4
    //   75: invokeinterface schemaItemDefinition : ()Ljava/lang/String;
    //   80: invokevirtual equals : (Ljava/lang/Object;)Z
    //   83: ifne -> 185
    //   86: aload #4
    //   88: invokeinterface schemaItemDefinition : ()Ljava/lang/String;
    //   93: astore_3
    //   94: aload #5
    //   96: ldc 'type'
    //   98: aload_2
    //   99: invokevirtual put : (Ljava/lang/String;Ljava/lang/String;)V
    //   102: aload_1
    //   103: ifnull -> 132
    //   106: new com/flurry/org/codehaus/jackson/map/ObjectMapper
    //   109: astore_2
    //   110: aload_2
    //   111: invokespecial <init> : ()V
    //   114: aload #5
    //   116: ldc 'properties'
    //   118: aload_2
    //   119: aload_1
    //   120: ldc com/flurry/org/codehaus/jackson/JsonNode
    //   122: invokevirtual readValue : (Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   125: checkcast com/flurry/org/codehaus/jackson/JsonNode
    //   128: invokevirtual put : (Ljava/lang/String;Lcom/flurry/org/codehaus/jackson/JsonNode;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   131: pop
    //   132: aload_3
    //   133: ifnull -> 162
    //   136: new com/flurry/org/codehaus/jackson/map/ObjectMapper
    //   139: astore_1
    //   140: aload_1
    //   141: invokespecial <init> : ()V
    //   144: aload #5
    //   146: ldc 'items'
    //   148: aload_1
    //   149: aload_3
    //   150: ldc com/flurry/org/codehaus/jackson/JsonNode
    //   152: invokevirtual readValue : (Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   155: checkcast com/flurry/org/codehaus/jackson/JsonNode
    //   158: invokevirtual put : (Ljava/lang/String;Lcom/flurry/org/codehaus/jackson/JsonNode;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   161: pop
    //   162: aload #5
    //   164: areturn
    //   165: astore_1
    //   166: new java/lang/IllegalStateException
    //   169: dup
    //   170: aload_1
    //   171: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   174: athrow
    //   175: astore_1
    //   176: new java/lang/IllegalStateException
    //   179: dup
    //   180: aload_1
    //   181: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   184: athrow
    //   185: goto -> 94
    //   188: aconst_null
    //   189: astore_1
    //   190: goto -> 71
    //   193: aconst_null
    //   194: astore #4
    //   196: aload_1
    //   197: astore_2
    //   198: aload #4
    //   200: astore_1
    //   201: goto -> 94
    // Exception table:
    //   from	to	target	type
    //   106	132	165	java/io/IOException
    //   136	162	175	java/io/IOException
  }
  
  public void serialize(JsonSerializableWithType paramJsonSerializableWithType, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonSerializableWithType.serialize(paramJsonGenerator, paramSerializerProvider);
  }
  
  public final void serializeWithType(JsonSerializableWithType paramJsonSerializableWithType, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer) {
    paramJsonSerializableWithType.serializeWithType(paramJsonGenerator, paramSerializerProvider, paramTypeSerializer);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\SerializableWithTypeSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */