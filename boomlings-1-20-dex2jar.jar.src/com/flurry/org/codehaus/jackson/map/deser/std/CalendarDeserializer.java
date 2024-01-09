package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import java.util.Calendar;
import java.util.Date;

@JacksonStdImpl
public class CalendarDeserializer extends StdScalarDeserializer {
  protected final Class _calendarClass;
  
  public CalendarDeserializer() {
    this(null);
  }
  
  public CalendarDeserializer(Class paramClass) {
    super(Calendar.class);
    this._calendarClass = paramClass;
  }
  
  public Calendar deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    Date date = _parseDate(paramJsonParser, paramDeserializationContext);
    if (date == null)
      return null; 
    if (this._calendarClass == null)
      return paramDeserializationContext.constructCalendar(date); 
    try {
      Calendar calendar = this._calendarClass.newInstance();
      calendar.setTimeInMillis(date.getTime());
    } catch (Exception exception) {
      throw paramDeserializationContext.instantiationException(this._calendarClass, exception);
    } 
    return (Calendar)exception;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\CalendarDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */