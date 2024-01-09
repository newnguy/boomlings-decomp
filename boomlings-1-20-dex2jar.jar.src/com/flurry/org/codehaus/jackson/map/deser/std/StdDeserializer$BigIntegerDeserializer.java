package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import java.math.BigInteger;

@JacksonStdImpl
public class StdDeserializer$BigIntegerDeserializer extends StdScalarDeserializer {
  public StdDeserializer$BigIntegerDeserializer() {
    super(BigInteger.class);
  }
  
  public BigInteger deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken == JsonToken.VALUE_NUMBER_INT) {
      String str;
      switch (StdDeserializer$1.$SwitchMap$org$codehaus$jackson$JsonParser$NumberType[paramJsonParser.getNumberType().ordinal()]) {
        default:
          str = paramJsonParser.getText().trim();
          if (str.length() == 0)
            return null; 
          break;
        case 1:
        case 2:
          return BigInteger.valueOf(str.getLongValue());
      } 
    } else {
      if (jsonToken == JsonToken.VALUE_NUMBER_FLOAT)
        return paramJsonParser.getDecimalValue().toBigInteger(); 
      if (jsonToken != JsonToken.VALUE_STRING)
        throw paramDeserializationContext.mappingException(this._valueClass, jsonToken); 
    } 
    try {
      BigInteger bigInteger = new BigInteger((String)paramJsonParser);
    } catch (IllegalArgumentException illegalArgumentException) {
      throw paramDeserializationContext.weirdStringException(this._valueClass, "not a valid representation");
    } 
    return (BigInteger)illegalArgumentException;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdDeserializer$BigIntegerDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */