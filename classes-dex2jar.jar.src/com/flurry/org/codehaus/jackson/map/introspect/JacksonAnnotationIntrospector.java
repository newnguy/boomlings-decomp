package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.annotate.JacksonAnnotation;
import com.flurry.org.codehaus.jackson.annotate.JsonAnyGetter;
import com.flurry.org.codehaus.jackson.annotate.JsonAnySetter;
import com.flurry.org.codehaus.jackson.annotate.JsonAutoDetect;
import com.flurry.org.codehaus.jackson.annotate.JsonBackReference;
import com.flurry.org.codehaus.jackson.annotate.JsonCreator;
import com.flurry.org.codehaus.jackson.annotate.JsonGetter;
import com.flurry.org.codehaus.jackson.annotate.JsonIgnore;
import com.flurry.org.codehaus.jackson.annotate.JsonIgnoreProperties;
import com.flurry.org.codehaus.jackson.annotate.JsonIgnoreType;
import com.flurry.org.codehaus.jackson.annotate.JsonManagedReference;
import com.flurry.org.codehaus.jackson.annotate.JsonProperty;
import com.flurry.org.codehaus.jackson.annotate.JsonPropertyOrder;
import com.flurry.org.codehaus.jackson.annotate.JsonRawValue;
import com.flurry.org.codehaus.jackson.annotate.JsonSetter;
import com.flurry.org.codehaus.jackson.annotate.JsonSubTypes;
import com.flurry.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.flurry.org.codehaus.jackson.annotate.JsonTypeName;
import com.flurry.org.codehaus.jackson.annotate.JsonUnwrapped;
import com.flurry.org.codehaus.jackson.annotate.JsonValue;
import com.flurry.org.codehaus.jackson.annotate.JsonWriteNullProperties;
import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.KeyDeserializer;
import com.flurry.org.codehaus.jackson.map.MapperConfig;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonInject;
import com.flurry.org.codehaus.jackson.map.annotate.JsonCachable;
import com.flurry.org.codehaus.jackson.map.annotate.JsonDeserialize;
import com.flurry.org.codehaus.jackson.map.annotate.JsonFilter;
import com.flurry.org.codehaus.jackson.map.annotate.JsonRootName;
import com.flurry.org.codehaus.jackson.map.annotate.JsonSerialize;
import com.flurry.org.codehaus.jackson.map.annotate.JsonTypeIdResolver;
import com.flurry.org.codehaus.jackson.map.annotate.JsonTypeResolver;
import com.flurry.org.codehaus.jackson.map.annotate.JsonValueInstantiator;
import com.flurry.org.codehaus.jackson.map.annotate.JsonView;
import com.flurry.org.codehaus.jackson.map.annotate.NoClass;
import com.flurry.org.codehaus.jackson.map.jsontype.NamedType;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeIdResolver;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import com.flurry.org.codehaus.jackson.map.jsontype.impl.StdTypeResolverBuilder;
import com.flurry.org.codehaus.jackson.map.ser.std.RawSerializer;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class JacksonAnnotationIntrospector extends AnnotationIntrospector {
  protected StdTypeResolverBuilder _constructNoTypeResolverBuilder() {
    return StdTypeResolverBuilder.noTypeInfoBuilder();
  }
  
  protected StdTypeResolverBuilder _constructStdTypeResolverBuilder() {
    return new StdTypeResolverBuilder();
  }
  
  protected TypeResolverBuilder _findTypeResolver(MapperConfig paramMapperConfig, Annotated paramAnnotated, JavaType paramJavaType) {
    TypeIdResolver typeIdResolver;
    StdTypeResolverBuilder stdTypeResolverBuilder;
    MapperConfig mapperConfig = null;
    JsonTypeInfo jsonTypeInfo = (JsonTypeInfo)paramAnnotated.getAnnotation(JsonTypeInfo.class);
    JsonTypeResolver jsonTypeResolver = (JsonTypeResolver)paramAnnotated.getAnnotation(JsonTypeResolver.class);
    if (jsonTypeResolver != null) {
      if (jsonTypeInfo == null)
        return null; 
      TypeResolverBuilder typeResolverBuilder = paramMapperConfig.typeResolverBuilderInstance(paramAnnotated, jsonTypeResolver.value());
    } else {
      if (jsonTypeInfo == null)
        return null; 
      if (jsonTypeInfo.use() == JsonTypeInfo.Id.NONE)
        return (TypeResolverBuilder)_constructNoTypeResolverBuilder(); 
      stdTypeResolverBuilder = _constructStdTypeResolverBuilder();
    } 
    JsonTypeIdResolver jsonTypeIdResolver = (JsonTypeIdResolver)paramAnnotated.getAnnotation(JsonTypeIdResolver.class);
    if (jsonTypeIdResolver == null) {
      paramMapperConfig = mapperConfig;
    } else {
      typeIdResolver = paramMapperConfig.typeIdResolverInstance(paramAnnotated, jsonTypeIdResolver.value());
    } 
    if (typeIdResolver != null)
      typeIdResolver.init(paramJavaType); 
    TypeResolverBuilder typeResolverBuilder2 = stdTypeResolverBuilder.init(jsonTypeInfo.use(), typeIdResolver);
    JsonTypeInfo.As as2 = jsonTypeInfo.include();
    JsonTypeInfo.As as1 = as2;
    if (as2 == JsonTypeInfo.As.EXTERNAL_PROPERTY) {
      as1 = as2;
      if (paramAnnotated instanceof AnnotatedClass)
        as1 = JsonTypeInfo.As.PROPERTY; 
    } 
    TypeResolverBuilder typeResolverBuilder1 = typeResolverBuilder2.inclusion(as1).typeProperty(jsonTypeInfo.property());
    Class<JsonTypeInfo.None> clazz = jsonTypeInfo.defaultImpl();
    if (clazz != JsonTypeInfo.None.class)
      typeResolverBuilder1 = typeResolverBuilder1.defaultImpl(clazz); 
    return typeResolverBuilder1;
  }
  
  protected boolean _isIgnorable(Annotated paramAnnotated) {
    JsonIgnore jsonIgnore = (JsonIgnore)paramAnnotated.getAnnotation(JsonIgnore.class);
    return (jsonIgnore != null && jsonIgnore.value());
  }
  
  public VisibilityChecker findAutoDetectVisibility(AnnotatedClass paramAnnotatedClass, VisibilityChecker paramVisibilityChecker) {
    JsonAutoDetect jsonAutoDetect = (JsonAutoDetect)paramAnnotatedClass.getAnnotation(JsonAutoDetect.class);
    if (jsonAutoDetect != null)
      paramVisibilityChecker = paramVisibilityChecker.with(jsonAutoDetect); 
    return paramVisibilityChecker;
  }
  
  public Boolean findCachability(AnnotatedClass paramAnnotatedClass) {
    JsonCachable jsonCachable = (JsonCachable)paramAnnotatedClass.getAnnotation(JsonCachable.class);
    return (jsonCachable == null) ? null : (jsonCachable.value() ? Boolean.TRUE : Boolean.FALSE);
  }
  
  public Class findContentDeserializer(Annotated paramAnnotated) {
    null = (JsonDeserialize)paramAnnotated.getAnnotation(JsonDeserialize.class);
    if (null != null) {
      Class<JsonDeserializer.None> clazz = null.contentUsing();
      if (clazz != JsonDeserializer.None.class)
        return clazz; 
    } 
    return null;
  }
  
  public Class findContentSerializer(Annotated paramAnnotated) {
    null = (JsonSerialize)paramAnnotated.getAnnotation(JsonSerialize.class);
    if (null != null) {
      Class<JsonSerializer.None> clazz = null.contentUsing();
      if (clazz != JsonSerializer.None.class)
        return clazz; 
    } 
    return null;
  }
  
  public String findDeserializablePropertyName(AnnotatedField paramAnnotatedField) {
    JsonProperty jsonProperty = (JsonProperty)paramAnnotatedField.getAnnotation(JsonProperty.class);
    return (jsonProperty != null) ? jsonProperty.value() : ((paramAnnotatedField.hasAnnotation(JsonDeserialize.class) || paramAnnotatedField.hasAnnotation(JsonView.class) || paramAnnotatedField.hasAnnotation(JsonBackReference.class) || paramAnnotatedField.hasAnnotation(JsonManagedReference.class)) ? "" : null);
  }
  
  public Class findDeserializationContentType(Annotated paramAnnotated, JavaType paramJavaType, String paramString) {
    null = (JsonDeserialize)paramAnnotated.getAnnotation(JsonDeserialize.class);
    if (null != null) {
      Class<NoClass> clazz = null.contentAs();
      if (clazz != NoClass.class)
        return clazz; 
    } 
    return null;
  }
  
  public Class findDeserializationKeyType(Annotated paramAnnotated, JavaType paramJavaType, String paramString) {
    null = (JsonDeserialize)paramAnnotated.getAnnotation(JsonDeserialize.class);
    if (null != null) {
      Class<NoClass> clazz = null.keyAs();
      if (clazz != NoClass.class)
        return clazz; 
    } 
    return null;
  }
  
  public Class findDeserializationType(Annotated paramAnnotated, JavaType paramJavaType, String paramString) {
    null = (JsonDeserialize)paramAnnotated.getAnnotation(JsonDeserialize.class);
    if (null != null) {
      Class<NoClass> clazz = null.as();
      if (clazz != NoClass.class)
        return clazz; 
    } 
    return null;
  }
  
  public Class findDeserializer(Annotated paramAnnotated) {
    null = (JsonDeserialize)paramAnnotated.getAnnotation(JsonDeserialize.class);
    if (null != null) {
      Class<JsonDeserializer.None> clazz = null.using();
      if (clazz != JsonDeserializer.None.class)
        return clazz; 
    } 
    return null;
  }
  
  public String findEnumValue(Enum paramEnum) {
    return paramEnum.name();
  }
  
  public Object findFilterId(AnnotatedClass paramAnnotatedClass) {
    null = (JsonFilter)paramAnnotatedClass.getAnnotation(JsonFilter.class);
    if (null != null) {
      String str = null.value();
      if (str.length() > 0)
        return str; 
    } 
    return null;
  }
  
  public String findGettablePropertyName(AnnotatedMethod paramAnnotatedMethod) {
    JsonProperty jsonProperty = (JsonProperty)paramAnnotatedMethod.getAnnotation(JsonProperty.class);
    if (jsonProperty != null)
      return jsonProperty.value(); 
    JsonGetter jsonGetter = (JsonGetter)paramAnnotatedMethod.getAnnotation(JsonGetter.class);
    return (jsonGetter != null) ? jsonGetter.value() : ((paramAnnotatedMethod.hasAnnotation(JsonSerialize.class) || paramAnnotatedMethod.hasAnnotation(JsonView.class)) ? "" : null);
  }
  
  public Boolean findIgnoreUnknownProperties(AnnotatedClass paramAnnotatedClass) {
    JsonIgnoreProperties jsonIgnoreProperties = (JsonIgnoreProperties)paramAnnotatedClass.getAnnotation(JsonIgnoreProperties.class);
    return (jsonIgnoreProperties == null) ? null : Boolean.valueOf(jsonIgnoreProperties.ignoreUnknown());
  }
  
  public Object findInjectableValueId(AnnotatedMember paramAnnotatedMember) {
    JacksonInject jacksonInject = (JacksonInject)paramAnnotatedMember.getAnnotation(JacksonInject.class);
    if (jacksonInject == null)
      return null; 
    String str2 = jacksonInject.value();
    String str1 = str2;
    if (str2.length() == 0) {
      if (!(paramAnnotatedMember instanceof AnnotatedMethod))
        return paramAnnotatedMember.getRawType().getName(); 
      AnnotatedMethod annotatedMethod = (AnnotatedMethod)paramAnnotatedMember;
      if (annotatedMethod.getParameterCount() == 0)
        return paramAnnotatedMember.getRawType().getName(); 
      str1 = annotatedMethod.getParameterClass(0).getName();
    } 
    return str1;
  }
  
  public Class findKeyDeserializer(Annotated paramAnnotated) {
    null = (JsonDeserialize)paramAnnotated.getAnnotation(JsonDeserialize.class);
    if (null != null) {
      Class<KeyDeserializer.None> clazz = null.keyUsing();
      if (clazz != KeyDeserializer.None.class)
        return clazz; 
    } 
    return null;
  }
  
  public Class findKeySerializer(Annotated paramAnnotated) {
    null = (JsonSerialize)paramAnnotated.getAnnotation(JsonSerialize.class);
    if (null != null) {
      Class<JsonSerializer.None> clazz = null.keyUsing();
      if (clazz != JsonSerializer.None.class)
        return clazz; 
    } 
    return null;
  }
  
  public String[] findPropertiesToIgnore(AnnotatedClass paramAnnotatedClass) {
    JsonIgnoreProperties jsonIgnoreProperties = (JsonIgnoreProperties)paramAnnotatedClass.getAnnotation(JsonIgnoreProperties.class);
    return (jsonIgnoreProperties == null) ? null : jsonIgnoreProperties.value();
  }
  
  public TypeResolverBuilder findPropertyContentTypeResolver(MapperConfig paramMapperConfig, AnnotatedMember paramAnnotatedMember, JavaType paramJavaType) {
    if (!paramJavaType.isContainerType())
      throw new IllegalArgumentException("Must call method with a container type (got " + paramJavaType + ")"); 
    return _findTypeResolver(paramMapperConfig, paramAnnotatedMember, paramJavaType);
  }
  
  public String findPropertyNameForParam(AnnotatedParameter paramAnnotatedParameter) {
    if (paramAnnotatedParameter != null) {
      JsonProperty jsonProperty = (JsonProperty)paramAnnotatedParameter.getAnnotation(JsonProperty.class);
      if (jsonProperty != null)
        return jsonProperty.value(); 
    } 
    return null;
  }
  
  public TypeResolverBuilder findPropertyTypeResolver(MapperConfig paramMapperConfig, AnnotatedMember paramAnnotatedMember, JavaType paramJavaType) {
    return paramJavaType.isContainerType() ? null : _findTypeResolver(paramMapperConfig, paramAnnotatedMember, paramJavaType);
  }
  
  public AnnotationIntrospector.ReferenceProperty findReferenceType(AnnotatedMember paramAnnotatedMember) {
    JsonManagedReference jsonManagedReference = (JsonManagedReference)paramAnnotatedMember.getAnnotation(JsonManagedReference.class);
    if (jsonManagedReference != null)
      return AnnotationIntrospector.ReferenceProperty.managed(jsonManagedReference.value()); 
    null = (JsonBackReference)paramAnnotatedMember.getAnnotation(JsonBackReference.class);
    return (null != null) ? AnnotationIntrospector.ReferenceProperty.back(null.value()) : null;
  }
  
  public String findRootName(AnnotatedClass paramAnnotatedClass) {
    JsonRootName jsonRootName = (JsonRootName)paramAnnotatedClass.getAnnotation(JsonRootName.class);
    return (jsonRootName == null) ? null : jsonRootName.value();
  }
  
  public String findSerializablePropertyName(AnnotatedField paramAnnotatedField) {
    JsonProperty jsonProperty = (JsonProperty)paramAnnotatedField.getAnnotation(JsonProperty.class);
    return (jsonProperty != null) ? jsonProperty.value() : ((paramAnnotatedField.hasAnnotation(JsonSerialize.class) || paramAnnotatedField.hasAnnotation(JsonView.class)) ? "" : null);
  }
  
  public Class findSerializationContentType(Annotated paramAnnotated, JavaType paramJavaType) {
    null = (JsonSerialize)paramAnnotated.getAnnotation(JsonSerialize.class);
    if (null != null) {
      Class<NoClass> clazz = null.contentAs();
      if (clazz != NoClass.class)
        return clazz; 
    } 
    return null;
  }
  
  public JsonSerialize.Inclusion findSerializationInclusion(Annotated paramAnnotated, JsonSerialize.Inclusion paramInclusion) {
    JsonSerialize jsonSerialize = (JsonSerialize)paramAnnotated.getAnnotation(JsonSerialize.class);
    if (jsonSerialize != null)
      return jsonSerialize.include(); 
    JsonWriteNullProperties jsonWriteNullProperties = (JsonWriteNullProperties)paramAnnotated.getAnnotation(JsonWriteNullProperties.class);
    return (jsonWriteNullProperties != null) ? (jsonWriteNullProperties.value() ? JsonSerialize.Inclusion.ALWAYS : JsonSerialize.Inclusion.NON_NULL) : paramInclusion;
  }
  
  public Class findSerializationKeyType(Annotated paramAnnotated, JavaType paramJavaType) {
    null = (JsonSerialize)paramAnnotated.getAnnotation(JsonSerialize.class);
    if (null != null) {
      Class<NoClass> clazz = null.keyAs();
      if (clazz != NoClass.class)
        return clazz; 
    } 
    return null;
  }
  
  public String[] findSerializationPropertyOrder(AnnotatedClass paramAnnotatedClass) {
    JsonPropertyOrder jsonPropertyOrder = (JsonPropertyOrder)paramAnnotatedClass.getAnnotation(JsonPropertyOrder.class);
    return (jsonPropertyOrder == null) ? null : jsonPropertyOrder.value();
  }
  
  public Boolean findSerializationSortAlphabetically(AnnotatedClass paramAnnotatedClass) {
    JsonPropertyOrder jsonPropertyOrder = (JsonPropertyOrder)paramAnnotatedClass.getAnnotation(JsonPropertyOrder.class);
    return (jsonPropertyOrder == null) ? null : Boolean.valueOf(jsonPropertyOrder.alphabetic());
  }
  
  public Class findSerializationType(Annotated paramAnnotated) {
    null = (JsonSerialize)paramAnnotated.getAnnotation(JsonSerialize.class);
    if (null != null) {
      Class<NoClass> clazz = null.as();
      if (clazz != NoClass.class)
        return clazz; 
    } 
    return null;
  }
  
  public JsonSerialize.Typing findSerializationTyping(Annotated paramAnnotated) {
    JsonSerialize jsonSerialize = (JsonSerialize)paramAnnotated.getAnnotation(JsonSerialize.class);
    return (jsonSerialize == null) ? null : jsonSerialize.typing();
  }
  
  public Class[] findSerializationViews(Annotated paramAnnotated) {
    JsonView jsonView = (JsonView)paramAnnotated.getAnnotation(JsonView.class);
    return (jsonView == null) ? null : jsonView.value();
  }
  
  public Object findSerializer(Annotated paramAnnotated) {
    JsonSerialize jsonSerialize = (JsonSerialize)paramAnnotated.getAnnotation(JsonSerialize.class);
    if (jsonSerialize != null) {
      Class<JsonSerializer.None> clazz = jsonSerialize.using();
      if (clazz != JsonSerializer.None.class)
        return clazz; 
    } 
    JsonRawValue jsonRawValue = (JsonRawValue)paramAnnotated.getAnnotation(JsonRawValue.class);
    return (jsonRawValue != null && jsonRawValue.value()) ? new RawSerializer(paramAnnotated.getRawType()) : null;
  }
  
  public String findSettablePropertyName(AnnotatedMethod paramAnnotatedMethod) {
    JsonProperty jsonProperty = (JsonProperty)paramAnnotatedMethod.getAnnotation(JsonProperty.class);
    if (jsonProperty != null)
      return jsonProperty.value(); 
    JsonSetter jsonSetter = (JsonSetter)paramAnnotatedMethod.getAnnotation(JsonSetter.class);
    return (jsonSetter != null) ? jsonSetter.value() : ((paramAnnotatedMethod.hasAnnotation(JsonDeserialize.class) || paramAnnotatedMethod.hasAnnotation(JsonView.class) || paramAnnotatedMethod.hasAnnotation(JsonBackReference.class) || paramAnnotatedMethod.hasAnnotation(JsonManagedReference.class)) ? "" : null);
  }
  
  public List findSubtypes(Annotated paramAnnotated) {
    JsonSubTypes jsonSubTypes = (JsonSubTypes)paramAnnotated.getAnnotation(JsonSubTypes.class);
    if (jsonSubTypes == null)
      return null; 
    JsonSubTypes.Type[] arrayOfType = jsonSubTypes.value();
    ArrayList<NamedType> arrayList = new ArrayList(arrayOfType.length);
    int i = arrayOfType.length;
    byte b = 0;
    while (true) {
      JsonSubTypes.Type type;
      ArrayList<NamedType> arrayList1 = arrayList;
      if (b < i) {
        type = arrayOfType[b];
        arrayList.add(new NamedType(type.value(), type.name()));
        b++;
        continue;
      } 
      return (List)type;
    } 
  }
  
  public String findTypeName(AnnotatedClass paramAnnotatedClass) {
    JsonTypeName jsonTypeName = (JsonTypeName)paramAnnotatedClass.getAnnotation(JsonTypeName.class);
    return (jsonTypeName == null) ? null : jsonTypeName.value();
  }
  
  public TypeResolverBuilder findTypeResolver(MapperConfig paramMapperConfig, AnnotatedClass paramAnnotatedClass, JavaType paramJavaType) {
    return _findTypeResolver(paramMapperConfig, paramAnnotatedClass, paramJavaType);
  }
  
  public Object findValueInstantiator(AnnotatedClass paramAnnotatedClass) {
    JsonValueInstantiator jsonValueInstantiator = (JsonValueInstantiator)paramAnnotatedClass.getAnnotation(JsonValueInstantiator.class);
    return (jsonValueInstantiator == null) ? null : jsonValueInstantiator.value();
  }
  
  public boolean hasAnyGetterAnnotation(AnnotatedMethod paramAnnotatedMethod) {
    return paramAnnotatedMethod.hasAnnotation(JsonAnyGetter.class);
  }
  
  public boolean hasAnySetterAnnotation(AnnotatedMethod paramAnnotatedMethod) {
    return paramAnnotatedMethod.hasAnnotation(JsonAnySetter.class);
  }
  
  public boolean hasAsValueAnnotation(AnnotatedMethod paramAnnotatedMethod) {
    JsonValue jsonValue = (JsonValue)paramAnnotatedMethod.getAnnotation(JsonValue.class);
    return (jsonValue != null && jsonValue.value());
  }
  
  public boolean hasCreatorAnnotation(Annotated paramAnnotated) {
    return paramAnnotated.hasAnnotation(JsonCreator.class);
  }
  
  public boolean hasIgnoreMarker(AnnotatedMember paramAnnotatedMember) {
    return _isIgnorable(paramAnnotatedMember);
  }
  
  public boolean isHandled(Annotation paramAnnotation) {
    return (paramAnnotation.annotationType().getAnnotation(JacksonAnnotation.class) != null);
  }
  
  public boolean isIgnorableConstructor(AnnotatedConstructor paramAnnotatedConstructor) {
    return _isIgnorable(paramAnnotatedConstructor);
  }
  
  public boolean isIgnorableField(AnnotatedField paramAnnotatedField) {
    return _isIgnorable(paramAnnotatedField);
  }
  
  public boolean isIgnorableMethod(AnnotatedMethod paramAnnotatedMethod) {
    return _isIgnorable(paramAnnotatedMethod);
  }
  
  public Boolean isIgnorableType(AnnotatedClass paramAnnotatedClass) {
    JsonIgnoreType jsonIgnoreType = (JsonIgnoreType)paramAnnotatedClass.getAnnotation(JsonIgnoreType.class);
    return (jsonIgnoreType == null) ? null : Boolean.valueOf(jsonIgnoreType.value());
  }
  
  public Boolean shouldUnwrapProperty(AnnotatedMember paramAnnotatedMember) {
    null = (JsonUnwrapped)paramAnnotatedMember.getAnnotation(JsonUnwrapped.class);
    return (null != null && null.enabled()) ? Boolean.TRUE : null;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\introspect\JacksonAnnotationIntrospector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */