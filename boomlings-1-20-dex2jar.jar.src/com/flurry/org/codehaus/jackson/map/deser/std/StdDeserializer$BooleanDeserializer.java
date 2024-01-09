package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;

@JacksonStdImpl
public final class StdDeserializer$BooleanDeserializer extends StdDeserializer$PrimitiveOrWrapperDeserializer {
  public StdDeserializer$BooleanDeserializer(Class paramClass, Boolean paramBoolean) {
    super(paramClass, paramBoolean);
  }
  
  public Boolean deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    return _parseBoolean(paramJsonParser, paramDeserializationContext);
  }
  
  public Boolean deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer) {
    return _parseBoolean(paramJsonParser, paramDeserializationContext);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdDeserializer$BooleanDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */