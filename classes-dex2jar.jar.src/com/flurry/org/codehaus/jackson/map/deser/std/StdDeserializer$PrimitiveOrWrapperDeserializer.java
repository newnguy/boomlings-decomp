package com.flurry.org.codehaus.jackson.map.deser.std;

public abstract class StdDeserializer$PrimitiveOrWrapperDeserializer extends StdScalarDeserializer {
  final Object _nullValue;
  
  protected StdDeserializer$PrimitiveOrWrapperDeserializer(Class paramClass, Object paramObject) {
    super(paramClass);
    this._nullValue = paramObject;
  }
  
  public final Object getNullValue() {
    return this._nullValue;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdDeserializer$PrimitiveOrWrapperDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */