package com.flurry.org.codehaus.jackson.map.ext;

import com.flurry.org.codehaus.jackson.map.ser.std.ToStringSerializer;
import com.flurry.org.codehaus.jackson.map.util.Provider;
import java.util.Collection;
import java.util.HashMap;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

public class CoreXMLSerializers implements Provider {
  static final HashMap _serializers = new HashMap<Object, Object>();
  
  static {
    ToStringSerializer toStringSerializer = ToStringSerializer.instance;
    _serializers.put(Duration.class, toStringSerializer);
    _serializers.put(XMLGregorianCalendar.class, new CoreXMLSerializers$XMLGregorianCalendarSerializer());
    _serializers.put(QName.class, toStringSerializer);
  }
  
  public Collection provide() {
    return _serializers.entrySet();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ext\CoreXMLSerializers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */