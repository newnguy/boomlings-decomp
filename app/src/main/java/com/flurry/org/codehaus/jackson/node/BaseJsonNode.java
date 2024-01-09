package com.flurry.org.codehaus.jackson.node;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.JsonSerializableWithType;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import java.util.List;

/* loaded from: classes.dex */
public abstract class BaseJsonNode extends JsonNode implements JsonSerializableWithType {
    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public abstract JsonToken asToken();

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public ObjectNode findParent(String str) {
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public List findParents(String str, List list) {
        return list;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public final JsonNode findPath(String str) {
        JsonNode findValue = findValue(str);
        return findValue == null ? MissingNode.getInstance() : findValue;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public JsonNode findValue(String str) {
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public List findValues(String str, List list) {
        return list;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public List findValuesAsText(String str, List list) {
        return list;
    }

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public JsonParser.NumberType getNumberType() {
        return null;
    }

    public abstract void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider);

    public abstract void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer);

    @Override // com.flurry.org.codehaus.jackson.JsonNode
    public JsonParser traverse() {
        return new TreeTraversingParser(this);
    }
}
