package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import java.util.TimeZone;

public class FromStringDeserializer$TimeZoneDeserializer extends FromStringDeserializer {
  public FromStringDeserializer$TimeZoneDeserializer() {
    super(TimeZone.class);
  }
  
  protected TimeZone _deserialize(String paramString, DeserializationContext paramDeserializationContext) {
    return TimeZone.getTimeZone(paramString);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\FromStringDeserializer$TimeZoneDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */