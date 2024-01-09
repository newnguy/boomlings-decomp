package com.flurry.org.codehaus.jackson.map.util;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.JsonSerializableWithType;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.type.TypeFactory;
import com.flurry.org.codehaus.jackson.type.JavaType;

public class JSONPObject implements JsonSerializableWithType {
  protected final String _function;
  
  protected final JavaType _serializationType;
  
  protected final Object _value;
  
  public JSONPObject(String paramString, Object paramObject) {
    this(paramString, paramObject, (JavaType)null);
  }
  
  public JSONPObject(String paramString, Object paramObject, JavaType paramJavaType) {
    this._function = paramString;
    this._value = paramObject;
    this._serializationType = paramJavaType;
  }
  
  @Deprecated
  public JSONPObject(String paramString, Object paramObject, Class paramClass) {
    JavaType javaType;
    this._function = paramString;
    this._value = paramObject;
    if (paramClass == null) {
      paramString = null;
    } else {
      javaType = TypeFactory.defaultInstance().constructType(paramClass);
    } 
    this._serializationType = javaType;
  }
  
  public String getFunction() {
    return this._function;
  }
  
  public JavaType getSerializationType() {
    return this._serializationType;
  }
  
  public Object getValue() {
    return this._value;
  }
  
  public void serialize(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramJsonGenerator.writeRaw(this._function);
    paramJsonGenerator.writeRaw('(');
    if (this._value == null) {
      paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
    } else if (this._serializationType != null) {
      paramSerializerProvider.findTypedValueSerializer(this._serializationType, true, null).serialize(this._value, paramJsonGenerator, paramSerializerProvider);
    } else {
      paramSerializerProvider.findTypedValueSerializer(this._value.getClass(), true, null).serialize(this._value, paramJsonGenerator, paramSerializerProvider);
    } 
    paramJsonGenerator.writeRaw(')');
  }
  
  public void serializeWithType(JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer) {
    serialize(paramJsonGenerator, paramSerializerProvider);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\ma\\util\JSONPObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */