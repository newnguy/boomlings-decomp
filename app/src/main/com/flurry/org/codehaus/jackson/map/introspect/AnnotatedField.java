package com.flurry.org.codehaus.jackson.map.introspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
public final class AnnotatedField extends AnnotatedMember {
    protected final Field _field;

    public AnnotatedField(Field field, AnnotationMap annotationMap) {
        super(annotationMap);
        this._field = field;
    }

    public void addOrOverride(Annotation annotation) {
        this._annotations.add(annotation);
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public Field getAnnotated() {
        return this._field;
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public Annotation getAnnotation(Class cls) {
        return this._annotations.get(cls);
    }

    public int getAnnotationCount() {
        return this._annotations.size();
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember
    public Class getDeclaringClass() {
        return this._field.getDeclaringClass();
    }

    public String getFullName() {
        return getDeclaringClass().getName() + "#" + getName();
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public Type getGenericType() {
        return this._field.getGenericType();
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember
    public Member getMember() {
        return this._field;
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public int getModifiers() {
        return this._field.getModifiers();
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public String getName() {
        return this._field.getName();
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public Class getRawType() {
        return this._field.getType();
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember
    public void setValue(Object obj, Object obj2) {
        try {
            this._field.set(obj, obj2);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Failed to setValue() for field " + getFullName() + ": " + e.getMessage(), e);
        }
    }

    public String toString() {
        return "[field " + getName() + ", annotations: " + this._annotations + "]";
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public AnnotatedField withAnnotations(AnnotationMap annotationMap) {
        return new AnnotatedField(this._field, annotationMap);
    }
}
