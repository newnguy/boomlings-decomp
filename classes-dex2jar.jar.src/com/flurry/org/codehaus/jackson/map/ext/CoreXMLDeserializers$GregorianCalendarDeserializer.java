package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.deser.std.StdScalarDeserializer;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.XMLGregorianCalendar;

public class CoreXMLDeserializers$GregorianCalendarDeserializer extends StdScalarDeserializer {
  public CoreXMLDeserializers$GregorianCalendarDeserializer() {
    super(XMLGregorianCalendar.class);
  }
  
  public XMLGregorianCalendar deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    Date date = _parseDate(paramJsonParser, paramDeserializationContext);
    if (date == null)
      return null; 
    GregorianCalendar gregorianCalendar = new GregorianCalendar();
    gregorianCalendar.setTime(date);
    return CoreXMLDeserializers._dataTypeFactory.newXMLGregorianCalendar(gregorianCalendar);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ext\CoreXMLDeserializers$GregorianCalendarDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */