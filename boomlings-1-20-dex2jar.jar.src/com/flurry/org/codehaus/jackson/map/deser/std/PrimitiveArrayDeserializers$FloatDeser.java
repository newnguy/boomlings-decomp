package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.util.ArrayBuilders;

@JacksonStdImpl
final class PrimitiveArrayDeserializers$FloatDeser extends PrimitiveArrayDeserializers$Base {
  public PrimitiveArrayDeserializers$FloatDeser() {
    super(float[].class);
  }
  
  private final float[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING && paramDeserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && paramJsonParser.getText().length() == 0)
      return null; 
    if (!paramDeserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
      throw paramDeserializationContext.mappingException(this._valueClass); 
    float[] arrayOfFloat = new float[1];
    arrayOfFloat[0] = _parseFloatPrimitive(paramJsonParser, paramDeserializationContext);
    return arrayOfFloat;
  }
  
  public float[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (!paramJsonParser.isExpectedStartArrayToken())
      return handleNonArray(paramJsonParser, paramDeserializationContext); 
    ArrayBuilders.FloatBuilder floatBuilder = paramDeserializationContext.getArrayBuilders().getFloatBuilder();
    float[] arrayOfFloat = (float[])floatBuilder.resetAndStart();
    int i;
    for (i = 0; paramJsonParser.nextToken() != JsonToken.END_ARRAY; i = j) {
      float f = _parseFloatPrimitive(paramJsonParser, paramDeserializationContext);
      if (i >= arrayOfFloat.length) {
        arrayOfFloat = (float[])floatBuilder.appendCompletedChunk(arrayOfFloat, i);
        i = 0;
      } 
      int j = i + 1;
      arrayOfFloat[i] = f;
    } 
    return (float[])floatBuilder.completeAndClearBuffer(arrayOfFloat, i);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\PrimitiveArrayDeserializers$FloatDeser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */