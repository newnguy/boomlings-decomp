package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;

public class StdKeySerializers$StringKeySerializer extends SerializerBase {
  public StdKeySerializers$StringKeySerializer() {
    super(String.class);
  }
  
  public void serialize(String paramString, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeFieldName(paramString);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\StdKeySerializers$StringKeySerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */