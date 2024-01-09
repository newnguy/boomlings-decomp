package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.io.NumberInput;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.KeyDeserializer;

public abstract class StdKeyDeserializer extends KeyDeserializer {
  protected final Class _keyClass;
  
  protected StdKeyDeserializer(Class paramClass) {
    this._keyClass = paramClass;
  }
  
  protected abstract Object _parse(String paramString, DeserializationContext paramDeserializationContext);
  
  protected double _parseDouble(String paramString) {
    return NumberInput.parseDouble(paramString);
  }
  
  protected int _parseInt(String paramString) {
    return Integer.parseInt(paramString);
  }
  
  protected long _parseLong(String paramString) {
    return Long.parseLong(paramString);
  }
  
  public final Object deserializeKey(String paramString, DeserializationContext paramDeserializationContext) {
    if (paramString == null)
      return null; 
    try {
      Object object2 = _parse(paramString, paramDeserializationContext);
      Object object1 = object2;
      if (object2 == null)
        throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "not a valid representation"); 
      return object1;
    } catch (Exception exception) {
      throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "not a valid representation: " + exception.getMessage());
    } 
  }
  
  public Class getKeyClass() {
    return this._keyClass;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdKeyDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */