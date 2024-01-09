package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import java.lang.reflect.Constructor;

final class StdKeyDeserializer$StringCtorKeyDeserializer extends StdKeyDeserializer {
  protected final Constructor _ctor;
  
  public StdKeyDeserializer$StringCtorKeyDeserializer(Constructor paramConstructor) {
    super(paramConstructor.getDeclaringClass());
    this._ctor = paramConstructor;
  }
  
  public Object _parse(String paramString, DeserializationContext paramDeserializationContext) {
    return this._ctor.newInstance(new Object[] { paramString });
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdKeyDeserializer$StringCtorKeyDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */