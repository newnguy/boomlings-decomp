package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.util.ArrayBuilders;

@JacksonStdImpl
final class PrimitiveArrayDeserializers$IntDeser extends PrimitiveArrayDeserializers$Base {
  public PrimitiveArrayDeserializers$IntDeser() {
    super(int[].class);
  }
  
  private final int[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING && paramDeserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && paramJsonParser.getText().length() == 0)
      return null; 
    if (!paramDeserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
      throw paramDeserializationContext.mappingException(this._valueClass); 
    int[] arrayOfInt = new int[1];
    arrayOfInt[0] = _parseIntPrimitive(paramJsonParser, paramDeserializationContext);
    return arrayOfInt;
  }
  
  public int[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (!paramJsonParser.isExpectedStartArrayToken())
      return handleNonArray(paramJsonParser, paramDeserializationContext); 
    ArrayBuilders.IntBuilder intBuilder = paramDeserializationContext.getArrayBuilders().getIntBuilder();
    int[] arrayOfInt = (int[])intBuilder.resetAndStart();
    int i;
    for (i = 0; paramJsonParser.nextToken() != JsonToken.END_ARRAY; i = j) {
      int k = _parseIntPrimitive(paramJsonParser, paramDeserializationContext);
      if (i >= arrayOfInt.length) {
        arrayOfInt = (int[])intBuilder.appendCompletedChunk(arrayOfInt, i);
        i = 0;
      } 
      int j = i + 1;
      arrayOfInt[i] = k;
    } 
    return (int[])intBuilder.completeAndClearBuffer(arrayOfInt, i);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\PrimitiveArrayDeserializers$IntDeser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */