package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.ResolvableSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.ser.impl.PropertySerializerMap;
import com.flurry.org.codehaus.jackson.type.JavaType;

/* loaded from: classes.dex */
public abstract class AsArraySerializerBase extends ContainerSerializerBase implements ResolvableSerializer {
    protected PropertySerializerMap _dynamicSerializers;
    protected JsonSerializer _elementSerializer;
    protected final JavaType _elementType;
    protected final BeanProperty _property;
    protected final boolean _staticTyping;
    protected final TypeSerializer _valueTypeSerializer;

    @Deprecated
    protected AsArraySerializerBase(Class cls, JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty) {
        this(cls, javaType, z, typeSerializer, beanProperty, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AsArraySerializerBase(Class cls, JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer jsonSerializer) {
        super(cls, false);
        boolean z2 = false;
        this._elementType = javaType;
        if (z || (javaType != null && javaType.isFinal())) {
            z2 = true;
        }
        this._staticTyping = z2;
        this._valueTypeSerializer = typeSerializer;
        this._property = beanProperty;
        this._elementSerializer = jsonSerializer;
        this._dynamicSerializers = PropertySerializerMap.emptyMap();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonSerializer _findAndAddDynamic(PropertySerializerMap propertySerializerMap, JavaType javaType, SerializerProvider serializerProvider) {
        PropertySerializerMap.SerializerAndMapResult findAndAddSerializer = propertySerializerMap.findAndAddSerializer(javaType, serializerProvider, this._property);
        if (propertySerializerMap != findAndAddSerializer.map) {
            this._dynamicSerializers = findAndAddSerializer.map;
        }
        return findAndAddSerializer.serializer;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonSerializer _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class cls, SerializerProvider serializerProvider) {
        PropertySerializerMap.SerializerAndMapResult findAndAddSerializer = propertySerializerMap.findAndAddSerializer(cls, serializerProvider, this._property);
        if (propertySerializerMap != findAndAddSerializer.map) {
            this._dynamicSerializers = findAndAddSerializer.map;
        }
        return findAndAddSerializer.serializer;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x004c  */
    @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.flurry.org.codehaus.jackson.JsonNode getSchema(com.flurry.org.codehaus.jackson.map.SerializerProvider r7, java.lang.reflect.Type r8) {
        /*
            r6 = this;
            r5 = 1
            r1 = 0
            java.lang.String r0 = "array"
            com.flurry.org.codehaus.jackson.node.ObjectNode r2 = r6.createSchemaNode(r0, r5)
            if (r8 == 0) goto L58
            com.flurry.org.codehaus.jackson.type.JavaType r0 = r7.constructType(r8)
            com.flurry.org.codehaus.jackson.type.JavaType r0 = r0.getContentType()
            if (r0 != 0) goto L28
            boolean r3 = r8 instanceof java.lang.reflect.ParameterizedType
            if (r3 == 0) goto L28
            java.lang.reflect.ParameterizedType r8 = (java.lang.reflect.ParameterizedType) r8
            java.lang.reflect.Type[] r3 = r8.getActualTypeArguments()
            int r4 = r3.length
            if (r4 != r5) goto L28
            r0 = 0
            r0 = r3[r0]
            com.flurry.org.codehaus.jackson.type.JavaType r0 = r7.constructType(r0)
        L28:
            if (r0 != 0) goto L30
            com.flurry.org.codehaus.jackson.type.JavaType r3 = r6._elementType
            if (r3 == 0) goto L30
            com.flurry.org.codehaus.jackson.type.JavaType r0 = r6._elementType
        L30:
            if (r0 == 0) goto L55
            java.lang.Class r3 = r0.getRawClass()
            java.lang.Class<java.lang.Object> r4 = java.lang.Object.class
            if (r3 == r4) goto L56
            com.flurry.org.codehaus.jackson.map.BeanProperty r3 = r6._property
            com.flurry.org.codehaus.jackson.map.JsonSerializer r0 = r7.findValueSerializer(r0, r3)
            boolean r3 = r0 instanceof com.flurry.org.codehaus.jackson.schema.SchemaAware
            if (r3 == 0) goto L56
            com.flurry.org.codehaus.jackson.schema.SchemaAware r0 = (com.flurry.org.codehaus.jackson.schema.SchemaAware) r0
            com.flurry.org.codehaus.jackson.JsonNode r0 = r0.getSchema(r7, r1)
        L4a:
            if (r0 != 0) goto L50
            com.flurry.org.codehaus.jackson.JsonNode r0 = com.flurry.org.codehaus.jackson.schema.JsonSchema.getDefaultSchemaNode()
        L50:
            java.lang.String r1 = "items"
            r2.put(r1, r0)
        L55:
            return r2
        L56:
            r0 = r1
            goto L4a
        L58:
            r0 = r1
            goto L28
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.org.codehaus.jackson.map.ser.std.AsArraySerializerBase.getSchema(com.flurry.org.codehaus.jackson.map.SerializerProvider, java.lang.reflect.Type):com.flurry.org.codehaus.jackson.JsonNode");
    }

    @Override // com.flurry.org.codehaus.jackson.map.ResolvableSerializer
    public void resolve(SerializerProvider serializerProvider) {
        if (this._staticTyping && this._elementType != null && this._elementSerializer == null) {
            this._elementSerializer = serializerProvider.findValueSerializer(this._elementType, this._property);
        }
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
    public final void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        jsonGenerator.writeStartArray();
        serializeContents(obj, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndArray();
    }

    protected abstract void serializeContents(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider);

    @Override // com.flurry.org.codehaus.jackson.map.JsonSerializer
    public final void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        typeSerializer.writeTypePrefixForArray(obj, jsonGenerator);
        serializeContents(obj, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffixForArray(obj, jsonGenerator);
    }
}
