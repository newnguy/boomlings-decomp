package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import java.nio.charset.Charset;

public class FromStringDeserializer$CharsetDeserializer extends FromStringDeserializer {
  public FromStringDeserializer$CharsetDeserializer() {
    super(Charset.class);
  }
  
  protected Charset _deserialize(String paramString, DeserializationContext paramDeserializationContext) {
    return Charset.forName(paramString);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\FromStringDeserializer$CharsetDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */