package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;

public abstract class ValueNode extends BaseJsonNode {
  public abstract JsonToken asToken();
  
  public boolean isValueNode() {
    return true;
  }
  
  public JsonNode path(int paramInt) {
    return MissingNode.getInstance();
  }
  
  public JsonNode path(String paramString) {
    return MissingNode.getInstance();
  }
  
  public void serializeWithType(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer) {
    paramTypeSerializer.writeTypePrefixForScalar(this, paramJsonGenerator);
    serialize(paramJsonGenerator, paramSerializerProvider);
    paramTypeSerializer.writeTypeSuffixForScalar(this, paramJsonGenerator);
  }
  
  public String toString() {
    return asText();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\node\ValueNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */