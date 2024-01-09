package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.map.type.TypeBindings;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

public abstract class Annotated {
  protected abstract AnnotationMap getAllAnnotations();
  
  public abstract AnnotatedElement getAnnotated();
  
  public abstract Annotation getAnnotation(Class paramClass);
  
  public abstract Type getGenericType();
  
  protected abstract int getModifiers();
  
  public abstract String getName();
  
  public abstract Class getRawType();
  
  public JavaType getType(TypeBindings paramTypeBindings) {
    return paramTypeBindings.resolveType(getGenericType());
  }
  
  public final boolean hasAnnotation(Class paramClass) {
    return (getAnnotation(paramClass) != null);
  }
  
  public final boolean isPublic() {
    return Modifier.isPublic(getModifiers());
  }
  
  public abstract Annotated withAnnotations(AnnotationMap paramAnnotationMap);
  
  public final Annotated withFallBackAnnotationsFrom(Annotated paramAnnotated) {
    return withAnnotations(AnnotationMap.merge(getAllAnnotations(), paramAnnotated.getAllAnnotations()));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\introspect\Annotated.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */