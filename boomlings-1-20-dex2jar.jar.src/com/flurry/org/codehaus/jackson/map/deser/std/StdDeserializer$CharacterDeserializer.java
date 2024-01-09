package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;

@JacksonStdImpl
public final class StdDeserializer$CharacterDeserializer extends StdDeserializer$PrimitiveOrWrapperDeserializer {
  public StdDeserializer$CharacterDeserializer(Class paramClass, Character paramCharacter) {
    super(paramClass, paramCharacter);
  }
  
  public Character deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken == JsonToken.VALUE_NUMBER_INT) {
      int i = paramJsonParser.getIntValue();
      if (i >= 0 && i <= 65535)
        return Character.valueOf((char)i); 
    } else if (jsonToken == JsonToken.VALUE_STRING) {
      String str = paramJsonParser.getText();
      if (str.length() == 1)
        return Character.valueOf(str.charAt(0)); 
      if (str.length() == 0)
        return (Character)getEmptyValue(); 
    } 
    throw paramDeserializationContext.mappingException(this._valueClass, jsonToken);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdDeserializer$CharacterDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */