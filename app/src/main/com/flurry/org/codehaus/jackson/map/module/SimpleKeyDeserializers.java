package com.flurry.org.codehaus.jackson.map.module;

import com.flurry.org.codehaus.jackson.map.BeanDescription;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.KeyDeserializer;
import com.flurry.org.codehaus.jackson.map.KeyDeserializers;
import com.flurry.org.codehaus.jackson.map.type.ClassKey;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.HashMap;

/* loaded from: classes.dex */
public class SimpleKeyDeserializers implements KeyDeserializers {
    protected HashMap _classMappings = null;

    public SimpleKeyDeserializers addDeserializer(Class cls, KeyDeserializer keyDeserializer) {
        if (this._classMappings == null) {
            this._classMappings = new HashMap();
        }
        this._classMappings.put(new ClassKey(cls), keyDeserializer);
        return this;
    }

    @Override // com.flurry.org.codehaus.jackson.map.KeyDeserializers
    public KeyDeserializer findKeyDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, BeanProperty beanProperty) {
        if (this._classMappings == null) {
            return null;
        }
        return (KeyDeserializer) this._classMappings.get(new ClassKey(javaType.getRawClass()));
    }
}
