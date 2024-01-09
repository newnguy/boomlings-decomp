package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.ResolvableSerializer;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.ser.impl.PropertySerializerMap;
import com.flurry.org.codehaus.jackson.map.type.TypeFactory;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

@JacksonStdImpl
public class MapSerializer extends ContainerSerializerBase implements ResolvableSerializer {
  protected static final JavaType UNSPECIFIED_TYPE = TypeFactory.unknownType();
  
  protected PropertySerializerMap _dynamicValueSerializers;
  
  protected final HashSet _ignoredEntries;
  
  protected JsonSerializer _keySerializer;
  
  protected final JavaType _keyType;
  
  protected final BeanProperty _property;
  
  protected JsonSerializer _valueSerializer;
  
  protected final JavaType _valueType;
  
  protected final boolean _valueTypeIsStatic;
  
  protected final TypeSerializer _valueTypeSerializer;
  
  protected MapSerializer() {
    this((HashSet)null, (JavaType)null, (JavaType)null, false, (TypeSerializer)null, (JsonSerializer)null, (JsonSerializer)null, (BeanProperty)null);
  }
  
  protected MapSerializer(HashSet paramHashSet, JavaType paramJavaType1, JavaType paramJavaType2, boolean paramBoolean, TypeSerializer paramTypeSerializer, JsonSerializer paramJsonSerializer1, JsonSerializer paramJsonSerializer2, BeanProperty paramBeanProperty) {
    super(Map.class, false);
    this._property = paramBeanProperty;
    this._ignoredEntries = paramHashSet;
    this._keyType = paramJavaType1;
    this._valueType = paramJavaType2;
    this._valueTypeIsStatic = paramBoolean;
    this._valueTypeSerializer = paramTypeSerializer;
    this._keySerializer = paramJsonSerializer1;
    this._valueSerializer = paramJsonSerializer2;
    this._dynamicValueSerializers = PropertySerializerMap.emptyMap();
  }
  
  @Deprecated
  public static MapSerializer construct(String[] paramArrayOfString, JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty) {
    return construct(paramArrayOfString, paramJavaType, paramBoolean, paramTypeSerializer, paramBeanProperty, (JsonSerializer)null, (JsonSerializer)null);
  }
  
  public static MapSerializer construct(String[] paramArrayOfString, JavaType paramJavaType, boolean paramBoolean, TypeSerializer paramTypeSerializer, BeanProperty paramBeanProperty, JsonSerializer paramJsonSerializer1, JsonSerializer paramJsonSerializer2) {
    JavaType javaType;
    HashSet hashSet = toSet(paramArrayOfString);
    if (paramJavaType == null) {
      javaType = UNSPECIFIED_TYPE;
      paramJavaType = javaType;
    } else {
      javaType = paramJavaType.getKeyType();
      paramJavaType = paramJavaType.getContentType();
    } 
    if (!paramBoolean) {
      if (paramJavaType != null && paramJavaType.isFinal()) {
        paramBoolean = true;
        return new MapSerializer(hashSet, javaType, paramJavaType, paramBoolean, paramTypeSerializer, paramJsonSerializer1, paramJsonSerializer2, paramBeanProperty);
      } 
    } else {
      return new MapSerializer(hashSet, javaType, paramJavaType, paramBoolean, paramTypeSerializer, paramJsonSerializer1, paramJsonSerializer2, paramBeanProperty);
    } 
    paramBoolean = false;
    return new MapSerializer(hashSet, javaType, paramJavaType, paramBoolean, paramTypeSerializer, paramJsonSerializer1, paramJsonSerializer2, paramBeanProperty);
  }
  
  private static HashSet toSet(String[] paramArrayOfString) {
    if (paramArrayOfString == null || paramArrayOfString.length == 0)
      return null; 
    HashSet<String> hashSet = new HashSet(paramArrayOfString.length);
    int i = paramArrayOfString.length;
    byte b = 0;
    while (true) {
      HashSet<String> hashSet1 = hashSet;
      if (b < i) {
        hashSet.add(paramArrayOfString[b]);
        b++;
        continue;
      } 
      return hashSet1;
    } 
  }
  
  protected final JsonSerializer _findAndAddDynamic(PropertySerializerMap paramPropertySerializerMap, JavaType paramJavaType, SerializerProvider paramSerializerProvider) {
    PropertySerializerMap.SerializerAndMapResult serializerAndMapResult = paramPropertySerializerMap.findAndAddSerializer(paramJavaType, paramSerializerProvider, this._property);
    if (paramPropertySerializerMap != serializerAndMapResult.map)
      this._dynamicValueSerializers = serializerAndMapResult.map; 
    return serializerAndMapResult.serializer;
  }
  
  protected final JsonSerializer _findAndAddDynamic(PropertySerializerMap paramPropertySerializerMap, Class paramClass, SerializerProvider paramSerializerProvider) {
    PropertySerializerMap.SerializerAndMapResult serializerAndMapResult = paramPropertySerializerMap.findAndAddSerializer(paramClass, paramSerializerProvider, this._property);
    if (paramPropertySerializerMap != serializerAndMapResult.map)
      this._dynamicValueSerializers = serializerAndMapResult.map; 
    return serializerAndMapResult.serializer;
  }
  
  public ContainerSerializerBase _withValueTypeSerializer(TypeSerializer paramTypeSerializer) {
    MapSerializer mapSerializer = new MapSerializer(this._ignoredEntries, this._keyType, this._valueType, this._valueTypeIsStatic, paramTypeSerializer, this._keySerializer, this._valueSerializer, this._property);
    if (this._valueSerializer != null)
      mapSerializer._valueSerializer = this._valueSerializer; 
    return mapSerializer;
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    return (JsonNode)createSchemaNode("object", true);
  }
  
  public void resolve(SerializerProvider paramSerializerProvider) {
    if (this._valueTypeIsStatic && this._valueSerializer == null)
      this._valueSerializer = paramSerializerProvider.findValueSerializer(this._valueType, this._property); 
    if (this._keySerializer == null)
      this._keySerializer = paramSerializerProvider.findKeySerializer(this._keyType, this._property); 
  }
  
  public void serialize(Map paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeStartObject();
    if (!paramMap.isEmpty())
      if (this._valueSerializer != null) {
        serializeFieldsUsing(paramMap, paramJsonGenerator, paramSerializerProvider, this._valueSerializer);
      } else {
        serializeFields(paramMap, paramJsonGenerator, paramSerializerProvider);
      }  
    paramJsonGenerator.writeEndObject();
  }
  
  public void serializeFields(Map paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    boolean bool;
    if (this._valueTypeSerializer != null) {
      serializeTypedFields(paramMap, paramJsonGenerator, paramSerializerProvider);
      return;
    } 
    JsonSerializer jsonSerializer = this._keySerializer;
    HashSet hashSet = this._ignoredEntries;
    if (!paramSerializerProvider.isEnabled(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES)) {
      bool = true;
    } else {
      bool = false;
    } 
    PropertySerializerMap propertySerializerMap = this._dynamicValueSerializers;
    Iterator<Map.Entry> iterator = paramMap.entrySet().iterator();
    while (true) {
      if (iterator.hasNext()) {
        Map.Entry entry = iterator.next();
        Object object1 = entry.getValue();
        Object object2 = entry.getKey();
        if (object2 == null) {
          paramSerializerProvider.getNullKeySerializer().serialize(null, paramJsonGenerator, paramSerializerProvider);
        } else if ((!bool || object1 != null) && (hashSet == null || !hashSet.contains(object2))) {
          jsonSerializer.serialize(object2, paramJsonGenerator, paramSerializerProvider);
        } else {
          continue;
        } 
        if (object1 == null) {
          paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
          continue;
        } 
        Class<?> clazz = object1.getClass();
        JsonSerializer jsonSerializer1 = propertySerializerMap.serializerFor(clazz);
        if (jsonSerializer1 == null) {
          JsonSerializer jsonSerializer2;
          if (this._valueType.hasGenericTypes()) {
            jsonSerializer2 = _findAndAddDynamic(propertySerializerMap, paramSerializerProvider.constructSpecializedType(this._valueType, clazz), paramSerializerProvider);
          } else {
            jsonSerializer2 = _findAndAddDynamic((PropertySerializerMap)jsonSerializer2, clazz, paramSerializerProvider);
          } 
          PropertySerializerMap propertySerializerMap2 = this._dynamicValueSerializers;
          jsonSerializer1 = jsonSerializer2;
          PropertySerializerMap propertySerializerMap1 = propertySerializerMap2;
        } 
        try {
          jsonSerializer1.serialize(object1, paramJsonGenerator, paramSerializerProvider);
        } catch (Exception exception) {
          wrapAndThrow(paramSerializerProvider, exception, paramMap, "" + object2);
        } 
        continue;
      } 
      return;
    } 
  }
  
  protected void serializeFieldsUsing(Map paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, JsonSerializer paramJsonSerializer) {
    boolean bool;
    JsonSerializer jsonSerializer = this._keySerializer;
    HashSet hashSet = this._ignoredEntries;
    TypeSerializer typeSerializer = this._valueTypeSerializer;
    if (!paramSerializerProvider.isEnabled(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES)) {
      bool = true;
    } else {
      bool = false;
    } 
    for (Map.Entry entry : paramMap.entrySet()) {
      object = entry.getValue();
      entry = (Map.Entry)entry.getKey();
      if (entry == null) {
        paramSerializerProvider.getNullKeySerializer().serialize(null, paramJsonGenerator, paramSerializerProvider);
      } else if ((!bool || object != null) && (hashSet == null || !hashSet.contains(entry))) {
        jsonSerializer.serialize(entry, paramJsonGenerator, paramSerializerProvider);
      } else {
        continue;
      } 
      if (object == null) {
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        continue;
      } 
      if (typeSerializer == null) {
        try {
          paramJsonSerializer.serialize(object, paramJsonGenerator, paramSerializerProvider);
        } catch (Exception object) {
          wrapAndThrow(paramSerializerProvider, (Throwable)object, paramMap, "" + entry);
        } 
        continue;
      } 
      paramJsonSerializer.serializeWithType(object, paramJsonGenerator, paramSerializerProvider, typeSerializer);
    } 
  }
  
  protected void serializeTypedFields(Map paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    boolean bool;
    JsonSerializer jsonSerializer = this._keySerializer;
    HashSet hashSet = this._ignoredEntries;
    if (!paramSerializerProvider.isEnabled(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES)) {
      bool = true;
    } else {
      bool = false;
    } 
    Iterator<Map.Entry> iterator = paramMap.entrySet().iterator();
    Class<?> clazz2 = null;
    Class<?> clazz1 = null;
    while (iterator.hasNext()) {
      JsonSerializer jsonSerializer1;
      Map.Entry entry = iterator.next();
      Object object2 = entry.getValue();
      Object object1 = entry.getKey();
      if (object1 == null) {
        paramSerializerProvider.getNullKeySerializer().serialize(null, paramJsonGenerator, paramSerializerProvider);
      } else if ((!bool || object2 != null) && (hashSet == null || !hashSet.contains(object1))) {
        jsonSerializer.serialize(object1, paramJsonGenerator, paramSerializerProvider);
      } else {
        continue;
      } 
      if (object2 == null) {
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        continue;
      } 
      Class<?> clazz = object2.getClass();
      if (clazz == clazz2) {
        clazz = clazz1;
        Class<?> clazz3 = clazz1;
        clazz1 = clazz;
      } else {
        jsonSerializer1 = paramSerializerProvider.findValueSerializer(clazz, this._property);
        JsonSerializer jsonSerializer2 = jsonSerializer1;
        clazz2 = clazz;
      } 
      try {
        jsonSerializer1.serializeWithType(object2, paramJsonGenerator, paramSerializerProvider, this._valueTypeSerializer);
      } catch (Exception exception) {
        wrapAndThrow(paramSerializerProvider, exception, paramMap, "" + object1);
      } 
    } 
  }
  
  public void serializeWithType(Map paramMap, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer) {
    paramTypeSerializer.writeTypePrefixForObject(paramMap, paramJsonGenerator);
    if (!paramMap.isEmpty())
      if (this._valueSerializer != null) {
        serializeFieldsUsing(paramMap, paramJsonGenerator, paramSerializerProvider, this._valueSerializer);
      } else {
        serializeFields(paramMap, paramJsonGenerator, paramSerializerProvider);
      }  
    paramTypeSerializer.writeTypeSuffixForObject(paramMap, paramJsonGenerator);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\MapSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */