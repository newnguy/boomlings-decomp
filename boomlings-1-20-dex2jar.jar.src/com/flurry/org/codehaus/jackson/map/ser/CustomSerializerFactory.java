package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.SerializerFactory;
import com.flurry.org.codehaus.jackson.map.type.ClassKey;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Modifier;
import java.util.HashMap;

public class CustomSerializerFactory extends BeanSerializerFactory {
  protected HashMap _directClassMappings = null;
  
  protected JsonSerializer _enumSerializerOverride;
  
  protected HashMap _interfaceMappings = null;
  
  protected HashMap _transitiveClassMappings = null;
  
  public CustomSerializerFactory() {
    this(null);
  }
  
  public CustomSerializerFactory(SerializerFactory.Config paramConfig) {
    super(paramConfig);
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
  
  public void addGenericMapping(Class paramClass, JsonSerializer paramJsonSerializer) {
    ClassKey classKey = new ClassKey(paramClass);
    if (paramClass.isInterface()) {
      if (this._interfaceMappings == null)
        this._interfaceMappings = new HashMap<Object, Object>(); 
      this._interfaceMappings.put(classKey, paramJsonSerializer);
      return;
    } 
    if (this._transitiveClassMappings == null)
      this._transitiveClassMappings = new HashMap<Object, Object>(); 
    this._transitiveClassMappings.put(classKey, paramJsonSerializer);
  }
  
  public void addSpecificMapping(Class paramClass, JsonSerializer paramJsonSerializer) {
    ClassKey classKey = new ClassKey(paramClass);
    if (paramClass.isInterface())
      throw new IllegalArgumentException("Can not add specific mapping for an interface (" + paramClass.getName() + ")"); 
    if (Modifier.isAbstract(paramClass.getModifiers()))
      throw new IllegalArgumentException("Can not add specific mapping for an abstract class (" + paramClass.getName() + ")"); 
    if (this._directClassMappings == null)
      this._directClassMappings = new HashMap<Object, Object>(); 
    this._directClassMappings.put(classKey, paramJsonSerializer);
  }
  
  public JsonSerializer createSerializer(SerializationConfig paramSerializationConfig, JavaType paramJavaType, BeanProperty paramBeanProperty) {
    JsonSerializer jsonSerializer = findCustomSerializer(paramJavaType.getRawClass(), paramSerializationConfig);
    return (jsonSerializer != null) ? jsonSerializer : super.createSerializer(paramSerializationConfig, paramJavaType, paramBeanProperty);
  }
  
  protected JsonSerializer findCustomSerializer(Class paramClass, SerializationConfig paramSerializationConfig) {
    // Byte code:
    //   0: new com/flurry/org/codehaus/jackson/map/type/ClassKey
    //   3: dup
    //   4: aload_1
    //   5: invokespecial <init> : (Ljava/lang/Class;)V
    //   8: astore #5
    //   10: aload_0
    //   11: getfield _directClassMappings : Ljava/util/HashMap;
    //   14: ifnull -> 36
    //   17: aload_0
    //   18: getfield _directClassMappings : Ljava/util/HashMap;
    //   21: aload #5
    //   23: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   26: checkcast com/flurry/org/codehaus/jackson/map/JsonSerializer
    //   29: astore_2
    //   30: aload_2
    //   31: ifnull -> 36
    //   34: aload_2
    //   35: areturn
    //   36: aload_1
    //   37: invokevirtual isEnum : ()Z
    //   40: ifeq -> 58
    //   43: aload_0
    //   44: getfield _enumSerializerOverride : Lcom/flurry/org/codehaus/jackson/map/JsonSerializer;
    //   47: ifnull -> 58
    //   50: aload_0
    //   51: getfield _enumSerializerOverride : Lcom/flurry/org/codehaus/jackson/map/JsonSerializer;
    //   54: astore_2
    //   55: goto -> 34
    //   58: aload_0
    //   59: getfield _transitiveClassMappings : Ljava/util/HashMap;
    //   62: ifnull -> 107
    //   65: aload_1
    //   66: astore_3
    //   67: aload_3
    //   68: ifnull -> 107
    //   71: aload #5
    //   73: aload_3
    //   74: invokevirtual reset : (Ljava/lang/Class;)V
    //   77: aload_0
    //   78: getfield _transitiveClassMappings : Ljava/util/HashMap;
    //   81: aload #5
    //   83: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   86: checkcast com/flurry/org/codehaus/jackson/map/JsonSerializer
    //   89: astore #4
    //   91: aload #4
    //   93: astore_2
    //   94: aload #4
    //   96: ifnonnull -> 34
    //   99: aload_3
    //   100: invokevirtual getSuperclass : ()Ljava/lang/Class;
    //   103: astore_3
    //   104: goto -> 67
    //   107: aload_0
    //   108: getfield _interfaceMappings : Ljava/util/HashMap;
    //   111: ifnull -> 165
    //   114: aload #5
    //   116: aload_1
    //   117: invokevirtual reset : (Ljava/lang/Class;)V
    //   120: aload_0
    //   121: getfield _interfaceMappings : Ljava/util/HashMap;
    //   124: aload #5
    //   126: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   129: checkcast com/flurry/org/codehaus/jackson/map/JsonSerializer
    //   132: astore_3
    //   133: aload_3
    //   134: astore_2
    //   135: aload_3
    //   136: ifnonnull -> 34
    //   139: aload_1
    //   140: ifnull -> 165
    //   143: aload_0
    //   144: aload_1
    //   145: aload #5
    //   147: invokevirtual _findInterfaceMapping : (Ljava/lang/Class;Lcom/flurry/org/codehaus/jackson/map/type/ClassKey;)Lcom/flurry/org/codehaus/jackson/map/JsonSerializer;
    //   150: astore_3
    //   151: aload_3
    //   152: astore_2
    //   153: aload_3
    //   154: ifnonnull -> 34
    //   157: aload_1
    //   158: invokevirtual getSuperclass : ()Ljava/lang/Class;
    //   161: astore_1
    //   162: goto -> 139
    //   165: aconst_null
    //   166: astore_2
    //   167: goto -> 34
  }
  
  public void setEnumSerializer(JsonSerializer paramJsonSerializer) {
    this._enumSerializerOverride = paramJsonSerializer;
  }
  
  public SerializerFactory withConfig(SerializerFactory.Config paramConfig) {
    if (getClass() != CustomSerializerFactory.class)
      throw new IllegalStateException("Subtype of CustomSerializerFactory (" + getClass().getName() + ") has not properly overridden method 'withAdditionalSerializers': can not instantiate subtype with " + "additional serializer definitions"); 
    return new CustomSerializerFactory(paramConfig);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\CustomSerializerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */