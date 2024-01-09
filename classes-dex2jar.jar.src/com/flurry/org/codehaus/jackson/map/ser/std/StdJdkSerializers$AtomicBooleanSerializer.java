package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

public final class StdJdkSerializers$AtomicBooleanSerializer extends ScalarSerializerBase {
  public StdJdkSerializers$AtomicBooleanSerializer() {
    super(AtomicBoolean.class, false);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    return (JsonNode)createSchemaNode("boolean", true);
  }
  
  public void serialize(AtomicBoolean paramAtomicBoolean, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeBoolean(paramAtomicBoolean.get());
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\StdJdkSerializers$AtomicBooleanSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */