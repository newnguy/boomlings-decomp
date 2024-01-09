package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.ser.FilterProvider;
import com.flurry.org.codehaus.jackson.map.type.TypeFactory;
import com.flurry.org.codehaus.jackson.schema.JsonSchema;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Type;
import java.util.Date;

/* loaded from: classes.dex */
public abstract class SerializerProvider {
    protected static final JavaType TYPE_OBJECT = TypeFactory.defaultInstance().uncheckedSimpleType(Object.class);
    protected final SerializationConfig _config;
    protected final Class _serializationView;

    /* JADX INFO: Access modifiers changed from: protected */
    public SerializerProvider(SerializationConfig serializationConfig) {
        this._config = serializationConfig;
        this._serializationView = serializationConfig == null ? null : this._config.getSerializationView();
    }

    public abstract int cachedSerializersCount();

    public JavaType constructSpecializedType(JavaType javaType, Class cls) {
        return this._config.constructSpecializedType(javaType, cls);
    }

    public JavaType constructType(Type type) {
        return this._config.getTypeFactory().constructType(type);
    }

    public abstract void defaultSerializeDateKey(long j, JsonGenerator jsonGenerator);

    public abstract void defaultSerializeDateKey(Date date, JsonGenerator jsonGenerator);

    public abstract void defaultSerializeDateValue(long j, JsonGenerator jsonGenerator);

    public abstract void defaultSerializeDateValue(Date date, JsonGenerator jsonGenerator);

    public final void defaultSerializeField(String str, Object obj, JsonGenerator jsonGenerator) {
        jsonGenerator.writeFieldName(str);
        if (obj == null) {
            getNullValueSerializer().serialize(null, jsonGenerator, this);
        } else {
            findTypedValueSerializer((Class) obj.getClass(), true, (BeanProperty) null).serialize(obj, jsonGenerator, this);
        }
    }

    public final void defaultSerializeNull(JsonGenerator jsonGenerator) {
        getNullValueSerializer().serialize(null, jsonGenerator, this);
    }

    public final void defaultSerializeValue(Object obj, JsonGenerator jsonGenerator) {
        if (obj == null) {
            getNullValueSerializer().serialize(null, jsonGenerator, this);
        } else {
            findTypedValueSerializer((Class) obj.getClass(), true, (BeanProperty) null).serialize(obj, jsonGenerator, this);
        }
    }

    public abstract JsonSerializer findKeySerializer(JavaType javaType, BeanProperty beanProperty);

    @Deprecated
    public final JsonSerializer findTypedValueSerializer(JavaType javaType, boolean z) {
        return findTypedValueSerializer(javaType, z, (BeanProperty) null);
    }

    public abstract JsonSerializer findTypedValueSerializer(JavaType javaType, boolean z, BeanProperty beanProperty);

    @Deprecated
    public final JsonSerializer findTypedValueSerializer(Class cls, boolean z) {
        return findTypedValueSerializer(cls, z, (BeanProperty) null);
    }

    public abstract JsonSerializer findTypedValueSerializer(Class cls, boolean z, BeanProperty beanProperty);

    @Deprecated
    public final JsonSerializer findValueSerializer(JavaType javaType) {
        return findValueSerializer(javaType, (BeanProperty) null);
    }

    public abstract JsonSerializer findValueSerializer(JavaType javaType, BeanProperty beanProperty);

    @Deprecated
    public final JsonSerializer findValueSerializer(Class cls) {
        return findValueSerializer(cls, (BeanProperty) null);
    }

    public abstract JsonSerializer findValueSerializer(Class cls, BeanProperty beanProperty);

    public abstract void flushCachedSerializers();

    public abstract JsonSchema generateJsonSchema(Class cls, SerializationConfig serializationConfig, SerializerFactory serializerFactory);

    public final SerializationConfig getConfig() {
        return this._config;
    }

    public final FilterProvider getFilterProvider() {
        return this._config.getFilterProvider();
    }

    @Deprecated
    public final JsonSerializer getKeySerializer() {
        return findKeySerializer(TYPE_OBJECT, null);
    }

    @Deprecated
    public final JsonSerializer getKeySerializer(JavaType javaType, BeanProperty beanProperty) {
        return findKeySerializer(javaType, beanProperty);
    }

    public abstract JsonSerializer getNullKeySerializer();

    public abstract JsonSerializer getNullValueSerializer();

    public final Class getSerializationView() {
        return this._serializationView;
    }

    public abstract JsonSerializer getUnknownTypeSerializer(Class cls);

    public abstract boolean hasSerializerFor(SerializationConfig serializationConfig, Class cls, SerializerFactory serializerFactory);

    public final boolean isEnabled(SerializationConfig.Feature feature) {
        return this._config.isEnabled(feature);
    }

    public abstract void serializeValue(SerializationConfig serializationConfig, JsonGenerator jsonGenerator, Object obj, SerializerFactory serializerFactory);

    public abstract void serializeValue(SerializationConfig serializationConfig, JsonGenerator jsonGenerator, Object obj, JavaType javaType, SerializerFactory serializerFactory);

    public abstract void setDefaultKeySerializer(JsonSerializer jsonSerializer);

    public abstract void setNullKeySerializer(JsonSerializer jsonSerializer);

    public abstract void setNullValueSerializer(JsonSerializer jsonSerializer);
}
