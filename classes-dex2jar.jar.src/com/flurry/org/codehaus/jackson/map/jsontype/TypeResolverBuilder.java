package com.flurry.org.codehaus.jackson.map.jsontype;

import com.flurry.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.Collection;

public interface TypeResolverBuilder {
  TypeDeserializer buildTypeDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, Collection paramCollection, BeanProperty paramBeanProperty);
  
  TypeSerializer buildTypeSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, Collection paramCollection, BeanProperty paramBeanProperty);
  
  TypeResolverBuilder defaultImpl(Class paramClass);
  
  Class getDefaultImpl();
  
  TypeResolverBuilder inclusion(JsonTypeInfo.As paramAs);
  
  TypeResolverBuilder init(JsonTypeInfo.Id paramId, TypeIdResolver paramTypeIdResolver);
  
  TypeResolverBuilder typeProperty(String paramString);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\jsontype\TypeResolverBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */