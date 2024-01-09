package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: classes.dex */
public final class DecimalNode extends NumericNode {
    protected final BigDecimal _value;

    public DecimalNode(BigDecimal bigDecimal) {
        this._value = bigDecimal;
    }

    public static DecimalNode valueOf(BigDecimal bigDecimal) {
        return new DecimalNode(bigDecimal);
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.JsonNode
    public String asText() {
        return this._value.toString();
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
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return ((DecimalNode) obj)._value.equals(this._value);
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.JsonNode
    public BigInteger getBigIntegerValue() {
        return this._value.toBigInteger();
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.JsonNode
    public BigDecimal getDecimalValue() {
        return this._value;
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
        return JsonParser.NumberType.BIG_DECIMAL;
    }

    @Override // com.flurry.org.codehaus.jackson.node.NumericNode, com.flurry.org.codehaus.jackson.JsonNode
    public Number getNumberValue() {
        return this._value;
    }

    public int hashCode() {
        return this._value.hashCode();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean isBigDecimal() {
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
