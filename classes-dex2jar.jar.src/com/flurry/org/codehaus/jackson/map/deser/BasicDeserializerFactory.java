package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.ContextualDeserializer;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializerFactory;
import com.flurry.org.codehaus.jackson.map.DeserializerProvider;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.KeyDeserializer;
import com.flurry.org.codehaus.jackson.map.MapperConfig;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.std.AtomicReferenceDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.std.EnumMapDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.std.JsonNodeDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.std.MapDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.std.ObjectArrayDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.std.PrimitiveArrayDeserializers;
import com.flurry.org.codehaus.jackson.map.deser.std.StdKeyDeserializers;
import com.flurry.org.codehaus.jackson.map.ext.OptionalHandlerFactory;
import com.flurry.org.codehaus.jackson.map.introspect.Annotated;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedClass;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import com.flurry.org.codehaus.jackson.map.type.ArrayType;
import com.flurry.org.codehaus.jackson.map.type.ClassKey;
import com.flurry.org.codehaus.jackson.map.type.CollectionLikeType;
import com.flurry.org.codehaus.jackson.map.type.CollectionType;
import com.flurry.org.codehaus.jackson.map.type.MapLikeType;
import com.flurry.org.codehaus.jackson.map.type.MapType;
import com.flurry.org.codehaus.jackson.map.type.TypeFactory;
import com.flurry.org.codehaus.jackson.map.util.EnumResolver;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
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

public abstract class BasicDeserializerFactory extends DeserializerFactory {
  protected static final HashMap _arrayDeserializers;
  
  static final HashMap _collectionFallbacks;
  
  static final HashMap _keyDeserializers;
  
  static final HashMap _mapFallbacks;
  
  static final HashMap _simpleDeserializers = StdDeserializers.constructAll();
  
  protected OptionalHandlerFactory optionalHandlers = OptionalHandlerFactory.instance;
  
  static {
    _keyDeserializers = StdKeyDeserializers.constructAll();
    _mapFallbacks = new HashMap<Object, Object>();
    _mapFallbacks.put(Map.class.getName(), LinkedHashMap.class);
    _mapFallbacks.put(ConcurrentMap.class.getName(), ConcurrentHashMap.class);
    _mapFallbacks.put(SortedMap.class.getName(), TreeMap.class);
    _mapFallbacks.put("java.util.NavigableMap", TreeMap.class);
    try {
      Class<?> clazz1 = Class.forName("java.util.ConcurrentNavigableMap");
      Class<?> clazz2 = Class.forName("java.util.ConcurrentSkipListMap");
      _mapFallbacks.put(clazz1.getName(), clazz2);
    } catch (ClassNotFoundException classNotFoundException) {}
    _collectionFallbacks = new HashMap<Object, Object>();
    _collectionFallbacks.put(Collection.class.getName(), ArrayList.class);
    _collectionFallbacks.put(List.class.getName(), ArrayList.class);
    _collectionFallbacks.put(Set.class.getName(), HashSet.class);
    _collectionFallbacks.put(SortedSet.class.getName(), TreeSet.class);
    _collectionFallbacks.put(Queue.class.getName(), LinkedList.class);
    _collectionFallbacks.put("java.util.Deque", LinkedList.class);
    _collectionFallbacks.put("java.util.NavigableSet", TreeSet.class);
    _arrayDeserializers = PrimitiveArrayDeserializers.getAll();
  }
  
  JsonDeserializer _constructDeserializer(DeserializationConfig paramDeserializationConfig, Annotated paramAnnotated, BeanProperty paramBeanProperty, Object paramObject) {
    if (paramObject instanceof JsonDeserializer) {
      paramObject = paramObject;
      object = paramObject;
      if (paramObject instanceof ContextualDeserializer)
        object = ((ContextualDeserializer)paramObject).createContextual(paramDeserializationConfig, paramBeanProperty); 
      return (JsonDeserializer)object;
    } 
    if (!(paramObject instanceof Class))
      throw new IllegalStateException("AnnotationIntrospector returned deserializer definition of type " + paramObject.getClass().getName() + "; expected type JsonDeserializer or Class<JsonDeserializer> instead"); 
    paramObject = paramObject;
    if (!JsonDeserializer.class.isAssignableFrom((Class<?>)paramObject))
      throw new IllegalStateException("AnnotationIntrospector returned Class " + paramObject.getName() + "; expected Class<JsonDeserializer>"); 
    paramObject = paramDeserializationConfig.deserializerInstance((Annotated)object, (Class)paramObject);
    Object object = paramObject;
    if (paramObject instanceof ContextualDeserializer)
      object = ((ContextualDeserializer)paramObject).createContextual(paramDeserializationConfig, paramBeanProperty); 
    return (JsonDeserializer)object;
  }
  
  protected abstract JsonDeserializer _findCustomArrayDeserializer(ArrayType paramArrayType, DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, BeanProperty paramBeanProperty, TypeDeserializer paramTypeDeserializer, JsonDeserializer paramJsonDeserializer);
  
  protected abstract JsonDeserializer _findCustomCollectionDeserializer(CollectionType paramCollectionType, DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty, TypeDeserializer paramTypeDeserializer, JsonDeserializer paramJsonDeserializer);
  
  protected abstract JsonDeserializer _findCustomCollectionLikeDeserializer(CollectionLikeType paramCollectionLikeType, DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty, TypeDeserializer paramTypeDeserializer, JsonDeserializer paramJsonDeserializer);
  
  protected abstract JsonDeserializer _findCustomEnumDeserializer(Class paramClass, DeserializationConfig paramDeserializationConfig, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty);
  
  protected abstract JsonDeserializer _findCustomMapDeserializer(MapType paramMapType, DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty, KeyDeserializer paramKeyDeserializer, TypeDeserializer paramTypeDeserializer, JsonDeserializer paramJsonDeserializer);
  
  protected abstract JsonDeserializer _findCustomMapLikeDeserializer(MapLikeType paramMapLikeType, DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty, KeyDeserializer paramKeyDeserializer, TypeDeserializer paramTypeDeserializer, JsonDeserializer paramJsonDeserializer);
  
  protected abstract JsonDeserializer _findCustomTreeNodeDeserializer(Class paramClass, DeserializationConfig paramDeserializationConfig, BeanProperty paramBeanProperty);
  
  protected EnumResolver constructEnumResolver(Class paramClass, DeserializationConfig paramDeserializationConfig) {
    return paramDeserializationConfig.isEnabled(DeserializationConfig.Feature.READ_ENUMS_USING_TO_STRING) ? EnumResolver.constructUnsafeUsingToString(paramClass) : EnumResolver.constructUnsafe(paramClass, paramDeserializationConfig.getAnnotationIntrospector());
  }
  
  public JsonDeserializer createArrayDeserializer(DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, ArrayType paramArrayType, BeanProperty paramBeanProperty) {
    JsonDeserializer jsonDeserializer1;
    ObjectArrayDeserializer objectArrayDeserializer;
    JavaType javaType = paramArrayType.getContentType();
    JsonDeserializer jsonDeserializer3 = (JsonDeserializer)javaType.getValueHandler();
    if (jsonDeserializer3 == null) {
      JsonDeserializer jsonDeserializer = (JsonDeserializer)_arrayDeserializers.get(javaType);
      if (jsonDeserializer != null) {
        jsonDeserializer1 = _findCustomArrayDeserializer(paramArrayType, paramDeserializationConfig, paramDeserializerProvider, paramBeanProperty, null, null);
        if (jsonDeserializer1 != null)
          jsonDeserializer = jsonDeserializer1; 
        return jsonDeserializer;
      } 
      if (javaType.isPrimitive())
        throw new IllegalArgumentException("Internal error: primitive type (" + paramArrayType + ") passed, no array deserializer found"); 
    } 
    TypeDeserializer typeDeserializer = (TypeDeserializer)javaType.getTypeHandler();
    if (typeDeserializer == null)
      typeDeserializer = findTypeDeserializer((DeserializationConfig)jsonDeserializer1, javaType, paramBeanProperty); 
    JsonDeserializer jsonDeserializer4 = _findCustomArrayDeserializer(paramArrayType, (DeserializationConfig)jsonDeserializer1, paramDeserializerProvider, paramBeanProperty, typeDeserializer, jsonDeserializer3);
    JsonDeserializer jsonDeserializer2 = jsonDeserializer4;
    if (jsonDeserializer4 == null) {
      jsonDeserializer2 = jsonDeserializer3;
      if (jsonDeserializer3 == null)
        jsonDeserializer2 = paramDeserializerProvider.findValueDeserializer((DeserializationConfig)jsonDeserializer1, javaType, paramBeanProperty); 
      objectArrayDeserializer = new ObjectArrayDeserializer(paramArrayType, jsonDeserializer2, typeDeserializer);
    } 
    return (JsonDeserializer)objectArrayDeserializer;
  }
  
  public JsonDeserializer createCollectionDeserializer(DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, CollectionType paramCollectionType, BeanProperty paramBeanProperty) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_3
    //   3: invokevirtual mapAbstractType : (Lcom/flurry/org/codehaus/jackson/map/DeserializationConfig;Lcom/flurry/org/codehaus/jackson/type/JavaType;)Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   6: checkcast com/flurry/org/codehaus/jackson/map/type/CollectionType
    //   9: astore #5
    //   11: aload #5
    //   13: invokevirtual getRawClass : ()Ljava/lang/Class;
    //   16: astore #11
    //   18: aload_1
    //   19: aload #5
    //   21: invokevirtual introspectForCreation : (Lcom/flurry/org/codehaus/jackson/type/JavaType;)Lcom/flurry/org/codehaus/jackson/map/BeanDescription;
    //   24: checkcast com/flurry/org/codehaus/jackson/map/introspect/BasicBeanDescription
    //   27: astore #6
    //   29: aload_0
    //   30: aload_1
    //   31: aload #6
    //   33: invokevirtual getClassInfo : ()Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotatedClass;
    //   36: aload #4
    //   38: invokevirtual findDeserializerFromAnnotation : (Lcom/flurry/org/codehaus/jackson/map/DeserializationConfig;Lcom/flurry/org/codehaus/jackson/map/introspect/Annotated;Lcom/flurry/org/codehaus/jackson/map/BeanProperty;)Lcom/flurry/org/codehaus/jackson/map/JsonDeserializer;
    //   41: astore_3
    //   42: aload_3
    //   43: ifnull -> 48
    //   46: aload_3
    //   47: areturn
    //   48: aload_0
    //   49: aload_1
    //   50: aload #6
    //   52: invokevirtual getClassInfo : ()Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotatedClass;
    //   55: aload #5
    //   57: aconst_null
    //   58: invokevirtual modifyTypeByAnnotation : (Lcom/flurry/org/codehaus/jackson/map/DeserializationConfig;Lcom/flurry/org/codehaus/jackson/map/introspect/Annotated;Lcom/flurry/org/codehaus/jackson/type/JavaType;Ljava/lang/String;)Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   61: checkcast com/flurry/org/codehaus/jackson/map/type/CollectionType
    //   64: astore #7
    //   66: aload #7
    //   68: invokevirtual getContentType : ()Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   71: astore #10
    //   73: aload #10
    //   75: invokevirtual getValueHandler : ()Ljava/lang/Object;
    //   78: checkcast com/flurry/org/codehaus/jackson/map/JsonDeserializer
    //   81: astore #8
    //   83: aload #10
    //   85: invokevirtual getTypeHandler : ()Ljava/lang/Object;
    //   88: checkcast com/flurry/org/codehaus/jackson/map/TypeDeserializer
    //   91: astore #5
    //   93: aload #5
    //   95: ifnonnull -> 331
    //   98: aload_0
    //   99: aload_1
    //   100: aload #10
    //   102: aload #4
    //   104: invokevirtual findTypeDeserializer : (Lcom/flurry/org/codehaus/jackson/map/DeserializationConfig;Lcom/flurry/org/codehaus/jackson/type/JavaType;Lcom/flurry/org/codehaus/jackson/map/BeanProperty;)Lcom/flurry/org/codehaus/jackson/map/TypeDeserializer;
    //   107: astore #5
    //   109: aload_0
    //   110: aload #7
    //   112: aload_1
    //   113: aload_2
    //   114: aload #6
    //   116: aload #4
    //   118: aload #5
    //   120: aload #8
    //   122: invokevirtual _findCustomCollectionDeserializer : (Lcom/flurry/org/codehaus/jackson/map/type/CollectionType;Lcom/flurry/org/codehaus/jackson/map/DeserializationConfig;Lcom/flurry/org/codehaus/jackson/map/DeserializerProvider;Lcom/flurry/org/codehaus/jackson/map/introspect/BasicBeanDescription;Lcom/flurry/org/codehaus/jackson/map/BeanProperty;Lcom/flurry/org/codehaus/jackson/map/TypeDeserializer;Lcom/flurry/org/codehaus/jackson/map/JsonDeserializer;)Lcom/flurry/org/codehaus/jackson/map/JsonDeserializer;
    //   125: astore #9
    //   127: aload #9
    //   129: astore_3
    //   130: aload #9
    //   132: ifnonnull -> 46
    //   135: aload #8
    //   137: astore_3
    //   138: aload #8
    //   140: ifnonnull -> 190
    //   143: ldc_w java/util/EnumSet
    //   146: aload #11
    //   148: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
    //   151: ifeq -> 180
    //   154: new com/flurry/org/codehaus/jackson/map/deser/std/EnumSetDeserializer
    //   157: dup
    //   158: aload #10
    //   160: invokevirtual getRawClass : ()Ljava/lang/Class;
    //   163: aload_0
    //   164: aload_1
    //   165: aload_2
    //   166: aload #10
    //   168: aload #4
    //   170: invokevirtual createEnumDeserializer : (Lcom/flurry/org/codehaus/jackson/map/DeserializationConfig;Lcom/flurry/org/codehaus/jackson/map/DeserializerProvider;Lcom/flurry/org/codehaus/jackson/type/JavaType;Lcom/flurry/org/codehaus/jackson/map/BeanProperty;)Lcom/flurry/org/codehaus/jackson/map/JsonDeserializer;
    //   173: invokespecial <init> : (Ljava/lang/Class;Lcom/flurry/org/codehaus/jackson/map/JsonDeserializer;)V
    //   176: astore_3
    //   177: goto -> 46
    //   180: aload_2
    //   181: aload_1
    //   182: aload #10
    //   184: aload #4
    //   186: invokevirtual findValueDeserializer : (Lcom/flurry/org/codehaus/jackson/map/DeserializationConfig;Lcom/flurry/org/codehaus/jackson/type/JavaType;Lcom/flurry/org/codehaus/jackson/map/BeanProperty;)Lcom/flurry/org/codehaus/jackson/map/JsonDeserializer;
    //   189: astore_3
    //   190: aload #7
    //   192: invokevirtual isInterface : ()Z
    //   195: ifne -> 213
    //   198: aload #7
    //   200: astore_2
    //   201: aload #6
    //   203: astore #4
    //   205: aload #7
    //   207: invokevirtual isAbstract : ()Z
    //   210: ifeq -> 282
    //   213: getstatic com/flurry/org/codehaus/jackson/map/deser/BasicDeserializerFactory._collectionFallbacks : Ljava/util/HashMap;
    //   216: aload #11
    //   218: invokevirtual getName : ()Ljava/lang/String;
    //   221: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   224: checkcast java/lang/Class
    //   227: astore_2
    //   228: aload_2
    //   229: ifnonnull -> 261
    //   232: new java/lang/IllegalArgumentException
    //   235: dup
    //   236: new java/lang/StringBuilder
    //   239: dup
    //   240: invokespecial <init> : ()V
    //   243: ldc_w 'Can not find a deserializer for non-concrete Collection type '
    //   246: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   249: aload #7
    //   251: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   254: invokevirtual toString : ()Ljava/lang/String;
    //   257: invokespecial <init> : (Ljava/lang/String;)V
    //   260: athrow
    //   261: aload_1
    //   262: aload #7
    //   264: aload_2
    //   265: invokevirtual constructSpecializedType : (Lcom/flurry/org/codehaus/jackson/type/JavaType;Ljava/lang/Class;)Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   268: checkcast com/flurry/org/codehaus/jackson/map/type/CollectionType
    //   271: astore_2
    //   272: aload_1
    //   273: aload_2
    //   274: invokevirtual introspectForCreation : (Lcom/flurry/org/codehaus/jackson/type/JavaType;)Lcom/flurry/org/codehaus/jackson/map/BeanDescription;
    //   277: checkcast com/flurry/org/codehaus/jackson/map/introspect/BasicBeanDescription
    //   280: astore #4
    //   282: aload_0
    //   283: aload_1
    //   284: aload #4
    //   286: invokevirtual findValueInstantiator : (Lcom/flurry/org/codehaus/jackson/map/DeserializationConfig;Lcom/flurry/org/codehaus/jackson/map/introspect/BasicBeanDescription;)Lcom/flurry/org/codehaus/jackson/map/deser/ValueInstantiator;
    //   289: astore_1
    //   290: aload #10
    //   292: invokevirtual getRawClass : ()Ljava/lang/Class;
    //   295: ldc_w java/lang/String
    //   298: if_acmpne -> 315
    //   301: new com/flurry/org/codehaus/jackson/map/deser/std/StringCollectionDeserializer
    //   304: dup
    //   305: aload_2
    //   306: aload_3
    //   307: aload_1
    //   308: invokespecial <init> : (Lcom/flurry/org/codehaus/jackson/type/JavaType;Lcom/flurry/org/codehaus/jackson/map/JsonDeserializer;Lcom/flurry/org/codehaus/jackson/map/deser/ValueInstantiator;)V
    //   311: astore_3
    //   312: goto -> 46
    //   315: new com/flurry/org/codehaus/jackson/map/deser/std/CollectionDeserializer
    //   318: dup
    //   319: aload_2
    //   320: aload_3
    //   321: aload #5
    //   323: aload_1
    //   324: invokespecial <init> : (Lcom/flurry/org/codehaus/jackson/type/JavaType;Lcom/flurry/org/codehaus/jackson/map/JsonDeserializer;Lcom/flurry/org/codehaus/jackson/map/TypeDeserializer;Lcom/flurry/org/codehaus/jackson/map/deser/ValueInstantiator;)V
    //   327: astore_3
    //   328: goto -> 46
    //   331: goto -> 109
  }
  
  public JsonDeserializer createCollectionLikeDeserializer(DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, CollectionLikeType paramCollectionLikeType, BeanProperty paramBeanProperty) {
    CollectionLikeType collectionLikeType1 = (CollectionLikeType)mapAbstractType(paramDeserializationConfig, (JavaType)paramCollectionLikeType);
    BasicBeanDescription basicBeanDescription = (BasicBeanDescription)paramDeserializationConfig.introspectClassAnnotations(collectionLikeType1.getRawClass());
    JsonDeserializer jsonDeserializer1 = findDeserializerFromAnnotation(paramDeserializationConfig, (Annotated)basicBeanDescription.getClassInfo(), paramBeanProperty);
    if (jsonDeserializer1 != null)
      return jsonDeserializer1; 
    CollectionLikeType collectionLikeType2 = (CollectionLikeType)modifyTypeByAnnotation(paramDeserializationConfig, (Annotated)basicBeanDescription.getClassInfo(), (JavaType)collectionLikeType1, null);
    JavaType javaType = collectionLikeType2.getContentType();
    JsonDeserializer jsonDeserializer2 = (JsonDeserializer)javaType.getValueHandler();
    TypeDeserializer typeDeserializer = (TypeDeserializer)javaType.getTypeHandler();
    if (typeDeserializer == null)
      typeDeserializer = findTypeDeserializer(paramDeserializationConfig, javaType, paramBeanProperty); 
    return _findCustomCollectionLikeDeserializer(collectionLikeType2, paramDeserializationConfig, paramDeserializerProvider, basicBeanDescription, paramBeanProperty, typeDeserializer, jsonDeserializer2);
  }
  
  public JsonDeserializer createEnumDeserializer(DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, JavaType paramJavaType, BeanProperty paramBeanProperty) {
    // Byte code:
    //   0: aload_1
    //   1: aload_3
    //   2: invokevirtual introspectForCreation : (Lcom/flurry/org/codehaus/jackson/type/JavaType;)Lcom/flurry/org/codehaus/jackson/map/BeanDescription;
    //   5: checkcast com/flurry/org/codehaus/jackson/map/introspect/BasicBeanDescription
    //   8: astore #5
    //   10: aload_0
    //   11: aload_1
    //   12: aload #5
    //   14: invokevirtual getClassInfo : ()Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotatedClass;
    //   17: aload #4
    //   19: invokevirtual findDeserializerFromAnnotation : (Lcom/flurry/org/codehaus/jackson/map/DeserializationConfig;Lcom/flurry/org/codehaus/jackson/map/introspect/Annotated;Lcom/flurry/org/codehaus/jackson/map/BeanProperty;)Lcom/flurry/org/codehaus/jackson/map/JsonDeserializer;
    //   22: astore_2
    //   23: aload_2
    //   24: ifnull -> 31
    //   27: aload_2
    //   28: astore_1
    //   29: aload_1
    //   30: areturn
    //   31: aload_3
    //   32: invokevirtual getRawClass : ()Ljava/lang/Class;
    //   35: astore_3
    //   36: aload_0
    //   37: aload_3
    //   38: aload_1
    //   39: aload #5
    //   41: aload #4
    //   43: invokevirtual _findCustomEnumDeserializer : (Ljava/lang/Class;Lcom/flurry/org/codehaus/jackson/map/DeserializationConfig;Lcom/flurry/org/codehaus/jackson/map/introspect/BasicBeanDescription;Lcom/flurry/org/codehaus/jackson/map/BeanProperty;)Lcom/flurry/org/codehaus/jackson/map/JsonDeserializer;
    //   46: astore_2
    //   47: aload_2
    //   48: ifnull -> 56
    //   51: aload_2
    //   52: astore_1
    //   53: goto -> 29
    //   56: aload #5
    //   58: invokevirtual getFactoryMethods : ()Ljava/util/List;
    //   61: invokeinterface iterator : ()Ljava/util/Iterator;
    //   66: astore #4
    //   68: aload #4
    //   70: invokeinterface hasNext : ()Z
    //   75: ifeq -> 176
    //   78: aload #4
    //   80: invokeinterface next : ()Ljava/lang/Object;
    //   85: checkcast com/flurry/org/codehaus/jackson/map/introspect/AnnotatedMethod
    //   88: astore_2
    //   89: aload_1
    //   90: invokevirtual getAnnotationIntrospector : ()Lcom/flurry/org/codehaus/jackson/map/AnnotationIntrospector;
    //   93: aload_2
    //   94: invokevirtual hasCreatorAnnotation : (Lcom/flurry/org/codehaus/jackson/map/introspect/Annotated;)Z
    //   97: ifeq -> 68
    //   100: aload_2
    //   101: invokevirtual getParameterCount : ()I
    //   104: iconst_1
    //   105: if_icmpne -> 129
    //   108: aload_2
    //   109: invokevirtual getRawType : ()Ljava/lang/Class;
    //   112: aload_3
    //   113: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
    //   116: ifeq -> 129
    //   119: aload_1
    //   120: aload_3
    //   121: aload_2
    //   122: invokestatic deserializerForCreator : (Lcom/flurry/org/codehaus/jackson/map/DeserializationConfig;Ljava/lang/Class;Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotatedMethod;)Lcom/flurry/org/codehaus/jackson/map/JsonDeserializer;
    //   125: astore_1
    //   126: goto -> 29
    //   129: new java/lang/IllegalArgumentException
    //   132: dup
    //   133: new java/lang/StringBuilder
    //   136: dup
    //   137: invokespecial <init> : ()V
    //   140: ldc_w 'Unsuitable method ('
    //   143: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: aload_2
    //   147: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   150: ldc_w ') decorated with @JsonCreator (for Enum type '
    //   153: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: aload_3
    //   157: invokevirtual getName : ()Ljava/lang/String;
    //   160: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   163: ldc_w ')'
    //   166: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: invokevirtual toString : ()Ljava/lang/String;
    //   172: invokespecial <init> : (Ljava/lang/String;)V
    //   175: athrow
    //   176: new com/flurry/org/codehaus/jackson/map/deser/std/EnumDeserializer
    //   179: dup
    //   180: aload_0
    //   181: aload_3
    //   182: aload_1
    //   183: invokevirtual constructEnumResolver : (Ljava/lang/Class;Lcom/flurry/org/codehaus/jackson/map/DeserializationConfig;)Lcom/flurry/org/codehaus/jackson/map/util/EnumResolver;
    //   186: invokespecial <init> : (Lcom/flurry/org/codehaus/jackson/map/util/EnumResolver;)V
    //   189: astore_1
    //   190: goto -> 29
  }
  
  public JsonDeserializer createMapDeserializer(DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, MapType paramMapType, BeanProperty paramBeanProperty) {
    MapDeserializer mapDeserializer;
    MapType mapType = (MapType)mapAbstractType(paramDeserializationConfig, (JavaType)paramMapType);
    BasicBeanDescription basicBeanDescription = (BasicBeanDescription)paramDeserializationConfig.introspectForCreation((JavaType)mapType);
    JsonDeserializer jsonDeserializer = findDeserializerFromAnnotation(paramDeserializationConfig, (Annotated)basicBeanDescription.getClassInfo(), paramBeanProperty);
    if (jsonDeserializer == null) {
      MapType mapType1 = (MapType)modifyTypeByAnnotation(paramDeserializationConfig, (Annotated)basicBeanDescription.getClassInfo(), (JavaType)mapType, null);
      JavaType javaType1 = mapType1.getKeyType();
      JavaType javaType2 = mapType1.getContentType();
      JsonDeserializer jsonDeserializer1 = (JsonDeserializer)javaType2.getValueHandler();
      KeyDeserializer keyDeserializer = (KeyDeserializer)javaType1.getValueHandler();
      if (keyDeserializer == null)
        keyDeserializer = paramDeserializerProvider.findKeyDeserializer(paramDeserializationConfig, javaType1, paramBeanProperty); 
      TypeDeserializer typeDeserializer = (TypeDeserializer)javaType2.getTypeHandler();
      if (typeDeserializer == null)
        typeDeserializer = findTypeDeserializer(paramDeserializationConfig, javaType2, paramBeanProperty); 
      JsonDeserializer jsonDeserializer2 = _findCustomMapDeserializer(mapType1, paramDeserializationConfig, paramDeserializerProvider, basicBeanDescription, paramBeanProperty, keyDeserializer, typeDeserializer, jsonDeserializer1);
      jsonDeserializer = jsonDeserializer2;
      if (jsonDeserializer2 == null) {
        BasicBeanDescription basicBeanDescription1;
        MapType mapType2;
        Class clazz;
        jsonDeserializer = jsonDeserializer1;
        if (jsonDeserializer1 == null)
          jsonDeserializer = paramDeserializerProvider.findValueDeserializer(paramDeserializationConfig, javaType2, paramBeanProperty); 
        Class<?> clazz1 = mapType1.getRawClass();
        if (EnumMap.class.isAssignableFrom(clazz1)) {
          clazz = javaType1.getRawClass();
          if (clazz == null || !clazz.isEnum())
            throw new IllegalArgumentException("Can not construct EnumMap; generic (key) type not available"); 
          return (JsonDeserializer)new EnumMapDeserializer(javaType1.getRawClass(), createEnumDeserializer(paramDeserializationConfig, paramDeserializerProvider, javaType1, paramBeanProperty), jsonDeserializer);
        } 
        if (mapType1.isInterface() || mapType1.isAbstract()) {
          Class clazz2 = (Class)_mapFallbacks.get(clazz1.getName());
          if (clazz2 == null)
            throw new IllegalArgumentException("Can not find a deserializer for non-concrete Map type " + mapType1); 
          mapType2 = (MapType)paramDeserializationConfig.constructSpecializedType((JavaType)mapType1, clazz2);
          basicBeanDescription1 = (BasicBeanDescription)paramDeserializationConfig.introspectForCreation((JavaType)mapType2);
        } else {
          basicBeanDescription1 = basicBeanDescription;
          mapType2 = mapType1;
        } 
        mapDeserializer = new MapDeserializer((JavaType)mapType2, findValueInstantiator(paramDeserializationConfig, basicBeanDescription1), (KeyDeserializer)clazz, jsonDeserializer, typeDeserializer);
        mapDeserializer.setIgnorableProperties(paramDeserializationConfig.getAnnotationIntrospector().findPropertiesToIgnore(basicBeanDescription1.getClassInfo()));
      } 
    } 
    return (JsonDeserializer)mapDeserializer;
  }
  
  public JsonDeserializer createMapLikeDeserializer(DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, MapLikeType paramMapLikeType, BeanProperty paramBeanProperty) {
    MapLikeType mapLikeType1 = (MapLikeType)mapAbstractType(paramDeserializationConfig, (JavaType)paramMapLikeType);
    BasicBeanDescription basicBeanDescription = (BasicBeanDescription)paramDeserializationConfig.introspectForCreation((JavaType)mapLikeType1);
    JsonDeserializer jsonDeserializer1 = findDeserializerFromAnnotation(paramDeserializationConfig, (Annotated)basicBeanDescription.getClassInfo(), paramBeanProperty);
    if (jsonDeserializer1 != null)
      return jsonDeserializer1; 
    MapLikeType mapLikeType2 = (MapLikeType)modifyTypeByAnnotation(paramDeserializationConfig, (Annotated)basicBeanDescription.getClassInfo(), (JavaType)mapLikeType1, null);
    JavaType javaType1 = mapLikeType2.getKeyType();
    JavaType javaType2 = mapLikeType2.getContentType();
    JsonDeserializer jsonDeserializer2 = (JsonDeserializer)javaType2.getValueHandler();
    KeyDeserializer keyDeserializer = (KeyDeserializer)javaType1.getValueHandler();
    if (keyDeserializer == null)
      keyDeserializer = paramDeserializerProvider.findKeyDeserializer(paramDeserializationConfig, javaType1, paramBeanProperty); 
    TypeDeserializer typeDeserializer = (TypeDeserializer)javaType2.getTypeHandler();
    if (typeDeserializer == null)
      typeDeserializer = findTypeDeserializer(paramDeserializationConfig, javaType2, paramBeanProperty); 
    return _findCustomMapLikeDeserializer(mapLikeType2, paramDeserializationConfig, paramDeserializerProvider, basicBeanDescription, paramBeanProperty, keyDeserializer, typeDeserializer, jsonDeserializer2);
  }
  
  public JsonDeserializer createTreeDeserializer(DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, JavaType paramJavaType, BeanProperty paramBeanProperty) {
    Class clazz = paramJavaType.getRawClass();
    JsonDeserializer jsonDeserializer = _findCustomTreeNodeDeserializer(clazz, paramDeserializationConfig, paramBeanProperty);
    if (jsonDeserializer == null)
      jsonDeserializer = JsonNodeDeserializer.getDeserializer(clazz); 
    return jsonDeserializer;
  }
  
  protected JsonDeserializer findDeserializerFromAnnotation(DeserializationConfig paramDeserializationConfig, Annotated paramAnnotated, BeanProperty paramBeanProperty) {
    Object object = paramDeserializationConfig.getAnnotationIntrospector().findDeserializer(paramAnnotated);
    return (object != null) ? _constructDeserializer(paramDeserializationConfig, paramAnnotated, paramBeanProperty, object) : null;
  }
  
  public TypeDeserializer findPropertyContentTypeDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, AnnotatedMember paramAnnotatedMember, BeanProperty paramBeanProperty) {
    AnnotationIntrospector annotationIntrospector = paramDeserializationConfig.getAnnotationIntrospector();
    TypeResolverBuilder typeResolverBuilder = annotationIntrospector.findPropertyContentTypeResolver((MapperConfig)paramDeserializationConfig, paramAnnotatedMember, paramJavaType);
    paramJavaType = paramJavaType.getContentType();
    return (typeResolverBuilder == null) ? findTypeDeserializer(paramDeserializationConfig, paramJavaType, paramBeanProperty) : typeResolverBuilder.buildTypeDeserializer(paramDeserializationConfig, paramJavaType, paramDeserializationConfig.getSubtypeResolver().collectAndResolveSubtypes(paramAnnotatedMember, (MapperConfig)paramDeserializationConfig, annotationIntrospector), paramBeanProperty);
  }
  
  public TypeDeserializer findPropertyTypeDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, AnnotatedMember paramAnnotatedMember, BeanProperty paramBeanProperty) {
    AnnotationIntrospector annotationIntrospector = paramDeserializationConfig.getAnnotationIntrospector();
    TypeResolverBuilder typeResolverBuilder = annotationIntrospector.findPropertyTypeResolver((MapperConfig)paramDeserializationConfig, paramAnnotatedMember, paramJavaType);
    return (typeResolverBuilder == null) ? findTypeDeserializer(paramDeserializationConfig, paramJavaType, paramBeanProperty) : typeResolverBuilder.buildTypeDeserializer(paramDeserializationConfig, paramJavaType, paramDeserializationConfig.getSubtypeResolver().collectAndResolveSubtypes(paramAnnotatedMember, (MapperConfig)paramDeserializationConfig, annotationIntrospector), paramBeanProperty);
  }
  
  protected JsonDeserializer findStdBeanDeserializer(DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, JavaType paramJavaType, BeanProperty paramBeanProperty) {
    Class<?> clazz = paramJavaType.getRawClass();
    JsonDeserializer jsonDeserializer3 = (JsonDeserializer)_simpleDeserializers.get(new ClassKey(clazz));
    if (jsonDeserializer3 != null)
      return jsonDeserializer3; 
    if (AtomicReference.class.isAssignableFrom(clazz)) {
      JavaType javaType;
      JavaType[] arrayOfJavaType = paramDeserializationConfig.getTypeFactory().findTypeParameters(paramJavaType, AtomicReference.class);
      if (arrayOfJavaType == null || arrayOfJavaType.length < 1) {
        javaType = TypeFactory.unknownType();
      } else {
        javaType = javaType[0];
      } 
      return (JsonDeserializer)new AtomicReferenceDeserializer(javaType, paramBeanProperty);
    } 
    JsonDeserializer jsonDeserializer2 = this.optionalHandlers.findDeserializer(paramJavaType, paramDeserializationConfig, paramDeserializerProvider);
    JsonDeserializer jsonDeserializer1 = jsonDeserializer2;
    if (jsonDeserializer2 == null)
      jsonDeserializer1 = null; 
    return jsonDeserializer1;
  }
  
  public TypeDeserializer findTypeDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, BeanProperty paramBeanProperty) {
    DeserializationConfig deserializationConfig = null;
    AnnotatedClass annotatedClass = ((BasicBeanDescription)paramDeserializationConfig.introspectClassAnnotations(paramJavaType.getRawClass())).getClassInfo();
    AnnotationIntrospector annotationIntrospector = paramDeserializationConfig.getAnnotationIntrospector();
    TypeResolverBuilder typeResolverBuilder1 = annotationIntrospector.findTypeResolver((MapperConfig)paramDeserializationConfig, annotatedClass, paramJavaType);
    if (typeResolverBuilder1 == null) {
      typeResolverBuilder1 = paramDeserializationConfig.getDefaultTyper(paramJavaType);
      if (typeResolverBuilder1 == null)
        return (TypeDeserializer)deserializationConfig; 
    } else {
      Collection collection = paramDeserializationConfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedClass, (MapperConfig)paramDeserializationConfig, annotationIntrospector);
      TypeResolverBuilder typeResolverBuilder = typeResolverBuilder1;
    } 
    deserializationConfig = null;
    TypeResolverBuilder typeResolverBuilder2 = typeResolverBuilder1;
  }
  
  public abstract ValueInstantiator findValueInstantiator(DeserializationConfig paramDeserializationConfig, BasicBeanDescription paramBasicBeanDescription);
  
  public abstract JavaType mapAbstractType(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType);
  
  protected JavaType modifyTypeByAnnotation(DeserializationConfig paramDeserializationConfig, Annotated paramAnnotated, JavaType paramJavaType, String paramString) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getAnnotationIntrospector : ()Lcom/flurry/org/codehaus/jackson/map/AnnotationIntrospector;
    //   4: astore #6
    //   6: aload #6
    //   8: aload_2
    //   9: aload_3
    //   10: aload #4
    //   12: invokevirtual findDeserializationType : (Lcom/flurry/org/codehaus/jackson/map/introspect/Annotated;Lcom/flurry/org/codehaus/jackson/type/JavaType;Ljava/lang/String;)Ljava/lang/Class;
    //   15: astore #7
    //   17: aload #7
    //   19: ifnull -> 447
    //   22: aload_3
    //   23: aload #7
    //   25: invokevirtual narrowBy : (Ljava/lang/Class;)Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   28: astore #5
    //   30: aload #5
    //   32: astore_3
    //   33: aload_3
    //   34: astore #5
    //   36: aload_3
    //   37: invokevirtual isContainerType : ()Z
    //   40: ifeq -> 327
    //   43: aload #6
    //   45: aload_2
    //   46: aload_3
    //   47: invokevirtual getKeyType : ()Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   50: aload #4
    //   52: invokevirtual findDeserializationKeyType : (Lcom/flurry/org/codehaus/jackson/map/introspect/Annotated;Lcom/flurry/org/codehaus/jackson/type/JavaType;Ljava/lang/String;)Ljava/lang/Class;
    //   55: astore #7
    //   57: aload_3
    //   58: astore #5
    //   60: aload #7
    //   62: ifnull -> 188
    //   65: aload_3
    //   66: instanceof com/flurry/org/codehaus/jackson/map/type/MapLikeType
    //   69: ifne -> 177
    //   72: new com/flurry/org/codehaus/jackson/map/JsonMappingException
    //   75: dup
    //   76: new java/lang/StringBuilder
    //   79: dup
    //   80: invokespecial <init> : ()V
    //   83: ldc_w 'Illegal key-type annotation: type '
    //   86: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: aload_3
    //   90: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   93: ldc_w ' is not a Map(-like) type'
    //   96: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: invokevirtual toString : ()Ljava/lang/String;
    //   102: invokespecial <init> : (Ljava/lang/String;)V
    //   105: athrow
    //   106: astore_1
    //   107: new com/flurry/org/codehaus/jackson/map/JsonMappingException
    //   110: dup
    //   111: new java/lang/StringBuilder
    //   114: dup
    //   115: invokespecial <init> : ()V
    //   118: ldc_w 'Failed to narrow type '
    //   121: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: aload_3
    //   125: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   128: ldc_w ' with concrete-type annotation (value '
    //   131: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: aload #7
    //   136: invokevirtual getName : ()Ljava/lang/String;
    //   139: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: ldc_w '), method ''
    //   145: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: aload_2
    //   149: invokevirtual getName : ()Ljava/lang/String;
    //   152: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   155: ldc_w '': '
    //   158: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   161: aload_1
    //   162: invokevirtual getMessage : ()Ljava/lang/String;
    //   165: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   168: invokevirtual toString : ()Ljava/lang/String;
    //   171: aconst_null
    //   172: aload_1
    //   173: invokespecial <init> : (Ljava/lang/String;Lcom/flurry/org/codehaus/jackson/JsonLocation;Ljava/lang/Throwable;)V
    //   176: athrow
    //   177: aload_3
    //   178: checkcast com/flurry/org/codehaus/jackson/map/type/MapLikeType
    //   181: aload #7
    //   183: invokevirtual narrowKey : (Ljava/lang/Class;)Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   186: astore #5
    //   188: aload #5
    //   190: invokevirtual getKeyType : ()Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   193: astore_3
    //   194: aload_3
    //   195: ifnull -> 237
    //   198: aload_3
    //   199: invokevirtual getValueHandler : ()Ljava/lang/Object;
    //   202: ifnonnull -> 237
    //   205: aload #6
    //   207: aload_2
    //   208: invokevirtual findKeyDeserializer : (Lcom/flurry/org/codehaus/jackson/map/introspect/Annotated;)Ljava/lang/Class;
    //   211: astore #7
    //   213: aload #7
    //   215: ifnull -> 237
    //   218: aload #7
    //   220: ldc_w com/flurry/org/codehaus/jackson/map/KeyDeserializer$None
    //   223: if_acmpeq -> 237
    //   226: aload_3
    //   227: aload_1
    //   228: aload_2
    //   229: aload #7
    //   231: invokevirtual keyDeserializerInstance : (Lcom/flurry/org/codehaus/jackson/map/introspect/Annotated;Ljava/lang/Class;)Lcom/flurry/org/codehaus/jackson/map/KeyDeserializer;
    //   234: invokevirtual setValueHandler : (Ljava/lang/Object;)V
    //   237: aload #6
    //   239: aload_2
    //   240: aload #5
    //   242: invokevirtual getContentType : ()Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   245: aload #4
    //   247: invokevirtual findDeserializationContentType : (Lcom/flurry/org/codehaus/jackson/map/introspect/Annotated;Lcom/flurry/org/codehaus/jackson/type/JavaType;Ljava/lang/String;)Ljava/lang/Class;
    //   250: astore #4
    //   252: aload #5
    //   254: astore_3
    //   255: aload #4
    //   257: ifnull -> 268
    //   260: aload #5
    //   262: aload #4
    //   264: invokevirtual narrowContentsBy : (Ljava/lang/Class;)Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   267: astore_3
    //   268: aload_3
    //   269: astore #5
    //   271: aload_3
    //   272: invokevirtual getContentType : ()Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   275: invokevirtual getValueHandler : ()Ljava/lang/Object;
    //   278: ifnonnull -> 327
    //   281: aload #6
    //   283: aload_2
    //   284: invokevirtual findContentDeserializer : (Lcom/flurry/org/codehaus/jackson/map/introspect/Annotated;)Ljava/lang/Class;
    //   287: astore #4
    //   289: aload_3
    //   290: astore #5
    //   292: aload #4
    //   294: ifnull -> 327
    //   297: aload_3
    //   298: astore #5
    //   300: aload #4
    //   302: ldc_w com/flurry/org/codehaus/jackson/map/JsonDeserializer$None
    //   305: if_acmpeq -> 327
    //   308: aload_1
    //   309: aload_2
    //   310: aload #4
    //   312: invokevirtual deserializerInstance : (Lcom/flurry/org/codehaus/jackson/map/introspect/Annotated;Ljava/lang/Class;)Lcom/flurry/org/codehaus/jackson/map/JsonDeserializer;
    //   315: astore_1
    //   316: aload_3
    //   317: invokevirtual getContentType : ()Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   320: aload_1
    //   321: invokevirtual setValueHandler : (Ljava/lang/Object;)V
    //   324: aload_3
    //   325: astore #5
    //   327: aload #5
    //   329: areturn
    //   330: astore_1
    //   331: new com/flurry/org/codehaus/jackson/map/JsonMappingException
    //   334: dup
    //   335: new java/lang/StringBuilder
    //   338: dup
    //   339: invokespecial <init> : ()V
    //   342: ldc_w 'Failed to narrow key type '
    //   345: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   348: aload_3
    //   349: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   352: ldc_w ' with key-type annotation ('
    //   355: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   358: aload #7
    //   360: invokevirtual getName : ()Ljava/lang/String;
    //   363: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   366: ldc_w '): '
    //   369: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   372: aload_1
    //   373: invokevirtual getMessage : ()Ljava/lang/String;
    //   376: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   379: invokevirtual toString : ()Ljava/lang/String;
    //   382: aconst_null
    //   383: aload_1
    //   384: invokespecial <init> : (Ljava/lang/String;Lcom/flurry/org/codehaus/jackson/JsonLocation;Ljava/lang/Throwable;)V
    //   387: athrow
    //   388: astore_1
    //   389: new com/flurry/org/codehaus/jackson/map/JsonMappingException
    //   392: dup
    //   393: new java/lang/StringBuilder
    //   396: dup
    //   397: invokespecial <init> : ()V
    //   400: ldc_w 'Failed to narrow content type '
    //   403: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   406: aload #5
    //   408: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   411: ldc_w ' with content-type annotation ('
    //   414: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   417: aload #4
    //   419: invokevirtual getName : ()Ljava/lang/String;
    //   422: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   425: ldc_w '): '
    //   428: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   431: aload_1
    //   432: invokevirtual getMessage : ()Ljava/lang/String;
    //   435: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   438: invokevirtual toString : ()Ljava/lang/String;
    //   441: aconst_null
    //   442: aload_1
    //   443: invokespecial <init> : (Ljava/lang/String;Lcom/flurry/org/codehaus/jackson/JsonLocation;Ljava/lang/Throwable;)V
    //   446: athrow
    //   447: goto -> 33
    // Exception table:
    //   from	to	target	type
    //   22	30	106	java/lang/IllegalArgumentException
    //   177	188	330	java/lang/IllegalArgumentException
    //   260	268	388	java/lang/IllegalArgumentException
  }
  
  protected JavaType resolveType(DeserializationConfig paramDeserializationConfig, BasicBeanDescription paramBasicBeanDescription, JavaType paramJavaType, AnnotatedMember paramAnnotatedMember, BeanProperty paramBeanProperty) {
    TypeDeserializer typeDeserializer;
    JavaType javaType = paramJavaType;
    if (paramJavaType.isContainerType()) {
      AnnotationIntrospector annotationIntrospector = paramDeserializationConfig.getAnnotationIntrospector();
      JavaType javaType1 = paramJavaType.getKeyType();
      if (javaType1 != null) {
        Class<KeyDeserializer.None> clazz1 = annotationIntrospector.findKeyDeserializer((Annotated)paramAnnotatedMember);
        if (clazz1 != null && clazz1 != KeyDeserializer.None.class)
          javaType1.setValueHandler(paramDeserializationConfig.keyDeserializerInstance((Annotated)paramAnnotatedMember, clazz1)); 
      } 
      Class<JsonDeserializer.None> clazz = annotationIntrospector.findContentDeserializer((Annotated)paramAnnotatedMember);
      if (clazz != null && clazz != JsonDeserializer.None.class) {
        JsonDeserializer jsonDeserializer = paramDeserializationConfig.deserializerInstance((Annotated)paramAnnotatedMember, clazz);
        paramJavaType.getContentType().setValueHandler(jsonDeserializer);
      } 
      javaType = paramJavaType;
      if (paramAnnotatedMember instanceof AnnotatedMember) {
        TypeDeserializer typeDeserializer1 = findPropertyContentTypeDeserializer(paramDeserializationConfig, paramJavaType, paramAnnotatedMember, paramBeanProperty);
        javaType = paramJavaType;
        if (typeDeserializer1 != null)
          javaType = paramJavaType.withContentTypeHandler(typeDeserializer1); 
      } 
    } 
    if (paramAnnotatedMember instanceof AnnotatedMember) {
      typeDeserializer = findPropertyTypeDeserializer(paramDeserializationConfig, javaType, paramAnnotatedMember, paramBeanProperty);
    } else {
      typeDeserializer = findTypeDeserializer((DeserializationConfig)typeDeserializer, javaType, null);
    } 
    paramJavaType = javaType;
    if (typeDeserializer != null)
      paramJavaType = javaType.withTypeHandler(typeDeserializer); 
    return paramJavaType;
  }
  
  public abstract DeserializerFactory withConfig(DeserializerFactory.Config paramConfig);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\BasicDeserializerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */