package com.flurry.org.codehaus.jackson.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class TypeReference implements Comparable {
  final Type _type;
  
  protected TypeReference() {
    Type type = getClass().getGenericSuperclass();
    if (type instanceof Class)
      throw new IllegalArgumentException("Internal error: TypeReference constructed without actual type information"); 
    this._type = ((ParameterizedType)type).getActualTypeArguments()[0];
  }
  
  public int compareTo(TypeReference paramTypeReference) {
    return 0;
  }
  
  public Type getType() {
    return this._type;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\type\TypeReference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */