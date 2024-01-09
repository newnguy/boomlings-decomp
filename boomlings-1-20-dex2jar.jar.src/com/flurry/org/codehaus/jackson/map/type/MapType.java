package com.flurry.org.codehaus.jackson.map.type;

import com.flurry.org.codehaus.jackson.type.JavaType;

public final class MapType extends MapLikeType {
  @Deprecated
  private MapType(Class paramClass, JavaType paramJavaType1, JavaType paramJavaType2) {
    this(paramClass, paramJavaType1, paramJavaType2, (Object)null, (Object)null);
  }
  
  private MapType(Class paramClass, JavaType paramJavaType1, JavaType paramJavaType2, Object paramObject1, Object paramObject2) {
    super(paramClass, paramJavaType1, paramJavaType2, paramObject1, paramObject2);
  }
  
  public static MapType construct(Class paramClass, JavaType paramJavaType1, JavaType paramJavaType2) {
    return new MapType(paramClass, paramJavaType1, paramJavaType2, null, null);
  }
  
  protected JavaType _narrow(Class paramClass) {
    return new MapType(paramClass, this._keyType, this._valueType, this._valueHandler, this._typeHandler);
  }
  
  public JavaType narrowContentsBy(Class paramClass) {
    return (paramClass == this._valueType.getRawClass()) ? this : new MapType(this._class, this._keyType, this._valueType.narrowBy(paramClass), this._valueHandler, this._typeHandler);
  }
  
  public JavaType narrowKey(Class paramClass) {
    return (paramClass == this._keyType.getRawClass()) ? this : new MapType(this._class, this._keyType.narrowBy(paramClass), this._valueType, this._valueHandler, this._typeHandler);
  }
  
  public String toString() {
    return "[map type; class " + this._class.getName() + ", " + this._keyType + " -> " + this._valueType + "]";
  }
  
  public JavaType widenContentsBy(Class paramClass) {
    return (paramClass == this._valueType.getRawClass()) ? this : new MapType(this._class, this._keyType, this._valueType.widenBy(paramClass), this._valueHandler, this._typeHandler);
  }
  
  public JavaType widenKey(Class paramClass) {
    return (paramClass == this._keyType.getRawClass()) ? this : new MapType(this._class, this._keyType.widenBy(paramClass), this._valueType, this._valueHandler, this._typeHandler);
  }
  
  public MapType withContentTypeHandler(Object paramObject) {
    return new MapType(this._class, this._keyType, this._valueType.withTypeHandler(paramObject), this._valueHandler, this._typeHandler);
  }
  
  public MapType withContentValueHandler(Object paramObject) {
    return new MapType(this._class, this._keyType, this._valueType.withValueHandler(paramObject), this._valueHandler, this._typeHandler);
  }
  
  public MapType withKeyTypeHandler(Object paramObject) {
    return new MapType(this._class, this._keyType.withTypeHandler(paramObject), this._valueType, this._valueHandler, this._typeHandler);
  }
  
  public MapType withKeyValueHandler(Object paramObject) {
    return new MapType(this._class, this._keyType.withValueHandler(paramObject), this._valueType, this._valueHandler, this._typeHandler);
  }
  
  public MapType withTypeHandler(Object paramObject) {
    return new MapType(this._class, this._keyType, this._valueType, this._valueHandler, paramObject);
  }
  
  public MapType withValueHandler(Object paramObject) {
    return new MapType(this._class, this._keyType, this._valueType, paramObject, this._typeHandler);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\type\MapType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */