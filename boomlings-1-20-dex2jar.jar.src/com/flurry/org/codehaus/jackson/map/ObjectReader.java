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

public class ObjectReader extends ObjectCodec implements Versioned {
  private static final JavaType JSON_NODE_TYPE = (JavaType)SimpleType.constructUnsafe(JsonNode.class);
  
  protected final DeserializationConfig _config;
  
  protected final InjectableValues _injectableValues;
  
  protected final JsonFactory _jsonFactory;
  
  protected final DeserializerProvider _provider;
  
  protected final ConcurrentHashMap _rootDeserializers;
  
  protected final FormatSchema _schema;
  
  protected final boolean _unwrapRoot;
  
  protected final Object _valueToUpdate;
  
  protected final JavaType _valueType;
  
  protected ObjectReader(ObjectMapper paramObjectMapper, DeserializationConfig paramDeserializationConfig) {
    this(paramObjectMapper, paramDeserializationConfig, (JavaType)null, (Object)null, (FormatSchema)null, (InjectableValues)null);
  }
  
  protected ObjectReader(ObjectMapper paramObjectMapper, DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, Object paramObject, FormatSchema paramFormatSchema, InjectableValues paramInjectableValues) {
    this._config = paramDeserializationConfig;
    this._rootDeserializers = paramObjectMapper._rootDeserializers;
    this._provider = paramObjectMapper._deserializerProvider;
    this._jsonFactory = paramObjectMapper._jsonFactory;
    this._valueType = paramJavaType;
    this._valueToUpdate = paramObject;
    if (paramObject != null && paramJavaType.isArrayType())
      throw new IllegalArgumentException("Can not update an array value"); 
    this._schema = paramFormatSchema;
    this._injectableValues = paramInjectableValues;
    this._unwrapRoot = paramDeserializationConfig.isEnabled(DeserializationConfig$Feature.UNWRAP_ROOT_VALUE);
  }
  
  protected ObjectReader(ObjectReader paramObjectReader, DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, Object paramObject, FormatSchema paramFormatSchema, InjectableValues paramInjectableValues) {
    this._config = paramDeserializationConfig;
    this._rootDeserializers = paramObjectReader._rootDeserializers;
    this._provider = paramObjectReader._provider;
    this._jsonFactory = paramObjectReader._jsonFactory;
    this._valueType = paramJavaType;
    this._valueToUpdate = paramObject;
    if (paramObject != null && paramJavaType.isArrayType())
      throw new IllegalArgumentException("Can not update an array value"); 
    this._schema = paramFormatSchema;
    this._injectableValues = paramInjectableValues;
    this._unwrapRoot = paramDeserializationConfig.isEnabled(DeserializationConfig$Feature.UNWRAP_ROOT_VALUE);
  }
  
  protected static JsonToken _initForReading(JsonParser paramJsonParser) {
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
  
  protected Object _bind(JsonParser paramJsonParser) {
    Object object = _initForReading(paramJsonParser);
    if (object == JsonToken.VALUE_NULL) {
      if (this._valueToUpdate == null) {
        Object object1 = _findRootDeserializer(this._config, this._valueType).getNullValue();
        paramJsonParser.clearCurrentToken();
        return object1;
      } 
      object = this._valueToUpdate;
      paramJsonParser.clearCurrentToken();
      return object;
    } 
    if (object == JsonToken.END_ARRAY || object == JsonToken.END_OBJECT) {
      object = this._valueToUpdate;
      paramJsonParser.clearCurrentToken();
      return object;
    } 
    DeserializationContext deserializationContext = _createDeserializationContext(paramJsonParser, this._config);
    object = _findRootDeserializer(this._config, this._valueType);
    if (this._unwrapRoot) {
      object = _unwrapAndDeserialize(paramJsonParser, deserializationContext, this._valueType, (JsonDeserializer)object);
      paramJsonParser.clearCurrentToken();
      return object;
    } 
    if (this._valueToUpdate == null) {
      object = object.deserialize(paramJsonParser, deserializationContext);
      paramJsonParser.clearCurrentToken();
      return object;
    } 
    object.deserialize(paramJsonParser, deserializationContext, this._valueToUpdate);
    object = this._valueToUpdate;
    paramJsonParser.clearCurrentToken();
    return object;
  }
  
  protected Object _bindAndClose(JsonParser paramJsonParser) {
    if (this._schema != null)
      paramJsonParser.setSchema(this._schema); 
    try {
      Object object = _initForReading(paramJsonParser);
      if (object == JsonToken.VALUE_NULL) {
        if (this._valueToUpdate == null) {
          Object object1 = _findRootDeserializer(this._config, this._valueType).getNullValue();
        } else {
          object = this._valueToUpdate;
        } 
      } else if (object == JsonToken.END_ARRAY || object == JsonToken.END_OBJECT) {
        object = this._valueToUpdate;
      } else {
        object = _createDeserializationContext(paramJsonParser, this._config);
        JsonDeserializer jsonDeserializer = _findRootDeserializer(this._config, this._valueType);
        if (this._unwrapRoot) {
          object = _unwrapAndDeserialize(paramJsonParser, (DeserializationContext)object, this._valueType, jsonDeserializer);
        } else if (this._valueToUpdate == null) {
          object = jsonDeserializer.deserialize(paramJsonParser, (DeserializationContext)object);
        } else {
          jsonDeserializer.deserialize(paramJsonParser, (DeserializationContext)object, this._valueToUpdate);
          object = this._valueToUpdate;
        } 
      } 
    } finally {
      try {
        iOException.close();
      } catch (IOException iOException1) {}
    } 
  }
  
  protected JsonNode _bindAndCloseAsTree(JsonParser paramJsonParser) {
    if (this._schema != null)
      paramJsonParser.setSchema(this._schema); 
    try {
      JsonNode jsonNode = _bindAsTree(paramJsonParser);
    } finally {
      try {
        iOException.close();
      } catch (IOException iOException1) {}
    } 
  }
  
  protected JsonNode _bindAsTree(JsonParser paramJsonParser) {
    JsonToken jsonToken = _initForReading(paramJsonParser);
    if (jsonToken == JsonToken.VALUE_NULL || jsonToken == JsonToken.END_ARRAY || jsonToken == JsonToken.END_OBJECT) {
      NullNode nullNode = NullNode.instance;
      paramJsonParser.clearCurrentToken();
      return (JsonNode)nullNode;
    } 
    DeserializationContext deserializationContext = _createDeserializationContext(paramJsonParser, this._config);
    JsonDeserializer jsonDeserializer = _findRootDeserializer(this._config, JSON_NODE_TYPE);
    if (this._unwrapRoot) {
      jsonNode = (JsonNode)_unwrapAndDeserialize(paramJsonParser, deserializationContext, JSON_NODE_TYPE, jsonDeserializer);
      paramJsonParser.clearCurrentToken();
      return jsonNode;
    } 
    JsonNode jsonNode = (JsonNode)jsonDeserializer.deserialize(paramJsonParser, (DeserializationContext)jsonNode);
    paramJsonParser.clearCurrentToken();
    return jsonNode;
  }
  
  protected DeserializationContext _createDeserializationContext(JsonParser paramJsonParser, DeserializationConfig paramDeserializationConfig) {
    return (DeserializationContext)new StdDeserializationContext(paramDeserializationConfig, paramJsonParser, this._provider, this._injectableValues);
  }
  
  protected JsonDeserializer _findRootDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType) {
    if (paramJavaType == null)
      throw new JsonMappingException("No value type configured for ObjectReader"); 
    JsonDeserializer jsonDeserializer2 = (JsonDeserializer)this._rootDeserializers.get(paramJavaType);
    if (jsonDeserializer2 != null)
      return jsonDeserializer2; 
    JsonDeserializer jsonDeserializer1 = this._provider.findTypedValueDeserializer(paramDeserializationConfig, paramJavaType, null);
    if (jsonDeserializer1 == null)
      throw new JsonMappingException("Can not find a deserializer for type " + paramJavaType); 
    this._rootDeserializers.put(paramJavaType, jsonDeserializer1);
    return jsonDeserializer1;
  }
  
  protected Object _unwrapAndDeserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, JavaType paramJavaType, JsonDeserializer paramJsonDeserializer) {
    Object object;
    SerializedString serializedString = this._provider.findExpectedRootName(paramDeserializationContext.getConfig(), paramJavaType);
    if (paramJsonParser.getCurrentToken() != JsonToken.START_OBJECT)
      throw JsonMappingException.from(paramJsonParser, "Current token not START_OBJECT (needed to unwrap root name '" + serializedString + "'), but " + paramJsonParser.getCurrentToken()); 
    if (paramJsonParser.nextToken() != JsonToken.FIELD_NAME)
      throw JsonMappingException.from(paramJsonParser, "Current token not FIELD_NAME (to contain expected root name '" + serializedString + "'), but " + paramJsonParser.getCurrentToken()); 
    String str = paramJsonParser.getCurrentName();
    if (!serializedString.getValue().equals(str))
      throw JsonMappingException.from(paramJsonParser, "Root name '" + str + "' does not match expected ('" + serializedString + "') for type " + paramJavaType); 
    paramJsonParser.nextToken();
    if (this._valueToUpdate == null) {
      object = paramJsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
    } else {
      paramJsonDeserializer.deserialize(paramJsonParser, (DeserializationContext)object, this._valueToUpdate);
      object = this._valueToUpdate;
    } 
    if (paramJsonParser.nextToken() != JsonToken.END_OBJECT)
      throw JsonMappingException.from(paramJsonParser, "Current token not END_OBJECT (to match wrapper object with root name '" + serializedString + "'), but " + paramJsonParser.getCurrentToken()); 
    return object;
  }
  
  public JsonNode createArrayNode() {
    return (JsonNode)this._config.getNodeFactory().arrayNode();
  }
  
  public JsonNode createObjectNode() {
    return (JsonNode)this._config.getNodeFactory().objectNode();
  }
  
  public JsonNode readTree(JsonParser paramJsonParser) {
    return _bindAsTree(paramJsonParser);
  }
  
  public JsonNode readTree(InputStream paramInputStream) {
    return _bindAndCloseAsTree(this._jsonFactory.createJsonParser(paramInputStream));
  }
  
  public JsonNode readTree(Reader paramReader) {
    return _bindAndCloseAsTree(this._jsonFactory.createJsonParser(paramReader));
  }
  
  public JsonNode readTree(String paramString) {
    return _bindAndCloseAsTree(this._jsonFactory.createJsonParser(paramString));
  }
  
  public Object readValue(JsonNode paramJsonNode) {
    return _bindAndClose(treeAsTokens(paramJsonNode));
  }
  
  public Object readValue(JsonParser paramJsonParser) {
    return _bind(paramJsonParser);
  }
  
  public Object readValue(JsonParser paramJsonParser, JavaType paramJavaType) {
    return withType(paramJavaType).readValue(paramJsonParser);
  }
  
  public Object readValue(JsonParser paramJsonParser, TypeReference paramTypeReference) {
    return withType(paramTypeReference).readValue(paramJsonParser);
  }
  
  public Object readValue(JsonParser paramJsonParser, Class paramClass) {
    return withType(paramClass).readValue(paramJsonParser);
  }
  
  public Object readValue(File paramFile) {
    return _bindAndClose(this._jsonFactory.createJsonParser(paramFile));
  }
  
  public Object readValue(InputStream paramInputStream) {
    return _bindAndClose(this._jsonFactory.createJsonParser(paramInputStream));
  }
  
  public Object readValue(Reader paramReader) {
    return _bindAndClose(this._jsonFactory.createJsonParser(paramReader));
  }
  
  public Object readValue(String paramString) {
    return _bindAndClose(this._jsonFactory.createJsonParser(paramString));
  }
  
  public Object readValue(URL paramURL) {
    return _bindAndClose(this._jsonFactory.createJsonParser(paramURL));
  }
  
  public Object readValue(byte[] paramArrayOfbyte) {
    return _bindAndClose(this._jsonFactory.createJsonParser(paramArrayOfbyte));
  }
  
  public Object readValue(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    return _bindAndClose(this._jsonFactory.createJsonParser(paramArrayOfbyte, paramInt1, paramInt2));
  }
  
  public MappingIterator readValues(JsonParser paramJsonParser) {
    DeserializationContext deserializationContext = _createDeserializationContext(paramJsonParser, this._config);
    return new MappingIterator(this._valueType, paramJsonParser, deserializationContext, _findRootDeserializer(this._config, this._valueType), false, this._valueToUpdate);
  }
  
  public MappingIterator readValues(File paramFile) {
    JsonParser jsonParser = this._jsonFactory.createJsonParser(paramFile);
    if (this._schema != null)
      jsonParser.setSchema(this._schema); 
    DeserializationContext deserializationContext = _createDeserializationContext(jsonParser, this._config);
    return new MappingIterator(this._valueType, jsonParser, deserializationContext, _findRootDeserializer(this._config, this._valueType), true, this._valueToUpdate);
  }
  
  public MappingIterator readValues(InputStream paramInputStream) {
    JsonParser jsonParser = this._jsonFactory.createJsonParser(paramInputStream);
    if (this._schema != null)
      jsonParser.setSchema(this._schema); 
    DeserializationContext deserializationContext = _createDeserializationContext(jsonParser, this._config);
    return new MappingIterator(this._valueType, jsonParser, deserializationContext, _findRootDeserializer(this._config, this._valueType), true, this._valueToUpdate);
  }
  
  public MappingIterator readValues(Reader paramReader) {
    JsonParser jsonParser = this._jsonFactory.createJsonParser(paramReader);
    if (this._schema != null)
      jsonParser.setSchema(this._schema); 
    DeserializationContext deserializationContext = _createDeserializationContext(jsonParser, this._config);
    return new MappingIterator(this._valueType, jsonParser, deserializationContext, _findRootDeserializer(this._config, this._valueType), true, this._valueToUpdate);
  }
  
  public MappingIterator readValues(String paramString) {
    JsonParser jsonParser = this._jsonFactory.createJsonParser(paramString);
    if (this._schema != null)
      jsonParser.setSchema(this._schema); 
    DeserializationContext deserializationContext = _createDeserializationContext(jsonParser, this._config);
    return new MappingIterator(this._valueType, jsonParser, deserializationContext, _findRootDeserializer(this._config, this._valueType), true, this._valueToUpdate);
  }
  
  public MappingIterator readValues(URL paramURL) {
    JsonParser jsonParser = this._jsonFactory.createJsonParser(paramURL);
    if (this._schema != null)
      jsonParser.setSchema(this._schema); 
    DeserializationContext deserializationContext = _createDeserializationContext(jsonParser, this._config);
    return new MappingIterator(this._valueType, jsonParser, deserializationContext, _findRootDeserializer(this._config, this._valueType), true, this._valueToUpdate);
  }
  
  public final MappingIterator readValues(byte[] paramArrayOfbyte) {
    return readValues(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  public MappingIterator readValues(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    JsonParser jsonParser = this._jsonFactory.createJsonParser(paramArrayOfbyte, paramInt1, paramInt2);
    if (this._schema != null)
      jsonParser.setSchema(this._schema); 
    DeserializationContext deserializationContext = _createDeserializationContext(jsonParser, this._config);
    return new MappingIterator(this._valueType, jsonParser, deserializationContext, _findRootDeserializer(this._config, this._valueType), true, this._valueToUpdate);
  }
  
  public Iterator readValues(JsonParser paramJsonParser, JavaType paramJavaType) {
    return withType(paramJavaType).readValues(paramJsonParser);
  }
  
  public Iterator readValues(JsonParser paramJsonParser, TypeReference paramTypeReference) {
    return withType(paramTypeReference).readValues(paramJsonParser);
  }
  
  public Iterator readValues(JsonParser paramJsonParser, Class paramClass) {
    return withType(paramClass).readValues(paramJsonParser);
  }
  
  public JsonParser treeAsTokens(JsonNode paramJsonNode) {
    return (JsonParser)new TreeTraversingParser(paramJsonNode, this);
  }
  
  public Object treeToValue(JsonNode paramJsonNode, Class paramClass) {
    return readValue(treeAsTokens(paramJsonNode), paramClass);
  }
  
  public Version version() {
    return VersionUtil.versionFor(getClass());
  }
  
  public ObjectReader withInjectableValues(InjectableValues paramInjectableValues) {
    return (this._injectableValues == paramInjectableValues) ? this : new ObjectReader(this, this._config, this._valueType, this._valueToUpdate, this._schema, paramInjectableValues);
  }
  
  public ObjectReader withNodeFactory(JsonNodeFactory paramJsonNodeFactory) {
    return (paramJsonNodeFactory == this._config.getNodeFactory()) ? this : new ObjectReader(this, this._config.withNodeFactory(paramJsonNodeFactory), this._valueType, this._valueToUpdate, this._schema, this._injectableValues);
  }
  
  public ObjectReader withSchema(FormatSchema paramFormatSchema) {
    return (this._schema == paramFormatSchema) ? this : new ObjectReader(this, this._config, this._valueType, this._valueToUpdate, paramFormatSchema, this._injectableValues);
  }
  
  public ObjectReader withType(JavaType paramJavaType) {
    return (paramJavaType == this._valueType) ? this : new ObjectReader(this, this._config, paramJavaType, this._valueToUpdate, this._schema, this._injectableValues);
  }
  
  public ObjectReader withType(TypeReference paramTypeReference) {
    return withType(this._config.getTypeFactory().constructType(paramTypeReference.getType()));
  }
  
  public ObjectReader withType(Class paramClass) {
    return withType(this._config.constructType(paramClass));
  }
  
  public ObjectReader withType(Type paramType) {
    return withType(this._config.getTypeFactory().constructType(paramType));
  }
  
  public ObjectReader withValueToUpdate(Object paramObject) {
    JavaType javaType;
    if (paramObject == this._valueToUpdate)
      return this; 
    if (paramObject == null)
      throw new IllegalArgumentException("cat not update null value"); 
    if (this._valueType == null) {
      javaType = this._config.constructType(paramObject.getClass());
    } else {
      javaType = this._valueType;
    } 
    return new ObjectReader(this, this._config, javaType, paramObject, this._schema, this._injectableValues);
  }
  
  public void writeTree(JsonGenerator paramJsonGenerator, JsonNode paramJsonNode) {
    throw new UnsupportedOperationException("Not implemented for ObjectReader");
  }
  
  public void writeValue(JsonGenerator paramJsonGenerator, Object paramObject) {
    throw new UnsupportedOperationException("Not implemented for ObjectReader");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ObjectReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */