package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import java.net.URI;

public class FromStringDeserializer$URIDeserializer extends FromStringDeserializer {
  public FromStringDeserializer$URIDeserializer() {
    super(URI.class);
  }
  
  protected URI _deserialize(String paramString, DeserializationContext paramDeserializationContext) {
    return URI.create(paramString);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\FromStringDeserializer$URIDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */