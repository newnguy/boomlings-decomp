package com.flurry.org.codehaus.jackson.map.jsontype.impl;

import com.flurry.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.flurry.org.codehaus.jackson.map.type.TypeFactory;
import com.flurry.org.codehaus.jackson.type.JavaType;

public class MinimalClassNameIdResolver extends ClassNameIdResolver {
  protected final String _basePackageName;
  
  protected final String _basePackagePrefix;
  
  protected MinimalClassNameIdResolver(JavaType paramJavaType, TypeFactory paramTypeFactory) {
    super(paramJavaType, paramTypeFactory);
    String str = paramJavaType.getRawClass().getName();
    int i = str.lastIndexOf('.');
    if (i < 0) {
      this._basePackageName = "";
      this._basePackagePrefix = ".";
      return;
    } 
    this._basePackagePrefix = str.substring(0, i + 1);
    this._basePackageName = str.substring(0, i);
  }
  
  public JsonTypeInfo.Id getMechanism() {
    return JsonTypeInfo.Id.MINIMAL_CLASS;
  }
  
  public String idFromValue(Object paramObject) {
    String str = paramObject.getClass().getName();
    paramObject = str;
    if (str.startsWith(this._basePackagePrefix))
      paramObject = str.substring(this._basePackagePrefix.length() - 1); 
    return (String)paramObject;
  }
  
  public JavaType typeFromId(String paramString) {
    String str = paramString;
    if (paramString.startsWith(".")) {
      StringBuilder stringBuilder = new StringBuilder(paramString.length() + this._basePackageName.length());
      if (this._basePackageName.length() == 0) {
        stringBuilder.append(paramString.substring(1));
      } else {
        stringBuilder.append(this._basePackageName).append(paramString);
      } 
      str = stringBuilder.toString();
    } 
    return super.typeFromId(str);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\jsontype\impl\MinimalClassNameIdResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */