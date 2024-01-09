package com.flurry.org.codehaus.jackson.map.jsontype.impl;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeIdResolver;

/* loaded from: classes.dex */
public class AsExternalTypeSerializer extends TypeSerializerBase {
    protected final String _typePropertyName;

    public AsExternalTypeSerializer(TypeIdResolver typeIdResolver, BeanProperty beanProperty, String str) {
        super(typeIdResolver, beanProperty);
        this._typePropertyName = str;
    }

    protected final void _writePrefix(Object obj, JsonGenerator jsonGenerator) {
        jsonGenerator.writeStartObject();
    }

    protected final void _writePrefix(Object obj, JsonGenerator jsonGenerator, Class cls) {
        jsonGenerator.writeStartObject();
    }

    protected final void _writeSuffix(Object obj, JsonGenerator jsonGenerator) {
        jsonGenerator.writeEndObject();
        jsonGenerator.writeStringField(this._typePropertyName, this._idResolver.idFromValue(obj));
    }

    @Override // com.flurry.org.codehaus.jackson.map.jsontype.impl.TypeSerializerBase, com.flurry.org.codehaus.jackson.map.TypeSerializer
    public String getPropertyName() {
        return this._typePropertyName;
    }

    @Override // com.flurry.org.codehaus.jackson.map.jsontype.impl.TypeSerializerBase, com.flurry.org.codehaus.jackson.map.TypeSerializer
    public JsonTypeInfo.As getTypeInclusion() {
        return JsonTypeInfo.As.EXTERNAL_PROPERTY;
    }

    @Override // com.flurry.org.codehaus.jackson.map.TypeSerializer
    public void writeTypePrefixForArray(Object obj, JsonGenerator jsonGenerator) {
        _writePrefix(obj, jsonGenerator);
    }

    @Override // com.flurry.org.codehaus.jackson.map.TypeSerializer
    public void writeTypePrefixForArray(Object obj, JsonGenerator jsonGenerator, Class cls) {
        _writePrefix(obj, jsonGenerator, cls);
    }

    @Override // com.flurry.org.codehaus.jackson.map.TypeSerializer
    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator) {
        _writePrefix(obj, jsonGenerator);
    }

    @Override // com.flurry.org.codehaus.jackson.map.TypeSerializer
    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator, Class cls) {
        _writePrefix(obj, jsonGenerator, cls);
    }

    @Override // com.flurry.org.codehaus.jackson.map.TypeSerializer
    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator) {
        _writePrefix(obj, jsonGenerator);
    }

    @Override // com.flurry.org.codehaus.jackson.map.TypeSerializer
    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator, Class cls) {
        _writePrefix(obj, jsonGenerator, cls);
    }

    @Override // com.flurry.org.codehaus.jackson.map.TypeSerializer
    public void writeTypeSuffixForArray(Object obj, JsonGenerator jsonGenerator) {
        _writeSuffix(obj, jsonGenerator);
    }

    @Override // com.flurry.org.codehaus.jackson.map.TypeSerializer
    public void writeTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator) {
        _writeSuffix(obj, jsonGenerator);
    }

    @Override // com.flurry.org.codehaus.jackson.map.TypeSerializer
    public void writeTypeSuffixForScalar(Object obj, JsonGenerator jsonGenerator) {
        _writeSuffix(obj, jsonGenerator);
    }
}
