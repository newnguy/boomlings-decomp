package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.node.ObjectNode;
import java.lang.reflect.Type;

@JacksonStdImpl
public final class StdArraySerializers$CharArraySerializer extends SerializerBase {
  public StdArraySerializers$CharArraySerializer() {
    super(char[].class);
  }
  
  private final void _writeArrayContents(JsonGenerator paramJsonGenerator, char[] paramArrayOfchar) {
    byte b = 0;
    int i = paramArrayOfchar.length;
    while (b < i) {
      paramJsonGenerator.writeString(paramArrayOfchar, b, 1);
      b++;
    } 
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    ObjectNode objectNode2 = createSchemaNode("array", true);
    ObjectNode objectNode1 = createSchemaNode("string");
    objectNode1.put("type", "string");
    objectNode2.put("items", (JsonNode)objectNode1);
    return (JsonNode)objectNode2;
  }
  
  public void serialize(char[] paramArrayOfchar, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    if (paramSerializerProvider.isEnabled(SerializationConfig.Feature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
      paramJsonGenerator.writeStartArray();
      _writeArrayContents(paramJsonGenerator, paramArrayOfchar);
      paramJsonGenerator.writeEndArray();
      return;
    } 
    paramJsonGenerator.writeString(paramArrayOfchar, 0, paramArrayOfchar.length);
  }
  
  public void serializeWithType(char[] paramArrayOfchar, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer) {
    if (paramSerializerProvider.isEnabled(SerializationConfig.Feature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
      paramTypeSerializer.writeTypePrefixForArray(paramArrayOfchar, paramJsonGenerator);
      _writeArrayContents(paramJsonGenerator, paramArrayOfchar);
      paramTypeSerializer.writeTypeSuffixForArray(paramArrayOfchar, paramJsonGenerator);
      return;
    } 
    paramTypeSerializer.writeTypePrefixForScalar(paramArrayOfchar, paramJsonGenerator);
    paramJsonGenerator.writeString(paramArrayOfchar, 0, paramArrayOfchar.length);
    paramTypeSerializer.writeTypeSuffixForScalar(paramArrayOfchar, paramJsonGenerator);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\StdArraySerializers$CharArraySerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */