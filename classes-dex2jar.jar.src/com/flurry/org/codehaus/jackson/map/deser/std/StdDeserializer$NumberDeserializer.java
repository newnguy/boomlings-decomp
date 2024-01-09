package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import java.math.BigDecimal;
import java.math.BigInteger;

@JacksonStdImpl
public final class StdDeserializer$NumberDeserializer extends StdScalarDeserializer {
  public StdDeserializer$NumberDeserializer() {
    super(Number.class);
  }
  
  public Number deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken == JsonToken.VALUE_NUMBER_INT)
      return paramDeserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_INTEGER_FOR_INTS) ? paramJsonParser.getBigIntegerValue() : paramJsonParser.getNumberValue(); 
    if (jsonToken == JsonToken.VALUE_NUMBER_FLOAT)
      return (Number)(paramDeserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS) ? paramJsonParser.getDecimalValue() : Double.valueOf(paramJsonParser.getDoubleValue())); 
    if (jsonToken == JsonToken.VALUE_STRING) {
      String str = paramJsonParser.getText().trim();
      try {
        if (str.indexOf('.') >= 0)
          return (Number)(paramDeserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_DECIMAL_FOR_FLOATS) ? new BigDecimal(str) : new Double(str)); 
      } catch (IllegalArgumentException illegalArgumentException) {
        throw paramDeserializationContext.weirdStringException(this._valueClass, "not a valid number");
      } 
      if (paramDeserializationContext.isEnabled(DeserializationConfig.Feature.USE_BIG_INTEGER_FOR_INTS))
        return new BigInteger((String)illegalArgumentException); 
      long l = Long.parseLong((String)illegalArgumentException);
      return (Number)((l <= 2147483647L && l >= -2147483648L) ? Integer.valueOf((int)l) : Long.valueOf(l));
    } 
    throw paramDeserializationContext.mappingException(this._valueClass, jsonToken);
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer) {
    switch (StdDeserializer$1.$SwitchMap$org$codehaus$jackson$JsonToken[paramJsonParser.getCurrentToken().ordinal()]) {
      default:
        return paramTypeDeserializer.deserializeTypedFromScalar(paramJsonParser, paramDeserializationContext);
      case 1:
      case 2:
      case 3:
        break;
    } 
    return deserialize(paramJsonParser, paramDeserializationContext);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdDeserializer$NumberDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */