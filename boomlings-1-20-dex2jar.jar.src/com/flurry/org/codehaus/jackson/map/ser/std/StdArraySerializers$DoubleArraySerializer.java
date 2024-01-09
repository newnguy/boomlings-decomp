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
public final class StdArraySerializers$DoubleArraySerializer extends StdArraySerializers$ArraySerializerBase {
  public StdArraySerializers$DoubleArraySerializer() {
    super(double[].class, (TypeSerializer)null, (BeanProperty)null);
  }
  
  public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer paramTypeSerializer) {
    return this;
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    ObjectNode objectNode = createSchemaNode("array", true);
    objectNode.put("items", (JsonNode)createSchemaNode("number"));
    return (JsonNode)objectNode;
  }
  
  public void serializeContents(double[] paramArrayOfdouble, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    byte b = 0;
    int i = paramArrayOfdouble.length;
    while (b < i) {
      paramJsonGenerator.writeNumber(paramArrayOfdouble[b]);
      b++;
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\StdArraySerializers$DoubleArraySerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */