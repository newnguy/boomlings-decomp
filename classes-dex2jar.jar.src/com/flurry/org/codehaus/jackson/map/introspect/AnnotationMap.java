package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.map.util.Annotations;
import java.lang.annotation.Annotation;
import java.util.HashMap;

public final class AnnotationMap implements Annotations {
  protected HashMap _annotations;
  
  public AnnotationMap() {}
  
  private AnnotationMap(HashMap paramHashMap) {
    this._annotations = paramHashMap;
  }
  
  public static AnnotationMap merge(AnnotationMap paramAnnotationMap1, AnnotationMap paramAnnotationMap2) {
    if (paramAnnotationMap1 == null || paramAnnotationMap1._annotations == null || paramAnnotationMap1._annotations.isEmpty())
      return paramAnnotationMap2; 
    AnnotationMap annotationMap = paramAnnotationMap1;
    if (paramAnnotationMap2 != null) {
      annotationMap = paramAnnotationMap1;
      if (paramAnnotationMap2._annotations != null) {
        annotationMap = paramAnnotationMap1;
        if (!paramAnnotationMap2._annotations.isEmpty()) {
          HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
          for (Annotation annotation : paramAnnotationMap2._annotations.values())
            hashMap.put(annotation.annotationType(), annotation); 
          for (Annotation annotation : paramAnnotationMap1._annotations.values())
            hashMap.put(annotation.annotationType(), annotation); 
          annotationMap = new AnnotationMap(hashMap);
        } 
      } 
    } 
    return annotationMap;
  }
  
  protected final void _add(Annotation paramAnnotation) {
    if (this._annotations == null)
      this._annotations = new HashMap<Object, Object>(); 
    this._annotations.put(paramAnnotation.annotationType(), paramAnnotation);
  }
  
  public void add(Annotation paramAnnotation) {
    _add(paramAnnotation);
  }
  
  public void addIfNotPresent(Annotation paramAnnotation) {
    if (this._annotations == null || !this._annotations.containsKey(paramAnnotation.annotationType()))
      _add(paramAnnotation); 
  }
  
  public Annotation get(Class paramClass) {
    return (this._annotations == null) ? null : (Annotation)this._annotations.get(paramClass);
  }
  
  public int size() {
    return (this._annotations == null) ? 0 : this._annotations.size();
  }
  
  public String toString() {
    return (this._annotations == null) ? "[null]" : this._annotations.toString();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\introspect\AnnotationMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */