package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.ResolvableSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.ser.impl.PropertySerializerMap;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Type;

public abstract class AsArraySerializerBase extends ContainerSerializerBase implements ResolvableSerializer {
  protected PropertySerializerMap _dynamicSerializers;
  
  protected JsonSerializer _elementSerializer;
  
  protected final JavaType _elementType;
  
  protected final BeanProperty _property;
  
  protected final boolean _staticTyping;
  
  protected final TypeSerializer _valueTypeSerializer;
  
  @Deprecated
  protected AsArraySerializerBase(Class paramClass, JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty) {
    this(paramClass, paramJavaType, paramBoolean, paramTypeSerializer, paramBeanProperty, null);
  }
  
  protected AsArraySerializerBase(Class paramClass, JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty, JsonSerializer paramJsonSerializer) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #7
    //   3: aload_0
    //   4: aload_1
    //   5: iconst_0
    //   6: invokespecial <init> : (Ljava/lang/Class;Z)V
    //   9: aload_0
    //   10: aload_2
    //   11: putfield _elementType : Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   14: iload_3
    //   15: ifne -> 35
    //   18: iload #7
    //   20: istore_3
    //   21: aload_2
    //   22: ifnull -> 37
    //   25: iload #7
    //   27: istore_3
    //   28: aload_2
    //   29: invokevirtual isFinal : ()Z
    //   32: ifeq -> 37
    //   35: iconst_1
    //   36: istore_3
    //   37: aload_0
    //   38: iload_3
    //   39: putfield _staticTyping : Z
    //   42: aload_0
    //   43: aload #4
    //   45: putfield _valueTypeSerializer : Lcom/flurry/org/codehaus/jackson/map/TypeSerializer;
    //   48: aload_0
    //   49: aload #5
    //   51: putfield _property : Lcom/flurry/org/codehaus/jackson/map/BeanProperty;
    //   54: aload_0
    //   55: aload #6
    //   57: putfield _elementSerializer : Lcom/flurry/org/codehaus/jackson/map/JsonSerializer;
    //   60: aload_0
    //   61: invokestatic emptyMap : ()Lcom/flurry/org/codehaus/jackson/map/ser/impl/PropertySerializerMap;
    //   64: putfield _dynamicSerializers : Lcom/flurry/org/codehaus/jackson/map/ser/impl/PropertySerializerMap;
    //   67: return
  }
  
  protected final JsonSerializer _findAndAddDynamic(PropertySerializerMap paramPropertySerializerMap, JavaType paramJavaType, SerializerProvider paramSerializerProvider) {
    PropertySerializerMap.SerializerAndMapResult serializerAndMapResult = paramPropertySerializerMap.findAndAddSerializer(paramJavaType, paramSerializerProvider, this._property);
    if (paramPropertySerializerMap != serializerAndMapResult.map)
      this._dynamicSerializers = serializerAndMapResult.map; 
    return serializerAndMapResult.serializer;
  }
  
  protected final JsonSerializer _findAndAddDynamic(PropertySerializerMap paramPropertySerializerMap, Class paramClass, SerializerProvider paramSerializerProvider) {
    PropertySerializerMap.SerializerAndMapResult serializerAndMapResult = paramPropertySerializerMap.findAndAddSerializer(paramClass, paramSerializerProvider, this._property);
    if (paramPropertySerializerMap != serializerAndMapResult.map)
      this._dynamicSerializers = serializerAndMapResult.map; 
    return serializerAndMapResult.serializer;
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    // Byte code:
    //   0: aload_0
    //   1: ldc 'array'
    //   3: iconst_1
    //   4: invokevirtual createSchemaNode : (Ljava/lang/String;Z)Lcom/flurry/org/codehaus/jackson/node/ObjectNode;
    //   7: astore #5
    //   9: aload_2
    //   10: ifnull -> 157
    //   13: aload_1
    //   14: aload_2
    //   15: invokevirtual constructType : (Ljava/lang/reflect/Type;)Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   18: invokevirtual getContentType : ()Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   21: astore #4
    //   23: aload #4
    //   25: astore_3
    //   26: aload #4
    //   28: ifnonnull -> 68
    //   31: aload #4
    //   33: astore_3
    //   34: aload_2
    //   35: instanceof java/lang/reflect/ParameterizedType
    //   38: ifeq -> 68
    //   41: aload_2
    //   42: checkcast java/lang/reflect/ParameterizedType
    //   45: invokeinterface getActualTypeArguments : ()[Ljava/lang/reflect/Type;
    //   50: astore_2
    //   51: aload #4
    //   53: astore_3
    //   54: aload_2
    //   55: arraylength
    //   56: iconst_1
    //   57: if_icmpne -> 68
    //   60: aload_1
    //   61: aload_2
    //   62: iconst_0
    //   63: aaload
    //   64: invokevirtual constructType : (Ljava/lang/reflect/Type;)Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   67: astore_3
    //   68: aload_3
    //   69: astore_2
    //   70: aload_3
    //   71: ifnonnull -> 88
    //   74: aload_3
    //   75: astore_2
    //   76: aload_0
    //   77: getfield _elementType : Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   80: ifnull -> 88
    //   83: aload_0
    //   84: getfield _elementType : Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   87: astore_2
    //   88: aload_2
    //   89: ifnull -> 149
    //   92: aload_2
    //   93: invokevirtual getRawClass : ()Ljava/lang/Class;
    //   96: ldc java/lang/Object
    //   98: if_acmpeq -> 152
    //   101: aload_1
    //   102: aload_2
    //   103: aload_0
    //   104: getfield _property : Lcom/flurry/org/codehaus/jackson/map/BeanProperty;
    //   107: invokevirtual findValueSerializer : (Lcom/flurry/org/codehaus/jackson/type/JavaType;Lcom/flurry/org/codehaus/jackson/map/BeanProperty;)Lcom/flurry/org/codehaus/jackson/map/JsonSerializer;
    //   110: astore_2
    //   111: aload_2
    //   112: instanceof com/flurry/org/codehaus/jackson/schema/SchemaAware
    //   115: ifeq -> 152
    //   118: aload_2
    //   119: checkcast com/flurry/org/codehaus/jackson/schema/SchemaAware
    //   122: aload_1
    //   123: aconst_null
    //   124: invokeinterface getSchema : (Lcom/flurry/org/codehaus/jackson/map/SerializerProvider;Ljava/lang/reflect/Type;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   129: astore_1
    //   130: aload_1
    //   131: astore_2
    //   132: aload_1
    //   133: ifnonnull -> 140
    //   136: invokestatic getDefaultSchemaNode : ()Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   139: astore_2
    //   140: aload #5
    //   142: ldc 'items'
    //   144: aload_2
    //   145: invokevirtual put : (Ljava/lang/String;Lcom/flurry/org/codehaus/jackson/JsonNode;)Lcom/flurry/org/codehaus/jackson/JsonNode;
    //   148: pop
    //   149: aload #5
    //   151: areturn
    //   152: aconst_null
    //   153: astore_1
    //   154: goto -> 130
    //   157: aconst_null
    //   158: astore_3
    //   159: goto -> 68
  }
  
  public void resolve(SerializerProvider paramSerializerProvider) {
    if (this._staticTyping && this._elementType != null && this._elementSerializer == null)
      this._elementSerializer = paramSerializerProvider.findValueSerializer(this._elementType, this._property); 
  }
  
  public final void serialize(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeStartArray();
    serializeContents(paramObject, paramJsonGenerator, paramSerializerProvider);
    paramJsonGenerator.writeEndArray();
  }
  
  protected abstract void serializeContents(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider);
  
  public final void serializeWithType(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer) {
    paramTypeSerializer.writeTypePrefixForArray(paramObject, paramJsonGenerator);
    serializeContents(paramObject, paramJsonGenerator, paramSerializerProvider);
    paramTypeSerializer.writeTypeSuffixForArray(paramObject, paramJsonGenerator);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\AsArraySerializerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */