package com.flurry.org.codehaus.jackson.map.ser.impl;

import com.flurry.org.codehaus.jackson.type.JavaType;

public final class SerializerCache$TypeKey {
  protected Class _class;
  
  protected int _hashCode;
  
  protected boolean _isTyped;
  
  protected JavaType _type;
  
  public SerializerCache$TypeKey(JavaType paramJavaType, boolean paramBoolean) {
    this._type = paramJavaType;
    this._class = null;
    this._isTyped = paramBoolean;
    this._hashCode = hash(paramJavaType, paramBoolean);
  }
  
  public SerializerCache$TypeKey(Class paramClass, boolean paramBoolean) {
    this._class = paramClass;
    this._type = null;
    this._isTyped = paramBoolean;
    this._hashCode = hash(paramClass, paramBoolean);
  }
  
  private static final int hash(JavaType paramJavaType, boolean paramBoolean) {
    int j = paramJavaType.hashCode() - 1;
    int i = j;
    if (paramBoolean)
      i = j - 1; 
    return i;
  }
  
  private static final int hash(Class paramClass, boolean paramBoolean) {
    int j = paramClass.getName().hashCode();
    int i = j;
    if (paramBoolean)
      i = j + 1; 
    return i;
  }
  
  public final boolean equals(Object paramObject) {
    boolean bool = true;
    if (paramObject != this) {
      paramObject = paramObject;
      if (((SerializerCache$TypeKey)paramObject)._isTyped == this._isTyped) {
        if (this._class != null) {
          if (((SerializerCache$TypeKey)paramObject)._class != this._class)
            bool = false; 
          return bool;
        } 
        return this._type.equals(((SerializerCache$TypeKey)paramObject)._type);
      } 
      bool = false;
    } 
    return bool;
  }
  
  public final int hashCode() {
    return this._hashCode;
  }
  
  public void resetTyped(JavaType paramJavaType) {
    this._type = paramJavaType;
    this._class = null;
    this._isTyped = true;
    this._hashCode = hash(paramJavaType, true);
  }
  
  public void resetTyped(Class paramClass) {
    this._type = null;
    this._class = paramClass;
    this._isTyped = true;
    this._hashCode = hash(paramClass, true);
  }
  
  public void resetUntyped(JavaType paramJavaType) {
    this._type = paramJavaType;
    this._class = null;
    this._isTyped = false;
    this._hashCode = hash(paramJavaType, false);
  }
  
  public void resetUntyped(Class paramClass) {
    this._type = null;
    this._class = paramClass;
    this._isTyped = false;
    this._hashCode = hash(paramClass, false);
  }
  
  public final String toString() {
    return (this._class != null) ? ("{class: " + this._class.getName() + ", typed? " + this._isTyped + "}") : ("{type: " + this._type + ", typed? " + this._isTyped + "}");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\impl\SerializerCache$TypeKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */