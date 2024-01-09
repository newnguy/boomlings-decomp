package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.type.JavaType;

public abstract class ContainerDeserializerBase extends StdDeserializer {
  protected ContainerDeserializerBase(Class paramClass) {
    super(paramClass);
  }
  
  public abstract JsonDeserializer getContentDeserializer();
  
  public abstract JavaType getContentType();
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\ContainerDeserializerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */