package com.flurry.org.codehaus.jackson.map.ser.impl;

import com.flurry.org.codehaus.jackson.map.JsonSerializer;

final class PropertySerializerMap$Empty extends PropertySerializerMap {
  protected static final PropertySerializerMap$Empty instance = new PropertySerializerMap$Empty();
  
  public PropertySerializerMap newWith(Class paramClass, JsonSerializer paramJsonSerializer) {
    return new PropertySerializerMap$Single(paramClass, paramJsonSerializer);
  }
  
  public JsonSerializer serializerFor(Class paramClass) {
    return null;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\impl\PropertySerializerMap$Empty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */