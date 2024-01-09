package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.ser.std.CalendarSerializer;
import com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase;
import java.lang.reflect.Type;
import javax.xml.datatype.XMLGregorianCalendar;

public class CoreXMLSerializers$XMLGregorianCalendarSerializer extends SerializerBase {
  public CoreXMLSerializers$XMLGregorianCalendarSerializer() {
    super(XMLGregorianCalendar.class);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    return CalendarSerializer.instance.getSchema(paramSerializerProvider, paramType);
  }
  
  public void serialize(XMLGregorianCalendar paramXMLGregorianCalendar, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    CalendarSerializer.instance.serialize(paramXMLGregorianCalendar.toGregorianCalendar(), paramJsonGenerator, paramSerializerProvider);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ext\CoreXMLSerializers$XMLGregorianCalendarSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */