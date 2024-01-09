package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.map.jsontype.impl.StdTypeResolverBuilder;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.Collection;

public class ObjectMapper$DefaultTypeResolverBuilder extends StdTypeResolverBuilder {
  protected final ObjectMapper$DefaultTyping _appliesFor;
  
  public ObjectMapper$DefaultTypeResolverBuilder(ObjectMapper$DefaultTyping paramObjectMapper$DefaultTyping) {
    this._appliesFor = paramObjectMapper$DefaultTyping;
  }
  
  public TypeDeserializer buildTypeDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType, Collection paramCollection, BeanProperty paramBeanProperty) {
    return useForType(paramJavaType) ? super.buildTypeDeserializer(paramDeserializationConfig, paramJavaType, paramCollection, paramBeanProperty) : null;
  }
  
  public TypeSerializer buildTypeSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, Collection paramCollection, BeanProperty paramBeanProperty) {
    return useForType(paramJavaType) ? super.buildTypeSerializer(paramSerializationConfig, paramJavaType, paramCollection, paramBeanProperty) : null;
  }
  
  public boolean useForType(JavaType paramJavaType) {
    boolean bool = true;
    null = false;
    JavaType javaType = paramJavaType;
    switch (ObjectMapper$2.$SwitchMap$org$codehaus$jackson$map$ObjectMapper$DefaultTyping[this._appliesFor.ordinal()]) {
      default:
        if (paramJavaType.getRawClass() == Object.class)
          return bool; 
        break;
      case 1:
        javaType = paramJavaType;
        if (paramJavaType.isArrayType())
          javaType = paramJavaType.getContentType(); 
      case 2:
        if (javaType.getRawClass() == Object.class || !javaType.isConcrete())
          null = true; 
        return null;
      case 3:
        javaType = paramJavaType;
        if (paramJavaType.isArrayType())
          javaType = paramJavaType.getContentType(); 
        null = bool;
        if (javaType.isFinal())
          null = false; 
        return null;
    } 
    return false;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ObjectMapper$DefaultTypeResolverBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */