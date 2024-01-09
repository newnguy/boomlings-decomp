package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicReference;

public final class StdJdkSerializers$AtomicReferenceSerializer extends SerializerBase {
  public StdJdkSerializers$AtomicReferenceSerializer() {
    super(AtomicReference.class, false);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    return (JsonNode)createSchemaNode("any", true);
  }
  
  public void serialize(AtomicReference paramAtomicReference, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramSerializerProvider.defaultSerializeValue(paramAtomicReference.get(), paramJsonGenerator);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\StdJdkSerializers$AtomicReferenceSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */