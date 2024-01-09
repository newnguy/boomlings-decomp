package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.type.JavaType;

public final class SettableBeanProperty$NullProvider {
  private final boolean _isPrimitive;
  
  private final Object _nullValue;
  
  private final Class _rawType;
  
  protected SettableBeanProperty$NullProvider(JavaType paramJavaType, Object paramObject) {
    this._nullValue = paramObject;
    this._isPrimitive = paramJavaType.isPrimitive();
    this._rawType = paramJavaType.getRawClass();
  }
  
  public Object nullValue(DeserializationContext paramDeserializationContext) {
    if (this._isPrimitive && paramDeserializationContext.isEnabled(DeserializationConfig.Feature.FAIL_ON_NULL_FOR_PRIMITIVES))
      throw paramDeserializationContext.mappingException("Can not map JSON null into type " + this._rawType.getName() + " (set DeserializationConfig.Feature.FAIL_ON_NULL_FOR_PRIMITIVES to 'false' to allow)"); 
    return this._nullValue;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\SettableBeanProperty$NullProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */