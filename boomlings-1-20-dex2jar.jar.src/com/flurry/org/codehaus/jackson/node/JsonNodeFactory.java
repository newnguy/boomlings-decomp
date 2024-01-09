package com.flurry.org.codehaus.jackson.node;

import java.math.BigDecimal;
import java.math.BigInteger;

public class JsonNodeFactory {
  public static final JsonNodeFactory instance = new JsonNodeFactory();
  
  public POJONode POJONode(Object paramObject) {
    return new POJONode(paramObject);
  }
  
  public ArrayNode arrayNode() {
    return new ArrayNode(this);
  }
  
  public BinaryNode binaryNode(byte[] paramArrayOfbyte) {
    return BinaryNode.valueOf(paramArrayOfbyte);
  }
  
  public BinaryNode binaryNode(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    return BinaryNode.valueOf(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public BooleanNode booleanNode(boolean paramBoolean) {
    return paramBoolean ? BooleanNode.getTrue() : BooleanNode.getFalse();
  }
  
  public NullNode nullNode() {
    return NullNode.getInstance();
  }
  
  public NumericNode numberNode(byte paramByte) {
    return IntNode.valueOf(paramByte);
  }
  
  public NumericNode numberNode(double paramDouble) {
    return DoubleNode.valueOf(paramDouble);
  }
  
  public NumericNode numberNode(float paramFloat) {
    return DoubleNode.valueOf(paramFloat);
  }
  
  public NumericNode numberNode(int paramInt) {
    return IntNode.valueOf(paramInt);
  }
  
  public NumericNode numberNode(long paramLong) {
    return LongNode.valueOf(paramLong);
  }
  
  public NumericNode numberNode(BigDecimal paramBigDecimal) {
    return DecimalNode.valueOf(paramBigDecimal);
  }
  
  public NumericNode numberNode(BigInteger paramBigInteger) {
    return BigIntegerNode.valueOf(paramBigInteger);
  }
  
  public NumericNode numberNode(short paramShort) {
    return IntNode.valueOf(paramShort);
  }
  
  public ValueNode numberNode(Byte paramByte) {
    return (ValueNode)((paramByte == null) ? nullNode() : IntNode.valueOf(paramByte.intValue()));
  }
  
  public ValueNode numberNode(Double paramDouble) {
    return (ValueNode)((paramDouble == null) ? nullNode() : DoubleNode.valueOf(paramDouble.doubleValue()));
  }
  
  public ValueNode numberNode(Float paramFloat) {
    return (ValueNode)((paramFloat == null) ? nullNode() : DoubleNode.valueOf(paramFloat.doubleValue()));
  }
  
  public ValueNode numberNode(Integer paramInteger) {
    return (ValueNode)((paramInteger == null) ? nullNode() : IntNode.valueOf(paramInteger.intValue()));
  }
  
  public ValueNode numberNode(Long paramLong) {
    return (ValueNode)((paramLong == null) ? nullNode() : LongNode.valueOf(paramLong.longValue()));
  }
  
  public ValueNode numberNode(Short paramShort) {
    return (ValueNode)((paramShort == null) ? nullNode() : IntNode.valueOf(paramShort.shortValue()));
  }
  
  public ObjectNode objectNode() {
    return new ObjectNode(this);
  }
  
  public TextNode textNode(String paramString) {
    return TextNode.valueOf(paramString);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\node\JsonNodeFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */