package com.flurry.org.codehaus.jackson.map.util;

import com.flurry.org.codehaus.jackson.io.SerializedString;
import com.flurry.org.codehaus.jackson.map.AnnotationIntrospector;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public final class EnumValues {
  private final EnumMap _values;
  
  private EnumValues(Map<Enum, ?> paramMap) {
    this._values = new EnumMap<Enum, Object>(paramMap);
  }
  
  public static EnumValues construct(Class paramClass, AnnotationIntrospector paramAnnotationIntrospector) {
    return constructFromName(paramClass, paramAnnotationIntrospector);
  }
  
  public static EnumValues constructFromName(Class paramClass, AnnotationIntrospector paramAnnotationIntrospector) {
    HashMap<Object, Object> hashMap;
    Enum[] arrayOfEnum = ClassUtil.findEnumType(paramClass).getEnumConstants();
    if (arrayOfEnum != null) {
      hashMap = new HashMap<Object, Object>();
      int i = arrayOfEnum.length;
      for (byte b = 0; b < i; b++) {
        Enum enum_ = arrayOfEnum[b];
        hashMap.put(enum_, new SerializedString(paramAnnotationIntrospector.findEnumValue(enum_)));
      } 
      return new EnumValues(hashMap);
    } 
    throw new IllegalArgumentException("Can not determine enum constants for Class " + hashMap.getName());
  }
  
  public static EnumValues constructFromToString(Class paramClass, AnnotationIntrospector paramAnnotationIntrospector) {
    HashMap<Object, Object> hashMap;
    Enum[] arrayOfEnum = ClassUtil.findEnumType(paramClass).getEnumConstants();
    if (arrayOfEnum != null) {
      hashMap = new HashMap<Object, Object>();
      int i = arrayOfEnum.length;
      for (byte b = 0; b < i; b++) {
        Enum enum_ = arrayOfEnum[b];
        hashMap.put(enum_, new SerializedString(enum_.toString()));
      } 
      return new EnumValues(hashMap);
    } 
    throw new IllegalArgumentException("Can not determine enum constants for Class " + hashMap.getName());
  }
  
  public SerializedString serializedValueFor(Enum paramEnum) {
    return (SerializedString)this._values.get(paramEnum);
  }
  
  @Deprecated
  public String valueFor(Enum paramEnum) {
    SerializedString serializedString = (SerializedString)this._values.get(paramEnum);
    return (serializedString == null) ? null : serializedString.getValue();
  }
  
  public Collection values() {
    return this._values.values();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\ma\\util\EnumValues.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */