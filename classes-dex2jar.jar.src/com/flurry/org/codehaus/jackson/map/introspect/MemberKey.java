package com.flurry.org.codehaus.jackson.map.introspect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public final class MemberKey {
  static final Class[] NO_CLASSES = new Class[0];
  
  final Class[] _argTypes;
  
  final String _name;
  
  public MemberKey(String paramString, Class[] paramArrayOfClass) {
    this._name = paramString;
    Class[] arrayOfClass = paramArrayOfClass;
    if (paramArrayOfClass == null)
      arrayOfClass = NO_CLASSES; 
    this._argTypes = arrayOfClass;
  }
  
  public MemberKey(Constructor paramConstructor) {
    this("", paramConstructor.getParameterTypes());
  }
  
  public MemberKey(Method paramMethod) {
    this(paramMethod.getName(), paramMethod.getParameterTypes());
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject == this)
      return bool; 
    if (paramObject == null)
      return false; 
    if (paramObject.getClass() != getClass())
      return false; 
    paramObject = paramObject;
    if (!this._name.equals(((MemberKey)paramObject)._name))
      return false; 
    paramObject = ((MemberKey)paramObject)._argTypes;
    int i = this._argTypes.length;
    if (paramObject.length != i)
      return false; 
    byte b = 0;
    while (true) {
      boolean bool1 = bool;
      if (b < i) {
        Object object = paramObject[b];
        Class<?> clazz = this._argTypes[b];
        if (object != clazz && !object.isAssignableFrom(clazz) && !clazz.isAssignableFrom((Class<?>)object))
          return false; 
        b++;
        continue;
      } 
      return bool1;
    } 
  }
  
  public int hashCode() {
    return this._name.hashCode() + this._argTypes.length;
  }
  
  public String toString() {
    return this._name + "(" + this._argTypes.length + "-args)";
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\introspect\MemberKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */