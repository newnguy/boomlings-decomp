package com.flurry.org.codehaus.jackson.map.ser.impl;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.ser.std.BeanSerializerBase;

/* loaded from: classes.dex */
public class UnwrappingBeanSerializer extends BeanSerializerBase {
    public UnwrappingBeanSerializer(BeanSerializerBase beanSerializerBase) {
        super(beanSerializerBase);
    }

    @Override // com.flurry.org.codehaus.jackson.map.JsonSerializer
    public boolean isUnwrappingSerializer() {
        return true;
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.std.BeanSerializerBase, com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
    public final void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        if (this._propertyFilterId != null) {
            serializeFieldsFiltered(obj, jsonGenerator, serializerProvider);
        } else {
            serializeFields(obj, jsonGenerator, serializerProvider);
        }
    }

    public String toString() {
        return "UnwrappingBeanSerializer for " + handledType().getName();
    }

    @Override // com.flurry.org.codehaus.jackson.map.JsonSerializer
    public JsonSerializer unwrappingSerializer() {
        return this;
    }
}
