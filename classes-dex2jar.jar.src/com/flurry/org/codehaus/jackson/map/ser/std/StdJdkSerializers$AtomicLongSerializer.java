package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicLong;

public final class StdJdkSerializers$AtomicLongSerializer extends ScalarSerializerBase {
  public StdJdkSerializers$AtomicLongSerializer() {
    super(AtomicLong.class, false);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    return (JsonNode)createSchemaNode("integer", true);
  }
  
  public void serialize(AtomicLong paramAtomicLong, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeNumber(paramAtomicLong.get());
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\StdJdkSerializers$AtomicLongSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */