package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.map.ser.std.ToStringSerializer;
import com.flurry.org.codehaus.jackson.map.util.Provider;
import java.util.Collection;
import java.util.HashMap;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;

public class JodaSerializers implements Provider {
  static final HashMap _serializers = new HashMap<Object, Object>();
  
  static {
    _serializers.put(DateTime.class, new JodaSerializers$DateTimeSerializer());
    _serializers.put(LocalDateTime.class, new JodaSerializers$LocalDateTimeSerializer());
    _serializers.put(LocalDate.class, new JodaSerializers$LocalDateSerializer());
    _serializers.put(DateMidnight.class, new JodaSerializers$DateMidnightSerializer());
    _serializers.put(Period.class, ToStringSerializer.instance);
  }
  
  public Collection provide() {
    return _serializers.entrySet();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ext\JodaSerializers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */