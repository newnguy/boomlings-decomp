package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import java.util.Locale;

public class FromStringDeserializer$LocaleDeserializer extends FromStringDeserializer {
  public FromStringDeserializer$LocaleDeserializer() {
    super(Locale.class);
  }
  
  protected Locale _deserialize(String paramString, DeserializationContext paramDeserializationContext) {
    int i = paramString.indexOf('_');
    if (i < 0)
      return new Locale(paramString); 
    String str = paramString.substring(0, i);
    paramString = paramString.substring(i + 1);
    i = paramString.indexOf('_');
    return (i < 0) ? new Locale(str, paramString) : new Locale(str, paramString.substring(0, i), paramString.substring(i + 1));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\FromStringDeserializer$LocaleDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */