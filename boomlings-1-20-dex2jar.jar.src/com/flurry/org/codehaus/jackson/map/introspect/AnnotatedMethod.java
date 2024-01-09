package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.map.type.TypeBindings;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public final class AnnotatedMethod extends AnnotatedWithParams {
  protected final Method _method;
  
  protected Class[] _paramTypes;
  
  public AnnotatedMethod(Method paramMethod, AnnotationMap paramAnnotationMap, AnnotationMap[] paramArrayOfAnnotationMap) {
    super(paramAnnotationMap, paramArrayOfAnnotationMap);
    this._method = paramMethod;
  }
  
  public final Object call() {
    return this._method.invoke(null, new Object[0]);
  }
  
  public final Object call(Object[] paramArrayOfObject) {
    return this._method.invoke(null, paramArrayOfObject);
  }
  
  public final Object call1(Object paramObject) {
    return this._method.invoke(null, new Object[] { paramObject });
  }
  
  public Method getAnnotated() {
    return this._method;
  }
  
  public Class getDeclaringClass() {
    return this._method.getDeclaringClass();
  }
  
  public String getFullName() {
    return getDeclaringClass().getName() + "#" + getName() + "(" + getParameterCount() + " params)";
  }
  
  public Type getGenericType() {
    return this._method.getGenericReturnType();
  }
  
  public Member getMember() {
    return this._method;
  }
  
  public int getModifiers() {
    return this._method.getModifiers();
  }
  
  public String getName() {
    return this._method.getName();
  }
  
  public Class getParameterClass(int paramInt) {
    Class[] arrayOfClass = this._method.getParameterTypes();
    return (paramInt >= arrayOfClass.length) ? null : arrayOfClass[paramInt];
  }
  
  public Class[] getParameterClasses() {
    if (this._paramTypes == null)
      this._paramTypes = this._method.getParameterTypes(); 
    return this._paramTypes;
  }
  
  public int getParameterCount() {
    return (getParameterTypes()).length;
  }
  
  public Type getParameterType(int paramInt) {
    Type[] arrayOfType = this._method.getGenericParameterTypes();
    return (paramInt >= arrayOfType.length) ? null : arrayOfType[paramInt];
  }
  
  public Type[] getParameterTypes() {
    return this._method.getGenericParameterTypes();
  }
  
  public Class getRawType() {
    return this._method.getReturnType();
  }
  
  public JavaType getType(TypeBindings paramTypeBindings) {
    return getType(paramTypeBindings, (TypeVariable[])this._method.getTypeParameters());
  }
  
  public void setValue(Object paramObject1, Object paramObject2) {
    try {
      this._method.invoke(paramObject1, new Object[] { paramObject2 });
      return;
    } catch (IllegalAccessException illegalAccessException) {
      throw new IllegalArgumentException("Failed to setValue() with method " + getFullName() + ": " + illegalAccessException.getMessage(), illegalAccessException);
    } catch (InvocationTargetException invocationTargetException) {
      throw new IllegalArgumentException("Failed to setValue() with method " + getFullName() + ": " + invocationTargetException.getMessage(), invocationTargetException);
    } 
  }
  
  public String toString() {
    return "[method " + getName() + ", annotations: " + this._annotations + "]";
  }
  
  public AnnotatedMethod withAnnotations(AnnotationMap paramAnnotationMap) {
    return new AnnotatedMethod(this._method, paramAnnotationMap, this._paramAnnotations);
  }
  
  public AnnotatedMethod withMethod(Method paramMethod) {
    return new AnnotatedMethod(paramMethod, this._annotations, this._paramAnnotations);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\introspect\AnnotatedMethod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */