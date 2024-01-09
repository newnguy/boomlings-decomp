package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.annotate.JsonAutoDetect;
import com.flurry.org.codehaus.jackson.annotate.JsonMethod;
import com.flurry.org.codehaus.jackson.map.annotate.JsonSerialize;
import com.flurry.org.codehaus.jackson.map.introspect.Annotated;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedClass;
import com.flurry.org.codehaus.jackson.map.introspect.VisibilityChecker;
import com.flurry.org.codehaus.jackson.map.jsontype.SubtypeResolver;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import com.flurry.org.codehaus.jackson.map.ser.FilterProvider;
import com.flurry.org.codehaus.jackson.map.type.TypeFactory;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.text.DateFormat;
import java.util.HashMap;

public class SerializationConfig extends MapperConfig$Impl {
  protected FilterProvider _filterProvider;
  
  protected JsonSerialize.Inclusion _serializationInclusion = null;
  
  protected Class _serializationView;
  
  public SerializationConfig(ClassIntrospector paramClassIntrospector, AnnotationIntrospector paramAnnotationIntrospector, VisibilityChecker paramVisibilityChecker, SubtypeResolver paramSubtypeResolver, PropertyNamingStrategy paramPropertyNamingStrategy, TypeFactory paramTypeFactory, HandlerInstantiator paramHandlerInstantiator) {
    super(paramClassIntrospector, paramAnnotationIntrospector, paramVisibilityChecker, paramSubtypeResolver, paramPropertyNamingStrategy, paramTypeFactory, paramHandlerInstantiator, collectFeatureDefaults(SerializationConfig$Feature.class));
    this._filterProvider = null;
  }
  
  protected SerializationConfig(SerializationConfig paramSerializationConfig) {
    this(paramSerializationConfig, paramSerializationConfig._base);
  }
  
  protected SerializationConfig(SerializationConfig paramSerializationConfig, int paramInt) {
    super(paramSerializationConfig, paramInt);
    this._serializationInclusion = paramSerializationConfig._serializationInclusion;
    this._serializationView = paramSerializationConfig._serializationView;
    this._filterProvider = paramSerializationConfig._filterProvider;
  }
  
  protected SerializationConfig(SerializationConfig paramSerializationConfig, MapperConfig$Base paramMapperConfig$Base) {
    super(paramSerializationConfig, paramMapperConfig$Base, paramSerializationConfig._subtypeResolver);
    this._serializationInclusion = paramSerializationConfig._serializationInclusion;
    this._serializationView = paramSerializationConfig._serializationView;
    this._filterProvider = paramSerializationConfig._filterProvider;
  }
  
  protected SerializationConfig(SerializationConfig paramSerializationConfig, JsonSerialize.Inclusion paramInclusion) {
    super(paramSerializationConfig);
    this._serializationInclusion = paramInclusion;
    if (paramInclusion == JsonSerialize.Inclusion.NON_NULL) {
      this._featureFlags &= SerializationConfig$Feature.WRITE_NULL_PROPERTIES.getMask() ^ 0xFFFFFFFF;
    } else {
      this._featureFlags |= SerializationConfig$Feature.WRITE_NULL_PROPERTIES.getMask();
    } 
    this._serializationView = paramSerializationConfig._serializationView;
    this._filterProvider = paramSerializationConfig._filterProvider;
  }
  
  protected SerializationConfig(SerializationConfig paramSerializationConfig, FilterProvider paramFilterProvider) {
    super(paramSerializationConfig);
    this._serializationInclusion = paramSerializationConfig._serializationInclusion;
    this._serializationView = paramSerializationConfig._serializationView;
    this._filterProvider = paramFilterProvider;
  }
  
  protected SerializationConfig(SerializationConfig paramSerializationConfig, Class paramClass) {
    super(paramSerializationConfig);
    this._serializationInclusion = paramSerializationConfig._serializationInclusion;
    this._serializationView = paramClass;
    this._filterProvider = paramSerializationConfig._filterProvider;
  }
  
  protected SerializationConfig(SerializationConfig paramSerializationConfig, HashMap paramHashMap, SubtypeResolver paramSubtypeResolver) {
    this(paramSerializationConfig, paramSerializationConfig._base);
    this._mixInAnnotations = paramHashMap;
    this._subtypeResolver = paramSubtypeResolver;
  }
  
  public boolean canOverrideAccessModifiers() {
    return isEnabled(SerializationConfig$Feature.CAN_OVERRIDE_ACCESS_MODIFIERS);
  }
  
  public SerializationConfig createUnshared(SubtypeResolver paramSubtypeResolver) {
    HashMap hashMap = this._mixInAnnotations;
    this._mixInAnnotationsShared = true;
    return new SerializationConfig(this, hashMap, paramSubtypeResolver);
  }
  
  @Deprecated
  public void disable(SerializationConfig$Feature paramSerializationConfig$Feature) {
    super.disable(paramSerializationConfig$Feature);
  }
  
  @Deprecated
  public void enable(SerializationConfig$Feature paramSerializationConfig$Feature) {
    super.enable(paramSerializationConfig$Feature);
  }
  
  @Deprecated
  public void fromAnnotations(Class paramClass) {
    AnnotationIntrospector annotationIntrospector = getAnnotationIntrospector();
    AnnotatedClass annotatedClass = AnnotatedClass.construct(paramClass, annotationIntrospector, null);
    this._base = this._base.withVisibilityChecker(annotationIntrospector.findAutoDetectVisibility(annotatedClass, getDefaultVisibilityChecker()));
    JsonSerialize.Inclusion inclusion = annotationIntrospector.findSerializationInclusion((Annotated)annotatedClass, null);
    if (inclusion != this._serializationInclusion)
      setSerializationInclusion(inclusion); 
    JsonSerialize.Typing typing = annotationIntrospector.findSerializationTyping((Annotated)annotatedClass);
    if (typing != null) {
      boolean bool;
      SerializationConfig$Feature serializationConfig$Feature = SerializationConfig$Feature.USE_STATIC_TYPING;
      if (typing == JsonSerialize.Typing.STATIC) {
        bool = true;
      } else {
        bool = false;
      } 
      set(serializationConfig$Feature, bool);
    } 
  }
  
  public AnnotationIntrospector getAnnotationIntrospector() {
    return isEnabled(SerializationConfig$Feature.USE_ANNOTATIONS) ? super.getAnnotationIntrospector() : AnnotationIntrospector.nopInstance();
  }
  
  public VisibilityChecker getDefaultVisibilityChecker() {
    VisibilityChecker visibilityChecker2 = super.getDefaultVisibilityChecker();
    VisibilityChecker visibilityChecker1 = visibilityChecker2;
    if (!isEnabled(SerializationConfig$Feature.AUTO_DETECT_GETTERS))
      visibilityChecker1 = visibilityChecker2.withGetterVisibility(JsonAutoDetect.Visibility.NONE); 
    visibilityChecker2 = visibilityChecker1;
    if (!isEnabled(SerializationConfig$Feature.AUTO_DETECT_IS_GETTERS))
      visibilityChecker2 = visibilityChecker1.withIsGetterVisibility(JsonAutoDetect.Visibility.NONE); 
    visibilityChecker1 = visibilityChecker2;
    if (!isEnabled(SerializationConfig$Feature.AUTO_DETECT_FIELDS))
      visibilityChecker1 = visibilityChecker2.withFieldVisibility(JsonAutoDetect.Visibility.NONE); 
    return visibilityChecker1;
  }
  
  public FilterProvider getFilterProvider() {
    return this._filterProvider;
  }
  
  public JsonSerialize.Inclusion getSerializationInclusion() {
    return (this._serializationInclusion != null) ? this._serializationInclusion : (isEnabled(SerializationConfig$Feature.WRITE_NULL_PROPERTIES) ? JsonSerialize.Inclusion.ALWAYS : JsonSerialize.Inclusion.NON_NULL);
  }
  
  public Class getSerializationView() {
    return this._serializationView;
  }
  
  public BeanDescription introspect(JavaType paramJavaType) {
    return getClassIntrospector().forSerialization(this, paramJavaType, this);
  }
  
  public BeanDescription introspectClassAnnotations(JavaType paramJavaType) {
    return getClassIntrospector().forClassAnnotations(this, paramJavaType, this);
  }
  
  public BeanDescription introspectDirectClassAnnotations(JavaType paramJavaType) {
    return getClassIntrospector().forDirectClassAnnotations(this, paramJavaType, this);
  }
  
  public boolean isAnnotationProcessingEnabled() {
    return isEnabled(SerializationConfig$Feature.USE_ANNOTATIONS);
  }
  
  public boolean isEnabled(SerializationConfig$Feature paramSerializationConfig$Feature) {
    return ((this._featureFlags & paramSerializationConfig$Feature.getMask()) != 0);
  }
  
  public JsonSerializer serializerInstance(Annotated paramAnnotated, Class paramClass) {
    HandlerInstantiator handlerInstantiator = getHandlerInstantiator();
    if (handlerInstantiator != null) {
      JsonSerializer jsonSerializer = handlerInstantiator.serializerInstance(this, paramAnnotated, paramClass);
      if (jsonSerializer != null)
        return jsonSerializer; 
    } 
    return (JsonSerializer)ClassUtil.createInstance(paramClass, canOverrideAccessModifiers());
  }
  
  @Deprecated
  public void set(SerializationConfig$Feature paramSerializationConfig$Feature, boolean paramBoolean) {
    super.set(paramSerializationConfig$Feature, paramBoolean);
  }
  
  @Deprecated
  public final void setDateFormat(DateFormat paramDateFormat) {
    boolean bool;
    super.setDateFormat(paramDateFormat);
    SerializationConfig$Feature serializationConfig$Feature = SerializationConfig$Feature.WRITE_DATES_AS_TIMESTAMPS;
    if (paramDateFormat == null) {
      bool = true;
    } else {
      bool = false;
    } 
    set(serializationConfig$Feature, bool);
  }
  
  @Deprecated
  public void setSerializationInclusion(JsonSerialize.Inclusion paramInclusion) {
    this._serializationInclusion = paramInclusion;
    if (paramInclusion == JsonSerialize.Inclusion.NON_NULL) {
      disable(SerializationConfig$Feature.WRITE_NULL_PROPERTIES);
      return;
    } 
    enable(SerializationConfig$Feature.WRITE_NULL_PROPERTIES);
  }
  
  @Deprecated
  public void setSerializationView(Class paramClass) {
    this._serializationView = paramClass;
  }
  
  public boolean shouldSortPropertiesAlphabetically() {
    return isEnabled(SerializationConfig$Feature.SORT_PROPERTIES_ALPHABETICALLY);
  }
  
  public String toString() {
    return "[SerializationConfig: flags=0x" + Integer.toHexString(this._featureFlags) + "]";
  }
  
  public SerializationConfig with(SerializationConfig$Feature... paramVarArgs) {
    int i = this._featureFlags;
    int j = paramVarArgs.length;
    for (byte b = 0; b < j; b++)
      i |= paramVarArgs[b].getMask(); 
    return new SerializationConfig(this, i);
  }
  
  public SerializationConfig withAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector) {
    return new SerializationConfig(this, this._base.withAnnotationIntrospector(paramAnnotationIntrospector));
  }
  
  public SerializationConfig withAppendedAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector) {
    return new SerializationConfig(this, this._base.withAppendedAnnotationIntrospector(paramAnnotationIntrospector));
  }
  
  public SerializationConfig withClassIntrospector(ClassIntrospector paramClassIntrospector) {
    return new SerializationConfig(this, this._base.withClassIntrospector(paramClassIntrospector));
  }
  
  public SerializationConfig withDateFormat(DateFormat paramDateFormat) {
    SerializationConfig serializationConfig = new SerializationConfig(this, this._base.withDateFormat(paramDateFormat));
    return (paramDateFormat == null) ? serializationConfig.with(new SerializationConfig$Feature[] { SerializationConfig$Feature.WRITE_DATES_AS_TIMESTAMPS }) : serializationConfig.without(new SerializationConfig$Feature[] { SerializationConfig$Feature.WRITE_DATES_AS_TIMESTAMPS });
  }
  
  public SerializationConfig withFilters(FilterProvider paramFilterProvider) {
    return new SerializationConfig(this, paramFilterProvider);
  }
  
  public SerializationConfig withHandlerInstantiator(HandlerInstantiator paramHandlerInstantiator) {
    return new SerializationConfig(this, this._base.withHandlerInstantiator(paramHandlerInstantiator));
  }
  
  public SerializationConfig withInsertedAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector) {
    return new SerializationConfig(this, this._base.withInsertedAnnotationIntrospector(paramAnnotationIntrospector));
  }
  
  public SerializationConfig withPropertyNamingStrategy(PropertyNamingStrategy paramPropertyNamingStrategy) {
    return new SerializationConfig(this, this._base.withPropertyNamingStrategy(paramPropertyNamingStrategy));
  }
  
  public SerializationConfig withSerializationInclusion(JsonSerialize.Inclusion paramInclusion) {
    return new SerializationConfig(this, paramInclusion);
  }
  
  public SerializationConfig withSubtypeResolver(SubtypeResolver paramSubtypeResolver) {
    SerializationConfig serializationConfig = new SerializationConfig(this);
    serializationConfig._subtypeResolver = paramSubtypeResolver;
    return serializationConfig;
  }
  
  public SerializationConfig withTypeFactory(TypeFactory paramTypeFactory) {
    return new SerializationConfig(this, this._base.withTypeFactory(paramTypeFactory));
  }
  
  public SerializationConfig withTypeResolverBuilder(TypeResolverBuilder paramTypeResolverBuilder) {
    return new SerializationConfig(this, this._base.withTypeResolverBuilder(paramTypeResolverBuilder));
  }
  
  public SerializationConfig withView(Class paramClass) {
    return new SerializationConfig(this, paramClass);
  }
  
  public SerializationConfig withVisibility(JsonMethod paramJsonMethod, JsonAutoDetect.Visibility paramVisibility) {
    return new SerializationConfig(this, this._base.withVisibility(paramJsonMethod, paramVisibility));
  }
  
  public SerializationConfig withVisibilityChecker(VisibilityChecker paramVisibilityChecker) {
    return new SerializationConfig(this, this._base.withVisibilityChecker(paramVisibilityChecker));
  }
  
  public SerializationConfig without(SerializationConfig$Feature... paramVarArgs) {
    int i = this._featureFlags;
    int j = paramVarArgs.length;
    for (byte b = 0; b < j; b++)
      i &= paramVarArgs[b].getMask() ^ 0xFFFFFFFF; 
    return new SerializationConfig(this, i);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\SerializationConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */