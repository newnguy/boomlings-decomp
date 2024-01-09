package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;

final class StdKeyDeserializer$IntKD extends StdKeyDeserializer {
  StdKeyDeserializer$IntKD() {
    super(Integer.class);
  }
  
  public Integer _parse(String paramString, DeserializationContext paramDeserializationContext) {
    return Integer.valueOf(_parseInt(paramString));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdKeyDeserializer$IntKD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */