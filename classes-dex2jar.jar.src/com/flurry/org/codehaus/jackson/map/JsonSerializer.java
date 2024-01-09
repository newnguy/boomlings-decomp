package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.JsonGenerator;

public abstract class JsonSerializer {
  public Class handledType() {
    return null;
  }
  
  public boolean isUnwrappingSerializer() {
    return false;
  }
  
  public abstract void serialize(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider);
  
  public void serializeWithType(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer) {
    serialize(paramObject, paramJsonGenerator, paramSerializerProvider);
  }
  
  public JsonSerializer unwrappingSerializer() {
    return this;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\JsonSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */