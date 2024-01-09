package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.map.type.TypeFactory;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
public final class AnnotatedParameter extends AnnotatedMember {
    protected final int _index;
    protected final AnnotatedWithParams _owner;
    protected final Type _type;

    public AnnotatedParameter(AnnotatedWithParams annotatedWithParams, Type type, AnnotationMap annotationMap, int i) {
        super(annotationMap);
        this._owner = annotatedWithParams;
        this._type = type;
        this._index = i;
    }

    public void addOrOverride(Annotation annotation) {
        this._annotations.add(annotation);
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public AnnotatedElement getAnnotated() {
        return null;
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public Annotation getAnnotation(Class cls) {
        return this._annotations.get(cls);
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember
    public Class getDeclaringClass() {
        return this._owner.getDeclaringClass();
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public Type getGenericType() {
        return this._type;
    }

    public int getIndex() {
        return this._index;
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember
    public Member getMember() {
        return this._owner.getMember();
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public int getModifiers() {
        return this._owner.getModifiers();
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public String getName() {
        return "";
    }

    public AnnotatedWithParams getOwner() {
        return this._owner;
    }

    public Type getParameterType() {
        return this._type;
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public Class getRawType() {
        return this._type instanceof Class ? (Class) this._type : TypeFactory.defaultInstance().constructType(this._type).getRawClass();
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember
    public void setValue(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Cannot call setValue() on constructor parameter of " + getDeclaringClass().getName());
    }

    public String toString() {
        return "[parameter #" + getIndex() + ", annotations: " + this._annotations + "]";
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public AnnotatedParameter withAnnotations(AnnotationMap annotationMap) {
        return annotationMap == this._annotations ? this : this._owner.replaceParameterAnnotations(this._index, annotationMap);
    }
}
