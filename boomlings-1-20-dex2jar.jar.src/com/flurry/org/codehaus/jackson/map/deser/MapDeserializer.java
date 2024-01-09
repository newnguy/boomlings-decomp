package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.KeyDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.std.MapDeserializer;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Constructor;

@Deprecated
public class MapDeserializer extends MapDeserializer {
  protected MapDeserializer(MapDeserializer paramMapDeserializer) {
    super(paramMapDeserializer);
  }
  
  public MapDeserializer(JavaType paramJavaType, ValueInstantiator paramValueInstantiator, KeyDeserializer paramKeyDeserializer, JsonDeserializer paramJsonDeserializer, TypeDeserializer paramTypeDeserializer) {
    super(paramJavaType, paramValueInstantiator, paramKeyDeserializer, paramJsonDeserializer, paramTypeDeserializer);
  }
  
  @Deprecated
  public MapDeserializer(JavaType paramJavaType, Constructor paramConstructor, KeyDeserializer paramKeyDeserializer, JsonDeserializer paramJsonDeserializer, TypeDeserializer paramTypeDeserializer) {
    super(paramJavaType, paramConstructor, paramKeyDeserializer, paramJsonDeserializer, paramTypeDeserializer);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\MapDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */