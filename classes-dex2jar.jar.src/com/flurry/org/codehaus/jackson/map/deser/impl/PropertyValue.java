package com.flurry.org.codehaus.jackson.map.deser.impl;

public abstract class PropertyValue {
  public final PropertyValue next;
  
  public final Object value;
  
  protected PropertyValue(PropertyValue paramPropertyValue, Object paramObject) {
    this.next = paramPropertyValue;
    this.value = paramObject;
  }
  
  public abstract void assign(Object paramObject);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\impl\PropertyValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */