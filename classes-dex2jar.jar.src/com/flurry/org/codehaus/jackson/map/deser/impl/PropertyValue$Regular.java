package com.flurry.org.codehaus.jackson.map.deser.impl;

import com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty;

final class PropertyValue$Regular extends PropertyValue {
  final SettableBeanProperty _property;
  
  public PropertyValue$Regular(PropertyValue paramPropertyValue, Object paramObject, SettableBeanProperty paramSettableBeanProperty) {
    super(paramPropertyValue, paramObject);
    this._property = paramSettableBeanProperty;
  }
  
  public void assign(Object paramObject) {
    this._property.set(paramObject, this.value);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\impl\PropertyValue$Regular.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */