package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.map.TypeSerializer;

/* loaded from: classes.dex */
public abstract class ContainerSerializerBase extends SerializerBase {
    public ContainerSerializerBase(Class cls) {
        super(cls);
    }

    public ContainerSerializerBase(Class cls, boolean z) {
        super(cls, z);
    }

    public abstract ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeSerializer);

    public ContainerSerializerBase withValueTypeSerializer(TypeSerializer typeSerializer) {
        return typeSerializer == null ? this : _withValueTypeSerializer(typeSerializer);
    }
}
