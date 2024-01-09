package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.util.ArrayBuilders;

@JacksonStdImpl
final class PrimitiveArrayDeserializers$ShortDeser extends PrimitiveArrayDeserializers$Base {
  public PrimitiveArrayDeserializers$ShortDeser() {
    super(short[].class);
  }
  
  private final short[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING && paramDeserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && paramJsonParser.getText().length() == 0)
      return null; 
    if (!paramDeserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
      throw paramDeserializationContext.mappingException(this._valueClass); 
    short[] arrayOfShort = new short[1];
    arrayOfShort[0] = _parseShortPrimitive(paramJsonParser, paramDeserializationContext);
    return arrayOfShort;
  }
  
  public short[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (!paramJsonParser.isExpectedStartArrayToken())
      return handleNonArray(paramJsonParser, paramDeserializationContext); 
    ArrayBuilders.ShortBuilder shortBuilder = paramDeserializationContext.getArrayBuilders().getShortBuilder();
    short[] arrayOfShort = (short[])shortBuilder.resetAndStart();
    int i;
    for (i = 0; paramJsonParser.nextToken() != JsonToken.END_ARRAY; i = j) {
      short s = _parseShortPrimitive(paramJsonParser, paramDeserializationContext);
      if (i >= arrayOfShort.length) {
        arrayOfShort = (short[])shortBuilder.appendCompletedChunk(arrayOfShort, i);
        i = 0;
      } 
      int j = i + 1;
      arrayOfShort[i] = s;
    } 
    return (short[])shortBuilder.completeAndClearBuffer(arrayOfShort, i);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\PrimitiveArrayDeserializers$ShortDeser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */