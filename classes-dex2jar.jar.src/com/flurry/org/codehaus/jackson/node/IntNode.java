package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.io.NumberOutput;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import java.math.BigDecimal;
import java.math.BigInteger;

public final class IntNode extends NumericNode {
  private static final IntNode[] CANONICALS = new IntNode[12];
  
  static final int MAX_CANONICAL = 10;
  
  static final int MIN_CANONICAL = -1;
  
  final int _value;
  
  static {
    for (byte b = 0; b < 12; b++)
      CANONICALS[b] = new IntNode(b - 1); 
  }
  
  public IntNode(int paramInt) {
    this._value = paramInt;
  }
  
  public static IntNode valueOf(int paramInt) {
    return (paramInt > 10 || paramInt < -1) ? new IntNode(paramInt) : CANONICALS[paramInt + 1];
  }
  
  public boolean asBoolean(boolean paramBoolean) {
    return (this._value != 0);
  }
  
  public String asText() {
    return NumberOutput.toString(this._value);
  }
  
  public JsonToken asToken() {
    return JsonToken.VALUE_NUMBER_INT;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject != this) {
      if (paramObject == null)
        return false; 
      if (paramObject.getClass() != getClass())
        return false; 
      if (((IntNode)paramObject)._value != this._value)
        bool = false; 
    } 
    return bool;
  }
  
  public BigInteger getBigIntegerValue() {
    return BigInteger.valueOf(this._value);
  }
  
  public BigDecimal getDecimalValue() {
    return BigDecimal.valueOf(this._value);
  }
  
  public double getDoubleValue() {
    return this._value;
  }
  
  public int getIntValue() {
    return this._value;
  }
  
  public long getLongValue() {
    return this._value;
  }
  
  public JsonParser.NumberType getNumberType() {
    return JsonParser.NumberType.INT;
  }
  
  public Number getNumberValue() {
    return Integer.valueOf(this._value);
  }
  
  public int hashCode() {
    return this._value;
  }
  
  public boolean isInt() {
    return true;
  }
  
  public boolean isIntegralNumber() {
    return true;
  }
  
  public final void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeNumber(this._value);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\node\IntNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */