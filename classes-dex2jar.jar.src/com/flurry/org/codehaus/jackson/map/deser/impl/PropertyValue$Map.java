package com.flurry.org.codehaus.jackson.map.deser.impl;

final class PropertyValue$Map extends PropertyValue {
  final Object _key;
  
  public PropertyValue$Map(PropertyValue paramPropertyValue, Object paramObject1, Object paramObject2) {
    super(paramPropertyValue, paramObject1);
    this._key = paramObject2;
  }
  
  public void assign(Object paramObject) {
    ((java.util.Map<Object, Object>)paramObject).put(this._key, this.value);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\impl\PropertyValue$Map.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */