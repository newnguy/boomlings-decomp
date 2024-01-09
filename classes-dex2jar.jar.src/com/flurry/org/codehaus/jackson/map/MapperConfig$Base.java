package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.annotate.JsonAutoDetect;
import com.flurry.org.codehaus.jackson.annotate.JsonMethod;
import com.flurry.org.codehaus.jackson.map.introspect.VisibilityChecker;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
import com.flurry.org.codehaus.jackson.map.type.TypeFactory;
import java.text.DateFormat;

public class MapperConfig$Base {
  protected final AnnotationIntrospector _annotationIntrospector;
  
  protected final ClassIntrospector _classIntrospector;
  
  protected final DateFormat _dateFormat;
  
  protected final HandlerInstantiator _handlerInstantiator;
  
  protected final PropertyNamingStrategy _propertyNamingStrategy;
  
  protected final TypeFactory _typeFactory;
  
  protected final TypeResolverBuilder _typeResolverBuilder;
  
  protected final VisibilityChecker _visibilityChecker;
  
  public MapperConfig$Base(ClassIntrospector paramClassIntrospector, AnnotationIntrospector paramAnnotationIntrospector, VisibilityChecker paramVisibilityChecker, PropertyNamingStrategy paramPropertyNamingStrategy, TypeFactory paramTypeFactory, TypeResolverBuilder paramTypeResolverBuilder, DateFormat paramDateFormat, HandlerInstantiator paramHandlerInstantiator) {
    this._classIntrospector = paramClassIntrospector;
    this._annotationIntrospector = paramAnnotationIntrospector;
    this._visibilityChecker = paramVisibilityChecker;
    this._propertyNamingStrategy = paramPropertyNamingStrategy;
    this._typeFactory = paramTypeFactory;
    this._typeResolverBuilder = paramTypeResolverBuilder;
    this._dateFormat = paramDateFormat;
    this._handlerInstantiator = paramHandlerInstantiator;
  }
  
  public AnnotationIntrospector getAnnotationIntrospector() {
    return this._annotationIntrospector;
  }
  
  public ClassIntrospector getClassIntrospector() {
    return this._classIntrospector;
  }
  
  public DateFormat getDateFormat() {
    return this._dateFormat;
  }
  
  public HandlerInstantiator getHandlerInstantiator() {
    return this._handlerInstantiator;
  }
  
  public PropertyNamingStrategy getPropertyNamingStrategy() {
    return this._propertyNamingStrategy;
  }
  
  public TypeFactory getTypeFactory() {
    return this._typeFactory;
  }
  
  public TypeResolverBuilder getTypeResolverBuilder() {
    return this._typeResolverBuilder;
  }
  
  public VisibilityChecker getVisibilityChecker() {
    return this._visibilityChecker;
  }
  
  public MapperConfig$Base withAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector) {
    return new MapperConfig$Base(this._classIntrospector, paramAnnotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
  }
  
  public MapperConfig$Base withAppendedAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector) {
    return withAnnotationIntrospector(AnnotationIntrospector$Pair.create(this._annotationIntrospector, paramAnnotationIntrospector));
  }
  
  public MapperConfig$Base withClassIntrospector(ClassIntrospector paramClassIntrospector) {
    return new MapperConfig$Base(paramClassIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
  }
  
  public MapperConfig$Base withDateFormat(DateFormat paramDateFormat) {
    return new MapperConfig$Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, paramDateFormat, this._handlerInstantiator);
  }
  
  public MapperConfig$Base withHandlerInstantiator(HandlerInstantiator paramHandlerInstantiator) {
    return new MapperConfig$Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, paramHandlerInstantiator);
  }
  
  public MapperConfig$Base withInsertedAnnotationIntrospector(AnnotationIntrospector paramAnnotationIntrospector) {
    return withAnnotationIntrospector(AnnotationIntrospector$Pair.create(paramAnnotationIntrospector, this._annotationIntrospector));
  }
  
  public MapperConfig$Base withPropertyNamingStrategy(PropertyNamingStrategy paramPropertyNamingStrategy) {
    return new MapperConfig$Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, paramPropertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
  }
  
  public MapperConfig$Base withTypeFactory(TypeFactory paramTypeFactory) {
    return new MapperConfig$Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, paramTypeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
  }
  
  public MapperConfig$Base withTypeResolverBuilder(TypeResolverBuilder paramTypeResolverBuilder) {
    return new MapperConfig$Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, paramTypeResolverBuilder, this._dateFormat, this._handlerInstantiator);
  }
  
  public MapperConfig$Base withVisibility(JsonMethod paramJsonMethod, JsonAutoDetect.Visibility paramVisibility) {
    return new MapperConfig$Base(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker.withVisibility(paramJsonMethod, paramVisibility), this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
  }
  
  public MapperConfig$Base withVisibilityChecker(VisibilityChecker paramVisibilityChecker) {
    return new MapperConfig$Base(this._classIntrospector, this._annotationIntrospector, paramVisibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\MapperConfig$Base.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */