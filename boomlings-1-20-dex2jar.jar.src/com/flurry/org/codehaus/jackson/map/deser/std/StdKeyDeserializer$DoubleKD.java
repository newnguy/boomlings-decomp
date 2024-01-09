package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;

final class StdKeyDeserializer$DoubleKD extends StdKeyDeserializer {
  StdKeyDeserializer$DoubleKD() {
    super(Double.class);
  }
  
  public Double _parse(String paramString, DeserializationContext paramDeserializationContext) {
    return Double.valueOf(_parseDouble(paramString));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdKeyDeserializer$DoubleKD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */