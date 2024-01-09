package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.node.ObjectNode;
import java.lang.reflect.Type;

@JacksonStdImpl
public final class StdArraySerializers$ByteArraySerializer extends SerializerBase {
  public StdArraySerializers$ByteArraySerializer() {
    super(byte[].class);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    ObjectNode objectNode = createSchemaNode("array", true);
    objectNode.put("items", (JsonNode)createSchemaNode("string"));
    return (JsonNode)objectNode;
  }
  
  public void serialize(byte[] paramArrayOfbyte, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeBinary(paramArrayOfbyte);
  }
  
  public void serializeWithType(byte[] paramArrayOfbyte, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer) {
    paramTypeSerializer.writeTypePrefixForScalar(paramArrayOfbyte, paramJsonGenerator);
    paramJsonGenerator.writeBinary(paramArrayOfbyte);
    paramTypeSerializer.writeTypeSuffixForScalar(paramArrayOfbyte, paramJsonGenerator);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\StdArraySerializers$ByteArraySerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */