package com.flurry.org.codehaus.jackson.map.module;

import com.flurry.org.codehaus.jackson.map.BeanDescription;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.KeyDeserializer;
import com.flurry.org.codehaus.jackson.map.KeyDeserializers;
import com.flurry.org.codehaus.jackson.map.type.ClassKey;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.HashMap;

public class SimpleKeyDeserializers implements KeyDeserializers {
  protected HashMap _classMappings = null;
  
  public SimpleKeyDeserializers addDeserializer(Class paramClass, KeyDeserializer paramKeyDeserializer) {
    if (this._classMappings == null)
      this._classMappings = new HashMap<Object, Object>(); 
    this._classMappings.put(new ClassKey(paramClass), paramKeyDeserializer);
    return this;
  }
  
  public KeyDeserializer findKeyDeserializer(JavaType paramJavaType, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, BeanProperty paramBeanProperty) {
    return (this._classMappings == null) ? null : (KeyDeserializer)this._classMappings.get(new ClassKey(paramJavaType.getRawClass()));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\module\SimpleKeyDeserializers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */