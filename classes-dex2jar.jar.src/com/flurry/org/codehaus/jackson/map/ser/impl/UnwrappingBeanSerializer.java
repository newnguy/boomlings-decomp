package com.flurry.org.codehaus.jackson.map.ser.impl;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.ser.std.BeanSerializerBase;

public class UnwrappingBeanSerializer extends BeanSerializerBase {
  public UnwrappingBeanSerializer(BeanSerializerBase paramBeanSerializerBase) {
    super(paramBeanSerializerBase);
  }
  
  public boolean isUnwrappingSerializer() {
    return true;
  }
  
  public final void serialize(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    if (this._propertyFilterId != null) {
      serializeFieldsFiltered(paramObject, paramJsonGenerator, paramSerializerProvider);
      return;
    } 
    serializeFields(paramObject, paramJsonGenerator, paramSerializerProvider);
  }
  
  public String toString() {
    return "UnwrappingBeanSerializer for " + handledType().getName();
  }
  
  public JsonSerializer unwrappingSerializer() {
    return (JsonSerializer)this;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\impl\UnwrappingBeanSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */