package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

public class JodaDeserializers$LocalDateTimeDeserializer extends JodaDeserializers$JodaDeserializer {
  public JodaDeserializers$LocalDateTimeDeserializer() {
    super(LocalDateTime.class);
  }
  
  public LocalDateTime deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (paramJsonParser.isExpectedStartArrayToken()) {
      paramJsonParser.nextToken();
      int i1 = paramJsonParser.getIntValue();
      paramJsonParser.nextToken();
      int m = paramJsonParser.getIntValue();
      paramJsonParser.nextToken();
      int n = paramJsonParser.getIntValue();
      paramJsonParser.nextToken();
      int i2 = paramJsonParser.getIntValue();
      paramJsonParser.nextToken();
      int j = paramJsonParser.getIntValue();
      paramJsonParser.nextToken();
      int k = paramJsonParser.getIntValue();
      int i = 0;
      if (paramJsonParser.nextToken() != JsonToken.END_ARRAY) {
        i = paramJsonParser.getIntValue();
        paramJsonParser.nextToken();
      } 
      if (paramJsonParser.getCurrentToken() != JsonToken.END_ARRAY)
        throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "after LocalDateTime ints"); 
      return new LocalDateTime(i1, m, n, i2, j, k, i);
    } 
    switch (JodaDeserializers$1.$SwitchMap$org$codehaus$jackson$JsonToken[paramJsonParser.getCurrentToken().ordinal()]) {
      default:
        throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.START_ARRAY, "expected JSON Array or Number");
      case 1:
        return new LocalDateTime(paramJsonParser.getLongValue());
      case 2:
        break;
    } 
    DateTime dateTime = parseLocal(paramJsonParser);
    return (dateTime == null) ? null : dateTime.toLocalDateTime();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ext\JodaDeserializers$LocalDateTimeDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */