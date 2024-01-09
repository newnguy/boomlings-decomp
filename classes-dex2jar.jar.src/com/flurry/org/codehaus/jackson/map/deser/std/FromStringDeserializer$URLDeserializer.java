package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import java.net.URL;

public class FromStringDeserializer$URLDeserializer extends FromStringDeserializer {
  public FromStringDeserializer$URLDeserializer() {
    super(URL.class);
  }
  
  protected URL _deserialize(String paramString, DeserializationContext paramDeserializationContext) {
    return new URL(paramString);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\FromStringDeserializer$URLDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */