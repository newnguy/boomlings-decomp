package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;

/* loaded from: classes.dex */
public final class BooleanNode extends ValueNode {
    public static final BooleanNode TRUE = new BooleanNode();
    public static final BooleanNode FALSE = new BooleanNode();

    private BooleanNode() {
    }

    public static BooleanNode getFalse() {
        return FALSE;
    }

    public static BooleanNode getTrue() {
        return TRUE;
    }

    public static BooleanNode valueOf(boolean z) {
        return z ? TRUE : FALSE;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean asBoolean() {
        return this == TRUE;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean asBoolean(boolean z) {
        return this == TRUE;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public double asDouble(double d) {
        return this == TRUE ? 1.0d : 0.0d;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public int asInt(int i) {
        return this == TRUE ? 1 : 0;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public long asLong(long j) {
        return this == TRUE ? 1L : 0L;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public String asText() {
        return this == TRUE ? "true" : "false";
    }

    @Override // com.flurry.org.codehaus.jackson.node.ValueNode, com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public JsonToken asToken() {
        return this == TRUE ? JsonToken.VALUE_TRUE : JsonToken.VALUE_FALSE;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean getBooleanValue() {
        return this == TRUE;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean isBoolean() {
        return true;
    }

    @Override // com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeBoolean(this == TRUE);
    }
}
