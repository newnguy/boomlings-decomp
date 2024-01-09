package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase;
import java.lang.reflect.Type;

@JacksonStdImpl
public final class StdSerializers$FloatSerializer extends ScalarSerializerBase {
  static final StdSerializers$FloatSerializer instance = new StdSerializers$FloatSerializer();
  
  public StdSerializers$FloatSerializer() {
    super(Float.class);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    return (JsonNode)createSchemaNode("number", true);
  }
  
  public void serialize(Float paramFloat, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeNumber(paramFloat.floatValue());
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\StdSerializers$FloatSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */