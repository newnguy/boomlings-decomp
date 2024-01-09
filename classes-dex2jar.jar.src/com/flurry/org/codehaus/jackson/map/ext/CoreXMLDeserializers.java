package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.map.deser.std.StdDeserializer;
import com.flurry.org.codehaus.jackson.map.util.Provider;
import java.util.Arrays;
import java.util.Collection;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

public class CoreXMLDeserializers implements Provider {
  static final DatatypeFactory _dataTypeFactory;
  
  static {
    try {
      _dataTypeFactory = DatatypeFactory.newInstance();
      return;
    } catch (DatatypeConfigurationException datatypeConfigurationException) {
      throw new RuntimeException(datatypeConfigurationException);
    } 
  }
  
  public Collection provide() {
    return Arrays.asList(new StdDeserializer[] { (StdDeserializer)new CoreXMLDeserializers$DurationDeserializer(), (StdDeserializer)new CoreXMLDeserializers$GregorianCalendarDeserializer(), (StdDeserializer)new CoreXMLDeserializers$QNameDeserializer() });
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ext\CoreXMLDeserializers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */