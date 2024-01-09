package com.flurry.org.codehaus.jackson.map.type;

import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Array;

public final class ArrayType extends TypeBase {
  protected final JavaType _componentType;
  
  protected final Object _emptyArray;
  
  private ArrayType(JavaType paramJavaType, Object paramObject1, Object paramObject2, Object paramObject3) {
    super(paramObject1.getClass(), paramJavaType.hashCode(), paramObject2, paramObject3);
    this._componentType = paramJavaType;
    this._emptyArray = paramObject1;
  }
  
  @Deprecated
  public static ArrayType construct(JavaType paramJavaType) {
    return construct(paramJavaType, (Object)null, (Object)null);
  }
  
  public static ArrayType construct(JavaType paramJavaType, Object paramObject1, Object paramObject2) {
    return new ArrayType(paramJavaType, Array.newInstance(paramJavaType.getRawClass(), 0), null, null);
  }
  
  protected JavaType _narrow(Class<?> paramClass) {
    if (!paramClass.isArray())
      throw new IllegalArgumentException("Incompatible narrowing operation: trying to narrow " + toString() + " to class " + paramClass.getName()); 
    paramClass = paramClass.getComponentType();
    return construct(TypeFactory.defaultInstance().constructType(paramClass), this._valueHandler, this._typeHandler);
  }
  
  protected String buildCanonicalName() {
    return this._class.getName();
  }
  
  public JavaType containedType(int paramInt) {
    return (paramInt == 0) ? this._componentType : null;
  }
  
  public int containedTypeCount() {
    return 1;
  }
  
  public String containedTypeName(int paramInt) {
    return (paramInt == 0) ? "E" : null;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool2 = false;
    if (paramObject == this)
      return true; 
    boolean bool1 = bool2;
    if (paramObject != null) {
      bool1 = bool2;
      if (paramObject.getClass() == getClass()) {
        paramObject = paramObject;
        bool1 = this._componentType.equals(((ArrayType)paramObject)._componentType);
      } 
    } 
    return bool1;
  }
  
  public JavaType getContentType() {
    return this._componentType;
  }
  
  public StringBuilder getErasedSignature(StringBuilder paramStringBuilder) {
    paramStringBuilder.append('[');
    return this._componentType.getErasedSignature(paramStringBuilder);
  }
  
  public StringBuilder getGenericSignature(StringBuilder paramStringBuilder) {
    paramStringBuilder.append('[');
    return this._componentType.getGenericSignature(paramStringBuilder);
  }
  
  public boolean hasGenericTypes() {
    return this._componentType.hasGenericTypes();
  }
  
  public boolean isAbstract() {
    return false;
  }
  
  public boolean isArrayType() {
    return true;
  }
  
  public boolean isConcrete() {
    return true;
  }
  
  public boolean isContainerType() {
    return true;
  }
  
  public JavaType narrowContentsBy(Class paramClass) {
    return (paramClass == this._componentType.getRawClass()) ? this : construct(this._componentType.narrowBy(paramClass), this._valueHandler, this._typeHandler);
  }
  
  public String toString() {
    return "[array type, component type: " + this._componentType + "]";
  }
  
  public JavaType widenContentsBy(Class paramClass) {
    return (paramClass == this._componentType.getRawClass()) ? this : construct(this._componentType.widenBy(paramClass), this._valueHandler, this._typeHandler);
  }
  
  public ArrayType withContentTypeHandler(Object paramObject) {
    return (paramObject == this._componentType.getTypeHandler()) ? this : new ArrayType(this._componentType.withTypeHandler(paramObject), this._emptyArray, this._valueHandler, this._typeHandler);
  }
  
  public ArrayType withContentValueHandler(Object paramObject) {
    return (paramObject == this._componentType.getValueHandler()) ? this : new ArrayType(this._componentType.withValueHandler(paramObject), this._emptyArray, this._valueHandler, this._typeHandler);
  }
  
  public ArrayType withTypeHandler(Object paramObject) {
    return (paramObject == this._typeHandler) ? this : new ArrayType(this._componentType, this._emptyArray, this._valueHandler, paramObject);
  }
  
  public ArrayType withValueHandler(Object paramObject) {
    return (paramObject == this._valueHandler) ? this : new ArrayType(this._componentType, this._emptyArray, paramObject, this._typeHandler);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\type\ArrayType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */