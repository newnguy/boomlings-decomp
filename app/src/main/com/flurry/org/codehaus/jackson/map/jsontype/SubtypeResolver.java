package com.flurry.org.codehaus.jackson.map.jsontype;

import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.MapperConfig;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedClass;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import java.util.Collection;

/* loaded from: classes.dex */
public abstract class SubtypeResolver {
    public abstract Collection collectAndResolveSubtypes(AnnotatedClass annotatedClass, MapperConfig mapperConfig, AnnotationIntrospector annotationIntrospector);

    public abstract Collection collectAndResolveSubtypes(AnnotatedMember annotatedMember, MapperConfig mapperConfig, AnnotationIntrospector annotationIntrospector);

    public abstract void registerSubtypes(NamedType... namedTypeArr);

    public abstract void registerSubtypes(Class... clsArr);
}
