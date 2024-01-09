package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.KeyDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Constructor;

@Deprecated
/* loaded from: classes.dex */
public class MapDeserializer extends com.flurry.org.codehaus.jackson.map.deser.std.MapDeserializer {
    protected MapDeserializer(MapDeserializer mapDeserializer) {
        super(mapDeserializer);
    }

    public MapDeserializer(JavaType javaType, ValueInstantiator valueInstantiator, KeyDeserializer keyDeserializer, JsonDeserializer jsonDeserializer, TypeDeserializer typeDeserializer) {
        super(javaType, valueInstantiator, keyDeserializer, jsonDeserializer, typeDeserializer);
    }

    @Deprecated
    public MapDeserializer(JavaType javaType, Constructor constructor, KeyDeserializer keyDeserializer, JsonDeserializer jsonDeserializer, TypeDeserializer typeDeserializer) {
        super(javaType, constructor, keyDeserializer, jsonDeserializer, typeDeserializer);
    }
}
