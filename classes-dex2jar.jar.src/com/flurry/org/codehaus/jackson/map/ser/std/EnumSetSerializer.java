package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.EnumSet;

public class EnumSetSerializer extends AsArraySerializerBase {
  public EnumSetSerializer(JavaType paramJavaType, BeanProperty paramBeanProperty) {
    super(EnumSet.class, paramJavaType, true, null, paramBeanProperty, null);
  }
  
  public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer paramTypeSerializer) {
    return this;
  }
  
  public void serializeContents(EnumSet paramEnumSet, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    JsonSerializer jsonSerializer = this._elementSerializer;
    for (Enum enum_ : paramEnumSet) {
      JsonSerializer jsonSerializer1 = jsonSerializer;
      if (jsonSerializer == null)
        jsonSerializer1 = paramSerializerProvider.findValueSerializer(enum_.getDeclaringClass(), this._property); 
      jsonSerializer1.serialize(enum_, paramJsonGenerator, paramSerializerProvider);
      jsonSerializer = jsonSerializer1;
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\EnumSetSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */