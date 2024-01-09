package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;

@JacksonStdImpl
public final class StdDeserializer$FloatDeserializer extends StdDeserializer$PrimitiveOrWrapperDeserializer {
  public StdDeserializer$FloatDeserializer(Class paramClass, Float paramFloat) {
    super(paramClass, paramFloat);
  }
  
  public Float deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    return _parseFloat(paramJsonParser, paramDeserializationContext);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdDeserializer$FloatDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */