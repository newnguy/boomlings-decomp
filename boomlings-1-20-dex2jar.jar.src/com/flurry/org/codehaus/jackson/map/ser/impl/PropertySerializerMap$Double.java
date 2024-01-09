package com.flurry.org.codehaus.jackson.map.ser.impl;

import com.flurry.org.codehaus.jackson.map.JsonSerializer;

final class PropertySerializerMap$Double extends PropertySerializerMap {
  private final JsonSerializer _serializer1;
  
  private final JsonSerializer _serializer2;
  
  private final Class _type1;
  
  private final Class _type2;
  
  public PropertySerializerMap$Double(Class paramClass1, JsonSerializer paramJsonSerializer1, Class paramClass2, JsonSerializer paramJsonSerializer2) {
    this._type1 = paramClass1;
    this._serializer1 = paramJsonSerializer1;
    this._type2 = paramClass2;
    this._serializer2 = paramJsonSerializer2;
  }
  
  public PropertySerializerMap newWith(Class paramClass, JsonSerializer paramJsonSerializer) {
    return new PropertySerializerMap$Multi(new PropertySerializerMap$TypeAndSerializer[] { new PropertySerializerMap$TypeAndSerializer(this._type1, this._serializer1), new PropertySerializerMap$TypeAndSerializer(this._type2, this._serializer2) });
  }
  
  public JsonSerializer serializerFor(Class paramClass) {
    return (paramClass == this._type1) ? this._serializer1 : ((paramClass == this._type2) ? this._serializer2 : null);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\impl\PropertySerializerMap$Double.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */