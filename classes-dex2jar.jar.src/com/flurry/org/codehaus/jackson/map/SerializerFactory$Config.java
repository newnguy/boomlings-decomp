package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.map.ser.BeanSerializerModifier;

public abstract class SerializerFactory$Config {
  public abstract boolean hasKeySerializers();
  
  public abstract boolean hasSerializerModifiers();
  
  public abstract boolean hasSerializers();
  
  public abstract Iterable keySerializers();
  
  public abstract Iterable serializerModifiers();
  
  public abstract Iterable serializers();
  
  public abstract SerializerFactory$Config withAdditionalKeySerializers(Serializers paramSerializers);
  
  public abstract SerializerFactory$Config withAdditionalSerializers(Serializers paramSerializers);
  
  public abstract SerializerFactory$Config withSerializerModifier(BeanSerializerModifier paramBeanSerializerModifier);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\SerializerFactory$Config.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */