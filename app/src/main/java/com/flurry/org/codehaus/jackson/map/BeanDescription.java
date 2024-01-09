package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedClass;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.flurry.org.codehaus.jackson.map.introspect.VisibilityChecker;
import com.flurry.org.codehaus.jackson.map.type.TypeBindings;
import com.flurry.org.codehaus.jackson.map.util.Annotations;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class BeanDescription {
    protected final JavaType _type;

    public BeanDescription(JavaType javaType) {
        this._type = javaType;
    }

    public abstract TypeBindings bindingsForBeanType();

    public abstract AnnotatedMethod findAnyGetter();

    public abstract AnnotatedMethod findAnySetter();

    public abstract AnnotatedConstructor findDefaultConstructor();

    @Deprecated
    public abstract LinkedHashMap findDeserializableFields(VisibilityChecker visibilityChecker, Collection collection);

    @Deprecated
    public abstract LinkedHashMap findGetters(VisibilityChecker visibilityChecker, Collection collection);

    public abstract Map findInjectables();

    public abstract AnnotatedMethod findJsonValueMethod();

    public abstract List findProperties();

    @Deprecated
    public abstract Map findSerializableFields(VisibilityChecker visibilityChecker, Collection collection);

    @Deprecated
    public abstract LinkedHashMap findSetters(VisibilityChecker visibilityChecker);

    public Class getBeanClass() {
        return this._type.getRawClass();
    }

    public abstract Annotations getClassAnnotations();

    public abstract AnnotatedClass getClassInfo();

    public abstract Set getIgnoredPropertyNames();

    public JavaType getType() {
        return this._type;
    }

    public abstract boolean hasKnownClassAnnotations();

    public abstract JavaType resolveType(Type type);
}
