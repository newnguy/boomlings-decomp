package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.apache.avro.file.DataFileConstants;
import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;

/* loaded from: classes.dex */
public final class NullNode extends ValueNode {
    public static final NullNode instance = new NullNode();

    private NullNode() {
    }

    public static NullNode getInstance() {
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
        return DataFileConstants.NULL_CODEC;
    }

    @Override // com.flurry.org.codehaus.jackson.node.ValueNode, com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public JsonToken asToken() {
        return JsonToken.VALUE_NULL;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean isNull() {
        return true;
    }

    @Override // com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeNull();
    }
}
