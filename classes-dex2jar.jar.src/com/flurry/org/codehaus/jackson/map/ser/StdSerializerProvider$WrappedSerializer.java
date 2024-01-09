package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;

final class StdSerializerProvider$WrappedSerializer extends JsonSerializer {
  protected final JsonSerializer _serializer;
  
  protected final TypeSerializer _typeSerializer;
  
  public StdSerializerProvider$WrappedSerializer(TypeSerializer paramTypeSerializer, JsonSerializer paramJsonSerializer) {
    this._typeSerializer = paramTypeSerializer;
    this._serializer = paramJsonSerializer;
  }
  
  public Class handledType() {
    return Object.class;
  }
  
  public void serialize(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    this._serializer.serializeWithType(paramObject, paramJsonGenerator, paramSerializerProvider, this._typeSerializer);
  }
  
  public void serializeWithType(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer) {
    this._serializer.serializeWithType(paramObject, paramJsonGenerator, paramSerializerProvider, paramTypeSerializer);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\StdSerializerProvider$WrappedSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */