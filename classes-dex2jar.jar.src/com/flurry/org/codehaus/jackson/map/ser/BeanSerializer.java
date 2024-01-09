package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.ser.impl.UnwrappingBeanSerializer;
import com.flurry.org.codehaus.jackson.map.ser.std.BeanSerializerBase;
import com.flurry.org.codehaus.jackson.type.JavaType;

public class BeanSerializer extends BeanSerializerBase {
  protected BeanSerializer(BeanSerializer paramBeanSerializer) {
    super(paramBeanSerializer);
  }
  
  protected BeanSerializer(BeanSerializerBase paramBeanSerializerBase) {
    super(paramBeanSerializerBase);
  }
  
  public BeanSerializer(JavaType paramJavaType, BeanPropertyWriter[] paramArrayOfBeanPropertyWriter1, BeanPropertyWriter[] paramArrayOfBeanPropertyWriter2, AnyGetterWriter paramAnyGetterWriter, Object paramObject) {
    super(paramJavaType, paramArrayOfBeanPropertyWriter1, paramArrayOfBeanPropertyWriter2, paramAnyGetterWriter, paramObject);
  }
  
  public BeanSerializer(Class paramClass, BeanPropertyWriter[] paramArrayOfBeanPropertyWriter1, BeanPropertyWriter[] paramArrayOfBeanPropertyWriter2, AnyGetterWriter paramAnyGetterWriter, Object paramObject) {
    super(paramClass, paramArrayOfBeanPropertyWriter1, paramArrayOfBeanPropertyWriter2, paramAnyGetterWriter, paramObject);
  }
  
  public static BeanSerializer createDummy(Class paramClass) {
    return new BeanSerializer(paramClass, NO_PROPS, null, null, null);
  }
  
  public final void serialize(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeStartObject();
    if (this._propertyFilterId != null) {
      serializeFieldsFiltered(paramObject, paramJsonGenerator, paramSerializerProvider);
    } else {
      serializeFields(paramObject, paramJsonGenerator, paramSerializerProvider);
    } 
    paramJsonGenerator.writeEndObject();
  }
  
  public String toString() {
    return "BeanSerializer for " + handledType().getName();
  }
  
  public JsonSerializer unwrappingSerializer() {
    return (JsonSerializer)new UnwrappingBeanSerializer(this);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\BeanSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */