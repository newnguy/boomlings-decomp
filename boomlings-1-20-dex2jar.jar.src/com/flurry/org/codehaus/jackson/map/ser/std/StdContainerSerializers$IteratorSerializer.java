package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.Iterator;

@JacksonStdImpl
public class StdContainerSerializers$IteratorSerializer extends AsArraySerializerBase {
  public StdContainerSerializers$IteratorSerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty) {
    super(Iterator.class, paramJavaType, paramBoolean, paramTypeSerializer, paramBeanProperty, null);
  }
  
  public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer paramTypeSerializer) {
    return new StdContainerSerializers$IteratorSerializer(this._elementType, this._staticTyping, paramTypeSerializer, this._property);
  }
  
  public void serializeContents(Iterator<Object> paramIterator, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    Class<?> clazz = null;
    if (paramIterator.hasNext()) {
      TypeSerializer typeSerializer = this._valueTypeSerializer;
      Class clazz1 = null;
      while (true) {
        Object object = paramIterator.next();
        if (object == null) {
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        } else {
          JsonSerializer jsonSerializer;
          Class<?> clazz2 = object.getClass();
          if (clazz2 == clazz) {
            clazz2 = clazz1;
          } else {
            JsonSerializer jsonSerializer1 = paramSerializerProvider.findValueSerializer(clazz2, this._property);
            clazz = clazz2;
            jsonSerializer = jsonSerializer1;
          } 
          if (typeSerializer == null) {
            jsonSerializer.serialize(object, paramJsonGenerator, paramSerializerProvider);
          } else {
            jsonSerializer.serializeWithType(object, paramJsonGenerator, paramSerializerProvider, typeSerializer);
          } 
        } 
        if (!paramIterator.hasNext())
          return; 
      } 
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\StdContainerSerializers$IteratorSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */