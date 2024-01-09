package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.KeyDeserializer;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.flurry.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import com.flurry.org.codehaus.jackson.map.type.TypeFactory;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import com.flurry.org.codehaus.jackson.map.util.EnumResolver;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;

public class StdKeyDeserializers {
  protected final HashMap _keyDeserializers = new HashMap<Object, Object>();
  
  protected StdKeyDeserializers() {
    add(new StdKeyDeserializer$BoolKD());
    add(new StdKeyDeserializer$ByteKD());
    add(new StdKeyDeserializer$CharKD());
    add(new StdKeyDeserializer$ShortKD());
    add(new StdKeyDeserializer$IntKD());
    add(new StdKeyDeserializer$LongKD());
    add(new StdKeyDeserializer$FloatKD());
    add(new StdKeyDeserializer$DoubleKD());
    add(new StdKeyDeserializer$DateKD());
    add(new StdKeyDeserializer$CalendarKD());
    add(new StdKeyDeserializer$UuidKD());
  }
  
  private void add(StdKeyDeserializer paramStdKeyDeserializer) {
    Class clazz = paramStdKeyDeserializer.getKeyClass();
    this._keyDeserializers.put(TypeFactory.defaultInstance().uncheckedSimpleType(clazz), paramStdKeyDeserializer);
  }
  
  public static HashMap constructAll() {
    return (new StdKeyDeserializers())._keyDeserializers;
  }
  
  public static KeyDeserializer constructEnumKeyDeserializer(EnumResolver paramEnumResolver) {
    return new StdKeyDeserializer$EnumKD(paramEnumResolver, null);
  }
  
  public static KeyDeserializer constructEnumKeyDeserializer(EnumResolver paramEnumResolver, AnnotatedMethod paramAnnotatedMethod) {
    return new StdKeyDeserializer$EnumKD(paramEnumResolver, paramAnnotatedMethod);
  }
  
  public static KeyDeserializer constructStringKeyDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType) {
    return StdKeyDeserializer$StringKD.forType(paramJavaType.getClass());
  }
  
  public static KeyDeserializer findStringBasedKeyDeserializer(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType) {
    BasicBeanDescription basicBeanDescription = (BasicBeanDescription)paramDeserializationConfig.introspect(paramJavaType);
    Constructor constructor = basicBeanDescription.findSingleArgConstructor(new Class[] { String.class });
    if (constructor != null) {
      if (paramDeserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS))
        ClassUtil.checkAndFixAccess(constructor); 
      return new StdKeyDeserializer$StringCtorKeyDeserializer(constructor);
    } 
    Method method = basicBeanDescription.findFactoryMethod(new Class[] { String.class });
    if (method != null) {
      if (paramDeserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS))
        ClassUtil.checkAndFixAccess(method); 
      return new StdKeyDeserializer$StringFactoryKeyDeserializer(method);
    } 
    return null;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdKeyDeserializers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */