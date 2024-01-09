package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.map.deser.ValueInstantiator;
import com.flurry.org.codehaus.jackson.map.introspect.Annotated;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeIdResolver;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;

public abstract class HandlerInstantiator {
  public abstract JsonDeserializer deserializerInstance(DeserializationConfig paramDeserializationConfig, Annotated paramAnnotated, Class paramClass);
  
  public abstract KeyDeserializer keyDeserializerInstance(DeserializationConfig paramDeserializationConfig, Annotated paramAnnotated, Class paramClass);
  
  public abstract JsonSerializer serializerInstance(SerializationConfig paramSerializationConfig, Annotated paramAnnotated, Class paramClass);
  
  public abstract TypeIdResolver typeIdResolverInstance(MapperConfig paramMapperConfig, Annotated paramAnnotated, Class paramClass);
  
  public abstract TypeResolverBuilder typeResolverBuilderInstance(MapperConfig paramMapperConfig, Annotated paramAnnotated, Class paramClass);
  
  public ValueInstantiator valueInstantiatorInstance(MapperConfig paramMapperConfig, Annotated paramAnnotated, Class paramClass) {
    return null;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\HandlerInstantiator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */