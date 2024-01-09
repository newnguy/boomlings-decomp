package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.ContextualSerializer;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.ResolvableSerializer;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.SerializerFactory;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.ser.impl.FailingSerializer;
import com.flurry.org.codehaus.jackson.map.ser.impl.ReadOnlyClassToSerializerMap;
import com.flurry.org.codehaus.jackson.map.ser.impl.SerializerCache;
import com.flurry.org.codehaus.jackson.map.ser.impl.UnknownSerializer;
import com.flurry.org.codehaus.jackson.map.ser.std.NullSerializer;
import com.flurry.org.codehaus.jackson.map.ser.std.StdKeySerializers;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import com.flurry.org.codehaus.jackson.map.util.RootNameLookup;
import com.flurry.org.codehaus.jackson.node.ObjectNode;
import com.flurry.org.codehaus.jackson.schema.JsonSchema;
import com.flurry.org.codehaus.jackson.schema.SchemaAware;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

/* loaded from: classes.dex */
public class StdSerializerProvider extends SerializerProvider {
    static final boolean CACHE_UNKNOWN_MAPPINGS = false;
    protected DateFormat _dateFormat;
    protected JsonSerializer _keySerializer;
    protected final ReadOnlyClassToSerializerMap _knownSerializers;
    protected JsonSerializer _nullKeySerializer;
    protected JsonSerializer _nullValueSerializer;
    protected final RootNameLookup _rootNames;
    protected final SerializerCache _serializerCache;
    protected final SerializerFactory _serializerFactory;
    protected JsonSerializer _unknownTypeSerializer;
    public static final JsonSerializer DEFAULT_NULL_KEY_SERIALIZER = new FailingSerializer("Null key for a Map not allowed in JSON (use a converting NullKeySerializer?)");
    @Deprecated
    public static final JsonSerializer DEFAULT_KEY_SERIALIZER = new com.flurry.org.codehaus.jackson.map.ser.std.StdKeySerializer();
    public static final JsonSerializer DEFAULT_UNKNOWN_SERIALIZER = new UnknownSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class WrappedSerializer extends JsonSerializer {
        protected final JsonSerializer _serializer;
        protected final TypeSerializer _typeSerializer;

        public WrappedSerializer(TypeSerializer typeSerializer, JsonSerializer jsonSerializer) {
            this._typeSerializer = typeSerializer;
            this._serializer = jsonSerializer;
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonSerializer
        public Class handledType() {
            return Object.class;
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) {
            this._serializer.serializeWithType(obj, jsonGenerator, serializerProvider, this._typeSerializer);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonSerializer
        public void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) {
            this._serializer.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
        }
    }

    public StdSerializerProvider() {
        super(null);
        this._unknownTypeSerializer = DEFAULT_UNKNOWN_SERIALIZER;
        this._nullValueSerializer = NullSerializer.instance;
        this._nullKeySerializer = DEFAULT_NULL_KEY_SERIALIZER;
        this._serializerFactory = null;
        this._serializerCache = new SerializerCache();
        this._knownSerializers = null;
        this._rootNames = new RootNameLookup();
    }

    protected StdSerializerProvider(SerializationConfig serializationConfig, StdSerializerProvider stdSerializerProvider, SerializerFactory serializerFactory) {
        super(serializationConfig);
        this._unknownTypeSerializer = DEFAULT_UNKNOWN_SERIALIZER;
        this._nullValueSerializer = NullSerializer.instance;
        this._nullKeySerializer = DEFAULT_NULL_KEY_SERIALIZER;
        if (serializationConfig == null) {
            throw new NullPointerException();
        }
        this._serializerFactory = serializerFactory;
        this._serializerCache = stdSerializerProvider._serializerCache;
        this._unknownTypeSerializer = stdSerializerProvider._unknownTypeSerializer;
        this._keySerializer = stdSerializerProvider._keySerializer;
        this._nullValueSerializer = stdSerializerProvider._nullValueSerializer;
        this._nullKeySerializer = stdSerializerProvider._nullKeySerializer;
        this._rootNames = stdSerializerProvider._rootNames;
        this._knownSerializers = this._serializerCache.getReadOnlyLookupMap();
    }

    protected JsonSerializer _createAndCacheUntypedSerializer(JavaType javaType, BeanProperty beanProperty) {
        try {
            JsonSerializer _createUntypedSerializer = _createUntypedSerializer(javaType, beanProperty);
            if (_createUntypedSerializer != null) {
                this._serializerCache.addAndResolveNonTypedSerializer(javaType, _createUntypedSerializer, this);
            }
            return _createUntypedSerializer;
        } catch (IllegalArgumentException e) {
            throw new JsonMappingException(e.getMessage(), null, e);
        }
    }

    protected JsonSerializer _createAndCacheUntypedSerializer(Class cls, BeanProperty beanProperty) {
        try {
            JsonSerializer _createUntypedSerializer = _createUntypedSerializer(this._config.constructType(cls), beanProperty);
            if (_createUntypedSerializer != null) {
                this._serializerCache.addAndResolveNonTypedSerializer(cls, _createUntypedSerializer, this);
            }
            return _createUntypedSerializer;
        } catch (IllegalArgumentException e) {
            throw new JsonMappingException(e.getMessage(), null, e);
        }
    }

    protected JsonSerializer _createUntypedSerializer(JavaType javaType, BeanProperty beanProperty) {
        return this._serializerFactory.createSerializer(this._config, javaType, beanProperty);
    }

    protected JsonSerializer _findExplicitUntypedSerializer(Class cls, BeanProperty beanProperty) {
        JsonSerializer untypedValueSerializer = this._knownSerializers.untypedValueSerializer(cls);
        if (untypedValueSerializer != null) {
            return untypedValueSerializer;
        }
        JsonSerializer untypedValueSerializer2 = this._serializerCache.untypedValueSerializer(cls);
        if (untypedValueSerializer2 == null) {
            try {
                return _createAndCacheUntypedSerializer(cls, beanProperty);
            } catch (Exception e) {
                return null;
            }
        }
        return untypedValueSerializer2;
    }

    protected JsonSerializer _handleContextualResolvable(JsonSerializer jsonSerializer, BeanProperty beanProperty) {
        if (jsonSerializer instanceof ContextualSerializer) {
            JsonSerializer createContextual = ((ContextualSerializer) jsonSerializer).createContextual(this._config, beanProperty);
            if (createContextual == jsonSerializer) {
                createContextual = jsonSerializer;
            } else if (createContextual instanceof ResolvableSerializer) {
                ((ResolvableSerializer) createContextual).resolve(this);
            }
            return createContextual;
        }
        return jsonSerializer;
    }

    protected void _reportIncompatibleRootType(Object obj, JavaType javaType) {
        if (!javaType.isPrimitive() || !ClassUtil.wrapperType(javaType.getRawClass()).isAssignableFrom(obj.getClass())) {
            throw new JsonMappingException("Incompatible types: declared root type (" + javaType + ") vs " + obj.getClass().getName());
        }
    }

    protected void _serializeValue(JsonGenerator jsonGenerator, Object obj) {
        JsonSerializer findTypedValueSerializer;
        boolean isEnabled;
        if (obj == null) {
            findTypedValueSerializer = getNullValueSerializer();
            isEnabled = false;
        } else {
            findTypedValueSerializer = findTypedValueSerializer((Class) obj.getClass(), true, (BeanProperty) null);
            isEnabled = this._config.isEnabled(SerializationConfig.Feature.WRAP_ROOT_VALUE);
            if (isEnabled) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeFieldName(this._rootNames.findRootName(obj.getClass(), this._config));
            }
        }
        try {
            findTypedValueSerializer.serialize(obj, jsonGenerator, this);
            if (isEnabled) {
                jsonGenerator.writeEndObject();
            }
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            String message = e2.getMessage();
            if (message == null) {
                message = "[no message for " + e2.getClass().getName() + "]";
            }
            throw new JsonMappingException(message, e2);
        }
    }

    protected void _serializeValue(JsonGenerator jsonGenerator, Object obj, JavaType javaType) {
        JsonSerializer findTypedValueSerializer;
        boolean isEnabled;
        if (obj == null) {
            findTypedValueSerializer = getNullValueSerializer();
            isEnabled = false;
        } else {
            if (!javaType.getRawClass().isAssignableFrom(obj.getClass())) {
                _reportIncompatibleRootType(obj, javaType);
            }
            findTypedValueSerializer = findTypedValueSerializer(javaType, true, (BeanProperty) null);
            isEnabled = this._config.isEnabled(SerializationConfig.Feature.WRAP_ROOT_VALUE);
            if (isEnabled) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeFieldName(this._rootNames.findRootName(javaType, this._config));
            }
        }
        try {
            findTypedValueSerializer.serialize(obj, jsonGenerator, this);
            if (isEnabled) {
                jsonGenerator.writeEndObject();
            }
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            String message = e2.getMessage();
            if (message == null) {
                message = "[no message for " + e2.getClass().getName() + "]";
            }
            throw new JsonMappingException(message, e2);
        }
    }

    @Override // com.flurry.org.codehaus.jackson.map.SerializerProvider
    public int cachedSerializersCount() {
        return this._serializerCache.size();
    }

    protected StdSerializerProvider createInstance(SerializationConfig serializationConfig, SerializerFactory serializerFactory) {
        return new StdSerializerProvider(serializationConfig, this, serializerFactory);
    }

    @Override // com.flurry.org.codehaus.jackson.map.SerializerProvider
    public void defaultSerializeDateKey(long j, JsonGenerator jsonGenerator) {
        if (isEnabled(SerializationConfig.Feature.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
            jsonGenerator.writeFieldName(String.valueOf(j));
            return;
        }
        if (this._dateFormat == null) {
            this._dateFormat = (DateFormat) this._config.getDateFormat().clone();
        }
        jsonGenerator.writeFieldName(this._dateFormat.format(new Date(j)));
    }

    @Override // com.flurry.org.codehaus.jackson.map.SerializerProvider
    public void defaultSerializeDateKey(Date date, JsonGenerator jsonGenerator) {
        if (isEnabled(SerializationConfig.Feature.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
            jsonGenerator.writeFieldName(String.valueOf(date.getTime()));
            return;
        }
        if (this._dateFormat == null) {
            this._dateFormat = (DateFormat) this._config.getDateFormat().clone();
        }
        jsonGenerator.writeFieldName(this._dateFormat.format(date));
    }

    @Override // com.flurry.org.codehaus.jackson.map.SerializerProvider
    public final void defaultSerializeDateValue(long j, JsonGenerator jsonGenerator) {
        if (isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
            jsonGenerator.writeNumber(j);
            return;
        }
        if (this._dateFormat == null) {
            this._dateFormat = (DateFormat) this._config.getDateFormat().clone();
        }
        jsonGenerator.writeString(this._dateFormat.format(new Date(j)));
    }

    @Override // com.flurry.org.codehaus.jackson.map.SerializerProvider
    public final void defaultSerializeDateValue(Date date, JsonGenerator jsonGenerator) {
        if (isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
            jsonGenerator.writeNumber(date.getTime());
            return;
        }
        if (this._dateFormat == null) {
            this._dateFormat = (DateFormat) this._config.getDateFormat().clone();
        }
        jsonGenerator.writeString(this._dateFormat.format(date));
    }

    @Override // com.flurry.org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer findKeySerializer(JavaType javaType, BeanProperty beanProperty) {
        JsonSerializer createKeySerializer = this._serializerFactory.createKeySerializer(this._config, javaType, beanProperty);
        if (createKeySerializer == null) {
            createKeySerializer = this._keySerializer == null ? StdKeySerializers.getStdKeySerializer(javaType) : this._keySerializer;
        }
        return createKeySerializer instanceof ContextualSerializer ? ((ContextualSerializer) createKeySerializer).createContextual(this._config, beanProperty) : createKeySerializer;
    }

    @Override // com.flurry.org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer findTypedValueSerializer(JavaType javaType, boolean z, BeanProperty beanProperty) {
        JsonSerializer typedValueSerializer = this._knownSerializers.typedValueSerializer(javaType);
        if (typedValueSerializer == null && (typedValueSerializer = this._serializerCache.typedValueSerializer(javaType)) == null) {
            JsonSerializer findValueSerializer = findValueSerializer(javaType, beanProperty);
            TypeSerializer createTypeSerializer = this._serializerFactory.createTypeSerializer(this._config, javaType, beanProperty);
            typedValueSerializer = createTypeSerializer != null ? new WrappedSerializer(createTypeSerializer, findValueSerializer) : findValueSerializer;
            if (z) {
                this._serializerCache.addTypedSerializer(javaType, typedValueSerializer);
            }
        }
        return typedValueSerializer;
    }

    @Override // com.flurry.org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer findTypedValueSerializer(Class cls, boolean z, BeanProperty beanProperty) {
        JsonSerializer typedValueSerializer = this._knownSerializers.typedValueSerializer(cls);
        if (typedValueSerializer == null && (typedValueSerializer = this._serializerCache.typedValueSerializer(cls)) == null) {
            JsonSerializer findValueSerializer = findValueSerializer(cls, beanProperty);
            TypeSerializer createTypeSerializer = this._serializerFactory.createTypeSerializer(this._config, this._config.constructType(cls), beanProperty);
            typedValueSerializer = createTypeSerializer != null ? new WrappedSerializer(createTypeSerializer, findValueSerializer) : findValueSerializer;
            if (z) {
                this._serializerCache.addTypedSerializer(cls, typedValueSerializer);
            }
        }
        return typedValueSerializer;
    }

    @Override // com.flurry.org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer findValueSerializer(JavaType javaType, BeanProperty beanProperty) {
        JsonSerializer untypedValueSerializer = this._knownSerializers.untypedValueSerializer(javaType);
        return (untypedValueSerializer == null && (untypedValueSerializer = this._serializerCache.untypedValueSerializer(javaType)) == null && (untypedValueSerializer = _createAndCacheUntypedSerializer(javaType, beanProperty)) == null) ? getUnknownTypeSerializer(javaType.getRawClass()) : _handleContextualResolvable(untypedValueSerializer, beanProperty);
    }

    @Override // com.flurry.org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer findValueSerializer(Class cls, BeanProperty beanProperty) {
        JsonSerializer untypedValueSerializer = this._knownSerializers.untypedValueSerializer(cls);
        return (untypedValueSerializer == null && (untypedValueSerializer = this._serializerCache.untypedValueSerializer(cls)) == null && (untypedValueSerializer = this._serializerCache.untypedValueSerializer(this._config.constructType(cls))) == null && (untypedValueSerializer = _createAndCacheUntypedSerializer(cls, beanProperty)) == null) ? getUnknownTypeSerializer(cls) : _handleContextualResolvable(untypedValueSerializer, beanProperty);
    }

    @Override // com.flurry.org.codehaus.jackson.map.SerializerProvider
    public void flushCachedSerializers() {
        this._serializerCache.flush();
    }

    @Override // com.flurry.org.codehaus.jackson.map.SerializerProvider
    public JsonSchema generateJsonSchema(Class cls, SerializationConfig serializationConfig, SerializerFactory serializerFactory) {
        if (cls == null) {
            throw new IllegalArgumentException("A class must be provided");
        }
        StdSerializerProvider createInstance = createInstance(serializationConfig, serializerFactory);
        if (createInstance.getClass() != getClass()) {
            throw new IllegalStateException("Broken serializer provider: createInstance returned instance of type " + createInstance.getClass() + "; blueprint of type " + getClass());
        }
        JsonSerializer findValueSerializer = createInstance.findValueSerializer(cls, (BeanProperty) null);
        JsonNode schema = findValueSerializer instanceof SchemaAware ? ((SchemaAware) findValueSerializer).getSchema(createInstance, null) : JsonSchema.getDefaultSchemaNode();
        if (schema instanceof ObjectNode) {
            return new JsonSchema((ObjectNode) schema);
        }
        throw new IllegalArgumentException("Class " + cls.getName() + " would not be serialized as a JSON object and therefore has no schema");
    }

    @Override // com.flurry.org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer getNullKeySerializer() {
        return this._nullKeySerializer;
    }

    @Override // com.flurry.org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer getNullValueSerializer() {
        return this._nullValueSerializer;
    }

    @Override // com.flurry.org.codehaus.jackson.map.SerializerProvider
    public JsonSerializer getUnknownTypeSerializer(Class cls) {
        return this._unknownTypeSerializer;
    }

    @Override // com.flurry.org.codehaus.jackson.map.SerializerProvider
    public boolean hasSerializerFor(SerializationConfig serializationConfig, Class cls, SerializerFactory serializerFactory) {
        return createInstance(serializationConfig, serializerFactory)._findExplicitUntypedSerializer(cls, null) != null;
    }

    @Override // com.flurry.org.codehaus.jackson.map.SerializerProvider
    public final void serializeValue(SerializationConfig serializationConfig, JsonGenerator jsonGenerator, Object obj, SerializerFactory serializerFactory) {
        if (serializerFactory == null) {
            throw new IllegalArgumentException("Can not pass null serializerFactory");
        }
        StdSerializerProvider createInstance = createInstance(serializationConfig, serializerFactory);
        if (createInstance.getClass() != getClass()) {
            throw new IllegalStateException("Broken serializer provider: createInstance returned instance of type " + createInstance.getClass() + "; blueprint of type " + getClass());
        }
        createInstance._serializeValue(jsonGenerator, obj);
    }

    @Override // com.flurry.org.codehaus.jackson.map.SerializerProvider
    public final void serializeValue(SerializationConfig serializationConfig, JsonGenerator jsonGenerator, Object obj, JavaType javaType, SerializerFactory serializerFactory) {
        if (serializerFactory == null) {
            throw new IllegalArgumentException("Can not pass null serializerFactory");
        }
        StdSerializerProvider createInstance = createInstance(serializationConfig, serializerFactory);
        if (createInstance.getClass() != getClass()) {
            throw new IllegalStateException("Broken serializer provider: createInstance returned instance of type " + createInstance.getClass() + "; blueprint of type " + getClass());
        }
        createInstance._serializeValue(jsonGenerator, obj, javaType);
    }

    @Override // com.flurry.org.codehaus.jackson.map.SerializerProvider
    public void setDefaultKeySerializer(JsonSerializer jsonSerializer) {
        if (jsonSerializer == null) {
            throw new IllegalArgumentException("Can not pass null JsonSerializer");
        }
        this._keySerializer = jsonSerializer;
    }

    @Override // com.flurry.org.codehaus.jackson.map.SerializerProvider
    public void setNullKeySerializer(JsonSerializer jsonSerializer) {
        if (jsonSerializer == null) {
            throw new IllegalArgumentException("Can not pass null JsonSerializer");
        }
        this._nullKeySerializer = jsonSerializer;
    }

    @Override // com.flurry.org.codehaus.jackson.map.SerializerProvider
    public void setNullValueSerializer(JsonSerializer jsonSerializer) {
        if (jsonSerializer == null) {
            throw new IllegalArgumentException("Can not pass null JsonSerializer");
        }
        this._nullValueSerializer = jsonSerializer;
    }
}
