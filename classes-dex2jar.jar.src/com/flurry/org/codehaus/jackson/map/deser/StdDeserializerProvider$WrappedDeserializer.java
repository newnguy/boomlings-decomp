package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;

public final class StdDeserializerProvider$WrappedDeserializer extends JsonDeserializer {
  final JsonDeserializer _deserializer;
  
  final TypeDeserializer _typeDeserializer;
  
  public StdDeserializerProvider$WrappedDeserializer(TypeDeserializer paramTypeDeserializer, JsonDeserializer paramJsonDeserializer) {
    this._typeDeserializer = paramTypeDeserializer;
    this._deserializer = paramJsonDeserializer;
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    return this._deserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, this._typeDeserializer);
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer) {
    throw new IllegalStateException("Type-wrapped deserializer's deserializeWithType should never get called");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\StdDeserializerProvider$WrappedDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */