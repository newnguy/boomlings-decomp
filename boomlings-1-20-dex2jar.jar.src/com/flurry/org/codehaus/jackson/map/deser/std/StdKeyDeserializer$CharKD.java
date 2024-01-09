package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;

final class StdKeyDeserializer$CharKD extends StdKeyDeserializer {
  StdKeyDeserializer$CharKD() {
    super(Character.class);
  }
  
  public Character _parse(String paramString, DeserializationContext paramDeserializationContext) {
    if (paramString.length() == 1)
      return Character.valueOf(paramString.charAt(0)); 
    throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "can only convert 1-character Strings");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdKeyDeserializer$CharKD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */