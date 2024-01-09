package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.ContextualSerializer;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.MapperConfig;
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
import com.flurry.org.codehaus.jackson.map.ser.std.StdKeySerializer;
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

public class StdSerializerProvider extends SerializerProvider {
  static final boolean CACHE_UNKNOWN_MAPPINGS = false;
  
  @Deprecated
  public static final JsonSerializer DEFAULT_KEY_SERIALIZER;
  
  public static final JsonSerializer DEFAULT_NULL_KEY_SERIALIZER = (JsonSerializer)new FailingSerializer("Null key for a Map not allowed in JSON (use a converting NullKeySerializer?)");
  
  public static final JsonSerializer DEFAULT_UNKNOWN_SERIALIZER;
  
  protected DateFormat _dateFormat;
  
  protected JsonSerializer _keySerializer;
  
  protected final ReadOnlyClassToSerializerMap _knownSerializers;
  
  protected JsonSerializer _nullKeySerializer = DEFAULT_NULL_KEY_SERIALIZER;
  
  protected JsonSerializer _nullValueSerializer = (JsonSerializer)NullSerializer.instance;
  
  protected final RootNameLookup _rootNames;
  
  protected final SerializerCache _serializerCache;
  
  protected final SerializerFactory _serializerFactory;
  
  protected JsonSerializer _unknownTypeSerializer = DEFAULT_UNKNOWN_SERIALIZER;
  
  static {
    DEFAULT_KEY_SERIALIZER = (JsonSerializer)new StdKeySerializer();
    DEFAULT_UNKNOWN_SERIALIZER = (JsonSerializer)new UnknownSerializer();
  }
  
  public StdSerializerProvider() {
    super(null);
    this._serializerFactory = null;
    this._serializerCache = new SerializerCache();
    this._knownSerializers = null;
    this._rootNames = new RootNameLookup();
  }
  
  protected StdSerializerProvider(SerializationConfig paramSerializationConfig, StdSerializerProvider paramStdSerializerProvider, SerializerFactory paramSerializerFactory) {
    super(paramSerializationConfig);
    if (paramSerializationConfig == null)
      throw new NullPointerException(); 
    this._serializerFactory = paramSerializerFactory;
    this._serializerCache = paramStdSerializerProvider._serializerCache;
    this._unknownTypeSerializer = paramStdSerializerProvider._unknownTypeSerializer;
    this._keySerializer = paramStdSerializerProvider._keySerializer;
    this._nullValueSerializer = paramStdSerializerProvider._nullValueSerializer;
    this._nullKeySerializer = paramStdSerializerProvider._nullKeySerializer;
    this._rootNames = paramStdSerializerProvider._rootNames;
    this._knownSerializers = this._serializerCache.getReadOnlyLookupMap();
  }
  
  protected JsonSerializer _createAndCacheUntypedSerializer(JavaType paramJavaType, BeanProperty paramBeanProperty) {
    try {
      JsonSerializer jsonSerializer = _createUntypedSerializer(paramJavaType, paramBeanProperty);
      if (jsonSerializer != null)
        this._serializerCache.addAndResolveNonTypedSerializer(paramJavaType, jsonSerializer, this); 
      return jsonSerializer;
    } catch (IllegalArgumentException illegalArgumentException) {
      throw new JsonMappingException(illegalArgumentException.getMessage(), null, illegalArgumentException);
    } 
  }
  
  protected JsonSerializer _createAndCacheUntypedSerializer(Class paramClass, BeanProperty paramBeanProperty) {
    try {
      JsonSerializer jsonSerializer = _createUntypedSerializer(this._config.constructType(paramClass), paramBeanProperty);
      if (jsonSerializer != null)
        this._serializerCache.addAndResolveNonTypedSerializer(paramClass, jsonSerializer, this); 
      return jsonSerializer;
    } catch (IllegalArgumentException illegalArgumentException) {
      throw new JsonMappingException(illegalArgumentException.getMessage(), null, illegalArgumentException);
    } 
  }
  
  protected JsonSerializer _createUntypedSerializer(JavaType paramJavaType, BeanProperty paramBeanProperty) {
    return this._serializerFactory.createSerializer(this._config, paramJavaType, paramBeanProperty);
  }
  
  protected JsonSerializer _findExplicitUntypedSerializer(Class paramClass, BeanProperty paramBeanProperty) {
    JsonSerializer jsonSerializer = this._knownSerializers.untypedValueSerializer(paramClass);
    if (jsonSerializer == null) {
      JsonSerializer jsonSerializer1 = this._serializerCache.untypedValueSerializer(paramClass);
      jsonSerializer = jsonSerializer1;
      if (jsonSerializer1 == null)
        try {
          jsonSerializer = _createAndCacheUntypedSerializer(paramClass, paramBeanProperty);
        } catch (Exception exception) {
          jsonSerializer = null;
        }  
    } 
    return jsonSerializer;
  }
  
  protected JsonSerializer _handleContextualResolvable(JsonSerializer paramJsonSerializer, BeanProperty paramBeanProperty) {
    if (paramJsonSerializer instanceof ContextualSerializer) {
      JsonSerializer jsonSerializer = ((ContextualSerializer)paramJsonSerializer).createContextual(this._config, paramBeanProperty);
      if (jsonSerializer != paramJsonSerializer) {
        paramJsonSerializer = jsonSerializer;
        if (jsonSerializer instanceof ResolvableSerializer) {
          ((ResolvableSerializer)jsonSerializer).resolve(this);
          paramJsonSerializer = jsonSerializer;
        } 
      } 
    } 
    return paramJsonSerializer;
  }
  
  protected void _reportIncompatibleRootType(Object paramObject, JavaType paramJavaType) {
    if (paramJavaType.isPrimitive() && ClassUtil.wrapperType(paramJavaType.getRawClass()).isAssignableFrom(paramObject.getClass()))
      return; 
    throw new JsonMappingException("Incompatible types: declared root type (" + paramJavaType + ") vs " + paramObject.getClass().getName());
  }
  
  protected void _serializeValue(JsonGenerator paramJsonGenerator, Object paramObject) {
    boolean bool;
    JsonSerializer jsonSerializer;
    if (paramObject == null) {
      jsonSerializer = getNullValueSerializer();
      bool = false;
    } else {
      JsonSerializer jsonSerializer1 = findTypedValueSerializer(paramObject.getClass(), true, (BeanProperty)null);
      boolean bool1 = this._config.isEnabled(SerializationConfig.Feature.WRAP_ROOT_VALUE);
      bool = bool1;
      jsonSerializer = jsonSerializer1;
      if (bool1) {
        paramJsonGenerator.writeStartObject();
        paramJsonGenerator.writeFieldName(this._rootNames.findRootName(paramObject.getClass(), (MapperConfig)this._config));
        bool = bool1;
        jsonSerializer = jsonSerializer1;
      } 
    } 
    try {
      jsonSerializer.serialize(paramObject, paramJsonGenerator, this);
      if (bool)
        paramJsonGenerator.writeEndObject(); 
      return;
    } catch (IOException iOException) {
      throw iOException;
    } catch (Exception exception) {
      paramObject = exception.getMessage();
      Object object = paramObject;
      if (paramObject == null)
        object = "[no message for " + exception.getClass().getName() + "]"; 
      throw new JsonMappingException(object, exception);
    } 
  }
  
  protected void _serializeValue(JsonGenerator paramJsonGenerator, Object paramObject, JavaType paramJavaType) {
    boolean bool;
    JsonSerializer jsonSerializer;
    if (paramObject == null) {
      jsonSerializer = getNullValueSerializer();
      bool = false;
    } else {
      if (!paramJavaType.getRawClass().isAssignableFrom(paramObject.getClass()))
        _reportIncompatibleRootType(paramObject, paramJavaType); 
      JsonSerializer jsonSerializer1 = findTypedValueSerializer(paramJavaType, true, (BeanProperty)null);
      boolean bool1 = this._config.isEnabled(SerializationConfig.Feature.WRAP_ROOT_VALUE);
      bool = bool1;
      jsonSerializer = jsonSerializer1;
      if (bool1) {
        paramJsonGenerator.writeStartObject();
        paramJsonGenerator.writeFieldName(this._rootNames.findRootName(paramJavaType, (MapperConfig)this._config));
        bool = bool1;
        jsonSerializer = jsonSerializer1;
      } 
    } 
    try {
      jsonSerializer.serialize(paramObject, paramJsonGenerator, this);
      if (bool)
        paramJsonGenerator.writeEndObject(); 
      return;
    } catch (IOException iOException) {
      throw iOException;
    } catch (Exception exception) {
      paramObject = exception.getMessage();
      Object object = paramObject;
      if (paramObject == null)
        object = "[no message for " + exception.getClass().getName() + "]"; 
      throw new JsonMappingException(object, exception);
    } 
  }
  
  public int cachedSerializersCount() {
    return this._serializerCache.size();
  }
  
  protected StdSerializerProvider createInstance(SerializationConfig paramSerializationConfig, SerializerFactory paramSerializerFactory) {
    return new StdSerializerProvider(paramSerializationConfig, this, paramSerializerFactory);
  }
  
  public void defaultSerializeDateKey(long paramLong, JsonGenerator paramJsonGenerator) {
    if (isEnabled(SerializationConfig.Feature.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
      paramJsonGenerator.writeFieldName(String.valueOf(paramLong));
      return;
    } 
    if (this._dateFormat == null)
      this._dateFormat = (DateFormat)this._config.getDateFormat().clone(); 
    paramJsonGenerator.writeFieldName(this._dateFormat.format(new Date(paramLong)));
  }
  
  public void defaultSerializeDateKey(Date paramDate, JsonGenerator paramJsonGenerator) {
    if (isEnabled(SerializationConfig.Feature.WRITE_DATE_KEYS_AS_TIMESTAMPS)) {
      paramJsonGenerator.writeFieldName(String.valueOf(paramDate.getTime()));
      return;
    } 
    if (this._dateFormat == null)
      this._dateFormat = (DateFormat)this._config.getDateFormat().clone(); 
    paramJsonGenerator.writeFieldName(this._dateFormat.format(paramDate));
  }
  
  public final void defaultSerializeDateValue(long paramLong, JsonGenerator paramJsonGenerator) {
    if (isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
      paramJsonGenerator.writeNumber(paramLong);
      return;
    } 
    if (this._dateFormat == null)
      this._dateFormat = (DateFormat)this._config.getDateFormat().clone(); 
    paramJsonGenerator.writeString(this._dateFormat.format(new Date(paramLong)));
  }
  
  public final void defaultSerializeDateValue(Date paramDate, JsonGenerator paramJsonGenerator) {
    if (isEnabled(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS)) {
      paramJsonGenerator.writeNumber(paramDate.getTime());
      return;
    } 
    if (this._dateFormat == null)
      this._dateFormat = (DateFormat)this._config.getDateFormat().clone(); 
    paramJsonGenerator.writeString(this._dateFormat.format(paramDate));
  }
  
  public JsonSerializer findKeySerializer(JavaType paramJavaType, BeanProperty paramBeanProperty) {
    JsonSerializer jsonSerializer3 = this._serializerFactory.createKeySerializer(this._config, paramJavaType, paramBeanProperty);
    JsonSerializer jsonSerializer2 = jsonSerializer3;
    if (jsonSerializer3 == null)
      if (this._keySerializer == null) {
        jsonSerializer2 = StdKeySerializers.getStdKeySerializer(paramJavaType);
      } else {
        jsonSerializer2 = this._keySerializer;
      }  
    JsonSerializer jsonSerializer1 = jsonSerializer2;
    if (jsonSerializer2 instanceof ContextualSerializer)
      jsonSerializer1 = ((ContextualSerializer)jsonSerializer2).createContextual(this._config, paramBeanProperty); 
    return jsonSerializer1;
  }
  
  public JsonSerializer findTypedValueSerializer(JavaType paramJavaType, boolean paramBoolean, BeanProperty paramBeanProperty) {
    JsonSerializer jsonSerializer = this._knownSerializers.typedValueSerializer(paramJavaType);
    if (jsonSerializer == null) {
      JsonSerializer jsonSerializer1 = this._serializerCache.typedValueSerializer(paramJavaType);
      jsonSerializer = jsonSerializer1;
      if (jsonSerializer1 == null) {
        JsonSerializer jsonSerializer2;
        jsonSerializer = findValueSerializer(paramJavaType, paramBeanProperty);
        TypeSerializer typeSerializer = this._serializerFactory.createTypeSerializer(this._config, paramJavaType, paramBeanProperty);
        if (typeSerializer != null) {
          jsonSerializer2 = new StdSerializerProvider$WrappedSerializer(typeSerializer, jsonSerializer);
        } else {
          jsonSerializer2 = jsonSerializer;
        } 
        jsonSerializer = jsonSerializer2;
        if (paramBoolean) {
          this._serializerCache.addTypedSerializer(paramJavaType, jsonSerializer2);
          jsonSerializer = jsonSerializer2;
        } 
      } 
    } 
    return jsonSerializer;
  }
  
  public JsonSerializer findTypedValueSerializer(Class paramClass, boolean paramBoolean, BeanProperty paramBeanProperty) {
    JsonSerializer jsonSerializer = this._knownSerializers.typedValueSerializer(paramClass);
    if (jsonSerializer == null) {
      JsonSerializer jsonSerializer1 = this._serializerCache.typedValueSerializer(paramClass);
      jsonSerializer = jsonSerializer1;
      if (jsonSerializer1 == null) {
        JsonSerializer jsonSerializer2;
        jsonSerializer = findValueSerializer(paramClass, paramBeanProperty);
        TypeSerializer typeSerializer = this._serializerFactory.createTypeSerializer(this._config, this._config.constructType(paramClass), paramBeanProperty);
        if (typeSerializer != null) {
          jsonSerializer2 = new StdSerializerProvider$WrappedSerializer(typeSerializer, jsonSerializer);
        } else {
          jsonSerializer2 = jsonSerializer;
        } 
        jsonSerializer = jsonSerializer2;
        if (paramBoolean) {
          this._serializerCache.addTypedSerializer(paramClass, jsonSerializer2);
          jsonSerializer = jsonSerializer2;
        } 
      } 
    } 
    return jsonSerializer;
  }
  
  public JsonSerializer findValueSerializer(JavaType paramJavaType, BeanProperty paramBeanProperty) {
    JsonSerializer jsonSerializer2 = this._knownSerializers.untypedValueSerializer(paramJavaType);
    JsonSerializer jsonSerializer1 = jsonSerializer2;
    if (jsonSerializer2 == null) {
      jsonSerializer2 = this._serializerCache.untypedValueSerializer(paramJavaType);
      jsonSerializer1 = jsonSerializer2;
      if (jsonSerializer2 == null) {
        jsonSerializer2 = _createAndCacheUntypedSerializer(paramJavaType, paramBeanProperty);
        jsonSerializer1 = jsonSerializer2;
        if (jsonSerializer2 == null)
          return getUnknownTypeSerializer(paramJavaType.getRawClass()); 
      } 
    } 
    return _handleContextualResolvable(jsonSerializer1, paramBeanProperty);
  }
  
  public JsonSerializer findValueSerializer(Class paramClass, BeanProperty paramBeanProperty) {
    JsonSerializer jsonSerializer2 = this._knownSerializers.untypedValueSerializer(paramClass);
    JsonSerializer jsonSerializer1 = jsonSerializer2;
    if (jsonSerializer2 == null) {
      jsonSerializer2 = this._serializerCache.untypedValueSerializer(paramClass);
      jsonSerializer1 = jsonSerializer2;
      if (jsonSerializer2 == null) {
        jsonSerializer2 = this._serializerCache.untypedValueSerializer(this._config.constructType(paramClass));
        jsonSerializer1 = jsonSerializer2;
        if (jsonSerializer2 == null) {
          jsonSerializer2 = _createAndCacheUntypedSerializer(paramClass, paramBeanProperty);
          jsonSerializer1 = jsonSerializer2;
          if (jsonSerializer2 == null)
            return getUnknownTypeSerializer(paramClass); 
        } 
      } 
    } 
    return _handleContextualResolvable(jsonSerializer1, paramBeanProperty);
  }
  
  public void flushCachedSerializers() {
    this._serializerCache.flush();
  }
  
  public JsonSchema generateJsonSchema(Class paramClass, SerializationConfig paramSerializationConfig, SerializerFactory paramSerializerFactory) {
    JsonNode jsonNode;
    if (paramClass == null)
      throw new IllegalArgumentException("A class must be provided"); 
    StdSerializerProvider stdSerializerProvider = createInstance(paramSerializationConfig, paramSerializerFactory);
    if (stdSerializerProvider.getClass() != getClass())
      throw new IllegalStateException("Broken serializer provider: createInstance returned instance of type " + stdSerializerProvider.getClass() + "; blueprint of type " + getClass()); 
    JsonSerializer jsonSerializer = stdSerializerProvider.findValueSerializer(paramClass, (BeanProperty)null);
    if (jsonSerializer instanceof SchemaAware) {
      jsonNode = ((SchemaAware)jsonSerializer).getSchema(stdSerializerProvider, null);
    } else {
      jsonNode = JsonSchema.getDefaultSchemaNode();
    } 
    if (!(jsonNode instanceof ObjectNode))
      throw new IllegalArgumentException("Class " + paramClass.getName() + " would not be serialized as a JSON object and therefore has no schema"); 
    return new JsonSchema((ObjectNode)jsonNode);
  }
  
  public JsonSerializer getNullKeySerializer() {
    return this._nullKeySerializer;
  }
  
  public JsonSerializer getNullValueSerializer() {
    return this._nullValueSerializer;
  }
  
  public JsonSerializer getUnknownTypeSerializer(Class paramClass) {
    return this._unknownTypeSerializer;
  }
  
  public boolean hasSerializerFor(SerializationConfig paramSerializationConfig, Class paramClass, SerializerFactory paramSerializerFactory) {
    return (createInstance(paramSerializationConfig, paramSerializerFactory)._findExplicitUntypedSerializer(paramClass, (BeanProperty)null) != null);
  }
  
  public final void serializeValue(SerializationConfig paramSerializationConfig, JsonGenerator paramJsonGenerator, Object paramObject, SerializerFactory paramSerializerFactory) {
    if (paramSerializerFactory == null)
      throw new IllegalArgumentException("Can not pass null serializerFactory"); 
    StdSerializerProvider stdSerializerProvider = createInstance(paramSerializationConfig, paramSerializerFactory);
    if (stdSerializerProvider.getClass() != getClass())
      throw new IllegalStateException("Broken serializer provider: createInstance returned instance of type " + stdSerializerProvider.getClass() + "; blueprint of type " + getClass()); 
    stdSerializerProvider._serializeValue(paramJsonGenerator, paramObject);
  }
  
  public final void serializeValue(SerializationConfig paramSerializationConfig, JsonGenerator paramJsonGenerator, Object paramObject, JavaType paramJavaType, SerializerFactory paramSerializerFactory) {
    if (paramSerializerFactory == null)
      throw new IllegalArgumentException("Can not pass null serializerFactory"); 
    StdSerializerProvider stdSerializerProvider = createInstance(paramSerializationConfig, paramSerializerFactory);
    if (stdSerializerProvider.getClass() != getClass())
      throw new IllegalStateException("Broken serializer provider: createInstance returned instance of type " + stdSerializerProvider.getClass() + "; blueprint of type " + getClass()); 
    stdSerializerProvider._serializeValue(paramJsonGenerator, paramObject, paramJavaType);
  }
  
  public void setDefaultKeySerializer(JsonSerializer paramJsonSerializer) {
    if (paramJsonSerializer == null)
      throw new IllegalArgumentException("Can not pass null JsonSerializer"); 
    this._keySerializer = paramJsonSerializer;
  }
  
  public void setNullKeySerializer(JsonSerializer paramJsonSerializer) {
    if (paramJsonSerializer == null)
      throw new IllegalArgumentException("Can not pass null JsonSerializer"); 
    this._nullKeySerializer = paramJsonSerializer;
  }
  
  public void setNullValueSerializer(JsonSerializer paramJsonSerializer) {
    if (paramJsonSerializer == null)
      throw new IllegalArgumentException("Can not pass null JsonSerializer"); 
    this._nullValueSerializer = paramJsonSerializer;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\StdSerializerProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */