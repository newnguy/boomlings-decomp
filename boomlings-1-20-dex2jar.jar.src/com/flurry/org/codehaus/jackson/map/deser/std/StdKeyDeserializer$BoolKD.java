package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;

final class StdKeyDeserializer$BoolKD extends StdKeyDeserializer {
  StdKeyDeserializer$BoolKD() {
    super(Boolean.class);
  }
  
  public Boolean _parse(String paramString, DeserializationContext paramDeserializationContext) {
    if ("true".equals(paramString))
      return Boolean.TRUE; 
    if ("false".equals(paramString))
      return Boolean.FALSE; 
    throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "value not 'true' or 'false'");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdKeyDeserializer$BoolKD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */