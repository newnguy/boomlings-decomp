package com.flurry.org.codehaus.jackson;

import com.flurry.org.codehaus.jackson.type.JavaType;
import com.flurry.org.codehaus.jackson.type.TypeReference;
import java.util.Iterator;

/* loaded from: classes.dex */
public abstract class ObjectCodec {
    public abstract JsonNode createArrayNode();

    public abstract JsonNode createObjectNode();

    public abstract JsonNode readTree(JsonParser jsonParser);

    public abstract Object readValue(JsonParser jsonParser, JavaType javaType);

    public abstract Object readValue(JsonParser jsonParser, TypeReference typeReference);

    public abstract Object readValue(JsonParser jsonParser, Class cls);

    public abstract Iterator readValues(JsonParser jsonParser, JavaType javaType);

    public abstract Iterator readValues(JsonParser jsonParser, TypeReference typeReference);

    public abstract Iterator readValues(JsonParser jsonParser, Class cls);

    public abstract JsonParser treeAsTokens(JsonNode jsonNode);

    public abstract Object treeToValue(JsonNode jsonNode, Class cls);

    public abstract void writeTree(JsonGenerator jsonGenerator, JsonNode jsonNode);

    public abstract void writeValue(JsonGenerator jsonGenerator, Object obj);
}
