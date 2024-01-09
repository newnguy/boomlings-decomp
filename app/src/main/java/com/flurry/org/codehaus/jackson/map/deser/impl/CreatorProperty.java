package com.flurry.org.codehaus.jackson.map.deser.impl;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedParameter;
import com.flurry.org.codehaus.jackson.map.util.Annotations;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.annotation.Annotation;

/* loaded from: classes.dex */
public class CreatorProperty extends SettableBeanProperty {
    protected final AnnotatedParameter _annotated;
    protected final Object _injectableValueId;

    protected CreatorProperty(CreatorProperty creatorProperty, JsonDeserializer jsonDeserializer) {
        super(creatorProperty, jsonDeserializer);
        this._annotated = creatorProperty._annotated;
        this._injectableValueId = creatorProperty._injectableValueId;
    }

    public CreatorProperty(String str, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedParameter annotatedParameter, int i, Object obj) {
        super(str, javaType, typeDeserializer, annotations);
        this._annotated = annotatedParameter;
        this._propertyIndex = i;
        this._injectableValueId = obj;
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty
    public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) {
        set(obj, deserialize(jsonParser, deserializationContext));
    }

    public Object findInjectableValue(DeserializationContext deserializationContext, Object obj) {
        if (this._injectableValueId == null) {
            throw new IllegalStateException("Property '" + getName() + "' (type " + getClass().getName() + ") has no injectable value id configured");
        }
        return deserializationContext.findInjectableValue(this._injectableValueId, this, obj);
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty, com.flurry.org.codehaus.jackson.map.BeanProperty
    public Annotation getAnnotation(Class cls) {
        if (this._annotated == null) {
            return null;
        }
        return this._annotated.getAnnotation(cls);
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty
    public Object getInjectableValueId() {
        return this._injectableValueId;
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty, com.flurry.org.codehaus.jackson.map.BeanProperty
    public AnnotatedMember getMember() {
        return this._annotated;
    }

    public void inject(DeserializationContext deserializationContext, Object obj) {
        set(obj, findInjectableValue(deserializationContext, obj));
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty
    public void set(Object obj, Object obj2) {
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty
    public CreatorProperty withValueDeserializer(JsonDeserializer jsonDeserializer) {
        return new CreatorProperty(this, jsonDeserializer);
    }
}
