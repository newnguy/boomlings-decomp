package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;

public abstract class StdArraySerializers$ArraySerializerBase extends ContainerSerializerBase {
  protected final BeanProperty _property;
  
  protected final TypeSerializer _valueTypeSerializer;
  
  protected StdArraySerializers$ArraySerializerBase(Class paramClass, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty) {
    super(paramClass);
    this._valueTypeSerializer = paramTypeSerializer;
    this._property = paramBeanProperty;
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


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\StdArraySerializers$ArraySerializerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */