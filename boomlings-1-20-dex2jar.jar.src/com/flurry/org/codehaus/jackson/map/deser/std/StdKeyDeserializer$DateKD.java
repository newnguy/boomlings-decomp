package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import java.util.Date;

final class StdKeyDeserializer$DateKD extends StdKeyDeserializer {
  protected StdKeyDeserializer$DateKD() {
    super(Date.class);
  }
  
  public Date _parse(String paramString, DeserializationContext paramDeserializationContext) {
    return paramDeserializationContext.parseDate(paramString);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdKeyDeserializer$DateKD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */