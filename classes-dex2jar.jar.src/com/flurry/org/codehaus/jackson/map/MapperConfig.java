package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.annotate.JsonAutoDetect;
import com.flurry.org.codehaus.jackson.annotate.JsonMethod;
import com.flurry.org.codehaus.jackson.map.introspect.Annotated;
import com.flurry.org.codehaus.jackson.map.introspect.VisibilityChecker;
import com.flurry.org.codehaus.jackson.map.jsontype.SubtypeResolver;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeIdResolver;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import com.flurry.org.codehaus.jackson.map.jsontype.impl.StdSubtypeResolver;
import com.flurry.org.codehaus.jackson.map.type.ClassKey;
import com.flurry.org.codehaus.jackson.map.type.TypeBindings;
import com.flurry.org.codehaus.jackson.map.type.TypeFactory;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import com.flurry.org.codehaus.jackson.map.util.StdDateFormat;
import com.flurry.org.codehaus.jackson.type.JavaType;
import com.flurry.org.codehaus.jackson.type.TypeReference;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

public abstract class MapperConfig implements ClassIntrospector$MixInResolver {
  protected static final DateFormat DEFAULT_DATE_FORMAT = (DateFormat)StdDateFormat.instance;
  
  protected MapperConfig$Base _base;
  
  protected HashMap _mixInAnnotations;
  
  protected boolean _mixInAnnotationsShared;
  
  protected SubtypeResolver _subtypeResolver;
  
  protected MapperConfig(ClassIntrospector paramClassIntrospector, AnnotationIntrospector paramAnnotationIntrospector, VisibilityChecker paramVisibilityChecker, SubtypeResolver paramSubtypeResolver, PropertyNamingStrategy paramPropertyNamingStrategy, TypeFactory paramTypeFactory, HandlerInstantiator paramHandlerInstantiator) {
    this._base = new MapperConfig$Base(paramClassIntrospector, paramAnnotationIntrospector, paramVisibilityChecker, paramPropertyNamingStrategy, paramTypeFactory, null, DEFAULT_DATE_FORMAT, paramHandlerInstantiator);
    this._subtypeResolver = paramSubtypeResolver;
    this._mixInAnnotationsShared = true;
  }
  
  protected MapperConfig(MapperConfig paramMapperConfig) {
    this(paramMapperConfig, paramMapperConfig._base, paramMapperConfig._subtypeResolver);
  }
  
  protected MapperConfig(MapperConfig paramMapperConfig, MapperConfig$Base paramMapperConfig$Base, SubtypeResolver paramSubtypeResolver) {
    this._base = paramMapperConfig$Base;
    this._subtypeResolver = paramSubtypeResolver;
    this._mixInAnnotationsShared = true;
    this._mixInAnnotations = paramMapperConfig._mixInAnnotations;
  }
  
  public final void addMixInAnnotations(Class paramClass1, Class<?> paramClass2) {
    if (this._mixInAnnotations == null) {
      this._mixInAnnotationsShared = false;
      this._mixInAnnotations = new HashMap<Object, Object>();
    } else if (this._mixInAnnotationsShared) {
      this._mixInAnnotationsShared = false;
      this._mixInAnnotations = new HashMap<Object, Object>(this._mixInAnnotations);
    } 
    this._mixInAnnotations.put(new ClassKey(paramClass1), paramClass2);
  }
  
  @Deprecated
  public final void appendAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector) {
    this._base = this._base.withAnnotationIntrospector(AnnotationIntrospector$Pair.create(getAnnotationIntrospector(), paramAnnotationIntrospector));
  }
  
  public abstract boolean canOverrideAccessModifiers();
  
  public JavaType constructSpecializedType(JavaType paramJavaType, Class paramClass) {
    return getTypeFactory().constructSpecializedType(paramJavaType, paramClass);
  }
  
  public final JavaType constructType(TypeReference paramTypeReference) {
    return getTypeFactory().constructType(paramTypeReference.getType(), (TypeBindings)null);
  }
  
  public final JavaType constructType(Class paramClass) {
    return getTypeFactory().constructType(paramClass, (TypeBindings)null);
  }
  
  public abstract MapperConfig createUnshared(SubtypeResolver paramSubtypeResolver);
  
  public final Class findMixInClassFor(Class paramClass) {
    return (this._mixInAnnotations == null) ? null : (Class)this._mixInAnnotations.get(new ClassKey(paramClass));
  }
  
  @Deprecated
  public abstract void fromAnnotations(Class paramClass);
  
  public AnnotationIntrospector getAnnotationIntrospector() {
    return this._base.getAnnotationIntrospector();
  }
  
  public ClassIntrospector getClassIntrospector() {
    return this._base.getClassIntrospector();
  }
  
  public final DateFormat getDateFormat() {
    return this._base.getDateFormat();
  }
  
  public final TypeResolverBuilder getDefaultTyper(JavaType paramJavaType) {
    return this._base.getTypeResolverBuilder();
  }
  
  public VisibilityChecker getDefaultVisibilityChecker() {
    return this._base.getVisibilityChecker();
  }
  
  public final HandlerInstantiator getHandlerInstantiator() {
    return this._base.getHandlerInstantiator();
  }
  
  public final PropertyNamingStrategy getPropertyNamingStrategy() {
    return this._base.getPropertyNamingStrategy();
  }
  
  public final SubtypeResolver getSubtypeResolver() {
    if (this._subtypeResolver == null)
      this._subtypeResolver = (SubtypeResolver)new StdSubtypeResolver(); 
    return this._subtypeResolver;
  }
  
  public final TypeFactory getTypeFactory() {
    return this._base.getTypeFactory();
  }
  
  @Deprecated
  public final void insertAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector) {
    this._base = this._base.withAnnotationIntrospector(AnnotationIntrospector$Pair.create(paramAnnotationIntrospector, getAnnotationIntrospector()));
  }
  
  public abstract BeanDescription introspectClassAnnotations(JavaType paramJavaType);
  
  public BeanDescription introspectClassAnnotations(Class paramClass) {
    return introspectClassAnnotations(constructType(paramClass));
  }
  
  public abstract BeanDescription introspectDirectClassAnnotations(JavaType paramJavaType);
  
  public BeanDescription introspectDirectClassAnnotations(Class paramClass) {
    return introspectDirectClassAnnotations(constructType(paramClass));
  }
  
  public abstract boolean isAnnotationProcessingEnabled();
  
  public abstract boolean isEnabled(MapperConfig$ConfigFeature paramMapperConfig$ConfigFeature);
  
  public final int mixInCount() {
    return (this._mixInAnnotations == null) ? 0 : this._mixInAnnotations.size();
  }
  
  @Deprecated
  public final void setAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector) {
    this._base = this._base.withAnnotationIntrospector(paramAnnotationIntrospector);
  }
  
  @Deprecated
  public void setDateFormat(DateFormat paramDateFormat) {
    DateFormat dateFormat = paramDateFormat;
    if (paramDateFormat == null)
      dateFormat = DEFAULT_DATE_FORMAT; 
    this._base = this._base.withDateFormat(dateFormat);
  }
  
  public final void setMixInAnnotations(Map paramMap) {
    HashMap<Object, Object> hashMap2 = null;
    HashMap<Object, Object> hashMap1 = hashMap2;
    if (paramMap != null) {
      hashMap1 = hashMap2;
      if (paramMap.size() > 0) {
        hashMap1 = new HashMap<Object, Object>(paramMap.size());
        for (Map.Entry entry : paramMap.entrySet())
          hashMap1.put(new ClassKey((Class)entry.getKey()), entry.getValue()); 
      } 
    } 
    this._mixInAnnotationsShared = false;
    this._mixInAnnotations = hashMap1;
  }
  
  public abstract boolean shouldSortPropertiesAlphabetically();
  
  public TypeIdResolver typeIdResolverInstance(Annotated paramAnnotated, Class paramClass) {
    HandlerInstantiator handlerInstantiator = getHandlerInstantiator();
    if (handlerInstantiator != null) {
      TypeIdResolver typeIdResolver = handlerInstantiator.typeIdResolverInstance(this, paramAnnotated, paramClass);
      if (typeIdResolver != null)
        return typeIdResolver; 
    } 
    return (TypeIdResolver)ClassUtil.createInstance(paramClass, canOverrideAccessModifiers());
  }
  
  public TypeResolverBuilder typeResolverBuilderInstance(Annotated paramAnnotated, Class paramClass) {
    HandlerInstantiator handlerInstantiator = getHandlerInstantiator();
    if (handlerInstantiator != null) {
      TypeResolverBuilder typeResolverBuilder = handlerInstantiator.typeResolverBuilderInstance(this, paramAnnotated, paramClass);
      if (typeResolverBuilder != null)
        return typeResolverBuilder; 
    } 
    return (TypeResolverBuilder)ClassUtil.createInstance(paramClass, canOverrideAccessModifiers());
  }
  
  public abstract MapperConfig withAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector);
  
  public abstract MapperConfig withAppendedAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector);
  
  public abstract MapperConfig withClassIntrospector(ClassIntrospector paramClassIntrospector);
  
  public abstract MapperConfig withDateFormat(DateFormat paramDateFormat);
  
  public abstract MapperConfig withHandlerInstantiator(HandlerInstantiator paramHandlerInstantiator);
  
  public abstract MapperConfig withInsertedAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector);
  
  public abstract MapperConfig withPropertyNamingStrategy(PropertyNamingStrategy paramPropertyNamingStrategy);
  
  public abstract MapperConfig withSubtypeResolver(SubtypeResolver paramSubtypeResolver);
  
  public abstract MapperConfig withTypeFactory(TypeFactory paramTypeFactory);
  
  public abstract MapperConfig withTypeResolverBuilder(TypeResolverBuilder paramTypeResolverBuilder);
  
  public abstract MapperConfig withVisibility(JsonMethod paramJsonMethod, JsonAutoDetect.Visibility paramVisibility);
  
  public abstract MapperConfig withVisibilityChecker(VisibilityChecker paramVisibilityChecker);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\MapperConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */