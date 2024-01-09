package com.flurry.org.codehaus.jackson.map.deser.impl;

import com.flurry.org.codehaus.jackson.map.deser.SettableAnyProperty;

final class PropertyValue$Any extends PropertyValue {
  final SettableAnyProperty _property;
  
  final String _propertyName;
  
  public PropertyValue$Any(PropertyValue paramPropertyValue, Object paramObject, SettableAnyProperty paramSettableAnyProperty, String paramString) {
    super(paramPropertyValue, paramObject);
    this._property = paramSettableAnyProperty;
    this._propertyName = paramString;
  }
  
  public void assign(Object paramObject) {
    this._property.set(paramObject, this._propertyName, this.value);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\impl\PropertyValue$Any.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */