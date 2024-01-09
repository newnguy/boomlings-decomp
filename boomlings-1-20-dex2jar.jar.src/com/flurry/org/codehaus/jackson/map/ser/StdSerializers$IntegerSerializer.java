package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.ser.std.NonTypedScalarSerializerBase;
import java.lang.reflect.Type;

@JacksonStdImpl
public final class StdSerializers$IntegerSerializer extends NonTypedScalarSerializerBase {
  public StdSerializers$IntegerSerializer() {
    super(Integer.class);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    return (JsonNode)createSchemaNode("integer", true);
  }
  
  public void serialize(Integer paramInteger, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeNumber(paramInteger.intValue());
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\StdSerializers$IntegerSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */