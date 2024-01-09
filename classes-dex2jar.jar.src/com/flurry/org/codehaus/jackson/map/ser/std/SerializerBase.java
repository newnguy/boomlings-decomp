package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.node.JsonNodeFactory;
import com.flurry.org.codehaus.jackson.node.ObjectNode;
import com.flurry.org.codehaus.jackson.schema.SchemaAware;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.io.IOException;
import java.lang.reflect.Type;

public abstract class SerializerBase extends JsonSerializer implements SchemaAware {
  protected final Class _handledType;
  
  protected SerializerBase(JavaType paramJavaType) {
    this._handledType = paramJavaType.getRawClass();
  }
  
  protected SerializerBase(Class paramClass) {
    this._handledType = paramClass;
  }
  
  protected SerializerBase(Class paramClass, boolean paramBoolean) {
    this._handledType = paramClass;
  }
  
  protected ObjectNode createObjectNode() {
    return JsonNodeFactory.instance.objectNode();
  }
  
  protected ObjectNode createSchemaNode(String paramString) {
    ObjectNode objectNode = createObjectNode();
    objectNode.put("type", paramString);
    return objectNode;
  }
  
  protected ObjectNode createSchemaNode(String paramString, boolean paramBoolean) {
    ObjectNode objectNode = createSchemaNode(paramString);
    if (!paramBoolean) {
      if (!paramBoolean) {
        paramBoolean = true;
      } else {
        paramBoolean = false;
      } 
      objectNode.put("required", paramBoolean);
    } 
    return objectNode;
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    return (JsonNode)createSchemaNode("string");
  }
  
  public final Class handledType() {
    return this._handledType;
  }
  
  protected boolean isDefaultSerializer(JsonSerializer paramJsonSerializer) {
    return (paramJsonSerializer != null && paramJsonSerializer.getClass().getAnnotation(JacksonStdImpl.class) != null);
  }
  
  public abstract void serialize(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider);
  
  public void wrapAndThrow(SerializerProvider paramSerializerProvider, Throwable paramThrowable, Object paramObject, int paramInt) {
    boolean bool;
    while (paramThrowable instanceof java.lang.reflect.InvocationTargetException && paramThrowable.getCause() != null)
      paramThrowable = paramThrowable.getCause(); 
    if (paramThrowable instanceof Error)
      throw (Error)paramThrowable; 
    if (paramSerializerProvider == null || paramSerializerProvider.isEnabled(SerializationConfig.Feature.WRAP_EXCEPTIONS)) {
      bool = true;
    } else {
      bool = false;
    } 
    if (paramThrowable instanceof IOException) {
      if (!bool || !(paramThrowable instanceof JsonMappingException))
        throw (IOException)paramThrowable; 
    } else if (!bool && paramThrowable instanceof RuntimeException) {
      throw (RuntimeException)paramThrowable;
    } 
    throw JsonMappingException.wrapWithPath(paramThrowable, paramObject, paramInt);
  }
  
  public void wrapAndThrow(SerializerProvider paramSerializerProvider, Throwable paramThrowable, Object paramObject, String paramString) {
    boolean bool;
    while (paramThrowable instanceof java.lang.reflect.InvocationTargetException && paramThrowable.getCause() != null)
      paramThrowable = paramThrowable.getCause(); 
    if (paramThrowable instanceof Error)
      throw (Error)paramThrowable; 
    if (paramSerializerProvider == null || paramSerializerProvider.isEnabled(SerializationConfig.Feature.WRAP_EXCEPTIONS)) {
      bool = true;
    } else {
      bool = false;
    } 
    if (paramThrowable instanceof IOException) {
      if (!bool || !(paramThrowable instanceof JsonMappingException))
        throw (IOException)paramThrowable; 
    } else if (!bool && paramThrowable instanceof RuntimeException) {
      throw (RuntimeException)paramThrowable;
    } 
    throw JsonMappingException.wrapWithPath(paramThrowable, paramObject, paramString);
  }
  
  @Deprecated
  public void wrapAndThrow(Throwable paramThrowable, Object paramObject, int paramInt) {
    wrapAndThrow((SerializerProvider)null, paramThrowable, paramObject, paramInt);
  }
  
  @Deprecated
  public void wrapAndThrow(Throwable paramThrowable, Object paramObject, String paramString) {
    wrapAndThrow((SerializerProvider)null, paramThrowable, paramObject, paramString);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\SerializerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */