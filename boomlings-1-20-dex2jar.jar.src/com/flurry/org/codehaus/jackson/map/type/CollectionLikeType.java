package com.flurry.org.codehaus.jackson.map.type;

import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.Collection;

public class CollectionLikeType extends TypeBase {
  protected final JavaType _elementType;
  
  @Deprecated
  protected CollectionLikeType(Class paramClass, JavaType paramJavaType) {
    super(paramClass, paramJavaType.hashCode(), (Object)null, (Object)null);
    this._elementType = paramJavaType;
  }
  
  protected CollectionLikeType(Class paramClass, JavaType paramJavaType, Object paramObject1, Object paramObject2) {
    super(paramClass, paramJavaType.hashCode(), paramObject1, paramObject2);
    this._elementType = paramJavaType;
  }
  
  public static CollectionLikeType construct(Class paramClass, JavaType paramJavaType) {
    return new CollectionLikeType(paramClass, paramJavaType, null, null);
  }
  
  protected JavaType _narrow(Class paramClass) {
    return new CollectionLikeType(paramClass, this._elementType, this._valueHandler, this._typeHandler);
  }
  
  protected String buildCanonicalName() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this._class.getName());
    if (this._elementType != null) {
      stringBuilder.append('<');
      stringBuilder.append(this._elementType.toCanonical());
      stringBuilder.append('>');
    } 
    return stringBuilder.toString();
  }
  
  public JavaType containedType(int paramInt) {
    return (paramInt == 0) ? this._elementType : null;
  }
  
  public int containedTypeCount() {
    return 1;
  }
  
  public String containedTypeName(int paramInt) {
    return (paramInt == 0) ? "E" : null;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject != this) {
      if (paramObject == null)
        return false; 
      if (paramObject.getClass() != getClass())
        return false; 
      paramObject = paramObject;
      if (this._class != ((CollectionLikeType)paramObject)._class || !this._elementType.equals(((CollectionLikeType)paramObject)._elementType))
        bool = false; 
    } 
    return bool;
  }
  
  public JavaType getContentType() {
    return this._elementType;
  }
  
  public StringBuilder getErasedSignature(StringBuilder paramStringBuilder) {
    return _classSignature(this._class, paramStringBuilder, true);
  }
  
  public StringBuilder getGenericSignature(StringBuilder paramStringBuilder) {
    _classSignature(this._class, paramStringBuilder, false);
    paramStringBuilder.append('<');
    this._elementType.getGenericSignature(paramStringBuilder);
    paramStringBuilder.append(">;");
    return paramStringBuilder;
  }
  
  public boolean isCollectionLikeType() {
    return true;
  }
  
  public boolean isContainerType() {
    return true;
  }
  
  public boolean isTrueCollectionType() {
    return Collection.class.isAssignableFrom(this._class);
  }
  
  public JavaType narrowContentsBy(Class paramClass) {
    return (paramClass == this._elementType.getRawClass()) ? this : new CollectionLikeType(this._class, this._elementType.narrowBy(paramClass), this._valueHandler, this._typeHandler);
  }
  
  public String toString() {
    return "[collection-like type; class " + this._class.getName() + ", contains " + this._elementType + "]";
  }
  
  public JavaType widenContentsBy(Class paramClass) {
    return (paramClass == this._elementType.getRawClass()) ? this : new CollectionLikeType(this._class, this._elementType.widenBy(paramClass), this._valueHandler, this._typeHandler);
  }
  
  public CollectionLikeType withContentTypeHandler(Object paramObject) {
    return new CollectionLikeType(this._class, this._elementType.withTypeHandler(paramObject), this._valueHandler, this._typeHandler);
  }
  
  public CollectionLikeType withContentValueHandler(Object paramObject) {
    return new CollectionLikeType(this._class, this._elementType.withValueHandler(paramObject), this._valueHandler, this._typeHandler);
  }
  
  public CollectionLikeType withTypeHandler(Object paramObject) {
    return new CollectionLikeType(this._class, this._elementType, this._valueHandler, paramObject);
  }
  
  public CollectionLikeType withValueHandler(Object paramObject) {
    return new CollectionLikeType(this._class, this._elementType, paramObject, this._typeHandler);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\type\CollectionLikeType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */