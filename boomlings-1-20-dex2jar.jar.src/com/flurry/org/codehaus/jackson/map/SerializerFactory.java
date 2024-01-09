package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.map.ser.BeanSerializerModifier;
import com.flurry.org.codehaus.jackson.type.JavaType;

public abstract class SerializerFactory {
  public abstract JsonSerializer createKeySerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, BeanProperty paramBeanProperty);
  
  public abstract JsonSerializer createSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, BeanProperty paramBeanProperty);
  
  @Deprecated
  public final JsonSerializer createSerializer(JavaType paramJavaType, SerializationConfig paramSerializationConfig) {
    try {
      return createSerializer(paramSerializationConfig, paramJavaType, null);
    } catch (JsonMappingException jsonMappingException) {
      throw new RuntimeJsonMappingException(jsonMappingException);
    } 
  }
  
  public abstract TypeSerializer createTypeSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, BeanProperty paramBeanProperty);
  
  @Deprecated
  public final TypeSerializer createTypeSerializer(JavaType paramJavaType, SerializationConfig paramSerializationConfig) {
    try {
      return createTypeSerializer(paramSerializationConfig, paramJavaType, null);
    } catch (JsonMappingException jsonMappingException) {
      throw new RuntimeException(jsonMappingException);
    } 
  }
  
  public abstract SerializerFactory$Config getConfig();
  
  public final SerializerFactory withAdditionalKeySerializers(Serializers paramSerializers) {
    return withConfig(getConfig().withAdditionalKeySerializers(paramSerializers));
  }
  
  public final SerializerFactory withAdditionalSerializers(Serializers paramSerializers) {
    return withConfig(getConfig().withAdditionalSerializers(paramSerializers));
  }
  
  public abstract SerializerFactory withConfig(SerializerFactory$Config paramSerializerFactory$Config);
  
  public final SerializerFactory withSerializerModifier(BeanSerializerModifier paramBeanSerializerModifier) {
    return withConfig(getConfig().withSerializerModifier(paramBeanSerializerModifier));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\SerializerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */