package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;

@JacksonStdImpl
public class ClassDeserializer extends StdScalarDeserializer {
  public ClassDeserializer() {
    super(Class.class);
  }
  
  public Class deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    String str;
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken == JsonToken.VALUE_STRING) {
      str = paramJsonParser.getText();
      if (str.indexOf('.') < 0) {
        if ("int".equals(str))
          return int.class; 
        if ("long".equals(str))
          return long.class; 
        if ("float".equals(str))
          return float.class; 
        if ("double".equals(str))
          return double.class; 
        if ("boolean".equals(str))
          return boolean.class; 
        if ("byte".equals(str))
          return byte.class; 
        if ("char".equals(str))
          return char.class; 
        if ("short".equals(str))
          return short.class; 
        if ("void".equals(str))
          return void.class; 
      } 
      try {
        Class<?> clazz = Class.forName(paramJsonParser.getText());
      } catch (ClassNotFoundException classNotFoundException) {
        throw paramDeserializationContext.instantiationException(this._valueClass, classNotFoundException);
      } 
      return (Class)classNotFoundException;
    } 
    throw paramDeserializationContext.mappingException(this._valueClass, str);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\ClassDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */