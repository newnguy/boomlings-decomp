package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.JsonParser;

public abstract class JsonDeserializer {
  public abstract Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext);
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject) {
    throw new UnsupportedOperationException();
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer) {
    return paramTypeDeserializer.deserializeTypedFromAny(paramJsonParser, paramDeserializationContext);
  }
  
  public Object getEmptyValue() {
    return getNullValue();
  }
  
  public Object getNullValue() {
    return null;
  }
  
  public JsonDeserializer unwrappingDeserializer() {
    return this;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\JsonDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */