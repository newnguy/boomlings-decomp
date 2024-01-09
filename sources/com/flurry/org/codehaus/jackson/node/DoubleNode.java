package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.io.NumberOutput;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: classes.dex */
public final class DoubleNode extends NumericNode {
    protected final double _value;

    public DoubleNode(double d) {
        this._value = d;
    }

    public static DoubleNode valueOf(double d) {
        return new DoubleNode(d);
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.JsonNode
    public String asText() {
        return NumberOutput.toString(this._value);
    }

    @Override // com.flurry.org.codehaus.jackson.node.ValueNode, com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public JsonToken asToken() {
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && obj.getClass() == getClass() && ((DoubleNode) obj)._value == this._value;
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.JsonNode
    public BigInteger getBigIntegerValue() {
        return getDecimalValue().toBigInteger();
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.JsonNode
    public BigDecimal getDecimalValue() {
        return BigDecimal.valueOf(this._value);
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.JsonNode
    public double getDoubleValue() {
        return this._value;
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.JsonNode
    public int getIntValue() {
        return (int) this._value;
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.JsonNode
    public long getLongValue() {
        return (long) this._value;
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public JsonParser.NumberType getNumberType() {
        return JsonParser.NumberType.DOUBLE;
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.JsonNode
    public Number getNumberValue() {
        return Double.valueOf(this._value);
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this._value);
        return ((int) (doubleToLongBits >> 32)) ^ ((int) doubleToLongBits);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean isDouble() {
        return true;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean isFloatingPointNumber() {
        return true;
    }

    @Override // com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeNumber(this._value);
    }
}
