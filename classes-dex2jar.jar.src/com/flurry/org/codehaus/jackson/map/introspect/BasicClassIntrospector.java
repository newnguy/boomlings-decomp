package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.BeanDescription;
import com.flurry.org.codehaus.jackson.map.ClassIntrospector;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.MapperConfig;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.type.SimpleType;
import com.flurry.org.codehaus.jackson.type.JavaType;

public class BasicClassIntrospector extends ClassIntrospector {
  protected static final BasicBeanDescription BOOLEAN_DESC;
  
  @Deprecated
  public static final BasicClassIntrospector$GetterMethodFilter DEFAULT_GETTER_FILTER;
  
  @Deprecated
  public static final BasicClassIntrospector$SetterAndGetterMethodFilter DEFAULT_SETTER_AND_GETTER_FILTER;
  
  @Deprecated
  public static final BasicClassIntrospector$SetterMethodFilter DEFAULT_SETTER_FILTER;
  
  protected static final BasicBeanDescription INT_DESC;
  
  protected static final BasicBeanDescription LONG_DESC;
  
  protected static final MethodFilter MINIMAL_FILTER;
  
  protected static final BasicBeanDescription STRING_DESC;
  
  public static final BasicClassIntrospector instance;
  
  static {
    AnnotatedClass annotatedClass = AnnotatedClass.constructWithoutSuperTypes(String.class, null, null);
    STRING_DESC = BasicBeanDescription.forOtherUse(null, (JavaType)SimpleType.constructUnsafe(String.class), annotatedClass);
    annotatedClass = AnnotatedClass.constructWithoutSuperTypes(boolean.class, null, null);
    BOOLEAN_DESC = BasicBeanDescription.forOtherUse(null, (JavaType)SimpleType.constructUnsafe(boolean.class), annotatedClass);
    annotatedClass = AnnotatedClass.constructWithoutSuperTypes(int.class, null, null);
    INT_DESC = BasicBeanDescription.forOtherUse(null, (JavaType)SimpleType.constructUnsafe(int.class), annotatedClass);
    annotatedClass = AnnotatedClass.constructWithoutSuperTypes(long.class, null, null);
    LONG_DESC = BasicBeanDescription.forOtherUse(null, (JavaType)SimpleType.constructUnsafe(long.class), annotatedClass);
    DEFAULT_GETTER_FILTER = new BasicClassIntrospector$GetterMethodFilter(null);
    DEFAULT_SETTER_FILTER = new BasicClassIntrospector$SetterMethodFilter();
    DEFAULT_SETTER_AND_GETTER_FILTER = new BasicClassIntrospector$SetterAndGetterMethodFilter();
    MINIMAL_FILTER = new BasicClassIntrospector$MinimalMethodFilter(null);
    instance = new BasicClassIntrospector();
  }
  
  protected BasicBeanDescription _findCachedDesc(JavaType paramJavaType) {
    null = paramJavaType.getRawClass();
    return (null == String.class) ? STRING_DESC : ((null == boolean.class) ? BOOLEAN_DESC : ((null == int.class) ? INT_DESC : ((null == long.class) ? LONG_DESC : null)));
  }
  
  public AnnotatedClass classWithCreators(MapperConfig paramMapperConfig, JavaType paramJavaType, ClassIntrospector.MixInResolver paramMixInResolver) {
    boolean bool = paramMapperConfig.isAnnotationProcessingEnabled();
    AnnotationIntrospector annotationIntrospector = paramMapperConfig.getAnnotationIntrospector();
    Class clazz = paramJavaType.getRawClass();
    if (!bool)
      annotationIntrospector = null; 
    AnnotatedClass annotatedClass = AnnotatedClass.construct(clazz, annotationIntrospector, paramMixInResolver);
    annotatedClass.resolveMemberMethods(MINIMAL_FILTER);
    annotatedClass.resolveCreators(true);
    return annotatedClass;
  }
  
  public POJOPropertiesCollector collectProperties(MapperConfig paramMapperConfig, JavaType paramJavaType, ClassIntrospector.MixInResolver paramMixInResolver, boolean paramBoolean) {
    AnnotatedClass annotatedClass = classWithCreators(paramMapperConfig, paramJavaType, paramMixInResolver);
    annotatedClass.resolveMemberMethods(MINIMAL_FILTER);
    annotatedClass.resolveFields();
    return constructPropertyCollector(paramMapperConfig, annotatedClass, paramJavaType, paramBoolean).collect();
  }
  
  protected POJOPropertiesCollector constructPropertyCollector(MapperConfig paramMapperConfig, AnnotatedClass paramAnnotatedClass, JavaType paramJavaType, boolean paramBoolean) {
    return new POJOPropertiesCollector(paramMapperConfig, paramBoolean, paramJavaType, paramAnnotatedClass);
  }
  
  public BasicBeanDescription forClassAnnotations(MapperConfig paramMapperConfig, JavaType paramJavaType, ClassIntrospector.MixInResolver paramMixInResolver) {
    boolean bool = paramMapperConfig.isAnnotationProcessingEnabled();
    AnnotationIntrospector annotationIntrospector = paramMapperConfig.getAnnotationIntrospector();
    Class clazz = paramJavaType.getRawClass();
    if (!bool)
      annotationIntrospector = null; 
    return BasicBeanDescription.forOtherUse(paramMapperConfig, paramJavaType, AnnotatedClass.construct(clazz, annotationIntrospector, paramMixInResolver));
  }
  
  public BasicBeanDescription forCreation(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, ClassIntrospector.MixInResolver paramMixInResolver) {
    BasicBeanDescription basicBeanDescription2 = _findCachedDesc(paramJavaType);
    BasicBeanDescription basicBeanDescription1 = basicBeanDescription2;
    if (basicBeanDescription2 == null)
      basicBeanDescription1 = BasicBeanDescription.forDeserialization(collectProperties((MapperConfig)paramDeserializationConfig, paramJavaType, paramMixInResolver, false)); 
    return basicBeanDescription1;
  }
  
  public BasicBeanDescription forDeserialization(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, ClassIntrospector.MixInResolver paramMixInResolver) {
    BasicBeanDescription basicBeanDescription2 = _findCachedDesc(paramJavaType);
    BasicBeanDescription basicBeanDescription1 = basicBeanDescription2;
    if (basicBeanDescription2 == null)
      basicBeanDescription1 = BasicBeanDescription.forDeserialization(collectProperties((MapperConfig)paramDeserializationConfig, paramJavaType, paramMixInResolver, false)); 
    return basicBeanDescription1;
  }
  
  public BasicBeanDescription forDirectClassAnnotations(MapperConfig paramMapperConfig, JavaType paramJavaType, ClassIntrospector.MixInResolver paramMixInResolver) {
    boolean bool = paramMapperConfig.isAnnotationProcessingEnabled();
    AnnotationIntrospector annotationIntrospector = paramMapperConfig.getAnnotationIntrospector();
    Class clazz = paramJavaType.getRawClass();
    if (!bool)
      annotationIntrospector = null; 
    return BasicBeanDescription.forOtherUse(paramMapperConfig, paramJavaType, AnnotatedClass.constructWithoutSuperTypes(clazz, annotationIntrospector, paramMixInResolver));
  }
  
  public BasicBeanDescription forSerialization(SerializationConfig paramSerializationConfig, JavaType paramJavaType, ClassIntrospector.MixInResolver paramMixInResolver) {
    BasicBeanDescription basicBeanDescription2 = _findCachedDesc(paramJavaType);
    BasicBeanDescription basicBeanDescription1 = basicBeanDescription2;
    if (basicBeanDescription2 == null)
      basicBeanDescription1 = BasicBeanDescription.forSerialization(collectProperties((MapperConfig)paramSerializationConfig, paramJavaType, paramMixInResolver, true)); 
    return basicBeanDescription1;
  }
  
  @Deprecated
  protected MethodFilter getDeserializationMethodFilter(DeserializationConfig paramDeserializationConfig) {
    return paramDeserializationConfig.isEnabled(DeserializationConfig.Feature.USE_GETTERS_AS_SETTERS) ? DEFAULT_SETTER_AND_GETTER_FILTER : DEFAULT_SETTER_FILTER;
  }
  
  @Deprecated
  protected MethodFilter getSerializationMethodFilter(SerializationConfig paramSerializationConfig) {
    return DEFAULT_GETTER_FILTER;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\introspect\BasicClassIntrospector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */