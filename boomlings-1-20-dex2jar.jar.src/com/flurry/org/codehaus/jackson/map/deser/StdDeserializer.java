package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.map.deser.std.StdDeserializer;
import com.flurry.org.codehaus.jackson.type.JavaType;

@Deprecated
public abstract class StdDeserializer extends StdDeserializer {
  protected StdDeserializer(JavaType paramJavaType) {
    super(paramJavaType);
  }
  
  protected StdDeserializer(Class paramClass) {
    super(paramClass);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\StdDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */