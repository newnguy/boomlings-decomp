package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.ResolvableSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.util.EnumValues;
import com.flurry.org.codehaus.jackson.node.JsonNodeFactory;
import com.flurry.org.codehaus.jackson.node.ObjectNode;
import com.flurry.org.codehaus.jackson.schema.JsonSchema;
import com.flurry.org.codehaus.jackson.schema.SchemaAware;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;

@JacksonStdImpl
public class EnumMapSerializer extends ContainerSerializerBase implements ResolvableSerializer {
  protected final EnumValues _keyEnums;
  
  protected final BeanProperty _property;
  
  protected final boolean _staticTyping;
  
  protected JsonSerializer _valueSerializer;
  
  protected final JavaType _valueType;
  
  protected final TypeSerializer _valueTypeSerializer;
  
  @Deprecated
  public EnumMapSerializer(JavaType paramJavaType, boolean paramBoolean, EnumValues paramEnumValues, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty) {
    this(paramJavaType, paramBoolean, paramEnumValues, paramTypeSerializer, paramBeanProperty, (JsonSerializer)null);
  }
  
  public EnumMapSerializer(JavaType paramJavaType, boolean paramBoolean, EnumValues paramEnumValues, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty, JsonSerializer paramJsonSerializer) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #7
    //   3: aload_0
    //   4: ldc java/util/EnumMap
    //   6: iconst_0
    //   7: invokespecial <init> : (Ljava/lang/Class;Z)V
    //   10: iload_2
    //   11: ifne -> 31
    //   14: iload #7
    //   16: istore_2
    //   17: aload_1
    //   18: ifnull -> 33
    //   21: iload #7
    //   23: istore_2
    //   24: aload_1
    //   25: invokevirtual isFinal : ()Z
    //   28: ifeq -> 33
    //   31: iconst_1
    //   32: istore_2
    //   33: aload_0
    //   34: iload_2
    //   35: putfield _staticTyping : Z
    //   38: aload_0
    //   39: aload_1
    //   40: putfield _valueType : Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   43: aload_0
    //   44: aload_3
    //   45: putfield _keyEnums : Lcom/flurry/org/codehaus/jackson/map/util/EnumValues;
    //   48: aload_0
    //   49: aload #4
    //   51: putfield _valueTypeSerializer : Lcom/flurry/org/codehaus/jackson/map/TypeSerializer;
    //   54: aload_0
    //   55: aload #5
    //   57: putfield _property : Lcom/flurry/org/codehaus/jackson/map/BeanProperty;
    //   60: aload_0
    //   61: aload #6
    //   63: putfield _valueSerializer : Lcom/flurry/org/codehaus/jackson/map/JsonSerializer;
    //   66: return
  }
  
  public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer paramTypeSerializer) {
    return new EnumMapSerializer(this._valueType, this._staticTyping, this._keyEnums, paramTypeSerializer, this._property, this._valueSerializer);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    ObjectNode objectNode = createSchemaNode("object", true);
    if (paramType instanceof ParameterizedType) {
      Type[] arrayOfType = ((ParameterizedType)paramType).getActualTypeArguments();
      if (arrayOfType.length == 2) {
        JavaType javaType1 = paramSerializerProvider.constructType(arrayOfType[0]);
        JavaType javaType2 = paramSerializerProvider.constructType(arrayOfType[1]);
        ObjectNode objectNode1 = JsonNodeFactory.instance.objectNode();
        for (Enum enum_ : (Enum[])javaType1.getRawClass().getEnumConstants()) {
          JsonNode jsonNode;
          JsonSerializer jsonSerializer = paramSerializerProvider.findValueSerializer(javaType2.getRawClass(), this._property);
          if (jsonSerializer instanceof SchemaAware) {
            jsonNode = ((SchemaAware)jsonSerializer).getSchema(paramSerializerProvider, null);
          } else {
            jsonNode = JsonSchema.getDefaultSchemaNode();
          } 
          objectNode1.put(paramSerializerProvider.getConfig().getAnnotationIntrospector().findEnumValue(enum_), jsonNode);
        } 
        objectNode.put("properties", (JsonNode)objectNode1);
      } 
    } 
    return (JsonNode)objectNode;
  }
  
  public void resolve(SerializerProvider paramSerializerProvider) {
    if (this._staticTyping && this._valueSerializer == null)
      this._valueSerializer = paramSerializerProvider.findValueSerializer(this._valueType, this._property); 
  }
  
  public void serialize(EnumMap paramEnumMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeStartObject();
    if (!paramEnumMap.isEmpty())
      serializeContents(paramEnumMap, paramJsonGenerator, paramSerializerProvider); 
    paramJsonGenerator.writeEndObject();
  }
  
  protected void serializeContents(EnumMap paramEnumMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    Class<?> clazz2 = null;
    if (this._valueSerializer != null) {
      serializeContentsUsing(paramEnumMap, paramJsonGenerator, paramSerializerProvider, this._valueSerializer);
      return;
    } 
    EnumValues enumValues = this._keyEnums;
    Iterator<Map.Entry> iterator = paramEnumMap.entrySet().iterator();
    Class<?> clazz1 = null;
    while (true) {
      if (iterator.hasNext()) {
        JsonSerializer jsonSerializer;
        Map.Entry entry = iterator.next();
        Enum enum_ = (Enum)entry.getKey();
        if (enumValues == null)
          enumValues = ((EnumSerializer)paramSerializerProvider.findValueSerializer(enum_.getDeclaringClass(), this._property)).getEnumValues(); 
        paramJsonGenerator.writeFieldName(enumValues.serializedValueFor(enum_));
        Object object = entry.getValue();
        if (object == null) {
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
          continue;
        } 
        Class<?> clazz = object.getClass();
        if (clazz == clazz2) {
          clazz = clazz1;
          Class<?> clazz3 = clazz1;
          clazz1 = clazz;
        } else {
          jsonSerializer = paramSerializerProvider.findValueSerializer(clazz, this._property);
          JsonSerializer jsonSerializer1 = jsonSerializer;
          clazz2 = clazz;
        } 
        try {
          jsonSerializer.serialize(object, paramJsonGenerator, paramSerializerProvider);
        } catch (Exception exception) {
          wrapAndThrow(paramSerializerProvider, exception, paramEnumMap, ((Enum)entry.getKey()).name());
        } 
        continue;
      } 
      return;
    } 
  }
  
  protected void serializeContentsUsing(EnumMap paramEnumMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, JsonSerializer paramJsonSerializer) {
    EnumValues enumValues = this._keyEnums;
    for (Map.Entry entry : paramEnumMap.entrySet()) {
      Enum enum_ = (Enum)entry.getKey();
      EnumValues enumValues1 = enumValues;
      if (enumValues == null)
        enumValues1 = ((EnumSerializer)paramSerializerProvider.findValueSerializer(enum_.getDeclaringClass(), this._property)).getEnumValues(); 
      paramJsonGenerator.writeFieldName(enumValues1.serializedValueFor(enum_));
      enumValues = (EnumValues)entry.getValue();
      if (enumValues == null) {
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        enumValues = enumValues1;
        continue;
      } 
      try {
        paramJsonSerializer.serialize(enumValues, paramJsonGenerator, paramSerializerProvider);
        enumValues = enumValues1;
      } catch (Exception exception) {
        wrapAndThrow(paramSerializerProvider, exception, paramEnumMap, ((Enum)entry.getKey()).name());
        EnumValues enumValues2 = enumValues1;
      } 
    } 
  }
  
  public void serializeWithType(EnumMap paramEnumMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer) {
    paramTypeSerializer.writeTypePrefixForObject(paramEnumMap, paramJsonGenerator);
    if (!paramEnumMap.isEmpty())
      serializeContents(paramEnumMap, paramJsonGenerator, paramSerializerProvider); 
    paramTypeSerializer.writeTypeSuffixForObject(paramEnumMap, paramJsonGenerator);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\EnumMapSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */