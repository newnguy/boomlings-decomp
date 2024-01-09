package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import java.net.InetAddress;

public class InetAddressSerializer extends ScalarSerializerBase {
  public static final InetAddressSerializer instance = new InetAddressSerializer();
  
  public InetAddressSerializer() {
    super(InetAddress.class);
  }
  
  public void serialize(InetAddress paramInetAddress, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    String str2 = paramInetAddress.toString().trim();
    int i = str2.indexOf('/');
    String str1 = str2;
    if (i >= 0)
      if (i == 0) {
        str1 = str2.substring(1);
      } else {
        str1 = str2.substring(0, i);
      }  
    paramJsonGenerator.writeString(str1);
  }
  
  public void serializeWithType(InetAddress paramInetAddress, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer) {
    paramTypeSerializer.writeTypePrefixForScalar(paramInetAddress, paramJsonGenerator, InetAddress.class);
    serialize(paramInetAddress, paramJsonGenerator, paramSerializerProvider);
    paramTypeSerializer.writeTypeSuffixForScalar(paramInetAddress, paramJsonGenerator);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\InetAddressSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */