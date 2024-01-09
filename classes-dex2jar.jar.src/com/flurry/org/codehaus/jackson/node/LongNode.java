package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.io.NumberOutput;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import java.math.BigDecimal;
import java.math.BigInteger;

public final class LongNode extends NumericNode {
  final long _value;
  
  public LongNode(long paramLong) {
    this._value = paramLong;
  }
  
  public static LongNode valueOf(long paramLong) {
    return new LongNode(paramLong);
  }
  
  public boolean asBoolean(boolean paramBoolean) {
    return (this._value != 0L);
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
      if (((LongNode)paramObject)._value != this._value)
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
    return (int)this._value;
  }
  
  public long getLongValue() {
    return this._value;
  }
  
  public JsonParser.NumberType getNumberType() {
    return JsonParser.NumberType.LONG;
  }
  
  public Number getNumberValue() {
    return Long.valueOf(this._value);
  }
  
  public int hashCode() {
    return (int)this._value ^ (int)(this._value >> 32L);
  }
  
  public boolean isIntegralNumber() {
    return true;
  }
  
  public boolean isLong() {
    return true;
  }
  
  public final void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeNumber(this._value);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\node\LongNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */