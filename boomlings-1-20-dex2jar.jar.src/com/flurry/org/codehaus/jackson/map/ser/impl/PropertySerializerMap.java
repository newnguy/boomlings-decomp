package com.flurry.org.codehaus.jackson.map.ser.impl;

import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.type.JavaType;

public abstract class PropertySerializerMap {
  public static PropertySerializerMap emptyMap() {
    return PropertySerializerMap$Empty.instance;
  }
  
  public final PropertySerializerMap$SerializerAndMapResult findAndAddSerializer(JavaType paramJavaType, SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty) {
    JsonSerializer jsonSerializer = paramSerializerProvider.findValueSerializer(paramJavaType, paramBeanProperty);
    return new PropertySerializerMap$SerializerAndMapResult(jsonSerializer, newWith(paramJavaType.getRawClass(), jsonSerializer));
  }
  
  public final PropertySerializerMap$SerializerAndMapResult findAndAddSerializer(Class paramClass, SerializerProvider paramSerializerProvider, BeanProperty paramBeanProperty) {
    JsonSerializer jsonSerializer = paramSerializerProvider.findValueSerializer(paramClass, paramBeanProperty);
    return new PropertySerializerMap$SerializerAndMapResult(jsonSerializer, newWith(paramClass, jsonSerializer));
  }
  
  public abstract PropertySerializerMap newWith(Class paramClass, JsonSerializer paramJsonSerializer);
  
  public abstract JsonSerializer serializerFor(Class paramClass);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\impl\PropertySerializerMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */