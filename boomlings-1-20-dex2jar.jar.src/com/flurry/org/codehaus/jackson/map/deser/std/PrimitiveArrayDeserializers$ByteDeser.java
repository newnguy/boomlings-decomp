package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.util.ArrayBuilders;

@JacksonStdImpl
final class PrimitiveArrayDeserializers$ByteDeser extends PrimitiveArrayDeserializers$Base {
  public PrimitiveArrayDeserializers$ByteDeser() {
    super(byte[].class);
  }
  
  private final byte[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    boolean bool;
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING && paramDeserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && paramJsonParser.getText().length() == 0)
      return null; 
    if (!paramDeserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
      throw paramDeserializationContext.mappingException(this._valueClass); 
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
      bool = paramJsonParser.getByteValue();
    } else {
      if (jsonToken != JsonToken.VALUE_NULL)
        throw paramDeserializationContext.mappingException(this._valueClass.getComponentType()); 
      bool = false;
    } 
    return new byte[] { bool };
  }
  
  public byte[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken == JsonToken.VALUE_STRING)
      return paramJsonParser.getBinaryValue(paramDeserializationContext.getBase64Variant()); 
    if (jsonToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
      Object object = paramJsonParser.getEmbeddedObject();
      if (object == null)
        return null; 
      if (object instanceof byte[])
        return (byte[])object; 
    } 
    if (!paramJsonParser.isExpectedStartArrayToken())
      return handleNonArray(paramJsonParser, paramDeserializationContext); 
    ArrayBuilders.ByteBuilder byteBuilder = paramDeserializationContext.getArrayBuilders().getByteBuilder();
    byte[] arrayOfByte = (byte[])byteBuilder.resetAndStart();
    int i = 0;
    while (true) {
      JsonToken jsonToken1 = paramJsonParser.nextToken();
      if (jsonToken1 != JsonToken.END_ARRAY) {
        boolean bool;
        if (jsonToken1 == JsonToken.VALUE_NUMBER_INT || jsonToken1 == JsonToken.VALUE_NUMBER_FLOAT) {
          bool = paramJsonParser.getByteValue();
        } else {
          if (jsonToken1 != JsonToken.VALUE_NULL)
            throw paramDeserializationContext.mappingException(this._valueClass.getComponentType()); 
          bool = false;
        } 
        if (i >= arrayOfByte.length) {
          arrayOfByte = (byte[])byteBuilder.appendCompletedChunk(arrayOfByte, i);
          i = 0;
        } 
        int j = i + 1;
        arrayOfByte[i] = bool;
        i = j;
        continue;
      } 
      return (byte[])byteBuilder.completeAndClearBuffer(arrayOfByte, i);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\PrimitiveArrayDeserializers$ByteDeser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */