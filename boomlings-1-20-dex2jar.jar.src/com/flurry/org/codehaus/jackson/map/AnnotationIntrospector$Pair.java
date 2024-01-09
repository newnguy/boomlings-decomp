package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.map.annotate.JsonSerialize;
import com.flurry.org.codehaus.jackson.map.introspect.Annotated;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedClass;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedField;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedParameter;
import com.flurry.org.codehaus.jackson.map.introspect.VisibilityChecker;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AnnotationIntrospector$Pair extends AnnotationIntrospector {
  protected final AnnotationIntrospector _primary;
  
  protected final AnnotationIntrospector _secondary;
  
  public AnnotationIntrospector$Pair(AnnotationIntrospector paramAnnotationIntrospector1, AnnotationIntrospector paramAnnotationIntrospector2) {
    this._primary = paramAnnotationIntrospector1;
    this._secondary = paramAnnotationIntrospector2;
  }
  
  public static AnnotationIntrospector create(AnnotationIntrospector paramAnnotationIntrospector1, AnnotationIntrospector paramAnnotationIntrospector2) {
    if (paramAnnotationIntrospector1 != null) {
      if (paramAnnotationIntrospector2 == null)
        return paramAnnotationIntrospector1; 
      paramAnnotationIntrospector2 = new AnnotationIntrospector$Pair(paramAnnotationIntrospector1, paramAnnotationIntrospector2);
    } 
    return paramAnnotationIntrospector2;
  }
  
  public Collection allIntrospectors() {
    return allIntrospectors(new ArrayList());
  }
  
  public Collection allIntrospectors(Collection paramCollection) {
    this._primary.allIntrospectors(paramCollection);
    this._secondary.allIntrospectors(paramCollection);
    return paramCollection;
  }
  
  public VisibilityChecker findAutoDetectVisibility(AnnotatedClass paramAnnotatedClass, VisibilityChecker paramVisibilityChecker) {
    paramVisibilityChecker = this._secondary.findAutoDetectVisibility(paramAnnotatedClass, paramVisibilityChecker);
    return this._primary.findAutoDetectVisibility(paramAnnotatedClass, paramVisibilityChecker);
  }
  
  public Boolean findCachability(AnnotatedClass paramAnnotatedClass) {
    Boolean bool2 = this._primary.findCachability(paramAnnotatedClass);
    Boolean bool1 = bool2;
    if (bool2 == null)
      bool1 = this._secondary.findCachability(paramAnnotatedClass); 
    return bool1;
  }
  
  public Class findContentDeserializer(Annotated paramAnnotated) {
    Class<JsonDeserializer$None> clazz = this._primary.findContentDeserializer(paramAnnotated);
    if (clazz != null) {
      Class<JsonDeserializer$None> clazz1 = clazz;
      return (clazz == JsonDeserializer$None.class) ? this._secondary.findContentDeserializer(paramAnnotated) : clazz1;
    } 
    return this._secondary.findContentDeserializer(paramAnnotated);
  }
  
  public Class findContentSerializer(Annotated paramAnnotated) {
    Class<JsonSerializer$None> clazz = this._primary.findContentSerializer(paramAnnotated);
    if (clazz != null) {
      Class<JsonSerializer$None> clazz1 = clazz;
      return (clazz == JsonSerializer$None.class) ? this._secondary.findContentSerializer(paramAnnotated) : clazz1;
    } 
    return this._secondary.findContentSerializer(paramAnnotated);
  }
  
  public String findDeserializablePropertyName(AnnotatedField paramAnnotatedField) {
    String str = this._primary.findDeserializablePropertyName(paramAnnotatedField);
    if (str == null)
      return this._secondary.findDeserializablePropertyName(paramAnnotatedField); 
    if (str.length() == 0) {
      String str2 = this._secondary.findDeserializablePropertyName(paramAnnotatedField);
      String str1 = str2;
      return (str2 == null) ? str : str1;
    } 
    return str;
  }
  
  public Class findDeserializationContentType(Annotated paramAnnotated, JavaType paramJavaType, String paramString) {
    Class clazz2 = this._primary.findDeserializationContentType(paramAnnotated, paramJavaType, paramString);
    Class clazz1 = clazz2;
    if (clazz2 == null)
      clazz1 = this._secondary.findDeserializationContentType(paramAnnotated, paramJavaType, paramString); 
    return clazz1;
  }
  
  public Class findDeserializationKeyType(Annotated paramAnnotated, JavaType paramJavaType, String paramString) {
    Class clazz2 = this._primary.findDeserializationKeyType(paramAnnotated, paramJavaType, paramString);
    Class clazz1 = clazz2;
    if (clazz2 == null)
      clazz1 = this._secondary.findDeserializationKeyType(paramAnnotated, paramJavaType, paramString); 
    return clazz1;
  }
  
  public Class findDeserializationType(Annotated paramAnnotated, JavaType paramJavaType, String paramString) {
    Class clazz2 = this._primary.findDeserializationType(paramAnnotated, paramJavaType, paramString);
    Class clazz1 = clazz2;
    if (clazz2 == null)
      clazz1 = this._secondary.findDeserializationType(paramAnnotated, paramJavaType, paramString); 
    return clazz1;
  }
  
  public Object findDeserializer(Annotated paramAnnotated) {
    Object object2 = this._primary.findDeserializer(paramAnnotated);
    Object object1 = object2;
    if (object2 == null)
      object1 = this._secondary.findDeserializer(paramAnnotated); 
    return object1;
  }
  
  public String findEnumValue(Enum paramEnum) {
    String str2 = this._primary.findEnumValue(paramEnum);
    String str1 = str2;
    if (str2 == null)
      str1 = this._secondary.findEnumValue(paramEnum); 
    return str1;
  }
  
  public Object findFilterId(AnnotatedClass paramAnnotatedClass) {
    Object object2 = this._primary.findFilterId(paramAnnotatedClass);
    Object object1 = object2;
    if (object2 == null)
      object1 = this._secondary.findFilterId(paramAnnotatedClass); 
    return object1;
  }
  
  public String findGettablePropertyName(AnnotatedMethod paramAnnotatedMethod) {
    String str = this._primary.findGettablePropertyName(paramAnnotatedMethod);
    if (str == null)
      return this._secondary.findGettablePropertyName(paramAnnotatedMethod); 
    if (str.length() == 0) {
      String str2 = this._secondary.findGettablePropertyName(paramAnnotatedMethod);
      String str1 = str2;
      return (str2 == null) ? str : str1;
    } 
    return str;
  }
  
  public Boolean findIgnoreUnknownProperties(AnnotatedClass paramAnnotatedClass) {
    Boolean bool2 = this._primary.findIgnoreUnknownProperties(paramAnnotatedClass);
    Boolean bool1 = bool2;
    if (bool2 == null)
      bool1 = this._secondary.findIgnoreUnknownProperties(paramAnnotatedClass); 
    return bool1;
  }
  
  public Object findInjectableValueId(AnnotatedMember paramAnnotatedMember) {
    Object object2 = this._primary.findInjectableValueId(paramAnnotatedMember);
    Object object1 = object2;
    if (object2 == null)
      object1 = this._secondary.findInjectableValueId(paramAnnotatedMember); 
    return object1;
  }
  
  public Class findKeyDeserializer(Annotated paramAnnotated) {
    Class<KeyDeserializer$None> clazz = this._primary.findKeyDeserializer(paramAnnotated);
    if (clazz != null) {
      Class<KeyDeserializer$None> clazz1 = clazz;
      return (clazz == KeyDeserializer$None.class) ? this._secondary.findKeyDeserializer(paramAnnotated) : clazz1;
    } 
    return this._secondary.findKeyDeserializer(paramAnnotated);
  }
  
  public Class findKeySerializer(Annotated paramAnnotated) {
    Class<JsonSerializer$None> clazz = this._primary.findKeySerializer(paramAnnotated);
    if (clazz != null) {
      Class<JsonSerializer$None> clazz1 = clazz;
      return (clazz == JsonSerializer$None.class) ? this._secondary.findKeySerializer(paramAnnotated) : clazz1;
    } 
    return this._secondary.findKeySerializer(paramAnnotated);
  }
  
  public String[] findPropertiesToIgnore(AnnotatedClass paramAnnotatedClass) {
    String[] arrayOfString2 = this._primary.findPropertiesToIgnore(paramAnnotatedClass);
    String[] arrayOfString1 = arrayOfString2;
    if (arrayOfString2 == null)
      arrayOfString1 = this._secondary.findPropertiesToIgnore(paramAnnotatedClass); 
    return arrayOfString1;
  }
  
  public TypeResolverBuilder findPropertyContentTypeResolver(MapperConfig paramMapperConfig, AnnotatedMember paramAnnotatedMember, JavaType paramJavaType) {
    TypeResolverBuilder typeResolverBuilder2 = this._primary.findPropertyContentTypeResolver(paramMapperConfig, paramAnnotatedMember, paramJavaType);
    TypeResolverBuilder typeResolverBuilder1 = typeResolverBuilder2;
    if (typeResolverBuilder2 == null)
      typeResolverBuilder1 = this._secondary.findPropertyContentTypeResolver(paramMapperConfig, paramAnnotatedMember, paramJavaType); 
    return typeResolverBuilder1;
  }
  
  public String findPropertyNameForParam(AnnotatedParameter paramAnnotatedParameter) {
    String str2 = this._primary.findPropertyNameForParam(paramAnnotatedParameter);
    String str1 = str2;
    if (str2 == null)
      str1 = this._secondary.findPropertyNameForParam(paramAnnotatedParameter); 
    return str1;
  }
  
  public TypeResolverBuilder findPropertyTypeResolver(MapperConfig paramMapperConfig, AnnotatedMember paramAnnotatedMember, JavaType paramJavaType) {
    TypeResolverBuilder typeResolverBuilder2 = this._primary.findPropertyTypeResolver(paramMapperConfig, paramAnnotatedMember, paramJavaType);
    TypeResolverBuilder typeResolverBuilder1 = typeResolverBuilder2;
    if (typeResolverBuilder2 == null)
      typeResolverBuilder1 = this._secondary.findPropertyTypeResolver(paramMapperConfig, paramAnnotatedMember, paramJavaType); 
    return typeResolverBuilder1;
  }
  
  public AnnotationIntrospector$ReferenceProperty findReferenceType(AnnotatedMember paramAnnotatedMember) {
    AnnotationIntrospector$ReferenceProperty annotationIntrospector$ReferenceProperty2 = this._primary.findReferenceType(paramAnnotatedMember);
    AnnotationIntrospector$ReferenceProperty annotationIntrospector$ReferenceProperty1 = annotationIntrospector$ReferenceProperty2;
    if (annotationIntrospector$ReferenceProperty2 == null)
      annotationIntrospector$ReferenceProperty1 = this._secondary.findReferenceType(paramAnnotatedMember); 
    return annotationIntrospector$ReferenceProperty1;
  }
  
  public String findRootName(AnnotatedClass paramAnnotatedClass) {
    String str2 = this._primary.findRootName(paramAnnotatedClass);
    if (str2 == null)
      return this._secondary.findRootName(paramAnnotatedClass); 
    String str1 = str2;
    if (str2.length() <= 0) {
      String str = this._secondary.findRootName(paramAnnotatedClass);
      str1 = str2;
      if (str != null)
        str1 = str; 
    } 
    return str1;
  }
  
  public String findSerializablePropertyName(AnnotatedField paramAnnotatedField) {
    String str = this._primary.findSerializablePropertyName(paramAnnotatedField);
    if (str == null)
      return this._secondary.findSerializablePropertyName(paramAnnotatedField); 
    if (str.length() == 0) {
      String str2 = this._secondary.findSerializablePropertyName(paramAnnotatedField);
      String str1 = str2;
      return (str2 == null) ? str : str1;
    } 
    return str;
  }
  
  public Class findSerializationContentType(Annotated paramAnnotated, JavaType paramJavaType) {
    Class clazz2 = this._primary.findSerializationContentType(paramAnnotated, paramJavaType);
    Class clazz1 = clazz2;
    if (clazz2 == null)
      clazz1 = this._secondary.findSerializationContentType(paramAnnotated, paramJavaType); 
    return clazz1;
  }
  
  public JsonSerialize.Inclusion findSerializationInclusion(Annotated paramAnnotated, JsonSerialize.Inclusion paramInclusion) {
    paramInclusion = this._secondary.findSerializationInclusion(paramAnnotated, paramInclusion);
    return this._primary.findSerializationInclusion(paramAnnotated, paramInclusion);
  }
  
  public Class findSerializationKeyType(Annotated paramAnnotated, JavaType paramJavaType) {
    Class clazz2 = this._primary.findSerializationKeyType(paramAnnotated, paramJavaType);
    Class clazz1 = clazz2;
    if (clazz2 == null)
      clazz1 = this._secondary.findSerializationKeyType(paramAnnotated, paramJavaType); 
    return clazz1;
  }
  
  public String[] findSerializationPropertyOrder(AnnotatedClass paramAnnotatedClass) {
    String[] arrayOfString2 = this._primary.findSerializationPropertyOrder(paramAnnotatedClass);
    String[] arrayOfString1 = arrayOfString2;
    if (arrayOfString2 == null)
      arrayOfString1 = this._secondary.findSerializationPropertyOrder(paramAnnotatedClass); 
    return arrayOfString1;
  }
  
  public Boolean findSerializationSortAlphabetically(AnnotatedClass paramAnnotatedClass) {
    Boolean bool2 = this._primary.findSerializationSortAlphabetically(paramAnnotatedClass);
    Boolean bool1 = bool2;
    if (bool2 == null)
      bool1 = this._secondary.findSerializationSortAlphabetically(paramAnnotatedClass); 
    return bool1;
  }
  
  public Class findSerializationType(Annotated paramAnnotated) {
    Class clazz2 = this._primary.findSerializationType(paramAnnotated);
    Class clazz1 = clazz2;
    if (clazz2 == null)
      clazz1 = this._secondary.findSerializationType(paramAnnotated); 
    return clazz1;
  }
  
  public JsonSerialize.Typing findSerializationTyping(Annotated paramAnnotated) {
    JsonSerialize.Typing typing2 = this._primary.findSerializationTyping(paramAnnotated);
    JsonSerialize.Typing typing1 = typing2;
    if (typing2 == null)
      typing1 = this._secondary.findSerializationTyping(paramAnnotated); 
    return typing1;
  }
  
  public Class[] findSerializationViews(Annotated paramAnnotated) {
    Class[] arrayOfClass2 = this._primary.findSerializationViews(paramAnnotated);
    Class[] arrayOfClass1 = arrayOfClass2;
    if (arrayOfClass2 == null)
      arrayOfClass1 = this._secondary.findSerializationViews(paramAnnotated); 
    return arrayOfClass1;
  }
  
  public Object findSerializer(Annotated paramAnnotated) {
    Object object2 = this._primary.findSerializer(paramAnnotated);
    Object object1 = object2;
    if (object2 == null)
      object1 = this._secondary.findSerializer(paramAnnotated); 
    return object1;
  }
  
  public String findSettablePropertyName(AnnotatedMethod paramAnnotatedMethod) {
    String str = this._primary.findSettablePropertyName(paramAnnotatedMethod);
    if (str == null)
      return this._secondary.findSettablePropertyName(paramAnnotatedMethod); 
    if (str.length() == 0) {
      String str2 = this._secondary.findSettablePropertyName(paramAnnotatedMethod);
      String str1 = str2;
      return (str2 == null) ? str : str1;
    } 
    return str;
  }
  
  public List findSubtypes(Annotated paramAnnotated) {
    List list2 = this._primary.findSubtypes(paramAnnotated);
    List list1 = this._secondary.findSubtypes(paramAnnotated);
    if (list2 != null && !list2.isEmpty()) {
      if (list1 == null || list1.isEmpty())
        return list2; 
      ArrayList arrayList = new ArrayList(list2.size() + list1.size());
      arrayList.addAll(list2);
      arrayList.addAll(list1);
      list1 = arrayList;
    } 
    return list1;
  }
  
  public String findTypeName(AnnotatedClass paramAnnotatedClass) {
    String str = this._primary.findTypeName(paramAnnotatedClass);
    if (str != null) {
      String str1 = str;
      return (str.length() == 0) ? this._secondary.findTypeName(paramAnnotatedClass) : str1;
    } 
    return this._secondary.findTypeName(paramAnnotatedClass);
  }
  
  public TypeResolverBuilder findTypeResolver(MapperConfig paramMapperConfig, AnnotatedClass paramAnnotatedClass, JavaType paramJavaType) {
    TypeResolverBuilder typeResolverBuilder2 = this._primary.findTypeResolver(paramMapperConfig, paramAnnotatedClass, paramJavaType);
    TypeResolverBuilder typeResolverBuilder1 = typeResolverBuilder2;
    if (typeResolverBuilder2 == null)
      typeResolverBuilder1 = this._secondary.findTypeResolver(paramMapperConfig, paramAnnotatedClass, paramJavaType); 
    return typeResolverBuilder1;
  }
  
  public Object findValueInstantiator(AnnotatedClass paramAnnotatedClass) {
    Object object2 = this._primary.findValueInstantiator(paramAnnotatedClass);
    Object object1 = object2;
    if (object2 == null)
      object1 = this._secondary.findValueInstantiator(paramAnnotatedClass); 
    return object1;
  }
  
  public boolean hasAnyGetterAnnotation(AnnotatedMethod paramAnnotatedMethod) {
    return (this._primary.hasAnyGetterAnnotation(paramAnnotatedMethod) || this._secondary.hasAnyGetterAnnotation(paramAnnotatedMethod));
  }
  
  public boolean hasAnySetterAnnotation(AnnotatedMethod paramAnnotatedMethod) {
    return (this._primary.hasAnySetterAnnotation(paramAnnotatedMethod) || this._secondary.hasAnySetterAnnotation(paramAnnotatedMethod));
  }
  
  public boolean hasAsValueAnnotation(AnnotatedMethod paramAnnotatedMethod) {
    return (this._primary.hasAsValueAnnotation(paramAnnotatedMethod) || this._secondary.hasAsValueAnnotation(paramAnnotatedMethod));
  }
  
  public boolean hasCreatorAnnotation(Annotated paramAnnotated) {
    return (this._primary.hasCreatorAnnotation(paramAnnotated) || this._secondary.hasCreatorAnnotation(paramAnnotated));
  }
  
  public boolean hasIgnoreMarker(AnnotatedMember paramAnnotatedMember) {
    return (this._primary.hasIgnoreMarker(paramAnnotatedMember) || this._secondary.hasIgnoreMarker(paramAnnotatedMember));
  }
  
  public boolean isHandled(Annotation paramAnnotation) {
    return (this._primary.isHandled(paramAnnotation) || this._secondary.isHandled(paramAnnotation));
  }
  
  public boolean isIgnorableConstructor(AnnotatedConstructor paramAnnotatedConstructor) {
    return (this._primary.isIgnorableConstructor(paramAnnotatedConstructor) || this._secondary.isIgnorableConstructor(paramAnnotatedConstructor));
  }
  
  public boolean isIgnorableField(AnnotatedField paramAnnotatedField) {
    return (this._primary.isIgnorableField(paramAnnotatedField) || this._secondary.isIgnorableField(paramAnnotatedField));
  }
  
  public boolean isIgnorableMethod(AnnotatedMethod paramAnnotatedMethod) {
    return (this._primary.isIgnorableMethod(paramAnnotatedMethod) || this._secondary.isIgnorableMethod(paramAnnotatedMethod));
  }
  
  public Boolean isIgnorableType(AnnotatedClass paramAnnotatedClass) {
    Boolean bool2 = this._primary.isIgnorableType(paramAnnotatedClass);
    Boolean bool1 = bool2;
    if (bool2 == null)
      bool1 = this._secondary.isIgnorableType(paramAnnotatedClass); 
    return bool1;
  }
  
  public Boolean shouldUnwrapProperty(AnnotatedMember paramAnnotatedMember) {
    Boolean bool2 = this._primary.shouldUnwrapProperty(paramAnnotatedMember);
    Boolean bool1 = bool2;
    if (bool2 == null)
      bool1 = this._secondary.shouldUnwrapProperty(paramAnnotatedMember); 
    return bool1;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\AnnotationIntrospector$Pair.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */