package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.io.SerializedString;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.ser.impl.PropertySerializerMap;
import com.flurry.org.codehaus.jackson.map.ser.impl.UnwrappingBeanPropertyWriter;
import com.flurry.org.codehaus.jackson.map.util.Annotations;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;

public class BeanPropertyWriter implements BeanProperty {
  protected final Method _accessorMethod;
  
  protected final JavaType _cfgSerializationType;
  
  protected final Annotations _contextAnnotations;
  
  protected final JavaType _declaredType;
  
  protected PropertySerializerMap _dynamicSerializers;
  
  protected final Field _field;
  
  protected Class[] _includeInViews;
  
  protected HashMap _internalSettings;
  
  protected final AnnotatedMember _member;
  
  protected final SerializedString _name;
  
  protected JavaType _nonTrivialBaseType;
  
  protected final JsonSerializer _serializer;
  
  protected final boolean _suppressNulls;
  
  protected final Object _suppressableValue;
  
  protected TypeSerializer _typeSerializer;
  
  public BeanPropertyWriter(AnnotatedMember paramAnnotatedMember, Annotations paramAnnotations, SerializedString paramSerializedString, JavaType paramJavaType1, JsonSerializer paramJsonSerializer, TypeSerializer paramTypeSerializer, JavaType paramJavaType2, Method paramMethod, Field paramField, boolean paramBoolean, Object paramObject) {
    this._member = paramAnnotatedMember;
    this._contextAnnotations = paramAnnotations;
    this._name = paramSerializedString;
    this._declaredType = paramJavaType1;
    this._serializer = paramJsonSerializer;
    if (paramJsonSerializer == null) {
      PropertySerializerMap propertySerializerMap = PropertySerializerMap.emptyMap();
    } else {
      paramAnnotatedMember = null;
    } 
    this._dynamicSerializers = (PropertySerializerMap)paramAnnotatedMember;
    this._typeSerializer = paramTypeSerializer;
    this._cfgSerializationType = paramJavaType2;
    this._accessorMethod = paramMethod;
    this._field = paramField;
    this._suppressNulls = paramBoolean;
    this._suppressableValue = paramObject;
  }
  
  public BeanPropertyWriter(AnnotatedMember paramAnnotatedMember, Annotations paramAnnotations, String paramString, JavaType paramJavaType1, JsonSerializer paramJsonSerializer, TypeSerializer paramTypeSerializer, JavaType paramJavaType2, Method paramMethod, Field paramField, boolean paramBoolean, Object paramObject) {
    this(paramAnnotatedMember, paramAnnotations, new SerializedString(paramString), paramJavaType1, paramJsonSerializer, paramTypeSerializer, paramJavaType2, paramMethod, paramField, paramBoolean, paramObject);
  }
  
  protected BeanPropertyWriter(BeanPropertyWriter paramBeanPropertyWriter) {
    this(paramBeanPropertyWriter, paramBeanPropertyWriter._serializer);
  }
  
  protected BeanPropertyWriter(BeanPropertyWriter paramBeanPropertyWriter, JsonSerializer paramJsonSerializer) {
    this._serializer = paramJsonSerializer;
    this._member = paramBeanPropertyWriter._member;
    this._contextAnnotations = paramBeanPropertyWriter._contextAnnotations;
    this._declaredType = paramBeanPropertyWriter._declaredType;
    this._accessorMethod = paramBeanPropertyWriter._accessorMethod;
    this._field = paramBeanPropertyWriter._field;
    if (paramBeanPropertyWriter._internalSettings != null)
      this._internalSettings = new HashMap<Object, Object>(paramBeanPropertyWriter._internalSettings); 
    this._name = paramBeanPropertyWriter._name;
    this._cfgSerializationType = paramBeanPropertyWriter._cfgSerializationType;
    this._dynamicSerializers = paramBeanPropertyWriter._dynamicSerializers;
    this._suppressNulls = paramBeanPropertyWriter._suppressNulls;
    this._suppressableValue = paramBeanPropertyWriter._suppressableValue;
    this._includeInViews = paramBeanPropertyWriter._includeInViews;
    this._typeSerializer = paramBeanPropertyWriter._typeSerializer;
    this._nonTrivialBaseType = paramBeanPropertyWriter._nonTrivialBaseType;
  }
  
  protected JsonSerializer _findAndAddDynamic(PropertySerializerMap paramPropertySerializerMap, Class paramClass, SerializerProvider paramSerializerProvider) {
    PropertySerializerMap.SerializerAndMapResult serializerAndMapResult;
    if (this._nonTrivialBaseType != null) {
      serializerAndMapResult = paramPropertySerializerMap.findAndAddSerializer(paramSerializerProvider.constructSpecializedType(this._nonTrivialBaseType, paramClass), paramSerializerProvider, this);
    } else {
      serializerAndMapResult = paramPropertySerializerMap.findAndAddSerializer((Class)serializerAndMapResult, paramSerializerProvider, this);
    } 
    if (paramPropertySerializerMap != serializerAndMapResult.map)
      this._dynamicSerializers = serializerAndMapResult.map; 
    return serializerAndMapResult.serializer;
  }
  
  protected void _reportSelfReference(Object paramObject) {
    throw new JsonMappingException("Direct self-reference leading to cycle");
  }
  
  public final Object get(Object paramObject) {
    return (this._accessorMethod != null) ? this._accessorMethod.invoke(paramObject, new Object[0]) : this._field.get(paramObject);
  }
  
  public Annotation getAnnotation(Class paramClass) {
    return this._member.getAnnotation(paramClass);
  }
  
  public Annotation getContextAnnotation(Class paramClass) {
    return this._contextAnnotations.get(paramClass);
  }
  
  public Type getGenericPropertyType() {
    return (this._accessorMethod != null) ? this._accessorMethod.getGenericReturnType() : this._field.getGenericType();
  }
  
  public Object getInternalSetting(Object paramObject) {
    return (this._internalSettings == null) ? null : this._internalSettings.get(paramObject);
  }
  
  public AnnotatedMember getMember() {
    return this._member;
  }
  
  public String getName() {
    return this._name.getValue();
  }
  
  public Class getPropertyType() {
    return (this._accessorMethod != null) ? this._accessorMethod.getReturnType() : this._field.getType();
  }
  
  public Class getRawSerializationType() {
    return (this._cfgSerializationType == null) ? null : this._cfgSerializationType.getRawClass();
  }
  
  public JavaType getSerializationType() {
    return this._cfgSerializationType;
  }
  
  public SerializedString getSerializedName() {
    return this._name;
  }
  
  public JsonSerializer getSerializer() {
    return this._serializer;
  }
  
  public JavaType getType() {
    return this._declaredType;
  }
  
  public Class[] getViews() {
    return this._includeInViews;
  }
  
  public boolean hasSerializer() {
    return (this._serializer != null);
  }
  
  public Object removeInternalSetting(Object paramObject) {
    if (this._internalSettings != null) {
      Object object = this._internalSettings.remove(paramObject);
      paramObject = object;
      if (this._internalSettings.size() == 0) {
        this._internalSettings = null;
        paramObject = object;
      } 
      return paramObject;
    } 
    return null;
  }
  
  public void serializeAsField(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    Object object = get(paramObject);
    if (object == null) {
      if (!this._suppressNulls) {
        paramJsonGenerator.writeFieldName(this._name);
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
      } 
      return;
    } 
    if (object == paramObject)
      _reportSelfReference(paramObject); 
    if (this._suppressableValue == null || !this._suppressableValue.equals(object)) {
      JsonSerializer jsonSerializer = this._serializer;
      paramObject = jsonSerializer;
      if (jsonSerializer == null) {
        Class<?> clazz = object.getClass();
        PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
        jsonSerializer = propertySerializerMap.serializerFor(clazz);
        paramObject = jsonSerializer;
        if (jsonSerializer == null)
          paramObject = _findAndAddDynamic(propertySerializerMap, clazz, paramSerializerProvider); 
      } 
      paramJsonGenerator.writeFieldName(this._name);
      if (this._typeSerializer == null) {
        paramObject.serialize(object, paramJsonGenerator, paramSerializerProvider);
        return;
      } 
      paramObject.serializeWithType(object, paramJsonGenerator, paramSerializerProvider, this._typeSerializer);
    } 
  }
  
  public Object setInternalSetting(Object paramObject1, Object paramObject2) {
    if (this._internalSettings == null)
      this._internalSettings = new HashMap<Object, Object>(); 
    return this._internalSettings.put(paramObject1, paramObject2);
  }
  
  public void setNonTrivialBaseType(JavaType paramJavaType) {
    this._nonTrivialBaseType = paramJavaType;
  }
  
  public void setViews(Class[] paramArrayOfClass) {
    this._includeInViews = paramArrayOfClass;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(40);
    stringBuilder.append("property '").append(getName()).append("' (");
    if (this._accessorMethod != null) {
      stringBuilder.append("via method ").append(this._accessorMethod.getDeclaringClass().getName()).append("#").append(this._accessorMethod.getName());
    } else {
      stringBuilder.append("field \"").append(this._field.getDeclaringClass().getName()).append("#").append(this._field.getName());
    } 
    if (this._serializer == null) {
      stringBuilder.append(", no static serializer");
      stringBuilder.append(')');
      return stringBuilder.toString();
    } 
    stringBuilder.append(", static serializer of type " + this._serializer.getClass().getName());
    stringBuilder.append(')');
    return stringBuilder.toString();
  }
  
  public BeanPropertyWriter unwrappingWriter() {
    return (BeanPropertyWriter)new UnwrappingBeanPropertyWriter(this);
  }
  
  public BeanPropertyWriter withSerializer(JsonSerializer paramJsonSerializer) {
    if (getClass() != BeanPropertyWriter.class)
      throw new IllegalStateException("BeanPropertyWriter sub-class does not override 'withSerializer()'; needs to!"); 
    return new BeanPropertyWriter(this, paramJsonSerializer);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\BeanPropertyWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */