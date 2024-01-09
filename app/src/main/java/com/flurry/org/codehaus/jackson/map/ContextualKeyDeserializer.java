package com.flurry.org.codehaus.jackson.map;

/* loaded from: classes.dex */
public interface ContextualKeyDeserializer {
    KeyDeserializer createContextual(DeserializationConfig deserializationConfig, BeanProperty beanProperty);
}
