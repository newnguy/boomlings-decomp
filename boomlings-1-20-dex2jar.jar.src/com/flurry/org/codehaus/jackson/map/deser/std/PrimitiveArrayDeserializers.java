package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.type.TypeFactory;
import java.util.HashMap;

public class PrimitiveArrayDeserializers {
  static final PrimitiveArrayDeserializers instance = new PrimitiveArrayDeserializers();
  
  HashMap _allDeserializers = new HashMap<Object, Object>();
  
  protected PrimitiveArrayDeserializers() {
    add(boolean.class, new PrimitiveArrayDeserializers$BooleanDeser());
    add(byte.class, new PrimitiveArrayDeserializers$ByteDeser());
    add(short.class, new PrimitiveArrayDeserializers$ShortDeser());
    add(int.class, new PrimitiveArrayDeserializers$IntDeser());
    add(long.class, new PrimitiveArrayDeserializers$LongDeser());
    add(float.class, new PrimitiveArrayDeserializers$FloatDeser());
    add(double.class, new PrimitiveArrayDeserializers$DoubleDeser());
    add(String.class, new PrimitiveArrayDeserializers$StringDeser());
    add(char.class, new PrimitiveArrayDeserializers$CharDeser());
  }
  
  private void add(Class paramClass, JsonDeserializer paramJsonDeserializer) {
    this._allDeserializers.put(TypeFactory.defaultInstance().constructType(paramClass), paramJsonDeserializer);
  }
  
  public static HashMap getAll() {
    return instance._allDeserializers;
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer) {
    return paramTypeDeserializer.deserializeTypedFromArray(paramJsonParser, paramDeserializationContext);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\PrimitiveArrayDeserializers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */