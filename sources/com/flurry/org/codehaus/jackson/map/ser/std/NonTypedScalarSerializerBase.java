package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;

/* loaded from: classes.dex */
public abstract class NonTypedScalarSerializerBase extends ScalarSerializerBase {
    /* JADX INFO: Access modifiers changed from: protected */
    public NonTypedScalarSerializerBase(Class cls) {
        super(cls);
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.std.ScalarSerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
    public final void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        serialize(obj, jsonGenerator, serializerProvider);
    }
}
