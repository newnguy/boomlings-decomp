package com.flurry.org.codehaus.jackson.map.module;

import com.flurry.org.codehaus.jackson.map.BeanDescription;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.Serializers;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.type.ArrayType;
import com.flurry.org.codehaus.jackson.map.type.ClassKey;
import com.flurry.org.codehaus.jackson.map.type.CollectionLikeType;
import com.flurry.org.codehaus.jackson.map.type.CollectionType;
import com.flurry.org.codehaus.jackson.map.type.MapLikeType;
import com.flurry.org.codehaus.jackson.map.type.MapType;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.HashMap;

public class SimpleSerializers extends Serializers.Base {
  protected HashMap _classMappings = null;
  
  protected HashMap _interfaceMappings = null;
  
  private void _addSerializer(Class paramClass, JsonSerializer paramJsonSerializer) {
    ClassKey classKey = new ClassKey(paramClass);
    if (paramClass.isInterface()) {
      if (this._interfaceMappings == null)
        this._interfaceMappings = new HashMap<Object, Object>(); 
      this._interfaceMappings.put(classKey, paramJsonSerializer);
      return;
    } 
    if (this._classMappings == null)
      this._classMappings = new HashMap<Object, Object>(); 
    this._classMappings.put(classKey, paramJsonSerializer);
  }
  
  protected JsonSerializer _findInterfaceMapping(Class paramClass, ClassKey paramClassKey) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getInterfaces : ()[Ljava/lang/Class;
    //   4: astore #6
    //   6: aload #6
    //   8: arraylength
    //   9: istore #4
    //   11: iconst_0
    //   12: istore_3
    //   13: iload_3
    //   14: iload #4
    //   16: if_icmpge -> 72
    //   19: aload #6
    //   21: iload_3
    //   22: aaload
    //   23: astore #5
    //   25: aload_2
    //   26: aload #5
    //   28: invokevirtual reset : (Ljava/lang/Class;)V
    //   31: aload_0
    //   32: getfield _interfaceMappings : Ljava/util/HashMap;
    //   35: aload_2
    //   36: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   39: checkcast com/flurry/org/codehaus/jackson/map/JsonSerializer
    //   42: astore_1
    //   43: aload_1
    //   44: ifnull -> 49
    //   47: aload_1
    //   48: areturn
    //   49: aload_0
    //   50: aload #5
    //   52: aload_2
    //   53: invokevirtual _findInterfaceMapping : (Ljava/lang/Class;Lcom/flurry/org/codehaus/jackson/map/type/ClassKey;)Lcom/flurry/org/codehaus/jackson/map/JsonSerializer;
    //   56: astore #5
    //   58: aload #5
    //   60: astore_1
    //   61: aload #5
    //   63: ifnonnull -> 47
    //   66: iinc #3, 1
    //   69: goto -> 13
    //   72: aconst_null
    //   73: astore_1
    //   74: goto -> 47
  }
  
  public void addSerializer(JsonSerializer paramJsonSerializer) {
    Class<Object> clazz = paramJsonSerializer.handledType();
    if (clazz == null || clazz == Object.class)
      throw new IllegalArgumentException("JsonSerializer of type " + paramJsonSerializer.getClass().getName() + " does not define valid handledType() -- must either register with method that takes type argument " + " or make serializer extend 'org.codehaus.jackson.map.ser.std.SerializerBase'"); 
    _addSerializer(clazz, paramJsonSerializer);
  }
  
  public void addSerializer(Class paramClass, JsonSerializer paramJsonSerializer) {
    _addSerializer(paramClass, paramJsonSerializer);
  }
  
  public JsonSerializer findArraySerializer(SerializationConfig paramSerializationConfig, ArrayType paramArrayType, BeanDescription paramBeanDescription, BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer paramJsonSerializer) {
    return findSerializer(paramSerializationConfig, (JavaType)paramArrayType, paramBeanDescription, paramBeanProperty);
  }
  
  public JsonSerializer findCollectionLikeSerializer(SerializationConfig paramSerializationConfig, CollectionLikeType paramCollectionLikeType, BeanDescription paramBeanDescription, BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer paramJsonSerializer) {
    return findSerializer(paramSerializationConfig, (JavaType)paramCollectionLikeType, paramBeanDescription, paramBeanProperty);
  }
  
  public JsonSerializer findCollectionSerializer(SerializationConfig paramSerializationConfig, CollectionType paramCollectionType, BeanDescription paramBeanDescription, BeanProperty paramBeanProperty, TypeSerializer paramTypeSerializer, JsonSerializer paramJsonSerializer) {
    return findSerializer(paramSerializationConfig, (JavaType)paramCollectionType, paramBeanDescription, paramBeanProperty);
  }
  
  public JsonSerializer findMapLikeSerializer(SerializationConfig paramSerializationConfig, MapLikeType paramMapLikeType, BeanDescription paramBeanDescription, BeanProperty paramBeanProperty, JsonSerializer paramJsonSerializer1, TypeSerializer paramTypeSerializer, JsonSerializer paramJsonSerializer2) {
    return findSerializer(paramSerializationConfig, (JavaType)paramMapLikeType, paramBeanDescription, paramBeanProperty);
  }
  
  public JsonSerializer findMapSerializer(SerializationConfig paramSerializationConfig, MapType paramMapType, BeanDescription paramBeanDescription, BeanProperty paramBeanProperty, JsonSerializer paramJsonSerializer1, TypeSerializer paramTypeSerializer, JsonSerializer paramJsonSerializer2) {
    return findSerializer(paramSerializationConfig, (JavaType)paramMapType, paramBeanDescription, paramBeanProperty);
  }
  
  public JsonSerializer findSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, BeanDescription paramBeanDescription, BeanProperty paramBeanProperty) {
    Class clazz = paramJavaType.getRawClass();
    ClassKey classKey = new ClassKey(clazz);
    if (clazz.isInterface()) {
      if (this._interfaceMappings != null) {
        JsonSerializer jsonSerializer = (JsonSerializer)this._interfaceMappings.get(classKey);
        if (jsonSerializer != null)
          return jsonSerializer; 
      } 
    } else if (this._classMappings != null) {
      JsonSerializer jsonSerializer2 = (JsonSerializer)this._classMappings.get(classKey);
      JsonSerializer jsonSerializer1 = jsonSerializer2;
      if (jsonSerializer2 == null) {
        Class clazz1 = clazz;
        while (clazz1 != null) {
          classKey.reset(clazz1);
          JsonSerializer jsonSerializer = (JsonSerializer)this._classMappings.get(classKey);
          jsonSerializer1 = jsonSerializer;
          if (jsonSerializer == null) {
            clazz1 = clazz1.getSuperclass();
            continue;
          } 
          return jsonSerializer1;
        } 
      } else {
        return jsonSerializer1;
      } 
    } 
    if (this._interfaceMappings != null) {
      JsonSerializer jsonSerializer2 = _findInterfaceMapping(clazz, classKey);
      JsonSerializer jsonSerializer1 = jsonSerializer2;
      if (jsonSerializer2 == null) {
        if (!clazz.isInterface()) {
          Class clazz1 = clazz;
          while (true) {
            clazz1 = clazz1.getSuperclass();
            if (clazz1 != null) {
              JsonSerializer jsonSerializer = _findInterfaceMapping(clazz1, classKey);
              if (jsonSerializer != null) {
                jsonSerializer1 = jsonSerializer;
                // Byte code: goto -> 46
              } 
              continue;
            } 
            break;
          } 
        } 
      } else {
        return jsonSerializer1;
      } 
    } 
    return null;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\module\SimpleSerializers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */