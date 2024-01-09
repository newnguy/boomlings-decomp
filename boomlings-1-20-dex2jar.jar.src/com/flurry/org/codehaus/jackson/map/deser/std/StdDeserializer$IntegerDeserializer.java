package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;

@JacksonStdImpl
public final class StdDeserializer$IntegerDeserializer extends StdDeserializer$PrimitiveOrWrapperDeserializer {
  public StdDeserializer$IntegerDeserializer(Class paramClass, Integer paramInteger) {
    super(paramClass, paramInteger);
  }
  
  public Integer deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    return _parseInteger(paramJsonParser, paramDeserializationContext);
  }
  
  public Integer deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer) {
    return _parseInteger(paramJsonParser, paramDeserializationContext);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdDeserializer$IntegerDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */