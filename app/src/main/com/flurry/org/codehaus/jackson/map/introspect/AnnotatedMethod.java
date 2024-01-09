package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.map.type.TypeBindings;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
public final class AnnotatedMethod extends AnnotatedWithParams {
    protected final Method _method;
    protected Class[] _paramTypes;

    public AnnotatedMethod(Method method, AnnotationMap annotationMap, AnnotationMap[] annotationMapArr) {
        super(annotationMap, annotationMapArr);
        this._method = method;
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.AnnotatedWithParams
    public final Object call() {
        return this._method.invoke(null, new Object[0]);
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.AnnotatedWithParams
    public final Object call(Object[] objArr) {
        return this._method.invoke(null, objArr);
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.AnnotatedWithParams
    public final Object call1(Object obj) {
        return this._method.invoke(null, obj);
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public Method getAnnotated() {
        return this._method;
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember
    public Class getDeclaringClass() {
        return this._method.getDeclaringClass();
    }

    public String getFullName() {
        return getDeclaringClass().getName() + "#" + getName() + "(" + getParameterCount() + " params)";
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public Type getGenericType() {
        return this._method.getGenericReturnType();
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember
    public Member getMember() {
        return this._method;
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public int getModifiers() {
        return this._method.getModifiers();
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public String getName() {
        return this._method.getName();
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.AnnotatedWithParams
    public Class getParameterClass(int i) {
        Class<?>[] parameterTypes = this._method.getParameterTypes();
        if (i >= parameterTypes.length) {
            return null;
        }
        return parameterTypes[i];
    }

    public Class[] getParameterClasses() {
        if (this._paramTypes == null) {
            this._paramTypes = this._method.getParameterTypes();
        }
        return this._paramTypes;
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.AnnotatedWithParams
    public int getParameterCount() {
        return getParameterTypes().length;
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.AnnotatedWithParams
    public Type getParameterType(int i) {
        Type[] genericParameterTypes = this._method.getGenericParameterTypes();
        if (i >= genericParameterTypes.length) {
            return null;
        }
        return genericParameterTypes[i];
    }

    public Type[] getParameterTypes() {
        return this._method.getGenericParameterTypes();
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public Class getRawType() {
        return this._method.getReturnType();
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public JavaType getType(TypeBindings typeBindings) {
        return getType(typeBindings, this._method.getTypeParameters());
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember
    public void setValue(Object obj, Object obj2) {
        try {
            this._method.invoke(obj, obj2);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Failed to setValue() with method " + getFullName() + ": " + e.getMessage(), e);
        } catch (InvocationTargetException e2) {
            throw new IllegalArgumentException("Failed to setValue() with method " + getFullName() + ": " + e2.getMessage(), e2);
        }
    }

    public String toString() {
        return "[method " + getName() + ", annotations: " + this._annotations + "]";
    }

    @Override // com.flurry.org.codehaus.jackson.map.introspect.Annotated
    public AnnotatedMethod withAnnotations(AnnotationMap annotationMap) {
        return new AnnotatedMethod(this._method, annotationMap, this._paramAnnotations);
    }

    public AnnotatedMethod withMethod(Method method) {
        return new AnnotatedMethod(method, this._annotations, this._paramAnnotations);
    }
}
