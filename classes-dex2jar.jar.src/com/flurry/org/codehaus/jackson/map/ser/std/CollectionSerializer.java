package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.ser.impl.PropertySerializerMap;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.Collection;
import java.util.Iterator;

public class CollectionSerializer extends AsArraySerializerBase {
  public CollectionSerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty, JsonSerializer paramJsonSerializer) {
    super(Collection.class, paramJavaType, paramBoolean, paramTypeSerializer, paramBeanProperty, paramJsonSerializer);
  }
  
  public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer paramTypeSerializer) {
    return new CollectionSerializer(this._elementType, this._staticTyping, paramTypeSerializer, this._property, this._elementSerializer);
  }
  
  public void serializeContents(Collection paramCollection, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    if (this._elementSerializer != null) {
      serializeContentsUsing(paramCollection, paramJsonGenerator, paramSerializerProvider, this._elementSerializer);
      return;
    } 
    Iterator<Object> iterator = paramCollection.iterator();
    if (iterator.hasNext()) {
      PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
      TypeSerializer typeSerializer = this._valueTypeSerializer;
      int i = 0;
      while (true) {
        int j = i;
        try {
          PropertySerializerMap propertySerializerMap1;
          Object object = iterator.next();
          if (object == null) {
            j = i;
            paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
            propertySerializerMap1 = propertySerializerMap;
          } else {
            j = i;
            Class<?> clazz = object.getClass();
            j = i;
            JsonSerializer jsonSerializer2 = propertySerializerMap.serializerFor(clazz);
            propertySerializerMap1 = propertySerializerMap;
            JsonSerializer jsonSerializer1 = jsonSerializer2;
            if (jsonSerializer2 == null) {
              JsonSerializer jsonSerializer;
              j = i;
              if (this._elementType.hasGenericTypes()) {
                j = i;
                jsonSerializer = _findAndAddDynamic(propertySerializerMap, paramSerializerProvider.constructSpecializedType(this._elementType, clazz), paramSerializerProvider);
              } else {
                j = i;
                jsonSerializer = _findAndAddDynamic(propertySerializerMap, clazz, paramSerializerProvider);
              } 
              j = i;
              propertySerializerMap = this._dynamicSerializers;
              jsonSerializer1 = jsonSerializer;
              propertySerializerMap1 = propertySerializerMap;
            } 
            if (typeSerializer == null) {
              j = i;
              jsonSerializer1.serialize(object, paramJsonGenerator, paramSerializerProvider);
            } else {
              j = i;
              jsonSerializer1.serializeWithType(object, paramJsonGenerator, paramSerializerProvider, typeSerializer);
            } 
          } 
          j = i + 1;
          propertySerializerMap = propertySerializerMap1;
          i = j;
          if (!iterator.hasNext())
            return; 
          continue;
        } catch (Exception exception) {
          wrapAndThrow(paramSerializerProvider, exception, paramCollection, j);
        } 
        return;
      } 
    } 
  }
  
  public void serializeContentsUsing(Collection paramCollection, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, JsonSerializer paramJsonSerializer) {
    Iterator<Object> iterator = paramCollection.iterator();
    if (iterator.hasNext()) {
      TypeSerializer typeSerializer = this._valueTypeSerializer;
      for (byte b = 0;; b++) {
        exception = (Exception)iterator.next();
        if (exception == null) {
          try {
            paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
            b++;
          } catch (Exception exception) {
            wrapAndThrow(paramSerializerProvider, exception, paramCollection, b);
          } 
          if (!iterator.hasNext())
            return; 
          continue;
        } 
        if (typeSerializer == null) {
          paramJsonSerializer.serialize(exception, paramJsonGenerator, paramSerializerProvider);
        } else {
          paramJsonSerializer.serializeWithType(exception, paramJsonGenerator, paramSerializerProvider, typeSerializer);
        } 
      } 
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\CollectionSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */