package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.flurry.org.codehaus.jackson.map.BeanDescription;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.DeserializerProvider;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.ResolvableDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.annotate.JsonCachable;
import com.flurry.org.codehaus.jackson.map.deser.impl.BeanPropertyMap;
import com.flurry.org.codehaus.jackson.map.deser.impl.CreatorCollector;
import com.flurry.org.codehaus.jackson.map.deser.impl.ExternalTypeHandler;
import com.flurry.org.codehaus.jackson.map.deser.impl.PropertyBasedCreator;
import com.flurry.org.codehaus.jackson.map.deser.impl.PropertyValueBuffer;
import com.flurry.org.codehaus.jackson.map.deser.impl.UnwrappedPropertyHandler;
import com.flurry.org.codehaus.jackson.map.deser.impl.ValueInjector;
import com.flurry.org.codehaus.jackson.map.deser.std.ContainerDeserializerBase;
import com.flurry.org.codehaus.jackson.map.deser.std.StdDeserializer;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedClass;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedWithParams;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import com.flurry.org.codehaus.jackson.type.JavaType;
import com.flurry.org.codehaus.jackson.util.TokenBuffer;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@JsonCachable
public class BeanDeserializer extends StdDeserializer implements ResolvableDeserializer {
  protected SettableAnyProperty _anySetter;
  
  protected final Map _backRefs;
  
  protected final BeanPropertyMap _beanProperties;
  
  protected final JavaType _beanType;
  
  protected JsonDeserializer _delegateDeserializer;
  
  protected ExternalTypeHandler _externalTypeIdHandler;
  
  protected final AnnotatedClass _forClass;
  
  protected final HashSet _ignorableProps;
  
  protected final boolean _ignoreAllUnknown;
  
  protected final ValueInjector[] _injectables;
  
  protected boolean _nonStandardCreation;
  
  protected final BeanProperty _property;
  
  protected final PropertyBasedCreator _propertyBasedCreator;
  
  protected HashMap _subDeserializers;
  
  protected UnwrappedPropertyHandler _unwrappedPropertyHandler;
  
  protected final ValueInstantiator _valueInstantiator;
  
  public BeanDeserializer(BeanDescription paramBeanDescription, BeanProperty paramBeanProperty, ValueInstantiator paramValueInstantiator, BeanPropertyMap paramBeanPropertyMap, Map paramMap, HashSet paramHashSet, boolean paramBoolean, SettableAnyProperty paramSettableAnyProperty, List paramList) {
    this(paramBeanDescription.getClassInfo(), paramBeanDescription.getType(), paramBeanProperty, paramValueInstantiator, paramBeanPropertyMap, paramMap, paramHashSet, paramBoolean, paramSettableAnyProperty, paramList);
  }
  
  protected BeanDeserializer(BeanDeserializer paramBeanDeserializer) {
    this(paramBeanDeserializer, paramBeanDeserializer._ignoreAllUnknown);
  }
  
  protected BeanDeserializer(BeanDeserializer paramBeanDeserializer, boolean paramBoolean) {
    super(paramBeanDeserializer._beanType);
    this._forClass = paramBeanDeserializer._forClass;
    this._beanType = paramBeanDeserializer._beanType;
    this._property = paramBeanDeserializer._property;
    this._valueInstantiator = paramBeanDeserializer._valueInstantiator;
    this._delegateDeserializer = paramBeanDeserializer._delegateDeserializer;
    this._propertyBasedCreator = paramBeanDeserializer._propertyBasedCreator;
    this._beanProperties = paramBeanDeserializer._beanProperties;
    this._backRefs = paramBeanDeserializer._backRefs;
    this._ignorableProps = paramBeanDeserializer._ignorableProps;
    this._ignoreAllUnknown = paramBoolean;
    this._anySetter = paramBeanDeserializer._anySetter;
    this._injectables = paramBeanDeserializer._injectables;
    this._nonStandardCreation = paramBeanDeserializer._nonStandardCreation;
    this._unwrappedPropertyHandler = paramBeanDeserializer._unwrappedPropertyHandler;
  }
  
  protected BeanDeserializer(AnnotatedClass paramAnnotatedClass, JavaType paramJavaType, BeanProperty paramBeanProperty, ValueInstantiator paramValueInstantiator, BeanPropertyMap paramBeanPropertyMap, Map paramMap, HashSet paramHashSet, boolean paramBoolean, SettableAnyProperty paramSettableAnyProperty, List paramList) {
    super(paramJavaType);
    ValueInjector[] arrayOfValueInjector;
    this._forClass = paramAnnotatedClass;
    this._beanType = paramJavaType;
    this._property = paramBeanProperty;
    this._valueInstantiator = paramValueInstantiator;
    if (paramValueInstantiator.canCreateFromObjectWith()) {
      this._propertyBasedCreator = new PropertyBasedCreator(paramValueInstantiator);
    } else {
      this._propertyBasedCreator = null;
    } 
    this._beanProperties = paramBeanPropertyMap;
    this._backRefs = paramMap;
    this._ignorableProps = paramHashSet;
    this._ignoreAllUnknown = paramBoolean;
    this._anySetter = paramSettableAnyProperty;
    paramAnnotatedClass = annotatedClass;
    if (paramList != null)
      if (paramList.isEmpty()) {
        paramAnnotatedClass = annotatedClass;
      } else {
        arrayOfValueInjector = (ValueInjector[])paramList.toArray((Object[])new ValueInjector[paramList.size()]);
      }  
    this._injectables = arrayOfValueInjector;
    if (paramValueInstantiator.canCreateUsingDelegate() || this._propertyBasedCreator != null || !paramValueInstantiator.canCreateUsingDefault() || this._unwrappedPropertyHandler != null) {
      paramBoolean = true;
    } else {
      paramBoolean = false;
    } 
    this._nonStandardCreation = paramBoolean;
  }
  
  @Deprecated
  public BeanDeserializer(AnnotatedClass paramAnnotatedClass, JavaType paramJavaType, BeanProperty paramBeanProperty, CreatorCollector paramCreatorCollector, BeanPropertyMap paramBeanPropertyMap, Map paramMap, HashSet paramHashSet, boolean paramBoolean, SettableAnyProperty paramSettableAnyProperty) {
    this(paramAnnotatedClass, paramJavaType, paramBeanProperty, paramCreatorCollector.constructValueInstantiator(null), paramBeanPropertyMap, paramMap, paramHashSet, paramBoolean, paramSettableAnyProperty, null);
  }
  
  private final void _handleUnknown(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, String paramString) {
    if (this._ignorableProps != null && this._ignorableProps.contains(paramString)) {
      paramJsonParser.skipChildren();
      return;
    } 
    if (this._anySetter != null) {
      try {
        this._anySetter.deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject, paramString);
      } catch (Exception exception) {
        wrapAndThrow(exception, paramObject, paramString, paramDeserializationContext);
      } 
      return;
    } 
    handleUnknownProperty((JsonParser)exception, paramDeserializationContext, paramObject, paramString);
  }
  
  protected final Object _deserializeUsingPropertyBased(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    Exception exception = null;
    PropertyBasedCreator propertyBasedCreator = this._propertyBasedCreator;
    PropertyValueBuffer propertyValueBuffer = propertyBasedCreator.startBuilding(paramJsonParser, paramDeserializationContext);
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    Object object2 = null;
    while (true) {
      if (jsonToken == JsonToken.FIELD_NAME) {
        Object object3;
        JsonToken jsonToken2;
        String str = paramJsonParser.getCurrentName();
        paramJsonParser.nextToken();
        SettableBeanProperty settableBeanProperty = propertyBasedCreator.findCreatorProperty(str);
        if (settableBeanProperty != null) {
          Object object4 = settableBeanProperty.deserialize(paramJsonParser, paramDeserializationContext);
          jsonToken = (JsonToken)object2;
          if (propertyValueBuffer.assignParameter(settableBeanProperty.getPropertyIndex(), object4)) {
            paramJsonParser.nextToken();
            try {
              object = propertyBasedCreator.build(propertyValueBuffer);
              if (object.getClass() != this._beanType.getRawClass())
                return handlePolymorphic(paramJsonParser, paramDeserializationContext, object, (TokenBuffer)object2); 
            } catch (Exception exception1) {
              wrapAndThrow(exception1, this._beanType.getRawClass(), str, paramDeserializationContext);
              object = object2;
              jsonToken2 = paramJsonParser.nextToken();
              object2 = object;
              object = jsonToken2;
              continue;
            } 
            if (object2 != null) {
              object3 = handleUnknownProperties(paramDeserializationContext, object, (TokenBuffer)object2);
            } else {
              object3 = object;
            } 
            return deserialize(paramJsonParser, paramDeserializationContext, object3);
          } 
        } else {
          object = this._beanProperties.find((String)jsonToken2);
          if (object != null) {
            propertyValueBuffer.bufferProperty((SettableBeanProperty)object, object.deserialize(paramJsonParser, paramDeserializationContext));
            Object object4 = object3;
          } else if (this._ignorableProps != null && this._ignorableProps.contains(jsonToken2)) {
            paramJsonParser.skipChildren();
            Object object4 = object3;
          } else if (this._anySetter != null) {
            propertyValueBuffer.bufferAnyProperty(this._anySetter, (String)jsonToken2, this._anySetter.deserialize(paramJsonParser, paramDeserializationContext));
            Object object4 = object3;
          } else {
            object = object3;
            if (object3 == null)
              object = new TokenBuffer(paramJsonParser.getCodec()); 
            object.writeFieldName((String)jsonToken2);
            object.copyCurrentStructure(paramJsonParser);
          } 
        } 
      } else {
        break;
      } 
      JsonToken jsonToken1 = paramJsonParser.nextToken();
      object2 = object;
      Object object = jsonToken1;
    } 
    try {
      object1 = propertyBasedCreator.build(propertyValueBuffer);
      if (object2 != null) {
        if (object1.getClass() != this._beanType.getRawClass())
          return handlePolymorphic(null, paramDeserializationContext, object1, (TokenBuffer)object2); 
        object1 = handleUnknownProperties(paramDeserializationContext, object1, (TokenBuffer)object2);
      } 
    } catch (Exception object1) {
      wrapInstantiationProblem((Throwable)object1, paramDeserializationContext);
      object1 = exception;
    } 
    return object1;
  }
  
  protected JsonDeserializer _findSubclassDeserializer(DeserializationContext paramDeserializationContext, Object paramObject, TokenBuffer paramTokenBuffer) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield _subDeserializers : Ljava/util/HashMap;
    //   6: ifnonnull -> 19
    //   9: aconst_null
    //   10: astore_3
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_3
    //   14: ifnull -> 56
    //   17: aload_3
    //   18: areturn
    //   19: aload_0
    //   20: getfield _subDeserializers : Ljava/util/HashMap;
    //   23: astore_3
    //   24: new com/flurry/org/codehaus/jackson/map/type/ClassKey
    //   27: astore #4
    //   29: aload #4
    //   31: aload_2
    //   32: invokevirtual getClass : ()Ljava/lang/Class;
    //   35: invokespecial <init> : (Ljava/lang/Class;)V
    //   38: aload_3
    //   39: aload #4
    //   41: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   44: checkcast com/flurry/org/codehaus/jackson/map/JsonDeserializer
    //   47: astore_3
    //   48: goto -> 11
    //   51: astore_1
    //   52: aload_0
    //   53: monitorexit
    //   54: aload_1
    //   55: athrow
    //   56: aload_1
    //   57: invokevirtual getDeserializerProvider : ()Lcom/flurry/org/codehaus/jackson/map/DeserializerProvider;
    //   60: astore #4
    //   62: aload #4
    //   64: ifnull -> 17
    //   67: aload_1
    //   68: aload_2
    //   69: invokevirtual getClass : ()Ljava/lang/Class;
    //   72: invokevirtual constructType : (Ljava/lang/Class;)Lcom/flurry/org/codehaus/jackson/type/JavaType;
    //   75: astore_3
    //   76: aload #4
    //   78: aload_1
    //   79: invokevirtual getConfig : ()Lcom/flurry/org/codehaus/jackson/map/DeserializationConfig;
    //   82: aload_3
    //   83: aload_0
    //   84: getfield _property : Lcom/flurry/org/codehaus/jackson/map/BeanProperty;
    //   87: invokevirtual findValueDeserializer : (Lcom/flurry/org/codehaus/jackson/map/DeserializationConfig;Lcom/flurry/org/codehaus/jackson/type/JavaType;Lcom/flurry/org/codehaus/jackson/map/BeanProperty;)Lcom/flurry/org/codehaus/jackson/map/JsonDeserializer;
    //   90: astore_1
    //   91: aload_1
    //   92: astore_3
    //   93: aload_1
    //   94: ifnull -> 17
    //   97: aload_0
    //   98: monitorenter
    //   99: aload_0
    //   100: getfield _subDeserializers : Ljava/util/HashMap;
    //   103: ifnonnull -> 119
    //   106: new java/util/HashMap
    //   109: astore_3
    //   110: aload_3
    //   111: invokespecial <init> : ()V
    //   114: aload_0
    //   115: aload_3
    //   116: putfield _subDeserializers : Ljava/util/HashMap;
    //   119: aload_0
    //   120: getfield _subDeserializers : Ljava/util/HashMap;
    //   123: astore_3
    //   124: new com/flurry/org/codehaus/jackson/map/type/ClassKey
    //   127: astore #4
    //   129: aload #4
    //   131: aload_2
    //   132: invokevirtual getClass : ()Ljava/lang/Class;
    //   135: invokespecial <init> : (Ljava/lang/Class;)V
    //   138: aload_3
    //   139: aload #4
    //   141: aload_1
    //   142: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   145: pop
    //   146: aload_0
    //   147: monitorexit
    //   148: aload_1
    //   149: astore_3
    //   150: goto -> 17
    //   153: astore_1
    //   154: aload_0
    //   155: monitorexit
    //   156: aload_1
    //   157: athrow
    // Exception table:
    //   from	to	target	type
    //   2	9	51	finally
    //   11	13	51	finally
    //   19	48	51	finally
    //   52	54	51	finally
    //   99	119	153	finally
    //   119	148	153	finally
    //   154	156	153	finally
  }
  
  protected SettableBeanProperty _resolveInnerClassValuedProperty(DeserializationConfig paramDeserializationConfig, SettableBeanProperty paramSettableBeanProperty) {
    JsonDeserializer jsonDeserializer = paramSettableBeanProperty.getValueDeserializer();
    SettableBeanProperty settableBeanProperty = paramSettableBeanProperty;
    if (jsonDeserializer instanceof BeanDeserializer) {
      settableBeanProperty = paramSettableBeanProperty;
      if (!((BeanDeserializer)jsonDeserializer).getValueInstantiator().canCreateUsingDefault()) {
        Class clazz2 = paramSettableBeanProperty.getType().getRawClass();
        Class clazz1 = ClassUtil.getOuterClass(clazz2);
        settableBeanProperty = paramSettableBeanProperty;
        if (clazz1 != null) {
          settableBeanProperty = paramSettableBeanProperty;
          if (clazz1 == this._beanType.getRawClass()) {
            Constructor[] arrayOfConstructor = (Constructor[])clazz2.getConstructors();
            int i = arrayOfConstructor.length;
            for (byte b = 0;; b++) {
              settableBeanProperty = paramSettableBeanProperty;
              if (b < i) {
                Constructor constructor = arrayOfConstructor[b];
                Class[] arrayOfClass = constructor.getParameterTypes();
                if (arrayOfClass.length == 1 && arrayOfClass[0] == clazz1) {
                  if (paramDeserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS))
                    ClassUtil.checkAndFixAccess(constructor); 
                  return new SettableBeanProperty$InnerClassProperty(paramSettableBeanProperty, constructor);
                } 
              } else {
                return settableBeanProperty;
              } 
            } 
          } 
        } 
      } 
    } 
    return settableBeanProperty;
  }
  
  protected SettableBeanProperty _resolveManagedReferenceProperty(DeserializationConfig paramDeserializationConfig, SettableBeanProperty paramSettableBeanProperty) {
    String str = paramSettableBeanProperty.getManagedReferenceName();
    if (str != null) {
      SettableBeanProperty settableBeanProperty;
      JsonDeserializer jsonDeserializer = paramSettableBeanProperty.getValueDeserializer();
      boolean bool = false;
      if (jsonDeserializer instanceof BeanDeserializer) {
        settableBeanProperty = ((BeanDeserializer)jsonDeserializer).findBackReference(str);
      } else if (settableBeanProperty instanceof ContainerDeserializerBase) {
        JsonDeserializer jsonDeserializer1 = ((ContainerDeserializerBase)settableBeanProperty).getContentDeserializer();
        if (!(jsonDeserializer1 instanceof BeanDeserializer))
          throw new IllegalArgumentException("Can not handle managed/back reference '" + str + "': value deserializer is of type ContainerDeserializerBase, but content type is not handled by a BeanDeserializer " + " (instead it's of type " + jsonDeserializer1.getClass().getName() + ")"); 
        settableBeanProperty = ((BeanDeserializer)jsonDeserializer1).findBackReference(str);
        bool = true;
      } else {
        if (settableBeanProperty instanceof AbstractDeserializer)
          throw new IllegalArgumentException("Can not handle managed/back reference for abstract types (property " + this._beanType.getRawClass().getName() + "." + paramSettableBeanProperty.getName() + ")"); 
        throw new IllegalArgumentException("Can not handle managed/back reference '" + str + "': type for value deserializer is not BeanDeserializer or ContainerDeserializerBase, but " + settableBeanProperty.getClass().getName());
      } 
      if (settableBeanProperty == null)
        throw new IllegalArgumentException("Can not handle managed/back reference '" + str + "': no back reference property found from type " + paramSettableBeanProperty.getType()); 
      JavaType javaType2 = this._beanType;
      JavaType javaType1 = settableBeanProperty.getType();
      if (!javaType1.getRawClass().isAssignableFrom(javaType2.getRawClass()))
        throw new IllegalArgumentException("Can not handle managed/back reference '" + str + "': back reference type (" + javaType1.getRawClass().getName() + ") not compatible with managed type (" + javaType2.getRawClass().getName() + ")"); 
      paramSettableBeanProperty = new SettableBeanProperty$ManagedReferenceProperty(str, paramSettableBeanProperty, settableBeanProperty, this._forClass.getAnnotations(), bool);
    } 
    return paramSettableBeanProperty;
  }
  
  protected SettableBeanProperty _resolveUnwrappedProperty(DeserializationConfig paramDeserializationConfig, SettableBeanProperty paramSettableBeanProperty) {
    AnnotatedMember annotatedMember = paramSettableBeanProperty.getMember();
    if (annotatedMember != null && paramDeserializationConfig.getAnnotationIntrospector().shouldUnwrapProperty(annotatedMember) == Boolean.TRUE) {
      JsonDeserializer jsonDeserializer1 = paramSettableBeanProperty.getValueDeserializer();
      JsonDeserializer jsonDeserializer2 = jsonDeserializer1.unwrappingDeserializer();
      if (jsonDeserializer2 != jsonDeserializer1 && jsonDeserializer2 != null)
        return paramSettableBeanProperty.withValueDeserializer(jsonDeserializer2); 
    } 
    return null;
  }
  
  public final Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken == JsonToken.START_OBJECT) {
      paramJsonParser.nextToken();
      return deserializeFromObject(paramJsonParser, paramDeserializationContext);
    } 
    switch (BeanDeserializer$1.$SwitchMap$org$codehaus$jackson$JsonToken[jsonToken.ordinal()]) {
      default:
        throw paramDeserializationContext.mappingException(getBeanClass());
      case 1:
        return deserializeFromString(paramJsonParser, paramDeserializationContext);
      case 2:
        return deserializeFromNumber(paramJsonParser, paramDeserializationContext);
      case 3:
        return deserializeFromDouble(paramJsonParser, paramDeserializationContext);
      case 4:
        return paramJsonParser.getEmbeddedObject();
      case 5:
      case 6:
        return deserializeFromBoolean(paramJsonParser, paramDeserializationContext);
      case 7:
        return deserializeFromArray(paramJsonParser, paramDeserializationContext);
      case 8:
      case 9:
        break;
    } 
    return deserializeFromObject(paramJsonParser, paramDeserializationContext);
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject) {
    if (this._injectables != null)
      injectValues(paramDeserializationContext, paramObject); 
    if (this._unwrappedPropertyHandler != null)
      return deserializeWithUnwrapped(paramJsonParser, paramDeserializationContext, paramObject); 
    if (this._externalTypeIdHandler != null)
      return deserializeWithExternalTypeId(paramJsonParser, paramDeserializationContext, paramObject); 
    JsonToken jsonToken2 = paramJsonParser.getCurrentToken();
    JsonToken jsonToken1 = jsonToken2;
    if (jsonToken2 == JsonToken.START_OBJECT)
      jsonToken1 = paramJsonParser.nextToken(); 
    while (true) {
      object = paramObject;
      if (jsonToken1 == JsonToken.FIELD_NAME) {
        String str = paramJsonParser.getCurrentName();
        paramJsonParser.nextToken();
        object = this._beanProperties.find(str);
        if (object != null) {
          try {
            object.deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject);
          } catch (Exception object) {
            wrapAndThrow((Throwable)object, paramObject, str, paramDeserializationContext);
          } 
        } else if (this._ignorableProps != null && this._ignorableProps.contains(str)) {
          paramJsonParser.skipChildren();
        } else if (this._anySetter != null) {
          this._anySetter.deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject, str);
        } else {
          handleUnknownProperty(paramJsonParser, paramDeserializationContext, paramObject, str);
        } 
        JsonToken jsonToken = paramJsonParser.nextToken();
        continue;
      } 
      return object;
    } 
  }
  
  public Object deserializeFromArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (this._delegateDeserializer != null)
      try {
        Object object = this._valueInstantiator.createUsingDelegate(this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
        if (this._injectables != null)
          injectValues(paramDeserializationContext, object); 
        return object;
      } catch (Exception exception) {
        wrapInstantiationProblem(exception, paramDeserializationContext);
      }  
    throw paramDeserializationContext.mappingException(getBeanClass());
  }
  
  public Object deserializeFromBoolean(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    boolean bool;
    if (this._delegateDeserializer != null && !this._valueInstantiator.canCreateFromBoolean()) {
      Object object = this._valueInstantiator.createUsingDelegate(this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
      null = object;
      if (this._injectables != null) {
        injectValues(paramDeserializationContext, object);
        null = object;
      } 
      return null;
    } 
    if (null.getCurrentToken() == JsonToken.VALUE_TRUE) {
      bool = true;
    } else {
      bool = false;
    } 
    return this._valueInstantiator.createFromBoolean(bool);
  }
  
  public Object deserializeFromDouble(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    switch (BeanDeserializer$1.$SwitchMap$org$codehaus$jackson$JsonParser$NumberType[paramJsonParser.getNumberType().ordinal()]) {
      default:
        if (this._delegateDeserializer != null)
          return this._valueInstantiator.createUsingDelegate(this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext)); 
        throw paramDeserializationContext.instantiationException(getBeanClass(), "no suitable creator method found to deserialize from JSON floating-point number");
      case 3:
      case 4:
        break;
    } 
    if (this._delegateDeserializer != null && !this._valueInstantiator.canCreateFromDouble()) {
      Object object = this._valueInstantiator.createUsingDelegate(this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
      null = object;
      if (this._injectables != null) {
        injectValues(paramDeserializationContext, object);
        null = object;
      } 
      return null;
    } 
    return this._valueInstantiator.createFromDouble(null.getDoubleValue());
  }
  
  public Object deserializeFromNumber(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    switch (BeanDeserializer$1.$SwitchMap$org$codehaus$jackson$JsonParser$NumberType[paramJsonParser.getNumberType().ordinal()]) {
      default:
        if (this._delegateDeserializer != null) {
          Object object = this._valueInstantiator.createUsingDelegate(this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
          null = object;
          if (this._injectables != null) {
            injectValues(paramDeserializationContext, object);
            null = object;
          } 
          return null;
        } 
        throw paramDeserializationContext.instantiationException(getBeanClass(), "no suitable creator method found to deserialize from JSON integer number");
      case 1:
        if (this._delegateDeserializer != null && !this._valueInstantiator.canCreateFromInt()) {
          Object object = this._valueInstantiator.createUsingDelegate(this._delegateDeserializer.deserialize((JsonParser)null, paramDeserializationContext));
          null = object;
          if (this._injectables != null) {
            injectValues(paramDeserializationContext, object);
            null = object;
          } 
          return null;
        } 
        return this._valueInstantiator.createFromInt(null.getIntValue());
      case 2:
        break;
    } 
    if (this._delegateDeserializer != null && !this._valueInstantiator.canCreateFromInt()) {
      Object object = this._valueInstantiator.createUsingDelegate(this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
      null = object;
      if (this._injectables != null) {
        injectValues(paramDeserializationContext, object);
        null = object;
      } 
      return null;
    } 
    return this._valueInstantiator.createFromLong(null.getLongValue());
  }
  
  public Object deserializeFromObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (this._nonStandardCreation)
      return (this._unwrappedPropertyHandler != null) ? deserializeWithUnwrapped(paramJsonParser, paramDeserializationContext) : ((this._externalTypeIdHandler != null) ? deserializeWithExternalTypeId(paramJsonParser, paramDeserializationContext) : deserializeFromObjectUsingNonDefault(paramJsonParser, paramDeserializationContext)); 
    Object object = this._valueInstantiator.createUsingDefault();
    if (this._injectables != null)
      injectValues(paramDeserializationContext, object); 
    while (true) {
      Object object1 = object;
      if (paramJsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
        object1 = paramJsonParser.getCurrentName();
        paramJsonParser.nextToken();
        SettableBeanProperty settableBeanProperty = this._beanProperties.find((String)object1);
        if (settableBeanProperty != null) {
          try {
            settableBeanProperty.deserializeAndSet(paramJsonParser, paramDeserializationContext, object);
          } catch (Exception exception) {
            wrapAndThrow(exception, object, (String)object1, paramDeserializationContext);
          } 
        } else {
          _handleUnknown(paramJsonParser, paramDeserializationContext, object, (String)object1);
        } 
        paramJsonParser.nextToken();
        continue;
      } 
      return object1;
    } 
  }
  
  protected Object deserializeFromObjectUsingNonDefault(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (this._delegateDeserializer != null)
      return this._valueInstantiator.createUsingDelegate(this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext)); 
    if (this._propertyBasedCreator != null)
      return _deserializeUsingPropertyBased(paramJsonParser, paramDeserializationContext); 
    if (this._beanType.isAbstract())
      throw JsonMappingException.from(paramJsonParser, "Can not instantiate abstract type " + this._beanType + " (need to add/enable type information?)"); 
    throw JsonMappingException.from(paramJsonParser, "No suitable constructor found for type " + this._beanType + ": can not instantiate from JSON object (need to add/enable type information?)");
  }
  
  public Object deserializeFromString(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (this._delegateDeserializer != null && !this._valueInstantiator.canCreateFromString()) {
      Object object = this._valueInstantiator.createUsingDelegate(this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
      null = object;
      if (this._injectables != null) {
        injectValues(paramDeserializationContext, object);
        null = object;
      } 
      return null;
    } 
    return this._valueInstantiator.createFromString(null.getText());
  }
  
  protected Object deserializeUsingPropertyBasedWithExternalTypeId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    ExternalTypeHandler externalTypeHandler = this._externalTypeIdHandler.start();
    PropertyBasedCreator propertyBasedCreator = this._propertyBasedCreator;
    PropertyValueBuffer propertyValueBuffer = propertyBasedCreator.startBuilding(paramJsonParser, paramDeserializationContext);
    TokenBuffer tokenBuffer = new TokenBuffer(paramJsonParser.getCodec());
    tokenBuffer.writeStartObject();
    for (JsonToken jsonToken = paramJsonParser.getCurrentToken();; jsonToken = paramJsonParser.nextToken()) {
      if (jsonToken == JsonToken.FIELD_NAME) {
        String str = paramJsonParser.getCurrentName();
        paramJsonParser.nextToken();
        SettableBeanProperty settableBeanProperty = propertyBasedCreator.findCreatorProperty(str);
        if (settableBeanProperty != null) {
          Object object1 = settableBeanProperty.deserialize(paramJsonParser, paramDeserializationContext);
          if (propertyValueBuffer.assignParameter(settableBeanProperty.getPropertyIndex(), object1)) {
            Object object2;
            object1 = paramJsonParser.nextToken();
            try {
              object2 = propertyBasedCreator.build(propertyValueBuffer);
              while (object1 == JsonToken.FIELD_NAME) {
                paramJsonParser.nextToken();
                tokenBuffer.copyCurrentStructure(paramJsonParser);
                object1 = paramJsonParser.nextToken();
              } 
            } catch (Exception exception) {
              wrapAndThrow(exception, this._beanType.getRawClass(), str, paramDeserializationContext);
              JsonToken jsonToken1 = paramJsonParser.nextToken();
              continue;
            } 
            if (object2.getClass() != this._beanType.getRawClass())
              throw paramDeserializationContext.mappingException("Can not create polymorphic instances with unwrapped values"); 
            return externalTypeHandler.complete(paramJsonParser, paramDeserializationContext, object2);
          } 
        } else {
          SettableBeanProperty settableBeanProperty1 = this._beanProperties.find(str);
          if (settableBeanProperty1 != null) {
            propertyValueBuffer.bufferProperty(settableBeanProperty1, settableBeanProperty1.deserialize(paramJsonParser, paramDeserializationContext));
          } else if (!externalTypeHandler.handleToken(paramJsonParser, paramDeserializationContext, str, null)) {
            if (this._ignorableProps != null && this._ignorableProps.contains(str)) {
              paramJsonParser.skipChildren();
            } else if (this._anySetter != null) {
              propertyValueBuffer.bufferAnyProperty(this._anySetter, str, this._anySetter.deserialize(paramJsonParser, paramDeserializationContext));
            } 
          } 
        } 
      } else {
        break;
      } 
    } 
    try {
      Object object1 = propertyBasedCreator.build(propertyValueBuffer);
      object = externalTypeHandler.complete(paramJsonParser, paramDeserializationContext, object1);
    } catch (Exception object) {
      wrapInstantiationProblem((Throwable)object, paramDeserializationContext);
      object = null;
    } 
    return object;
  }
  
  protected Object deserializeUsingPropertyBasedWithUnwrapped(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    PropertyBasedCreator propertyBasedCreator = this._propertyBasedCreator;
    PropertyValueBuffer propertyValueBuffer = propertyBasedCreator.startBuilding(paramJsonParser, paramDeserializationContext);
    TokenBuffer tokenBuffer = new TokenBuffer(paramJsonParser.getCodec());
    tokenBuffer.writeStartObject();
    for (JsonToken jsonToken = paramJsonParser.getCurrentToken();; jsonToken = paramJsonParser.nextToken()) {
      if (jsonToken == JsonToken.FIELD_NAME) {
        String str = paramJsonParser.getCurrentName();
        paramJsonParser.nextToken();
        SettableBeanProperty settableBeanProperty = propertyBasedCreator.findCreatorProperty(str);
        if (settableBeanProperty != null) {
          Object object1 = settableBeanProperty.deserialize(paramJsonParser, paramDeserializationContext);
          if (propertyValueBuffer.assignParameter(settableBeanProperty.getPropertyIndex(), object1)) {
            JsonToken jsonToken1 = paramJsonParser.nextToken();
            try {
              object1 = propertyBasedCreator.build(propertyValueBuffer);
              while (jsonToken1 == JsonToken.FIELD_NAME) {
                paramJsonParser.nextToken();
                tokenBuffer.copyCurrentStructure(paramJsonParser);
                jsonToken1 = paramJsonParser.nextToken();
              } 
            } catch (Exception exception) {
              wrapAndThrow(exception, this._beanType.getRawClass(), str, paramDeserializationContext);
              JsonToken jsonToken2 = paramJsonParser.nextToken();
              continue;
            } 
            tokenBuffer.writeEndObject();
            if (object1.getClass() != this._beanType.getRawClass())
              throw paramDeserializationContext.mappingException("Can not create polymorphic instances with unwrapped values"); 
            return this._unwrappedPropertyHandler.processUnwrapped(paramJsonParser, paramDeserializationContext, object1, tokenBuffer);
          } 
        } else {
          settableBeanProperty = this._beanProperties.find(str);
          if (settableBeanProperty != null) {
            propertyValueBuffer.bufferProperty(settableBeanProperty, settableBeanProperty.deserialize(paramJsonParser, paramDeserializationContext));
          } else if (this._ignorableProps != null && this._ignorableProps.contains(str)) {
            paramJsonParser.skipChildren();
          } else {
            tokenBuffer.writeFieldName(str);
            tokenBuffer.copyCurrentStructure(paramJsonParser);
            if (this._anySetter != null)
              propertyValueBuffer.bufferAnyProperty(this._anySetter, str, this._anySetter.deserialize(paramJsonParser, paramDeserializationContext)); 
          } 
        } 
      } else {
        break;
      } 
    } 
    try {
      Object object1 = propertyBasedCreator.build(propertyValueBuffer);
      object = this._unwrappedPropertyHandler.processUnwrapped(paramJsonParser, paramDeserializationContext, object1, tokenBuffer);
    } catch (Exception object) {
      wrapInstantiationProblem((Throwable)object, paramDeserializationContext);
      object = null;
    } 
    return object;
  }
  
  protected Object deserializeWithExternalTypeId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    return (this._propertyBasedCreator != null) ? deserializeUsingPropertyBasedWithExternalTypeId(paramJsonParser, paramDeserializationContext) : deserializeWithExternalTypeId(paramJsonParser, paramDeserializationContext, this._valueInstantiator.createUsingDefault());
  }
  
  protected Object deserializeWithExternalTypeId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject) {
    ExternalTypeHandler externalTypeHandler = this._externalTypeIdHandler.start();
    while (paramJsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
      String str = paramJsonParser.getCurrentName();
      paramJsonParser.nextToken();
      SettableBeanProperty settableBeanProperty = this._beanProperties.find(str);
      if (settableBeanProperty != null) {
        try {
          settableBeanProperty.deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject);
        } catch (Exception exception) {
          wrapAndThrow(exception, paramObject, str, paramDeserializationContext);
        } 
      } else if (this._ignorableProps != null && this._ignorableProps.contains(str)) {
        paramJsonParser.skipChildren();
      } else if (!externalTypeHandler.handleToken(paramJsonParser, paramDeserializationContext, str, paramObject)) {
        if (this._anySetter != null) {
          try {
            this._anySetter.deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject, str);
          } catch (Exception exception) {
            wrapAndThrow(exception, paramObject, str, paramDeserializationContext);
          } 
        } else {
          handleUnknownProperty(paramJsonParser, paramDeserializationContext, paramObject, str);
        } 
      } 
      paramJsonParser.nextToken();
    } 
    return externalTypeHandler.complete(paramJsonParser, paramDeserializationContext, paramObject);
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer) {
    return paramTypeDeserializer.deserializeTypedFromObject(paramJsonParser, paramDeserializationContext);
  }
  
  protected Object deserializeWithUnwrapped(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (this._delegateDeserializer != null)
      return this._valueInstantiator.createUsingDelegate(this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext)); 
    if (this._propertyBasedCreator != null)
      return deserializeUsingPropertyBasedWithUnwrapped(paramJsonParser, paramDeserializationContext); 
    TokenBuffer tokenBuffer = new TokenBuffer(paramJsonParser.getCodec());
    tokenBuffer.writeStartObject();
    Object object = this._valueInstantiator.createUsingDefault();
    if (this._injectables != null)
      injectValues(paramDeserializationContext, object); 
    while (paramJsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
      String str = paramJsonParser.getCurrentName();
      paramJsonParser.nextToken();
      SettableBeanProperty settableBeanProperty = this._beanProperties.find(str);
      if (settableBeanProperty != null) {
        try {
          settableBeanProperty.deserializeAndSet(paramJsonParser, paramDeserializationContext, object);
        } catch (Exception exception) {
          wrapAndThrow(exception, object, str, paramDeserializationContext);
        } 
      } else if (this._ignorableProps != null && this._ignorableProps.contains(str)) {
        paramJsonParser.skipChildren();
      } else {
        tokenBuffer.writeFieldName(str);
        tokenBuffer.copyCurrentStructure(paramJsonParser);
        if (this._anySetter != null)
          try {
            this._anySetter.deserializeAndSet(paramJsonParser, paramDeserializationContext, object, str);
          } catch (Exception exception) {
            wrapAndThrow(exception, object, str, paramDeserializationContext);
          }  
      } 
      paramJsonParser.nextToken();
    } 
    tokenBuffer.writeEndObject();
    this._unwrappedPropertyHandler.processUnwrapped(paramJsonParser, paramDeserializationContext, object, tokenBuffer);
    return object;
  }
  
  protected Object deserializeWithUnwrapped(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject) {
    JsonToken jsonToken2 = paramJsonParser.getCurrentToken();
    JsonToken jsonToken1 = jsonToken2;
    if (jsonToken2 == JsonToken.START_OBJECT)
      jsonToken1 = paramJsonParser.nextToken(); 
    TokenBuffer tokenBuffer = new TokenBuffer(paramJsonParser.getCodec());
    tokenBuffer.writeStartObject();
    while (jsonToken1 == JsonToken.FIELD_NAME) {
      String str = paramJsonParser.getCurrentName();
      SettableBeanProperty settableBeanProperty = this._beanProperties.find(str);
      paramJsonParser.nextToken();
      if (settableBeanProperty != null) {
        try {
          settableBeanProperty.deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject);
        } catch (Exception exception) {
          wrapAndThrow(exception, paramObject, str, paramDeserializationContext);
        } 
      } else if (this._ignorableProps != null && this._ignorableProps.contains(str)) {
        paramJsonParser.skipChildren();
      } else {
        tokenBuffer.writeFieldName(str);
        tokenBuffer.copyCurrentStructure(paramJsonParser);
        if (this._anySetter != null)
          this._anySetter.deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject, str); 
      } 
      JsonToken jsonToken = paramJsonParser.nextToken();
    } 
    tokenBuffer.writeEndObject();
    this._unwrappedPropertyHandler.processUnwrapped(paramJsonParser, paramDeserializationContext, paramObject, tokenBuffer);
    return paramObject;
  }
  
  public SettableBeanProperty findBackReference(String paramString) {
    return (this._backRefs == null) ? null : (SettableBeanProperty)this._backRefs.get(paramString);
  }
  
  public final Class getBeanClass() {
    return this._beanType.getRawClass();
  }
  
  public int getPropertyCount() {
    return this._beanProperties.size();
  }
  
  public ValueInstantiator getValueInstantiator() {
    return this._valueInstantiator;
  }
  
  public JavaType getValueType() {
    return this._beanType;
  }
  
  protected Object handlePolymorphic(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, TokenBuffer paramTokenBuffer) {
    Object object;
    JsonDeserializer jsonDeserializer = _findSubclassDeserializer(paramDeserializationContext, paramObject, paramTokenBuffer);
    if (jsonDeserializer != null) {
      if (paramTokenBuffer != null) {
        paramTokenBuffer.writeEndObject();
        JsonParser jsonParser = paramTokenBuffer.asParser();
        jsonParser.nextToken();
        object = jsonDeserializer.deserialize(jsonParser, paramDeserializationContext, paramObject);
      } else {
        object = paramObject;
      } 
      paramObject = object;
      if (paramJsonParser != null)
        paramObject = jsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext, object); 
      return paramObject;
    } 
    if (object != null) {
      object = handleUnknownProperties(paramDeserializationContext, paramObject, (TokenBuffer)object);
    } else {
      object = paramObject;
    } 
    paramObject = object;
    if (paramJsonParser != null)
      paramObject = deserialize(paramJsonParser, paramDeserializationContext, object); 
    return paramObject;
  }
  
  protected Object handleUnknownProperties(DeserializationContext paramDeserializationContext, Object paramObject, TokenBuffer paramTokenBuffer) {
    paramTokenBuffer.writeEndObject();
    JsonParser jsonParser = paramTokenBuffer.asParser();
    while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
      String str = jsonParser.getCurrentName();
      jsonParser.nextToken();
      handleUnknownProperty(jsonParser, paramDeserializationContext, paramObject, str);
    } 
    return paramObject;
  }
  
  protected void handleUnknownProperty(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, String paramString) {
    if (this._ignoreAllUnknown || (this._ignorableProps != null && this._ignorableProps.contains(paramString))) {
      paramJsonParser.skipChildren();
      return;
    } 
    super.handleUnknownProperty(paramJsonParser, paramDeserializationContext, paramObject, paramString);
  }
  
  public boolean hasProperty(String paramString) {
    return (this._beanProperties.find(paramString) != null);
  }
  
  protected void injectValues(DeserializationContext paramDeserializationContext, Object paramObject) {
    ValueInjector[] arrayOfValueInjector = this._injectables;
    int i = arrayOfValueInjector.length;
    for (byte b = 0; b < i; b++)
      arrayOfValueInjector[b].inject(paramDeserializationContext, paramObject); 
  }
  
  public Iterator properties() {
    if (this._beanProperties == null)
      throw new IllegalStateException("Can only call before BeanDeserializer has been resolved"); 
    return this._beanProperties.allProperties();
  }
  
  public void resolve(DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider) {
    Iterator<SettableBeanProperty> iterator = this._beanProperties.allProperties();
    ExternalTypeHandler.Builder builder = null;
    SettableBeanProperty settableBeanProperty = null;
    while (true) {
      if (iterator.hasNext()) {
        UnwrappedPropertyHandler unwrappedPropertyHandler1;
        SettableBeanProperty settableBeanProperty1;
        UnwrappedPropertyHandler unwrappedPropertyHandler2;
        SettableBeanProperty settableBeanProperty5 = iterator.next();
        if (!settableBeanProperty5.hasValueDeserializer()) {
          settableBeanProperty2 = settableBeanProperty5.withValueDeserializer(findDeserializer(paramDeserializationConfig, paramDeserializerProvider, settableBeanProperty5.getType(), settableBeanProperty5));
        } else {
          settableBeanProperty2 = settableBeanProperty5;
        } 
        SettableBeanProperty settableBeanProperty2 = _resolveManagedReferenceProperty(paramDeserializationConfig, settableBeanProperty2);
        SettableBeanProperty settableBeanProperty4 = _resolveUnwrappedProperty(paramDeserializationConfig, settableBeanProperty2);
        if (settableBeanProperty4 != null) {
          settableBeanProperty2 = settableBeanProperty;
          if (settableBeanProperty == null)
            unwrappedPropertyHandler1 = new UnwrappedPropertyHandler(); 
          unwrappedPropertyHandler1.addProperty(settableBeanProperty4);
        } else {
          unwrappedPropertyHandler2 = unwrappedPropertyHandler1;
          settableBeanProperty1 = settableBeanProperty;
        } 
        SettableBeanProperty settableBeanProperty3 = _resolveInnerClassValuedProperty(paramDeserializationConfig, (SettableBeanProperty)unwrappedPropertyHandler2);
        if (settableBeanProperty3 != settableBeanProperty5)
          this._beanProperties.replace(settableBeanProperty3); 
        settableBeanProperty = settableBeanProperty1;
        if (settableBeanProperty3.hasValueTypeDeserializer()) {
          TypeDeserializer typeDeserializer = settableBeanProperty3.getValueTypeDeserializer();
          settableBeanProperty = settableBeanProperty1;
          if (typeDeserializer.getTypeInclusion() == JsonTypeInfo.As.EXTERNAL_PROPERTY) {
            if (builder == null)
              builder = new ExternalTypeHandler.Builder(); 
            builder.addExternal(settableBeanProperty3, typeDeserializer.getPropertyName());
            this._beanProperties.remove(settableBeanProperty3);
            settableBeanProperty = settableBeanProperty1;
          } 
        } 
        continue;
      } 
      if (this._anySetter != null && !this._anySetter.hasValueDeserializer())
        this._anySetter = this._anySetter.withValueDeserializer(findDeserializer(paramDeserializationConfig, paramDeserializerProvider, this._anySetter.getType(), this._anySetter.getProperty())); 
      if (this._valueInstantiator.canCreateUsingDelegate()) {
        JavaType javaType = this._valueInstantiator.getDelegateType();
        if (javaType == null)
          throw new IllegalArgumentException("Invalid delegate-creator definition for " + this._beanType + ": value instantiator (" + this._valueInstantiator.getClass().getName() + ") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'"); 
        AnnotatedWithParams annotatedWithParams = this._valueInstantiator.getDelegateCreator();
        this._delegateDeserializer = findDeserializer(paramDeserializationConfig, paramDeserializerProvider, javaType, (BeanProperty)new BeanProperty.Std(null, javaType, this._forClass.getAnnotations(), (AnnotatedMember)annotatedWithParams));
      } 
      if (this._propertyBasedCreator != null)
        for (SettableBeanProperty settableBeanProperty1 : this._propertyBasedCreator.getCreatorProperties()) {
          if (!settableBeanProperty1.hasValueDeserializer())
            this._propertyBasedCreator.assignDeserializer(settableBeanProperty1, findDeserializer(paramDeserializationConfig, paramDeserializerProvider, settableBeanProperty1.getType(), settableBeanProperty1)); 
        }  
      if (builder != null) {
        this._externalTypeIdHandler = builder.build();
        this._nonStandardCreation = true;
      } 
      this._unwrappedPropertyHandler = (UnwrappedPropertyHandler)settableBeanProperty;
      if (settableBeanProperty != null)
        this._nonStandardCreation = true; 
      return;
    } 
  }
  
  public JsonDeserializer unwrappingDeserializer() {
    return (JsonDeserializer)((getClass() != BeanDeserializer.class) ? this : new BeanDeserializer(this, true));
  }
  
  @Deprecated
  public void wrapAndThrow(Throwable paramThrowable, Object paramObject, int paramInt) {
    wrapAndThrow(paramThrowable, paramObject, paramInt, (DeserializationContext)null);
  }
  
  public void wrapAndThrow(Throwable paramThrowable, Object paramObject, int paramInt, DeserializationContext paramDeserializationContext) {
    boolean bool;
    while (paramThrowable instanceof java.lang.reflect.InvocationTargetException && paramThrowable.getCause() != null)
      paramThrowable = paramThrowable.getCause(); 
    if (paramThrowable instanceof Error)
      throw (Error)paramThrowable; 
    if (paramDeserializationContext == null || paramDeserializationContext.isEnabled(DeserializationConfig.Feature.WRAP_EXCEPTIONS)) {
      bool = true;
    } else {
      bool = false;
    } 
    if (paramThrowable instanceof IOException) {
      if (!bool || !(paramThrowable instanceof JsonMappingException))
        throw (IOException)paramThrowable; 
    } else if (!bool && paramThrowable instanceof RuntimeException) {
      throw (RuntimeException)paramThrowable;
    } 
    throw JsonMappingException.wrapWithPath(paramThrowable, paramObject, paramInt);
  }
  
  @Deprecated
  public void wrapAndThrow(Throwable paramThrowable, Object paramObject, String paramString) {
    wrapAndThrow(paramThrowable, paramObject, paramString, (DeserializationContext)null);
  }
  
  public void wrapAndThrow(Throwable paramThrowable, Object paramObject, String paramString, DeserializationContext paramDeserializationContext) {
    boolean bool;
    while (paramThrowable instanceof java.lang.reflect.InvocationTargetException && paramThrowable.getCause() != null)
      paramThrowable = paramThrowable.getCause(); 
    if (paramThrowable instanceof Error)
      throw (Error)paramThrowable; 
    if (paramDeserializationContext == null || paramDeserializationContext.isEnabled(DeserializationConfig.Feature.WRAP_EXCEPTIONS)) {
      bool = true;
    } else {
      bool = false;
    } 
    if (paramThrowable instanceof IOException) {
      if (!bool || !(paramThrowable instanceof JsonMappingException))
        throw (IOException)paramThrowable; 
    } else if (!bool && paramThrowable instanceof RuntimeException) {
      throw (RuntimeException)paramThrowable;
    } 
    throw JsonMappingException.wrapWithPath(paramThrowable, paramObject, paramString);
  }
  
  protected void wrapInstantiationProblem(Throwable paramThrowable, DeserializationContext paramDeserializationContext) {
    boolean bool;
    while (paramThrowable instanceof java.lang.reflect.InvocationTargetException && paramThrowable.getCause() != null)
      paramThrowable = paramThrowable.getCause(); 
    if (paramThrowable instanceof Error)
      throw (Error)paramThrowable; 
    if (paramDeserializationContext == null || paramDeserializationContext.isEnabled(DeserializationConfig.Feature.WRAP_EXCEPTIONS)) {
      bool = true;
    } else {
      bool = false;
    } 
    if (paramThrowable instanceof IOException)
      throw (IOException)paramThrowable; 
    if (!bool && paramThrowable instanceof RuntimeException)
      throw (RuntimeException)paramThrowable; 
    throw paramDeserializationContext.instantiationException(this._beanType.getRawClass(), paramThrowable);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\BeanDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */