package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.JsonGenerator;

/* loaded from: classes.dex */
public interface JsonSerializableWithType extends JsonSerializable {
    void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer);
}
