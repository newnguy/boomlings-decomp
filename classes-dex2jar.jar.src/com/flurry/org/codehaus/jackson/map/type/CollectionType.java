package com.flurry.org.codehaus.jackson.map.type;

import com.flurry.org.codehaus.jackson.type.JavaType;

public final class CollectionType extends CollectionLikeType {
  private CollectionType(Class paramClass, JavaType paramJavaType, Object paramObject1, Object paramObject2) {
    super(paramClass, paramJavaType, paramObject1, paramObject2);
  }
  
  public static CollectionType construct(Class paramClass, JavaType paramJavaType) {
    return new CollectionType(paramClass, paramJavaType, null, null);
  }
  
  protected JavaType _narrow(Class paramClass) {
    return new CollectionType(paramClass, this._elementType, null, null);
  }
  
  public JavaType narrowContentsBy(Class paramClass) {
    return (paramClass == this._elementType.getRawClass()) ? this : new CollectionType(this._class, this._elementType.narrowBy(paramClass), this._valueHandler, this._typeHandler);
  }
  
  public String toString() {
    return "[collection type; class " + this._class.getName() + ", contains " + this._elementType + "]";
  }
  
  public JavaType widenContentsBy(Class paramClass) {
    return (paramClass == this._elementType.getRawClass()) ? this : new CollectionType(this._class, this._elementType.widenBy(paramClass), this._valueHandler, this._typeHandler);
  }
  
  public CollectionType withContentTypeHandler(Object paramObject) {
    return new CollectionType(this._class, this._elementType.withTypeHandler(paramObject), this._valueHandler, this._typeHandler);
  }
  
  public CollectionType withContentValueHandler(Object paramObject) {
    return new CollectionType(this._class, this._elementType.withValueHandler(paramObject), this._valueHandler, this._typeHandler);
  }
  
  public CollectionType withTypeHandler(Object paramObject) {
    return new CollectionType(this._class, this._elementType, this._valueHandler, paramObject);
  }
  
  public CollectionType withValueHandler(Object paramObject) {
    return new CollectionType(this._class, this._elementType, paramObject, this._typeHandler);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\type\CollectionType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */