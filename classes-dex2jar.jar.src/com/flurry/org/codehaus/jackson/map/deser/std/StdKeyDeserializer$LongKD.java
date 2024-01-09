package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;

final class StdKeyDeserializer$LongKD extends StdKeyDeserializer {
  StdKeyDeserializer$LongKD() {
    super(Long.class);
  }
  
  public Long _parse(String paramString, DeserializationContext paramDeserializationContext) {
    return Long.valueOf(_parseLong(paramString));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdKeyDeserializer$LongKD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */