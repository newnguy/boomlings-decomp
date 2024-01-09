package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.type.JavaType;

public class StdContainerSerializers {
  public static ContainerSerializerBase collectionSerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty, JsonSerializer paramJsonSerializer) {
    return new CollectionSerializer(paramJavaType, paramBoolean, paramTypeSerializer, paramBeanProperty, paramJsonSerializer);
  }
  
  public static JsonSerializer enumSetSerializer(JavaType paramJavaType, BeanProperty paramBeanProperty) {
    return new EnumSetSerializer(paramJavaType, paramBeanProperty);
  }
  
  public static ContainerSerializerBase indexedListSerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty, JsonSerializer paramJsonSerializer) {
    return new StdContainerSerializers$IndexedListSerializer(paramJavaType, paramBoolean, paramTypeSerializer, paramBeanProperty, paramJsonSerializer);
  }
  
  public static ContainerSerializerBase iterableSerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty) {
    return new IterableSerializer(paramJavaType, paramBoolean, paramTypeSerializer, paramBeanProperty);
  }
  
  public static ContainerSerializerBase iteratorSerializer(JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty) {
    return new StdContainerSerializers$IteratorSerializer(paramJavaType, paramBoolean, paramTypeSerializer, paramBeanProperty);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\StdContainerSerializers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */