package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadableInstant;

public class JodaDeserializers$DateTimeDeserializer extends JodaDeserializers$JodaDeserializer {
  public JodaDeserializers$DateTimeDeserializer(Class paramClass) {
    super(paramClass);
  }
  
  public ReadableInstant deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken == JsonToken.VALUE_NUMBER_INT)
      return (ReadableInstant)new DateTime(paramJsonParser.getLongValue(), DateTimeZone.UTC); 
    if (jsonToken == JsonToken.VALUE_STRING) {
      String str = paramJsonParser.getText().trim();
      return (ReadableInstant)((str.length() == 0) ? null : new DateTime(str, DateTimeZone.UTC));
    } 
    throw paramDeserializationContext.mappingException(getValueClass());
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ext\JodaDeserializers$DateTimeDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */