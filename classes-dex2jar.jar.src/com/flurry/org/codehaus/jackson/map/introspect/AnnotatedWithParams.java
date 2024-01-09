package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.map.type.TypeBindings;
import com.flurry.org.codehaus.jackson.map.type.TypeFactory;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public abstract class AnnotatedWithParams extends AnnotatedMember {
  protected final AnnotationMap[] _paramAnnotations;
  
  protected AnnotatedWithParams(AnnotationMap paramAnnotationMap, AnnotationMap[] paramArrayOfAnnotationMap) {
    super(paramAnnotationMap);
    this._paramAnnotations = paramArrayOfAnnotationMap;
  }
  
  public final void addIfNotPresent(Annotation paramAnnotation) {
    this._annotations.addIfNotPresent(paramAnnotation);
  }
  
  public final void addOrOverride(Annotation paramAnnotation) {
    this._annotations.add(paramAnnotation);
  }
  
  public final void addOrOverrideParam(int paramInt, Annotation paramAnnotation) {
    AnnotationMap annotationMap2 = this._paramAnnotations[paramInt];
    AnnotationMap annotationMap1 = annotationMap2;
    if (annotationMap2 == null) {
      annotationMap1 = new AnnotationMap();
      this._paramAnnotations[paramInt] = annotationMap1;
    } 
    annotationMap1.add(paramAnnotation);
  }
  
  public abstract Object call();
  
  public abstract Object call(Object[] paramArrayOfObject);
  
  public abstract Object call1(Object paramObject);
  
  public final Annotation getAnnotation(Class paramClass) {
    return this._annotations.get(paramClass);
  }
  
  public final int getAnnotationCount() {
    return this._annotations.size();
  }
  
  public final AnnotatedParameter getParameter(int paramInt) {
    return new AnnotatedParameter(this, getParameterType(paramInt), this._paramAnnotations[paramInt], paramInt);
  }
  
  public final AnnotationMap getParameterAnnotations(int paramInt) {
    return (this._paramAnnotations != null && paramInt >= 0 && paramInt <= this._paramAnnotations.length) ? this._paramAnnotations[paramInt] : null;
  }
  
  public abstract Class getParameterClass(int paramInt);
  
  public abstract int getParameterCount();
  
  public abstract Type getParameterType(int paramInt);
  
  protected JavaType getType(TypeBindings paramTypeBindings, TypeVariable[] paramArrayOfTypeVariable) {
    TypeVariable typeVariable;
    TypeBindings typeBindings = paramTypeBindings;
    if (paramArrayOfTypeVariable != null) {
      typeBindings = paramTypeBindings;
      if (paramArrayOfTypeVariable.length > 0) {
        TypeBindings typeBindings1 = paramTypeBindings.childInstance();
        int i = paramArrayOfTypeVariable.length;
        byte b = 0;
        while (true) {
          typeBindings = typeBindings1;
          if (b < i) {
            JavaType javaType;
            typeVariable = paramArrayOfTypeVariable[b];
            typeBindings1._addPlaceholder(typeVariable.getName());
            Type type = typeVariable.getBounds()[0];
            if (type == null) {
              javaType = TypeFactory.unknownType();
            } else {
              javaType = typeBindings1.resolveType((Type)javaType);
            } 
            typeBindings1.addBinding(typeVariable.getName(), javaType);
            b++;
            continue;
          } 
          break;
        } 
      } 
    } 
    return typeVariable.resolveType(getGenericType());
  }
  
  protected AnnotatedParameter replaceParameterAnnotations(int paramInt, AnnotationMap paramAnnotationMap) {
    this._paramAnnotations[paramInt] = paramAnnotationMap;
    return getParameter(paramInt);
  }
  
  public final JavaType resolveParameterType(int paramInt, TypeBindings paramTypeBindings) {
    return paramTypeBindings.resolveType(getParameterType(paramInt));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\introspect\AnnotatedWithParams.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */