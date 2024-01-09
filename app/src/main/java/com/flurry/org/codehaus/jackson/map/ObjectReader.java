package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.FormatSchema;
import com.flurry.org.codehaus.jackson.JsonFactory;
import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.ObjectCodec;
import com.flurry.org.codehaus.jackson.Version;
import com.flurry.org.codehaus.jackson.Versioned;
import com.flurry.org.codehaus.jackson.io.SerializedString;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.deser.StdDeserializationContext;
import com.flurry.org.codehaus.jackson.map.type.SimpleType;
import com.flurry.org.codehaus.jackson.node.JsonNodeFactory;
import com.flurry.org.codehaus.jackson.node.NullNode;
import com.flurry.org.codehaus.jackson.node.TreeTraversingParser;
import com.flurry.org.codehaus.jackson.type.JavaType;
import com.flurry.org.codehaus.jackson.type.TypeReference;
import com.flurry.org.codehaus.jackson.util.VersionUtil;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class ObjectReader extends ObjectCodec implements Versioned {
    private static final JavaType JSON_NODE_TYPE = SimpleType.constructUnsafe(JsonNode.class);
    protected final DeserializationConfig _config;
    protected final InjectableValues _injectableValues;
    protected final JsonFactory _jsonFactory;
    protected final DeserializerProvider _provider;
    protected final ConcurrentHashMap _rootDeserializers;
    protected final FormatSchema _schema;
    protected final boolean _unwrapRoot;
    protected final Object _valueToUpdate;
    protected final JavaType _valueType;

    public ObjectReader(ObjectMapper objectMapper, DeserializationConfig deserializationConfig) {
        this(objectMapper, deserializationConfig, (JavaType) null, (Object) null, (FormatSchema) null, (InjectableValues) null);
    }

    public ObjectReader(ObjectMapper objectMapper, DeserializationConfig deserializationConfig, JavaType javaType, Object obj, FormatSchema formatSchema, InjectableValues injectableValues) {
        this._config = deserializationConfig;
        this._rootDeserializers = objectMapper._rootDeserializers;
        this._provider = objectMapper._deserializerProvider;
        this._jsonFactory = objectMapper._jsonFactory;
        this._valueType = javaType;
        this._valueToUpdate = obj;
        if (obj != null && javaType.isArrayType()) {
            throw new IllegalArgumentException("Can not update an array value");
        }
        this._schema = formatSchema;
        this._injectableValues = injectableValues;
        this._unwrapRoot = deserializationConfig.isEnabled(DeserializationConfig.Feature.UNWRAP_ROOT_VALUE);
    }

    protected ObjectReader(ObjectReader objectReader, DeserializationConfig deserializationConfig, JavaType javaType, Object obj, FormatSchema formatSchema, InjectableValues injectableValues) {
        this._config = deserializationConfig;
        this._rootDeserializers = objectReader._rootDeserializers;
        this._provider = objectReader._provider;
        this._jsonFactory = objectReader._jsonFactory;
        this._valueType = javaType;
        this._valueToUpdate = obj;
        if (obj != null && javaType.isArrayType()) {
            throw new IllegalArgumentException("Can not update an array value");
        }
        this._schema = formatSchema;
        this._injectableValues = injectableValues;
        this._unwrapRoot = deserializationConfig.isEnabled(DeserializationConfig.Feature.UNWRAP_ROOT_VALUE);
    }

    protected static JsonToken _initForReading(JsonParser jsonParser) {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == null && (currentToken = jsonParser.nextToken()) == null) {
            throw new EOFException("No content to map to Object due to end of input");
        }
        return currentToken;
    }

    protected Object _bind(JsonParser jsonParser) {
        Object obj;
        JsonToken _initForReading = _initForReading(jsonParser);
        if (_initForReading == JsonToken.VALUE_NULL) {
            obj = this._valueToUpdate == null ? _findRootDeserializer(this._config, this._valueType).getNullValue() : this._valueToUpdate;
        } else if (_initForReading == JsonToken.END_ARRAY || _initForReading == JsonToken.END_OBJECT) {
            obj = this._valueToUpdate;
        } else {
            DeserializationContext _createDeserializationContext = _createDeserializationContext(jsonParser, this._config);
            JsonDeserializer _findRootDeserializer = _findRootDeserializer(this._config, this._valueType);
            if (this._unwrapRoot) {
                obj = _unwrapAndDeserialize(jsonParser, _createDeserializationContext, this._valueType, _findRootDeserializer);
            } else if (this._valueToUpdate == null) {
                obj = _findRootDeserializer.deserialize(jsonParser, _createDeserializationContext);
            } else {
                _findRootDeserializer.deserialize(jsonParser, _createDeserializationContext, this._valueToUpdate);
                obj = this._valueToUpdate;
            }
        }
        jsonParser.clearCurrentToken();
        return obj;
    }

    protected Object _bindAndClose(JsonParser jsonParser) {
        Object obj;
        if (this._schema != null) {
            jsonParser.setSchema(this._schema);
        }
        try {
            JsonToken _initForReading = _initForReading(jsonParser);
            if (_initForReading == JsonToken.VALUE_NULL) {
                obj = this._valueToUpdate == null ? _findRootDeserializer(this._config, this._valueType).getNullValue() : this._valueToUpdate;
            } else if (_initForReading == JsonToken.END_ARRAY || _initForReading == JsonToken.END_OBJECT) {
                obj = this._valueToUpdate;
            } else {
                DeserializationContext _createDeserializationContext = _createDeserializationContext(jsonParser, this._config);
                JsonDeserializer _findRootDeserializer = _findRootDeserializer(this._config, this._valueType);
                if (this._unwrapRoot) {
                    obj = _unwrapAndDeserialize(jsonParser, _createDeserializationContext, this._valueType, _findRootDeserializer);
                } else if (this._valueToUpdate == null) {
                    obj = _findRootDeserializer.deserialize(jsonParser, _createDeserializationContext);
                } else {
                    _findRootDeserializer.deserialize(jsonParser, _createDeserializationContext, this._valueToUpdate);
                    obj = this._valueToUpdate;
                }
            }
            return obj;
        } finally {
            try {
                jsonParser.close();
            } catch (IOException e) {
            }
        }
    }

    protected JsonNode _bindAndCloseAsTree(JsonParser jsonParser) {
        if (this._schema != null) {
            jsonParser.setSchema(this._schema);
        }
        try {
            return _bindAsTree(jsonParser);
        } finally {
            try {
                jsonParser.close();
            } catch (IOException e) {
            }
        }
    }

    protected JsonNode _bindAsTree(JsonParser jsonParser) {
        JsonNode jsonNode;
        JsonToken _initForReading = _initForReading(jsonParser);
        if (_initForReading == JsonToken.VALUE_NULL || _initForReading == JsonToken.END_ARRAY || _initForReading == JsonToken.END_OBJECT) {
            jsonNode = NullNode.instance;
        } else {
            DeserializationContext _createDeserializationContext = _createDeserializationContext(jsonParser, this._config);
            JsonDeserializer _findRootDeserializer = _findRootDeserializer(this._config, JSON_NODE_TYPE);
            jsonNode = this._unwrapRoot ? (JsonNode) _unwrapAndDeserialize(jsonParser, _createDeserializationContext, JSON_NODE_TYPE, _findRootDeserializer) : (JsonNode) _findRootDeserializer.deserialize(jsonParser, _createDeserializationContext);
        }
        jsonParser.clearCurrentToken();
        return jsonNode;
    }

    protected DeserializationContext _createDeserializationContext(JsonParser jsonParser, DeserializationConfig deserializationConfig) {
        return new StdDeserializationContext(deserializationConfig, jsonParser, this._provider, this._injectableValues);
    }

    protected JsonDeserializer _findRootDeserializer(DeserializationConfig deserializationConfig, JavaType javaType) {
        if (javaType == null) {
            throw new JsonMappingException("No value type configured for ObjectReader");
        }
        JsonDeserializer jsonDeserializer = (JsonDeserializer) this._rootDeserializers.get(javaType);
        if (jsonDeserializer == null) {
            jsonDeserializer = this._provider.findTypedValueDeserializer(deserializationConfig, javaType, null);
            if (jsonDeserializer == null) {
                throw new JsonMappingException("Can not find a deserializer for type " + javaType);
            }
            this._rootDeserializers.put(javaType, jsonDeserializer);
        }
        return jsonDeserializer;
    }

    protected Object _unwrapAndDeserialize(JsonParser jsonParser, DeserializationContext deserializationContext, JavaType javaType, JsonDeserializer jsonDeserializer) {
        Object obj;
        SerializedString findExpectedRootName = this._provider.findExpectedRootName(deserializationContext.getConfig(), javaType);
        if (jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
            throw JsonMappingException.from(jsonParser, "Current token not START_OBJECT (needed to unwrap root name '" + findExpectedRootName + "'), but " + jsonParser.getCurrentToken());
        }
        if (jsonParser.nextToken() != JsonToken.FIELD_NAME) {
            throw JsonMappingException.from(jsonParser, "Current token not FIELD_NAME (to contain expected root name '" + findExpectedRootName + "'), but " + jsonParser.getCurrentToken());
        }
        String currentName = jsonParser.getCurrentName();
        if (findExpectedRootName.getValue().equals(currentName)) {
            jsonParser.nextToken();
            if (this._valueToUpdate == null) {
                obj = jsonDeserializer.deserialize(jsonParser, deserializationContext);
            } else {
                jsonDeserializer.deserialize(jsonParser, deserializationContext, this._valueToUpdate);
                obj = this._valueToUpdate;
            }
            if (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                throw JsonMappingException.from(jsonParser, "Current token not END_OBJECT (to match wrapper object with root name '" + findExpectedRootName + "'), but " + jsonParser.getCurrentToken());
            }
            return obj;
        }
        throw JsonMappingException.from(jsonParser, "Root name '" + currentName + "' does not match expected ('" + findExpectedRootName + "') for type " + javaType);
    }

    @Override // com.flurry.org.codehaus.jackson.ObjectCodec
    public JsonNode createArrayNode() {
        return this._config.getNodeFactory().arrayNode();
    }

    @Override // com.flurry.org.codehaus.jackson.ObjectCodec
    public JsonNode createObjectNode() {
        return this._config.getNodeFactory().objectNode();
    }

    @Override // com.flurry.org.codehaus.jackson.ObjectCodec
    public JsonNode readTree(JsonParser jsonParser) {
        return _bindAsTree(jsonParser);
    }

    public JsonNode readTree(InputStream inputStream) {
        return _bindAndCloseAsTree(this._jsonFactory.createJsonParser(inputStream));
    }

    public JsonNode readTree(Reader reader) {
        return _bindAndCloseAsTree(this._jsonFactory.createJsonParser(reader));
    }

    public JsonNode readTree(String str) {
        return _bindAndCloseAsTree(this._jsonFactory.createJsonParser(str));
    }

    public Object readValue(JsonNode jsonNode) {
        return _bindAndClose(treeAsTokens(jsonNode));
    }

    public Object readValue(JsonParser jsonParser) {
        return _bind(jsonParser);
    }

    @Override // com.flurry.org.codehaus.jackson.ObjectCodec
    public Object readValue(JsonParser jsonParser, JavaType javaType) {
        return withType(javaType).readValue(jsonParser);
    }

    @Override // com.flurry.org.codehaus.jackson.ObjectCodec
    public Object readValue(JsonParser jsonParser, TypeReference typeReference) {
        return withType(typeReference).readValue(jsonParser);
    }

    @Override // com.flurry.org.codehaus.jackson.ObjectCodec
    public Object readValue(JsonParser jsonParser, Class cls) {
        return withType(cls).readValue(jsonParser);
    }

    public Object readValue(File file) {
        return _bindAndClose(this._jsonFactory.createJsonParser(file));
    }

    public Object readValue(InputStream inputStream) {
        return _bindAndClose(this._jsonFactory.createJsonParser(inputStream));
    }

    public Object readValue(Reader reader) {
        return _bindAndClose(this._jsonFactory.createJsonParser(reader));
    }

    public Object readValue(String str) {
        return _bindAndClose(this._jsonFactory.createJsonParser(str));
    }

    public Object readValue(URL url) {
        return _bindAndClose(this._jsonFactory.createJsonParser(url));
    }

    public Object readValue(byte[] bArr) {
        return _bindAndClose(this._jsonFactory.createJsonParser(bArr));
    }

    public Object readValue(byte[] bArr, int i, int i2) {
        return _bindAndClose(this._jsonFactory.createJsonParser(bArr, i, i2));
    }

    public MappingIterator readValues(JsonParser jsonParser) {
        return new MappingIterator(this._valueType, jsonParser, _createDeserializationContext(jsonParser, this._config), _findRootDeserializer(this._config, this._valueType), false, this._valueToUpdate);
    }

    public MappingIterator readValues(File file) {
        JsonParser createJsonParser = this._jsonFactory.createJsonParser(file);
        if (this._schema != null) {
            createJsonParser.setSchema(this._schema);
        }
        return new MappingIterator(this._valueType, createJsonParser, _createDeserializationContext(createJsonParser, this._config), _findRootDeserializer(this._config, this._valueType), true, this._valueToUpdate);
    }

    public MappingIterator readValues(InputStream inputStream) {
        JsonParser createJsonParser = this._jsonFactory.createJsonParser(inputStream);
        if (this._schema != null) {
            createJsonParser.setSchema(this._schema);
        }
        return new MappingIterator(this._valueType, createJsonParser, _createDeserializationContext(createJsonParser, this._config), _findRootDeserializer(this._config, this._valueType), true, this._valueToUpdate);
    }

    public MappingIterator readValues(Reader reader) {
        JsonParser createJsonParser = this._jsonFactory.createJsonParser(reader);
        if (this._schema != null) {
            createJsonParser.setSchema(this._schema);
        }
        return new MappingIterator(this._valueType, createJsonParser, _createDeserializationContext(createJsonParser, this._config), _findRootDeserializer(this._config, this._valueType), true, this._valueToUpdate);
    }

    public MappingIterator readValues(String str) {
        JsonParser createJsonParser = this._jsonFactory.createJsonParser(str);
        if (this._schema != null) {
            createJsonParser.setSchema(this._schema);
        }
        return new MappingIterator(this._valueType, createJsonParser, _createDeserializationContext(createJsonParser, this._config), _findRootDeserializer(this._config, this._valueType), true, this._valueToUpdate);
    }

    public MappingIterator readValues(URL url) {
        JsonParser createJsonParser = this._jsonFactory.createJsonParser(url);
        if (this._schema != null) {
            createJsonParser.setSchema(this._schema);
        }
        return new MappingIterator(this._valueType, createJsonParser, _createDeserializationContext(createJsonParser, this._config), _findRootDeserializer(this._config, this._valueType), true, this._valueToUpdate);
    }

    public final MappingIterator readValues(byte[] bArr) {
        return readValues(bArr, 0, bArr.length);
    }

    public MappingIterator readValues(byte[] bArr, int i, int i2) {
        JsonParser createJsonParser = this._jsonFactory.createJsonParser(bArr, i, i2);
        if (this._schema != null) {
            createJsonParser.setSchema(this._schema);
        }
        return new MappingIterator(this._valueType, createJsonParser, _createDeserializationContext(createJsonParser, this._config), _findRootDeserializer(this._config, this._valueType), true, this._valueToUpdate);
    }

    @Override // com.flurry.org.codehaus.jackson.ObjectCodec
    public Iterator readValues(JsonParser jsonParser, JavaType javaType) {
        return withType(javaType).readValues(jsonParser);
    }

    @Override // com.flurry.org.codehaus.jackson.ObjectCodec
    public Iterator readValues(JsonParser jsonParser, TypeReference typeReference) {
        return withType(typeReference).readValues(jsonParser);
    }

    @Override // com.flurry.org.codehaus.jackson.ObjectCodec
    public Iterator readValues(JsonParser jsonParser, Class cls) {
        return withType(cls).readValues(jsonParser);
    }

    @Override // com.flurry.org.codehaus.jackson.ObjectCodec
    public JsonParser treeAsTokens(JsonNode jsonNode) {
        return new TreeTraversingParser(jsonNode, this);
    }

    @Override // com.flurry.org.codehaus.jackson.ObjectCodec
    public Object treeToValue(JsonNode jsonNode, Class cls) {
        return readValue(treeAsTokens(jsonNode), cls);
    }

    @Override // com.flurry.org.codehaus.jackson.Versioned
    public Version version() {
        return VersionUtil.versionFor(getClass());
    }

    public ObjectReader withInjectableValues(InjectableValues injectableValues) {
        return this._injectableValues == injectableValues ? this : new ObjectReader(this, this._config, this._valueType, this._valueToUpdate, this._schema, injectableValues);
    }

    public ObjectReader withNodeFactory(JsonNodeFactory jsonNodeFactory) {
        return jsonNodeFactory == this._config.getNodeFactory() ? this : new ObjectReader(this, this._config.withNodeFactory(jsonNodeFactory), this._valueType, this._valueToUpdate, this._schema, this._injectableValues);
    }

    public ObjectReader withSchema(FormatSchema formatSchema) {
        return this._schema == formatSchema ? this : new ObjectReader(this, this._config, this._valueType, this._valueToUpdate, formatSchema, this._injectableValues);
    }

    public ObjectReader withType(JavaType javaType) {
        return javaType == this._valueType ? this : new ObjectReader(this, this._config, javaType, this._valueToUpdate, this._schema, this._injectableValues);
    }

    public ObjectReader withType(TypeReference typeReference) {
        return withType(this._config.getTypeFactory().constructType(typeReference.getType()));
    }

    public ObjectReader withType(Class cls) {
        return withType(this._config.constructType(cls));
    }

    public ObjectReader withType(Type type) {
        return withType(this._config.getTypeFactory().constructType(type));
    }

    public ObjectReader withValueToUpdate(Object obj) {
        if (obj == this._valueToUpdate) {
            return this;
        }
        if (obj == null) {
            throw new IllegalArgumentException("cat not update null value");
        }
        return new ObjectReader(this, this._config, this._valueType == null ? this._config.constructType(obj.getClass()) : this._valueType, obj, this._schema, this._injectableValues);
    }

    @Override // com.flurry.org.codehaus.jackson.ObjectCodec
    public void writeTree(JsonGenerator jsonGenerator, JsonNode jsonNode) {
        throw new UnsupportedOperationException("Not implemented for ObjectReader");
    }

    @Override // com.flurry.org.codehaus.jackson.ObjectCodec
    public void writeValue(JsonGenerator jsonGenerator, Object obj) {
        throw new UnsupportedOperationException("Not implemented for ObjectReader");
    }
}
