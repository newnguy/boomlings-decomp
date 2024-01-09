package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.ResolvableSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.ser.AnyGetterWriter;
import com.flurry.org.codehaus.jackson.map.ser.BeanPropertyFilter;
import com.flurry.org.codehaus.jackson.map.ser.BeanPropertyWriter;
import com.flurry.org.codehaus.jackson.map.ser.FilterProvider;
import com.flurry.org.codehaus.jackson.node.ObjectNode;
import com.flurry.org.codehaus.jackson.schema.JsonSchema;
import com.flurry.org.codehaus.jackson.schema.SchemaAware;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Type;

/* loaded from: classes.dex */
public abstract class BeanSerializerBase extends SerializerBase implements ResolvableSerializer, SchemaAware {
    protected static final BeanPropertyWriter[] NO_PROPS = new BeanPropertyWriter[0];
    protected final AnyGetterWriter _anyGetterWriter;
    protected final BeanPropertyWriter[] _filteredProps;
    protected final Object _propertyFilterId;
    protected final BeanPropertyWriter[] _props;

    /* JADX INFO: Access modifiers changed from: protected */
    public BeanSerializerBase(BeanSerializerBase beanSerializerBase) {
        this(beanSerializerBase._handledType, beanSerializerBase._props, beanSerializerBase._filteredProps, beanSerializerBase._anyGetterWriter, beanSerializerBase._propertyFilterId);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BeanSerializerBase(JavaType javaType, BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2, AnyGetterWriter anyGetterWriter, Object obj) {
        super(javaType);
        this._props = beanPropertyWriterArr;
        this._filteredProps = beanPropertyWriterArr2;
        this._anyGetterWriter = anyGetterWriter;
        this._propertyFilterId = obj;
    }

    public BeanSerializerBase(Class cls, BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2, AnyGetterWriter anyGetterWriter, Object obj) {
        super(cls);
        this._props = beanPropertyWriterArr;
        this._filteredProps = beanPropertyWriterArr2;
        this._anyGetterWriter = anyGetterWriter;
        this._propertyFilterId = obj;
    }

    protected BeanPropertyFilter findFilter(SerializerProvider serializerProvider) {
        Object obj = this._propertyFilterId;
        FilterProvider filterProvider = serializerProvider.getFilterProvider();
        if (filterProvider == null) {
            throw new JsonMappingException("Can not resolve BeanPropertyFilter with id '" + obj + "'; no FilterProvider configured");
        }
        return filterProvider.findFilter(obj);
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.schema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        ObjectNode createSchemaNode = createSchemaNode("object", true);
        ObjectNode objectNode = createSchemaNode.objectNode();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this._props.length) {
                createSchemaNode.put("properties", objectNode);
                return createSchemaNode;
            }
            BeanPropertyWriter beanPropertyWriter = this._props[i2];
            JavaType serializationType = beanPropertyWriter.getSerializationType();
            Class genericPropertyType = serializationType == null ? beanPropertyWriter.getGenericPropertyType() : serializationType.getRawClass();
            JsonSerializer serializer = beanPropertyWriter.getSerializer();
            if (serializer == null) {
                Class rawSerializationType = beanPropertyWriter.getRawSerializationType();
                if (rawSerializationType == null) {
                    rawSerializationType = beanPropertyWriter.getPropertyType();
                }
                serializer = serializerProvider.findValueSerializer(rawSerializationType, beanPropertyWriter);
            }
            objectNode.put(beanPropertyWriter.getName(), serializer instanceof SchemaAware ? ((SchemaAware) serializer).getSchema(serializerProvider, genericPropertyType) : JsonSchema.getDefaultSchemaNode());
            i = i2 + 1;
        }
    }

    @Override // com.flurry.org.codehaus.jackson.map.ResolvableSerializer
    public void resolve(SerializerProvider serializerProvider) {
        BeanPropertyWriter beanPropertyWriter;
        TypeSerializer typeSerializer;
        int length = this._filteredProps == null ? 0 : this._filteredProps.length;
        int length2 = this._props.length;
        for (int i = 0; i < length2; i++) {
            BeanPropertyWriter beanPropertyWriter2 = this._props[i];
            if (!beanPropertyWriter2.hasSerializer()) {
                JavaType serializationType = beanPropertyWriter2.getSerializationType();
                if (serializationType == null) {
                    serializationType = serializerProvider.constructType(beanPropertyWriter2.getGenericPropertyType());
                    if (!serializationType.isFinal()) {
                        if (serializationType.isContainerType() || serializationType.containedTypeCount() > 0) {
                            beanPropertyWriter2.setNonTrivialBaseType(serializationType);
                        }
                    }
                }
                JsonSerializer findValueSerializer = serializerProvider.findValueSerializer(serializationType, beanPropertyWriter2);
                if (serializationType.isContainerType() && (typeSerializer = (TypeSerializer) serializationType.getContentType().getTypeHandler()) != null && (findValueSerializer instanceof ContainerSerializerBase)) {
                    findValueSerializer = ((ContainerSerializerBase) findValueSerializer).withValueTypeSerializer(typeSerializer);
                }
                this._props[i] = beanPropertyWriter2.withSerializer(findValueSerializer);
                if (i < length && (beanPropertyWriter = this._filteredProps[i]) != null) {
                    this._filteredProps[i] = beanPropertyWriter.withSerializer(findValueSerializer);
                }
            }
        }
        if (this._anyGetterWriter != null) {
            this._anyGetterWriter.resolve(serializerProvider);
        }
    }

    @Override // com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase, com.flurry.org.codehaus.jackson.map.JsonSerializer
    public abstract void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider);

    /* JADX INFO: Access modifiers changed from: protected */
    public void serializeFields(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        BeanPropertyWriter[] beanPropertyWriterArr = (this._filteredProps == null || serializerProvider.getSerializationView() == null) ? this._props : this._filteredProps;
        int i = 0;
        try {
            int length = beanPropertyWriterArr.length;
            while (i < length) {
                BeanPropertyWriter beanPropertyWriter = beanPropertyWriterArr[i];
                if (beanPropertyWriter != null) {
                    beanPropertyWriter.serializeAsField(obj, jsonGenerator, serializerProvider);
                }
                i++;
            }
            if (this._anyGetterWriter != null) {
                this._anyGetterWriter.getAndSerialize(obj, jsonGenerator, serializerProvider);
            }
        } catch (Exception e) {
            wrapAndThrow(serializerProvider, e, obj, i == beanPropertyWriterArr.length ? "[anySetter]" : beanPropertyWriterArr[i].getName());
        } catch (StackOverflowError e2) {
            JsonMappingException jsonMappingException = new JsonMappingException("Infinite recursion (StackOverflowError)");
            jsonMappingException.prependPath(new JsonMappingException.Reference(obj, i == beanPropertyWriterArr.length ? "[anySetter]" : beanPropertyWriterArr[i].getName()));
            throw jsonMappingException;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void serializeFieldsFiltered(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
        BeanPropertyWriter[] beanPropertyWriterArr = (this._filteredProps == null || serializerProvider.getSerializationView() == null) ? this._props : this._filteredProps;
        BeanPropertyFilter findFilter = findFilter(serializerProvider);
        if (findFilter == null) {
            serializeFields(obj, jsonGenerator, serializerProvider);
            return;
        }
        int i = 0;
        try {
            int length = beanPropertyWriterArr.length;
            while (i < length) {
                BeanPropertyWriter beanPropertyWriter = beanPropertyWriterArr[i];
                if (beanPropertyWriter != null) {
                    findFilter.serializeAsField(obj, jsonGenerator, serializerProvider, beanPropertyWriter);
                }
                i++;
            }
            if (this._anyGetterWriter != null) {
                this._anyGetterWriter.getAndSerialize(obj, jsonGenerator, serializerProvider);
            }
        } catch (Exception e) {
            wrapAndThrow(serializerProvider, e, obj, i == beanPropertyWriterArr.length ? "[anySetter]" : beanPropertyWriterArr[i].getName());
        } catch (StackOverflowError e2) {
            JsonMappingException jsonMappingException = new JsonMappingException("Infinite recursion (StackOverflowError)");
            jsonMappingException.prependPath(new JsonMappingException.Reference(obj, i == beanPropertyWriterArr.length ? "[anySetter]" : beanPropertyWriterArr[i].getName()));
            throw jsonMappingException;
        }
    }

    @Override // com.flurry.org.codehaus.jackson.map.JsonSerializer
    public void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
        typeSerializer.writeTypePrefixForObject(obj, jsonGenerator);
        if (this._propertyFilterId != null) {
            serializeFieldsFiltered(obj, jsonGenerator, serializerProvider);
        } else {
            serializeFields(obj, jsonGenerator, serializerProvider);
        }
        typeSerializer.writeTypeSuffixForObject(obj, jsonGenerator);
    }
}
