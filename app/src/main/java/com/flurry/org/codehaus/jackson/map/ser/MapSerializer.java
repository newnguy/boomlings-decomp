package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.HashSet;

@JacksonStdImpl
@Deprecated
/* loaded from: classes.dex */
public class MapSerializer extends com.flurry.org.codehaus.jackson.map.ser.std.MapSerializer {
    protected MapSerializer() {
        this(null, null, null, false, null, null, null, null);
    }

    @Deprecated
    protected MapSerializer(HashSet hashSet, JavaType javaType, JavaType javaType2, boolean z, TypeSerializer typeSerializer, JsonSerializer jsonSerializer, BeanProperty beanProperty) {
        super(hashSet, javaType, javaType2, z, typeSerializer, jsonSerializer, null, beanProperty);
    }

    protected MapSerializer(HashSet hashSet, JavaType javaType, JavaType javaType2, boolean z, TypeSerializer typeSerializer, JsonSerializer jsonSerializer, JsonSerializer jsonSerializer2, BeanProperty beanProperty) {
        super(hashSet, javaType, javaType2, z, typeSerializer, jsonSerializer, jsonSerializer2, beanProperty);
    }

    @Deprecated
    protected MapSerializer(HashSet hashSet, JavaType javaType, boolean z, TypeSerializer typeSerializer) {
        super(hashSet, UNSPECIFIED_TYPE, javaType, z, typeSerializer, null, null, null);
    }
}
