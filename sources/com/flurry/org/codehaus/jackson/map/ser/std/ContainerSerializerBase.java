package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.map.TypeSerializer;

/* loaded from: classes.dex */
public abstract class ContainerSerializerBase extends SerializerBase {
    /* JADX INFO: Access modifiers changed from: protected */
    public ContainerSerializerBase(Class cls) {
        super(cls);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ContainerSerializerBase(Class cls, boolean z) {
        super(cls, z);
    }

    public abstract ContainerSerializerBase _withValueTypeSerializer(TypeSerializer typeSerializer);

    public ContainerSerializerBase withValueTypeSerializer(TypeSerializer typeSerializer) {
        return typeSerializer == null ? this : _withValueTypeSerializer(typeSerializer);
    }
}
