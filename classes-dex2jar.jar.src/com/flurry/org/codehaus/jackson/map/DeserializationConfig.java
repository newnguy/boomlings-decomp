package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.Base64Variant;
import com.flurry.org.codehaus.jackson.Base64Variants;
import com.flurry.org.codehaus.jackson.annotate.JsonAutoDetect;
import com.flurry.org.codehaus.jackson.annotate.JsonMethod;
import com.flurry.org.codehaus.jackson.map.deser.ValueInstantiator;
import com.flurry.org.codehaus.jackson.map.introspect.Annotated;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedClass;
import com.flurry.org.codehaus.jackson.map.introspect.NopAnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.introspect.VisibilityChecker;
import com.flurry.org.codehaus.jackson.map.jsontype.SubtypeResolver;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import com.flurry.org.codehaus.jackson.map.type.TypeFactory;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import com.flurry.org.codehaus.jackson.map.util.LinkedNode;
import com.flurry.org.codehaus.jackson.node.JsonNodeFactory;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.text.DateFormat;
import java.util.HashMap;

public class DeserializationConfig extends MapperConfig$Impl {
  protected final JsonNodeFactory _nodeFactory;
  
  protected LinkedNode _problemHandlers;
  
  protected boolean _sortPropertiesAlphabetically;
  
  public DeserializationConfig(ClassIntrospector paramClassIntrospector, AnnotationIntrospector paramAnnotationIntrospector, VisibilityChecker paramVisibilityChecker, SubtypeResolver paramSubtypeResolver, PropertyNamingStrategy paramPropertyNamingStrategy, TypeFactory paramTypeFactory, HandlerInstantiator paramHandlerInstantiator) {
    super(paramClassIntrospector, paramAnnotationIntrospector, paramVisibilityChecker, paramSubtypeResolver, paramPropertyNamingStrategy, paramTypeFactory, paramHandlerInstantiator, collectFeatureDefaults(DeserializationConfig$Feature.class));
    this._nodeFactory = JsonNodeFactory.instance;
  }
  
  protected DeserializationConfig(DeserializationConfig paramDeserializationConfig) {
    this(paramDeserializationConfig, paramDeserializationConfig._base);
  }
  
  protected DeserializationConfig(DeserializationConfig paramDeserializationConfig, int paramInt) {
    super(paramDeserializationConfig, paramInt);
    this._problemHandlers = paramDeserializationConfig._problemHandlers;
    this._nodeFactory = paramDeserializationConfig._nodeFactory;
    this._sortPropertiesAlphabetically = paramDeserializationConfig._sortPropertiesAlphabetically;
  }
  
  protected DeserializationConfig(DeserializationConfig paramDeserializationConfig, MapperConfig$Base paramMapperConfig$Base) {
    super(paramDeserializationConfig, paramMapperConfig$Base, paramDeserializationConfig._subtypeResolver);
    this._problemHandlers = paramDeserializationConfig._problemHandlers;
    this._nodeFactory = paramDeserializationConfig._nodeFactory;
    this._sortPropertiesAlphabetically = paramDeserializationConfig._sortPropertiesAlphabetically;
  }
  
  protected DeserializationConfig(DeserializationConfig paramDeserializationConfig, JsonNodeFactory paramJsonNodeFactory) {
    super(paramDeserializationConfig);
    this._problemHandlers = paramDeserializationConfig._problemHandlers;
    this._nodeFactory = paramJsonNodeFactory;
    this._sortPropertiesAlphabetically = paramDeserializationConfig._sortPropertiesAlphabetically;
  }
  
  private DeserializationConfig(DeserializationConfig paramDeserializationConfig, HashMap paramHashMap, SubtypeResolver paramSubtypeResolver) {
    this(paramDeserializationConfig, paramDeserializationConfig._base);
    this._mixInAnnotations = paramHashMap;
    this._subtypeResolver = paramSubtypeResolver;
  }
  
  public void addHandler(DeserializationProblemHandler paramDeserializationProblemHandler) {
    if (!LinkedNode.contains(this._problemHandlers, paramDeserializationProblemHandler))
      this._problemHandlers = new LinkedNode(paramDeserializationProblemHandler, this._problemHandlers); 
  }
  
  public boolean canOverrideAccessModifiers() {
    return isEnabled(DeserializationConfig$Feature.CAN_OVERRIDE_ACCESS_MODIFIERS);
  }
  
  public void clearHandlers() {
    this._problemHandlers = null;
  }
  
  public DeserializationConfig createUnshared(SubtypeResolver paramSubtypeResolver) {
    HashMap hashMap = this._mixInAnnotations;
    this._mixInAnnotationsShared = true;
    return new DeserializationConfig(this, hashMap, paramSubtypeResolver);
  }
  
  public JsonDeserializer deserializerInstance(Annotated paramAnnotated, Class paramClass) {
    HandlerInstantiator handlerInstantiator = getHandlerInstantiator();
    if (handlerInstantiator != null) {
      JsonDeserializer jsonDeserializer = handlerInstantiator.deserializerInstance(this, paramAnnotated, paramClass);
      if (jsonDeserializer != null)
        return jsonDeserializer; 
    } 
    return (JsonDeserializer)ClassUtil.createInstance(paramClass, canOverrideAccessModifiers());
  }
  
  @Deprecated
  public void disable(DeserializationConfig$Feature paramDeserializationConfig$Feature) {
    super.disable(paramDeserializationConfig$Feature);
  }
  
  @Deprecated
  public void enable(DeserializationConfig$Feature paramDeserializationConfig$Feature) {
    super.enable(paramDeserializationConfig$Feature);
  }
  
  @Deprecated
  public void fromAnnotations(Class paramClass) {
    AnnotationIntrospector annotationIntrospector = getAnnotationIntrospector();
    AnnotatedClass annotatedClass = AnnotatedClass.construct(paramClass, annotationIntrospector, null);
    VisibilityChecker visibilityChecker = getDefaultVisibilityChecker();
    this._base = this._base.withVisibilityChecker(annotationIntrospector.findAutoDetectVisibility(annotatedClass, visibilityChecker));
  }
  
  public AnnotationIntrospector getAnnotationIntrospector() {
    return (AnnotationIntrospector)(isEnabled(DeserializationConfig$Feature.USE_ANNOTATIONS) ? super.getAnnotationIntrospector() : NopAnnotationIntrospector.instance);
  }
  
  public Base64Variant getBase64Variant() {
    return Base64Variants.getDefaultVariant();
  }
  
  public VisibilityChecker getDefaultVisibilityChecker() {
    VisibilityChecker visibilityChecker1 = super.getDefaultVisibilityChecker();
    VisibilityChecker visibilityChecker2 = visibilityChecker1;
    if (!isEnabled(DeserializationConfig$Feature.AUTO_DETECT_SETTERS))
      visibilityChecker2 = visibilityChecker1.withSetterVisibility(JsonAutoDetect.Visibility.NONE); 
    visibilityChecker1 = visibilityChecker2;
    if (!isEnabled(DeserializationConfig$Feature.AUTO_DETECT_CREATORS))
      visibilityChecker1 = visibilityChecker2.withCreatorVisibility(JsonAutoDetect.Visibility.NONE); 
    visibilityChecker2 = visibilityChecker1;
    if (!isEnabled(DeserializationConfig$Feature.AUTO_DETECT_FIELDS))
      visibilityChecker2 = visibilityChecker1.withFieldVisibility(JsonAutoDetect.Visibility.NONE); 
    return visibilityChecker2;
  }
  
  public final JsonNodeFactory getNodeFactory() {
    return this._nodeFactory;
  }
  
  public LinkedNode getProblemHandlers() {
    return this._problemHandlers;
  }
  
  public BeanDescription introspect(JavaType paramJavaType) {
    return getClassIntrospector().forDeserialization(this, paramJavaType, this);
  }
  
  public BeanDescription introspectClassAnnotations(JavaType paramJavaType) {
    return getClassIntrospector().forClassAnnotations(this, paramJavaType, this);
  }
  
  public BeanDescription introspectDirectClassAnnotations(JavaType paramJavaType) {
    return getClassIntrospector().forDirectClassAnnotations(this, paramJavaType, this);
  }
  
  public BeanDescription introspectForCreation(JavaType paramJavaType) {
    return getClassIntrospector().forCreation(this, paramJavaType, this);
  }
  
  public boolean isAnnotationProcessingEnabled() {
    return isEnabled(DeserializationConfig$Feature.USE_ANNOTATIONS);
  }
  
  public boolean isEnabled(DeserializationConfig$Feature paramDeserializationConfig$Feature) {
    return ((this._featureFlags & paramDeserializationConfig$Feature.getMask()) != 0);
  }
  
  public KeyDeserializer keyDeserializerInstance(Annotated paramAnnotated, Class paramClass) {
    HandlerInstantiator handlerInstantiator = getHandlerInstantiator();
    if (handlerInstantiator != null) {
      KeyDeserializer keyDeserializer = handlerInstantiator.keyDeserializerInstance(this, paramAnnotated, paramClass);
      if (keyDeserializer != null)
        return keyDeserializer; 
    } 
    return (KeyDeserializer)ClassUtil.createInstance(paramClass, canOverrideAccessModifiers());
  }
  
  protected DeserializationConfig passSerializationFeatures(int paramInt) {
    if ((SerializationConfig$Feature.SORT_PROPERTIES_ALPHABETICALLY.getMask() & paramInt) != 0) {
      boolean bool1 = true;
      this._sortPropertiesAlphabetically = bool1;
      return this;
    } 
    boolean bool = false;
    this._sortPropertiesAlphabetically = bool;
    return this;
  }
  
  @Deprecated
  public void set(DeserializationConfig$Feature paramDeserializationConfig$Feature, boolean paramBoolean) {
    super.set(paramDeserializationConfig$Feature, paramBoolean);
  }
  
  public boolean shouldSortPropertiesAlphabetically() {
    return this._sortPropertiesAlphabetically;
  }
  
  public ValueInstantiator valueInstantiatorInstance(Annotated paramAnnotated, Class paramClass) {
    HandlerInstantiator handlerInstantiator = getHandlerInstantiator();
    if (handlerInstantiator != null) {
      ValueInstantiator valueInstantiator = handlerInstantiator.valueInstantiatorInstance(this, paramAnnotated, paramClass);
      if (valueInstantiator != null)
        return valueInstantiator; 
    } 
    return (ValueInstantiator)ClassUtil.createInstance(paramClass, canOverrideAccessModifiers());
  }
  
  public DeserializationConfig with(DeserializationConfig$Feature... paramVarArgs) {
    int i = this._featureFlags;
    int j = paramVarArgs.length;
    for (byte b = 0; b < j; b++)
      i |= paramVarArgs[b].getMask(); 
    return new DeserializationConfig(this, i);
  }
  
  public DeserializationConfig withAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector) {
    return new DeserializationConfig(this, this._base.withAnnotationIntrospector(paramAnnotationIntrospector));
  }
  
  public DeserializationConfig withAppendedAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector) {
    return new DeserializationConfig(this, this._base.withAppendedAnnotationIntrospector(paramAnnotationIntrospector));
  }
  
  public DeserializationConfig withClassIntrospector(ClassIntrospector paramClassIntrospector) {
    return new DeserializationConfig(this, this._base.withClassIntrospector(paramClassIntrospector));
  }
  
  public DeserializationConfig withDateFormat(DateFormat paramDateFormat) {
    return (paramDateFormat == this._base.getDateFormat()) ? this : new DeserializationConfig(this, this._base.withDateFormat(paramDateFormat));
  }
  
  public DeserializationConfig withHandlerInstantiator(HandlerInstantiator paramHandlerInstantiator) {
    return (paramHandlerInstantiator == this._base.getHandlerInstantiator()) ? this : new DeserializationConfig(this, this._base.withHandlerInstantiator(paramHandlerInstantiator));
  }
  
  public DeserializationConfig withInsertedAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector) {
    return new DeserializationConfig(this, this._base.withInsertedAnnotationIntrospector(paramAnnotationIntrospector));
  }
  
  public DeserializationConfig withNodeFactory(JsonNodeFactory paramJsonNodeFactory) {
    return new DeserializationConfig(this, paramJsonNodeFactory);
  }
  
  public DeserializationConfig withPropertyNamingStrategy(PropertyNamingStrategy paramPropertyNamingStrategy) {
    return new DeserializationConfig(this, this._base.withPropertyNamingStrategy(paramPropertyNamingStrategy));
  }
  
  public DeserializationConfig withSubtypeResolver(SubtypeResolver paramSubtypeResolver) {
    DeserializationConfig deserializationConfig = new DeserializationConfig(this);
    deserializationConfig._subtypeResolver = paramSubtypeResolver;
    return deserializationConfig;
  }
  
  public DeserializationConfig withTypeFactory(TypeFactory paramTypeFactory) {
    return (paramTypeFactory == this._base.getTypeFactory()) ? this : new DeserializationConfig(this, this._base.withTypeFactory(paramTypeFactory));
  }
  
  public DeserializationConfig withTypeResolverBuilder(TypeResolverBuilder paramTypeResolverBuilder) {
    return new DeserializationConfig(this, this._base.withTypeResolverBuilder(paramTypeResolverBuilder));
  }
  
  public DeserializationConfig withVisibility(JsonMethod paramJsonMethod, JsonAutoDetect.Visibility paramVisibility) {
    return new DeserializationConfig(this, this._base.withVisibility(paramJsonMethod, paramVisibility));
  }
  
  public DeserializationConfig withVisibilityChecker(VisibilityChecker paramVisibilityChecker) {
    return new DeserializationConfig(this, this._base.withVisibilityChecker(paramVisibilityChecker));
  }
  
  public DeserializationConfig without(DeserializationConfig$Feature... paramVarArgs) {
    int i = this._featureFlags;
    int j = paramVarArgs.length;
    for (byte b = 0; b < j; b++)
      i &= paramVarArgs[b].getMask() ^ 0xFFFFFFFF; 
    return new DeserializationConfig(this, i);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\DeserializationConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */