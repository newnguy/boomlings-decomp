package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.map.deser.ValueInstantiator;
import com.flurry.org.codehaus.jackson.map.introspect.Annotated;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeIdResolver;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;

/* loaded from: classes.dex */
public abstract class HandlerInstantiator {
    public abstract JsonDeserializer deserializerInstance(DeserializationConfig deserializationConfig, Annotated annotated, Class cls);

    public abstract KeyDeserializer keyDeserializerInstance(DeserializationConfig deserializationConfig, Annotated annotated, Class cls);

    public abstract JsonSerializer serializerInstance(SerializationConfig serializationConfig, Annotated annotated, Class cls);

    public abstract TypeIdResolver typeIdResolverInstance(MapperConfig mapperConfig, Annotated annotated, Class cls);

    public abstract TypeResolverBuilder typeResolverBuilderInstance(MapperConfig mapperConfig, Annotated annotated, Class cls);

    public ValueInstantiator valueInstantiatorInstance(MapperConfig mapperConfig, Annotated annotated, Class cls) {
        return null;
    }
}
