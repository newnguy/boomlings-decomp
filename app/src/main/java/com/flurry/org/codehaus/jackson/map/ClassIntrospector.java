package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.type.JavaType;

/* loaded from: classes.dex */
public abstract class ClassIntrospector {

    /* loaded from: classes.dex */
    public interface MixInResolver {
        Class findMixInClassFor(Class cls);
    }

    public abstract BeanDescription forClassAnnotations(MapperConfig mapperConfig, JavaType javaType, MixInResolver mixInResolver);

    @Deprecated
    public BeanDescription forClassAnnotations(MapperConfig mapperConfig, Class cls, MixInResolver mixInResolver) {
        return forClassAnnotations(mapperConfig, mapperConfig.constructType(cls), mixInResolver);
    }

    public abstract BeanDescription forCreation(DeserializationConfig deserializationConfig, JavaType javaType, MixInResolver mixInResolver);

    public abstract BeanDescription forDeserialization(DeserializationConfig deserializationConfig, JavaType javaType, MixInResolver mixInResolver);

    public abstract BeanDescription forDirectClassAnnotations(MapperConfig mapperConfig, JavaType javaType, MixInResolver mixInResolver);

    @Deprecated
    public BeanDescription forDirectClassAnnotations(MapperConfig mapperConfig, Class cls, MixInResolver mixInResolver) {
        return forDirectClassAnnotations(mapperConfig, mapperConfig.constructType(cls), mixInResolver);
    }

    public abstract BeanDescription forSerialization(SerializationConfig serializationConfig, JavaType javaType, MixInResolver mixInResolver);
}
