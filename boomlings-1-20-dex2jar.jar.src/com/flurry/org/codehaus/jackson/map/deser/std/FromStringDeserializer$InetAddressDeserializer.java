package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import java.net.InetAddress;

public class FromStringDeserializer$InetAddressDeserializer extends FromStringDeserializer {
  public FromStringDeserializer$InetAddressDeserializer() {
    super(InetAddress.class);
  }
  
  protected InetAddress _deserialize(String paramString, DeserializationContext paramDeserializationContext) {
    return InetAddress.getByName(paramString);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\FromStringDeserializer$InetAddressDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */