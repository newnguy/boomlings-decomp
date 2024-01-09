package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: classes.dex */
public final class BigIntegerNode extends NumericNode {
    protected final BigInteger _value;

    public BigIntegerNode(BigInteger bigInteger) {
        this._value = bigInteger;
    }

    public static BigIntegerNode valueOf(BigInteger bigInteger) {
        return new BigIntegerNode(bigInteger);
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean asBoolean(boolean z) {
        return !BigInteger.ZERO.equals(this._value);
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.JsonNode
    public String asText() {
        return this._value.toString();
    }

    @Override // com.flurry.org.codehaus.jackson.node.ValueNode, com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public JsonToken asToken() {
        return JsonToken.VALUE_NUMBER_INT;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj != null && obj.getClass() == getClass() && ((BigIntegerNode) obj)._value == this._value;
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.JsonNode
    public BigInteger getBigIntegerValue() {
        return this._value;
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.JsonNode
    public BigDecimal getDecimalValue() {
        return new BigDecimal(this._value);
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.JsonNode
    public double getDoubleValue() {
        return this._value.doubleValue();
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.JsonNode
    public int getIntValue() {
        return this._value.intValue();
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.JsonNode
    public long getLongValue() {
        return this._value.longValue();
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public JsonParser.NumberType getNumberType() {
        return JsonParser.NumberType.BIG_INTEGER;
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.JsonNode
    public Number getNumberValue() {
        return this._value;
    }

    public int hashCode() {
        return this._value.hashCode();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean isBigInteger() {
        return true;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean isIntegralNumber() {
        return true;
    }

    @Override // com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeNumber(this._value);
    }
}
