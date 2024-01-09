package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.type.JavaType;

/* loaded from: classes.dex */
public abstract class ContainerDeserializerBase extends StdDeserializer {
    public ContainerDeserializerBase(Class cls) {
        super(cls);
    }

    public abstract JsonDeserializer getContentDeserializer();

    public abstract JavaType getContentType();
}
