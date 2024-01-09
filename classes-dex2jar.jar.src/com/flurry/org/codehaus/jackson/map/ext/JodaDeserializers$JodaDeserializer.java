package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.deser.std.StdScalarDeserializer;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

abstract class JodaDeserializers$JodaDeserializer extends StdScalarDeserializer {
  static final DateTimeFormatter _localDateTimeFormat = ISODateTimeFormat.localDateOptionalTimeParser();
  
  protected JodaDeserializers$JodaDeserializer(Class paramClass) {
    super(paramClass);
  }
  
  protected DateTime parseLocal(JsonParser paramJsonParser) {
    String str = paramJsonParser.getText().trim();
    return (str.length() == 0) ? null : _localDateTimeFormat.parseDateTime(str);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ext\JodaDeserializers$JodaDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */