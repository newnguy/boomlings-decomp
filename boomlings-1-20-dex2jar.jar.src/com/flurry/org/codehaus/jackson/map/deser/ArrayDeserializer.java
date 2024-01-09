package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.std.ObjectArrayDeserializer;
import com.flurry.org.codehaus.jackson.map.type.ArrayType;

@Deprecated
public class ArrayDeserializer extends ObjectArrayDeserializer {
  @Deprecated
  public ArrayDeserializer(ArrayType paramArrayType, JsonDeserializer paramJsonDeserializer) {
    this(paramArrayType, paramJsonDeserializer, null);
  }
  
  public ArrayDeserializer(ArrayType paramArrayType, JsonDeserializer paramJsonDeserializer, TypeDeserializer paramTypeDeserializer) {
    super(paramArrayType, paramJsonDeserializer, paramTypeDeserializer);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\ArrayDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */