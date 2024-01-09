package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.util.ArrayBuilders;

@JacksonStdImpl
final class PrimitiveArrayDeserializers$DoubleDeser extends PrimitiveArrayDeserializers$Base {
  public PrimitiveArrayDeserializers$DoubleDeser() {
    super(double[].class);
  }
  
  private final double[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING && paramDeserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && paramJsonParser.getText().length() == 0)
      return null; 
    if (!paramDeserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
      throw paramDeserializationContext.mappingException(this._valueClass); 
    double[] arrayOfDouble = new double[1];
    arrayOfDouble[0] = _parseDoublePrimitive(paramJsonParser, paramDeserializationContext);
    return arrayOfDouble;
  }
  
  public double[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (!paramJsonParser.isExpectedStartArrayToken())
      return handleNonArray(paramJsonParser, paramDeserializationContext); 
    ArrayBuilders.DoubleBuilder doubleBuilder = paramDeserializationContext.getArrayBuilders().getDoubleBuilder();
    double[] arrayOfDouble = (double[])doubleBuilder.resetAndStart();
    int i;
    for (i = 0; paramJsonParser.nextToken() != JsonToken.END_ARRAY; i = j) {
      double d = _parseDoublePrimitive(paramJsonParser, paramDeserializationContext);
      if (i >= arrayOfDouble.length) {
        arrayOfDouble = (double[])doubleBuilder.appendCompletedChunk(arrayOfDouble, i);
        i = 0;
      } 
      int j = i + 1;
      arrayOfDouble[i] = d;
    } 
    return (double[])doubleBuilder.completeAndClearBuffer(arrayOfDouble, i);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\PrimitiveArrayDeserializers$DoubleDeser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */