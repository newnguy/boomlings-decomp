package com.flurry.org.codehaus.jackson.map.jsontype.impl;

import com.flurry.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.flurry.org.codehaus.jackson.map.MapperConfig;
import com.flurry.org.codehaus.jackson.map.jsontype.NamedType;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.Collection;
import java.util.HashMap;

public class TypeNameIdResolver extends TypeIdResolverBase {
  protected final MapperConfig _config;
  
  protected final HashMap _idToType;
  
  protected final HashMap _typeToId;
  
  protected TypeNameIdResolver(MapperConfig paramMapperConfig, JavaType paramJavaType, HashMap paramHashMap1, HashMap paramHashMap2) {
    super(paramJavaType, paramMapperConfig.getTypeFactory());
    this._config = paramMapperConfig;
    this._typeToId = paramHashMap1;
    this._idToType = paramHashMap2;
  }
  
  protected static String _defaultTypeId(Class paramClass) {
    String str = paramClass.getName();
    int i = str.lastIndexOf('.');
    if (i >= 0)
      str = str.substring(i + 1); 
    return str;
  }
  
  public static TypeNameIdResolver construct(MapperConfig paramMapperConfig, JavaType paramJavaType, Collection paramCollection, boolean paramBoolean1, boolean paramBoolean2) {
    HashMap<String, String> hashMap;
    HashMap<String, JavaType> hashMap1;
    if (paramBoolean1 == paramBoolean2)
      throw new IllegalArgumentException(); 
    if (paramBoolean1) {
      hashMap = (HashMap)new HashMap<Object, Object>();
    } else {
      hashMap = null;
    } 
    if (paramBoolean2) {
      hashMap1 = (HashMap)new HashMap<Object, Object>();
    } else {
      hashMap1 = null;
    } 
    if (paramCollection != null)
      for (NamedType namedType : paramCollection) {
        String str;
        Class clazz = namedType.getType();
        if (namedType.hasName()) {
          str = namedType.getName();
        } else {
          str = _defaultTypeId(clazz);
        } 
        if (paramBoolean1)
          hashMap.put(clazz.getName(), str); 
        if (paramBoolean2) {
          JavaType javaType = (JavaType)hashMap1.get(str);
          if (javaType == null || !clazz.isAssignableFrom(javaType.getRawClass()))
            hashMap1.put(str, paramMapperConfig.constructType(clazz)); 
        } 
      }  
    return new TypeNameIdResolver(paramMapperConfig, paramJavaType, hashMap, hashMap1);
  }
  
  public JsonTypeInfo.Id getMechanism() {
    return JsonTypeInfo.Id.NAME;
  }
  
  public String idFromValue(Object paramObject) {
    Class<?> clazz = paramObject.getClass();
    String str = clazz.getName();
    synchronized (this._typeToId) {
      String str1 = (String)this._typeToId.get(str);
      paramObject = str1;
      if (str1 == null) {
        if (this._config.isAnnotationProcessingEnabled()) {
          paramObject = this._config.introspectClassAnnotations(clazz);
          str1 = this._config.getAnnotationIntrospector().findTypeName(paramObject.getClassInfo());
        } 
        paramObject = str1;
        if (str1 == null)
          paramObject = _defaultTypeId(clazz); 
        this._typeToId.put(str, paramObject);
      } 
      return (String)paramObject;
    } 
  }
  
  public String idFromValueAndType(Object paramObject, Class paramClass) {
    return idFromValue(paramObject);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append('[').append(getClass().getName());
    stringBuilder.append("; id-to-type=").append(this._idToType);
    stringBuilder.append(']');
    return stringBuilder.toString();
  }
  
  public JavaType typeFromId(String paramString) {
    return (JavaType)this._idToType.get(paramString);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\jsontype\impl\TypeNameIdResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */