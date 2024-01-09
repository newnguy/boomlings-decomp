package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.map.type.TypeBindings;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

public final class AnnotatedConstructor extends AnnotatedWithParams {
  protected final Constructor _constructor;
  
  public AnnotatedConstructor(Constructor paramConstructor, AnnotationMap paramAnnotationMap, AnnotationMap[] paramArrayOfAnnotationMap) {
    super(paramAnnotationMap, paramArrayOfAnnotationMap);
    if (paramConstructor == null)
      throw new IllegalArgumentException("Null constructor not allowed"); 
    this._constructor = paramConstructor;
  }
  
  public final Object call() {
    return this._constructor.newInstance(new Object[0]);
  }
  
  public final Object call(Object[] paramArrayOfObject) {
    return this._constructor.newInstance(paramArrayOfObject);
  }
  
  public final Object call1(Object paramObject) {
    return this._constructor.newInstance(new Object[] { paramObject });
  }
  
  public Constructor getAnnotated() {
    return this._constructor;
  }
  
  public Class getDeclaringClass() {
    return this._constructor.getDeclaringClass();
  }
  
  public Type getGenericType() {
    return getRawType();
  }
  
  public Member getMember() {
    return this._constructor;
  }
  
  public int getModifiers() {
    return this._constructor.getModifiers();
  }
  
  public String getName() {
    return this._constructor.getName();
  }
  
  public Class getParameterClass(int paramInt) {
    Class[] arrayOfClass = this._constructor.getParameterTypes();
    return (paramInt >= arrayOfClass.length) ? null : arrayOfClass[paramInt];
  }
  
  public int getParameterCount() {
    return (this._constructor.getParameterTypes()).length;
  }
  
  public Type getParameterType(int paramInt) {
    Type[] arrayOfType = this._constructor.getGenericParameterTypes();
    return (paramInt >= arrayOfType.length) ? null : arrayOfType[paramInt];
  }
  
  public Class getRawType() {
    return this._constructor.getDeclaringClass();
  }
  
  public JavaType getType(TypeBindings paramTypeBindings) {
    return getType(paramTypeBindings, this._constructor.getTypeParameters());
  }
  
  public void setValue(Object paramObject1, Object paramObject2) {
    throw new UnsupportedOperationException("Cannot call setValue() on constructor of " + getDeclaringClass().getName());
  }
  
  public String toString() {
    return "[constructor for " + getName() + ", annotations: " + this._annotations + "]";
  }
  
  public AnnotatedConstructor withAnnotations(AnnotationMap paramAnnotationMap) {
    return new AnnotatedConstructor(this._constructor, paramAnnotationMap, this._paramAnnotations);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\introspect\AnnotatedConstructor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */