package com.flurry.org.codehaus.jackson.node;

import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: classes.dex */
public class JsonNodeFactory {
    public static final JsonNodeFactory instance = new JsonNodeFactory();

    protected JsonNodeFactory() {
    }

    public POJONode POJONode(Object obj) {
        return new POJONode(obj);
    }

    public ArrayNode arrayNode() {
        return new ArrayNode(this);
    }

    public BinaryNode binaryNode(byte[] bArr) {
        return BinaryNode.valueOf(bArr);
    }

    public BinaryNode binaryNode(byte[] bArr, int i, int i2) {
        return BinaryNode.valueOf(bArr, i, i2);
    }

    public BooleanNode booleanNode(boolean z) {
        return z ? BooleanNode.getTrue() : BooleanNode.getFalse();
    }

    public NullNode nullNode() {
        return NullNode.getInstance();
    }

    public NumericNode numberNode(byte b) {
        return IntNode.valueOf(b);
    }

    public NumericNode numberNode(double d) {
        return DoubleNode.valueOf(d);
    }

    public NumericNode numberNode(float f) {
        return DoubleNode.valueOf(f);
    }

    public NumericNode numberNode(int i) {
        return IntNode.valueOf(i);
    }

    public NumericNode numberNode(long j) {
        return LongNode.valueOf(j);
    }

    public NumericNode numberNode(BigDecimal bigDecimal) {
        return DecimalNode.valueOf(bigDecimal);
    }

    public NumericNode numberNode(BigInteger bigInteger) {
        return BigIntegerNode.valueOf(bigInteger);
    }

    public NumericNode numberNode(short s) {
        return IntNode.valueOf(s);
    }

    public ValueNode numberNode(Byte b) {
        return b == null ? nullNode() : IntNode.valueOf(b.intValue());
    }

    public ValueNode numberNode(Double d) {
        return d == null ? nullNode() : DoubleNode.valueOf(d.doubleValue());
    }

    public ValueNode numberNode(Float f) {
        return f == null ? nullNode() : DoubleNode.valueOf(f.doubleValue());
    }

    public ValueNode numberNode(Integer num) {
        return num == null ? nullNode() : IntNode.valueOf(num.intValue());
    }

    public ValueNode numberNode(Long l) {
        return l == null ? nullNode() : LongNode.valueOf(l.longValue());
    }

    public ValueNode numberNode(Short sh) {
        return sh == null ? nullNode() : IntNode.valueOf(sh.shortValue());
    }

    public ObjectNode objectNode() {
        return new ObjectNode(this);
    }

    public TextNode textNode(String str) {
        return TextNode.valueOf(str);
    }
}
