package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.std.ObjectArrayDeserializer;
import com.flurry.org.codehaus.jackson.map.type.ArrayType;

@Deprecated
/* loaded from: classes.dex */
public class ArrayDeserializer extends ObjectArrayDeserializer {
    @Deprecated
    public ArrayDeserializer(ArrayType arrayType, JsonDeserializer jsonDeserializer) {
        this(arrayType, jsonDeserializer, null);
    }

    public ArrayDeserializer(ArrayType arrayType, JsonDeserializer jsonDeserializer, TypeDeserializer typeDeserializer) {
        super(arrayType, jsonDeserializer, typeDeserializer);
    }
}
