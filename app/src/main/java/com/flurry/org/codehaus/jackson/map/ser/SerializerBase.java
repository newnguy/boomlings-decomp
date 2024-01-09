package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.type.JavaType;

@Deprecated
/* loaded from: classes.dex */
public abstract class SerializerBase extends com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase {
    protected SerializerBase(JavaType javaType) {
        super(javaType);
    }

    public SerializerBase(Class cls) {
        super(cls);
    }

    protected SerializerBase(Class cls, boolean z) {
        super(cls, z);
    }
}
