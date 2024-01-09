package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.std.CollectionDeserializer;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Constructor;

@Deprecated
public class CollectionDeserializer extends CollectionDeserializer {
  protected CollectionDeserializer(CollectionDeserializer paramCollectionDeserializer) {
    super(paramCollectionDeserializer);
  }
  
  public CollectionDeserializer(JavaType paramJavaType, JsonDeserializer paramJsonDeserializer, TypeDeserializer paramTypeDeserializer, ValueInstantiator paramValueInstantiator) {
    super(paramJavaType, paramJsonDeserializer, paramTypeDeserializer, paramValueInstantiator);
  }
  
  @Deprecated
  public CollectionDeserializer(JavaType paramJavaType, JsonDeserializer paramJsonDeserializer, TypeDeserializer paramTypeDeserializer, Constructor paramConstructor) {
    super(paramJavaType, paramJsonDeserializer, paramTypeDeserializer, paramConstructor);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\CollectionDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */