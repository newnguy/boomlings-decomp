package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;

/* loaded from: classes.dex */
public final class MissingNode extends BaseJsonNode {
    private static final MissingNode instance = new MissingNode();

    private MissingNode() {
    }

    public static MissingNode getInstance() {
        return instance;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public double asDouble(double d) {
        return 0.0d;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public int asInt(int i) {
        return 0;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public long asLong(long j) {
        return 0L;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public String asText() {
        return "";
    }

    @Override // com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public JsonToken asToken() {
        return JsonToken.NOT_AVAILABLE;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean isMissingNode() {
        return true;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public JsonNode path(int i) {
        return this;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public JsonNode path(String str) {
        return this;
    }

    @Override // com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeNull();
    }

    @Override // com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.map.JsonSerializableWithType
    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        jsonGenerator.writeNull();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public String toString() {
        return "";
    }
}
