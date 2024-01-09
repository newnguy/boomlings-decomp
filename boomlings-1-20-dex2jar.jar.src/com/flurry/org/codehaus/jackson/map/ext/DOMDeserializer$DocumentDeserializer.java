package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import org.w3c.dom.Document;

public class DOMDeserializer$DocumentDeserializer extends DOMDeserializer {
  public DOMDeserializer$DocumentDeserializer() {
    super(Document.class);
  }
  
  public Document _deserialize(String paramString, DeserializationContext paramDeserializationContext) {
    return parse(paramString);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ext\DOMDeserializer$DocumentDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */