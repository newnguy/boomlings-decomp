package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import java.util.UUID;

final class StdKeyDeserializer$UuidKD extends StdKeyDeserializer {
  protected StdKeyDeserializer$UuidKD() {
    super(UUID.class);
  }
  
  public UUID _parse(String paramString, DeserializationContext paramDeserializationContext) {
    return UUID.fromString(paramString);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdKeyDeserializer$UuidKD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */