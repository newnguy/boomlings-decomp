package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.JsonFactory;
import com.flurry.org.codehaus.jackson.ObjectCodec;
import com.flurry.org.codehaus.jackson.format.InputAccessor;
import com.flurry.org.codehaus.jackson.format.MatchStrength;

public class MappingJsonFactory extends JsonFactory {
  public MappingJsonFactory() {
    this((ObjectMapper)null);
  }
  
  public MappingJsonFactory(ObjectMapper paramObjectMapper) {
    super(paramObjectMapper);
    if (paramObjectMapper == null)
      setCodec(new ObjectMapper(this)); 
  }
  
  public final ObjectMapper getCodec() {
    return (ObjectMapper)this._objectCodec;
  }
  
  public String getFormatName() {
    return "JSON";
  }
  
  public MatchStrength hasFormat(InputAccessor paramInputAccessor) {
    return hasJSONFormat(paramInputAccessor);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\MappingJsonFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */