package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;

public final class BooleanNode extends ValueNode {
  public static final BooleanNode FALSE;
  
  public static final BooleanNode TRUE = new BooleanNode();
  
  static {
    FALSE = new BooleanNode();
  }
  
  public static BooleanNode getFalse() {
    return FALSE;
  }
  
  public static BooleanNode getTrue() {
    return TRUE;
  }
  
  public static BooleanNode valueOf(boolean paramBoolean) {
    return paramBoolean ? TRUE : FALSE;
  }
  
  public boolean asBoolean() {
    return (this == TRUE);
  }
  
  public boolean asBoolean(boolean paramBoolean) {
    return (this == TRUE);
  }
  
  public double asDouble(double paramDouble) {
    return (this == TRUE) ? 1.0D : 0.0D;
  }
  
  public int asInt(int paramInt) {
    return (this == TRUE) ? 1 : 0;
  }
  
  public long asLong(long paramLong) {
    return (this == TRUE) ? 1L : 0L;
  }
  
  public String asText() {
    return (this == TRUE) ? "true" : "false";
  }
  
  public JsonToken asToken() {
    return (this == TRUE) ? JsonToken.VALUE_TRUE : JsonToken.VALUE_FALSE;
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject == this);
  }
  
  public boolean getBooleanValue() {
    return (this == TRUE);
  }
  
  public boolean isBoolean() {
    return true;
  }
  
  public final void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    boolean bool;
    if (this == TRUE) {
      bool = true;
    } else {
      bool = false;
    } 
    paramJsonGenerator.writeBoolean(bool);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\node\BooleanNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */