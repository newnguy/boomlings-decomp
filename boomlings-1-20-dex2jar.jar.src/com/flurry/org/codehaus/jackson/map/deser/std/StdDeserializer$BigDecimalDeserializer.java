package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import java.math.BigDecimal;

@JacksonStdImpl
public class StdDeserializer$BigDecimalDeserializer extends StdScalarDeserializer {
  public StdDeserializer$BigDecimalDeserializer() {
    super(BigDecimal.class);
  }
  
  public BigDecimal deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT)
      return paramJsonParser.getDecimalValue(); 
    if (jsonToken == JsonToken.VALUE_STRING) {
      String str = paramJsonParser.getText().trim();
      if (str.length() == 0)
        return null; 
      try {
        BigDecimal bigDecimal = new BigDecimal(str);
      } catch (IllegalArgumentException illegalArgumentException) {
        throw paramDeserializationContext.weirdStringException(this._valueClass, "not a valid representation");
      } 
      return (BigDecimal)illegalArgumentException;
    } 
    throw paramDeserializationContext.mappingException(this._valueClass, jsonToken);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdDeserializer$BigDecimalDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */