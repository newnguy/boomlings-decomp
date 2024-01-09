package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.ser.impl.UnwrappingBeanSerializer;
import com.flurry.org.codehaus.jackson.map.ser.std.BeanSerializerBase;
import com.flurry.org.codehaus.jackson.type.JavaType;

/* loaded from: classes.dex */
public class BeanSerializer extends BeanSerializerBase {
    protected BeanSerializer(BeanSerializer beanSerializer) {
        super(beanSerializer);
    }

    protected BeanSerializer(BeanSerializerBase beanSerializerBase) {
        super(beanSerializerBase);
    }

    public BeanSerializer(JavaType javaType, BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2, AnyGetterWriter anyGetterWriter, Object obj) {
        super(javaType, beanPropertyWriterArr, beanPropertyWriterArr2, anyGetterWriter, obj);
    }

    public BeanSerializer(Class cls, BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2, AnyGetterWriter anyGetterWriter, Object obj) {
        super(cls, beanPropertyWriterArr, beanPropertyWriterArr2, anyGetterWriter, obj);
    }

    public static BeanSerializer createDummy(Class cls) {
        return new BeanSerializer(cls, NO_PROPS, (BeanPropertyWriter[]) null, (AnyGetterWriter) null, (Object) null);
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.std.BeanSerializerBase, com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
    public final void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeStartObject();
        if (this._propertyFilterId != null) {
            serializeFieldsFiltered(obj, jsonGenerator, serializerProvider);
        } else {
            serializeFields(obj, jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndObject();
    }

    public String toString() {
        return "BeanSerializer for " + handledType().getName();
    }

    @Override // com.flurry.org.codehaus.jackson.map.JsonSerializer
    public JsonSerializer unwrappingSerializer() {
        return new UnwrappingBeanSerializer(this);
    }
}
