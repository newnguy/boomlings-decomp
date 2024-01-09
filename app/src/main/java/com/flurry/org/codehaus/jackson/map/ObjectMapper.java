package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.FormatSchema;
import com.flurry.org.codehaus.jackson.JsonEncoding;
import com.flurry.org.codehaus.jackson.JsonFactory;
import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.ObjectCodec;
import com.flurry.org.codehaus.jackson.PrettyPrinter;
import com.flurry.org.codehaus.jackson.Version;
import com.flurry.org.codehaus.jackson.Versioned;
import com.flurry.org.codehaus.jackson.annotate.JsonAutoDetect;
import com.flurry.org.codehaus.jackson.annotate.JsonMethod;
import com.flurry.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.flurry.org.codehaus.jackson.io.SegmentedStringWriter;
import com.flurry.org.codehaus.jackson.io.SerializedString;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.Module;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.annotate.JsonSerialize;
import com.flurry.org.codehaus.jackson.map.deser.BeanDeserializerModifier;
import com.flurry.org.codehaus.jackson.map.deser.StdDeserializationContext;
import com.flurry.org.codehaus.jackson.map.deser.StdDeserializerProvider;
import com.flurry.org.codehaus.jackson.map.deser.ValueInstantiators;
import com.flurry.org.codehaus.jackson.map.introspect.BasicClassIntrospector;
import com.flurry.org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.introspect.VisibilityChecker;
import com.flurry.org.codehaus.jackson.map.jsontype.NamedType;
import com.flurry.org.codehaus.jackson.map.jsontype.SubtypeResolver;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeIdResolver;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import com.flurry.org.codehaus.jackson.map.jsontype.impl.StdSubtypeResolver;
import com.flurry.org.codehaus.jackson.map.jsontype.impl.StdTypeResolverBuilder;
import com.flurry.org.codehaus.jackson.map.ser.BeanSerializerFactory;
import com.flurry.org.codehaus.jackson.map.ser.BeanSerializerModifier;
import com.flurry.org.codehaus.jackson.map.ser.FilterProvider;
import com.flurry.org.codehaus.jackson.map.ser.StdSerializerProvider;
import com.flurry.org.codehaus.jackson.map.type.SimpleType;
import com.flurry.org.codehaus.jackson.map.type.TypeFactory;
import com.flurry.org.codehaus.jackson.map.type.TypeModifier;
import com.flurry.org.codehaus.jackson.node.ArrayNode;
import com.flurry.org.codehaus.jackson.node.JsonNodeFactory;
import com.flurry.org.codehaus.jackson.node.NullNode;
import com.flurry.org.codehaus.jackson.node.ObjectNode;
import com.flurry.org.codehaus.jackson.node.TreeTraversingParser;
import com.flurry.org.codehaus.jackson.schema.JsonSchema;
import com.flurry.org.codehaus.jackson.type.JavaType;
import com.flurry.org.codehaus.jackson.type.TypeReference;
import com.flurry.org.codehaus.jackson.util.ByteArrayBuilder;
import com.flurry.org.codehaus.jackson.util.DefaultPrettyPrinter;
import com.flurry.org.codehaus.jackson.util.TokenBuffer;
import com.flurry.org.codehaus.jackson.util.VersionUtil;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.URL;
import java.text.DateFormat;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class ObjectMapper extends ObjectCodec implements Versioned {
    protected DeserializationConfig _deserializationConfig;
    protected DeserializerProvider _deserializerProvider;
    protected InjectableValues _injectableValues;
    protected final JsonFactory _jsonFactory;
    protected final ConcurrentHashMap _rootDeserializers;
    protected SerializationConfig _serializationConfig;
    protected SerializerFactory _serializerFactory;
    protected SerializerProvider _serializerProvider;
    protected SubtypeResolver _subtypeResolver;
    protected TypeFactory _typeFactory;
    private static final JavaType JSON_NODE_TYPE = SimpleType.constructUnsafe(JsonNode.class);
    protected static final ClassIntrospector DEFAULT_INTROSPECTOR = BasicClassIntrospector.instance;
    protected static final AnnotationIntrospector DEFAULT_ANNOTATION_INTROSPECTOR = new JacksonAnnotationIntrospector();
    protected static final VisibilityChecker STD_VISIBILITY_CHECKER = VisibilityChecker.Std.defaultInstance();

    /* loaded from: classes.dex */
    public class DefaultTypeResolverBuilder extends StdTypeResolverBuilder {
        protected final DefaultTyping _appliesFor;

        public DefaultTypeResolverBuilder(DefaultTyping defaultTyping) {
            this._appliesFor = defaultTyping;
        }

        @Override // com.flurry.org.codehaus.jackson.map.jsontype.impl.StdTypeResolverBuilder, com.flurry.org.codehaus.jackson.map.jsontype.TypeResolverBuilder
        public TypeDeserializer buildTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, Collection collection, BeanProperty beanProperty) {
            if (useForType(javaType)) {
                return super.buildTypeDeserializer(deserializationConfig, javaType, collection, beanProperty);
            }
            return null;
        }

        @Override // com.flurry.org.codehaus.jackson.map.jsontype.impl.StdTypeResolverBuilder, com.flurry.org.codehaus.jackson.map.jsontype.TypeResolverBuilder
        public TypeSerializer buildTypeSerializer(SerializationConfig serializationConfig, JavaType javaType, Collection collection, BeanProperty beanProperty) {
            if (useForType(javaType)) {
                return super.buildTypeSerializer(serializationConfig, javaType, collection, beanProperty);
            }
            return null;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public boolean useForType(JavaType javaType) {
            boolean z = false;
            switch (this._appliesFor) {
                case NON_CONCRETE_AND_ARRAYS:
                    if (javaType.isArrayType()) {
                        javaType = javaType.getContentType();
                        break;
                    }
                    break;
                case OBJECT_AND_NON_CONCRETE:
                    break;
                case NON_FINAL:
                    if (javaType.isArrayType()) {
                        javaType = javaType.getContentType();
                    }
                    return !javaType.isFinal();
                default:
                    return javaType.getRawClass() == Object.class;
            }
            if (javaType.getRawClass() == Object.class || !javaType.isConcrete()) {
                z = true;
            }
            return z;
        }
    }

    /* loaded from: classes.dex */
    public enum DefaultTyping {
        JAVA_LANG_OBJECT,
        OBJECT_AND_NON_CONCRETE,
        NON_CONCRETE_AND_ARRAYS,
        NON_FINAL
    }

    public ObjectMapper() {
        this(null, null, null);
    }

    public ObjectMapper(JsonFactory jsonFactory) {
        this(jsonFactory, null, null);
    }

    public ObjectMapper(JsonFactory jsonFactory, SerializerProvider serializerProvider, DeserializerProvider deserializerProvider) {
        this(jsonFactory, serializerProvider, deserializerProvider, null, null);
    }

    public ObjectMapper(JsonFactory jsonFactory, SerializerProvider serializerProvider, DeserializerProvider deserializerProvider, SerializationConfig serializationConfig, DeserializationConfig deserializationConfig) {
        this._rootDeserializers = new ConcurrentHashMap(64, 0.6f, 2);
        if (jsonFactory == null) {
            this._jsonFactory = new MappingJsonFactory(this);
        } else {
            this._jsonFactory = jsonFactory;
            if (jsonFactory.getCodec() == null) {
                this._jsonFactory.setCodec(this);
            }
        }
        this._typeFactory = TypeFactory.defaultInstance();
        this._serializationConfig = serializationConfig == null ? new SerializationConfig(DEFAULT_INTROSPECTOR, DEFAULT_ANNOTATION_INTROSPECTOR, STD_VISIBILITY_CHECKER, null, null, this._typeFactory, null) : serializationConfig;
        this._deserializationConfig = deserializationConfig == null ? new DeserializationConfig(DEFAULT_INTROSPECTOR, DEFAULT_ANNOTATION_INTROSPECTOR, STD_VISIBILITY_CHECKER, null, null, this._typeFactory, null) : deserializationConfig;
        this._serializerProvider = serializerProvider == null ? new StdSerializerProvider() : serializerProvider;
        this._deserializerProvider = deserializerProvider == null ? new StdDeserializerProvider() : deserializerProvider;
        this._serializerFactory = BeanSerializerFactory.instance;
    }

    @Deprecated
    public ObjectMapper(SerializerFactory serializerFactory) {
        this(null, null, null);
        setSerializerFactory(serializerFactory);
    }

    private final void _configAndWriteCloseable(JsonGenerator jsonGenerator, Object obj, SerializationConfig serializationConfig) {
        Closeable closeable;
        Throwable th;
        Closeable closeable2 = (Closeable) obj;
        try {
            this._serializerProvider.serializeValue(serializationConfig, jsonGenerator, obj, this._serializerFactory);
            AutoCloseable autoCloseable = null;
            try {
                jsonGenerator.close();
                Closeable closeable3 = null;
                try {
                    closeable2.close();
                    if (0 != 0) {
                        try {
                            autoCloseable.close();
                        } catch (IOException e) {
                        }
                    }
                    if (0 != 0) {
                        try {
                            closeable3.close();
                        } catch (IOException e2) {
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    closeable = null;
                    jsonGenerator = null;
                    if (jsonGenerator != null) {
                        try {
                            jsonGenerator.close();
                        } catch (IOException e3) {
                        }
                    }
                    if (closeable != null) {
                        try {
                            closeable.close();
                        } catch (IOException e4) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                jsonGenerator = null;
                th = th3;
                closeable = closeable2;
            }
        } catch (Throwable th4) {
            closeable = closeable2;
            th = th4;
        }
    }

    private final void _writeCloseableValue(JsonGenerator jsonGenerator, Object obj, SerializationConfig serializationConfig) {
        Closeable closeable;
        Throwable th;
        Closeable closeable2 = (Closeable) obj;
        try {
            this._serializerProvider.serializeValue(serializationConfig, jsonGenerator, obj, this._serializerFactory);
            if (serializationConfig.isEnabled(SerializationConfig.Feature.FLUSH_AFTER_WRITE_VALUE)) {
                jsonGenerator.flush();
            }
            closeable = null;
        } catch (Throwable th2) {
            closeable = closeable2;
            th = th2;
        }
        try {
            closeable2.close();
            if (0 != 0) {
                try {
                    closeable.close();
                } catch (IOException e) {
                }
            }
        } catch (Throwable th3) {
            th = th3;
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e2) {
                }
            }
            throw th;
        }
    }

    protected final void _configAndWriteValue(JsonGenerator jsonGenerator, Object obj) {
        SerializationConfig copySerializationConfig = copySerializationConfig();
        if (copySerializationConfig.isEnabled(SerializationConfig.Feature.INDENT_OUTPUT)) {
            jsonGenerator.useDefaultPrettyPrinter();
        }
        if (copySerializationConfig.isEnabled(SerializationConfig.Feature.CLOSE_CLOSEABLE) && (obj instanceof Closeable)) {
            _configAndWriteCloseable(jsonGenerator, obj, copySerializationConfig);
            return;
        }
        boolean z = false;
        try {
            this._serializerProvider.serializeValue(copySerializationConfig, jsonGenerator, obj, this._serializerFactory);
            z = true;
            jsonGenerator.close();
        } catch (Throwable th) {
            if (!z) {
                try {
                    jsonGenerator.close();
                } catch (IOException e) {
                }
            }
            throw th;
        }
    }

    protected final void _configAndWriteValue(JsonGenerator jsonGenerator, Object obj, Class cls) {
        SerializationConfig withView = copySerializationConfig().withView(cls);
        if (withView.isEnabled(SerializationConfig.Feature.INDENT_OUTPUT)) {
            jsonGenerator.useDefaultPrettyPrinter();
        }
        if (withView.isEnabled(SerializationConfig.Feature.CLOSE_CLOSEABLE) && (obj instanceof Closeable)) {
            _configAndWriteCloseable(jsonGenerator, obj, withView);
            return;
        }
        boolean z = false;
        try {
            this._serializerProvider.serializeValue(withView, jsonGenerator, obj, this._serializerFactory);
            z = true;
            jsonGenerator.close();
        } catch (Throwable th) {
            if (!z) {
                try {
                    jsonGenerator.close();
                } catch (IOException e) {
                }
            }
            throw th;
        }
    }

    protected Object _convert(Object obj, JavaType javaType) {
        if (obj == null) {
            return null;
        }
        TokenBuffer tokenBuffer = new TokenBuffer(this);
        try {
            writeValue(tokenBuffer, obj);
            JsonParser asParser = tokenBuffer.asParser();
            Object readValue = readValue(asParser, javaType);
            asParser.close();
            return readValue;
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    protected DeserializationContext _createDeserializationContext(JsonParser jsonParser, DeserializationConfig deserializationConfig) {
        return new StdDeserializationContext(deserializationConfig, jsonParser, this._deserializerProvider, this._injectableValues);
    }

    protected PrettyPrinter _defaultPrettyPrinter() {
        return new DefaultPrettyPrinter();
    }

    protected JsonDeserializer _findRootDeserializer(DeserializationConfig deserializationConfig, JavaType javaType) {
        JsonDeserializer jsonDeserializer = (JsonDeserializer) this._rootDeserializers.get(javaType);
        if (jsonDeserializer == null) {
            jsonDeserializer = this._deserializerProvider.findTypedValueDeserializer(deserializationConfig, javaType, null);
            if (jsonDeserializer == null) {
                throw new JsonMappingException("Can not find a deserializer for type " + javaType);
            }
            this._rootDeserializers.put(javaType, jsonDeserializer);
        }
        return jsonDeserializer;
    }

    protected JsonToken _initForReading(JsonParser jsonParser) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == null && (currentToken = jsonParser.nextToken()) == null) {
            throw new EOFException("No content to map to Object due to end of input");
        }
        return currentToken;
    }

    protected Object _readMapAndClose(JsonParser jsonParser, JavaType javaType) {
        Object obj;
        try {
            JsonToken _initForReading = _initForReading(jsonParser);
            if (_initForReading == JsonToken.VALUE_NULL) {
                obj = _findRootDeserializer(this._deserializationConfig, javaType).getNullValue();
            } else if (_initForReading == JsonToken.END_ARRAY || _initForReading == JsonToken.END_OBJECT) {
                obj = null;
            } else {
                DeserializationConfig copyDeserializationConfig = copyDeserializationConfig();
                DeserializationContext _createDeserializationContext = _createDeserializationContext(jsonParser, copyDeserializationConfig);
                JsonDeserializer _findRootDeserializer = _findRootDeserializer(copyDeserializationConfig, javaType);
                obj = copyDeserializationConfig.isEnabled(DeserializationConfig.Feature.UNWRAP_ROOT_VALUE) ? _unwrapAndDeserialize(jsonParser, javaType, _createDeserializationContext, _findRootDeserializer) : _findRootDeserializer.deserialize(jsonParser, _createDeserializationContext);
            }
            jsonParser.clearCurrentToken();
            return obj;
        } finally {
            try {
                jsonParser.close();
            } catch (IOException e) {
            }
        }
    }

    protected Object _readValue(DeserializationConfig deserializationConfig, JsonParser jsonParser, JavaType javaType) {
        Object obj;
        JsonToken _initForReading = _initForReading(jsonParser);
        if (_initForReading == JsonToken.VALUE_NULL) {
            obj = _findRootDeserializer(deserializationConfig, javaType).getNullValue();
        } else if (_initForReading == JsonToken.END_ARRAY || _initForReading == JsonToken.END_OBJECT) {
            obj = null;
        } else {
            DeserializationContext _createDeserializationContext = _createDeserializationContext(jsonParser, deserializationConfig);
            JsonDeserializer _findRootDeserializer = _findRootDeserializer(deserializationConfig, javaType);
            obj = deserializationConfig.isEnabled(DeserializationConfig.Feature.UNWRAP_ROOT_VALUE) ? _unwrapAndDeserialize(jsonParser, javaType, _createDeserializationContext, _findRootDeserializer) : _findRootDeserializer.deserialize(jsonParser, _createDeserializationContext);
        }
        jsonParser.clearCurrentToken();
        return obj;
    }

    protected Object _unwrapAndDeserialize(JsonParser jsonParser, JavaType javaType, DeserializationContext deserializationContext, JsonDeserializer jsonDeserializer) {
        SerializedString findExpectedRootName = this._deserializerProvider.findExpectedRootName(deserializationContext.getConfig(), javaType);
        if (jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
            throw JsonMappingException.from(jsonParser, "Current token not START_OBJECT (needed to unwrap root name '" + findExpectedRootName + "'), but " + jsonParser.getCurrentToken());
        }
        if (jsonParser.nextToken() != JsonToken.FIELD_NAME) {
            throw JsonMappingException.from(jsonParser, "Current token not FIELD_NAME (to contain expected root name '" + findExpectedRootName + "'), but " + jsonParser.getCurrentToken());
        }
        String currentName = jsonParser.getCurrentName();
        if (findExpectedRootName.getValue().equals(currentName)) {
            jsonParser.nextToken();
            Object deserialize = jsonDeserializer.deserialize(jsonParser, deserializationContext);
            if (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                throw JsonMappingException.from(jsonParser, "Current token not END_OBJECT (to match wrapper object with root name '" + findExpectedRootName + "'), but " + jsonParser.getCurrentToken());
            }
            return deserialize;
        }
        throw JsonMappingException.from(jsonParser, "Root name '" + currentName + "' does not match expected ('" + findExpectedRootName + "') for type " + javaType);
    }

    public boolean canDeserialize(JavaType javaType) {
        return this._deserializerProvider.hasValueDeserializerFor(copyDeserializationConfig(), javaType);
    }

    public boolean canSerialize(Class cls) {
        return this._serializerProvider.hasSerializerFor(copySerializationConfig(), cls, this._serializerFactory);
    }

    public ObjectMapper configure(JsonGenerator.Feature feature, boolean z) {
        this._jsonFactory.configure(feature, z);
        return this;
    }

    public ObjectMapper configure(JsonParser.Feature feature, boolean z) {
        this._jsonFactory.configure(feature, z);
        return this;
    }

    public ObjectMapper configure(DeserializationConfig.Feature feature, boolean z) {
        this._deserializationConfig.set(feature, z);
        return this;
    }

    public ObjectMapper configure(SerializationConfig.Feature feature, boolean z) {
        this._serializationConfig.set(feature, z);
        return this;
    }

    public JavaType constructType(Type type) {
        return this._typeFactory.constructType(type);
    }

    public Object convertValue(Object obj, JavaType javaType) {
        return _convert(obj, javaType);
    }

    public Object convertValue(Object obj, TypeReference typeReference) {
        return _convert(obj, this._typeFactory.constructType(typeReference));
    }

    public Object convertValue(Object obj, Class cls) {
        return _convert(obj, this._typeFactory.constructType(cls));
    }

    public DeserializationConfig copyDeserializationConfig() {
        return this._deserializationConfig.createUnshared(this._subtypeResolver).passSerializationFeatures(this._serializationConfig._featureFlags);
    }

    public SerializationConfig copySerializationConfig() {
        return this._serializationConfig.createUnshared(this._subtypeResolver);
    }

    @Override // com.flurry.org.codehaus.jackson.ObjectCodec
    public ArrayNode createArrayNode() {
        return this._deserializationConfig.getNodeFactory().arrayNode();
    }

    @Override // com.flurry.org.codehaus.jackson.ObjectCodec
    public ObjectNode createObjectNode() {
        return this._deserializationConfig.getNodeFactory().objectNode();
    }

    @Deprecated
    public ObjectWriter defaultPrettyPrintingWriter() {
        return writerWithDefaultPrettyPrinter();
    }

    public ObjectMapper disable(DeserializationConfig.Feature... featureArr) {
        this._deserializationConfig = this._deserializationConfig.without(featureArr);
        return this;
    }

    public ObjectMapper disable(SerializationConfig.Feature... featureArr) {
        this._serializationConfig = this._serializationConfig.without(featureArr);
        return this;
    }

    public ObjectMapper disableDefaultTyping() {
        return setDefaultTyping(null);
    }

    public ObjectMapper enable(DeserializationConfig.Feature... featureArr) {
        this._deserializationConfig = this._deserializationConfig.with(featureArr);
        return this;
    }

    public ObjectMapper enable(SerializationConfig.Feature... featureArr) {
        this._serializationConfig = this._serializationConfig.with(featureArr);
        return this;
    }

    public ObjectMapper enableDefaultTyping() {
        return enableDefaultTyping(DefaultTyping.OBJECT_AND_NON_CONCRETE);
    }

    public ObjectMapper enableDefaultTyping(DefaultTyping defaultTyping) {
        return enableDefaultTyping(defaultTyping, JsonTypeInfo.As.WRAPPER_ARRAY);
    }

    public ObjectMapper enableDefaultTyping(DefaultTyping defaultTyping, JsonTypeInfo.As as) {
        return setDefaultTyping(new DefaultTypeResolverBuilder(defaultTyping).init(JsonTypeInfo.Id.CLASS, (TypeIdResolver) null).inclusion(as));
    }

    public ObjectMapper enableDefaultTypingAsProperty(DefaultTyping defaultTyping, String str) {
        return setDefaultTyping(new DefaultTypeResolverBuilder(defaultTyping).init(JsonTypeInfo.Id.CLASS, (TypeIdResolver) null).inclusion(JsonTypeInfo.As.PROPERTY).typeProperty(str));
    }

    @Deprecated
    public ObjectWriter filteredWriter(FilterProvider filterProvider) {
        return writer(filterProvider);
    }

    public JsonSchema generateJsonSchema(Class cls) {
        return generateJsonSchema(cls, copySerializationConfig());
    }

    public JsonSchema generateJsonSchema(Class cls, SerializationConfig serializationConfig) {
        return this._serializerProvider.generateJsonSchema(cls, serializationConfig, this._serializerFactory);
    }

    public DeserializationConfig getDeserializationConfig() {
        return this._deserializationConfig;
    }

    public DeserializerProvider getDeserializerProvider() {
        return this._deserializerProvider;
    }

    public JsonFactory getJsonFactory() {
        return this._jsonFactory;
    }

    public JsonNodeFactory getNodeFactory() {
        return this._deserializationConfig.getNodeFactory();
    }

    public SerializationConfig getSerializationConfig() {
        return this._serializationConfig;
    }

    public SerializerProvider getSerializerProvider() {
        return this._serializerProvider;
    }

    public SubtypeResolver getSubtypeResolver() {
        if (this._subtypeResolver == null) {
            this._subtypeResolver = new StdSubtypeResolver();
        }
        return this._subtypeResolver;
    }

    public TypeFactory getTypeFactory() {
        return this._typeFactory;
    }

    public VisibilityChecker getVisibilityChecker() {
        return this._serializationConfig.getDefaultVisibilityChecker();
    }

    public boolean isEnabled(JsonGenerator.Feature feature) {
        return this._jsonFactory.isEnabled(feature);
    }

    public boolean isEnabled(JsonParser.Feature feature) {
        return this._jsonFactory.isEnabled(feature);
    }

    public boolean isEnabled(DeserializationConfig.Feature feature) {
        return this._deserializationConfig.isEnabled(feature);
    }

    public boolean isEnabled(SerializationConfig.Feature feature) {
        return this._serializationConfig.isEnabled(feature);
    }

    @Deprecated
    public ObjectWriter prettyPrintingWriter(PrettyPrinter prettyPrinter) {
        return writer(prettyPrinter);
    }

    @Override // com.flurry.org.codehaus.jackson.ObjectCodec
    public JsonNode readTree(JsonParser jsonParser) {
        DeserializationConfig copyDeserializationConfig = copyDeserializationConfig();
        if (jsonParser.getCurrentToken() == null && jsonParser.nextToken() == null) {
            return null;
        }
        JsonNode jsonNode = (JsonNode) _readValue(copyDeserializationConfig, jsonParser, JSON_NODE_TYPE);
        return jsonNode == null ? getNodeFactory().nullNode() : jsonNode;
    }

    public JsonNode readTree(JsonParser jsonParser, DeserializationConfig deserializationConfig) {
        JsonNode jsonNode = (JsonNode) _readValue(deserializationConfig, jsonParser, JSON_NODE_TYPE);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public JsonNode readTree(File file) {
        JsonNode jsonNode = (JsonNode) _readMapAndClose(this._jsonFactory.createJsonParser(file), JSON_NODE_TYPE);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public JsonNode readTree(InputStream inputStream) {
        JsonNode jsonNode = (JsonNode) _readMapAndClose(this._jsonFactory.createJsonParser(inputStream), JSON_NODE_TYPE);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public JsonNode readTree(Reader reader) {
        JsonNode jsonNode = (JsonNode) _readMapAndClose(this._jsonFactory.createJsonParser(reader), JSON_NODE_TYPE);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public JsonNode readTree(String str) {
        JsonNode jsonNode = (JsonNode) _readMapAndClose(this._jsonFactory.createJsonParser(str), JSON_NODE_TYPE);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public JsonNode readTree(URL url) {
        JsonNode jsonNode = (JsonNode) _readMapAndClose(this._jsonFactory.createJsonParser(url), JSON_NODE_TYPE);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public JsonNode readTree(byte[] bArr) {
        JsonNode jsonNode = (JsonNode) _readMapAndClose(this._jsonFactory.createJsonParser(bArr), JSON_NODE_TYPE);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public Object readValue(JsonNode jsonNode, JavaType javaType) {
        return _readValue(copyDeserializationConfig(), treeAsTokens(jsonNode), javaType);
    }

    public Object readValue(JsonNode jsonNode, TypeReference typeReference) {
        return _readValue(copyDeserializationConfig(), treeAsTokens(jsonNode), this._typeFactory.constructType(typeReference));
    }

    public Object readValue(JsonNode jsonNode, Class cls) {
        return _readValue(copyDeserializationConfig(), treeAsTokens(jsonNode), this._typeFactory.constructType(cls));
    }

    @Override // com.flurry.org.codehaus.jackson.ObjectCodec
    public Object readValue(JsonParser jsonParser, JavaType javaType) {
        return _readValue(copyDeserializationConfig(), jsonParser, javaType);
    }

    public Object readValue(JsonParser jsonParser, JavaType javaType, DeserializationConfig deserializationConfig) {
        return _readValue(deserializationConfig, jsonParser, javaType);
    }

    @Override // com.flurry.org.codehaus.jackson.ObjectCodec
    public Object readValue(JsonParser jsonParser, TypeReference typeReference) {
        return _readValue(copyDeserializationConfig(), jsonParser, this._typeFactory.constructType(typeReference));
    }

    public Object readValue(JsonParser jsonParser, TypeReference typeReference, DeserializationConfig deserializationConfig) {
        return _readValue(deserializationConfig, jsonParser, this._typeFactory.constructType(typeReference));
    }

    @Override // com.flurry.org.codehaus.jackson.ObjectCodec
    public Object readValue(JsonParser jsonParser, Class cls) {
        return _readValue(copyDeserializationConfig(), jsonParser, this._typeFactory.constructType(cls));
    }

    public Object readValue(JsonParser jsonParser, Class cls, DeserializationConfig deserializationConfig) {
        return _readValue(deserializationConfig, jsonParser, this._typeFactory.constructType(cls));
    }

    public Object readValue(File file, JavaType javaType) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(file), javaType);
    }

    public Object readValue(File file, TypeReference typeReference) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(file), this._typeFactory.constructType(typeReference));
    }

    public Object readValue(File file, Class cls) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(file), this._typeFactory.constructType(cls));
    }

    public Object readValue(InputStream inputStream, JavaType javaType) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(inputStream), javaType);
    }

    public Object readValue(InputStream inputStream, TypeReference typeReference) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(inputStream), this._typeFactory.constructType(typeReference));
    }

    public Object readValue(InputStream inputStream, Class cls) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(inputStream), this._typeFactory.constructType(cls));
    }

    public Object readValue(Reader reader, JavaType javaType) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(reader), javaType);
    }

    public Object readValue(Reader reader, TypeReference typeReference) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(reader), this._typeFactory.constructType(typeReference));
    }

    public Object readValue(Reader reader, Class cls) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(reader), this._typeFactory.constructType(cls));
    }

    public Object readValue(String str, JavaType javaType) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(str), javaType);
    }

    public Object readValue(String str, TypeReference typeReference) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(str), this._typeFactory.constructType(typeReference));
    }

    public Object readValue(String str, Class cls) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(str), this._typeFactory.constructType(cls));
    }

    public Object readValue(URL url, JavaType javaType) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(url), javaType);
    }

    public Object readValue(URL url, TypeReference typeReference) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(url), this._typeFactory.constructType(typeReference));
    }

    public Object readValue(URL url, Class cls) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(url), this._typeFactory.constructType(cls));
    }

    public Object readValue(byte[] bArr, int i, int i2, JavaType javaType) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(bArr, i, i2), javaType);
    }

    public Object readValue(byte[] bArr, int i, int i2, TypeReference typeReference) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(bArr, i, i2), this._typeFactory.constructType(typeReference));
    }

    public Object readValue(byte[] bArr, int i, int i2, Class cls) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(bArr, i, i2), this._typeFactory.constructType(cls));
    }

    public Object readValue(byte[] bArr, JavaType javaType) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(bArr), javaType);
    }

    public Object readValue(byte[] bArr, TypeReference typeReference) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(bArr), this._typeFactory.constructType(typeReference));
    }

    public Object readValue(byte[] bArr, Class cls) {
        return _readMapAndClose(this._jsonFactory.createJsonParser(bArr), this._typeFactory.constructType(cls));
    }

    @Override // com.flurry.org.codehaus.jackson.ObjectCodec
    public MappingIterator readValues(JsonParser jsonParser, JavaType javaType) {
        DeserializationConfig copyDeserializationConfig = copyDeserializationConfig();
        return new MappingIterator(javaType, jsonParser, _createDeserializationContext(jsonParser, copyDeserializationConfig), _findRootDeserializer(copyDeserializationConfig, javaType), false, null);
    }

    @Override // com.flurry.org.codehaus.jackson.ObjectCodec
    public MappingIterator readValues(JsonParser jsonParser, TypeReference typeReference) {
        return readValues(jsonParser, this._typeFactory.constructType(typeReference));
    }

    @Override // com.flurry.org.codehaus.jackson.ObjectCodec
    public MappingIterator readValues(JsonParser jsonParser, Class cls) {
        return readValues(jsonParser, this._typeFactory.constructType(cls));
    }

    public ObjectReader reader() {
        return new ObjectReader(this, copyDeserializationConfig()).withInjectableValues(this._injectableValues);
    }

    public ObjectReader reader(FormatSchema formatSchema) {
        return new ObjectReader(this, copyDeserializationConfig(), (JavaType) null, (Object) null, formatSchema, this._injectableValues);
    }

    public ObjectReader reader(InjectableValues injectableValues) {
        return new ObjectReader(this, copyDeserializationConfig(), (JavaType) null, (Object) null, (FormatSchema) null, injectableValues);
    }

    public ObjectReader reader(JsonNodeFactory jsonNodeFactory) {
        return new ObjectReader(this, copyDeserializationConfig()).withNodeFactory(jsonNodeFactory);
    }

    public ObjectReader reader(JavaType javaType) {
        return new ObjectReader(this, copyDeserializationConfig(), javaType, (Object) null, (FormatSchema) null, this._injectableValues);
    }

    public ObjectReader reader(TypeReference typeReference) {
        return reader(this._typeFactory.constructType(typeReference));
    }

    public ObjectReader reader(Class cls) {
        return reader(this._typeFactory.constructType(cls));
    }

    public ObjectReader readerForUpdating(Object obj) {
        return new ObjectReader(this, copyDeserializationConfig(), this._typeFactory.constructType(obj.getClass()), obj, (FormatSchema) null, this._injectableValues);
    }

    public void registerModule(Module module) {
        if (module.getModuleName() == null) {
            throw new IllegalArgumentException("Module without defined name");
        }
        if (module.version() == null) {
            throw new IllegalArgumentException("Module without defined version");
        }
        module.setupModule(new Module.SetupContext() { // from class: com.flurry.org.codehaus.jackson.map.ObjectMapper.1
            @Override // com.flurry.org.codehaus.jackson.map.Module.SetupContext
            public void addAbstractTypeResolver(AbstractTypeResolver abstractTypeResolver) {
                this._deserializerProvider = this._deserializerProvider.withAbstractTypeResolver(abstractTypeResolver);
            }

            @Override // com.flurry.org.codehaus.jackson.map.Module.SetupContext
            public void addBeanDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier) {
                this._deserializerProvider = this._deserializerProvider.withDeserializerModifier(beanDeserializerModifier);
            }

            @Override // com.flurry.org.codehaus.jackson.map.Module.SetupContext
            public void addBeanSerializerModifier(BeanSerializerModifier beanSerializerModifier) {
                this._serializerFactory = this._serializerFactory.withSerializerModifier(beanSerializerModifier);
            }

            @Override // com.flurry.org.codehaus.jackson.map.Module.SetupContext
            public void addDeserializers(Deserializers deserializers) {
                this._deserializerProvider = this._deserializerProvider.withAdditionalDeserializers(deserializers);
            }

            @Override // com.flurry.org.codehaus.jackson.map.Module.SetupContext
            public void addKeyDeserializers(KeyDeserializers keyDeserializers) {
                this._deserializerProvider = this._deserializerProvider.withAdditionalKeyDeserializers(keyDeserializers);
            }

            @Override // com.flurry.org.codehaus.jackson.map.Module.SetupContext
            public void addKeySerializers(Serializers serializers) {
                this._serializerFactory = this._serializerFactory.withAdditionalKeySerializers(serializers);
            }

            @Override // com.flurry.org.codehaus.jackson.map.Module.SetupContext
            public void addSerializers(Serializers serializers) {
                this._serializerFactory = this._serializerFactory.withAdditionalSerializers(serializers);
            }

            @Override // com.flurry.org.codehaus.jackson.map.Module.SetupContext
            public void addTypeModifier(TypeModifier typeModifier) {
                this.setTypeFactory(this._typeFactory.withModifier(typeModifier));
            }

            @Override // com.flurry.org.codehaus.jackson.map.Module.SetupContext
            public void addValueInstantiators(ValueInstantiators valueInstantiators) {
                this._deserializerProvider = this._deserializerProvider.withValueInstantiators(valueInstantiators);
            }

            @Override // com.flurry.org.codehaus.jackson.map.Module.SetupContext
            public void appendAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
                this._deserializationConfig = this._deserializationConfig.withAppendedAnnotationIntrospector(annotationIntrospector);
                this._serializationConfig = this._serializationConfig.withAppendedAnnotationIntrospector(annotationIntrospector);
            }

            @Override // com.flurry.org.codehaus.jackson.map.Module.SetupContext
            public DeserializationConfig getDeserializationConfig() {
                return this.getDeserializationConfig();
            }

            @Override // com.flurry.org.codehaus.jackson.map.Module.SetupContext
            public Version getMapperVersion() {
                return ObjectMapper.this.version();
            }

            @Override // com.flurry.org.codehaus.jackson.map.Module.SetupContext
            public SerializationConfig getSerializationConfig() {
                return this.getSerializationConfig();
            }

            @Override // com.flurry.org.codehaus.jackson.map.Module.SetupContext
            public void insertAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
                this._deserializationConfig = this._deserializationConfig.withInsertedAnnotationIntrospector(annotationIntrospector);
                this._serializationConfig = this._serializationConfig.withInsertedAnnotationIntrospector(annotationIntrospector);
            }

            @Override // com.flurry.org.codehaus.jackson.map.Module.SetupContext
            public boolean isEnabled(JsonGenerator.Feature feature) {
                return this.isEnabled(feature);
            }

            @Override // com.flurry.org.codehaus.jackson.map.Module.SetupContext
            public boolean isEnabled(JsonParser.Feature feature) {
                return this.isEnabled(feature);
            }

            @Override // com.flurry.org.codehaus.jackson.map.Module.SetupContext
            public boolean isEnabled(DeserializationConfig.Feature feature) {
                return this.isEnabled(feature);
            }

            @Override // com.flurry.org.codehaus.jackson.map.Module.SetupContext
            public boolean isEnabled(SerializationConfig.Feature feature) {
                return this.isEnabled(feature);
            }

            @Override // com.flurry.org.codehaus.jackson.map.Module.SetupContext
            public void setMixInAnnotations(Class cls, Class cls2) {
                this._deserializationConfig.addMixInAnnotations(cls, cls2);
                this._serializationConfig.addMixInAnnotations(cls, cls2);
            }
        });
    }

    public void registerSubtypes(NamedType... namedTypeArr) {
        getSubtypeResolver().registerSubtypes(namedTypeArr);
    }

    public void registerSubtypes(Class... clsArr) {
        getSubtypeResolver().registerSubtypes(clsArr);
    }

    @Deprecated
    public ObjectReader schemaBasedReader(FormatSchema formatSchema) {
        return reader(formatSchema);
    }

    @Deprecated
    public ObjectWriter schemaBasedWriter(FormatSchema formatSchema) {
        return writer(formatSchema);
    }

    public ObjectMapper setAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        this._serializationConfig = this._serializationConfig.withAnnotationIntrospector(annotationIntrospector);
        this._deserializationConfig = this._deserializationConfig.withAnnotationIntrospector(annotationIntrospector);
        return this;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this._deserializationConfig = this._deserializationConfig.withDateFormat(dateFormat);
        this._serializationConfig = this._serializationConfig.withDateFormat(dateFormat);
    }

    public ObjectMapper setDefaultTyping(TypeResolverBuilder typeResolverBuilder) {
        this._deserializationConfig = this._deserializationConfig.withTypeResolverBuilder(typeResolverBuilder);
        this._serializationConfig = this._serializationConfig.withTypeResolverBuilder(typeResolverBuilder);
        return this;
    }

    public ObjectMapper setDeserializationConfig(DeserializationConfig deserializationConfig) {
        this._deserializationConfig = deserializationConfig;
        return this;
    }

    public ObjectMapper setDeserializerProvider(DeserializerProvider deserializerProvider) {
        this._deserializerProvider = deserializerProvider;
        return this;
    }

    public void setFilters(FilterProvider filterProvider) {
        this._serializationConfig = this._serializationConfig.withFilters(filterProvider);
    }

    public void setHandlerInstantiator(HandlerInstantiator handlerInstantiator) {
        this._deserializationConfig = this._deserializationConfig.withHandlerInstantiator(handlerInstantiator);
        this._serializationConfig = this._serializationConfig.withHandlerInstantiator(handlerInstantiator);
    }

    public ObjectMapper setInjectableValues(InjectableValues injectableValues) {
        this._injectableValues = injectableValues;
        return this;
    }

    public ObjectMapper setNodeFactory(JsonNodeFactory jsonNodeFactory) {
        this._deserializationConfig = this._deserializationConfig.withNodeFactory(jsonNodeFactory);
        return this;
    }

    public ObjectMapper setPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy) {
        this._serializationConfig = this._serializationConfig.withPropertyNamingStrategy(propertyNamingStrategy);
        this._deserializationConfig = this._deserializationConfig.withPropertyNamingStrategy(propertyNamingStrategy);
        return this;
    }

    public ObjectMapper setSerializationConfig(SerializationConfig serializationConfig) {
        this._serializationConfig = serializationConfig;
        return this;
    }

    public ObjectMapper setSerializationInclusion(JsonSerialize.Inclusion inclusion) {
        this._serializationConfig = this._serializationConfig.withSerializationInclusion(inclusion);
        return this;
    }

    public ObjectMapper setSerializerFactory(SerializerFactory serializerFactory) {
        this._serializerFactory = serializerFactory;
        return this;
    }

    public ObjectMapper setSerializerProvider(SerializerProvider serializerProvider) {
        this._serializerProvider = serializerProvider;
        return this;
    }

    public void setSubtypeResolver(SubtypeResolver subtypeResolver) {
        this._subtypeResolver = subtypeResolver;
    }

    public ObjectMapper setTypeFactory(TypeFactory typeFactory) {
        this._typeFactory = typeFactory;
        this._deserializationConfig = this._deserializationConfig.withTypeFactory(typeFactory);
        this._serializationConfig = this._serializationConfig.withTypeFactory(typeFactory);
        return this;
    }

    public ObjectMapper setVisibility(JsonMethod jsonMethod, JsonAutoDetect.Visibility visibility) {
        this._deserializationConfig = this._deserializationConfig.withVisibility(jsonMethod, visibility);
        this._serializationConfig = this._serializationConfig.withVisibility(jsonMethod, visibility);
        return this;
    }

    public void setVisibilityChecker(VisibilityChecker visibilityChecker) {
        this._deserializationConfig = this._deserializationConfig.withVisibilityChecker(visibilityChecker);
        this._serializationConfig = this._serializationConfig.withVisibilityChecker(visibilityChecker);
    }

    @Override // com.flurry.org.codehaus.jackson.ObjectCodec
    public JsonParser treeAsTokens(JsonNode jsonNode) {
        return new TreeTraversingParser(jsonNode, this);
    }

    @Override // com.flurry.org.codehaus.jackson.ObjectCodec
    public Object treeToValue(JsonNode jsonNode, Class cls) {
        return readValue(treeAsTokens(jsonNode), cls);
    }

    @Deprecated
    public ObjectWriter typedWriter(JavaType javaType) {
        return writerWithType(javaType);
    }

    @Deprecated
    public ObjectWriter typedWriter(TypeReference typeReference) {
        return writerWithType(typeReference);
    }

    @Deprecated
    public ObjectWriter typedWriter(Class cls) {
        return writerWithType(cls);
    }

    @Deprecated
    public ObjectReader updatingReader(Object obj) {
        return readerForUpdating(obj);
    }

    public JsonNode valueToTree(Object obj) {
        if (obj == null) {
            return null;
        }
        TokenBuffer tokenBuffer = new TokenBuffer(this);
        try {
            writeValue(tokenBuffer, obj);
            JsonParser asParser = tokenBuffer.asParser();
            JsonNode readTree = readTree(asParser);
            asParser.close();
            return readTree;
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    @Override // com.flurry.org.codehaus.jackson.Versioned
    public Version version() {
        return VersionUtil.versionFor(getClass());
    }

    @Deprecated
    public ObjectWriter viewWriter(Class cls) {
        return writerWithView(cls);
    }

    public ObjectMapper withModule(Module module) {
        registerModule(module);
        return this;
    }

    @Override // com.flurry.org.codehaus.jackson.ObjectCodec
    public void writeTree(JsonGenerator jsonGenerator, JsonNode jsonNode) {
        SerializationConfig copySerializationConfig = copySerializationConfig();
        this._serializerProvider.serializeValue(copySerializationConfig, jsonGenerator, jsonNode, this._serializerFactory);
        if (copySerializationConfig.isEnabled(SerializationConfig.Feature.FLUSH_AFTER_WRITE_VALUE)) {
            jsonGenerator.flush();
        }
    }

    public void writeTree(JsonGenerator jsonGenerator, JsonNode jsonNode, SerializationConfig serializationConfig) {
        this._serializerProvider.serializeValue(serializationConfig, jsonGenerator, jsonNode, this._serializerFactory);
        if (serializationConfig.isEnabled(SerializationConfig.Feature.FLUSH_AFTER_WRITE_VALUE)) {
            jsonGenerator.flush();
        }
    }

    @Override // com.flurry.org.codehaus.jackson.ObjectCodec
    public void writeValue(JsonGenerator jsonGenerator, Object obj) {
        SerializationConfig copySerializationConfig = copySerializationConfig();
        if (copySerializationConfig.isEnabled(SerializationConfig.Feature.CLOSE_CLOSEABLE) && (obj instanceof Closeable)) {
            _writeCloseableValue(jsonGenerator, obj, copySerializationConfig);
            return;
        }
        this._serializerProvider.serializeValue(copySerializationConfig, jsonGenerator, obj, this._serializerFactory);
        if (copySerializationConfig.isEnabled(SerializationConfig.Feature.FLUSH_AFTER_WRITE_VALUE)) {
            jsonGenerator.flush();
        }
    }

    public void writeValue(JsonGenerator jsonGenerator, Object obj, SerializationConfig serializationConfig) {
        if (serializationConfig.isEnabled(SerializationConfig.Feature.CLOSE_CLOSEABLE) && (obj instanceof Closeable)) {
            _writeCloseableValue(jsonGenerator, obj, serializationConfig);
            return;
        }
        this._serializerProvider.serializeValue(serializationConfig, jsonGenerator, obj, this._serializerFactory);
        if (serializationConfig.isEnabled(SerializationConfig.Feature.FLUSH_AFTER_WRITE_VALUE)) {
            jsonGenerator.flush();
        }
    }

    public void writeValue(File file, Object obj) {
        _configAndWriteValue(this._jsonFactory.createJsonGenerator(file, JsonEncoding.UTF8), obj);
    }

    public void writeValue(OutputStream outputStream, Object obj) {
        _configAndWriteValue(this._jsonFactory.createJsonGenerator(outputStream, JsonEncoding.UTF8), obj);
    }

    public void writeValue(Writer writer, Object obj) {
        _configAndWriteValue(this._jsonFactory.createJsonGenerator(writer), obj);
    }

    public byte[] writeValueAsBytes(Object obj) {
        ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder(this._jsonFactory._getBufferRecycler());
        _configAndWriteValue(this._jsonFactory.createJsonGenerator(byteArrayBuilder, JsonEncoding.UTF8), obj);
        byte[] byteArray = byteArrayBuilder.toByteArray();
        byteArrayBuilder.release();
        return byteArray;
    }

    public String writeValueAsString(Object obj) {
        SegmentedStringWriter segmentedStringWriter = new SegmentedStringWriter(this._jsonFactory._getBufferRecycler());
        _configAndWriteValue(this._jsonFactory.createJsonGenerator(segmentedStringWriter), obj);
        return segmentedStringWriter.getAndClear();
    }

    public ObjectWriter writer() {
        return new ObjectWriter(this, copySerializationConfig());
    }

    public ObjectWriter writer(FormatSchema formatSchema) {
        return new ObjectWriter(this, copySerializationConfig(), formatSchema);
    }

    public ObjectWriter writer(PrettyPrinter prettyPrinter) {
        if (prettyPrinter == null) {
            prettyPrinter = ObjectWriter.NULL_PRETTY_PRINTER;
        }
        return new ObjectWriter(this, copySerializationConfig(), null, prettyPrinter);
    }

    public ObjectWriter writer(FilterProvider filterProvider) {
        return new ObjectWriter(this, copySerializationConfig().withFilters(filterProvider));
    }

    public ObjectWriter writer(DateFormat dateFormat) {
        return new ObjectWriter(this, copySerializationConfig().withDateFormat(dateFormat));
    }

    public ObjectWriter writerWithDefaultPrettyPrinter() {
        return new ObjectWriter(this, copySerializationConfig(), null, _defaultPrettyPrinter());
    }

    public ObjectWriter writerWithType(JavaType javaType) {
        return new ObjectWriter(this, copySerializationConfig(), javaType, null);
    }

    public ObjectWriter writerWithType(TypeReference typeReference) {
        return new ObjectWriter(this, copySerializationConfig(), typeReference == null ? null : this._typeFactory.constructType(typeReference), null);
    }

    public ObjectWriter writerWithType(Class cls) {
        return new ObjectWriter(this, copySerializationConfig(), cls == null ? null : this._typeFactory.constructType(cls), null);
    }

    public ObjectWriter writerWithView(Class cls) {
        return new ObjectWriter(this, copySerializationConfig().withView(cls));
    }
}
