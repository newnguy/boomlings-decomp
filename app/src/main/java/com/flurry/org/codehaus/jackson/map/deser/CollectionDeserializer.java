package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Constructor;

@Deprecated
/* loaded from: classes.dex */
public class CollectionDeserializer extends com.flurry.org.codehaus.jackson.map.deser.std.CollectionDeserializer {
    protected CollectionDeserializer(CollectionDeserializer collectionDeserializer) {
        super(collectionDeserializer);
    }

    public CollectionDeserializer(JavaType javaType, JsonDeserializer jsonDeserializer, TypeDeserializer typeDeserializer, ValueInstantiator valueInstantiator) {
        super(javaType, jsonDeserializer, typeDeserializer, valueInstantiator);
    }

    @Deprecated
    public CollectionDeserializer(JavaType javaType, JsonDeserializer jsonDeserializer, TypeDeserializer typeDeserializer, Constructor constructor) {
        super(javaType, jsonDeserializer, typeDeserializer, constructor);
    }
}
