package com.flurry.org.codehaus.jackson.map.type;

import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Type;

public abstract class TypeModifier {
  public abstract JavaType modifyType(JavaType paramJavaType, Type paramType, TypeBindings paramTypeBindings, TypeFactory paramTypeFactory);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\type\TypeModifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */