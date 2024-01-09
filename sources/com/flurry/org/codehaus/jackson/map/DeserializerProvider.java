package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.io.SerializedString;
import com.flurry.org.codehaus.jackson.map.deser.BeanDeserializerModifier;
import com.flurry.org.codehaus.jackson.map.deser.ValueInstantiators;
import com.flurry.org.codehaus.jackson.type.JavaType;

/* loaded from: classes.dex */
public abstract class DeserializerProvider {
    public abstract int cachedDeserializersCount();

    public abstract SerializedString findExpectedRootName(DeserializationConfig deserializationConfig, JavaType javaType);

    public abstract KeyDeserializer findKeyDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty);

    public abstract JsonDeserializer findTypedValueDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty);

    public abstract JsonDeserializer findValueDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty);

    public abstract void flushCachedDeserializers();

    public abstract boolean hasValueDeserializerFor(DeserializationConfig deserializationConfig, JavaType javaType);

    public abstract JavaType mapAbstractType(DeserializationConfig deserializationConfig, JavaType javaType);

    public abstract DeserializerProvider withAbstractTypeResolver(AbstractTypeResolver abstractTypeResolver);

    public abstract DeserializerProvider withAdditionalDeserializers(Deserializers deserializers);

    public abstract DeserializerProvider withAdditionalKeyDeserializers(KeyDeserializers keyDeserializers);

    public abstract DeserializerProvider withDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier);

    public abstract DeserializerProvider withFactory(DeserializerFactory deserializerFactory);

    public abstract DeserializerProvider withValueInstantiators(ValueInstantiators valueInstantiators);
}
