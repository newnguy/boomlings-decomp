package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.io.SerializedString;
import com.flurry.org.codehaus.jackson.map.AbstractTypeResolver;
import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.ContextualDeserializer;
import com.flurry.org.codehaus.jackson.map.ContextualKeyDeserializer;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.DeserializerFactory;
import com.flurry.org.codehaus.jackson.map.DeserializerProvider;
import com.flurry.org.codehaus.jackson.map.Deserializers;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.KeyDeserializer;
import com.flurry.org.codehaus.jackson.map.KeyDeserializers;
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

/* loaded from: classes.dex */
public class StdDeserializerProvider extends DeserializerProvider {
    protected final ConcurrentHashMap _cachedDeserializers;
    protected DeserializerFactory _factory;
    protected final HashMap _incompleteDeserializers;
    protected final RootNameLookup _rootNames;

    /* loaded from: classes.dex */
    public final class WrappedDeserializer extends JsonDeserializer {
        final JsonDeserializer _deserializer;
        final TypeDeserializer _typeDeserializer;

        public WrappedDeserializer(TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer) {
            this._typeDeserializer = typeDeserializer;
            this._deserializer = jsonDeserializer;
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
            return this._deserializer.deserializeWithType(jsonParser, deserializationContext, this._typeDeserializer);
        }

        @Override // com.flurry.org.codehaus.jackson.map.JsonDeserializer
        public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) {
            throw new IllegalStateException("Type-wrapped deserializer's deserializeWithType should never get called");
        }
    }

    public StdDeserializerProvider() {
        this(BeanDeserializerFactory.instance);
    }

    public StdDeserializerProvider(DeserializerFactory deserializerFactory) {
        this._cachedDeserializers = new ConcurrentHashMap(64, 0.75f, 2);
        this._incompleteDeserializers = new HashMap(8);
        this._factory = deserializerFactory;
        this._rootNames = new RootNameLookup();
    }

    protected JsonDeserializer _createAndCache2(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) {
        try {
            JsonDeserializer _createDeserializer = _createDeserializer(deserializationConfig, javaType, beanProperty);
            if (_createDeserializer == null) {
                return null;
            }
            boolean z = _createDeserializer instanceof ResolvableDeserializer;
            boolean z2 = _createDeserializer.getClass() == BeanDeserializer.class;
            if (!z2 && deserializationConfig.isEnabled(DeserializationConfig.Feature.USE_ANNOTATIONS)) {
                AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
                Boolean findCachability = annotationIntrospector.findCachability(AnnotatedClass.construct(_createDeserializer.getClass(), annotationIntrospector, null));
                if (findCachability != null) {
                    z2 = findCachability.booleanValue();
                }
            }
            if (z) {
                this._incompleteDeserializers.put(javaType, _createDeserializer);
                _resolveDeserializer(deserializationConfig, (ResolvableDeserializer) _createDeserializer);
                this._incompleteDeserializers.remove(javaType);
            }
            if (z2) {
                this._cachedDeserializers.put(javaType, _createDeserializer);
                return _createDeserializer;
            }
            return _createDeserializer;
        } catch (IllegalArgumentException e) {
            throw new JsonMappingException(e.getMessage(), null, e);
        }
    }

    protected JsonDeserializer _createAndCacheValueDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) {
        JsonDeserializer _findCachedDeserializer;
        synchronized (this._incompleteDeserializers) {
            _findCachedDeserializer = _findCachedDeserializer(javaType);
            if (_findCachedDeserializer == null) {
                int size = this._incompleteDeserializers.size();
                if (size <= 0 || (_findCachedDeserializer = (JsonDeserializer) this._incompleteDeserializers.get(javaType)) == null) {
                    _findCachedDeserializer = _createAndCache2(deserializationConfig, javaType, beanProperty);
                    if (size == 0 && this._incompleteDeserializers.size() > 0) {
                        this._incompleteDeserializers.clear();
                    }
                }
            }
        }
        return _findCachedDeserializer;
    }

    protected JsonDeserializer _createDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) {
        if (javaType.isEnumType()) {
            return this._factory.createEnumDeserializer(deserializationConfig, this, javaType, beanProperty);
        }
        if (javaType.isContainerType()) {
            if (javaType.isArrayType()) {
                return this._factory.createArrayDeserializer(deserializationConfig, this, (ArrayType) javaType, beanProperty);
            }
            if (javaType.isMapLikeType()) {
                MapLikeType mapLikeType = (MapLikeType) javaType;
                return mapLikeType.isTrueMapType() ? this._factory.createMapDeserializer(deserializationConfig, this, (MapType) mapLikeType, beanProperty) : this._factory.createMapLikeDeserializer(deserializationConfig, this, mapLikeType, beanProperty);
            } else if (javaType.isCollectionLikeType()) {
                CollectionLikeType collectionLikeType = (CollectionLikeType) javaType;
                return collectionLikeType.isTrueCollectionType() ? this._factory.createCollectionDeserializer(deserializationConfig, this, (CollectionType) collectionLikeType, beanProperty) : this._factory.createCollectionLikeDeserializer(deserializationConfig, this, collectionLikeType, beanProperty);
            }
        }
        return JsonNode.class.isAssignableFrom(javaType.getRawClass()) ? this._factory.createTreeDeserializer(deserializationConfig, this, javaType, beanProperty) : this._factory.createBeanDeserializer(deserializationConfig, this, javaType, beanProperty);
    }

    protected JsonDeserializer _findCachedDeserializer(JavaType javaType) {
        if (javaType == null) {
            throw new IllegalArgumentException();
        }
        return (JsonDeserializer) this._cachedDeserializers.get(javaType);
    }

    protected KeyDeserializer _handleUnknownKeyDeserializer(JavaType javaType) {
        throw new JsonMappingException("Can not find a (Map) Key deserializer for type " + javaType);
    }

    protected JsonDeserializer _handleUnknownValueDeserializer(JavaType javaType) {
        if (ClassUtil.isConcrete(javaType.getRawClass())) {
            throw new JsonMappingException("Can not find a Value deserializer for type " + javaType);
        }
        throw new JsonMappingException("Can not find a Value deserializer for abstract type " + javaType);
    }

    protected void _resolveDeserializer(DeserializationConfig deserializationConfig, ResolvableDeserializer resolvableDeserializer) {
        resolvableDeserializer.resolve(deserializationConfig, this);
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerProvider
    public int cachedDeserializersCount() {
        return this._cachedDeserializers.size();
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerProvider
    public SerializedString findExpectedRootName(DeserializationConfig deserializationConfig, JavaType javaType) {
        return this._rootNames.findRootName(javaType, deserializationConfig);
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerProvider
    public KeyDeserializer findKeyDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) {
        KeyDeserializer createKeyDeserializer = this._factory.createKeyDeserializer(deserializationConfig, javaType, beanProperty);
        if (createKeyDeserializer instanceof ContextualKeyDeserializer) {
            createKeyDeserializer = ((ContextualKeyDeserializer) createKeyDeserializer).createContextual(deserializationConfig, beanProperty);
        }
        return createKeyDeserializer == null ? _handleUnknownKeyDeserializer(javaType) : createKeyDeserializer;
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerProvider
    public JsonDeserializer findTypedValueDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) {
        JsonDeserializer findValueDeserializer = findValueDeserializer(deserializationConfig, javaType, beanProperty);
        TypeDeserializer findTypeDeserializer = this._factory.findTypeDeserializer(deserializationConfig, javaType, beanProperty);
        return findTypeDeserializer != null ? new WrappedDeserializer(findTypeDeserializer, findValueDeserializer) : findValueDeserializer;
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerProvider
    public JsonDeserializer findValueDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) {
        JsonDeserializer _findCachedDeserializer = _findCachedDeserializer(javaType);
        if (_findCachedDeserializer != null) {
            return _findCachedDeserializer instanceof ContextualDeserializer ? ((ContextualDeserializer) _findCachedDeserializer).createContextual(deserializationConfig, beanProperty) : _findCachedDeserializer;
        }
        JsonDeserializer _createAndCacheValueDeserializer = _createAndCacheValueDeserializer(deserializationConfig, javaType, beanProperty);
        if (_createAndCacheValueDeserializer == null) {
            _createAndCacheValueDeserializer = _handleUnknownValueDeserializer(javaType);
        }
        return _createAndCacheValueDeserializer instanceof ContextualDeserializer ? ((ContextualDeserializer) _createAndCacheValueDeserializer).createContextual(deserializationConfig, beanProperty) : _createAndCacheValueDeserializer;
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerProvider
    public void flushCachedDeserializers() {
        this._cachedDeserializers.clear();
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerProvider
    public boolean hasValueDeserializerFor(DeserializationConfig deserializationConfig, JavaType javaType) {
        JsonDeserializer _findCachedDeserializer = _findCachedDeserializer(javaType);
        if (_findCachedDeserializer == null) {
            try {
                _findCachedDeserializer = _createAndCacheValueDeserializer(deserializationConfig, javaType, null);
            } catch (Exception e) {
                return false;
            }
        }
        return _findCachedDeserializer != null;
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerProvider
    public JavaType mapAbstractType(DeserializationConfig deserializationConfig, JavaType javaType) {
        return this._factory.mapAbstractType(deserializationConfig, javaType);
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerProvider
    public DeserializerProvider withAbstractTypeResolver(AbstractTypeResolver abstractTypeResolver) {
        return withFactory(this._factory.withAbstractTypeResolver(abstractTypeResolver));
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerProvider
    public DeserializerProvider withAdditionalDeserializers(Deserializers deserializers) {
        return withFactory(this._factory.withAdditionalDeserializers(deserializers));
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerProvider
    public DeserializerProvider withAdditionalKeyDeserializers(KeyDeserializers keyDeserializers) {
        return withFactory(this._factory.withAdditionalKeyDeserializers(keyDeserializers));
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerProvider
    public DeserializerProvider withDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier) {
        return withFactory(this._factory.withDeserializerModifier(beanDeserializerModifier));
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerProvider
    public StdDeserializerProvider withFactory(DeserializerFactory deserializerFactory) {
        if (getClass() != StdDeserializerProvider.class) {
            throw new IllegalStateException("DeserializerProvider of type " + getClass().getName() + " does not override 'withFactory()' method");
        }
        return new StdDeserializerProvider(deserializerFactory);
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerProvider
    public DeserializerProvider withValueInstantiators(ValueInstantiators valueInstantiators) {
        return withFactory(this._factory.withValueInstantiators(valueInstantiators));
    }
}
