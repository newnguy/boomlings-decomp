package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;

public final class POJONode extends ValueNode {
  protected final Object _value;
  
  public POJONode(Object paramObject) {
    this._value = paramObject;
  }
  
  public boolean asBoolean(boolean paramBoolean) {
    boolean bool = paramBoolean;
    if (this._value != null) {
      bool = paramBoolean;
      if (this._value instanceof Boolean)
        bool = ((Boolean)this._value).booleanValue(); 
    } 
    return bool;
  }
  
  public double asDouble(double paramDouble) {
    if (this._value instanceof Number)
      paramDouble = ((Number)this._value).doubleValue(); 
    return paramDouble;
  }
  
  public int asInt(int paramInt) {
    if (this._value instanceof Number)
      paramInt = ((Number)this._value).intValue(); 
    return paramInt;
  }
  
  public long asLong(long paramLong) {
    if (this._value instanceof Number)
      paramLong = ((Number)this._value).longValue(); 
    return paramLong;
  }
  
  public String asText() {
    return (this._value == null) ? "null" : this._value.toString();
  }
  
  public JsonToken asToken() {
    return JsonToken.VALUE_EMBEDDED_OBJECT;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject != this) {
      if (paramObject == null)
        return false; 
      if (paramObject.getClass() != getClass())
        return false; 
      paramObject = paramObject;
      if (this._value == null) {
        if (((POJONode)paramObject)._value != null)
          bool = false; 
        return bool;
      } 
      bool = this._value.equals(((POJONode)paramObject)._value);
    } 
    return bool;
  }
  
  public byte[] getBinaryValue() {
    return (this._value instanceof byte[]) ? (byte[])this._value : super.getBinaryValue();
  }
  
  public Object getPojo() {
    return this._value;
  }
  
  public int hashCode() {
    return this._value.hashCode();
  }
  
  public boolean isPojo() {
    return true;
  }
  
  public final void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    if (this._value == null) {
      paramJsonGenerator.writeNull();
      return;
    } 
    paramJsonGenerator.writeObject(this._value);
  }
  
  public String toString() {
    return String.valueOf(this._value);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\node\POJONode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */