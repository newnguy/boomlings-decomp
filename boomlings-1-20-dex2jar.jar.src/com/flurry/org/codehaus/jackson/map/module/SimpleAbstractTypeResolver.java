package com.flurry.org.codehaus.jackson.map.module;

import com.flurry.org.codehaus.jackson.map.AbstractTypeResolver;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.type.ClassKey;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Modifier;
import java.util.HashMap;

public class SimpleAbstractTypeResolver extends AbstractTypeResolver {
  protected final HashMap _mappings = new HashMap<Object, Object>();
  
  public SimpleAbstractTypeResolver addMapping(Class paramClass1, Class<?> paramClass2) {
    if (paramClass1 == paramClass2)
      throw new IllegalArgumentException("Can not add mapping from class to itself"); 
    if (!paramClass1.isAssignableFrom(paramClass2))
      throw new IllegalArgumentException("Can not add mapping from class " + paramClass1.getName() + " to " + paramClass2.getName() + ", as latter is not a subtype of former"); 
    if (!Modifier.isAbstract(paramClass1.getModifiers()))
      throw new IllegalArgumentException("Can not add mapping from class " + paramClass1.getName() + " since it is not abstract"); 
    this._mappings.put(new ClassKey(paramClass1), paramClass2);
    return this;
  }
  
  public JavaType findTypeMapping(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType) {
    Class clazz = paramJavaType.getRawClass();
    clazz = (Class)this._mappings.get(new ClassKey(clazz));
    return (clazz == null) ? null : paramJavaType.narrowBy(clazz);
  }
  
  public JavaType resolveAbstractType(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType) {
    return null;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\module\SimpleAbstractTypeResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */