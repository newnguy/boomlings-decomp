package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.io.IOException;
import java.lang.reflect.Method;

public final class SettableAnyProperty {
  protected final BeanProperty _property;
  
  protected final Method _setter;
  
  protected final JavaType _type;
  
  protected JsonDeserializer _valueDeserializer;
  
  @Deprecated
  public SettableAnyProperty(BeanProperty paramBeanProperty, AnnotatedMethod paramAnnotatedMethod, JavaType paramJavaType) {
    this(paramBeanProperty, paramAnnotatedMethod, paramJavaType, (JsonDeserializer)null);
  }
  
  public SettableAnyProperty(BeanProperty paramBeanProperty, AnnotatedMethod paramAnnotatedMethod, JavaType paramJavaType, JsonDeserializer paramJsonDeserializer) {
    this(paramBeanProperty, paramAnnotatedMethod.getAnnotated(), paramJavaType, paramJsonDeserializer);
  }
  
  public SettableAnyProperty(BeanProperty paramBeanProperty, Method paramMethod, JavaType paramJavaType, JsonDeserializer paramJsonDeserializer) {
    this._property = paramBeanProperty;
    this._type = paramJavaType;
    this._setter = paramMethod;
    this._valueDeserializer = paramJsonDeserializer;
  }
  
  private String getClassName() {
    return this._setter.getDeclaringClass().getName();
  }
  
  protected void _throwAsIOE(Exception paramException, String paramString, Object paramObject) {
    if (paramException instanceof IllegalArgumentException) {
      if (paramObject == null) {
        paramObject = "[NULL]";
      } else {
        paramObject = paramObject.getClass().getName();
      } 
      StringBuilder stringBuilder = (new StringBuilder("Problem deserializing \"any\" property '")).append(paramString);
      stringBuilder.append("' of class " + getClassName() + " (expected type: ").append(this._type);
      stringBuilder.append("; actual type: ").append((String)paramObject).append(")");
      paramObject = paramException.getMessage();
      if (paramObject != null) {
        stringBuilder.append(", problem: ").append((String)paramObject);
        throw new JsonMappingException(stringBuilder.toString(), null, paramException);
      } 
      stringBuilder.append(" (no error message provided)");
      throw new JsonMappingException(stringBuilder.toString(), null, paramException);
    } 
    if (paramException instanceof IOException)
      throw (IOException)paramException; 
    Throwable throwable = paramException;
    if (paramException instanceof RuntimeException)
      throw (RuntimeException)paramException; 
    while (throwable.getCause() != null)
      throwable = throwable.getCause(); 
    throw new JsonMappingException(throwable.getMessage(), null, throwable);
  }
  
  public final Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    return (paramJsonParser.getCurrentToken() == JsonToken.VALUE_NULL) ? null : this._valueDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
  }
  
  public final void deserializeAndSet(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, String paramString) {
    set(paramObject, paramString, deserialize(paramJsonParser, paramDeserializationContext));
  }
  
  public BeanProperty getProperty() {
    return this._property;
  }
  
  public JavaType getType() {
    return this._type;
  }
  
  public boolean hasValueDeserializer() {
    return (this._valueDeserializer != null);
  }
  
  public final void set(Object paramObject1, String paramString, Object paramObject2) {
    try {
      this._setter.invoke(paramObject1, new Object[] { paramString, paramObject2 });
    } catch (Exception exception) {
      _throwAsIOE(exception, paramString, paramObject2);
    } 
  }
  
  @Deprecated
  public void setValueDeserializer(JsonDeserializer paramJsonDeserializer) {
    if (this._valueDeserializer != null)
      throw new IllegalStateException("Already had assigned deserializer for SettableAnyProperty"); 
    this._valueDeserializer = paramJsonDeserializer;
  }
  
  public String toString() {
    return "[any property on class " + getClassName() + "]";
  }
  
  public SettableAnyProperty withValueDeserializer(JsonDeserializer paramJsonDeserializer) {
    return new SettableAnyProperty(this._property, this._setter, this._type, paramJsonDeserializer);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\SettableAnyProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */