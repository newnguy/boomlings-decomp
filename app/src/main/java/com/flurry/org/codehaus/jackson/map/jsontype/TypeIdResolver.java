package com.flurry.org.codehaus.jackson.map.jsontype;

import com.flurry.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.flurry.org.codehaus.jackson.type.JavaType;

/* loaded from: classes.dex */
public interface TypeIdResolver {
    JsonTypeInfo.Id getMechanism();

    String idFromValue(Object obj);

    String idFromValueAndType(Object obj, Class cls);

    void init(JavaType javaType);

    JavaType typeFromId(String str);
}
