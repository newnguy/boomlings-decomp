package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.Base64Variants;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;

@JacksonStdImpl
final class PrimitiveArrayDeserializers$CharDeser extends PrimitiveArrayDeserializers$Base {
  public PrimitiveArrayDeserializers$CharDeser() {
    super(char[].class);
  }
  
  public char[] deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    char[] arrayOfChar1;
    char[] arrayOfChar2;
    StringBuilder stringBuilder;
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken == JsonToken.VALUE_STRING) {
      arrayOfChar2 = paramJsonParser.getTextCharacters();
      int i = paramJsonParser.getTextOffset();
      int j = paramJsonParser.getTextLength();
      arrayOfChar1 = new char[j];
      System.arraycopy(arrayOfChar2, i, arrayOfChar1, 0, j);
      return arrayOfChar1;
    } 
    if (arrayOfChar1.isExpectedStartArrayToken()) {
      stringBuilder = new StringBuilder(64);
      while (true) {
        JsonToken jsonToken1 = arrayOfChar1.nextToken();
        if (jsonToken1 != JsonToken.END_ARRAY) {
          if (jsonToken1 != JsonToken.VALUE_STRING)
            throw arrayOfChar2.mappingException(char.class); 
          String str = arrayOfChar1.getText();
          if (str.length() != 1)
            throw JsonMappingException.from(arrayOfChar1, "Can not convert a JSON String of length " + str.length() + " into a char element of char array"); 
          stringBuilder.append(str.charAt(0));
          continue;
        } 
        return stringBuilder.toString().toCharArray();
      } 
    } 
    if (stringBuilder == JsonToken.VALUE_EMBEDDED_OBJECT) {
      Object object = arrayOfChar1.getEmbeddedObject();
      if (object == null)
        return null; 
      if (object instanceof char[])
        return (char[])object; 
      if (object instanceof String)
        return ((String)object).toCharArray(); 
      if (object instanceof byte[])
        return Base64Variants.getDefaultVariant().encode((byte[])object, false).toCharArray(); 
    } 
    throw arrayOfChar2.mappingException(this._valueClass);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\PrimitiveArrayDeserializers$CharDeser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */