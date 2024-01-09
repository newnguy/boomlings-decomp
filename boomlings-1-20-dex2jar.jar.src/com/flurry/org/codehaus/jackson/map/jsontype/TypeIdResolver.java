package com.flurry.org.codehaus.jackson.map.jsontype;

import com.flurry.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.flurry.org.codehaus.jackson.type.JavaType;

public interface TypeIdResolver {
  JsonTypeInfo.Id getMechanism();
  
  String idFromValue(Object paramObject);
  
  String idFromValueAndType(Object paramObject, Class paramClass);
  
  void init(JavaType paramJavaType);
  
  JavaType typeFromId(String paramString);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\jsontype\TypeIdResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */