package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.util.TokenBuffer;
import java.lang.reflect.Type;

@JacksonStdImpl
public class TokenBufferSerializer extends SerializerBase {
  public TokenBufferSerializer() {
    super(TokenBuffer.class);
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    return (JsonNode)createSchemaNode("any", true);
  }
  
  public void serialize(TokenBuffer paramTokenBuffer, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramTokenBuffer.serialize(paramJsonGenerator);
  }
  
  public final void serializeWithType(TokenBuffer paramTokenBuffer, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer) {
    paramTypeSerializer.writeTypePrefixForScalar(paramTokenBuffer, paramJsonGenerator);
    serialize(paramTokenBuffer, paramJsonGenerator, paramSerializerProvider);
    paramTypeSerializer.writeTypeSuffixForScalar(paramTokenBuffer, paramJsonGenerator);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\TokenBufferSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */