package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import java.util.regex.Pattern;

public class FromStringDeserializer$PatternDeserializer extends FromStringDeserializer {
  public FromStringDeserializer$PatternDeserializer() {
    super(Pattern.class);
  }
  
  protected Pattern _deserialize(String paramString, DeserializationContext paramDeserializationContext) {
    return Pattern.compile(paramString);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\FromStringDeserializer$PatternDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */