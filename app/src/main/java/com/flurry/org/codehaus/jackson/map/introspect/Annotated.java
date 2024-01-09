package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.map.type.TypeBindings;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
public abstract class Annotated {
    protected abstract AnnotationMap getAllAnnotations();

    public abstract AnnotatedElement getAnnotated();

    public abstract Annotation getAnnotation(Class cls);

    public abstract Type getGenericType();

    public abstract int getModifiers();

    public abstract String getName();

    public abstract Class getRawType();

    public JavaType getType(TypeBindings typeBindings) {
        return typeBindings.resolveType(getGenericType());
    }

    public final boolean hasAnnotation(Class cls) {
        return getAnnotation(cls) != null;
    }

    public final boolean isPublic() {
        return Modifier.isPublic(getModifiers());
    }

    public abstract Annotated withAnnotations(AnnotationMap annotationMap);

    public final Annotated withFallBackAnnotationsFrom(Annotated annotated) {
        return withAnnotations(AnnotationMap.merge(getAllAnnotations(), annotated.getAllAnnotations()));
    }
}
