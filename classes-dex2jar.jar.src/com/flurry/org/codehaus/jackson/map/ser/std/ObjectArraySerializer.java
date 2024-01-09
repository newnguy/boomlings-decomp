package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.ResolvableSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.ser.impl.PropertySerializerMap;
import com.flurry.org.codehaus.jackson.map.type.ArrayType;
import com.flurry.org.codehaus.jackson.node.ObjectNode;
import com.flurry.org.codehaus.jackson.schema.JsonSchema;
import com.flurry.org.codehaus.jackson.schema.SchemaAware;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.io.IOException;
import java.lang.reflect.Type;

@JacksonStdImpl
public class ObjectArraySerializer extends StdArraySerializers$ArraySerializerBase implements ResolvableSerializer {
  protected PropertySerializerMap _dynamicSerializers;
  
  protected JsonSerializer _elementSerializer;
  
  protected final JavaType _elementType;
  
  protected final boolean _staticTyping;
  
  @Deprecated
  public ObjectArraySerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty) {
    this(paramJavaType, paramBoolean, paramTypeSerializer, paramBeanProperty, (JsonSerializer)null);
  }
  
  public ObjectArraySerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty, JsonSerializer paramJsonSerializer) {
    super(Object[].class, paramTypeSerializer, paramBeanProperty);
    this._elementType = paramJavaType;
    this._staticTyping = paramBoolean;
    this._dynamicSerializers = PropertySerializerMap.emptyMap();
    this._elementSerializer = paramJsonSerializer;
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
  
  public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer paramTypeSerializer) {
    return new ObjectArraySerializer(this._elementType, this._staticTyping, paramTypeSerializer, this._property, this._elementSerializer);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    JsonNode jsonNode;
    Class<Object> clazz;
    ObjectNode objectNode = createSchemaNode("array", true);
    if (paramType != null) {
      JavaType javaType = paramSerializerProvider.constructType(paramType);
      if (javaType.isArrayType()) {
        clazz = ((ArrayType)javaType).getContentType().getRawClass();
        if (clazz == Object.class) {
          objectNode.put("items", JsonSchema.getDefaultSchemaNode());
          return (JsonNode)objectNode;
        } 
      } else {
        return (JsonNode)objectNode;
      } 
    } else {
      return (JsonNode)objectNode;
    } 
    JsonSerializer jsonSerializer = paramSerializerProvider.findValueSerializer(clazz, this._property);
    if (jsonSerializer instanceof SchemaAware) {
      jsonNode = ((SchemaAware)jsonSerializer).getSchema(paramSerializerProvider, null);
    } else {
      jsonNode = JsonSchema.getDefaultSchemaNode();
    } 
    objectNode.put("items", jsonNode);
    return (JsonNode)objectNode;
  }
  
  public void resolve(SerializerProvider paramSerializerProvider) {
    if (this._staticTyping && this._elementSerializer == null)
      this._elementSerializer = paramSerializerProvider.findValueSerializer(this._elementType, this._property); 
  }
  
  public void serializeContents(Object[] paramArrayOfObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    int i = paramArrayOfObject.length;
    if (i != 0) {
      if (this._elementSerializer != null) {
        serializeContentsUsing(paramArrayOfObject, paramJsonGenerator, paramSerializerProvider, this._elementSerializer);
        return;
      } 
      if (this._valueTypeSerializer != null) {
        serializeTypedContents(paramArrayOfObject, paramJsonGenerator, paramSerializerProvider);
        return;
      } 
      byte b2 = 0;
      byte b1 = 0;
      Object object1 = null;
      Object object2 = null;
      try {
        PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
        while (true) {
          if (b1 < i) {
            object2 = paramArrayOfObject[b1];
            if (object2 == null) {
              object1 = object2;
              b2 = b1;
              paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
            } else {
              object1 = object2;
              b2 = b1;
              Class<?> clazz = object2.getClass();
              object1 = object2;
              b2 = b1;
              JsonSerializer jsonSerializer2 = propertySerializerMap.serializerFor(clazz);
              JsonSerializer jsonSerializer1 = jsonSerializer2;
              if (jsonSerializer2 == null) {
                object1 = object2;
                b2 = b1;
                if (this._elementType.hasGenericTypes()) {
                  object1 = object2;
                  b2 = b1;
                  jsonSerializer1 = _findAndAddDynamic(propertySerializerMap, paramSerializerProvider.constructSpecializedType(this._elementType, clazz), paramSerializerProvider);
                } else {
                  object1 = object2;
                  b2 = b1;
                  jsonSerializer1 = _findAndAddDynamic(propertySerializerMap, clazz, paramSerializerProvider);
                } 
              } 
              object1 = object2;
              b2 = b1;
              jsonSerializer1.serialize(object2, paramJsonGenerator, paramSerializerProvider);
            } 
            b1++;
            continue;
          } 
          return;
        } 
      } catch (IOException iOException) {
        throw iOException;
      } catch (Exception null) {
        Throwable throwable;
        while (throwable instanceof java.lang.reflect.InvocationTargetException && throwable.getCause() != null)
          throwable = throwable.getCause(); 
        if (throwable instanceof Error)
          throw (Error)throwable; 
        throw JsonMappingException.wrapWithPath(throwable, object1, b2);
      } 
    } 
  }
  
  public void serializeContentsUsing(Object[] paramArrayOfObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, JsonSerializer paramJsonSerializer) {
    int i = paramArrayOfObject.length;
    TypeSerializer typeSerializer = this._valueTypeSerializer;
    Object object = null;
    byte b;
    for (b = 0;; b++) {
      if (b < i) {
        object = paramArrayOfObject[b];
        if (object == null) {
          Object object1 = object;
          try {
            paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
            b++;
          } catch (IOException iOException) {
            throw iOException;
          } catch (Exception null) {
            Throwable throwable;
            while (throwable instanceof java.lang.reflect.InvocationTargetException && throwable.getCause() != null)
              throwable = throwable.getCause(); 
            if (throwable instanceof Error)
              throw (Error)throwable; 
            throw JsonMappingException.wrapWithPath(throwable, object1, b);
          } 
          continue;
        } 
        if (typeSerializer == null) {
          Object object1 = object;
          paramJsonSerializer.serialize(object, paramJsonGenerator, paramSerializerProvider);
        } else {
          Object object1 = object;
          paramJsonSerializer.serializeWithType(object, paramJsonGenerator, paramSerializerProvider, typeSerializer);
        } 
      } else {
        break;
      } 
    } 
  }
  
  public void serializeTypedContents(Object[] paramArrayOfObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    int i = paramArrayOfObject.length;
    TypeSerializer typeSerializer = this._valueTypeSerializer;
    byte b2 = 0;
    byte b1 = 0;
    Object object1 = null;
    Object object2 = null;
    try {
      PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
      while (b1 < i) {
        object2 = paramArrayOfObject[b1];
        if (object2 == null) {
          object1 = object2;
          b2 = b1;
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        } else {
          object1 = object2;
          b2 = b1;
          Class<?> clazz = object2.getClass();
          object1 = object2;
          b2 = b1;
          JsonSerializer jsonSerializer2 = propertySerializerMap.serializerFor(clazz);
          JsonSerializer jsonSerializer1 = jsonSerializer2;
          if (jsonSerializer2 == null) {
            object1 = object2;
            b2 = b1;
            jsonSerializer1 = _findAndAddDynamic(propertySerializerMap, clazz, paramSerializerProvider);
          } 
          object1 = object2;
          b2 = b1;
          jsonSerializer1.serializeWithType(object2, paramJsonGenerator, paramSerializerProvider, typeSerializer);
        } 
        b1++;
      } 
      return;
    } catch (IOException iOException) {
      throw iOException;
    } catch (Exception null) {
      Throwable throwable;
      while (throwable instanceof java.lang.reflect.InvocationTargetException && throwable.getCause() != null)
        throwable = throwable.getCause(); 
      if (throwable instanceof Error)
        throw (Error)throwable; 
      throw JsonMappingException.wrapWithPath(throwable, object1, b2);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\ObjectArraySerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */