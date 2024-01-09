package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.BeanDescription;
import com.flurry.org.codehaus.jackson.map.BeanPropertyDefinition;
import com.flurry.org.codehaus.jackson.map.MapperConfig;
import com.flurry.org.codehaus.jackson.map.annotate.JsonSerialize;
import com.flurry.org.codehaus.jackson.map.type.TypeBindings;
import com.flurry.org.codehaus.jackson.map.util.Annotations;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BasicBeanDescription extends BeanDescription {
  protected final AnnotationIntrospector _annotationIntrospector;
  
  protected AnnotatedMethod _anyGetterMethod;
  
  protected AnnotatedMethod _anySetterMethod;
  
  protected TypeBindings _bindings;
  
  protected final AnnotatedClass _classInfo;
  
  protected final MapperConfig _config;
  
  protected Set _ignoredPropertyNames;
  
  protected Set _ignoredPropertyNamesForDeser;
  
  protected Map _injectables;
  
  protected AnnotatedMethod _jsonValueMethod;
  
  protected final List _properties;
  
  @Deprecated
  public BasicBeanDescription(MapperConfig paramMapperConfig, JavaType paramJavaType, AnnotatedClass paramAnnotatedClass) {
    this(paramMapperConfig, paramJavaType, paramAnnotatedClass, Collections.emptyList());
  }
  
  protected BasicBeanDescription(MapperConfig paramMapperConfig, JavaType paramJavaType, AnnotatedClass paramAnnotatedClass, List paramList) {
    super(paramJavaType);
    AnnotationIntrospector annotationIntrospector;
    this._config = paramMapperConfig;
    if (paramMapperConfig == null) {
      paramMapperConfig = null;
    } else {
      annotationIntrospector = paramMapperConfig.getAnnotationIntrospector();
    } 
    this._annotationIntrospector = annotationIntrospector;
    this._classInfo = paramAnnotatedClass;
    this._properties = paramList;
  }
  
  public static BasicBeanDescription forDeserialization(POJOPropertiesCollector paramPOJOPropertiesCollector) {
    BasicBeanDescription basicBeanDescription = new BasicBeanDescription(paramPOJOPropertiesCollector.getConfig(), paramPOJOPropertiesCollector.getType(), paramPOJOPropertiesCollector.getClassDef(), paramPOJOPropertiesCollector.getProperties());
    basicBeanDescription._anySetterMethod = paramPOJOPropertiesCollector.getAnySetterMethod();
    basicBeanDescription._ignoredPropertyNames = paramPOJOPropertiesCollector.getIgnoredPropertyNames();
    basicBeanDescription._ignoredPropertyNamesForDeser = paramPOJOPropertiesCollector.getIgnoredPropertyNamesForDeser();
    basicBeanDescription._injectables = paramPOJOPropertiesCollector.getInjectables();
    return basicBeanDescription;
  }
  
  public static BasicBeanDescription forOtherUse(MapperConfig paramMapperConfig, JavaType paramJavaType, AnnotatedClass paramAnnotatedClass) {
    return new BasicBeanDescription(paramMapperConfig, paramJavaType, paramAnnotatedClass, Collections.emptyList());
  }
  
  public static BasicBeanDescription forSerialization(POJOPropertiesCollector paramPOJOPropertiesCollector) {
    BasicBeanDescription basicBeanDescription = new BasicBeanDescription(paramPOJOPropertiesCollector.getConfig(), paramPOJOPropertiesCollector.getType(), paramPOJOPropertiesCollector.getClassDef(), paramPOJOPropertiesCollector.getProperties());
    basicBeanDescription._jsonValueMethod = paramPOJOPropertiesCollector.getJsonValueMethod();
    basicBeanDescription._anyGetterMethod = paramPOJOPropertiesCollector.getAnyGetterMethod();
    return basicBeanDescription;
  }
  
  public LinkedHashMap _findPropertyFields(Collection paramCollection, boolean paramBoolean) {
    LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
    for (BeanPropertyDefinition beanPropertyDefinition : this._properties) {
      AnnotatedField annotatedField = beanPropertyDefinition.getField();
      if (annotatedField != null) {
        String str = beanPropertyDefinition.getName();
        if (paramCollection == null || !paramCollection.contains(str))
          linkedHashMap.put(str, annotatedField); 
      } 
    } 
    return linkedHashMap;
  }
  
  public TypeBindings bindingsForBeanType() {
    if (this._bindings == null)
      this._bindings = new TypeBindings(this._config.getTypeFactory(), this._type); 
    return this._bindings;
  }
  
  public AnnotatedMethod findAnyGetter() {
    if (this._anyGetterMethod != null && !Map.class.isAssignableFrom(this._anyGetterMethod.getRawType()))
      throw new IllegalArgumentException("Invalid 'any-getter' annotation on method " + this._anyGetterMethod.getName() + "(): return type is not instance of java.util.Map"); 
    return this._anyGetterMethod;
  }
  
  public AnnotatedMethod findAnySetter() {
    if (this._anySetterMethod != null) {
      Class<String> clazz = this._anySetterMethod.getParameterClass(0);
      if (clazz != String.class && clazz != Object.class)
        throw new IllegalArgumentException("Invalid 'any-setter' annotation on method " + this._anySetterMethod.getName() + "(): first argument not of type String or Object, but " + clazz.getName()); 
    } 
    return this._anySetterMethod;
  }
  
  public Map findBackReferenceProperties() {
    HashMap<Object, Object> hashMap = null;
    Iterator<BeanPropertyDefinition> iterator = this._properties.iterator();
    while (iterator.hasNext()) {
      AnnotatedMember annotatedMember = ((BeanPropertyDefinition)iterator.next()).getMutator();
      if (annotatedMember != null) {
        AnnotationIntrospector.ReferenceProperty referenceProperty = this._annotationIntrospector.findReferenceType(annotatedMember);
        if (referenceProperty != null && referenceProperty.isBackReference()) {
          if (hashMap == null)
            hashMap = new HashMap<Object, Object>(); 
          String str = referenceProperty.getName();
          if (hashMap.put(str, annotatedMember) != null)
            throw new IllegalArgumentException("Multiple back-reference properties with name '" + str + "'"); 
        } 
      } 
    } 
    return hashMap;
  }
  
  public List findCreatorPropertyNames() {
    List<String> list1 = null;
    for (byte b = 0; b < 2; b++) {
      List<String> list;
      if (b == 0) {
        list = getConstructors();
      } else {
        list = getFactoryMethods();
      } 
      label29: for (AnnotatedWithParams annotatedWithParams : list) {
        int i = annotatedWithParams.getParameterCount();
        if (i >= 1) {
          String str = this._annotationIntrospector.findPropertyNameForParam(annotatedWithParams.getParameter(0));
          if (str != null) {
            list = list1;
            if (list1 == null)
              list = new ArrayList(); 
            list.add(str);
            byte b1 = 1;
            while (true) {
              list1 = list;
              if (b1 < i) {
                list.add(this._annotationIntrospector.findPropertyNameForParam(annotatedWithParams.getParameter(b1)));
                b1++;
                continue;
              } 
              continue label29;
            } 
          } 
        } 
      } 
    } 
    List<String> list2 = list1;
    if (list1 == null)
      list2 = Collections.emptyList(); 
    return list2;
  }
  
  public AnnotatedConstructor findDefaultConstructor() {
    return this._classInfo.getDefaultConstructor();
  }
  
  public LinkedHashMap findDeserializableFields(VisibilityChecker paramVisibilityChecker, Collection paramCollection) {
    return _findPropertyFields(paramCollection, false);
  }
  
  public Method findFactoryMethod(Class... paramVarArgs) {
    for (AnnotatedMethod annotatedMethod : this._classInfo.getStaticMethods()) {
      if (isFactoryMethod(annotatedMethod)) {
        Class clazz = annotatedMethod.getParameterClass(0);
        int i = paramVarArgs.length;
        for (byte b = 0; b < i; b++) {
          if (clazz.isAssignableFrom(paramVarArgs[b]))
            return annotatedMethod.getAnnotated(); 
        } 
      } 
    } 
    return null;
  }
  
  public LinkedHashMap findGetters(VisibilityChecker paramVisibilityChecker, Collection paramCollection) {
    LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
    for (BeanPropertyDefinition beanPropertyDefinition : this._properties) {
      AnnotatedMethod annotatedMethod = beanPropertyDefinition.getGetter();
      if (annotatedMethod != null) {
        String str = beanPropertyDefinition.getName();
        if (paramCollection == null || !paramCollection.contains(str))
          linkedHashMap.put(str, annotatedMethod); 
      } 
    } 
    return linkedHashMap;
  }
  
  public Map findInjectables() {
    return this._injectables;
  }
  
  public AnnotatedMethod findJsonValueMethod() {
    return this._jsonValueMethod;
  }
  
  public AnnotatedMethod findMethod(String paramString, Class[] paramArrayOfClass) {
    return this._classInfo.findMethod(paramString, paramArrayOfClass);
  }
  
  public List findProperties() {
    return this._properties;
  }
  
  public LinkedHashMap findSerializableFields(VisibilityChecker paramVisibilityChecker, Collection paramCollection) {
    return _findPropertyFields(paramCollection, true);
  }
  
  public JsonSerialize.Inclusion findSerializationInclusion(JsonSerialize.Inclusion paramInclusion) {
    if (this._annotationIntrospector != null)
      paramInclusion = this._annotationIntrospector.findSerializationInclusion(this._classInfo, paramInclusion); 
    return paramInclusion;
  }
  
  public LinkedHashMap findSetters(VisibilityChecker paramVisibilityChecker) {
    LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<Object, Object>();
    for (BeanPropertyDefinition beanPropertyDefinition : this._properties) {
      AnnotatedMethod annotatedMethod = beanPropertyDefinition.getSetter();
      if (annotatedMethod != null)
        linkedHashMap.put(beanPropertyDefinition.getName(), annotatedMethod); 
    } 
    return linkedHashMap;
  }
  
  public Constructor findSingleArgConstructor(Class... paramVarArgs) {
    for (AnnotatedConstructor annotatedConstructor : this._classInfo.getConstructors()) {
      if (annotatedConstructor.getParameterCount() == 1) {
        Class clazz = annotatedConstructor.getParameterClass(0);
        int i = paramVarArgs.length;
        for (byte b = 0; b < i; b++) {
          if (paramVarArgs[b] == clazz)
            return annotatedConstructor.getAnnotated(); 
        } 
      } 
    } 
    return null;
  }
  
  public Annotations getClassAnnotations() {
    return this._classInfo.getAnnotations();
  }
  
  public AnnotatedClass getClassInfo() {
    return this._classInfo;
  }
  
  public List getConstructors() {
    return this._classInfo.getConstructors();
  }
  
  public List getFactoryMethods() {
    List list = this._classInfo.getStaticMethods();
    if (list.isEmpty())
      return list; 
    ArrayList<AnnotatedMethod> arrayList = new ArrayList();
    Iterator<AnnotatedMethod> iterator = list.iterator();
    while (true) {
      if (iterator.hasNext()) {
        AnnotatedMethod annotatedMethod = iterator.next();
        if (isFactoryMethod(annotatedMethod))
          arrayList.add(annotatedMethod); 
        continue;
      } 
      return arrayList;
    } 
  }
  
  public Set getIgnoredPropertyNames() {
    return (this._ignoredPropertyNames == null) ? Collections.emptySet() : this._ignoredPropertyNames;
  }
  
  public Set getIgnoredPropertyNamesForDeser() {
    return this._ignoredPropertyNamesForDeser;
  }
  
  public boolean hasKnownClassAnnotations() {
    return this._classInfo.hasAnnotations();
  }
  
  public Object instantiateBean(boolean paramBoolean) {
    AnnotatedConstructor annotatedConstructor = this._classInfo.getDefaultConstructor();
    if (annotatedConstructor == null)
      return null; 
    if (paramBoolean)
      annotatedConstructor.fixAccess(); 
    try {
      annotatedConstructor = annotatedConstructor.getAnnotated().newInstance(new Object[0]);
    } catch (Exception exception) {}
    return exception;
  }
  
  protected boolean isFactoryMethod(AnnotatedMethod paramAnnotatedMethod) {
    boolean bool = false;
    Class<?> clazz = paramAnnotatedMethod.getRawType();
    if (getBeanClass().isAssignableFrom(clazz)) {
      if (this._annotationIntrospector.hasCreatorAnnotation(paramAnnotatedMethod))
        return true; 
      if ("valueOf".equals(paramAnnotatedMethod.getName()))
        bool = true; 
    } 
    return bool;
  }
  
  public JavaType resolveType(Type paramType) {
    return (paramType == null) ? null : bindingsForBeanType().resolveType(paramType);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\introspect\BasicBeanDescription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */