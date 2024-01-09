package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import java.util.Calendar;

public class StdKeySerializers$CalendarKeySerializer extends SerializerBase {
  protected static final JsonSerializer instance = new StdKeySerializers$CalendarKeySerializer();
  
  public StdKeySerializers$CalendarKeySerializer() {
    super(Calendar.class);
  }
  
  public void serialize(Calendar paramCalendar, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramSerializerProvider.defaultSerializeDateKey(paramCalendar.getTimeInMillis(), paramJsonGenerator);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\StdKeySerializers$CalendarKeySerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */