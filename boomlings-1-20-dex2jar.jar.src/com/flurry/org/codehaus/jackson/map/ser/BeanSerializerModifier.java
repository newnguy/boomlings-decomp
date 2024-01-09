package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import java.util.List;

public abstract class BeanSerializerModifier {
  public List changeProperties(SerializationConfig paramSerializationConfig, BasicBeanDescription paramBasicBeanDescription, List paramList) {
    return paramList;
  }
  
  public JsonSerializer modifySerializer(SerializationConfig paramSerializationConfig, BasicBeanDescription paramBasicBeanDescription, JsonSerializer paramJsonSerializer) {
    return paramJsonSerializer;
  }
  
  public List orderProperties(SerializationConfig paramSerializationConfig, BasicBeanDescription paramBasicBeanDescription, List paramList) {
    return paramList;
  }
  
  public BeanSerializerBuilder updateBuilder(SerializationConfig paramSerializationConfig, BasicBeanDescription paramBasicBeanDescription, BeanSerializerBuilder paramBeanSerializerBuilder) {
    return paramBeanSerializerBuilder;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\BeanSerializerModifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */