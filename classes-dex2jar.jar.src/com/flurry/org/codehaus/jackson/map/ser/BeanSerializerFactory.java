package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.BeanDescription;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.BeanPropertyDefinition;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.MapperConfig;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.SerializerFactory;
import com.flurry.org.codehaus.jackson.map.Serializers;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.introspect.Annotated;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.flurry.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import com.flurry.org.codehaus.jackson.map.ser.std.MapSerializer;
import com.flurry.org.codehaus.jackson.map.type.TypeBindings;
import com.flurry.org.codehaus.jackson.map.util.ArrayBuilders;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class BeanSerializerFactory extends BasicSerializerFactory {
  public static final BeanSerializerFactory instance = new BeanSerializerFactory(null);
  
  protected final SerializerFactory.Config _factoryConfig;
  
  protected BeanSerializerFactory(SerializerFactory.Config paramConfig) {
    SerializerFactory.Config config = paramConfig;
    if (paramConfig == null)
      config = new BeanSerializerFactory$ConfigImpl(); 
    this._factoryConfig = config;
  }
  
  protected BeanPropertyWriter _constructWriter(SerializationConfig paramSerializationConfig, TypeBindings paramTypeBindings, PropertyBuilder paramPropertyBuilder, boolean paramBoolean, String paramString, AnnotatedMember paramAnnotatedMember) {
    TypeSerializer typeSerializer;
    if (paramSerializationConfig.isEnabled(SerializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS))
      paramAnnotatedMember.fixAccess(); 
    JavaType javaType = paramAnnotatedMember.getType(paramTypeBindings);
    BeanProperty.Std std = new BeanProperty.Std(paramString, javaType, paramPropertyBuilder.getClassAnnotations(), paramAnnotatedMember);
    JsonSerializer jsonSerializer = findSerializerFromAnnotation(paramSerializationConfig, (Annotated)paramAnnotatedMember, (BeanProperty)std);
    paramTypeBindings = null;
    if (ClassUtil.isCollectionMapOrArray(javaType.getRawClass()))
      typeSerializer = findPropertyContentTypeSerializer(javaType, paramSerializationConfig, paramAnnotatedMember, (BeanProperty)std); 
    BeanPropertyWriter beanPropertyWriter = paramPropertyBuilder.buildWriter(paramString, javaType, jsonSerializer, findPropertyTypeSerializer(javaType, paramSerializationConfig, paramAnnotatedMember, (BeanProperty)std), typeSerializer, paramAnnotatedMember, paramBoolean);
    beanPropertyWriter.setViews(paramSerializationConfig.getAnnotationIntrospector().findSerializationViews((Annotated)paramAnnotatedMember));
    return beanPropertyWriter;
  }
  
  protected JsonSerializer constructBeanSerializer(SerializationConfig paramSerializationConfig, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty) {
    BeanSerializer beanSerializer;
    BeanSerializerBuilder beanSerializerBuilder1;
    if (paramBasicBeanDescription.getBeanClass() == Object.class)
      throw new IllegalArgumentException("Can not create bean serializer for Object.class"); 
    BeanSerializerBuilder beanSerializerBuilder2 = constructBeanSerializerBuilder(paramBasicBeanDescription);
    List list2 = findBeanProperties(paramSerializationConfig, paramBasicBeanDescription);
    List list1 = list2;
    if (list2 == null)
      list1 = new ArrayList(); 
    if (this._factoryConfig.hasSerializerModifiers()) {
      Iterator<BeanSerializerModifier> iterator = this._factoryConfig.serializerModifiers().iterator();
      while (true) {
        list2 = list1;
        if (iterator.hasNext()) {
          list1 = ((BeanSerializerModifier)iterator.next()).changeProperties(paramSerializationConfig, paramBasicBeanDescription, list1);
          continue;
        } 
        break;
      } 
    } else {
      list2 = list1;
    } 
    list1 = sortBeanProperties(paramSerializationConfig, paramBasicBeanDescription, filterBeanProperties(paramSerializationConfig, paramBasicBeanDescription, list2));
    if (this._factoryConfig.hasSerializerModifiers()) {
      Iterator<BeanSerializerModifier> iterator = this._factoryConfig.serializerModifiers().iterator();
      while (true) {
        list2 = list1;
        if (iterator.hasNext()) {
          list1 = ((BeanSerializerModifier)iterator.next()).orderProperties(paramSerializationConfig, paramBasicBeanDescription, list1);
          continue;
        } 
        break;
      } 
    } else {
      list2 = list1;
    } 
    beanSerializerBuilder2.setProperties(list2);
    beanSerializerBuilder2.setFilterId(findFilterId(paramSerializationConfig, paramBasicBeanDescription));
    AnnotatedMethod annotatedMethod = paramBasicBeanDescription.findAnyGetter();
    if (annotatedMethod != null) {
      if (paramSerializationConfig.isEnabled(SerializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS))
        annotatedMethod.fixAccess(); 
      JavaType javaType = annotatedMethod.getType(paramBasicBeanDescription.bindingsForBeanType());
      beanSerializerBuilder2.setAnyGetter(new AnyGetterWriter(annotatedMethod, MapSerializer.construct(null, javaType, paramSerializationConfig.isEnabled(SerializationConfig.Feature.USE_STATIC_TYPING), createTypeSerializer(paramSerializationConfig, javaType.getContentType(), paramBeanProperty), paramBeanProperty, null, null)));
    } 
    processViews(paramSerializationConfig, beanSerializerBuilder2);
    if (this._factoryConfig.hasSerializerModifiers()) {
      Iterator<BeanSerializerModifier> iterator = this._factoryConfig.serializerModifiers().iterator();
      BeanSerializerBuilder beanSerializerBuilder = beanSerializerBuilder2;
      while (true) {
        beanSerializerBuilder1 = beanSerializerBuilder;
        if (iterator.hasNext()) {
          beanSerializerBuilder = ((BeanSerializerModifier)iterator.next()).updateBuilder(paramSerializationConfig, paramBasicBeanDescription, beanSerializerBuilder);
          continue;
        } 
        break;
      } 
    } else {
      beanSerializerBuilder1 = beanSerializerBuilder2;
    } 
    JsonSerializer jsonSerializer2 = beanSerializerBuilder1.build();
    JsonSerializer jsonSerializer1 = jsonSerializer2;
    if (jsonSerializer2 == null) {
      jsonSerializer1 = jsonSerializer2;
      if (paramBasicBeanDescription.hasKnownClassAnnotations())
        beanSerializer = beanSerializerBuilder1.createDummy(); 
    } 
    return (JsonSerializer)beanSerializer;
  }
  
  protected BeanSerializerBuilder constructBeanSerializerBuilder(BasicBeanDescription paramBasicBeanDescription) {
    return new BeanSerializerBuilder(paramBasicBeanDescription);
  }
  
  protected BeanPropertyWriter constructFilteredBeanWriter(BeanPropertyWriter paramBeanPropertyWriter, Class[] paramArrayOfClass) {
    return FilteredBeanPropertyWriter.constructViewBased(paramBeanPropertyWriter, paramArrayOfClass);
  }
  
  protected PropertyBuilder constructPropertyBuilder(SerializationConfig paramSerializationConfig, BasicBeanDescription paramBasicBeanDescription) {
    return new PropertyBuilder(paramSerializationConfig, paramBasicBeanDescription);
  }
  
  public JsonSerializer createKeySerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, BeanProperty paramBeanProperty) {
    JsonSerializer jsonSerializer1 = null;
    JsonSerializer jsonSerializer2 = null;
    if (!this._factoryConfig.hasKeySerializers())
      return jsonSerializer2; 
    BasicBeanDescription basicBeanDescription = (BasicBeanDescription)paramSerializationConfig.introspectClassAnnotations(paramJavaType.getRawClass());
    Iterator<Serializers> iterator = this._factoryConfig.keySerializers().iterator();
    while (true) {
      if (iterator.hasNext()) {
        jsonSerializer2 = ((Serializers)iterator.next()).findSerializer(paramSerializationConfig, paramJavaType, (BeanDescription)basicBeanDescription, paramBeanProperty);
        jsonSerializer1 = jsonSerializer2;
        if (jsonSerializer2 != null)
          return jsonSerializer2; 
        continue;
      } 
      return jsonSerializer1;
    } 
  }
  
  public JsonSerializer createSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, BeanProperty paramBeanProperty) {
    boolean bool;
    BasicBeanDescription basicBeanDescription = (BasicBeanDescription)paramSerializationConfig.introspect(paramJavaType);
    JsonSerializer jsonSerializer3 = findSerializerFromAnnotation(paramSerializationConfig, (Annotated)basicBeanDescription.getClassInfo(), paramBeanProperty);
    if (jsonSerializer3 != null)
      return jsonSerializer3; 
    JavaType javaType = modifyTypeByAnnotation(paramSerializationConfig, (Annotated)basicBeanDescription.getClassInfo(), paramJavaType);
    if (javaType != paramJavaType) {
      bool = true;
    } else {
      bool = false;
    } 
    if (paramJavaType.isContainerType())
      return buildContainerSerializer(paramSerializationConfig, javaType, basicBeanDescription, paramBeanProperty, bool); 
    Iterator<Serializers> iterator = this._factoryConfig.serializers().iterator();
    while (iterator.hasNext()) {
      JsonSerializer jsonSerializer = ((Serializers)iterator.next()).findSerializer(paramSerializationConfig, javaType, (BeanDescription)basicBeanDescription, paramBeanProperty);
      if (jsonSerializer != null)
        return jsonSerializer; 
    } 
    JsonSerializer jsonSerializer2 = findSerializerByLookup(javaType, paramSerializationConfig, basicBeanDescription, paramBeanProperty, bool);
    JsonSerializer jsonSerializer1 = jsonSerializer2;
    if (jsonSerializer2 == null) {
      jsonSerializer2 = findSerializerByPrimaryType(javaType, paramSerializationConfig, basicBeanDescription, paramBeanProperty, bool);
      jsonSerializer1 = jsonSerializer2;
      if (jsonSerializer2 == null) {
        jsonSerializer2 = findBeanSerializer(paramSerializationConfig, javaType, basicBeanDescription, paramBeanProperty);
        jsonSerializer1 = jsonSerializer2;
        if (jsonSerializer2 == null)
          jsonSerializer1 = findSerializerByAddonType(paramSerializationConfig, javaType, basicBeanDescription, paramBeanProperty, bool); 
      } 
    } 
    return jsonSerializer1;
  }
  
  protected Iterable customSerializers() {
    return this._factoryConfig.serializers();
  }
  
  protected List filterBeanProperties(SerializationConfig paramSerializationConfig, BasicBeanDescription paramBasicBeanDescription, List paramList) {
    String[] arrayOfString = paramSerializationConfig.getAnnotationIntrospector().findPropertiesToIgnore(paramBasicBeanDescription.getClassInfo());
    if (arrayOfString != null && arrayOfString.length > 0) {
      HashSet hashSet = ArrayBuilders.arrayToSet((Object[])arrayOfString);
      Iterator<BeanPropertyWriter> iterator = paramList.iterator();
      while (iterator.hasNext()) {
        if (hashSet.contains(((BeanPropertyWriter)iterator.next()).getName()))
          iterator.remove(); 
      } 
    } 
    return paramList;
  }
  
  protected List findBeanProperties(SerializationConfig paramSerializationConfig, BasicBeanDescription paramBasicBeanDescription) {
    SerializationConfig serializationConfig = null;
    List list = paramBasicBeanDescription.findProperties();
    AnnotationIntrospector annotationIntrospector = paramSerializationConfig.getAnnotationIntrospector();
    removeIgnorableTypes(paramSerializationConfig, paramBasicBeanDescription, list);
    if (paramSerializationConfig.isEnabled(SerializationConfig.Feature.REQUIRE_SETTERS_FOR_GETTERS))
      removeSetterlessGetters(paramSerializationConfig, paramBasicBeanDescription, list); 
    if (list.isEmpty())
      return (List)serializationConfig; 
    boolean bool = usesStaticTyping(paramSerializationConfig, paramBasicBeanDescription, null, null);
    PropertyBuilder propertyBuilder = constructPropertyBuilder(paramSerializationConfig, paramBasicBeanDescription);
    ArrayList<BeanPropertyWriter> arrayList = new ArrayList(list.size());
    TypeBindings typeBindings = paramBasicBeanDescription.bindingsForBeanType();
    for (BeanPropertyDefinition beanPropertyDefinition : list) {
      AnnotatedMember annotatedMember = beanPropertyDefinition.getAccessor();
      AnnotationIntrospector.ReferenceProperty referenceProperty = annotationIntrospector.findReferenceType(annotatedMember);
      if (referenceProperty == null || !referenceProperty.isBackReference()) {
        String str = beanPropertyDefinition.getName();
        if (annotatedMember instanceof AnnotatedMethod) {
          arrayList.add(_constructWriter(paramSerializationConfig, typeBindings, propertyBuilder, bool, str, annotatedMember));
          continue;
        } 
        arrayList.add(_constructWriter(paramSerializationConfig, typeBindings, propertyBuilder, bool, str, annotatedMember));
      } 
    } 
    return arrayList;
  }
  
  public JsonSerializer findBeanSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, BasicBeanDescription paramBasicBeanDescription, BeanProperty paramBeanProperty) {
    if (!isPotentialBeanType(paramJavaType.getRawClass()))
      return null; 
    JsonSerializer jsonSerializer = constructBeanSerializer(paramSerializationConfig, paramBasicBeanDescription, paramBeanProperty);
    if (this._factoryConfig.hasSerializerModifiers()) {
      Iterator<BeanSerializerModifier> iterator = this._factoryConfig.serializerModifiers().iterator();
      while (true) {
        JsonSerializer jsonSerializer1 = jsonSerializer;
        if (iterator.hasNext()) {
          jsonSerializer = ((BeanSerializerModifier)iterator.next()).modifySerializer(paramSerializationConfig, paramBasicBeanDescription, jsonSerializer);
          continue;
        } 
        return jsonSerializer1;
      } 
    } 
    return jsonSerializer;
  }
  
  protected Object findFilterId(SerializationConfig paramSerializationConfig, BasicBeanDescription paramBasicBeanDescription) {
    return paramSerializationConfig.getAnnotationIntrospector().findFilterId(paramBasicBeanDescription.getClassInfo());
  }
  
  public TypeSerializer findPropertyContentTypeSerializer(JavaType paramJavaType, SerializationConfig paramSerializationConfig, AnnotatedMember paramAnnotatedMember, BeanProperty paramBeanProperty) {
    JavaType javaType = paramJavaType.getContentType();
    AnnotationIntrospector annotationIntrospector = paramSerializationConfig.getAnnotationIntrospector();
    TypeResolverBuilder typeResolverBuilder = annotationIntrospector.findPropertyContentTypeResolver((MapperConfig)paramSerializationConfig, paramAnnotatedMember, paramJavaType);
    return (typeResolverBuilder == null) ? createTypeSerializer(paramSerializationConfig, javaType, paramBeanProperty) : typeResolverBuilder.buildTypeSerializer(paramSerializationConfig, javaType, paramSerializationConfig.getSubtypeResolver().collectAndResolveSubtypes(paramAnnotatedMember, (MapperConfig)paramSerializationConfig, annotationIntrospector), paramBeanProperty);
  }
  
  public TypeSerializer findPropertyTypeSerializer(JavaType paramJavaType, SerializationConfig paramSerializationConfig, AnnotatedMember paramAnnotatedMember, BeanProperty paramBeanProperty) {
    AnnotationIntrospector annotationIntrospector = paramSerializationConfig.getAnnotationIntrospector();
    TypeResolverBuilder typeResolverBuilder = annotationIntrospector.findPropertyTypeResolver((MapperConfig)paramSerializationConfig, paramAnnotatedMember, paramJavaType);
    return (typeResolverBuilder == null) ? createTypeSerializer(paramSerializationConfig, paramJavaType, paramBeanProperty) : typeResolverBuilder.buildTypeSerializer(paramSerializationConfig, paramJavaType, paramSerializationConfig.getSubtypeResolver().collectAndResolveSubtypes(paramAnnotatedMember, (MapperConfig)paramSerializationConfig, annotationIntrospector), paramBeanProperty);
  }
  
  public SerializerFactory.Config getConfig() {
    return this._factoryConfig;
  }
  
  protected boolean isPotentialBeanType(Class paramClass) {
    return (ClassUtil.canBeABeanType(paramClass) == null && !ClassUtil.isProxyType(paramClass));
  }
  
  protected void processViews(SerializationConfig paramSerializationConfig, BeanSerializerBuilder paramBeanSerializerBuilder) {
    List<BeanPropertyWriter> list = paramBeanSerializerBuilder.getProperties();
    boolean bool = paramSerializationConfig.isEnabled(SerializationConfig.Feature.DEFAULT_VIEW_INCLUSION);
    int j = list.size();
    BeanPropertyWriter[] arrayOfBeanPropertyWriter = new BeanPropertyWriter[j];
    byte b = 0;
    int i = 0;
    while (b < j) {
      int k;
      BeanPropertyWriter beanPropertyWriter = list.get(b);
      Class[] arrayOfClass = beanPropertyWriter.getViews();
      if (arrayOfClass == null) {
        k = i;
        if (bool) {
          arrayOfBeanPropertyWriter[b] = beanPropertyWriter;
          continue;
        } 
      } else {
        k = i + 1;
        arrayOfBeanPropertyWriter[b] = constructFilteredBeanWriter(beanPropertyWriter, arrayOfClass);
      } 
      i = k;
      continue;
      b++;
    } 
    if (!bool || i != 0)
      paramBeanSerializerBuilder.setFilteredProperties(arrayOfBeanPropertyWriter); 
  }
  
  protected void removeIgnorableTypes(SerializationConfig paramSerializationConfig, BasicBeanDescription paramBasicBeanDescription, List paramList) {
    AnnotationIntrospector annotationIntrospector = paramSerializationConfig.getAnnotationIntrospector();
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    Iterator<BeanPropertyDefinition> iterator = paramList.iterator();
    while (iterator.hasNext()) {
      AnnotatedMember annotatedMember = ((BeanPropertyDefinition)iterator.next()).getAccessor();
      if (annotatedMember == null) {
        iterator.remove();
        continue;
      } 
      Class clazz = annotatedMember.getRawType();
      Boolean bool2 = (Boolean)hashMap.get(clazz);
      Boolean bool1 = bool2;
      if (bool2 == null) {
        bool2 = annotationIntrospector.isIgnorableType(((BasicBeanDescription)paramSerializationConfig.introspectClassAnnotations(clazz)).getClassInfo());
        bool1 = bool2;
        if (bool2 == null)
          bool1 = Boolean.FALSE; 
        hashMap.put(clazz, bool1);
      } 
      if (bool1.booleanValue())
        iterator.remove(); 
    } 
  }
  
  protected void removeSetterlessGetters(SerializationConfig paramSerializationConfig, BasicBeanDescription paramBasicBeanDescription, List paramList) {
    Iterator<BeanPropertyDefinition> iterator = paramList.iterator();
    while (iterator.hasNext()) {
      if (!((BeanPropertyDefinition)iterator.next()).couldDeserialize())
        iterator.remove(); 
    } 
  }
  
  @Deprecated
  protected List sortBeanProperties(SerializationConfig paramSerializationConfig, BasicBeanDescription paramBasicBeanDescription, List paramList) {
    return paramList;
  }
  
  public SerializerFactory withConfig(SerializerFactory.Config paramConfig) {
    if (this._factoryConfig == paramConfig)
      return this; 
    if (getClass() != BeanSerializerFactory.class)
      throw new IllegalStateException("Subtype of BeanSerializerFactory (" + getClass().getName() + ") has not properly overridden method 'withAdditionalSerializers': can not instantiate subtype with " + "additional serializer definitions"); 
    return new BeanSerializerFactory(paramConfig);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\BeanSerializerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */