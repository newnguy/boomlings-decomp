package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import com.flurry.org.codehaus.jackson.map.util.EnumResolver;

final class StdKeyDeserializer$EnumKD extends StdKeyDeserializer {
  protected final AnnotatedMethod _factory;
  
  protected final EnumResolver _resolver;
  
  protected StdKeyDeserializer$EnumKD(EnumResolver paramEnumResolver, AnnotatedMethod paramAnnotatedMethod) {
    super(paramEnumResolver.getEnumClass());
    this._resolver = paramEnumResolver;
    this._factory = paramAnnotatedMethod;
  }
  
  public Object _parse(String paramString, DeserializationContext paramDeserializationContext) {
    if (this._factory != null)
      try {
        return this._factory.call1(paramString);
      } catch (Exception exception) {
        ClassUtil.unwrapAndThrowAsIAE(exception);
      }  
    Enum enum_2 = this._resolver.findEnum(paramString);
    Enum enum_1 = enum_2;
    if (enum_2 == null)
      throw paramDeserializationContext.weirdKeyException(this._keyClass, paramString, "not one of values for Enum class"); 
    return enum_1;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdKeyDeserializer$EnumKD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */