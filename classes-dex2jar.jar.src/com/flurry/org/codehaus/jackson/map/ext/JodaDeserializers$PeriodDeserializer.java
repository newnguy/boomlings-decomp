package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import org.joda.time.Period;
import org.joda.time.ReadablePeriod;

public class JodaDeserializers$PeriodDeserializer extends JodaDeserializers$JodaDeserializer {
  public JodaDeserializers$PeriodDeserializer() {
    super(ReadablePeriod.class);
  }
  
  public ReadablePeriod deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    switch (JodaDeserializers$1.$SwitchMap$org$codehaus$jackson$JsonToken[paramJsonParser.getCurrentToken().ordinal()]) {
      default:
        throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.START_ARRAY, "expected JSON Number or String");
      case 1:
        return (ReadablePeriod)new Period(paramJsonParser.getLongValue());
      case 2:
        break;
    } 
    return (ReadablePeriod)new Period(paramJsonParser.getText());
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ext\JodaDeserializers$PeriodDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */