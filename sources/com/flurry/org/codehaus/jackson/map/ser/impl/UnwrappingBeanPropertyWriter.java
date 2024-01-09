package com.flurry.org.codehaus.jackson.map.ser.impl;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.ser.BeanPropertyWriter;

/* loaded from: classes.dex */
public class UnwrappingBeanPropertyWriter extends BeanPropertyWriter {
    public UnwrappingBeanPropertyWriter(BeanPropertyWriter beanPropertyWriter) {
        super(beanPropertyWriter);
    }

    public UnwrappingBeanPropertyWriter(BeanPropertyWriter beanPropertyWriter, JsonSerializer jsonSerializer) {
        super(beanPropertyWriter, jsonSerializer);
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.BeanPropertyWriter
    protected JsonSerializer _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class cls, SerializerProvider serializerProvider) {
        JsonSerializer findValueSerializer = this._nonTrivialBaseType != null ? serializerProvider.findValueSerializer(serializerProvider.constructSpecializedType(this._nonTrivialBaseType, cls), this) : serializerProvider.findValueSerializer(cls, this);
        if (!findValueSerializer.isUnwrappingSerializer()) {
            findValueSerializer = findValueSerializer.unwrappingSerializer();
        }
        this._dynamicSerializers = this._dynamicSerializers.newWith(cls, findValueSerializer);
        return findValueSerializer;
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.BeanPropertyWriter
    public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        Class<?> cls;
        PropertySerializerMap propertySerializerMap;
        Object obj2 = get(obj);
        if (obj2 == null) {
            return;
        }
        if (obj2 == obj) {
            _reportSelfReference(obj);
        }
        if (this._suppressableValue == null || !this._suppressableValue.equals(obj2)) {
            JsonSerializer jsonSerializer = this._serializer;
            if (jsonSerializer == null && (jsonSerializer = (propertySerializerMap = this._dynamicSerializers).serializerFor((cls = obj2.getClass()))) == null) {
                jsonSerializer = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
            }
            if (!jsonSerializer.isUnwrappingSerializer()) {
                jsonGenerator.writeFieldName(this._name);
            }
            if (this._typeSerializer == null) {
                jsonSerializer.serialize(obj2, jsonGenerator, serializerProvider);
            } else {
                jsonSerializer.serializeWithType(obj2, jsonGenerator, serializerProvider, this._typeSerializer);
            }
        }
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.BeanPropertyWriter
    public BeanPropertyWriter withSerializer(JsonSerializer jsonSerializer) {
        if (getClass() != UnwrappingBeanPropertyWriter.class) {
            throw new IllegalStateException("UnwrappingBeanPropertyWriter sub-class does not override 'withSerializer()'; needs to!");
        }
        if (!jsonSerializer.isUnwrappingSerializer()) {
            jsonSerializer = jsonSerializer.unwrappingSerializer();
        }
        return new UnwrappingBeanPropertyWriter(this, jsonSerializer);
    }
}
