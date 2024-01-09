package com.flurry.org.codehaus.jackson.map.util;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.JsonSerializableWithType;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.type.TypeFactory;
import com.flurry.org.codehaus.jackson.type.JavaType;

public class JSONWrappedObject implements JsonSerializableWithType {
  protected final String _prefix;
  
  protected final JavaType _serializationType;
  
  protected final String _suffix;
  
  protected final Object _value;
  
  public JSONWrappedObject(String paramString1, String paramString2, Object paramObject) {
    this(paramString1, paramString2, paramObject, (JavaType)null);
  }
  
  public JSONWrappedObject(String paramString1, String paramString2, Object paramObject, JavaType paramJavaType) {
    this._prefix = paramString1;
    this._suffix = paramString2;
    this._value = paramObject;
    this._serializationType = paramJavaType;
  }
  
  @Deprecated
  public JSONWrappedObject(String paramString1, String paramString2, Object paramObject, Class paramClass) {
    JavaType javaType;
    this._prefix = paramString1;
    this._suffix = paramString2;
    this._value = paramObject;
    if (paramClass == null) {
      paramString1 = null;
    } else {
      javaType = TypeFactory.defaultInstance().constructType(paramClass);
    } 
    this._serializationType = javaType;
  }
  
  public String getPrefix() {
    return this._prefix;
  }
  
  public JavaType getSerializationType() {
    return this._serializationType;
  }
  
  public String getSuffix() {
    return this._suffix;
  }
  
  public Object getValue() {
    return this._value;
  }
  
  public void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    if (this._prefix != null)
      paramJsonGenerator.writeRaw(this._prefix); 
    if (this._value == null) {
      paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
    } else if (this._serializationType != null) {
      paramSerializerProvider.findTypedValueSerializer(this._serializationType, true, null).serialize(this._value, paramJsonGenerator, paramSerializerProvider);
    } else {
      paramSerializerProvider.findTypedValueSerializer(this._value.getClass(), true, null).serialize(this._value, paramJsonGenerator, paramSerializerProvider);
    } 
    if (this._suffix != null)
      paramJsonGenerator.writeRaw(this._suffix); 
  }
  
  public void serializeWithType(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer) {
    serialize(paramJsonGenerator, paramSerializerProvider);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\ma\\util\JSONWrappedObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */