package com.flurry.org.codehaus.jackson.map.ser.impl;

import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.ser.impl.SerializerCache;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.HashMap;

/* loaded from: classes.dex */
public final class ReadOnlyClassToSerializerMap {
    protected final SerializerCache.TypeKey _cacheKey = new SerializerCache.TypeKey((Class) getClass(), false);
    protected final JsonSerializerMap _map;

    private ReadOnlyClassToSerializerMap(JsonSerializerMap jsonSerializerMap) {
        this._map = jsonSerializerMap;
    }

    public static ReadOnlyClassToSerializerMap from(HashMap hashMap) {
        return new ReadOnlyClassToSerializerMap(new JsonSerializerMap(hashMap));
    }

    public ReadOnlyClassToSerializerMap instance() {
        return new ReadOnlyClassToSerializerMap(this._map);
    }

    public JsonSerializer typedValueSerializer(JavaType javaType) {
        this._cacheKey.resetTyped(javaType);
        return this._map.find(this._cacheKey);
    }

    public JsonSerializer typedValueSerializer(Class cls) {
        this._cacheKey.resetTyped(cls);
        return this._map.find(this._cacheKey);
    }

    public JsonSerializer untypedValueSerializer(JavaType javaType) {
        this._cacheKey.resetUntyped(javaType);
        return this._map.find(this._cacheKey);
    }

    public JsonSerializer untypedValueSerializer(Class cls) {
        this._cacheKey.resetUntyped(cls);
        return this._map.find(this._cacheKey);
    }
}
