package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonParser;
import java.math.BigDecimal;
import java.math.BigInteger;

/* loaded from: classes.dex */
public abstract class NumericNode extends ValueNode {
    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public double asDouble() {
        return getDoubleValue();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public double asDouble(double d) {
        return getDoubleValue();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public int asInt() {
        return getIntValue();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public int asInt(int i) {
        return getIntValue();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public long asLong() {
        return getLongValue();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public long asLong(long j) {
        return getLongValue();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public abstract String asText();

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public abstract BigInteger getBigIntegerValue();

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public abstract BigDecimal getDecimalValue();

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public abstract double getDoubleValue();

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public abstract int getIntValue();

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public abstract long getLongValue();

    @Override // com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public abstract JsonParser.NumberType getNumberType();

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public abstract Number getNumberValue();

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public final boolean isNumber() {
        return true;
    }
}
