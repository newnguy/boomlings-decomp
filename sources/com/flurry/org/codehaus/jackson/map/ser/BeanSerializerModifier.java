package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import java.util.List;

/* loaded from: classes.dex */
public abstract class BeanSerializerModifier {
    public List changeProperties(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, List list) {
        return list;
    }

    public JsonSerializer modifySerializer(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, JsonSerializer jsonSerializer) {
        return jsonSerializer;
    }

    public List orderProperties(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, List list) {
        return list;
    }

    public BeanSerializerBuilder updateBuilder(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, BeanSerializerBuilder beanSerializerBuilder) {
        return beanSerializerBuilder;
    }
}
