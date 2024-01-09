package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import java.sql.Timestamp;

public class TimestampDeserializer extends StdScalarDeserializer {
  public TimestampDeserializer() {
    super(Timestamp.class);
  }
  
  public Timestamp deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    return new Timestamp(_parseDate(paramJsonParser, paramDeserializationContext).getTime());
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\TimestampDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */