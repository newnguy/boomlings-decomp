package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.deser.std.FromStringDeserializer;
import javax.xml.namespace.QName;

public class CoreXMLDeserializers$QNameDeserializer extends FromStringDeserializer {
  public CoreXMLDeserializers$QNameDeserializer() {
    super(QName.class);
  }
  
  protected QName _deserialize(String paramString, DeserializationContext paramDeserializationContext) {
    return QName.valueOf(paramString);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ext\CoreXMLDeserializers$QNameDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */