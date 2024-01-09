package com.flurry.org.codehaus.jackson.map.ser.impl;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.ser.BeanPropertyWriter;

public class UnwrappingBeanPropertyWriter extends BeanPropertyWriter {
  public UnwrappingBeanPropertyWriter(BeanPropertyWriter paramBeanPropertyWriter) {
    super(paramBeanPropertyWriter);
  }
  
  public UnwrappingBeanPropertyWriter(BeanPropertyWriter paramBeanPropertyWriter, JsonSerializer paramJsonSerializer) {
    super(paramBeanPropertyWriter, paramJsonSerializer);
  }
  
  protected JsonSerializer _findAndAddDynamic(PropertySerializerMap paramPropertySerializerMap, Class paramClass, SerializerProvider paramSerializerProvider) {
    JsonSerializer jsonSerializer1;
    if (this._nonTrivialBaseType != null) {
      jsonSerializer1 = paramSerializerProvider.findValueSerializer(paramSerializerProvider.constructSpecializedType(this._nonTrivialBaseType, paramClass), (BeanProperty)this);
    } else {
      jsonSerializer1 = paramSerializerProvider.findValueSerializer(paramClass, (BeanProperty)this);
    } 
    JsonSerializer jsonSerializer2 = jsonSerializer1;
    if (!jsonSerializer1.isUnwrappingSerializer())
      jsonSerializer2 = jsonSerializer1.unwrappingSerializer(); 
    this._dynamicSerializers = this._dynamicSerializers.newWith(paramClass, jsonSerializer2);
    return jsonSerializer2;
  }
  
  public void serializeAsField(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    Object object = get(paramObject);
    if (object != null) {
      if (object == paramObject)
        _reportSelfReference(paramObject); 
      if (this._suppressableValue == null || !this._suppressableValue.equals(object)) {
        JsonSerializer jsonSerializer = this._serializer;
        paramObject = jsonSerializer;
        if (jsonSerializer == null) {
          Class<?> clazz = object.getClass();
          PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
          jsonSerializer = propertySerializerMap.serializerFor(clazz);
          paramObject = jsonSerializer;
          if (jsonSerializer == null)
            paramObject = _findAndAddDynamic(propertySerializerMap, clazz, paramSerializerProvider); 
        } 
        if (!paramObject.isUnwrappingSerializer())
          paramJsonGenerator.writeFieldName(this._name); 
        if (this._typeSerializer == null) {
          paramObject.serialize(object, paramJsonGenerator, paramSerializerProvider);
          return;
        } 
        paramObject.serializeWithType(object, paramJsonGenerator, paramSerializerProvider, this._typeSerializer);
      } 
    } 
  }
  
  public BeanPropertyWriter withSerializer(JsonSerializer paramJsonSerializer) {
    if (getClass() != UnwrappingBeanPropertyWriter.class)
      throw new IllegalStateException("UnwrappingBeanPropertyWriter sub-class does not override 'withSerializer()'; needs to!"); 
    JsonSerializer jsonSerializer = paramJsonSerializer;
    if (!paramJsonSerializer.isUnwrappingSerializer())
      jsonSerializer = paramJsonSerializer.unwrappingSerializer(); 
    return new UnwrappingBeanPropertyWriter(this, jsonSerializer);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\impl\UnwrappingBeanPropertyWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */