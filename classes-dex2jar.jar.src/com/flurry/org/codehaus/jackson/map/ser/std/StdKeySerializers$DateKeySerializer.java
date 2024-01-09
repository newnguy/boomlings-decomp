package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import java.util.Date;

public class StdKeySerializers$DateKeySerializer extends SerializerBase {
  protected static final JsonSerializer instance = new StdKeySerializers$DateKeySerializer();
  
  public StdKeySerializers$DateKeySerializer() {
    super(Date.class);
  }
  
  public void serialize(Date paramDate, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramSerializerProvider.defaultSerializeDateKey(paramDate, paramJsonGenerator);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\StdKeySerializers$DateKeySerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */