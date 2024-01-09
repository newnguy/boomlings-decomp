package com.flurry.org.codehaus.jackson.map.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class HierarchicType {
  protected final Type _actualType;
  
  protected final ParameterizedType _genericType;
  
  protected final Class _rawClass;
  
  protected HierarchicType _subType;
  
  protected HierarchicType _superType;
  
  public HierarchicType(Type paramType) {
    this._actualType = paramType;
    if (paramType instanceof Class) {
      this._rawClass = (Class)paramType;
      this._genericType = null;
      return;
    } 
    if (paramType instanceof ParameterizedType) {
      this._genericType = (ParameterizedType)paramType;
      this._rawClass = (Class)this._genericType.getRawType();
      return;
    } 
    throw new IllegalArgumentException("Type " + paramType.getClass().getName() + " can not be used to construct HierarchicType");
  }
  
  private HierarchicType(Type paramType, Class paramClass, ParameterizedType paramParameterizedType, HierarchicType paramHierarchicType1, HierarchicType paramHierarchicType2) {
    this._actualType = paramType;
    this._rawClass = paramClass;
    this._genericType = paramParameterizedType;
    this._superType = paramHierarchicType1;
    this._subType = paramHierarchicType2;
  }
  
  public final ParameterizedType asGeneric() {
    return this._genericType;
  }
  
  public HierarchicType deepCloneWithoutSubtype() {
    HierarchicType hierarchicType1;
    if (this._superType == null) {
      hierarchicType1 = null;
    } else {
      hierarchicType1 = this._superType.deepCloneWithoutSubtype();
    } 
    HierarchicType hierarchicType2 = new HierarchicType(this._actualType, this._rawClass, this._genericType, hierarchicType1, null);
    if (hierarchicType1 != null)
      hierarchicType1.setSubType(hierarchicType2); 
    return hierarchicType2;
  }
  
  public final Class getRawClass() {
    return this._rawClass;
  }
  
  public final HierarchicType getSubType() {
    return this._subType;
  }
  
  public final HierarchicType getSuperType() {
    return this._superType;
  }
  
  public final boolean isGeneric() {
    return (this._genericType != null);
  }
  
  public void setSubType(HierarchicType paramHierarchicType) {
    this._subType = paramHierarchicType;
  }
  
  public void setSuperType(HierarchicType paramHierarchicType) {
    this._superType = paramHierarchicType;
  }
  
  public String toString() {
    return (this._genericType != null) ? this._genericType.toString() : this._rawClass.getName();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\type\HierarchicType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */