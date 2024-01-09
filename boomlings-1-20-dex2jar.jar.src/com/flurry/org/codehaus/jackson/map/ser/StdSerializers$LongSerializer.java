package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase;
import java.lang.reflect.Type;

@JacksonStdImpl
public final class StdSerializers$LongSerializer extends ScalarSerializerBase {
  static final StdSerializers$LongSerializer instance = new StdSerializers$LongSerializer();
  
  public StdSerializers$LongSerializer() {
    super(Long.class);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    return (JsonNode)createSchemaNode("number", true);
  }
  
  public void serialize(Long paramLong, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeNumber(paramLong.longValue());
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\StdSerializers$LongSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */