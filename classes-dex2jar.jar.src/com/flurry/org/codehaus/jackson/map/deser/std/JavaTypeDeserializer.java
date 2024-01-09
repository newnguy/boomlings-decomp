package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.type.JavaType;

public class JavaTypeDeserializer extends StdScalarDeserializer {
  public JavaTypeDeserializer() {
    super(JavaType.class);
  }
  
  public JavaType deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken == JsonToken.VALUE_STRING) {
      String str = paramJsonParser.getText().trim();
      return (str.length() == 0) ? (JavaType)getEmptyValue() : paramDeserializationContext.getTypeFactory().constructFromCanonical(str);
    } 
    if (jsonToken == JsonToken.VALUE_EMBEDDED_OBJECT)
      return (JavaType)paramJsonParser.getEmbeddedObject(); 
    throw paramDeserializationContext.mappingException(this._valueClass);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\JavaTypeDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */