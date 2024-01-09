package com.flurry.org.codehaus.jackson.map.util;

import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import java.util.HashMap;

public class EnumResolver {
  protected final Class _enumClass;
  
  protected final Enum[] _enums;
  
  protected final HashMap _enumsById;
  
  protected EnumResolver(Class paramClass, Enum[] paramArrayOfEnum, HashMap paramHashMap) {
    this._enumClass = paramClass;
    this._enums = paramArrayOfEnum;
    this._enumsById = paramHashMap;
  }
  
  public static EnumResolver constructFor(Class<Enum> paramClass, AnnotationIntrospector paramAnnotationIntrospector) {
    Enum[] arrayOfEnum = paramClass.getEnumConstants();
    if (arrayOfEnum == null)
      throw new IllegalArgumentException("No enum constants for class " + paramClass.getName()); 
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    int i = arrayOfEnum.length;
    for (byte b = 0; b < i; b++) {
      Enum enum_ = arrayOfEnum[b];
      hashMap.put(paramAnnotationIntrospector.findEnumValue(enum_), enum_);
    } 
    return new EnumResolver(paramClass, arrayOfEnum, hashMap);
  }
  
  public static EnumResolver constructUnsafe(Class paramClass, AnnotationIntrospector paramAnnotationIntrospector) {
    return constructFor(paramClass, paramAnnotationIntrospector);
  }
  
  public static EnumResolver constructUnsafeUsingToString(Class paramClass) {
    return constructUsingToString(paramClass);
  }
  
  public static EnumResolver constructUsingToString(Class<Enum> paramClass) {
    Enum[] arrayOfEnum = paramClass.getEnumConstants();
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    int i = arrayOfEnum.length;
    while (--i >= 0) {
      Enum enum_ = arrayOfEnum[i];
      hashMap.put(enum_.toString(), enum_);
    } 
    return new EnumResolver(paramClass, arrayOfEnum, hashMap);
  }
  
  public Enum findEnum(String paramString) {
    return (Enum)this._enumsById.get(paramString);
  }
  
  public Enum getEnum(int paramInt) {
    return (paramInt < 0 || paramInt >= this._enums.length) ? null : this._enums[paramInt];
  }
  
  public Class getEnumClass() {
    return this._enumClass;
  }
  
  public int lastValidIndex() {
    return this._enums.length - 1;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\ma\\util\EnumResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */