package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.JsonGenerator;

public interface JsonSerializableWithType extends JsonSerializable {
  void serializeWithType(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\JsonSerializableWithType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */