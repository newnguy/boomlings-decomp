package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.map.type.TypeFactory;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

public final class AnnotatedParameter extends AnnotatedMember {
  protected final int _index;
  
  protected final AnnotatedWithParams _owner;
  
  protected final Type _type;
  
  public AnnotatedParameter(AnnotatedWithParams paramAnnotatedWithParams, Type paramType, AnnotationMap paramAnnotationMap, int paramInt) {
    super(paramAnnotationMap);
    this._owner = paramAnnotatedWithParams;
    this._type = paramType;
    this._index = paramInt;
  }
  
  public void addOrOverride(Annotation paramAnnotation) {
    this._annotations.add(paramAnnotation);
  }
  
  public AnnotatedElement getAnnotated() {
    return null;
  }
  
  public Annotation getAnnotation(Class paramClass) {
    return this._annotations.get(paramClass);
  }
  
  public Class getDeclaringClass() {
    return this._owner.getDeclaringClass();
  }
  
  public Type getGenericType() {
    return this._type;
  }
  
  public int getIndex() {
    return this._index;
  }
  
  public Member getMember() {
    return this._owner.getMember();
  }
  
  public int getModifiers() {
    return this._owner.getModifiers();
  }
  
  public String getName() {
    return "";
  }
  
  public AnnotatedWithParams getOwner() {
    return this._owner;
  }
  
  public Type getParameterType() {
    return this._type;
  }
  
  public Class getRawType() {
    return (this._type instanceof Class) ? (Class)this._type : TypeFactory.defaultInstance().constructType(this._type).getRawClass();
  }
  
  public void setValue(Object paramObject1, Object paramObject2) {
    throw new UnsupportedOperationException("Cannot call setValue() on constructor parameter of " + getDeclaringClass().getName());
  }
  
  public String toString() {
    return "[parameter #" + getIndex() + ", annotations: " + this._annotations + "]";
  }
  
  public AnnotatedParameter withAnnotations(AnnotationMap paramAnnotationMap) {
    return (paramAnnotationMap == this._annotations) ? this : this._owner.replaceParameterAnnotations(this._index, paramAnnotationMap);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\introspect\AnnotatedParameter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */