package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.JsonGenerator;

/* loaded from: classes.dex */
public abstract class JsonSerializer {

    /* loaded from: classes.dex */
    public abstract class None extends JsonSerializer {
    }

    public Class handledType() {
        return null;
    }

    public boolean isUnwrappingSerializer() {
        return false;
    }

    public abstract void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider);

    public void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        serialize(obj, jsonGenerator, serializerProvider);
    }

    public JsonSerializer unwrappingSerializer() {
        return this;
    }
}
