package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import java.lang.reflect.Method;

final class StdKeyDeserializer$StringFactoryKeyDeserializer extends StdKeyDeserializer {
  final Method _factoryMethod;
  
  public StdKeyDeserializer$StringFactoryKeyDeserializer(Method paramMethod) {
    super(paramMethod.getDeclaringClass());
    this._factoryMethod = paramMethod;
  }
  
  public Object _parse(String paramString, DeserializationContext paramDeserializationContext) {
    return this._factoryMethod.invoke(null, new Object[] { paramString });
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdKeyDeserializer$StringFactoryKeyDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */