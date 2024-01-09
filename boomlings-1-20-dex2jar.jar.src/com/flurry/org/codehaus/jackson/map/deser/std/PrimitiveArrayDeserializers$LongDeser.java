package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.util.ArrayBuilders;

@JacksonStdImpl
final class PrimitiveArrayDeserializers$LongDeser extends PrimitiveArrayDeserializers$Base {
  public PrimitiveArrayDeserializers$LongDeser() {
    super(long[].class);
  }
  
  private final long[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING && paramDeserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && paramJsonParser.getText().length() == 0)
      return null; 
    if (!paramDeserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
      throw paramDeserializationContext.mappingException(this._valueClass); 
    long[] arrayOfLong = new long[1];
    arrayOfLong[0] = _parseLongPrimitive(paramJsonParser, paramDeserializationContext);
    return arrayOfLong;
  }
  
  public long[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (!paramJsonParser.isExpectedStartArrayToken())
      return handleNonArray(paramJsonParser, paramDeserializationContext); 
    ArrayBuilders.LongBuilder longBuilder = paramDeserializationContext.getArrayBuilders().getLongBuilder();
    long[] arrayOfLong = (long[])longBuilder.resetAndStart();
    int i;
    for (i = 0; paramJsonParser.nextToken() != JsonToken.END_ARRAY; i = j) {
      long l = _parseLongPrimitive(paramJsonParser, paramDeserializationContext);
      if (i >= arrayOfLong.length) {
        arrayOfLong = (long[])longBuilder.appendCompletedChunk(arrayOfLong, i);
        i = 0;
      } 
      int j = i + 1;
      arrayOfLong[i] = l;
    } 
    return (long[])longBuilder.completeAndClearBuffer(arrayOfLong, i);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\PrimitiveArrayDeserializers$LongDeser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */