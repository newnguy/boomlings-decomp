package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.apache.avro.file.DataFileConstants;
import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;

/* loaded from: classes.dex */
public final class POJONode extends ValueNode {
    protected final Object _value;

    public POJONode(Object obj) {
        this._value = obj;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean asBoolean(boolean z) {
        return (this._value == null || !(this._value instanceof Boolean)) ? z : ((Boolean) this._value).booleanValue();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public double asDouble(double d) {
        return this._value instanceof Number ? ((Number) this._value).doubleValue() : d;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public int asInt(int i) {
        return this._value instanceof Number ? ((Number) this._value).intValue() : i;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public long asLong(long j) {
        return this._value instanceof Number ? ((Number) this._value).longValue() : j;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public String asText() {
        return this._value == null ? DataFileConstants.NULL_CODEC : this._value.toString();
    }

    @Override // com.flurry.org.codehaus.jackson.node.ValueNode, com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.JsonNode
    public JsonToken asToken() {
        return JsonToken.VALUE_EMBEDDED_OBJECT;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            POJONode pOJONode = (POJONode) obj;
            return this._value == null ? pOJONode._value == null : this._value.equals(pOJONode._value);
        }
        return false;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public byte[] getBinaryValue() {
        return this._value instanceof byte[] ? (byte[]) this._value : super.getBinaryValue();
    }

    public Object getPojo() {
        return this._value;
    }

    public int hashCode() {
        return this._value.hashCode();
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public boolean isPojo() {
        return true;
    }

    @Override // com.flurry.org.codehaus.jackson.node.BaseJsonNode, com.flurry.org.codehaus.jackson.map.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (this._value == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeObject(this._value);
        }
    }

    @Override // com.flurry.org.codehaus.jackson.node.ValueNode, com.flurry.org.codehaus.jackson.JsonNode
    public String toString() {
        return String.valueOf(this._value);
    }
}
