package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;

@JacksonStdImpl
public final class StdDeserializer$DoubleDeserializer extends StdDeserializer$PrimitiveOrWrapperDeserializer {
  public StdDeserializer$DoubleDeserializer(Class paramClass, Double paramDouble) {
    super(paramClass, paramDouble);
  }
  
  public Double deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    return _parseDouble(paramJsonParser, paramDeserializationContext);
  }
  
  public Double deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer) {
    return _parseDouble(paramJsonParser, paramDeserializationContext);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdDeserializer$DoubleDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */