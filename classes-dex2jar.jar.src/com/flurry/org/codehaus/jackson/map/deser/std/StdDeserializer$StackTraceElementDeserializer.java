package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;

public class StdDeserializer$StackTraceElementDeserializer extends StdScalarDeserializer {
  public StdDeserializer$StackTraceElementDeserializer() {
    super(StackTraceElement.class);
  }
  
  public StackTraceElement deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    String str;
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken == JsonToken.START_OBJECT) {
      str = "";
      String str1 = "";
      String str2 = "";
      int i = -1;
      while (true) {
        JsonToken jsonToken1 = paramJsonParser.nextValue();
        if (jsonToken1 != JsonToken.END_OBJECT) {
          String str3 = paramJsonParser.getCurrentName();
          if ("className".equals(str3)) {
            str = paramJsonParser.getText();
            continue;
          } 
          if ("fileName".equals(str3)) {
            str2 = paramJsonParser.getText();
            continue;
          } 
          if ("lineNumber".equals(str3)) {
            if (jsonToken1.isNumeric()) {
              i = paramJsonParser.getIntValue();
              continue;
            } 
            throw JsonMappingException.from(paramJsonParser, "Non-numeric token (" + jsonToken1 + ") for property 'lineNumber'");
          } 
          if ("methodName".equals(str3)) {
            str1 = paramJsonParser.getText();
            continue;
          } 
          if (!"nativeMethod".equals(str3))
            handleUnknownProperty(paramJsonParser, paramDeserializationContext, this._valueClass, str3); 
          continue;
        } 
        return new StackTraceElement(str, str1, str2, i);
      } 
    } 
    throw paramDeserializationContext.mappingException(this._valueClass, str);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdDeserializer$StackTraceElementDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */