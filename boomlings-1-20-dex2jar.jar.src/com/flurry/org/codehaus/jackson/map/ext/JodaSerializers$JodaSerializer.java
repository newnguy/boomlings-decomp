package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public abstract class JodaSerializers$JodaSerializer extends SerializerBase {
  static final DateTimeFormatter _localDateFormat;
  
  static final DateTimeFormatter _localDateTimeFormat = ISODateTimeFormat.dateTime();
  
  static {
    _localDateFormat = ISODateTimeFormat.date();
  }
  
  protected JodaSerializers$JodaSerializer(Class paramClass) {
    super(paramClass);
  }
  
  protected String printLocalDate(ReadableInstant paramReadableInstant) {
    return _localDateFormat.print(paramReadableInstant);
  }
  
  protected String printLocalDate(ReadablePartial paramReadablePartial) {
    return _localDateFormat.print(paramReadablePartial);
  }
  
  protected String printLocalDateTime(ReadablePartial paramReadablePartial) {
    return _localDateTimeFormat.print(paramReadablePartial);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ext\JodaSerializers$JodaSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */