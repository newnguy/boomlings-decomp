package com.flurry.org.codehaus.jackson.map.module;

import com.flurry.org.codehaus.jackson.map.BeanDescription;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializerProvider;
import com.flurry.org.codehaus.jackson.map.Deserializers;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.KeyDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.type.ArrayType;
import com.flurry.org.codehaus.jackson.map.type.ClassKey;
import com.flurry.org.codehaus.jackson.map.type.CollectionLikeType;
import com.flurry.org.codehaus.jackson.map.type.CollectionType;
import com.flurry.org.codehaus.jackson.map.type.MapLikeType;
import com.flurry.org.codehaus.jackson.map.type.MapType;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.HashMap;

public class SimpleDeserializers implements Deserializers {
  protected HashMap _classMappings = null;
  
  public void addDeserializer(Class paramClass, JsonDeserializer paramJsonDeserializer) {
    ClassKey classKey = new ClassKey(paramClass);
    if (this._classMappings == null)
      this._classMappings = new HashMap<Object, Object>(); 
    this._classMappings.put(classKey, paramJsonDeserializer);
  }
  
  public JsonDeserializer findArrayDeserializer(ArrayType paramArrayType, DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, BeanProperty paramBeanProperty, TypeDeserializer paramTypeDeserializer, JsonDeserializer paramJsonDeserializer) {
    return (this._classMappings == null) ? null : (JsonDeserializer)this._classMappings.get(new ClassKey(paramArrayType.getRawClass()));
  }
  
  public JsonDeserializer findBeanDeserializer(JavaType paramJavaType, DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, BeanDescription paramBeanDescription, BeanProperty paramBeanProperty) {
    return (this._classMappings == null) ? null : (JsonDeserializer)this._classMappings.get(new ClassKey(paramJavaType.getRawClass()));
  }
  
  public JsonDeserializer findCollectionDeserializer(CollectionType paramCollectionType, DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, BeanDescription paramBeanDescription, BeanProperty paramBeanProperty, TypeDeserializer paramTypeDeserializer, JsonDeserializer paramJsonDeserializer) {
    return (this._classMappings == null) ? null : (JsonDeserializer)this._classMappings.get(new ClassKey(paramCollectionType.getRawClass()));
  }
  
  public JsonDeserializer findCollectionLikeDeserializer(CollectionLikeType paramCollectionLikeType, DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, BeanDescription paramBeanDescription, BeanProperty paramBeanProperty, TypeDeserializer paramTypeDeserializer, JsonDeserializer paramJsonDeserializer) {
    return (this._classMappings == null) ? null : (JsonDeserializer)this._classMappings.get(new ClassKey(paramCollectionLikeType.getRawClass()));
  }
  
  public JsonDeserializer findEnumDeserializer(Class paramClass, DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, BeanProperty paramBeanProperty) {
    return (this._classMappings == null) ? null : (JsonDeserializer)this._classMappings.get(new ClassKey(paramClass));
  }
  
  public JsonDeserializer findMapDeserializer(MapType paramMapType, DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, BeanDescription paramBeanDescription, BeanProperty paramBeanProperty, KeyDeserializer paramKeyDeserializer, TypeDeserializer paramTypeDeserializer, JsonDeserializer paramJsonDeserializer) {
    return (this._classMappings == null) ? null : (JsonDeserializer)this._classMappings.get(new ClassKey(paramMapType.getRawClass()));
  }
  
  public JsonDeserializer findMapLikeDeserializer(MapLikeType paramMapLikeType, DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, BeanDescription paramBeanDescription, BeanProperty paramBeanProperty, KeyDeserializer paramKeyDeserializer, TypeDeserializer paramTypeDeserializer, JsonDeserializer paramJsonDeserializer) {
    return (this._classMappings == null) ? null : (JsonDeserializer)this._classMappings.get(new ClassKey(paramMapLikeType.getRawClass()));
  }
  
  public JsonDeserializer findTreeNodeDeserializer(Class paramClass, DeserializationConfig paramDeserializationConfig, BeanProperty paramBeanProperty) {
    return (this._classMappings == null) ? null : (JsonDeserializer)this._classMappings.get(new ClassKey(paramClass));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\module\SimpleDeserializers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */