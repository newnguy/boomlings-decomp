package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.map.annotate.JsonSerialize;
import com.flurry.org.codehaus.jackson.map.introspect.Annotated;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedClass;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedField;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedParameter;
import com.flurry.org.codehaus.jackson.map.introspect.NopAnnotationIntrospector;
import com.flurry.org.codehaus.jackson.map.introspect.VisibilityChecker;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class AnnotationIntrospector {
  public static AnnotationIntrospector nopInstance() {
    return (AnnotationIntrospector)NopAnnotationIntrospector.instance;
  }
  
  public static AnnotationIntrospector pair(AnnotationIntrospector paramAnnotationIntrospector1, AnnotationIntrospector paramAnnotationIntrospector2) {
    return new AnnotationIntrospector$Pair(paramAnnotationIntrospector1, paramAnnotationIntrospector2);
  }
  
  public Collection allIntrospectors() {
    return Collections.singletonList(this);
  }
  
  public Collection allIntrospectors(Collection<AnnotationIntrospector> paramCollection) {
    paramCollection.add(this);
    return paramCollection;
  }
  
  public VisibilityChecker findAutoDetectVisibility(AnnotatedClass paramAnnotatedClass, VisibilityChecker paramVisibilityChecker) {
    return paramVisibilityChecker;
  }
  
  public Boolean findCachability(AnnotatedClass paramAnnotatedClass) {
    return null;
  }
  
  public abstract Class findContentDeserializer(Annotated paramAnnotated);
  
  public Class findContentSerializer(Annotated paramAnnotated) {
    return null;
  }
  
  public abstract String findDeserializablePropertyName(AnnotatedField paramAnnotatedField);
  
  public abstract Class findDeserializationContentType(Annotated paramAnnotated, JavaType paramJavaType, String paramString);
  
  public abstract Class findDeserializationKeyType(Annotated paramAnnotated, JavaType paramJavaType, String paramString);
  
  public abstract Class findDeserializationType(Annotated paramAnnotated, JavaType paramJavaType, String paramString);
  
  public abstract Object findDeserializer(Annotated paramAnnotated);
  
  public abstract String findEnumValue(Enum paramEnum);
  
  public Object findFilterId(AnnotatedClass paramAnnotatedClass) {
    return null;
  }
  
  public abstract String findGettablePropertyName(AnnotatedMethod paramAnnotatedMethod);
  
  public abstract Boolean findIgnoreUnknownProperties(AnnotatedClass paramAnnotatedClass);
  
  public Object findInjectableValueId(AnnotatedMember paramAnnotatedMember) {
    return null;
  }
  
  public abstract Class findKeyDeserializer(Annotated paramAnnotated);
  
  public Class findKeySerializer(Annotated paramAnnotated) {
    return null;
  }
  
  public abstract String[] findPropertiesToIgnore(AnnotatedClass paramAnnotatedClass);
  
  public TypeResolverBuilder findPropertyContentTypeResolver(MapperConfig paramMapperConfig, AnnotatedMember paramAnnotatedMember, JavaType paramJavaType) {
    return null;
  }
  
  public abstract String findPropertyNameForParam(AnnotatedParameter paramAnnotatedParameter);
  
  public TypeResolverBuilder findPropertyTypeResolver(MapperConfig paramMapperConfig, AnnotatedMember paramAnnotatedMember, JavaType paramJavaType) {
    return null;
  }
  
  public AnnotationIntrospector$ReferenceProperty findReferenceType(AnnotatedMember paramAnnotatedMember) {
    return null;
  }
  
  public abstract String findRootName(AnnotatedClass paramAnnotatedClass);
  
  public abstract String findSerializablePropertyName(AnnotatedField paramAnnotatedField);
  
  public Class findSerializationContentType(Annotated paramAnnotated, JavaType paramJavaType) {
    return null;
  }
  
  public JsonSerialize.Inclusion findSerializationInclusion(Annotated paramAnnotated, JsonSerialize.Inclusion paramInclusion) {
    return paramInclusion;
  }
  
  public Class findSerializationKeyType(Annotated paramAnnotated, JavaType paramJavaType) {
    return null;
  }
  
  public abstract String[] findSerializationPropertyOrder(AnnotatedClass paramAnnotatedClass);
  
  public abstract Boolean findSerializationSortAlphabetically(AnnotatedClass paramAnnotatedClass);
  
  public abstract Class findSerializationType(Annotated paramAnnotated);
  
  public abstract JsonSerialize.Typing findSerializationTyping(Annotated paramAnnotated);
  
  public abstract Class[] findSerializationViews(Annotated paramAnnotated);
  
  public abstract Object findSerializer(Annotated paramAnnotated);
  
  public abstract String findSettablePropertyName(AnnotatedMethod paramAnnotatedMethod);
  
  public List findSubtypes(Annotated paramAnnotated) {
    return null;
  }
  
  public String findTypeName(AnnotatedClass paramAnnotatedClass) {
    return null;
  }
  
  public TypeResolverBuilder findTypeResolver(MapperConfig paramMapperConfig, AnnotatedClass paramAnnotatedClass, JavaType paramJavaType) {
    return null;
  }
  
  public Object findValueInstantiator(AnnotatedClass paramAnnotatedClass) {
    return null;
  }
  
  public boolean hasAnyGetterAnnotation(AnnotatedMethod paramAnnotatedMethod) {
    return false;
  }
  
  public boolean hasAnySetterAnnotation(AnnotatedMethod paramAnnotatedMethod) {
    return false;
  }
  
  public abstract boolean hasAsValueAnnotation(AnnotatedMethod paramAnnotatedMethod);
  
  public boolean hasCreatorAnnotation(Annotated paramAnnotated) {
    return false;
  }
  
  public boolean hasIgnoreMarker(AnnotatedMember paramAnnotatedMember) {
    return (paramAnnotatedMember instanceof AnnotatedMethod) ? isIgnorableMethod((AnnotatedMethod)paramAnnotatedMember) : ((paramAnnotatedMember instanceof AnnotatedField) ? isIgnorableField((AnnotatedField)paramAnnotatedMember) : ((paramAnnotatedMember instanceof AnnotatedConstructor) ? isIgnorableConstructor((AnnotatedConstructor)paramAnnotatedMember) : false));
  }
  
  public abstract boolean isHandled(Annotation paramAnnotation);
  
  public abstract boolean isIgnorableConstructor(AnnotatedConstructor paramAnnotatedConstructor);
  
  public abstract boolean isIgnorableField(AnnotatedField paramAnnotatedField);
  
  public abstract boolean isIgnorableMethod(AnnotatedMethod paramAnnotatedMethod);
  
  public Boolean isIgnorableType(AnnotatedClass paramAnnotatedClass) {
    return null;
  }
  
  public Boolean shouldUnwrapProperty(AnnotatedMember paramAnnotatedMember) {
    return null;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\AnnotationIntrospector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */