package com.flurry.org.codehaus.jackson.map;

/* loaded from: classes.dex */
public interface ContextualDeserializer {
    JsonDeserializer createContextual(DeserializationConfig deserializationConfig, BeanProperty beanProperty);
}
