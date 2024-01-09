package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;

public class JodaDeserializers$DateMidnightDeserializer extends JodaDeserializers$JodaDeserializer {
  public JodaDeserializers$DateMidnightDeserializer() {
    super(DateMidnight.class);
  }
  
  public DateMidnight deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (paramJsonParser.isExpectedStartArrayToken()) {
      paramJsonParser.nextToken();
      int i = paramJsonParser.getIntValue();
      paramJsonParser.nextToken();
      int k = paramJsonParser.getIntValue();
      paramJsonParser.nextToken();
      int j = paramJsonParser.getIntValue();
      if (paramJsonParser.nextToken() != JsonToken.END_ARRAY)
        throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "after DateMidnight ints"); 
      return new DateMidnight(i, k, j);
    } 
    switch (JodaDeserializers$1.$SwitchMap$org$codehaus$jackson$JsonToken[paramJsonParser.getCurrentToken().ordinal()]) {
      default:
        throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.START_ARRAY, "expected JSON Array, Number or String");
      case 1:
        return new DateMidnight(paramJsonParser.getLongValue());
      case 2:
        break;
    } 
    DateTime dateTime = parseLocal(paramJsonParser);
    return (dateTime == null) ? null : dateTime.toDateMidnight();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ext\JodaDeserializers$DateMidnightDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */