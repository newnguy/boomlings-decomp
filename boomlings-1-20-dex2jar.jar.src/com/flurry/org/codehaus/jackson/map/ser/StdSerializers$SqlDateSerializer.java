package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase;
import java.lang.reflect.Type;
import java.sql.Date;

@JacksonStdImpl
public final class StdSerializers$SqlDateSerializer extends ScalarSerializerBase {
  public StdSerializers$SqlDateSerializer() {
    super(Date.class);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    return (JsonNode)createSchemaNode("string", true);
  }
  
  public void serialize(Date paramDate, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeString(paramDate.toString());
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\StdSerializers$SqlDateSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */