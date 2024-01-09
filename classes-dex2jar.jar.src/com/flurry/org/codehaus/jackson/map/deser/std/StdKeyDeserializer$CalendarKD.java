package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import java.util.Calendar;
import java.util.Date;

final class StdKeyDeserializer$CalendarKD extends StdKeyDeserializer {
  protected StdKeyDeserializer$CalendarKD() {
    super(Calendar.class);
  }
  
  public Calendar _parse(String paramString, DeserializationContext paramDeserializationContext) {
    Date date = paramDeserializationContext.parseDate(paramString);
    return (date == null) ? null : paramDeserializationContext.constructCalendar(date);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdKeyDeserializer$CalendarKD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */