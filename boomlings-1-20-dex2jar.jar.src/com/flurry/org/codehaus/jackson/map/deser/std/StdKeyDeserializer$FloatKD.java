package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;

final class StdKeyDeserializer$FloatKD extends StdKeyDeserializer {
  StdKeyDeserializer$FloatKD() {
    super(Float.class);
  }
  
  public Float _parse(String paramString, DeserializationContext paramDeserializationContext) {
    return Float.valueOf((float)_parseDouble(paramString));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdKeyDeserializer$FloatKD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */