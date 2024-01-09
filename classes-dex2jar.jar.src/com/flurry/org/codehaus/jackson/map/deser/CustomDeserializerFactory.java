package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializerFactory;
import com.flurry.org.codehaus.jackson.map.DeserializerProvider;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.type.ArrayType;
import com.flurry.org.codehaus.jackson.map.type.ClassKey;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.HashMap;

@Deprecated
public class CustomDeserializerFactory extends BeanDeserializerFactory {
  protected HashMap _directClassMappings = null;
  
  protected HashMap _mixInAnnotations;
  
  public CustomDeserializerFactory() {
    this(null);
  }
  
  protected CustomDeserializerFactory(DeserializerFactory.Config paramConfig) {
    super(paramConfig);
  }
  
  public void addMixInAnnotationMapping(Class paramClass1, Class<?> paramClass2) {
    if (this._mixInAnnotations == null)
      this._mixInAnnotations = new HashMap<Object, Object>(); 
    this._mixInAnnotations.put(new ClassKey(paramClass1), paramClass2);
  }
  
  public void addSpecificMapping(Class paramClass, JsonDeserializer paramJsonDeserializer) {
    ClassKey classKey = new ClassKey(paramClass);
    if (this._directClassMappings == null)
      this._directClassMappings = new HashMap<Object, Object>(); 
    this._directClassMappings.put(classKey, paramJsonDeserializer);
  }
  
  public JsonDeserializer createArrayDeserializer(DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, ArrayType paramArrayType, BeanProperty paramBeanProperty) {
    ClassKey classKey = new ClassKey(paramArrayType.getRawClass());
    if (this._directClassMappings != null) {
      JsonDeserializer jsonDeserializer = (JsonDeserializer)this._directClassMappings.get(classKey);
      if (jsonDeserializer != null)
        return jsonDeserializer; 
    } 
    return super.createArrayDeserializer(paramDeserializationConfig, paramDeserializerProvider, paramArrayType, paramBeanProperty);
  }
  
  public JsonDeserializer createBeanDeserializer(DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, JavaType paramJavaType, BeanProperty paramBeanProperty) {
    ClassKey classKey = new ClassKey(paramJavaType.getRawClass());
    if (this._directClassMappings != null) {
      JsonDeserializer jsonDeserializer = (JsonDeserializer)this._directClassMappings.get(classKey);
      if (jsonDeserializer != null)
        return jsonDeserializer; 
    } 
    return super.createBeanDeserializer(paramDeserializationConfig, paramDeserializerProvider, paramJavaType, paramBeanProperty);
  }
  
  public JsonDeserializer createEnumDeserializer(DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, JavaType paramJavaType, BeanProperty paramBeanProperty) {
    if (this._directClassMappings != null) {
      ClassKey classKey = new ClassKey(paramJavaType.getRawClass());
      JsonDeserializer jsonDeserializer = (JsonDeserializer)this._directClassMappings.get(classKey);
      if (jsonDeserializer != null)
        return jsonDeserializer; 
    } 
    return super.createEnumDeserializer(paramDeserializationConfig, paramDeserializerProvider, paramJavaType, paramBeanProperty);
  }
  
  public DeserializerFactory withConfig(DeserializerFactory.Config paramConfig) {
    if (getClass() != CustomDeserializerFactory.class)
      throw new IllegalStateException("Subtype of CustomDeserializerFactory (" + getClass().getName() + ") has not properly overridden method 'withAdditionalDeserializers': can not instantiate subtype with " + "additional deserializer definitions"); 
    return new CustomDeserializerFactory(paramConfig);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\CustomDeserializerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */