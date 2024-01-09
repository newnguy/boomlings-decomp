package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;

public final class NullNode extends ValueNode {
  public static final NullNode instance = new NullNode();
  
  public static NullNode getInstance() {
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
    return "null";
  }
  
  public JsonToken asToken() {
    return JsonToken.VALUE_NULL;
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject == this);
  }
  
  public boolean isNull() {
    return true;
  }
  
  public final void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeNull();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\node\NullNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */