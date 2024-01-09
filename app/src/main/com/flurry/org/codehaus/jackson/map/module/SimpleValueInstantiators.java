package com.flurry.org.codehaus.jackson.map.module;

import com.flurry.org.codehaus.jackson.map.BeanDescription;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.deser.ValueInstantiator;
import com.flurry.org.codehaus.jackson.map.deser.ValueInstantiators;
import com.flurry.org.codehaus.jackson.map.type.ClassKey;
import java.util.HashMap;

/* loaded from: classes.dex */
public class SimpleValueInstantiators extends ValueInstantiators.Base {
    protected HashMap _classMappings = new HashMap();

    public SimpleValueInstantiators addValueInstantiator(Class cls, ValueInstantiator valueInstantiator) {
        this._classMappings.put(new ClassKey(cls), valueInstantiator);
        return this;
    }

    @Override // com.flurry.org.codehaus.jackson.map.deser.ValueInstantiators.Base, com.flurry.org.codehaus.jackson.map.deser.ValueInstantiators
    public ValueInstantiator findValueInstantiator(DeserializationConfig deserializationConfig, BeanDescription beanDescription, ValueInstantiator valueInstantiator) {
        ValueInstantiator valueInstantiator2 = (ValueInstantiator) this._classMappings.get(new ClassKey(beanDescription.getBeanClass()));
        return valueInstantiator2 == null ? valueInstantiator : valueInstantiator2;
    }
}
