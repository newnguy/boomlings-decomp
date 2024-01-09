package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.map.type.TypeBindings;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
public final class AnnotatedConstructor extends AnnotatedWithParams {
    protected final Constructor _constructor;

    public AnnotatedConstructor(Constructor constructor, AnnotationMap annotationMap, AnnotationMap[] annotationMapArr) {
        super(annotationMap, annotationMapArr);
        if (constructor == null) {
            throw new IllegalArgumentException("Null constructor not allowed");
        }
        this._constructor = constructor;
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.AnnotatedWithParams
    public final Object call() {
        return this._constructor.newInstance(new Object[0]);
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.AnnotatedWithParams
    public final Object call(Object[] objArr) {
        return this._constructor.newInstance(objArr);
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.AnnotatedWithParams
    public final Object call1(Object obj) {
        return this._constructor.newInstance(obj);
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public Constructor getAnnotated() {
        return this._constructor;
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember
    public Class getDeclaringClass() {
        return this._constructor.getDeclaringClass();
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public Type getGenericType() {
        return getRawType();
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember
    public Member getMember() {
        return this._constructor;
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public int getModifiers() {
        return this._constructor.getModifiers();
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public String getName() {
        return this._constructor.getName();
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.AnnotatedWithParams
    public Class getParameterClass(int i) {
        Class<?>[] parameterTypes = this._constructor.getParameterTypes();
        if (i >= parameterTypes.length) {
            return null;
        }
        return parameterTypes[i];
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.AnnotatedWithParams
    public int getParameterCount() {
        return this._constructor.getParameterTypes().length;
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.AnnotatedWithParams
    public Type getParameterType(int i) {
        Type[] genericParameterTypes = this._constructor.getGenericParameterTypes();
        if (i >= genericParameterTypes.length) {
            return null;
        }
        return genericParameterTypes[i];
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public Class getRawType() {
        return this._constructor.getDeclaringClass();
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public JavaType getType(TypeBindings typeBindings) {
        return getType(typeBindings, this._constructor.getTypeParameters());
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember
    public void setValue(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Cannot call setValue() on constructor of " + getDeclaringClass().getName());
    }

    public String toString() {
        return "[constructor for " + getName() + ", annotations: " + this._annotations + "]";
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public AnnotatedConstructor withAnnotations(AnnotationMap annotationMap) {
        return new AnnotatedConstructor(this._constructor, annotationMap, this._paramAnnotations);
    }
}
