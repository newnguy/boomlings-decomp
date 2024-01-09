package com.flurry.org.codehaus.jackson.map.jsontype.impl;

import com.flurry.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeIdResolver;

public abstract class TypeSerializerBase extends TypeSerializer {
  protected final TypeIdResolver _idResolver;
  
  protected final BeanProperty _property;
  
  protected TypeSerializerBase(TypeIdResolver paramTypeIdResolver, BeanProperty paramBeanProperty) {
    this._idResolver = paramTypeIdResolver;
    this._property = paramBeanProperty;
  }
  
  public String getPropertyName() {
    return null;
  }
  
  public TypeIdResolver getTypeIdResolver() {
    return this._idResolver;
  }
  
  public abstract JsonTypeInfo.As getTypeInclusion();
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\jsontype\impl\TypeSerializerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */