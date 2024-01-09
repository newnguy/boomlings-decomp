package com.flurry.org.codehaus.jackson.map.jsontype;

import com.flurry.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.Collection;

/* loaded from: classes.dex */
public interface TypeResolverBuilder {
    TypeDeserializer buildTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, Collection collection, BeanProperty beanProperty);

    TypeSerializer buildTypeSerializer(SerializationConfig serializationConfig, JavaType javaType, Collection collection, BeanProperty beanProperty);

    TypeResolverBuilder defaultImpl(Class cls);

    Class getDefaultImpl();

    TypeResolverBuilder inclusion(JsonTypeInfo.As as);

    TypeResolverBuilder init(JsonTypeInfo.Id id, TypeIdResolver typeIdResolver);

    TypeResolverBuilder typeProperty(String str);
}
