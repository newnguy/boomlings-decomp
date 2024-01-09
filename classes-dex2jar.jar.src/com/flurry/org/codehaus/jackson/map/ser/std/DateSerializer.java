package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import java.lang.reflect.Type;
import java.util.Date;

@JacksonStdImpl
public class DateSerializer extends ScalarSerializerBase {
  public static DateSerializer instance = new DateSerializer();
  
  public DateSerializer() {
    super(Date.class);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    if (paramSerializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
      String str1 = "number";
      return (JsonNode)createSchemaNode(str1, true);
    } 
    String str = "string";
    return (JsonNode)createSchemaNode(str, true);
  }
  
  public void serialize(Date paramDate, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramSerializerProvider.defaultSerializeDateValue(paramDate, paramJsonGenerator);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\DateSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */