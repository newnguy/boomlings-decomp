package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.type.JavaType;

public interface KeyDeserializers {
  KeyDeserializer findKeyDeserializer(JavaType paramJavaType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, BeanProperty paramBeanProperty);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\KeyDeserializers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */