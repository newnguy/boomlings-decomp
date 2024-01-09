package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.BeanDescription;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.ContextualSerializer;
import com.flurry.org.codehaus.jackson.map.JsonSerializable;
import com.flurry.org.codehaus.jackson.map.JsonSerializableWithType;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.MapperConfig;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.SerializerFactory;
import com.flurry.org.codehaus.jackson.map.Serializers;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.annotate.JsonSerialize;
import com.flurry.org.codehaus.jackson.map.ext.OptionalHandlerFactory;
import com.flurry.org.codehaus.jackson.map.introspect.Annotated;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedClass;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.flurry.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import com.flurry.org.codehaus.jackson.map.ser.std.CalendarSerializer;
import com.flurry.org.codehaus.jackson.map.ser.std.DateSerializer;
import com.flurry.org.codehaus.jackson.map.ser.std.EnumMapSerializer;
import com.flurry.org.codehaus.jackson.map.ser.std.EnumSerializer;
import com.flurry.org.codehaus.jackson.map.ser.std.IndexedStringListSerializer;
import com.flurry.org.codehaus.jackson.map.ser.std.InetAddressSerializer;
import com.flurry.org.codehaus.jackson.map.ser.std.JsonValueSerializer;
import com.flurry.org.codehaus.jackson.map.ser.std.MapSerializer;
import com.flurry.org.codehaus.jackson.map.ser.std.NullSerializer;
import com.flurry.org.codehaus.jackson.map.ser.std.ObjectArraySerializer;
import com.flurry.org.codehaus.jackson.map.ser.std.SerializableSerializer;
import com.flurry.org.codehaus.jackson.map.ser.std.SerializableWithTypeSerializer;
import com.flurry.org.codehaus.jackson.map.ser.std.StdArraySerializers;
import com.flurry.org.codehaus.jackson.map.ser.std.StdContainerSerializers;
import com.flurry.org.codehaus.jackson.map.ser.std.StdJdkSerializers;
import com.flurry.org.codehaus.jackson.map.ser.std.StringCollectionSerializer;
import com.flurry.org.codehaus.jackson.map.ser.std.StringSerializer;
import com.flurry.org.codehaus.jackson.map.ser.std.TimeZoneSerializer;
import com.flurry.org.codehaus.jackson.map.ser.std.ToStringSerializer;
import com.flurry.org.codehaus.jackson.map.ser.std.TokenBufferSerializer;
import com.flurry.org.codehaus.jackson.map.type.ArrayType;
import com.flurry.org.codehaus.jackson.map.type.CollectionLikeType;
import com.flurry.org.codehaus.jackson.map.type.CollectionType;
import com.flurry.org.codehaus.jackson.map.type.MapLikeType;
import com.flurry.org.codehaus.jackson.map.type.MapType;
import com.flurry.org.codehaus.jackson.map.type.TypeFactory;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import com.flurry.org.codehaus.jackson.map.util.EnumValues;
import com.flurry.org.codehaus.jackson.type.JavaType;
import com.flurry.org.codehaus.jackson.util.TokenBuffer;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.RandomAccess;
import java.util.TimeZone;

public abstract class BasicSerializerFactory extends SerializerFactory {
  protected static final HashMap _arraySerializers;
  
  protected static final HashMap _concrete = new HashMap<Object, Object>();
  
  protected static final HashMap _concreteLazy = new HashMap<Object, Object>();
  
  protected OptionalHandlerFactory optionalHandlers = OptionalHandlerFactory.instance;
  
  static {
    _concrete.put(String.class.getName(), new StringSerializer());
    ToStringSerializer toStringSerializer = ToStringSerializer.instance;
    _concrete.put(StringBuffer.class.getName(), toStringSerializer);
    _concrete.put(StringBuilder.class.getName(), toStringSerializer);
    _concrete.put(Character.class.getName(), toStringSerializer);
    _concrete.put(char.class.getName(), toStringSerializer);
    _concrete.put(boolean.class.getName(), new StdSerializers$BooleanSerializer(true));
    _concrete.put(Boolean.class.getName(), new StdSerializers$BooleanSerializer(false));
    StdSerializers$IntegerSerializer stdSerializers$IntegerSerializer = new StdSerializers$IntegerSerializer();
    _concrete.put(Integer.class.getName(), stdSerializers$IntegerSerializer);
    _concrete.put(int.class.getName(), stdSerializers$IntegerSerializer);
    _concrete.put(Long.class.getName(), StdSerializers$LongSerializer.instance);
    _concrete.put(long.class.getName(), StdSerializers$LongSerializer.instance);
    _concrete.put(Byte.class.getName(), StdSerializers$IntLikeSerializer.instance);
    _concrete.put(byte.class.getName(), StdSerializers$IntLikeSerializer.instance);
    _concrete.put(Short.class.getName(), StdSerializers$IntLikeSerializer.instance);
    _concrete.put(short.class.getName(), StdSerializers$IntLikeSerializer.instance);
    _concrete.put(Float.class.getName(), StdSerializers$FloatSerializer.instance);
    _concrete.put(float.class.getName(), StdSerializers$FloatSerializer.instance);
    _concrete.put(Double.class.getName(), StdSerializers$DoubleSerializer.instance);
    _concrete.put(double.class.getName(), StdSerializers$DoubleSerializer.instance);
    StdSerializers$NumberSerializer stdSerializers$NumberSerializer = new StdSerializers$NumberSerializer();
    _concrete.put(BigInteger.class.getName(), stdSerializers$NumberSerializer);
    _concrete.put(BigDecimal.class.getName(), stdSerializers$NumberSerializer);
    _concrete.put(Calendar.class.getName(), CalendarSerializer.instance);
    DateSerializer dateSerializer = DateSerializer.instance;
    _concrete.put(Date.class.getName(), dateSerializer);
    _concrete.put(Timestamp.class.getName(), dateSerializer);
    _concrete.put(Date.class.getName(), new StdSerializers$SqlDateSerializer());
    _concrete.put(Time.class.getName(), new StdSerializers$SqlTimeSerializer());
    for (Map.Entry entry : (new StdJdkSerializers()).provide()) {
      Object object = entry.getValue();
      if (object instanceof JsonSerializer) {
        _concrete.put(((Class)entry.getKey()).getName(), (JsonSerializer)object);
        continue;
      } 
      if (object instanceof Class) {
        object = object;
        _concreteLazy.put(((Class)entry.getKey()).getName(), object);
        continue;
      } 
      throw new IllegalStateException("Internal error: unrecognized value of type " + entry.getClass().getName());
    } 
    _concreteLazy.put(TokenBuffer.class.getName(), TokenBufferSerializer.class);
    _arraySerializers = new HashMap<Object, Object>();
    _arraySerializers.put(boolean[].class.getName(), new StdArraySerializers.BooleanArraySerializer());
    _arraySerializers.put(byte[].class.getName(), new StdArraySerializers.ByteArraySerializer());
    _arraySerializers.put(char[].class.getName(), new StdArraySerializers.CharArraySerializer());
    _arraySerializers.put(short[].class.getName(), new StdArraySerializers.ShortArraySerializer());
    _arraySerializers.put(int[].class.getName(), new StdArraySerializers.IntArraySerializer());
    _arraySerializers.put(long[].class.getName(), new StdArraySerializers.LongArraySerializer());
    _arraySerializers.put(float[].class.getName(), new StdArraySerializers.FloatArraySerializer());
    _arraySerializers.put(double[].class.getName(), new StdArraySerializers.DoubleArraySerializer());
  }
  
  protected static JsonSerializer findContentSerializer(SerializationConfig paramSerializationConfig, Annotated paramAnnotated, BeanProperty paramBeanProperty) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getAnnotationIntrospector : ()Lcom/flurry/org/codehaus/jackson/map/AnnotationIntrospector;
    //   4: astore #5
    //   6: aload #5
    //   8: aload_1
    //   9: invokevirtual findContentSerializer : (Lcom/flurry/org/codehaus/jackson/map/introspect/Annotated;)Ljava/lang/Class;
    //   12: astore #4
    //   14: aload #4
    //   16: ifnull -> 30
    //   19: aload #4
    //   21: astore_3
    //   22: aload #4
    //   24: ldc_w com/flurry/org/codehaus/jackson/map/JsonSerializer$None
    //   27: if_acmpne -> 49
    //   30: aload #4
    //   32: astore_3
    //   33: aload_2
    //   34: ifnull -> 49
    //   37: aload #5
    //   39: aload_2
    //   40: invokeinterface getMember : ()Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotatedMember;
    //   45: invokevirtual findContentSerializer : (Lcom/flurry/org/codehaus/jackson/map/introspect/Annotated;)Ljava/lang/Class;
    //   48: astore_3
    //   49: aload_3
    //   50: ifnull -> 69
    //   53: aload_3
    //   54: ldc_w com/flurry/org/codehaus/jackson/map/JsonSerializer$None
    //   57: if_acmpeq -> 69
    //   60: aload_0
    //   61: aload_1
    //   62: aload_3
    //   63: invokevirtual serializerInstance : (Lcom/flurry/org/codehaus/jackson/map/introspect/Annotated;Ljava/lang/Class;)Lcom/flurry/org/codehaus/jackson/map/JsonSerializer;
    //   66: astore_0
    //   67: aload_0
    //   68: areturn
    //   69: aconst_null
    //   70: astore_0
    //   71: goto -> 67
  }
  
  protected static JsonSerializer findKeySerializer(SerializationConfig paramSerializationConfig, Annotated paramAnnotated, BeanProperty paramBeanProperty) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getAnnotationIntrospector : ()Lcom/flurry/org/codehaus/jackson/map/AnnotationIntrospector;
    //   4: astore #5
    //   6: aload #5
    //   8: aload_1
    //   9: invokevirtual findKeySerializer : (Lcom/flurry/org/codehaus/jackson/map/introspect/Annotated;)Ljava/lang/Class;
    //   12: astore #4
    //   14: aload #4
    //   16: ifnull -> 30
    //   19: aload #4
    //   21: astore_3
    //   22: aload #4
    //   24: ldc_w com/flurry/org/codehaus/jackson/map/JsonSerializer$None
    //   27: if_acmpne -> 49
    //   30: aload #4
    //   32: astore_3
    //   33: aload_2
    //   34: ifnull -> 49
    //   37: aload #5
    //   39: aload_2
    //   40: invokeinterface getMember : ()Lcom/flurry/org/codehaus/jackson/map/introspect/AnnotatedMember;
    //   45: invokevirtual findKeySerializer : (Lcom/flurry/org/codehaus/jackson/map/introspect/Annotated;)Ljava/lang/Class;
    //   48: astore_3
    //   49: aload_3
    //   50: ifnull -> 69
    //   53: aload_3
    //   54: ldc_w com/flurry/org/codehaus/jackson/map/JsonSerializer$None
    //   57: if_acmpeq -> 69
    //   60: aload_0
    //   61: aload_1
    //   62: aload_3
    //   63: invokevirtual serializerInstance : (Lcom/flurry/org/codehaus/jackson/map/introspect/Annotated;Ljava/lang/Class;)Lcom/flurry/org/codehaus/jackson/map/JsonSerializer;
    //   66: astore_0
    //   67: aload_0
    //   68: areturn
    //   69: aconst_null
    //   70: astore_0
    //   71: goto -> 67
  }
  
  protected static JavaType modifySecondaryTypesByAnnotation(SerializationConfig paramSerializationConfig, Annotated paramAnnotated, JavaType paramJavaType) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getAnnotationIntrospector : ()Lcom/flurry/org/codehaus/jackson/map/AnnotationIntrospector;
    //   4: astore #4
    //   6: aload_2
    //   7: astore_3
    //   8: aload_2
    //   9: invokevirtual isContainerType : ()Z
    //   12: ifeq -> 105
    //   15: aload #4
    //   17: aload_1
    //   18: aload_2
    //   19: invokevirtual getKeyType : ()Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   22: invokevirtual findSerializationKeyType : (Lcom/flurry/org/codehaus/jackson/map/introspect/Annotated;Lcom/flurry/org/codehaus/jackson/type/JavaType;)Ljava/lang/Class;
    //   25: astore_3
    //   26: aload_2
    //   27: astore_0
    //   28: aload_3
    //   29: ifnull -> 82
    //   32: aload_2
    //   33: instanceof com/flurry/org/codehaus/jackson/map/type/MapType
    //   36: ifne -> 73
    //   39: new java/lang/IllegalArgumentException
    //   42: dup
    //   43: new java/lang/StringBuilder
    //   46: dup
    //   47: invokespecial <init> : ()V
    //   50: ldc_w 'Illegal key-type annotation: type '
    //   53: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   56: aload_2
    //   57: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   60: ldc_w ' is not a Map type'
    //   63: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   66: invokevirtual toString : ()Ljava/lang/String;
    //   69: invokespecial <init> : (Ljava/lang/String;)V
    //   72: athrow
    //   73: aload_2
    //   74: checkcast com/flurry/org/codehaus/jackson/map/type/MapType
    //   77: aload_3
    //   78: invokevirtual widenKey : (Ljava/lang/Class;)Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   81: astore_0
    //   82: aload #4
    //   84: aload_1
    //   85: aload_0
    //   86: invokevirtual getContentType : ()Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   89: invokevirtual findSerializationContentType : (Lcom/flurry/org/codehaus/jackson/map/introspect/Annotated;Lcom/flurry/org/codehaus/jackson/type/JavaType;)Ljava/lang/Class;
    //   92: astore_1
    //   93: aload_0
    //   94: astore_3
    //   95: aload_1
    //   96: ifnull -> 105
    //   99: aload_0
    //   100: aload_1
    //   101: invokevirtual widenContentsBy : (Ljava/lang/Class;)Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   104: astore_3
    //   105: aload_3
    //   106: areturn
    //   107: astore_0
    //   108: new java/lang/IllegalArgumentException
    //   111: dup
    //   112: new java/lang/StringBuilder
    //   115: dup
    //   116: invokespecial <init> : ()V
    //   119: ldc_w 'Failed to narrow key type '
    //   122: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: aload_2
    //   126: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   129: ldc_w ' with key-type annotation ('
    //   132: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   135: aload_3
    //   136: invokevirtual getName : ()Ljava/lang/String;
    //   139: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: ldc_w '): '
    //   145: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: aload_0
    //   149: invokevirtual getMessage : ()Ljava/lang/String;
    //   152: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   155: invokevirtual toString : ()Ljava/lang/String;
    //   158: invokespecial <init> : (Ljava/lang/String;)V
    //   161: athrow
    //   162: astore_2
    //   163: new java/lang/IllegalArgumentException
    //   166: dup
    //   167: new java/lang/StringBuilder
    //   170: dup
    //   171: invokespecial <init> : ()V
    //   174: ldc_w 'Failed to narrow content type '
    //   177: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   180: aload_0
    //   181: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   184: ldc_w ' with content-type annotation ('
    //   187: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: aload_1
    //   191: invokevirtual getName : ()Ljava/lang/String;
    //   194: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: ldc_w '): '
    //   200: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   203: aload_2
    //   204: invokevirtual getMessage : ()Ljava/lang/String;
    //   207: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   210: invokevirtual toString : ()Ljava/lang/String;
    //   213: invokespecial <init> : (Ljava/lang/String;)V
    //   216: athrow
    // Exception table:
    //   from	to	target	type
    //   73	82	107	java/lang/IllegalArgumentException
    //   99	105	162	java/lang/IllegalArgumentException
  }
  
  protected JsonSerializer buildArraySerializer(SerializationConfig paramSerializationConfig, ArrayType paramArrayType, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer paramJsonSerializer) {
    ObjectArraySerializer objectArraySerializer;
    Class<String[]> clazz = paramArrayType.getRawClass();
    if (String[].class == clazz)
      return (JsonSerializer)new StdArraySerializers.StringArraySerializer(paramBeanProperty); 
    JsonSerializer jsonSerializer2 = (JsonSerializer)_arraySerializers.get(clazz.getName());
    JsonSerializer jsonSerializer1 = jsonSerializer2;
    if (jsonSerializer2 == null)
      objectArraySerializer = new ObjectArraySerializer(paramArrayType.getContentType(), paramBoolean, paramTypeSerializer, paramBeanProperty, paramJsonSerializer); 
    return (JsonSerializer)objectArraySerializer;
  }
  
  protected JsonSerializer buildCollectionLikeSerializer(SerializationConfig paramSerializationConfig, CollectionLikeType paramCollectionLikeType, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer paramJsonSerializer) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual customSerializers : ()Ljava/lang/Iterable;
    //   4: invokeinterface iterator : ()Ljava/util/Iterator;
    //   9: astore #9
    //   11: aload #9
    //   13: invokeinterface hasNext : ()Z
    //   18: ifeq -> 57
    //   21: aload #9
    //   23: invokeinterface next : ()Ljava/lang/Object;
    //   28: checkcast com/flurry/org/codehaus/jackson/map/Serializers
    //   31: aload_1
    //   32: aload_2
    //   33: aload_3
    //   34: aload #4
    //   36: aload #6
    //   38: aload #7
    //   40: invokeinterface findCollectionLikeSerializer : (Lcom/flurry/org/codehaus/jackson/map/SerializationConfig;Lcom/flurry/org/codehaus/jackson/map/type/CollectionLikeType;Lcom/flurry/org/codehaus/jackson/map/BeanDescription;Lcom/flurry/org/codehaus/jackson/map/BeanProperty;Lcom/flurry/org/codehaus/jackson/map/TypeSerializer;Lcom/flurry/org/codehaus/jackson/map/JsonSerializer;)Lcom/flurry/org/codehaus/jackson/map/JsonSerializer;
    //   45: astore #8
    //   47: aload #8
    //   49: ifnull -> 11
    //   52: aload #8
    //   54: astore_1
    //   55: aload_1
    //   56: areturn
    //   57: aconst_null
    //   58: astore_1
    //   59: goto -> 55
  }
  
  protected JsonSerializer buildCollectionSerializer(SerializationConfig paramSerializationConfig, CollectionType paramCollectionType, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer paramJsonSerializer) {
    Iterator<Serializers> iterator = customSerializers().iterator();
    while (iterator.hasNext()) {
      JsonSerializer jsonSerializer = ((Serializers)iterator.next()).findCollectionSerializer(paramSerializationConfig, paramCollectionType, (BeanDescription)paramBasicBeanDescription, paramBeanProperty, paramTypeSerializer, paramJsonSerializer);
      if (jsonSerializer != null)
        return jsonSerializer; 
    } 
    Class<?> clazz1 = paramCollectionType.getRawClass();
    if (EnumSet.class.isAssignableFrom(clazz1))
      return buildEnumSetSerializer(paramSerializationConfig, (JavaType)paramCollectionType, paramBasicBeanDescription, paramBeanProperty, paramBoolean, paramTypeSerializer, paramJsonSerializer); 
    Class<String> clazz = paramCollectionType.getContentType().getRawClass();
    return (JsonSerializer)(isIndexedList(clazz1) ? ((clazz == String.class) ? new IndexedStringListSerializer(paramBeanProperty) : StdContainerSerializers.indexedListSerializer(paramCollectionType.getContentType(), paramBoolean, paramTypeSerializer, paramBeanProperty, paramJsonSerializer)) : ((clazz == String.class) ? new StringCollectionSerializer(paramBeanProperty) : StdContainerSerializers.collectionSerializer(paramCollectionType.getContentType(), paramBoolean, paramTypeSerializer, paramBeanProperty, paramJsonSerializer)));
  }
  
  public JsonSerializer buildContainerSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty, boolean paramBoolean) {
    MapLikeType mapLikeType;
    CollectionLikeType collectionLikeType;
    TypeSerializer typeSerializer = createTypeSerializer(paramSerializationConfig, paramJavaType.getContentType(), paramBeanProperty);
    if (typeSerializer != null) {
      paramBoolean = false;
    } else if (!paramBoolean) {
      paramBoolean = usesStaticTyping(paramSerializationConfig, paramBasicBeanDescription, typeSerializer, paramBeanProperty);
    } 
    JsonSerializer jsonSerializer = findContentSerializer(paramSerializationConfig, (Annotated)paramBasicBeanDescription.getClassInfo(), paramBeanProperty);
    if (paramJavaType.isMapLikeType()) {
      mapLikeType = (MapLikeType)paramJavaType;
      JsonSerializer jsonSerializer1 = findKeySerializer(paramSerializationConfig, (Annotated)paramBasicBeanDescription.getClassInfo(), paramBeanProperty);
      return mapLikeType.isTrueMapType() ? buildMapSerializer(paramSerializationConfig, (MapType)mapLikeType, paramBasicBeanDescription, paramBeanProperty, paramBoolean, jsonSerializer1, typeSerializer, jsonSerializer) : buildMapLikeSerializer(paramSerializationConfig, mapLikeType, paramBasicBeanDescription, paramBeanProperty, paramBoolean, jsonSerializer1, typeSerializer, jsonSerializer);
    } 
    if (mapLikeType.isCollectionLikeType()) {
      collectionLikeType = (CollectionLikeType)mapLikeType;
      return collectionLikeType.isTrueCollectionType() ? buildCollectionSerializer(paramSerializationConfig, (CollectionType)collectionLikeType, paramBasicBeanDescription, paramBeanProperty, paramBoolean, typeSerializer, jsonSerializer) : buildCollectionLikeSerializer(paramSerializationConfig, collectionLikeType, paramBasicBeanDescription, paramBeanProperty, paramBoolean, typeSerializer, jsonSerializer);
    } 
    return collectionLikeType.isArrayType() ? buildArraySerializer(paramSerializationConfig, (ArrayType)collectionLikeType, paramBasicBeanDescription, paramBeanProperty, paramBoolean, typeSerializer, jsonSerializer) : null;
  }
  
  protected JsonSerializer buildEnumMapSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer paramJsonSerializer) {
    EnumValues enumValues;
    JavaType javaType = paramJavaType.getKeyType();
    paramBasicBeanDescription = null;
    if (javaType.isEnumType())
      enumValues = EnumValues.construct(javaType.getRawClass(), paramSerializationConfig.getAnnotationIntrospector()); 
    return (JsonSerializer)new EnumMapSerializer(paramJavaType.getContentType(), paramBoolean, enumValues, paramTypeSerializer, paramBeanProperty, paramJsonSerializer);
  }
  
  protected JsonSerializer buildEnumSetSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer paramJsonSerializer) {
    paramJavaType = paramJavaType.getContentType();
    JavaType javaType = paramJavaType;
    if (!paramJavaType.isEnumType())
      javaType = null; 
    return StdContainerSerializers.enumSetSerializer(javaType, paramBeanProperty);
  }
  
  protected JsonSerializer buildIterableSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty, boolean paramBoolean) {
    JavaType javaType = paramJavaType.containedType(0);
    paramJavaType = javaType;
    if (javaType == null)
      paramJavaType = TypeFactory.unknownType(); 
    TypeSerializer typeSerializer = createTypeSerializer(paramSerializationConfig, paramJavaType, paramBeanProperty);
    return (JsonSerializer)StdContainerSerializers.iterableSerializer(paramJavaType, usesStaticTyping(paramSerializationConfig, paramBasicBeanDescription, typeSerializer, paramBeanProperty), typeSerializer, paramBeanProperty);
  }
  
  protected JsonSerializer buildIteratorSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty, boolean paramBoolean) {
    JavaType javaType = paramJavaType.containedType(0);
    paramJavaType = javaType;
    if (javaType == null)
      paramJavaType = TypeFactory.unknownType(); 
    TypeSerializer typeSerializer = createTypeSerializer(paramSerializationConfig, paramJavaType, paramBeanProperty);
    return (JsonSerializer)StdContainerSerializers.iteratorSerializer(paramJavaType, usesStaticTyping(paramSerializationConfig, paramBasicBeanDescription, typeSerializer, paramBeanProperty), typeSerializer, paramBeanProperty);
  }
  
  protected JsonSerializer buildMapLikeSerializer(SerializationConfig paramSerializationConfig, MapLikeType paramMapLikeType, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty, boolean paramBoolean, JsonSerializer paramJsonSerializer1, TypeSerializer paramTypeSerializer, JsonSerializer paramJsonSerializer2) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual customSerializers : ()Ljava/lang/Iterable;
    //   4: invokeinterface iterator : ()Ljava/util/Iterator;
    //   9: astore #10
    //   11: aload #10
    //   13: invokeinterface hasNext : ()Z
    //   18: ifeq -> 59
    //   21: aload #10
    //   23: invokeinterface next : ()Ljava/lang/Object;
    //   28: checkcast com/flurry/org/codehaus/jackson/map/Serializers
    //   31: aload_1
    //   32: aload_2
    //   33: aload_3
    //   34: aload #4
    //   36: aload #6
    //   38: aload #7
    //   40: aload #8
    //   42: invokeinterface findMapLikeSerializer : (Lcom/flurry/org/codehaus/jackson/map/SerializationConfig;Lcom/flurry/org/codehaus/jackson/map/type/MapLikeType;Lcom/flurry/org/codehaus/jackson/map/BeanDescription;Lcom/flurry/org/codehaus/jackson/map/BeanProperty;Lcom/flurry/org/codehaus/jackson/map/JsonSerializer;Lcom/flurry/org/codehaus/jackson/map/TypeSerializer;Lcom/flurry/org/codehaus/jackson/map/JsonSerializer;)Lcom/flurry/org/codehaus/jackson/map/JsonSerializer;
    //   47: astore #9
    //   49: aload #9
    //   51: ifnull -> 11
    //   54: aload #9
    //   56: astore_1
    //   57: aload_1
    //   58: areturn
    //   59: aconst_null
    //   60: astore_1
    //   61: goto -> 57
  }
  
  protected JsonSerializer buildMapSerializer(SerializationConfig paramSerializationConfig, MapType paramMapType, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty, boolean paramBoolean, JsonSerializer paramJsonSerializer1, TypeSerializer paramTypeSerializer, JsonSerializer paramJsonSerializer2) {
    Iterator<Serializers> iterator = customSerializers().iterator();
    while (iterator.hasNext()) {
      JsonSerializer jsonSerializer = ((Serializers)iterator.next()).findMapSerializer(paramSerializationConfig, paramMapType, (BeanDescription)paramBasicBeanDescription, paramBeanProperty, paramJsonSerializer1, paramTypeSerializer, paramJsonSerializer2);
      if (jsonSerializer != null)
        return jsonSerializer; 
    } 
    return (JsonSerializer)(EnumMap.class.isAssignableFrom(paramMapType.getRawClass()) ? buildEnumMapSerializer(paramSerializationConfig, (JavaType)paramMapType, paramBasicBeanDescription, paramBeanProperty, paramBoolean, paramTypeSerializer, paramJsonSerializer2) : MapSerializer.construct(paramSerializationConfig.getAnnotationIntrospector().findPropertiesToIgnore(paramBasicBeanDescription.getClassInfo()), (JavaType)paramMapType, paramBoolean, paramTypeSerializer, paramBeanProperty, paramJsonSerializer1, paramJsonSerializer2));
  }
  
  public abstract JsonSerializer createSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, BeanProperty paramBeanProperty);
  
  public TypeSerializer createTypeSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, BeanProperty paramBeanProperty) {
    Collection collection;
    AnnotatedClass annotatedClass = ((BasicBeanDescription)paramSerializationConfig.introspectClassAnnotations(paramJavaType.getRawClass())).getClassInfo();
    AnnotationIntrospector annotationIntrospector = paramSerializationConfig.getAnnotationIntrospector();
    TypeResolverBuilder typeResolverBuilder = annotationIntrospector.findTypeResolver((MapperConfig)paramSerializationConfig, annotatedClass, paramJavaType);
    if (typeResolverBuilder == null) {
      typeResolverBuilder = paramSerializationConfig.getDefaultTyper(paramJavaType);
      annotatedClass = null;
    } else {
      collection = paramSerializationConfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedClass, (MapperConfig)paramSerializationConfig, annotationIntrospector);
    } 
    return (typeResolverBuilder == null) ? null : typeResolverBuilder.buildTypeSerializer(paramSerializationConfig, paramJavaType, collection, paramBeanProperty);
  }
  
  protected abstract Iterable customSerializers();
  
  public final JsonSerializer findSerializerByAddonType(SerializationConfig paramSerializationConfig, JavaType paramJavaType, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty, boolean paramBoolean) {
    Class<?> clazz = paramJavaType.getRawClass();
    return (JsonSerializer)(Iterator.class.isAssignableFrom(clazz) ? buildIteratorSerializer(paramSerializationConfig, paramJavaType, paramBasicBeanDescription, paramBeanProperty, paramBoolean) : (Iterable.class.isAssignableFrom(clazz) ? buildIterableSerializer(paramSerializationConfig, paramJavaType, paramBasicBeanDescription, paramBeanProperty, paramBoolean) : (CharSequence.class.isAssignableFrom(clazz) ? ToStringSerializer.instance : null)));
  }
  
  public final JsonSerializer findSerializerByLookup(JavaType paramJavaType, SerializationConfig paramSerializationConfig, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty, boolean paramBoolean) {
    String str = paramJavaType.getRawClass().getName();
    JsonSerializer jsonSerializer = (JsonSerializer)_concrete.get(str);
    if (jsonSerializer == null) {
      Class<JsonSerializer> clazz = (Class)_concreteLazy.get(str);
      if (clazz != null) {
        try {
          jsonSerializer = clazz.newInstance();
        } catch (Exception exception) {
          throw new IllegalStateException("Failed to instantiate standard serializer (of type " + clazz.getName() + "): " + exception.getMessage(), exception);
        } 
        return (JsonSerializer)exception;
      } 
      jsonSerializer = null;
    } 
    return jsonSerializer;
  }
  
  public final JsonSerializer findSerializerByPrimaryType(JavaType paramJavaType, SerializationConfig paramSerializationConfig, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty, boolean paramBoolean) {
    Class<?> clazz = paramJavaType.getRawClass();
    if (JsonSerializable.class.isAssignableFrom(clazz))
      return (JsonSerializer)(JsonSerializableWithType.class.isAssignableFrom(clazz) ? SerializableWithTypeSerializer.instance : SerializableSerializer.instance); 
    AnnotatedMethod annotatedMethod = paramBasicBeanDescription.findJsonValueMethod();
    if (annotatedMethod != null) {
      Method method = annotatedMethod.getAnnotated();
      if (paramSerializationConfig.isEnabled(SerializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS))
        ClassUtil.checkAndFixAccess(method); 
      return (JsonSerializer)new JsonValueSerializer(method, findSerializerFromAnnotation(paramSerializationConfig, (Annotated)annotatedMethod, paramBeanProperty), paramBeanProperty);
    } 
    if (InetAddress.class.isAssignableFrom(clazz))
      return (JsonSerializer)InetAddressSerializer.instance; 
    if (TimeZone.class.isAssignableFrom(clazz))
      return (JsonSerializer)TimeZoneSerializer.instance; 
    if (Charset.class.isAssignableFrom(clazz))
      return (JsonSerializer)ToStringSerializer.instance; 
    JsonSerializer jsonSerializer2 = this.optionalHandlers.findSerializer(paramSerializationConfig, paramJavaType);
    JsonSerializer jsonSerializer1 = jsonSerializer2;
    if (jsonSerializer2 == null) {
      if (Number.class.isAssignableFrom(clazz))
        return (JsonSerializer)StdSerializers$NumberSerializer.instance; 
      if (Enum.class.isAssignableFrom(clazz))
        return (JsonSerializer)EnumSerializer.construct(clazz, paramSerializationConfig, paramBasicBeanDescription); 
      if (Calendar.class.isAssignableFrom(clazz))
        return (JsonSerializer)CalendarSerializer.instance; 
      if (Date.class.isAssignableFrom(clazz))
        return (JsonSerializer)DateSerializer.instance; 
      jsonSerializer1 = null;
    } 
    return jsonSerializer1;
  }
  
  protected JsonSerializer findSerializerFromAnnotation(SerializationConfig paramSerializationConfig, Annotated paramAnnotated, BeanProperty paramBeanProperty) {
    Object object2 = paramSerializationConfig.getAnnotationIntrospector().findSerializer(paramAnnotated);
    if (object2 == null)
      return null; 
    if (object2 instanceof JsonSerializer) {
      object2 = object2;
      object1 = object2;
      if (object2 instanceof ContextualSerializer)
        object1 = ((ContextualSerializer)object2).createContextual(paramSerializationConfig, paramBeanProperty); 
      return (JsonSerializer)object1;
    } 
    if (!(object2 instanceof Class))
      throw new IllegalStateException("AnnotationIntrospector returned value of type " + object2.getClass().getName() + "; expected type JsonSerializer or Class<JsonSerializer> instead"); 
    object2 = object2;
    if (!JsonSerializer.class.isAssignableFrom((Class<?>)object2))
      throw new IllegalStateException("AnnotationIntrospector returned Class " + object2.getName() + "; expected Class<JsonSerializer>"); 
    object2 = paramSerializationConfig.serializerInstance((Annotated)object1, (Class)object2);
    Object object1 = object2;
    if (object2 instanceof ContextualSerializer)
      object1 = ((ContextualSerializer)object2).createContextual(paramSerializationConfig, paramBeanProperty); 
    return (JsonSerializer)object1;
  }
  
  public final JsonSerializer getNullSerializer() {
    return (JsonSerializer)NullSerializer.instance;
  }
  
  protected boolean isIndexedList(Class<?> paramClass) {
    return RandomAccess.class.isAssignableFrom(paramClass);
  }
  
  protected JavaType modifyTypeByAnnotation(SerializationConfig paramSerializationConfig, Annotated paramAnnotated, JavaType paramJavaType) {
    Class clazz = paramSerializationConfig.getAnnotationIntrospector().findSerializationType(paramAnnotated);
    JavaType javaType = paramJavaType;
    if (clazz != null)
      try {
        javaType = paramJavaType.widenBy(clazz);
        return modifySecondaryTypesByAnnotation(paramSerializationConfig, paramAnnotated, javaType);
      } catch (IllegalArgumentException illegalArgumentException) {
        throw new IllegalArgumentException("Failed to widen type " + paramJavaType + " with concrete-type annotation (value " + clazz.getName() + "), method '" + paramAnnotated.getName() + "': " + illegalArgumentException.getMessage());
      }  
    return modifySecondaryTypesByAnnotation((SerializationConfig)illegalArgumentException, paramAnnotated, javaType);
  }
  
  protected boolean usesStaticTyping(SerializationConfig paramSerializationConfig, BasicBeanDescription paramBasicBeanDescription, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty) {
    boolean bool2 = false;
    if (paramTypeSerializer != null)
      return bool2; 
    AnnotationIntrospector annotationIntrospector = paramSerializationConfig.getAnnotationIntrospector();
    JsonSerialize.Typing typing = annotationIntrospector.findSerializationTyping((Annotated)paramBasicBeanDescription.getClassInfo());
    if (typing != null) {
      if (typing == JsonSerialize.Typing.STATIC)
        return true; 
    } else if (paramSerializationConfig.isEnabled(SerializationConfig.Feature.USE_STATIC_TYPING)) {
      return true;
    } 
    boolean bool1 = bool2;
    if (paramBeanProperty != null) {
      JavaType javaType = paramBeanProperty.getType();
      bool1 = bool2;
      if (javaType.isContainerType()) {
        if (annotationIntrospector.findSerializationContentType((Annotated)paramBeanProperty.getMember(), paramBeanProperty.getType()) != null)
          return true; 
        bool1 = bool2;
        if (javaType instanceof MapType) {
          bool1 = bool2;
          if (annotationIntrospector.findSerializationKeyType((Annotated)paramBeanProperty.getMember(), paramBeanProperty.getType()) != null)
            bool1 = true; 
        } 
      } 
    } 
    return bool1;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\BasicSerializerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */