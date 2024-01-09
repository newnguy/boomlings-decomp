package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.util.ArrayBuilders;

@JacksonStdImpl
final class PrimitiveArrayDeserializers$BooleanDeser extends PrimitiveArrayDeserializers$Base {
  public PrimitiveArrayDeserializers$BooleanDeser() {
    super(boolean[].class);
  }
  
  private final boolean[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING && paramDeserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && paramJsonParser.getText().length() == 0)
      return null; 
    if (!paramDeserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
      throw paramDeserializationContext.mappingException(this._valueClass); 
    boolean[] arrayOfBoolean = new boolean[1];
    arrayOfBoolean[0] = _parseBooleanPrimitive(paramJsonParser, paramDeserializationContext);
    return arrayOfBoolean;
  }
  
  public boolean[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (!paramJsonParser.isExpectedStartArrayToken())
      return handleNonArray(paramJsonParser, paramDeserializationContext); 
    ArrayBuilders.BooleanBuilder booleanBuilder = paramDeserializationContext.getArrayBuilders().getBooleanBuilder();
    boolean[] arrayOfBoolean = (boolean[])booleanBuilder.resetAndStart();
    int i;
    for (i = 0; paramJsonParser.nextToken() != JsonToken.END_ARRAY; i = j) {
      boolean bool = _parseBooleanPrimitive(paramJsonParser, paramDeserializationContext);
      if (i >= arrayOfBoolean.length) {
        arrayOfBoolean = (boolean[])booleanBuilder.appendCompletedChunk(arrayOfBoolean, i);
        i = 0;
      } 
      int j = i + 1;
      arrayOfBoolean[i] = bool;
    } 
    return (boolean[])booleanBuilder.completeAndClearBuffer(arrayOfBoolean, i);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\PrimitiveArrayDeserializers$BooleanDeser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */