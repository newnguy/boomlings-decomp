package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.io.SerializedString;
import com.flurry.org.codehaus.jackson.map.AbstractTypeResolver;
import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.ContextualDeserializer;
import com.flurry.org.codehaus.jackson.map.ContextualKeyDeserializer;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializerFactory;
import com.flurry.org.codehaus.jackson.map.DeserializerProvider;
import com.flurry.org.codehaus.jackson.map.Deserializers;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.KeyDeserializer;
import com.flurry.org.codehaus.jackson.map.KeyDeserializers;
import com.flurry.org.codehaus.jackson.map.MapperConfig;
import com.flurry.org.codehaus.jackson.map.ResolvableDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedClass;
import com.flurry.org.codehaus.jackson.map.type.ArrayType;
import com.flurry.org.codehaus.jackson.map.type.CollectionLikeType;
import com.flurry.org.codehaus.jackson.map.type.CollectionType;
import com.flurry.org.codehaus.jackson.map.type.MapLikeType;
import com.flurry.org.codehaus.jackson.map.type.MapType;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import com.flurry.org.codehaus.jackson.map.util.RootNameLookup;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class StdDeserializerProvider extends DeserializerProvider {
  protected final ConcurrentHashMap _cachedDeserializers = new ConcurrentHashMap<Object, Object>(64, 0.75F, 2);
  
  protected DeserializerFactory _factory;
  
  protected final HashMap _incompleteDeserializers = new HashMap<Object, Object>(8);
  
  protected final RootNameLookup _rootNames;
  
  public StdDeserializerProvider() {
    this(BeanDeserializerFactory.instance);
  }
  
  public StdDeserializerProvider(DeserializerFactory paramDeserializerFactory) {
    this._factory = paramDeserializerFactory;
    this._rootNames = new RootNameLookup();
  }
  
  protected JsonDeserializer _createAndCache2(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, BeanProperty paramBeanProperty) {
    JsonDeserializer jsonDeserializer2;
    boolean bool1;
    try {
      jsonDeserializer2 = _createDeserializer(paramDeserializationConfig, paramJavaType, paramBeanProperty);
      if (jsonDeserializer2 == null)
        return null; 
    } catch (IllegalArgumentException illegalArgumentException) {
      throw new JsonMappingException(illegalArgumentException.getMessage(), null, illegalArgumentException);
    } 
    boolean bool3 = jsonDeserializer2 instanceof ResolvableDeserializer;
    if (jsonDeserializer2.getClass() == BeanDeserializer.class) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    boolean bool2 = bool1;
    if (!bool1) {
      bool2 = bool1;
      if (illegalArgumentException.isEnabled(DeserializationConfig.Feature.USE_ANNOTATIONS)) {
        AnnotationIntrospector annotationIntrospector = illegalArgumentException.getAnnotationIntrospector();
        Boolean bool = annotationIntrospector.findCachability(AnnotatedClass.construct(jsonDeserializer2.getClass(), annotationIntrospector, null));
        bool2 = bool1;
        if (bool != null)
          bool2 = bool.booleanValue(); 
      } 
    } 
    if (bool3) {
      this._incompleteDeserializers.put(paramJavaType, jsonDeserializer2);
      _resolveDeserializer((DeserializationConfig)illegalArgumentException, (ResolvableDeserializer)jsonDeserializer2);
      this._incompleteDeserializers.remove(paramJavaType);
    } 
    JsonDeserializer jsonDeserializer1 = jsonDeserializer2;
    if (bool2) {
      this._cachedDeserializers.put(paramJavaType, jsonDeserializer2);
      jsonDeserializer1 = jsonDeserializer2;
    } 
    return jsonDeserializer1;
  }
  
  protected JsonDeserializer _createAndCacheValueDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, BeanProperty paramBeanProperty) {
    int i;
    synchronized (this._incompleteDeserializers) {
      JsonDeserializer jsonDeserializer = _findCachedDeserializer(paramJavaType);
      if (jsonDeserializer != null)
        return jsonDeserializer; 
      i = this._incompleteDeserializers.size();
      if (i > 0) {
        jsonDeserializer = (JsonDeserializer)this._incompleteDeserializers.get(paramJavaType);
        if (jsonDeserializer != null)
          return jsonDeserializer; 
      } 
    } 
    try {
      JsonDeserializer jsonDeserializer = _createAndCache2(paramDeserializationConfig, paramJavaType, paramBeanProperty);
      if (i == 0 && this._incompleteDeserializers.size() > 0)
        this._incompleteDeserializers.clear(); 
      /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=SYNTHETIC_LOCAL_VARIABLE_6} */
    } finally {}
    return (JsonDeserializer)paramDeserializationConfig;
  }
  
  protected JsonDeserializer _createDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, BeanProperty paramBeanProperty) {
    CollectionLikeType collectionLikeType;
    if (paramJavaType.isEnumType())
      return this._factory.createEnumDeserializer(paramDeserializationConfig, this, paramJavaType, paramBeanProperty); 
    if (paramJavaType.isContainerType()) {
      MapLikeType mapLikeType;
      if (paramJavaType.isArrayType())
        return this._factory.createArrayDeserializer(paramDeserializationConfig, this, (ArrayType)paramJavaType, paramBeanProperty); 
      if (paramJavaType.isMapLikeType()) {
        mapLikeType = (MapLikeType)paramJavaType;
        return mapLikeType.isTrueMapType() ? this._factory.createMapDeserializer(paramDeserializationConfig, this, (MapType)mapLikeType, paramBeanProperty) : this._factory.createMapLikeDeserializer(paramDeserializationConfig, this, mapLikeType, paramBeanProperty);
      } 
      if (mapLikeType.isCollectionLikeType()) {
        collectionLikeType = (CollectionLikeType)mapLikeType;
        return collectionLikeType.isTrueCollectionType() ? this._factory.createCollectionDeserializer(paramDeserializationConfig, this, (CollectionType)collectionLikeType, paramBeanProperty) : this._factory.createCollectionLikeDeserializer(paramDeserializationConfig, this, collectionLikeType, paramBeanProperty);
      } 
    } 
    return JsonNode.class.isAssignableFrom(collectionLikeType.getRawClass()) ? this._factory.createTreeDeserializer(paramDeserializationConfig, this, (JavaType)collectionLikeType, paramBeanProperty) : this._factory.createBeanDeserializer(paramDeserializationConfig, this, (JavaType)collectionLikeType, paramBeanProperty);
  }
  
  protected JsonDeserializer _findCachedDeserializer(JavaType paramJavaType) {
    if (paramJavaType == null)
      throw new IllegalArgumentException(); 
    return (JsonDeserializer)this._cachedDeserializers.get(paramJavaType);
  }
  
  protected KeyDeserializer _handleUnknownKeyDeserializer(JavaType paramJavaType) {
    throw new JsonMappingException("Can not find a (Map) Key deserializer for type " + paramJavaType);
  }
  
  protected JsonDeserializer _handleUnknownValueDeserializer(JavaType paramJavaType) {
    if (!ClassUtil.isConcrete(paramJavaType.getRawClass()))
      throw new JsonMappingException("Can not find a Value deserializer for abstract type " + paramJavaType); 
    throw new JsonMappingException("Can not find a Value deserializer for type " + paramJavaType);
  }
  
  protected void _resolveDeserializer(DeserializationConfig paramDeserializationConfig, ResolvableDeserializer paramResolvableDeserializer) {
    paramResolvableDeserializer.resolve(paramDeserializationConfig, this);
  }
  
  public int cachedDeserializersCount() {
    return this._cachedDeserializers.size();
  }
  
  public SerializedString findExpectedRootName(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType) {
    return this._rootNames.findRootName(paramJavaType, (MapperConfig)paramDeserializationConfig);
  }
  
  public KeyDeserializer findKeyDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, BeanProperty paramBeanProperty) {
    KeyDeserializer keyDeserializer3 = this._factory.createKeyDeserializer(paramDeserializationConfig, paramJavaType, paramBeanProperty);
    KeyDeserializer keyDeserializer2 = keyDeserializer3;
    if (keyDeserializer3 instanceof ContextualKeyDeserializer)
      keyDeserializer2 = ((ContextualKeyDeserializer)keyDeserializer3).createContextual(paramDeserializationConfig, paramBeanProperty); 
    KeyDeserializer keyDeserializer1 = keyDeserializer2;
    if (keyDeserializer2 == null)
      keyDeserializer1 = _handleUnknownKeyDeserializer(paramJavaType); 
    return keyDeserializer1;
  }
  
  public JsonDeserializer findTypedValueDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, BeanProperty paramBeanProperty) {
    JsonDeserializer jsonDeserializer = findValueDeserializer(paramDeserializationConfig, paramJavaType, paramBeanProperty);
    TypeDeserializer typeDeserializer = this._factory.findTypeDeserializer(paramDeserializationConfig, paramJavaType, paramBeanProperty);
    return (typeDeserializer != null) ? new StdDeserializerProvider$WrappedDeserializer(typeDeserializer, jsonDeserializer) : jsonDeserializer;
  }
  
  public JsonDeserializer findValueDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, BeanProperty paramBeanProperty) {
    JsonDeserializer jsonDeserializer2 = _findCachedDeserializer(paramJavaType);
    if (jsonDeserializer2 != null) {
      jsonDeserializer1 = jsonDeserializer2;
      if (jsonDeserializer2 instanceof ContextualDeserializer)
        jsonDeserializer1 = ((ContextualDeserializer)jsonDeserializer2).createContextual(paramDeserializationConfig, paramBeanProperty); 
      return jsonDeserializer1;
    } 
    JsonDeserializer jsonDeserializer3 = _createAndCacheValueDeserializer(paramDeserializationConfig, (JavaType)jsonDeserializer1, paramBeanProperty);
    jsonDeserializer2 = jsonDeserializer3;
    if (jsonDeserializer3 == null)
      jsonDeserializer2 = _handleUnknownValueDeserializer((JavaType)jsonDeserializer1); 
    JsonDeserializer jsonDeserializer1 = jsonDeserializer2;
    if (jsonDeserializer2 instanceof ContextualDeserializer)
      jsonDeserializer1 = ((ContextualDeserializer)jsonDeserializer2).createContextual(paramDeserializationConfig, paramBeanProperty); 
    return jsonDeserializer1;
  }
  
  public void flushCachedDeserializers() {
    this._cachedDeserializers.clear();
  }
  
  public boolean hasValueDeserializerFor(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType) {
    boolean bool = false;
    JsonDeserializer jsonDeserializer2 = _findCachedDeserializer(paramJavaType);
    JsonDeserializer jsonDeserializer1 = jsonDeserializer2;
    if (jsonDeserializer2 == null) {
      try {
        jsonDeserializer1 = _createAndCacheValueDeserializer(paramDeserializationConfig, paramJavaType, null);
        if (jsonDeserializer1 != null)
          bool = true; 
      } catch (Exception exception) {}
      return bool;
    } 
    if (jsonDeserializer1 != null)
      bool = true; 
  }
  
  public JavaType mapAbstractType(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType) {
    return this._factory.mapAbstractType(paramDeserializationConfig, paramJavaType);
  }
  
  public DeserializerProvider withAbstractTypeResolver(AbstractTypeResolver paramAbstractTypeResolver) {
    return withFactory(this._factory.withAbstractTypeResolver(paramAbstractTypeResolver));
  }
  
  public DeserializerProvider withAdditionalDeserializers(Deserializers paramDeserializers) {
    return withFactory(this._factory.withAdditionalDeserializers(paramDeserializers));
  }
  
  public DeserializerProvider withAdditionalKeyDeserializers(KeyDeserializers paramKeyDeserializers) {
    return withFactory(this._factory.withAdditionalKeyDeserializers(paramKeyDeserializers));
  }
  
  public DeserializerProvider withDeserializerModifier(BeanDeserializerModifier paramBeanDeserializerModifier) {
    return withFactory(this._factory.withDeserializerModifier(paramBeanDeserializerModifier));
  }
  
  public StdDeserializerProvider withFactory(DeserializerFactory paramDeserializerFactory) {
    if (getClass() != StdDeserializerProvider.class)
      throw new IllegalStateException("DeserializerProvider of type " + getClass().getName() + " does not override 'withFactory()' method"); 
    return new StdDeserializerProvider(paramDeserializerFactory);
  }
  
  public DeserializerProvider withValueInstantiators(ValueInstantiators paramValueInstantiators) {
    return withFactory(this._factory.withValueInstantiators(paramValueInstantiators));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\StdDeserializerProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */