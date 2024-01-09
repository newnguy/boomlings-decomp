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
public class IterableSerializer extends AsArraySerializerBase {
  public IterableSerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty) {
    super(Iterable.class, paramJavaType, paramBoolean, paramTypeSerializer, paramBeanProperty, null);
  }
  
  public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer paramTypeSerializer) {
    return new IterableSerializer(this._elementType, this._staticTyping, paramTypeSerializer, this._property);
  }
  
  public void serializeContents(Iterable paramIterable, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    Class clazz = null;
    Iterator<Object> iterator = paramIterable.iterator();
    if (iterator.hasNext()) {
      TypeSerializer typeSerializer = this._valueTypeSerializer;
      paramIterable = null;
      while (true) {
        Object object = iterator.next();
        if (object == null) {
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        } else {
          Iterable iterable;
          JsonSerializer jsonSerializer;
          Class<?> clazz1 = object.getClass();
          if (clazz1 == clazz) {
            iterable = paramIterable;
          } else {
            JsonSerializer jsonSerializer1 = paramSerializerProvider.findValueSerializer((Class)iterable, this._property);
            Iterable iterable1 = iterable;
            jsonSerializer = jsonSerializer1;
          } 
          if (typeSerializer == null) {
            jsonSerializer.serialize(object, paramJsonGenerator, paramSerializerProvider);
          } else {
            jsonSerializer.serializeWithType(object, paramJsonGenerator, paramSerializerProvider, typeSerializer);
          } 
        } 
        if (!iterator.hasNext())
          return; 
      } 
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\IterableSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */