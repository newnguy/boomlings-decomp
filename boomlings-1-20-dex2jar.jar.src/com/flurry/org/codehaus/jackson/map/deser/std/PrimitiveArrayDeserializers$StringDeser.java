package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.util.ObjectBuffer;

@JacksonStdImpl
final class PrimitiveArrayDeserializers$StringDeser extends PrimitiveArrayDeserializers$Base {
  public PrimitiveArrayDeserializers$StringDeser() {
    super(String[].class);
  }
  
  private final String[] handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    String str;
    JsonParser jsonParser1 = null;
    JsonParser jsonParser2 = null;
    if (!paramDeserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
      if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING && paramDeserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && paramJsonParser.getText().length() == 0)
        return (String[])jsonParser2; 
      throw paramDeserializationContext.mappingException(this._valueClass);
    } 
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
      paramJsonParser = jsonParser1;
    } else {
      str = paramJsonParser.getText();
    } 
    return new String[] { str };
  }
  
  public String[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (!paramJsonParser.isExpectedStartArrayToken())
      return handleNonArray(paramJsonParser, paramDeserializationContext); 
    ObjectBuffer objectBuffer = paramDeserializationContext.leaseObjectBuffer();
    Object[] arrayOfObject = objectBuffer.resetAndStart();
    int i = 0;
    while (true) {
      JsonToken jsonToken = paramJsonParser.nextToken();
      if (jsonToken != JsonToken.END_ARRAY) {
        String str;
        if (jsonToken == JsonToken.VALUE_NULL) {
          jsonToken = null;
        } else {
          str = paramJsonParser.getText();
        } 
        if (i >= arrayOfObject.length) {
          arrayOfObject = objectBuffer.appendCompletedChunk(arrayOfObject);
          i = 0;
        } 
        int j = i + 1;
        arrayOfObject[i] = str;
        i = j;
        continue;
      } 
      String[] arrayOfString = (String[])objectBuffer.completeAndClearBuffer(arrayOfObject, i, String.class);
      paramDeserializationContext.returnObjectBuffer(objectBuffer);
      return arrayOfString;
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\PrimitiveArrayDeserializers$StringDeser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */