package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import java.util.TimeZone;

public class TimeZoneSerializer extends ScalarSerializerBase {
  public static final TimeZoneSerializer instance = new TimeZoneSerializer();
  
  public TimeZoneSerializer() {
    super(TimeZone.class);
  }
  
  public void serialize(TimeZone paramTimeZone, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeString(paramTimeZone.getID());
  }
  
  public void serializeWithType(TimeZone paramTimeZone, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer) {
    paramTypeSerializer.writeTypePrefixForScalar(paramTimeZone, paramJsonGenerator, TimeZone.class);
    serialize(paramTimeZone, paramJsonGenerator, paramSerializerProvider);
    paramTypeSerializer.writeTypeSuffixForScalar(paramTimeZone, paramJsonGenerator);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\TimeZoneSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */