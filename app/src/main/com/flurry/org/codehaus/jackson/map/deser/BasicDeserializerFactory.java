package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.ContextualDeserializer;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializerFactory;
import com.flurry.org.codehaus.jackson.map.DeserializerProvider;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.KeyDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.std.AtomicReferenceDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.std.EnumMapDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.std.EnumSetDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.std.ObjectArrayDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.std.PrimitiveArrayDeserializers;
import com.flurry.org.codehaus.jackson.map.deser.std.StringCollectionDeserializer;
import com.flurry.org.codehaus.jackson.map.ext.OptionalHandlerFactory;
import com.flurry.org.codehaus.jackson.map.introspect.Annotated;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedClass;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.flurry.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import com.flurry.org.codehaus.jackson.map.type.ArrayType;
import com.flurry.org.codehaus.jackson.map.type.ClassKey;
import com.flurry.org.codehaus.jackson.map.type.CollectionLikeType;
import com.flurry.org.codehaus.jackson.map.type.CollectionType;
import com.flurry.org.codehaus.jackson.map.type.MapLikeType;
import com.flurry.org.codehaus.jackson.map.type.MapType;
import com.flurry.org.codehaus.jackson.map.type.TypeFactory;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public abstract class BasicDeserializerFactory extends DeserializerFactory {
    protected static final HashMap _arrayDeserializers;
    static final HashMap _collectionFallbacks;
    protected OptionalHandlerFactory optionalHandlers = OptionalHandlerFactory.instance;
    static final HashMap _simpleDeserializers = StdDeserializers.constructAll();
    static final HashMap _keyDeserializers = com.flurry.org.codehaus.jackson.map.deser.std.StdKeyDeserializers.constructAll();
    static final HashMap _mapFallbacks = new HashMap();

    static {
        _mapFallbacks.put(Map.class.getName(), LinkedHashMap.class);
        _mapFallbacks.put(ConcurrentMap.class.getName(), ConcurrentHashMap.class);
        _mapFallbacks.put(SortedMap.class.getName(), TreeMap.class);
        _mapFallbacks.put("java.util.NavigableMap", TreeMap.class);
        try {
            _mapFallbacks.put(Class.forName("java.util.ConcurrentNavigableMap").getName(), Class.forName("java.util.ConcurrentSkipListMap"));
        } catch (ClassNotFoundException e) {
        }
        _collectionFallbacks = new HashMap();
        _collectionFallbacks.put(Collection.class.getName(), ArrayList.class);
        _collectionFallbacks.put(List.class.getName(), ArrayList.class);
        _collectionFallbacks.put(Set.class.getName(), HashSet.class);
        _collectionFallbacks.put(SortedSet.class.getName(), TreeSet.class);
        _collectionFallbacks.put(Queue.class.getName(), LinkedList.class);
        _collectionFallbacks.put("java.util.Deque", LinkedList.class);
        _collectionFallbacks.put("java.util.NavigableSet", TreeSet.class);
        _arrayDeserializers = PrimitiveArrayDeserializers.getAll();
    }

    JsonDeserializer _constructDeserializer(DeserializationConfig deserializationConfig, Annotated annotated, BeanProperty beanProperty, Object obj) {
        if (obj instanceof JsonDeserializer) {
            JsonDeserializer jsonDeserializer = (JsonDeserializer) obj;
            return jsonDeserializer instanceof ContextualDeserializer ? ((ContextualDeserializer) jsonDeserializer).createContextual(deserializationConfig, beanProperty) : jsonDeserializer;
        } else if (obj instanceof Class) {
            Class cls = (Class) obj;
            if (JsonDeserializer.class.isAssignableFrom(cls)) {
                JsonDeserializer deserializerInstance = deserializationConfig.deserializerInstance(annotated, cls);
                if (deserializerInstance instanceof ContextualDeserializer) {
                    deserializerInstance = ((ContextualDeserializer) deserializerInstance).createContextual(deserializationConfig, beanProperty);
                }
                return deserializerInstance;
            }
            throw new IllegalStateException("AnnotationIntrospector returned Class " + cls.getName() + "; expected Class<JsonDeserializer>");
        } else {
            throw new IllegalStateException("AnnotationIntrospector returned deserializer definition of type " + obj.getClass().getName() + "; expected type JsonDeserializer or Class<JsonDeserializer> instead");
        }
    }

    protected abstract JsonDeserializer _findCustomArrayDeserializer(ArrayType arrayType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer);

    protected abstract JsonDeserializer _findCustomCollectionDeserializer(CollectionType collectionType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer);

    protected abstract JsonDeserializer _findCustomCollectionLikeDeserializer(CollectionLikeType collectionLikeType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer);

    protected abstract JsonDeserializer _findCustomEnumDeserializer(Class cls, DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty);

    protected abstract JsonDeserializer _findCustomMapDeserializer(MapType mapType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer);

    protected abstract JsonDeserializer _findCustomMapLikeDeserializer(MapLikeType mapLikeType, DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, BasicBeanDescription basicBeanDescription, BeanProperty beanProperty, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer jsonDeserializer);

    protected abstract JsonDeserializer _findCustomTreeNodeDeserializer(Class cls, DeserializationConfig deserializationConfig, BeanProperty beanProperty);

    /* JADX INFO: Access modifiers changed from: protected */
    public com.flurry.org.codehaus.jackson.map.util.EnumResolver constructEnumResolver(Class cls, DeserializationConfig deserializationConfig) {
        return deserializationConfig.isEnabled(DeserializationConfig.Feature.READ_ENUMS_USING_TO_STRING) ? com.flurry.org.codehaus.jackson.map.util.EnumResolver.constructUnsafeUsingToString(cls) : com.flurry.org.codehaus.jackson.map.util.EnumResolver.constructUnsafe(cls, deserializationConfig.getAnnotationIntrospector());
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer createArrayDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, ArrayType arrayType, BeanProperty beanProperty) {
        JavaType contentType = arrayType.getContentType();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        if (jsonDeserializer == null) {
            JsonDeserializer jsonDeserializer2 = (JsonDeserializer) _arrayDeserializers.get(contentType);
            if (jsonDeserializer2 != null) {
                JsonDeserializer _findCustomArrayDeserializer = _findCustomArrayDeserializer(arrayType, deserializationConfig, deserializerProvider, beanProperty, null, null);
                return _findCustomArrayDeserializer != null ? _findCustomArrayDeserializer : jsonDeserializer2;
            } else if (contentType.isPrimitive()) {
                throw new IllegalArgumentException("Internal error: primitive type (" + arrayType + ") passed, no array deserializer found");
            }
        }
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        TypeDeserializer findTypeDeserializer = typeDeserializer == null ? findTypeDeserializer(deserializationConfig, contentType, beanProperty) : typeDeserializer;
        JsonDeserializer _findCustomArrayDeserializer2 = _findCustomArrayDeserializer(arrayType, deserializationConfig, deserializerProvider, beanProperty, findTypeDeserializer, jsonDeserializer);
        if (_findCustomArrayDeserializer2 == null) {
            if (jsonDeserializer == null) {
                jsonDeserializer = deserializerProvider.findValueDeserializer(deserializationConfig, contentType, beanProperty);
            }
            return new ObjectArrayDeserializer(arrayType, jsonDeserializer, findTypeDeserializer);
        }
        return _findCustomArrayDeserializer2;
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer createCollectionDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, CollectionType collectionType, BeanProperty beanProperty) {
        CollectionType collectionType2 = (CollectionType) mapAbstractType(deserializationConfig, collectionType);
        Class rawClass = collectionType2.getRawClass();
        BasicBeanDescription basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectForCreation(collectionType2);
        JsonDeserializer findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (findDeserializerFromAnnotation != null) {
            return findDeserializerFromAnnotation;
        }
        CollectionType collectionType3 = (CollectionType) modifyTypeByAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), collectionType2, null);
        JavaType contentType = collectionType3.getContentType();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        TypeDeserializer findTypeDeserializer = typeDeserializer == null ? findTypeDeserializer(deserializationConfig, contentType, beanProperty) : typeDeserializer;
        JsonDeserializer _findCustomCollectionDeserializer = _findCustomCollectionDeserializer(collectionType3, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty, findTypeDeserializer, jsonDeserializer);
        if (_findCustomCollectionDeserializer == null) {
            if (jsonDeserializer == null) {
                if (EnumSet.class.isAssignableFrom(rawClass)) {
                    return new EnumSetDeserializer(contentType.getRawClass(), createEnumDeserializer(deserializationConfig, deserializerProvider, contentType, beanProperty));
                }
                jsonDeserializer = deserializerProvider.findValueDeserializer(deserializationConfig, contentType, beanProperty);
            }
            if (collectionType3.isInterface() || collectionType3.isAbstract()) {
                Class cls = (Class) _collectionFallbacks.get(rawClass.getName());
                if (cls == null) {
                    throw new IllegalArgumentException("Can not find a deserializer for non-concrete Collection type " + collectionType3);
                }
                CollectionType collectionType4 = (CollectionType) deserializationConfig.constructSpecializedType(collectionType3, cls);
                basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectForCreation(collectionType4);
                collectionType3 = collectionType4;
            }
            ValueInstantiator findValueInstantiator = findValueInstantiator(deserializationConfig, basicBeanDescription);
            return contentType.getRawClass() == String.class ? new StringCollectionDeserializer(collectionType3, jsonDeserializer, findValueInstantiator) : new com.flurry.org.codehaus.jackson.map.deser.std.CollectionDeserializer(collectionType3, jsonDeserializer, findTypeDeserializer, findValueInstantiator);
        }
        return _findCustomCollectionDeserializer;
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer createCollectionLikeDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, CollectionLikeType collectionLikeType, BeanProperty beanProperty) {
        CollectionLikeType collectionLikeType2 = (CollectionLikeType) mapAbstractType(deserializationConfig, collectionLikeType);
        BasicBeanDescription basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectClassAnnotations(collectionLikeType2.getRawClass());
        JsonDeserializer findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (findDeserializerFromAnnotation != null) {
            return findDeserializerFromAnnotation;
        }
        CollectionLikeType collectionLikeType3 = (CollectionLikeType) modifyTypeByAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), collectionLikeType2, null);
        JavaType contentType = collectionLikeType3.getContentType();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        return _findCustomCollectionLikeDeserializer(collectionLikeType3, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty, typeDeserializer == null ? findTypeDeserializer(deserializationConfig, contentType, beanProperty) : typeDeserializer, jsonDeserializer);
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer createEnumDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) {
        BasicBeanDescription basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectForCreation(javaType);
        JsonDeserializer findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (findDeserializerFromAnnotation != null) {
            return findDeserializerFromAnnotation;
        }
        Class<?> rawClass = javaType.getRawClass();
        JsonDeserializer _findCustomEnumDeserializer = _findCustomEnumDeserializer(rawClass, deserializationConfig, basicBeanDescription, beanProperty);
        if (_findCustomEnumDeserializer != null) {
            return _findCustomEnumDeserializer;
        }
        for (AnnotatedMethod annotatedMethod : basicBeanDescription.getFactoryMethods()) {
            if (deserializationConfig.getAnnotationIntrospector().hasCreatorAnnotation(annotatedMethod)) {
                if (annotatedMethod.getParameterCount() == 1 && annotatedMethod.getRawType().isAssignableFrom(rawClass)) {
                    return com.flurry.org.codehaus.jackson.map.deser.std.EnumDeserializer.deserializerForCreator(deserializationConfig, rawClass, annotatedMethod);
                }
                throw new IllegalArgumentException("Unsuitable method (" + annotatedMethod + ") decorated with @JsonCreator (for Enum type " + rawClass.getName() + ")");
            }
        }
        return new com.flurry.org.codehaus.jackson.map.deser.std.EnumDeserializer(constructEnumResolver(rawClass, deserializationConfig));
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer createMapDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, MapType mapType, BeanProperty beanProperty) {
        BasicBeanDescription basicBeanDescription;
        MapType mapType2 = (MapType) mapAbstractType(deserializationConfig, mapType);
        BasicBeanDescription basicBeanDescription2 = (BasicBeanDescription) deserializationConfig.introspectForCreation(mapType2);
        JsonDeserializer findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription2.getClassInfo(), beanProperty);
        if (findDeserializerFromAnnotation != null) {
            return findDeserializerFromAnnotation;
        }
        MapType mapType3 = (MapType) modifyTypeByAnnotation(deserializationConfig, basicBeanDescription2.getClassInfo(), mapType2, null);
        JavaType keyType = mapType3.getKeyType();
        JavaType contentType = mapType3.getContentType();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        KeyDeserializer keyDeserializer = (KeyDeserializer) keyType.getValueHandler();
        KeyDeserializer findKeyDeserializer = keyDeserializer == null ? deserializerProvider.findKeyDeserializer(deserializationConfig, keyType, beanProperty) : keyDeserializer;
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        TypeDeserializer findTypeDeserializer = typeDeserializer == null ? findTypeDeserializer(deserializationConfig, contentType, beanProperty) : typeDeserializer;
        JsonDeserializer _findCustomMapDeserializer = _findCustomMapDeserializer(mapType3, deserializationConfig, deserializerProvider, basicBeanDescription2, beanProperty, findKeyDeserializer, findTypeDeserializer, jsonDeserializer);
        if (_findCustomMapDeserializer == null) {
            if (jsonDeserializer == null) {
                jsonDeserializer = deserializerProvider.findValueDeserializer(deserializationConfig, contentType, beanProperty);
            }
            Class rawClass = mapType3.getRawClass();
            if (EnumMap.class.isAssignableFrom(rawClass)) {
                Class rawClass2 = keyType.getRawClass();
                if (rawClass2 == null || !rawClass2.isEnum()) {
                    throw new IllegalArgumentException("Can not construct EnumMap; generic (key) type not available");
                }
                return new EnumMapDeserializer(keyType.getRawClass(), createEnumDeserializer(deserializationConfig, deserializerProvider, keyType, beanProperty), jsonDeserializer);
            }
            if (mapType3.isInterface() || mapType3.isAbstract()) {
                Class cls = (Class) _mapFallbacks.get(rawClass.getName());
                if (cls == null) {
                    throw new IllegalArgumentException("Can not find a deserializer for non-concrete Map type " + mapType3);
                }
                MapType mapType4 = (MapType) deserializationConfig.constructSpecializedType(mapType3, cls);
                basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectForCreation(mapType4);
                mapType3 = mapType4;
            } else {
                basicBeanDescription = basicBeanDescription2;
            }
            com.flurry.org.codehaus.jackson.map.deser.std.MapDeserializer mapDeserializer = new com.flurry.org.codehaus.jackson.map.deser.std.MapDeserializer(mapType3, findValueInstantiator(deserializationConfig, basicBeanDescription), findKeyDeserializer, jsonDeserializer, findTypeDeserializer);
            mapDeserializer.setIgnorableProperties(deserializationConfig.getAnnotationIntrospector().findPropertiesToIgnore(basicBeanDescription.getClassInfo()));
            return mapDeserializer;
        }
        return _findCustomMapDeserializer;
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer createMapLikeDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, MapLikeType mapLikeType, BeanProperty beanProperty) {
        MapLikeType mapLikeType2 = (MapLikeType) mapAbstractType(deserializationConfig, mapLikeType);
        BasicBeanDescription basicBeanDescription = (BasicBeanDescription) deserializationConfig.introspectForCreation(mapLikeType2);
        JsonDeserializer findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), beanProperty);
        if (findDeserializerFromAnnotation != null) {
            return findDeserializerFromAnnotation;
        }
        MapLikeType mapLikeType3 = (MapLikeType) modifyTypeByAnnotation(deserializationConfig, basicBeanDescription.getClassInfo(), mapLikeType2, null);
        JavaType keyType = mapLikeType3.getKeyType();
        JavaType contentType = mapLikeType3.getContentType();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        KeyDeserializer keyDeserializer = (KeyDeserializer) keyType.getValueHandler();
        KeyDeserializer findKeyDeserializer = keyDeserializer == null ? deserializerProvider.findKeyDeserializer(deserializationConfig, keyType, beanProperty) : keyDeserializer;
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        return _findCustomMapLikeDeserializer(mapLikeType3, deserializationConfig, deserializerProvider, basicBeanDescription, beanProperty, findKeyDeserializer, typeDeserializer == null ? findTypeDeserializer(deserializationConfig, contentType, beanProperty) : typeDeserializer, jsonDeserializer);
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory
    public JsonDeserializer createTreeDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) {
        Class rawClass = javaType.getRawClass();
        JsonDeserializer _findCustomTreeNodeDeserializer = _findCustomTreeNodeDeserializer(rawClass, deserializationConfig, beanProperty);
        return _findCustomTreeNodeDeserializer != null ? _findCustomTreeNodeDeserializer : com.flurry.org.codehaus.jackson.map.deser.std.JsonNodeDeserializer.getDeserializer(rawClass);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonDeserializer findDeserializerFromAnnotation(DeserializationConfig deserializationConfig, Annotated annotated, BeanProperty beanProperty) {
        Object findDeserializer = deserializationConfig.getAnnotationIntrospector().findDeserializer(annotated);
        if (findDeserializer != null) {
            return _constructDeserializer(deserializationConfig, annotated, beanProperty, findDeserializer);
        }
        return null;
    }

    public TypeDeserializer findPropertyContentTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, AnnotatedMember annotatedMember, BeanProperty beanProperty) {
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder findPropertyContentTypeResolver = annotationIntrospector.findPropertyContentTypeResolver(deserializationConfig, annotatedMember, javaType);
        JavaType contentType = javaType.getContentType();
        return findPropertyContentTypeResolver == null ? findTypeDeserializer(deserializationConfig, contentType, beanProperty) : findPropertyContentTypeResolver.buildTypeDeserializer(deserializationConfig, contentType, deserializationConfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember, deserializationConfig, annotationIntrospector), beanProperty);
    }

    public TypeDeserializer findPropertyTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, AnnotatedMember annotatedMember, BeanProperty beanProperty) {
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder findPropertyTypeResolver = annotationIntrospector.findPropertyTypeResolver(deserializationConfig, annotatedMember, javaType);
        return findPropertyTypeResolver == null ? findTypeDeserializer(deserializationConfig, javaType, beanProperty) : findPropertyTypeResolver.buildTypeDeserializer(deserializationConfig, javaType, deserializationConfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember, deserializationConfig, annotationIntrospector), beanProperty);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsonDeserializer findStdBeanDeserializer(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider, JavaType javaType, BeanProperty beanProperty) {
        Class rawClass = javaType.getRawClass();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) _simpleDeserializers.get(new ClassKey(rawClass));
        if (jsonDeserializer != null) {
            return jsonDeserializer;
        }
        if (AtomicReference.class.isAssignableFrom(rawClass)) {
            JavaType[] findTypeParameters = deserializationConfig.getTypeFactory().findTypeParameters(javaType, AtomicReference.class);
            return new AtomicReferenceDeserializer((findTypeParameters == null || findTypeParameters.length < 1) ? TypeFactory.unknownType() : findTypeParameters[0], beanProperty);
        }
        JsonDeserializer findDeserializer = this.optionalHandlers.findDeserializer(javaType, deserializationConfig, deserializerProvider);
        if (findDeserializer == null) {
            return null;
        }
        return findDeserializer;
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory
    public TypeDeserializer findTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) {
        Collection collectAndResolveSubtypes;
        TypeResolverBuilder typeResolverBuilder;
        JavaType mapAbstractType;
        AnnotatedClass classInfo = ((BasicBeanDescription) deserializationConfig.introspectClassAnnotations(javaType.getRawClass())).getClassInfo();
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder findTypeResolver = annotationIntrospector.findTypeResolver(deserializationConfig, classInfo, javaType);
        if (findTypeResolver == null) {
            TypeResolverBuilder defaultTyper = deserializationConfig.getDefaultTyper(javaType);
            if (defaultTyper == null) {
                return null;
            }
            typeResolverBuilder = defaultTyper;
            collectAndResolveSubtypes = null;
        } else {
            collectAndResolveSubtypes = deserializationConfig.getSubtypeResolver().collectAndResolveSubtypes(classInfo, deserializationConfig, annotationIntrospector);
            typeResolverBuilder = findTypeResolver;
        }
        if (typeResolverBuilder.getDefaultImpl() == null && javaType.isAbstract() && (mapAbstractType = mapAbstractType(deserializationConfig, javaType)) != null && mapAbstractType.getRawClass() != javaType.getRawClass()) {
            typeResolverBuilder = typeResolverBuilder.defaultImpl(mapAbstractType.getRawClass());
        }
        return typeResolverBuilder.buildTypeDeserializer(deserializationConfig, javaType, collectAndResolveSubtypes, beanProperty);
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory
    public abstract ValueInstantiator findValueInstantiator(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription);

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory
    public abstract JavaType mapAbstractType(DeserializationConfig deserializationConfig, JavaType javaType);

    /* JADX INFO: Access modifiers changed from: protected */
    public JavaType modifyTypeByAnnotation(DeserializationConfig deserializationConfig, Annotated annotated, JavaType javaType, String str) {
        JavaType narrowBy;
        Class findContentDeserializer;
        Class findKeyDeserializer;
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        Class findDeserializationType = annotationIntrospector.findDeserializationType(annotated, javaType, str);
        if (findDeserializationType != null) {
            try {
                narrowBy = javaType.narrowBy(findDeserializationType);
            } catch (IllegalArgumentException e) {
                throw new JsonMappingException("Failed to narrow type " + javaType + " with concrete-type annotation (value " + findDeserializationType.getName() + "), method '" + annotated.getName() + "': " + e.getMessage(), null, e);
            }
        } else {
            narrowBy = javaType;
        }
        if (narrowBy.isContainerType()) {
            Class findDeserializationKeyType = annotationIntrospector.findDeserializationKeyType(annotated, narrowBy.getKeyType(), str);
            if (findDeserializationKeyType != null) {
                if (!(narrowBy instanceof MapLikeType)) {
                    throw new JsonMappingException("Illegal key-type annotation: type " + narrowBy + " is not a Map(-like) type");
                }
                try {
                    narrowBy = ((MapLikeType) narrowBy).narrowKey(findDeserializationKeyType);
                } catch (IllegalArgumentException e2) {
                    throw new JsonMappingException("Failed to narrow key type " + narrowBy + " with key-type annotation (" + findDeserializationKeyType.getName() + "): " + e2.getMessage(), null, e2);
                }
            }
            JavaType keyType = narrowBy.getKeyType();
            if (keyType != null && keyType.getValueHandler() == null && (findKeyDeserializer = annotationIntrospector.findKeyDeserializer(annotated)) != null && findKeyDeserializer != KeyDeserializer.None.class) {
                keyType.setValueHandler(deserializationConfig.keyDeserializerInstance(annotated, findKeyDeserializer));
            }
            Class findDeserializationContentType = annotationIntrospector.findDeserializationContentType(annotated, narrowBy.getContentType(), str);
            if (findDeserializationContentType != null) {
                try {
                    narrowBy = narrowBy.narrowContentsBy(findDeserializationContentType);
                } catch (IllegalArgumentException e3) {
                    throw new JsonMappingException("Failed to narrow content type " + narrowBy + " with content-type annotation (" + findDeserializationContentType.getName() + "): " + e3.getMessage(), null, e3);
                }
            }
            if (narrowBy.getContentType().getValueHandler() == null && (findContentDeserializer = annotationIntrospector.findContentDeserializer(annotated)) != null && findContentDeserializer != JsonDeserializer.None.class) {
                narrowBy.getContentType().setValueHandler(deserializationConfig.deserializerInstance(annotated, findContentDeserializer));
            }
        }
        return narrowBy;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JavaType resolveType(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, JavaType javaType, AnnotatedMember annotatedMember, BeanProperty beanProperty) {
        TypeDeserializer findPropertyContentTypeDeserializer;
        Class findKeyDeserializer;
        if (javaType.isContainerType()) {
            AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
            JavaType keyType = javaType.getKeyType();
            if (keyType != null && (findKeyDeserializer = annotationIntrospector.findKeyDeserializer(annotatedMember)) != null && findKeyDeserializer != KeyDeserializer.None.class) {
                keyType.setValueHandler(deserializationConfig.keyDeserializerInstance(annotatedMember, findKeyDeserializer));
            }
            Class findContentDeserializer = annotationIntrospector.findContentDeserializer(annotatedMember);
            if (findContentDeserializer != null && findContentDeserializer != JsonDeserializer.None.class) {
                javaType.getContentType().setValueHandler(deserializationConfig.deserializerInstance(annotatedMember, findContentDeserializer));
            }
            if ((annotatedMember instanceof AnnotatedMember) && (findPropertyContentTypeDeserializer = findPropertyContentTypeDeserializer(deserializationConfig, javaType, annotatedMember, beanProperty)) != null) {
                javaType = javaType.withContentTypeHandler(findPropertyContentTypeDeserializer);
            }
        }
        TypeDeserializer findPropertyTypeDeserializer = annotatedMember instanceof AnnotatedMember ? findPropertyTypeDeserializer(deserializationConfig, javaType, annotatedMember, beanProperty) : findTypeDeserializer(deserializationConfig, javaType, null);
        return findPropertyTypeDeserializer != null ? javaType.withTypeHandler(findPropertyTypeDeserializer) : javaType;
    }

    @Override // com.flurry.org.codehaus.jackson.map.DeserializerFactory
    public abstract DeserializerFactory withConfig(DeserializerFactory.Config config);
}
