package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.node.ObjectNode;
import java.lang.reflect.Type;

@JacksonStdImpl
public final class StdArraySerializers$FloatArraySerializer extends StdArraySerializers$ArraySerializerBase {
  public StdArraySerializers$FloatArraySerializer() {
    this((TypeSerializer)null);
  }
  
  public StdArraySerializers$FloatArraySerializer(TypeSerializer paramTypeSerializer) {
    super(float[].class, paramTypeSerializer, (BeanProperty)null);
  }
  
  public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer paramTypeSerializer) {
    return new StdArraySerializers$FloatArraySerializer(paramTypeSerializer);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    ObjectNode objectNode = createSchemaNode("array", true);
    objectNode.put("items", (JsonNode)createSchemaNode("number"));
    return (JsonNode)objectNode;
  }
  
  public void serializeContents(float[] paramArrayOffloat, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    byte b = 0;
    int i = paramArrayOffloat.length;
    while (b < i) {
      paramJsonGenerator.writeNumber(paramArrayOffloat[b]);
      b++;
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\StdArraySerializers$FloatArraySerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */