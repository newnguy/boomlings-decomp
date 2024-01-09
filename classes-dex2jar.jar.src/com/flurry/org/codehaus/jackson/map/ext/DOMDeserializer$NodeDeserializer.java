package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import org.w3c.dom.Node;

public class DOMDeserializer$NodeDeserializer extends DOMDeserializer {
  public DOMDeserializer$NodeDeserializer() {
    super(Node.class);
  }
  
  public Node _deserialize(String paramString, DeserializationContext paramDeserializationContext) {
    return parse(paramString);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ext\DOMDeserializer$NodeDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */