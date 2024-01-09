package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;

final class StdKeyDeserializer$ShortKD extends StdKeyDeserializer {
  StdKeyDeserializer$ShortKD() {
    super(Integer.class);
  }
  
  public Short _parse(String paramString, DeserializationContext paramDeserializationContext) {
    int i = _parseInt(paramString);
    if (i < -32768 || i > 32767)
      throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "overflow, value can not be represented as 16-bit value"); 
    return Short.valueOf((short)i);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdKeyDeserializer$ShortKD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */