package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.Base64Variant;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.type.TypeFactory;
import com.flurry.org.codehaus.jackson.map.util.ArrayBuilders;
import com.flurry.org.codehaus.jackson.map.util.ObjectBuffer;
import com.flurry.org.codehaus.jackson.node.JsonNodeFactory;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes.dex */
public abstract class DeserializationContext {
    protected final DeserializationConfig _config;
    protected final int _featureFlags;

    /* JADX INFO: Access modifiers changed from: protected */
    public DeserializationContext(DeserializationConfig deserializationConfig) {
        this._config = deserializationConfig;
        this._featureFlags = deserializationConfig._featureFlags;
    }

    public abstract Calendar constructCalendar(Date date);

    public JavaType constructType(Class cls) {
        return this._config.constructType(cls);
    }

    public abstract Object findInjectableValue(Object obj, BeanProperty beanProperty, Object obj2);

    public abstract ArrayBuilders getArrayBuilders();

    public Base64Variant getBase64Variant() {
        return this._config.getBase64Variant();
    }

    public DeserializationConfig getConfig() {
        return this._config;
    }

    public DeserializerProvider getDeserializerProvider() {
        return null;
    }

    public final JsonNodeFactory getNodeFactory() {
        return this._config.getNodeFactory();
    }

    public abstract JsonParser getParser();

    public TypeFactory getTypeFactory() {
        return this._config.getTypeFactory();
    }

    public abstract boolean handleUnknownProperty(JsonParser jsonParser, JsonDeserializer jsonDeserializer, Object obj, String str);

    public abstract JsonMappingException instantiationException(Class cls, String str);

    public abstract JsonMappingException instantiationException(Class cls, Throwable th);

    public boolean isEnabled(DeserializationConfig.Feature feature) {
        return (this._featureFlags & feature.getMask()) != 0;
    }

    public abstract ObjectBuffer leaseObjectBuffer();

    public abstract JsonMappingException mappingException(Class cls);

    public abstract JsonMappingException mappingException(Class cls, JsonToken jsonToken);

    public JsonMappingException mappingException(String str) {
        return JsonMappingException.from(getParser(), str);
    }

    public abstract Date parseDate(String str);

    public abstract void returnObjectBuffer(ObjectBuffer objectBuffer);

    public abstract JsonMappingException unknownFieldException(Object obj, String str);

    public abstract JsonMappingException unknownTypeException(JavaType javaType, String str);

    public abstract JsonMappingException weirdKeyException(Class cls, String str, String str2);

    public abstract JsonMappingException weirdNumberException(Class cls, String str);

    public abstract JsonMappingException weirdStringException(Class cls, String str);

    public abstract JsonMappingException wrongTokenException(JsonParser jsonParser, JsonToken jsonToken, String str);
}
