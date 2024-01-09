package com.flurry.org.codehaus.jackson.map.jsontype.impl;

import com.flurry.org.codehaus.jackson.map.jsontype.TypeIdResolver;
import com.flurry.org.codehaus.jackson.map.type.TypeFactory;
import com.flurry.org.codehaus.jackson.type.JavaType;

public abstract class TypeIdResolverBase implements TypeIdResolver {
  protected final JavaType _baseType;
  
  protected final TypeFactory _typeFactory;
  
  protected TypeIdResolverBase(JavaType paramJavaType, TypeFactory paramTypeFactory) {
    this._baseType = paramJavaType;
    this._typeFactory = paramTypeFactory;
  }
  
  public String idFromBaseType() {
    return idFromValueAndType(null, this._baseType.getRawClass());
  }
  
  public void init(JavaType paramJavaType) {}
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\jsontype\impl\TypeIdResolverBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */