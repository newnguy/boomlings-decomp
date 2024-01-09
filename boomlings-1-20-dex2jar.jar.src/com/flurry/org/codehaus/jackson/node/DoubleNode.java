package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.io.NumberOutput;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import java.math.BigDecimal;
import java.math.BigInteger;

public final class DoubleNode extends NumericNode {
  protected final double _value;
  
  public DoubleNode(double paramDouble) {
    this._value = paramDouble;
  }
  
  public static DoubleNode valueOf(double paramDouble) {
    return new DoubleNode(paramDouble);
  }
  
  public String asText() {
    return NumberOutput.toString(this._value);
  }
  
  public JsonToken asToken() {
    return JsonToken.VALUE_NUMBER_FLOAT;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject != this) {
      if (paramObject == null)
        return false; 
      if (paramObject.getClass() != getClass())
        return false; 
      if (((DoubleNode)paramObject)._value != this._value)
        bool = false; 
    } 
    return bool;
  }
  
  public BigInteger getBigIntegerValue() {
    return getDecimalValue().toBigInteger();
  }
  
  public BigDecimal getDecimalValue() {
    return BigDecimal.valueOf(this._value);
  }
  
  public double getDoubleValue() {
    return this._value;
  }
  
  public int getIntValue() {
    return (int)this._value;
  }
  
  public long getLongValue() {
    return (long)this._value;
  }
  
  public JsonParser.NumberType getNumberType() {
    return JsonParser.NumberType.DOUBLE;
  }
  
  public Number getNumberValue() {
    return Double.valueOf(this._value);
  }
  
  public int hashCode() {
    long l = Double.doubleToLongBits(this._value);
    int i = (int)l;
    return (int)(l >> 32L) ^ i;
  }
  
  public boolean isDouble() {
    return true;
  }
  
  public boolean isFloatingPointNumber() {
    return true;
  }
  
  public final void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeNumber(this._value);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\node\DoubleNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */