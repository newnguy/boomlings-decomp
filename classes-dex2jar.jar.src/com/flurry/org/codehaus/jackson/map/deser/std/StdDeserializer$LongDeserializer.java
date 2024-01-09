package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;

@JacksonStdImpl
public final class StdDeserializer$LongDeserializer extends StdDeserializer$PrimitiveOrWrapperDeserializer {
  public StdDeserializer$LongDeserializer(Class paramClass, Long paramLong) {
    super(paramClass, paramLong);
  }
  
  public Long deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    return _parseLong(paramJsonParser, paramDeserializationContext);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdDeserializer$LongDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */