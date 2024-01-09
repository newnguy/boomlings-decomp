package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;

final class StdKeyDeserializer$ByteKD extends StdKeyDeserializer {
  StdKeyDeserializer$ByteKD() {
    super(Byte.class);
  }
  
  public Byte _parse(String paramString, DeserializationContext paramDeserializationContext) {
    int i = _parseInt(paramString);
    if (i < -128 || i > 127)
      throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "overflow, value can not be represented as 8-bit value"); 
    return Byte.valueOf((byte)i);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdKeyDeserializer$ByteKD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */