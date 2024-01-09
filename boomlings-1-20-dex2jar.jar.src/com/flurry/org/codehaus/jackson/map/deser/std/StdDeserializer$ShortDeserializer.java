package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;

@JacksonStdImpl
public final class StdDeserializer$ShortDeserializer extends StdDeserializer$PrimitiveOrWrapperDeserializer {
  public StdDeserializer$ShortDeserializer(Class paramClass, Short paramShort) {
    super(paramClass, paramShort);
  }
  
  public Short deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    return _parseShort(paramJsonParser, paramDeserializationContext);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdDeserializer$ShortDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */