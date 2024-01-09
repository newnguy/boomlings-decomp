package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.JsonParser;

/* loaded from: classes.dex */
public abstract class JsonDeserializer {

    /* loaded from: classes.dex */
    public abstract class None extends JsonDeserializer {
    }

    public abstract Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext);

    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
        throw new UnsupportedOperationException();
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    public Object getEmptyValue() {
        return getNullValue();
    }

    public Object getNullValue() {
        return null;
    }

    public JsonDeserializer unwrappingDeserializer() {
        return this;
    }
}
