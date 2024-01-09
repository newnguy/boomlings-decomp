package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.type.JavaType;

/* loaded from: classes.dex */
public interface KeyDeserializers {
    KeyDeserializer findKeyDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, BeanProperty beanProperty);
}
