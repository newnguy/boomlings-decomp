package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import java.sql.Date;
import java.util.Date;

public class StdDeserializer$SqlDateDeserializer extends StdScalarDeserializer {
  public StdDeserializer$SqlDateDeserializer() {
    super(Date.class);
  }
  
  public Date deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    null = _parseDate(paramJsonParser, paramDeserializationContext);
    return (null == null) ? null : new Date(null.getTime());
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdDeserializer$SqlDateDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */