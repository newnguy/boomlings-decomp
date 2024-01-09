package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.deser.std.FromStringDeserializer;
import javax.xml.datatype.Duration;

public class CoreXMLDeserializers$DurationDeserializer extends FromStringDeserializer {
  public CoreXMLDeserializers$DurationDeserializer() {
    super(Duration.class);
  }
  
  protected Duration _deserialize(String paramString, DeserializationContext paramDeserializationContext) {
    return CoreXMLDeserializers._dataTypeFactory.newDuration(paramString);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ext\CoreXMLDeserializers$DurationDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */