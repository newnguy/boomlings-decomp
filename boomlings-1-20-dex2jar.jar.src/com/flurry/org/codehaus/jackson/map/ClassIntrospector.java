package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.type.JavaType;

public abstract class ClassIntrospector {
  public abstract BeanDescription forClassAnnotations(MapperConfig paramMapperConfig, JavaType paramJavaType, ClassIntrospector$MixInResolver paramClassIntrospector$MixInResolver);
  
  @Deprecated
  public BeanDescription forClassAnnotations(MapperConfig paramMapperConfig, Class paramClass, ClassIntrospector$MixInResolver paramClassIntrospector$MixInResolver) {
    return forClassAnnotations(paramMapperConfig, paramMapperConfig.constructType(paramClass), paramClassIntrospector$MixInResolver);
  }
  
  public abstract BeanDescription forCreation(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, ClassIntrospector$MixInResolver paramClassIntrospector$MixInResolver);
  
  public abstract BeanDescription forDeserialization(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, ClassIntrospector$MixInResolver paramClassIntrospector$MixInResolver);
  
  public abstract BeanDescription forDirectClassAnnotations(MapperConfig paramMapperConfig, JavaType paramJavaType, ClassIntrospector$MixInResolver paramClassIntrospector$MixInResolver);
  
  @Deprecated
  public BeanDescription forDirectClassAnnotations(MapperConfig paramMapperConfig, Class paramClass, ClassIntrospector$MixInResolver paramClassIntrospector$MixInResolver) {
    return forDirectClassAnnotations(paramMapperConfig, paramMapperConfig.constructType(paramClass), paramClassIntrospector$MixInResolver);
  }
  
  public abstract BeanDescription forSerialization(SerializationConfig paramSerializationConfig, JavaType paramJavaType, ClassIntrospector$MixInResolver paramClassIntrospector$MixInResolver);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ClassIntrospector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */