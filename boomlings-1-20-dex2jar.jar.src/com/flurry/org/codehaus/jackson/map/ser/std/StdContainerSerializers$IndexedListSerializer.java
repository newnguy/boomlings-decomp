package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.ser.impl.PropertySerializerMap;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.List;

@JacksonStdImpl
public class StdContainerSerializers$IndexedListSerializer extends AsArraySerializerBase {
  public StdContainerSerializers$IndexedListSerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty, JsonSerializer paramJsonSerializer) {
    super(List.class, paramJavaType, paramBoolean, paramTypeSerializer, paramBeanProperty, paramJsonSerializer);
  }
  
  public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer paramTypeSerializer) {
    return new StdContainerSerializers$IndexedListSerializer(this._elementType, this._staticTyping, paramTypeSerializer, this._property, this._elementSerializer);
  }
  
  public void serializeContents(List<Object> paramList, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    if (this._elementSerializer != null) {
      serializeContentsUsing(paramList, paramJsonGenerator, paramSerializerProvider, this._elementSerializer);
      return;
    } 
    if (this._valueTypeSerializer != null) {
      serializeTypedContents(paramList, paramJsonGenerator, paramSerializerProvider);
      return;
    } 
    int i = paramList.size();
    if (i != 0) {
      byte b2 = 0;
      byte b1 = 0;
      try {
        PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
        while (true) {
          if (b1 < i) {
            PropertySerializerMap propertySerializerMap1;
            b2 = b1;
            Object object = paramList.get(b1);
            if (object == null) {
              b2 = b1;
              paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
              propertySerializerMap1 = propertySerializerMap;
            } else {
              b2 = b1;
              Class<?> clazz = object.getClass();
              b2 = b1;
              JsonSerializer jsonSerializer2 = propertySerializerMap.serializerFor(clazz);
              propertySerializerMap1 = propertySerializerMap;
              JsonSerializer jsonSerializer1 = jsonSerializer2;
              if (jsonSerializer2 == null) {
                JsonSerializer jsonSerializer;
                b2 = b1;
                if (this._elementType.hasGenericTypes()) {
                  b2 = b1;
                  jsonSerializer = _findAndAddDynamic(propertySerializerMap, paramSerializerProvider.constructSpecializedType(this._elementType, clazz), paramSerializerProvider);
                } else {
                  b2 = b1;
                  jsonSerializer = _findAndAddDynamic(propertySerializerMap, clazz, paramSerializerProvider);
                } 
                b2 = b1;
                propertySerializerMap = this._dynamicSerializers;
                jsonSerializer1 = jsonSerializer;
                propertySerializerMap1 = propertySerializerMap;
              } 
              b2 = b1;
              jsonSerializer1.serialize(object, paramJsonGenerator, paramSerializerProvider);
            } 
            b1++;
            propertySerializerMap = propertySerializerMap1;
            continue;
          } 
          return;
        } 
      } catch (Exception exception) {
        wrapAndThrow(paramSerializerProvider, exception, paramList, b2);
      } 
    } 
  }
  
  public void serializeContentsUsing(List<Object> paramList, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, JsonSerializer paramJsonSerializer) {
    int i = paramList.size();
    if (i != 0) {
      TypeSerializer typeSerializer = this._valueTypeSerializer;
      byte b = 0;
      while (true) {
        if (b < i) {
          exception = (Exception)paramList.get(b);
          if (exception == null) {
            try {
              paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
            } catch (Exception exception) {
              wrapAndThrow(paramSerializerProvider, exception, paramList, b);
            } 
          } else if (typeSerializer == null) {
            paramJsonSerializer.serialize(exception, paramJsonGenerator, paramSerializerProvider);
          } else {
            paramJsonSerializer.serializeWithType(exception, paramJsonGenerator, paramSerializerProvider, typeSerializer);
          } 
          b++;
          continue;
        } 
        return;
      } 
    } 
  }
  
  public void serializeTypedContents(List<Object> paramList, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    int i = paramList.size();
    if (i != 0) {
      byte b3 = 0;
      byte b1 = 0;
      byte b2 = b3;
      try {
        TypeSerializer typeSerializer = this._valueTypeSerializer;
        b2 = b3;
        PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
        while (true) {
          if (b1 < i) {
            PropertySerializerMap propertySerializerMap1;
            b2 = b1;
            Object object = paramList.get(b1);
            if (object == null) {
              b2 = b1;
              paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
              propertySerializerMap1 = propertySerializerMap;
            } else {
              b2 = b1;
              Class<?> clazz = object.getClass();
              b2 = b1;
              JsonSerializer jsonSerializer2 = propertySerializerMap.serializerFor(clazz);
              propertySerializerMap1 = propertySerializerMap;
              JsonSerializer jsonSerializer1 = jsonSerializer2;
              if (jsonSerializer2 == null) {
                JsonSerializer jsonSerializer;
                b2 = b1;
                if (this._elementType.hasGenericTypes()) {
                  b2 = b1;
                  jsonSerializer = _findAndAddDynamic(propertySerializerMap, paramSerializerProvider.constructSpecializedType(this._elementType, clazz), paramSerializerProvider);
                } else {
                  b2 = b1;
                  jsonSerializer = _findAndAddDynamic(propertySerializerMap, clazz, paramSerializerProvider);
                } 
                b2 = b1;
                propertySerializerMap = this._dynamicSerializers;
                jsonSerializer1 = jsonSerializer;
                propertySerializerMap1 = propertySerializerMap;
              } 
              b2 = b1;
              jsonSerializer1.serializeWithType(object, paramJsonGenerator, paramSerializerProvider, typeSerializer);
            } 
            b1++;
            propertySerializerMap = propertySerializerMap1;
            continue;
          } 
          return;
        } 
      } catch (Exception exception) {
        wrapAndThrow(paramSerializerProvider, exception, paramList, b2);
      } 
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\StdContainerSerializers$IndexedListSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */