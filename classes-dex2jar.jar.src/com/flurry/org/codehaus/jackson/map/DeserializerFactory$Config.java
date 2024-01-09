package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.map.deser.BeanDeserializerModifier;
import com.flurry.org.codehaus.jackson.map.deser.ValueInstantiators;

public abstract class DeserializerFactory$Config {
  public abstract Iterable abstractTypeResolvers();
  
  public abstract Iterable deserializerModifiers();
  
  public abstract Iterable deserializers();
  
  public abstract boolean hasAbstractTypeResolvers();
  
  public abstract boolean hasDeserializerModifiers();
  
  public abstract boolean hasDeserializers();
  
  public abstract boolean hasKeyDeserializers();
  
  public abstract boolean hasValueInstantiators();
  
  public abstract Iterable keyDeserializers();
  
  public abstract Iterable valueInstantiators();
  
  public abstract DeserializerFactory$Config withAbstractTypeResolver(AbstractTypeResolver paramAbstractTypeResolver);
  
  public abstract DeserializerFactory$Config withAdditionalDeserializers(Deserializers paramDeserializers);
  
  public abstract DeserializerFactory$Config withAdditionalKeyDeserializers(KeyDeserializers paramKeyDeserializers);
  
  public abstract DeserializerFactory$Config withDeserializerModifier(BeanDeserializerModifier paramBeanDeserializerModifier);
  
  public abstract DeserializerFactory$Config withValueInstantiators(ValueInstantiators paramValueInstantiators);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\DeserializerFactory$Config.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */