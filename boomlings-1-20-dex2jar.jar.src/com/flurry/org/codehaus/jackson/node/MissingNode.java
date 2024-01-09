package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;

public final class MissingNode extends BaseJsonNode {
  private static final MissingNode instance = new MissingNode();
  
  public static MissingNode getInstance() {
    return instance;
  }
  
  public double asDouble(double paramDouble) {
    return 0.0D;
  }
  
  public int asInt(int paramInt) {
    return 0;
  }
  
  public long asLong(long paramLong) {
    return 0L;
  }
  
  public String asText() {
    return "";
  }
  
  public JsonToken asToken() {
    return JsonToken.NOT_AVAILABLE;
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject == this);
  }
  
  public boolean isMissingNode() {
    return true;
  }
  
  public JsonNode path(int paramInt) {
    return this;
  }
  
  public JsonNode path(String paramString) {
    return this;
  }
  
  public final void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeNull();
  }
  
  public void serializeWithType(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer) {
    paramJsonGenerator.writeNull();
  }
  
  public String toString() {
    return "";
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\node\MissingNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */