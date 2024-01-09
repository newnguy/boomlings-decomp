package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import java.lang.reflect.Type;
import java.util.Calendar;

@JacksonStdImpl
public class CalendarSerializer extends ScalarSerializerBase {
  public static CalendarSerializer instance = new CalendarSerializer();
  
  public CalendarSerializer() {
    super(Calendar.class);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    if (paramSerializerProvider.isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
      String str1 = "number";
      return (JsonNode)createSchemaNode(str1, true);
    } 
    String str = "string";
    return (JsonNode)createSchemaNode(str, true);
  }
  
  public void serialize(Calendar paramCalendar, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramSerializerProvider.defaultSerializeDateValue(paramCalendar.getTimeInMillis(), paramJsonGenerator);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\CalendarSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */