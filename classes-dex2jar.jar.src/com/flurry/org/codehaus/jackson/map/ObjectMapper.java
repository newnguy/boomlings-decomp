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
import com.flurry.org.codehaus.jackson.map.annotate.JsonSerialize;
import com.flurry.org.codehaus.jackson.map.deser.StdDeserializationContext;
import com.flurry.org.codehaus.jackson.map.deser.StdDeserializerProvider;
import com.flurry.org.codehaus.jackson.map.introspect.BasicClassIntrospector;
import com.flurry.org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.introspect.VisibilityChecker;
import com.flurry.org.codehaus.jackson.map.jsontype.NamedType;
import com.flurry.org.codehaus.jackson.map.jsontype.SubtypeResolver;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import com.flurry.org.codehaus.jackson.map.jsontype.impl.StdSubtypeResolver;
import com.flurry.org.codehaus.jackson.map.ser.BeanSerializerFactory;
import com.flurry.org.codehaus.jackson.map.ser.FilterProvider;
import com.flurry.org.codehaus.jackson.map.ser.StdSerializerProvider;
import com.flurry.org.codehaus.jackson.map.type.SimpleType;
import com.flurry.org.codehaus.jackson.map.type.TypeFactory;
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
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class ObjectMapper extends ObjectCodec implements Versioned {
  protected static final AnnotationIntrospector DEFAULT_ANNOTATION_INTROSPECTOR;
  
  protected static final ClassIntrospector DEFAULT_INTROSPECTOR;
  
  private static final JavaType JSON_NODE_TYPE = (JavaType)SimpleType.constructUnsafe(JsonNode.class);
  
  protected static final VisibilityChecker STD_VISIBILITY_CHECKER;
  
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
  
  static {
    DEFAULT_INTROSPECTOR = (ClassIntrospector)BasicClassIntrospector.instance;
    DEFAULT_ANNOTATION_INTROSPECTOR = (AnnotationIntrospector)new JacksonAnnotationIntrospector();
    STD_VISIBILITY_CHECKER = (VisibilityChecker)VisibilityChecker.Std.defaultInstance();
  }
  
  public ObjectMapper() {
    this(null, null, null);
  }
  
  public ObjectMapper(JsonFactory paramJsonFactory) {
    this(paramJsonFactory, null, null);
  }
  
  public ObjectMapper(JsonFactory paramJsonFactory, SerializerProvider paramSerializerProvider, DeserializerProvider paramDeserializerProvider) {
    this(paramJsonFactory, paramSerializerProvider, paramDeserializerProvider, null, null);
  }
  
  public ObjectMapper(JsonFactory paramJsonFactory, SerializerProvider paramSerializerProvider, DeserializerProvider paramDeserializerProvider, SerializationConfig paramSerializationConfig, DeserializationConfig paramDeserializationConfig) {
    StdSerializerProvider stdSerializerProvider;
    StdDeserializerProvider stdDeserializerProvider;
    this._rootDeserializers = new ConcurrentHashMap<Object, Object>(64, 0.6F, 2);
    if (paramJsonFactory == null) {
      this._jsonFactory = new MappingJsonFactory(this);
    } else {
      this._jsonFactory = paramJsonFactory;
      if (paramJsonFactory.getCodec() == null)
        this._jsonFactory.setCodec(this); 
    } 
    this._typeFactory = TypeFactory.defaultInstance();
    if (paramSerializationConfig == null)
      paramSerializationConfig = new SerializationConfig(DEFAULT_INTROSPECTOR, DEFAULT_ANNOTATION_INTROSPECTOR, STD_VISIBILITY_CHECKER, null, null, this._typeFactory, null); 
    this._serializationConfig = paramSerializationConfig;
    if (paramDeserializationConfig == null)
      paramDeserializationConfig = new DeserializationConfig(DEFAULT_INTROSPECTOR, DEFAULT_ANNOTATION_INTROSPECTOR, STD_VISIBILITY_CHECKER, null, null, this._typeFactory, null); 
    this._deserializationConfig = paramDeserializationConfig;
    SerializerProvider serializerProvider = paramSerializerProvider;
    if (paramSerializerProvider == null)
      stdSerializerProvider = new StdSerializerProvider(); 
    this._serializerProvider = (SerializerProvider)stdSerializerProvider;
    DeserializerProvider deserializerProvider = paramDeserializerProvider;
    if (paramDeserializerProvider == null)
      stdDeserializerProvider = new StdDeserializerProvider(); 
    this._deserializerProvider = (DeserializerProvider)stdDeserializerProvider;
    this._serializerFactory = (SerializerFactory)BeanSerializerFactory.instance;
  }
  
  @Deprecated
  public ObjectMapper(SerializerFactory paramSerializerFactory) {
    this(null, null, null);
    setSerializerFactory(paramSerializerFactory);
  }
  
  private final void _configAndWriteCloseable(JsonGenerator paramJsonGenerator, Object paramObject, SerializationConfig paramSerializationConfig) {
    SerializationConfig serializationConfig;
    JsonGenerator jsonGenerator;
    Closeable closeable = (Closeable)paramObject;
    try {
    
    } finally {
      paramSerializationConfig = null;
      paramObject = closeable;
      SerializationConfig serializationConfig1 = paramSerializationConfig;
      jsonGenerator = paramJsonGenerator;
    } 
    if (jsonGenerator != null)
      try {
        jsonGenerator.close();
      } catch (IOException iOException) {} 
    if (paramObject != null)
      try {
        paramObject.close();
      } catch (IOException iOException) {} 
    throw serializationConfig;
  }
  
  private final void _writeCloseableValue(JsonGenerator paramJsonGenerator, Object paramObject, SerializationConfig paramSerializationConfig) {
    Closeable closeable = (Closeable)paramObject;
    try {
      this._serializerProvider.serializeValue(paramSerializationConfig, paramJsonGenerator, paramObject, this._serializerFactory);
      if (paramSerializationConfig.isEnabled(SerializationConfig$Feature.FLUSH_AFTER_WRITE_VALUE))
        paramJsonGenerator.flush(); 
      paramObject = null;
    } finally {
      paramJsonGenerator = null;
    } 
    if (paramObject != null)
      try {
        paramObject.close();
      } catch (IOException iOException) {} 
    throw paramJsonGenerator;
  }
  
  protected final void _configAndWriteValue(JsonGenerator paramJsonGenerator, Object paramObject) {
    SerializationConfig serializationConfig = copySerializationConfig();
    if (serializationConfig.isEnabled(SerializationConfig$Feature.INDENT_OUTPUT))
      paramJsonGenerator.useDefaultPrettyPrinter(); 
    if (serializationConfig.isEnabled(SerializationConfig$Feature.CLOSE_CLOSEABLE) && paramObject instanceof Closeable) {
      _configAndWriteCloseable(paramJsonGenerator, paramObject, serializationConfig);
      return;
    } 
    boolean bool = false;
    try {
      this._serializerProvider.serializeValue(serializationConfig, paramJsonGenerator, paramObject, this._serializerFactory);
      bool = true;
      return;
    } finally {
      if (!bool)
        try {
          paramJsonGenerator.close();
        } catch (IOException iOException) {} 
    } 
  }
  
  protected final void _configAndWriteValue(JsonGenerator paramJsonGenerator, Object paramObject, Class paramClass) {
    SerializationConfig serializationConfig = copySerializationConfig().withView(paramClass);
    if (serializationConfig.isEnabled(SerializationConfig$Feature.INDENT_OUTPUT))
      paramJsonGenerator.useDefaultPrettyPrinter(); 
    if (serializationConfig.isEnabled(SerializationConfig$Feature.CLOSE_CLOSEABLE) && paramObject instanceof Closeable) {
      _configAndWriteCloseable(paramJsonGenerator, paramObject, serializationConfig);
      return;
    } 
    boolean bool = false;
    try {
      this._serializerProvider.serializeValue(serializationConfig, paramJsonGenerator, paramObject, this._serializerFactory);
      bool = true;
      return;
    } finally {
      if (!bool)
        try {
          paramJsonGenerator.close();
        } catch (IOException iOException) {} 
    } 
  }
  
  protected Object _convert(Object paramObject, JavaType paramJavaType) {
    if (paramObject == null)
      return null; 
    TokenBuffer tokenBuffer = new TokenBuffer(this);
    try {
      writeValue((JsonGenerator)tokenBuffer, paramObject);
      JsonParser jsonParser = tokenBuffer.asParser();
      paramObject = readValue(jsonParser, paramJavaType);
      jsonParser.close();
    } catch (IOException iOException) {
      throw new IllegalArgumentException(iOException.getMessage(), iOException);
    } 
    return iOException;
  }
  
  protected DeserializationContext _createDeserializationContext(JsonParser paramJsonParser, DeserializationConfig paramDeserializationConfig) {
    return (DeserializationContext)new StdDeserializationContext(paramDeserializationConfig, paramJsonParser, this._deserializerProvider, this._injectableValues);
  }
  
  protected PrettyPrinter _defaultPrettyPrinter() {
    return (PrettyPrinter)new DefaultPrettyPrinter();
  }
  
  protected JsonDeserializer _findRootDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType) {
    JsonDeserializer jsonDeserializer2 = (JsonDeserializer)this._rootDeserializers.get(paramJavaType);
    if (jsonDeserializer2 != null)
      return jsonDeserializer2; 
    JsonDeserializer jsonDeserializer1 = this._deserializerProvider.findTypedValueDeserializer(paramDeserializationConfig, paramJavaType, null);
    if (jsonDeserializer1 == null)
      throw new JsonMappingException("Can not find a deserializer for type " + paramJavaType); 
    this._rootDeserializers.put(paramJavaType, jsonDeserializer1);
    return jsonDeserializer1;
  }
  
  protected JsonToken _initForReading(JsonParser paramJsonParser) {
    JsonToken jsonToken2 = paramJsonParser.getCurrentToken();
    JsonToken jsonToken1 = jsonToken2;
    if (jsonToken2 == null) {
      JsonToken jsonToken = paramJsonParser.nextToken();
      jsonToken1 = jsonToken;
      if (jsonToken == null)
        throw new EOFException("No content to map to Object due to end of input"); 
    } 
    return jsonToken1;
  }
  
  protected Object _readMapAndClose(JsonParser paramJsonParser, JavaType paramJavaType) {
    try {
      JsonToken jsonToken = _initForReading(paramJsonParser);
      if (jsonToken == JsonToken.VALUE_NULL) {
        Object object = _findRootDeserializer(this._deserializationConfig, paramJavaType).getNullValue();
      } else if (jsonToken == JsonToken.END_ARRAY || jsonToken == JsonToken.END_OBJECT) {
        paramJavaType = null;
      } else {
        DeserializationConfig deserializationConfig = copyDeserializationConfig();
        DeserializationContext deserializationContext = _createDeserializationContext(paramJsonParser, deserializationConfig);
        JsonDeserializer jsonDeserializer = _findRootDeserializer(deserializationConfig, paramJavaType);
        if (deserializationConfig.isEnabled(DeserializationConfig$Feature.UNWRAP_ROOT_VALUE)) {
          Object object = _unwrapAndDeserialize(paramJsonParser, paramJavaType, deserializationContext, jsonDeserializer);
        } else {
          Object object = jsonDeserializer.deserialize(paramJsonParser, deserializationContext);
        } 
      } 
      paramJsonParser.clearCurrentToken();
    } finally {
      try {
        iOException.close();
      } catch (IOException iOException1) {}
    } 
  }
  
  protected Object _readValue(DeserializationConfig paramDeserializationConfig, JsonParser paramJsonParser, JavaType paramJavaType) {
    JsonToken jsonToken = _initForReading(paramJsonParser);
    if (jsonToken == JsonToken.VALUE_NULL) {
      Object object1 = _findRootDeserializer(paramDeserializationConfig, paramJavaType).getNullValue();
      paramJsonParser.clearCurrentToken();
      return object1;
    } 
    if (jsonToken == JsonToken.END_ARRAY || jsonToken == JsonToken.END_OBJECT) {
      paramDeserializationConfig = null;
      paramJsonParser.clearCurrentToken();
      return paramDeserializationConfig;
    } 
    DeserializationContext deserializationContext = _createDeserializationContext(paramJsonParser, paramDeserializationConfig);
    JsonDeserializer jsonDeserializer = _findRootDeserializer(paramDeserializationConfig, paramJavaType);
    if (paramDeserializationConfig.isEnabled(DeserializationConfig$Feature.UNWRAP_ROOT_VALUE)) {
      Object object1 = _unwrapAndDeserialize(paramJsonParser, paramJavaType, deserializationContext, jsonDeserializer);
      paramJsonParser.clearCurrentToken();
      return object1;
    } 
    Object object = jsonDeserializer.deserialize(paramJsonParser, deserializationContext);
    paramJsonParser.clearCurrentToken();
    return object;
  }
  
  protected Object _unwrapAndDeserialize(JsonParser paramJsonParser, JavaType paramJavaType, DeserializationContext paramDeserializationContext, JsonDeserializer paramJsonDeserializer) {
    SerializedString serializedString = this._deserializerProvider.findExpectedRootName(paramDeserializationContext.getConfig(), paramJavaType);
    if (paramJsonParser.getCurrentToken() != JsonToken.START_OBJECT)
      throw JsonMappingException.from(paramJsonParser, "Current token not START_OBJECT (needed to unwrap root name '" + serializedString + "'), but " + paramJsonParser.getCurrentToken()); 
    if (paramJsonParser.nextToken() != JsonToken.FIELD_NAME)
      throw JsonMappingException.from(paramJsonParser, "Current token not FIELD_NAME (to contain expected root name '" + serializedString + "'), but " + paramJsonParser.getCurrentToken()); 
    String str = paramJsonParser.getCurrentName();
    if (!serializedString.getValue().equals(str))
      throw JsonMappingException.from(paramJsonParser, "Root name '" + str + "' does not match expected ('" + serializedString + "') for type " + paramJavaType); 
    paramJsonParser.nextToken();
    Object object = paramJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
    if (paramJsonParser.nextToken() != JsonToken.END_OBJECT)
      throw JsonMappingException.from(paramJsonParser, "Current token not END_OBJECT (to match wrapper object with root name '" + serializedString + "'), but " + paramJsonParser.getCurrentToken()); 
    return object;
  }
  
  public boolean canDeserialize(JavaType paramJavaType) {
    return this._deserializerProvider.hasValueDeserializerFor(copyDeserializationConfig(), paramJavaType);
  }
  
  public boolean canSerialize(Class paramClass) {
    return this._serializerProvider.hasSerializerFor(copySerializationConfig(), paramClass, this._serializerFactory);
  }
  
  public ObjectMapper configure(JsonGenerator.Feature paramFeature, boolean paramBoolean) {
    this._jsonFactory.configure(paramFeature, paramBoolean);
    return this;
  }
  
  public ObjectMapper configure(JsonParser.Feature paramFeature, boolean paramBoolean) {
    this._jsonFactory.configure(paramFeature, paramBoolean);
    return this;
  }
  
  public ObjectMapper configure(DeserializationConfig$Feature paramDeserializationConfig$Feature, boolean paramBoolean) {
    this._deserializationConfig.set(paramDeserializationConfig$Feature, paramBoolean);
    return this;
  }
  
  public ObjectMapper configure(SerializationConfig$Feature paramSerializationConfig$Feature, boolean paramBoolean) {
    this._serializationConfig.set(paramSerializationConfig$Feature, paramBoolean);
    return this;
  }
  
  public JavaType constructType(Type paramType) {
    return this._typeFactory.constructType(paramType);
  }
  
  public Object convertValue(Object paramObject, JavaType paramJavaType) {
    return _convert(paramObject, paramJavaType);
  }
  
  public Object convertValue(Object paramObject, TypeReference paramTypeReference) {
    return _convert(paramObject, this._typeFactory.constructType(paramTypeReference));
  }
  
  public Object convertValue(Object paramObject, Class paramClass) {
    return _convert(paramObject, this._typeFactory.constructType(paramClass));
  }
  
  public DeserializationConfig copyDeserializationConfig() {
    return this._deserializationConfig.createUnshared(this._subtypeResolver).passSerializationFeatures(this._serializationConfig._featureFlags);
  }
  
  public SerializationConfig copySerializationConfig() {
    return this._serializationConfig.createUnshared(this._subtypeResolver);
  }
  
  public ArrayNode createArrayNode() {
    return this._deserializationConfig.getNodeFactory().arrayNode();
  }
  
  public ObjectNode createObjectNode() {
    return this._deserializationConfig.getNodeFactory().objectNode();
  }
  
  @Deprecated
  public ObjectWriter defaultPrettyPrintingWriter() {
    return writerWithDefaultPrettyPrinter();
  }
  
  public ObjectMapper disable(DeserializationConfig$Feature... paramVarArgs) {
    this._deserializationConfig = this._deserializationConfig.without(paramVarArgs);
    return this;
  }
  
  public ObjectMapper disable(SerializationConfig$Feature... paramVarArgs) {
    this._serializationConfig = this._serializationConfig.without(paramVarArgs);
    return this;
  }
  
  public ObjectMapper disableDefaultTyping() {
    return setDefaultTyping(null);
  }
  
  public ObjectMapper enable(DeserializationConfig$Feature... paramVarArgs) {
    this._deserializationConfig = this._deserializationConfig.with(paramVarArgs);
    return this;
  }
  
  public ObjectMapper enable(SerializationConfig$Feature... paramVarArgs) {
    this._serializationConfig = this._serializationConfig.with(paramVarArgs);
    return this;
  }
  
  public ObjectMapper enableDefaultTyping() {
    return enableDefaultTyping(ObjectMapper$DefaultTyping.OBJECT_AND_NON_CONCRETE);
  }
  
  public ObjectMapper enableDefaultTyping(ObjectMapper$DefaultTyping paramObjectMapper$DefaultTyping) {
    return enableDefaultTyping(paramObjectMapper$DefaultTyping, JsonTypeInfo.As.WRAPPER_ARRAY);
  }
  
  public ObjectMapper enableDefaultTyping(ObjectMapper$DefaultTyping paramObjectMapper$DefaultTyping, JsonTypeInfo.As paramAs) {
    return setDefaultTyping((new ObjectMapper$DefaultTypeResolverBuilder(paramObjectMapper$DefaultTyping)).init(JsonTypeInfo.Id.CLASS, null).inclusion(paramAs));
  }
  
  public ObjectMapper enableDefaultTypingAsProperty(ObjectMapper$DefaultTyping paramObjectMapper$DefaultTyping, String paramString) {
    return setDefaultTyping((new ObjectMapper$DefaultTypeResolverBuilder(paramObjectMapper$DefaultTyping)).init(JsonTypeInfo.Id.CLASS, null).inclusion(JsonTypeInfo.As.PROPERTY).typeProperty(paramString));
  }
  
  @Deprecated
  public ObjectWriter filteredWriter(FilterProvider paramFilterProvider) {
    return writer(paramFilterProvider);
  }
  
  public JsonSchema generateJsonSchema(Class paramClass) {
    return generateJsonSchema(paramClass, copySerializationConfig());
  }
  
  public JsonSchema generateJsonSchema(Class paramClass, SerializationConfig paramSerializationConfig) {
    return this._serializerProvider.generateJsonSchema(paramClass, paramSerializationConfig, this._serializerFactory);
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
    if (this._subtypeResolver == null)
      this._subtypeResolver = (SubtypeResolver)new StdSubtypeResolver(); 
    return this._subtypeResolver;
  }
  
  public TypeFactory getTypeFactory() {
    return this._typeFactory;
  }
  
  public VisibilityChecker getVisibilityChecker() {
    return this._serializationConfig.getDefaultVisibilityChecker();
  }
  
  public boolean isEnabled(JsonGenerator.Feature paramFeature) {
    return this._jsonFactory.isEnabled(paramFeature);
  }
  
  public boolean isEnabled(JsonParser.Feature paramFeature) {
    return this._jsonFactory.isEnabled(paramFeature);
  }
  
  public boolean isEnabled(DeserializationConfig$Feature paramDeserializationConfig$Feature) {
    return this._deserializationConfig.isEnabled(paramDeserializationConfig$Feature);
  }
  
  public boolean isEnabled(SerializationConfig$Feature paramSerializationConfig$Feature) {
    return this._serializationConfig.isEnabled(paramSerializationConfig$Feature);
  }
  
  @Deprecated
  public ObjectWriter prettyPrintingWriter(PrettyPrinter paramPrettyPrinter) {
    return writer(paramPrettyPrinter);
  }
  
  public JsonNode readTree(JsonParser paramJsonParser) {
    NullNode nullNode;
    DeserializationConfig deserializationConfig = copyDeserializationConfig();
    if (paramJsonParser.getCurrentToken() == null && paramJsonParser.nextToken() == null)
      return null; 
    JsonNode jsonNode2 = (JsonNode)_readValue(deserializationConfig, paramJsonParser, JSON_NODE_TYPE);
    JsonNode jsonNode1 = jsonNode2;
    if (jsonNode2 == null)
      nullNode = getNodeFactory().nullNode(); 
    return (JsonNode)nullNode;
  }
  
  public JsonNode readTree(JsonParser paramJsonParser, DeserializationConfig paramDeserializationConfig) {
    NullNode nullNode;
    JsonNode jsonNode2 = (JsonNode)_readValue(paramDeserializationConfig, paramJsonParser, JSON_NODE_TYPE);
    JsonNode jsonNode1 = jsonNode2;
    if (jsonNode2 == null)
      nullNode = NullNode.instance; 
    return (JsonNode)nullNode;
  }
  
  public JsonNode readTree(File paramFile) {
    NullNode nullNode;
    JsonNode jsonNode2 = (JsonNode)_readMapAndClose(this._jsonFactory.createJsonParser(paramFile), JSON_NODE_TYPE);
    JsonNode jsonNode1 = jsonNode2;
    if (jsonNode2 == null)
      nullNode = NullNode.instance; 
    return (JsonNode)nullNode;
  }
  
  public JsonNode readTree(InputStream paramInputStream) {
    NullNode nullNode;
    JsonNode jsonNode2 = (JsonNode)_readMapAndClose(this._jsonFactory.createJsonParser(paramInputStream), JSON_NODE_TYPE);
    JsonNode jsonNode1 = jsonNode2;
    if (jsonNode2 == null)
      nullNode = NullNode.instance; 
    return (JsonNode)nullNode;
  }
  
  public JsonNode readTree(Reader paramReader) {
    NullNode nullNode;
    JsonNode jsonNode2 = (JsonNode)_readMapAndClose(this._jsonFactory.createJsonParser(paramReader), JSON_NODE_TYPE);
    JsonNode jsonNode1 = jsonNode2;
    if (jsonNode2 == null)
      nullNode = NullNode.instance; 
    return (JsonNode)nullNode;
  }
  
  public JsonNode readTree(String paramString) {
    NullNode nullNode;
    JsonNode jsonNode2 = (JsonNode)_readMapAndClose(this._jsonFactory.createJsonParser(paramString), JSON_NODE_TYPE);
    JsonNode jsonNode1 = jsonNode2;
    if (jsonNode2 == null)
      nullNode = NullNode.instance; 
    return (JsonNode)nullNode;
  }
  
  public JsonNode readTree(URL paramURL) {
    NullNode nullNode;
    JsonNode jsonNode2 = (JsonNode)_readMapAndClose(this._jsonFactory.createJsonParser(paramURL), JSON_NODE_TYPE);
    JsonNode jsonNode1 = jsonNode2;
    if (jsonNode2 == null)
      nullNode = NullNode.instance; 
    return (JsonNode)nullNode;
  }
  
  public JsonNode readTree(byte[] paramArrayOfbyte) {
    NullNode nullNode;
    JsonNode jsonNode2 = (JsonNode)_readMapAndClose(this._jsonFactory.createJsonParser(paramArrayOfbyte), JSON_NODE_TYPE);
    JsonNode jsonNode1 = jsonNode2;
    if (jsonNode2 == null)
      nullNode = NullNode.instance; 
    return (JsonNode)nullNode;
  }
  
  public Object readValue(JsonNode paramJsonNode, JavaType paramJavaType) {
    return _readValue(copyDeserializationConfig(), treeAsTokens(paramJsonNode), paramJavaType);
  }
  
  public Object readValue(JsonNode paramJsonNode, TypeReference paramTypeReference) {
    return _readValue(copyDeserializationConfig(), treeAsTokens(paramJsonNode), this._typeFactory.constructType(paramTypeReference));
  }
  
  public Object readValue(JsonNode paramJsonNode, Class paramClass) {
    return _readValue(copyDeserializationConfig(), treeAsTokens(paramJsonNode), this._typeFactory.constructType(paramClass));
  }
  
  public Object readValue(JsonParser paramJsonParser, JavaType paramJavaType) {
    return _readValue(copyDeserializationConfig(), paramJsonParser, paramJavaType);
  }
  
  public Object readValue(JsonParser paramJsonParser, JavaType paramJavaType, DeserializationConfig paramDeserializationConfig) {
    return _readValue(paramDeserializationConfig, paramJsonParser, paramJavaType);
  }
  
  public Object readValue(JsonParser paramJsonParser, TypeReference paramTypeReference) {
    return _readValue(copyDeserializationConfig(), paramJsonParser, this._typeFactory.constructType(paramTypeReference));
  }
  
  public Object readValue(JsonParser paramJsonParser, TypeReference paramTypeReference, DeserializationConfig paramDeserializationConfig) {
    return _readValue(paramDeserializationConfig, paramJsonParser, this._typeFactory.constructType(paramTypeReference));
  }
  
  public Object readValue(JsonParser paramJsonParser, Class paramClass) {
    return _readValue(copyDeserializationConfig(), paramJsonParser, this._typeFactory.constructType(paramClass));
  }
  
  public Object readValue(JsonParser paramJsonParser, Class paramClass, DeserializationConfig paramDeserializationConfig) {
    return _readValue(paramDeserializationConfig, paramJsonParser, this._typeFactory.constructType(paramClass));
  }
  
  public Object readValue(File paramFile, JavaType paramJavaType) {
    return _readMapAndClose(this._jsonFactory.createJsonParser(paramFile), paramJavaType);
  }
  
  public Object readValue(File paramFile, TypeReference paramTypeReference) {
    return _readMapAndClose(this._jsonFactory.createJsonParser(paramFile), this._typeFactory.constructType(paramTypeReference));
  }
  
  public Object readValue(File paramFile, Class paramClass) {
    return _readMapAndClose(this._jsonFactory.createJsonParser(paramFile), this._typeFactory.constructType(paramClass));
  }
  
  public Object readValue(InputStream paramInputStream, JavaType paramJavaType) {
    return _readMapAndClose(this._jsonFactory.createJsonParser(paramInputStream), paramJavaType);
  }
  
  public Object readValue(InputStream paramInputStream, TypeReference paramTypeReference) {
    return _readMapAndClose(this._jsonFactory.createJsonParser(paramInputStream), this._typeFactory.constructType(paramTypeReference));
  }
  
  public Object readValue(InputStream paramInputStream, Class paramClass) {
    return _readMapAndClose(this._jsonFactory.createJsonParser(paramInputStream), this._typeFactory.constructType(paramClass));
  }
  
  public Object readValue(Reader paramReader, JavaType paramJavaType) {
    return _readMapAndClose(this._jsonFactory.createJsonParser(paramReader), paramJavaType);
  }
  
  public Object readValue(Reader paramReader, TypeReference paramTypeReference) {
    return _readMapAndClose(this._jsonFactory.createJsonParser(paramReader), this._typeFactory.constructType(paramTypeReference));
  }
  
  public Object readValue(Reader paramReader, Class paramClass) {
    return _readMapAndClose(this._jsonFactory.createJsonParser(paramReader), this._typeFactory.constructType(paramClass));
  }
  
  public Object readValue(String paramString, JavaType paramJavaType) {
    return _readMapAndClose(this._jsonFactory.createJsonParser(paramString), paramJavaType);
  }
  
  public Object readValue(String paramString, TypeReference paramTypeReference) {
    return _readMapAndClose(this._jsonFactory.createJsonParser(paramString), this._typeFactory.constructType(paramTypeReference));
  }
  
  public Object readValue(String paramString, Class paramClass) {
    return _readMapAndClose(this._jsonFactory.createJsonParser(paramString), this._typeFactory.constructType(paramClass));
  }
  
  public Object readValue(URL paramURL, JavaType paramJavaType) {
    return _readMapAndClose(this._jsonFactory.createJsonParser(paramURL), paramJavaType);
  }
  
  public Object readValue(URL paramURL, TypeReference paramTypeReference) {
    return _readMapAndClose(this._jsonFactory.createJsonParser(paramURL), this._typeFactory.constructType(paramTypeReference));
  }
  
  public Object readValue(URL paramURL, Class paramClass) {
    return _readMapAndClose(this._jsonFactory.createJsonParser(paramURL), this._typeFactory.constructType(paramClass));
  }
  
  public Object readValue(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, JavaType paramJavaType) {
    return _readMapAndClose(this._jsonFactory.createJsonParser(paramArrayOfbyte, paramInt1, paramInt2), paramJavaType);
  }
  
  public Object readValue(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, TypeReference paramTypeReference) {
    return _readMapAndClose(this._jsonFactory.createJsonParser(paramArrayOfbyte, paramInt1, paramInt2), this._typeFactory.constructType(paramTypeReference));
  }
  
  public Object readValue(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, Class paramClass) {
    return _readMapAndClose(this._jsonFactory.createJsonParser(paramArrayOfbyte, paramInt1, paramInt2), this._typeFactory.constructType(paramClass));
  }
  
  public Object readValue(byte[] paramArrayOfbyte, JavaType paramJavaType) {
    return _readMapAndClose(this._jsonFactory.createJsonParser(paramArrayOfbyte), paramJavaType);
  }
  
  public Object readValue(byte[] paramArrayOfbyte, TypeReference paramTypeReference) {
    return _readMapAndClose(this._jsonFactory.createJsonParser(paramArrayOfbyte), this._typeFactory.constructType(paramTypeReference));
  }
  
  public Object readValue(byte[] paramArrayOfbyte, Class paramClass) {
    return _readMapAndClose(this._jsonFactory.createJsonParser(paramArrayOfbyte), this._typeFactory.constructType(paramClass));
  }
  
  public MappingIterator readValues(JsonParser paramJsonParser, JavaType paramJavaType) {
    DeserializationConfig deserializationConfig = copyDeserializationConfig();
    return new MappingIterator(paramJavaType, paramJsonParser, _createDeserializationContext(paramJsonParser, deserializationConfig), _findRootDeserializer(deserializationConfig, paramJavaType), false, null);
  }
  
  public MappingIterator readValues(JsonParser paramJsonParser, TypeReference paramTypeReference) {
    return readValues(paramJsonParser, this._typeFactory.constructType(paramTypeReference));
  }
  
  public MappingIterator readValues(JsonParser paramJsonParser, Class paramClass) {
    return readValues(paramJsonParser, this._typeFactory.constructType(paramClass));
  }
  
  public ObjectReader reader() {
    return (new ObjectReader(this, copyDeserializationConfig())).withInjectableValues(this._injectableValues);
  }
  
  public ObjectReader reader(FormatSchema paramFormatSchema) {
    return new ObjectReader(this, copyDeserializationConfig(), null, null, paramFormatSchema, this._injectableValues);
  }
  
  public ObjectReader reader(InjectableValues paramInjectableValues) {
    return new ObjectReader(this, copyDeserializationConfig(), null, null, null, paramInjectableValues);
  }
  
  public ObjectReader reader(JsonNodeFactory paramJsonNodeFactory) {
    return (new ObjectReader(this, copyDeserializationConfig())).withNodeFactory(paramJsonNodeFactory);
  }
  
  public ObjectReader reader(JavaType paramJavaType) {
    return new ObjectReader(this, copyDeserializationConfig(), paramJavaType, null, null, this._injectableValues);
  }
  
  public ObjectReader reader(TypeReference paramTypeReference) {
    return reader(this._typeFactory.constructType(paramTypeReference));
  }
  
  public ObjectReader reader(Class paramClass) {
    return reader(this._typeFactory.constructType(paramClass));
  }
  
  public ObjectReader readerForUpdating(Object paramObject) {
    JavaType javaType = this._typeFactory.constructType(paramObject.getClass());
    return new ObjectReader(this, copyDeserializationConfig(), javaType, paramObject, null, this._injectableValues);
  }
  
  public void registerModule(Module paramModule) {
    if (paramModule.getModuleName() == null)
      throw new IllegalArgumentException("Module without defined name"); 
    if (paramModule.version() == null)
      throw new IllegalArgumentException("Module without defined version"); 
    paramModule.setupModule(new ObjectMapper$1(this, this));
  }
  
  public void registerSubtypes(NamedType... paramVarArgs) {
    getSubtypeResolver().registerSubtypes(paramVarArgs);
  }
  
  public void registerSubtypes(Class... paramVarArgs) {
    getSubtypeResolver().registerSubtypes(paramVarArgs);
  }
  
  @Deprecated
  public ObjectReader schemaBasedReader(FormatSchema paramFormatSchema) {
    return reader(paramFormatSchema);
  }
  
  @Deprecated
  public ObjectWriter schemaBasedWriter(FormatSchema paramFormatSchema) {
    return writer(paramFormatSchema);
  }
  
  public ObjectMapper setAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector) {
    this._serializationConfig = this._serializationConfig.withAnnotationIntrospector(paramAnnotationIntrospector);
    this._deserializationConfig = this._deserializationConfig.withAnnotationIntrospector(paramAnnotationIntrospector);
    return this;
  }
  
  public void setDateFormat(DateFormat paramDateFormat) {
    this._deserializationConfig = this._deserializationConfig.withDateFormat(paramDateFormat);
    this._serializationConfig = this._serializationConfig.withDateFormat(paramDateFormat);
  }
  
  public ObjectMapper setDefaultTyping(TypeResolverBuilder paramTypeResolverBuilder) {
    this._deserializationConfig = this._deserializationConfig.withTypeResolverBuilder(paramTypeResolverBuilder);
    this._serializationConfig = this._serializationConfig.withTypeResolverBuilder(paramTypeResolverBuilder);
    return this;
  }
  
  public ObjectMapper setDeserializationConfig(DeserializationConfig paramDeserializationConfig) {
    this._deserializationConfig = paramDeserializationConfig;
    return this;
  }
  
  public ObjectMapper setDeserializerProvider(DeserializerProvider paramDeserializerProvider) {
    this._deserializerProvider = paramDeserializerProvider;
    return this;
  }
  
  public void setFilters(FilterProvider paramFilterProvider) {
    this._serializationConfig = this._serializationConfig.withFilters(paramFilterProvider);
  }
  
  public void setHandlerInstantiator(HandlerInstantiator paramHandlerInstantiator) {
    this._deserializationConfig = this._deserializationConfig.withHandlerInstantiator(paramHandlerInstantiator);
    this._serializationConfig = this._serializationConfig.withHandlerInstantiator(paramHandlerInstantiator);
  }
  
  public ObjectMapper setInjectableValues(InjectableValues paramInjectableValues) {
    this._injectableValues = paramInjectableValues;
    return this;
  }
  
  public ObjectMapper setNodeFactory(JsonNodeFactory paramJsonNodeFactory) {
    this._deserializationConfig = this._deserializationConfig.withNodeFactory(paramJsonNodeFactory);
    return this;
  }
  
  public ObjectMapper setPropertyNamingStrategy(PropertyNamingStrategy paramPropertyNamingStrategy) {
    this._serializationConfig = this._serializationConfig.withPropertyNamingStrategy(paramPropertyNamingStrategy);
    this._deserializationConfig = this._deserializationConfig.withPropertyNamingStrategy(paramPropertyNamingStrategy);
    return this;
  }
  
  public ObjectMapper setSerializationConfig(SerializationConfig paramSerializationConfig) {
    this._serializationConfig = paramSerializationConfig;
    return this;
  }
  
  public ObjectMapper setSerializationInclusion(JsonSerialize.Inclusion paramInclusion) {
    this._serializationConfig = this._serializationConfig.withSerializationInclusion(paramInclusion);
    return this;
  }
  
  public ObjectMapper setSerializerFactory(SerializerFactory paramSerializerFactory) {
    this._serializerFactory = paramSerializerFactory;
    return this;
  }
  
  public ObjectMapper setSerializerProvider(SerializerProvider paramSerializerProvider) {
    this._serializerProvider = paramSerializerProvider;
    return this;
  }
  
  public void setSubtypeResolver(SubtypeResolver paramSubtypeResolver) {
    this._subtypeResolver = paramSubtypeResolver;
  }
  
  public ObjectMapper setTypeFactory(TypeFactory paramTypeFactory) {
    this._typeFactory = paramTypeFactory;
    this._deserializationConfig = this._deserializationConfig.withTypeFactory(paramTypeFactory);
    this._serializationConfig = this._serializationConfig.withTypeFactory(paramTypeFactory);
    return this;
  }
  
  public ObjectMapper setVisibility(JsonMethod paramJsonMethod, JsonAutoDetect.Visibility paramVisibility) {
    this._deserializationConfig = this._deserializationConfig.withVisibility(paramJsonMethod, paramVisibility);
    this._serializationConfig = this._serializationConfig.withVisibility(paramJsonMethod, paramVisibility);
    return this;
  }
  
  public void setVisibilityChecker(VisibilityChecker paramVisibilityChecker) {
    this._deserializationConfig = this._deserializationConfig.withVisibilityChecker(paramVisibilityChecker);
    this._serializationConfig = this._serializationConfig.withVisibilityChecker(paramVisibilityChecker);
  }
  
  public JsonParser treeAsTokens(JsonNode paramJsonNode) {
    return (JsonParser)new TreeTraversingParser(paramJsonNode, this);
  }
  
  public Object treeToValue(JsonNode paramJsonNode, Class paramClass) {
    return readValue(treeAsTokens(paramJsonNode), paramClass);
  }
  
  @Deprecated
  public ObjectWriter typedWriter(JavaType paramJavaType) {
    return writerWithType(paramJavaType);
  }
  
  @Deprecated
  public ObjectWriter typedWriter(TypeReference paramTypeReference) {
    return writerWithType(paramTypeReference);
  }
  
  @Deprecated
  public ObjectWriter typedWriter(Class paramClass) {
    return writerWithType(paramClass);
  }
  
  @Deprecated
  public ObjectReader updatingReader(Object paramObject) {
    return readerForUpdating(paramObject);
  }
  
  public JsonNode valueToTree(Object paramObject) {
    if (paramObject == null)
      return null; 
    TokenBuffer tokenBuffer = new TokenBuffer(this);
    try {
      writeValue((JsonGenerator)tokenBuffer, paramObject);
      JsonParser jsonParser = tokenBuffer.asParser();
      paramObject = readTree(jsonParser);
      jsonParser.close();
    } catch (IOException iOException) {
      throw new IllegalArgumentException(iOException.getMessage(), iOException);
    } 
    return (JsonNode)iOException;
  }
  
  public Version version() {
    return VersionUtil.versionFor(getClass());
  }
  
  @Deprecated
  public ObjectWriter viewWriter(Class paramClass) {
    return writerWithView(paramClass);
  }
  
  public ObjectMapper withModule(Module paramModule) {
    registerModule(paramModule);
    return this;
  }
  
  public void writeTree(JsonGenerator paramJsonGenerator, JsonNode paramJsonNode) {
    SerializationConfig serializationConfig = copySerializationConfig();
    this._serializerProvider.serializeValue(serializationConfig, paramJsonGenerator, paramJsonNode, this._serializerFactory);
    if (serializationConfig.isEnabled(SerializationConfig$Feature.FLUSH_AFTER_WRITE_VALUE))
      paramJsonGenerator.flush(); 
  }
  
  public void writeTree(JsonGenerator paramJsonGenerator, JsonNode paramJsonNode, SerializationConfig paramSerializationConfig) {
    this._serializerProvider.serializeValue(paramSerializationConfig, paramJsonGenerator, paramJsonNode, this._serializerFactory);
    if (paramSerializationConfig.isEnabled(SerializationConfig$Feature.FLUSH_AFTER_WRITE_VALUE))
      paramJsonGenerator.flush(); 
  }
  
  public void writeValue(JsonGenerator paramJsonGenerator, Object paramObject) {
    SerializationConfig serializationConfig = copySerializationConfig();
    if (serializationConfig.isEnabled(SerializationConfig$Feature.CLOSE_CLOSEABLE) && paramObject instanceof Closeable) {
      _writeCloseableValue(paramJsonGenerator, paramObject, serializationConfig);
      return;
    } 
    this._serializerProvider.serializeValue(serializationConfig, paramJsonGenerator, paramObject, this._serializerFactory);
    if (serializationConfig.isEnabled(SerializationConfig$Feature.FLUSH_AFTER_WRITE_VALUE))
      paramJsonGenerator.flush(); 
  }
  
  public void writeValue(JsonGenerator paramJsonGenerator, Object paramObject, SerializationConfig paramSerializationConfig) {
    if (paramSerializationConfig.isEnabled(SerializationConfig$Feature.CLOSE_CLOSEABLE) && paramObject instanceof Closeable) {
      _writeCloseableValue(paramJsonGenerator, paramObject, paramSerializationConfig);
      return;
    } 
    this._serializerProvider.serializeValue(paramSerializationConfig, paramJsonGenerator, paramObject, this._serializerFactory);
    if (paramSerializationConfig.isEnabled(SerializationConfig$Feature.FLUSH_AFTER_WRITE_VALUE))
      paramJsonGenerator.flush(); 
  }
  
  public void writeValue(File paramFile, Object paramObject) {
    _configAndWriteValue(this._jsonFactory.createJsonGenerator(paramFile, JsonEncoding.UTF8), paramObject);
  }
  
  public void writeValue(OutputStream paramOutputStream, Object paramObject) {
    _configAndWriteValue(this._jsonFactory.createJsonGenerator(paramOutputStream, JsonEncoding.UTF8), paramObject);
  }
  
  public void writeValue(Writer paramWriter, Object paramObject) {
    _configAndWriteValue(this._jsonFactory.createJsonGenerator(paramWriter), paramObject);
  }
  
  public byte[] writeValueAsBytes(Object paramObject) {
    ByteArrayBuilder byteArrayBuilder = new ByteArrayBuilder(this._jsonFactory._getBufferRecycler());
    _configAndWriteValue(this._jsonFactory.createJsonGenerator((OutputStream)byteArrayBuilder, JsonEncoding.UTF8), paramObject);
    paramObject = byteArrayBuilder.toByteArray();
    byteArrayBuilder.release();
    return (byte[])paramObject;
  }
  
  public String writeValueAsString(Object paramObject) {
    SegmentedStringWriter segmentedStringWriter = new SegmentedStringWriter(this._jsonFactory._getBufferRecycler());
    _configAndWriteValue(this._jsonFactory.createJsonGenerator((Writer)segmentedStringWriter), paramObject);
    return segmentedStringWriter.getAndClear();
  }
  
  public ObjectWriter writer() {
    return new ObjectWriter(this, copySerializationConfig());
  }
  
  public ObjectWriter writer(FormatSchema paramFormatSchema) {
    return new ObjectWriter(this, copySerializationConfig(), paramFormatSchema);
  }
  
  public ObjectWriter writer(PrettyPrinter paramPrettyPrinter) {
    PrettyPrinter prettyPrinter = paramPrettyPrinter;
    if (paramPrettyPrinter == null)
      prettyPrinter = ObjectWriter.NULL_PRETTY_PRINTER; 
    return new ObjectWriter(this, copySerializationConfig(), null, prettyPrinter);
  }
  
  public ObjectWriter writer(FilterProvider paramFilterProvider) {
    return new ObjectWriter(this, copySerializationConfig().withFilters(paramFilterProvider));
  }
  
  public ObjectWriter writer(DateFormat paramDateFormat) {
    return new ObjectWriter(this, copySerializationConfig().withDateFormat(paramDateFormat));
  }
  
  public ObjectWriter writerWithDefaultPrettyPrinter() {
    return new ObjectWriter(this, copySerializationConfig(), null, _defaultPrettyPrinter());
  }
  
  public ObjectWriter writerWithType(JavaType paramJavaType) {
    return new ObjectWriter(this, copySerializationConfig(), paramJavaType, null);
  }
  
  public ObjectWriter writerWithType(TypeReference paramTypeReference) {
    if (paramTypeReference == null) {
      paramTypeReference = null;
      return new ObjectWriter(this, copySerializationConfig(), (JavaType)paramTypeReference, null);
    } 
    JavaType javaType = this._typeFactory.constructType(paramTypeReference);
    return new ObjectWriter(this, copySerializationConfig(), javaType, null);
  }
  
  public ObjectWriter writerWithType(Class paramClass) {
    if (paramClass == null) {
      paramClass = null;
      return new ObjectWriter(this, copySerializationConfig(), (JavaType)paramClass, null);
    } 
    JavaType javaType = this._typeFactory.constructType(paramClass);
    return new ObjectWriter(this, copySerializationConfig(), javaType, null);
  }
  
  public ObjectWriter writerWithView(Class paramClass) {
    return new ObjectWriter(this, copySerializationConfig().withView(paramClass));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ObjectMapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */