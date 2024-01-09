package com.flurry.org.codehaus.jackson.map.ser.impl;

import com.flurry.org.codehaus.jackson.map.JsonSerializer;

final class PropertySerializerMap$Single extends PropertySerializerMap {
  private final JsonSerializer _serializer;
  
  private final Class _type;
  
  public PropertySerializerMap$Single(Class paramClass, JsonSerializer paramJsonSerializer) {
    this._type = paramClass;
    this._serializer = paramJsonSerializer;
  }
  
  public PropertySerializerMap newWith(Class paramClass, JsonSerializer paramJsonSerializer) {
    return new PropertySerializerMap$Double(this._type, this._serializer, paramClass, paramJsonSerializer);
  }
  
  public JsonSerializer serializerFor(Class paramClass) {
    return (paramClass == this._type) ? this._serializer : null;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\impl\PropertySerializerMap$Single.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */