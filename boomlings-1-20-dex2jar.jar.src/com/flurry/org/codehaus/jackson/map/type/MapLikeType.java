package com.flurry.org.codehaus.jackson.map.type;

import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.Map;

public class MapLikeType extends TypeBase {
  protected final JavaType _keyType;
  
  protected final JavaType _valueType;
  
  @Deprecated
  protected MapLikeType(Class paramClass, JavaType paramJavaType1, JavaType paramJavaType2) {
    super(paramClass, paramJavaType1.hashCode() ^ paramJavaType2.hashCode(), (Object)null, (Object)null);
    this._keyType = paramJavaType1;
    this._valueType = paramJavaType2;
  }
  
  protected MapLikeType(Class paramClass, JavaType paramJavaType1, JavaType paramJavaType2, Object paramObject1, Object paramObject2) {
    super(paramClass, paramJavaType1.hashCode() ^ paramJavaType2.hashCode(), paramObject1, paramObject2);
    this._keyType = paramJavaType1;
    this._valueType = paramJavaType2;
  }
  
  public static MapLikeType construct(Class paramClass, JavaType paramJavaType1, JavaType paramJavaType2) {
    return new MapLikeType(paramClass, paramJavaType1, paramJavaType2, null, null);
  }
  
  protected JavaType _narrow(Class paramClass) {
    return new MapLikeType(paramClass, this._keyType, this._valueType, this._valueHandler, this._typeHandler);
  }
  
  protected String buildCanonicalName() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this._class.getName());
    if (this._keyType != null) {
      stringBuilder.append('<');
      stringBuilder.append(this._keyType.toCanonical());
      stringBuilder.append(',');
      stringBuilder.append(this._valueType.toCanonical());
      stringBuilder.append('>');
    } 
    return stringBuilder.toString();
  }
  
  public JavaType containedType(int paramInt) {
    return (paramInt == 0) ? this._keyType : ((paramInt == 1) ? this._valueType : null);
  }
  
  public int containedTypeCount() {
    return 2;
  }
  
  public String containedTypeName(int paramInt) {
    return (paramInt == 0) ? "K" : ((paramInt == 1) ? "V" : null);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject != this) {
      if (paramObject == null)
        return false; 
      if (paramObject.getClass() != getClass())
        return false; 
      paramObject = paramObject;
      if (this._class != ((MapLikeType)paramObject)._class || !this._keyType.equals(((MapLikeType)paramObject)._keyType) || !this._valueType.equals(((MapLikeType)paramObject)._valueType))
        bool = false; 
    } 
    return bool;
  }
  
  public JavaType getContentType() {
    return this._valueType;
  }
  
  public StringBuilder getErasedSignature(StringBuilder paramStringBuilder) {
    return _classSignature(this._class, paramStringBuilder, true);
  }
  
  public StringBuilder getGenericSignature(StringBuilder paramStringBuilder) {
    _classSignature(this._class, paramStringBuilder, false);
    paramStringBuilder.append('<');
    this._keyType.getGenericSignature(paramStringBuilder);
    this._valueType.getGenericSignature(paramStringBuilder);
    paramStringBuilder.append(">;");
    return paramStringBuilder;
  }
  
  public JavaType getKeyType() {
    return this._keyType;
  }
  
  public boolean isContainerType() {
    return true;
  }
  
  public boolean isMapLikeType() {
    return true;
  }
  
  public boolean isTrueMapType() {
    return Map.class.isAssignableFrom(this._class);
  }
  
  public JavaType narrowContentsBy(Class paramClass) {
    return (paramClass == this._valueType.getRawClass()) ? this : new MapLikeType(this._class, this._keyType, this._valueType.narrowBy(paramClass), this._valueHandler, this._typeHandler);
  }
  
  public JavaType narrowKey(Class paramClass) {
    return (paramClass == this._keyType.getRawClass()) ? this : new MapLikeType(this._class, this._keyType.narrowBy(paramClass), this._valueType, this._valueHandler, this._typeHandler);
  }
  
  public String toString() {
    return "[map-like type; class " + this._class.getName() + ", " + this._keyType + " -> " + this._valueType + "]";
  }
  
  public JavaType widenContentsBy(Class paramClass) {
    return (paramClass == this._valueType.getRawClass()) ? this : new MapLikeType(this._class, this._keyType, this._valueType.widenBy(paramClass), this._valueHandler, this._typeHandler);
  }
  
  public JavaType widenKey(Class paramClass) {
    return (paramClass == this._keyType.getRawClass()) ? this : new MapLikeType(this._class, this._keyType.widenBy(paramClass), this._valueType, this._valueHandler, this._typeHandler);
  }
  
  public MapLikeType withContentTypeHandler(Object paramObject) {
    return new MapLikeType(this._class, this._keyType, this._valueType.withTypeHandler(paramObject), this._valueHandler, this._typeHandler);
  }
  
  public MapLikeType withContentValueHandler(Object paramObject) {
    return new MapLikeType(this._class, this._keyType, this._valueType.withValueHandler(paramObject), this._valueHandler, this._typeHandler);
  }
  
  public MapLikeType withKeyTypeHandler(Object paramObject) {
    return new MapLikeType(this._class, this._keyType.withTypeHandler(paramObject), this._valueType, this._valueHandler, this._typeHandler);
  }
  
  public MapLikeType withKeyValueHandler(Object paramObject) {
    return new MapLikeType(this._class, this._keyType.withValueHandler(paramObject), this._valueType, this._valueHandler, this._typeHandler);
  }
  
  public MapLikeType withTypeHandler(Object paramObject) {
    return new MapLikeType(this._class, this._keyType, this._valueType, this._valueHandler, paramObject);
  }
  
  public MapLikeType withValueHandler(Object paramObject) {
    return new MapLikeType(this._class, this._keyType, this._valueType, paramObject, this._typeHandler);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\type\MapLikeType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */