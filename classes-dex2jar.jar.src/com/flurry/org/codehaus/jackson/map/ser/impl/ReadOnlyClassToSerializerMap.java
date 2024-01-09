package com.flurry.org.codehaus.jackson.map.ser.impl;

import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.HashMap;

public final class ReadOnlyClassToSerializerMap {
  protected final SerializerCache$TypeKey _cacheKey = new SerializerCache$TypeKey(getClass(), false);
  
  protected final JsonSerializerMap _map;
  
  private ReadOnlyClassToSerializerMap(JsonSerializerMap paramJsonSerializerMap) {
    this._map = paramJsonSerializerMap;
  }
  
  public static ReadOnlyClassToSerializerMap from(HashMap paramHashMap) {
    return new ReadOnlyClassToSerializerMap(new JsonSerializerMap(paramHashMap));
  }
  
  public ReadOnlyClassToSerializerMap instance() {
    return new ReadOnlyClassToSerializerMap(this._map);
  }
  
  public JsonSerializer typedValueSerializer(JavaType paramJavaType) {
    this._cacheKey.resetTyped(paramJavaType);
    return this._map.find(this._cacheKey);
  }
  
  public JsonSerializer typedValueSerializer(Class paramClass) {
    this._cacheKey.resetTyped(paramClass);
    return this._map.find(this._cacheKey);
  }
  
  public JsonSerializer untypedValueSerializer(JavaType paramJavaType) {
    this._cacheKey.resetUntyped(paramJavaType);
    return this._map.find(this._cacheKey);
  }
  
  public JsonSerializer untypedValueSerializer(Class paramClass) {
    this._cacheKey.resetUntyped(paramClass);
    return this._map.find(this._cacheKey);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\impl\ReadOnlyClassToSerializerMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */