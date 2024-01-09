package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import java.util.Date;

public class DateDeserializer extends StdScalarDeserializer {
  public DateDeserializer() {
    super(Date.class);
  }
  
  public Date deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    return _parseDate(paramJsonParser, paramDeserializationContext);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\DateDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */