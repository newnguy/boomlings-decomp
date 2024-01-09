package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes.dex */
public class AnyGetterWriter {
    protected final Method _anyGetter;
    protected final com.flurry.org.codehaus.jackson.map.ser.std.MapSerializer _serializer;

    public AnyGetterWriter(AnnotatedMethod annotatedMethod, com.flurry.org.codehaus.jackson.map.ser.std.MapSerializer mapSerializer) {
        this._anyGetter = annotatedMethod.getAnnotated();
        this._serializer = mapSerializer;
    }

    public void getAndSerialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        Object invoke = this._anyGetter.invoke(obj, new Object[0]);
        if (invoke == null) {
            return;
        }
        if (!(invoke instanceof Map)) {
            throw new JsonMappingException("Value returned by 'any-getter' (" + this._anyGetter.getName() + "()) not java.util.Map but " + invoke.getClass().getName());
        }
        this._serializer.serializeFields((Map) invoke, jsonGenerator, serializerProvider);
    }

    public void resolve(SerializerProvider serializerProvider) {
        this._serializer.resolve(serializerProvider);
    }
}
