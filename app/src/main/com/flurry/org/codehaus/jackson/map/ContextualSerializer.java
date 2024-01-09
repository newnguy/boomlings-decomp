package com.flurry.org.codehaus.jackson.map;

/* loaded from: classes.dex */
public interface ContextualSerializer {
    JsonSerializer createContextual(SerializationConfig serializationConfig, BeanProperty beanProperty);
}
