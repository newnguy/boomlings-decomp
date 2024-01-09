package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public class JodaDeserializers$LocalDateDeserializer extends JodaDeserializers$JodaDeserializer {
  public JodaDeserializers$LocalDateDeserializer() {
    super(LocalDate.class);
  }
  
  public LocalDate deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (paramJsonParser.isExpectedStartArrayToken()) {
      paramJsonParser.nextToken();
      int k = paramJsonParser.getIntValue();
      paramJsonParser.nextToken();
      int j = paramJsonParser.getIntValue();
      paramJsonParser.nextToken();
      int i = paramJsonParser.getIntValue();
      if (paramJsonParser.nextToken() != JsonToken.END_ARRAY)
        throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "after LocalDate ints"); 
      return new LocalDate(k, j, i);
    } 
    switch (JodaDeserializers$1.$SwitchMap$org$codehaus$jackson$JsonToken[paramJsonParser.getCurrentToken().ordinal()]) {
      default:
        throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.START_ARRAY, "expected JSON Array, String or Number");
      case 1:
        return new LocalDate(paramJsonParser.getLongValue());
      case 2:
        break;
    } 
    DateTime dateTime = parseLocal(paramJsonParser);
    return (dateTime == null) ? null : dateTime.toLocalDate();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ext\JodaDeserializers$LocalDateDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */