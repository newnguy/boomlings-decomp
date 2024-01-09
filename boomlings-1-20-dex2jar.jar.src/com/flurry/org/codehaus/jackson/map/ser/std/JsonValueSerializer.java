package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.JsonNode;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.ResolvableSerializer;
import com.flurry.org.codehaus.jackson.map.SerializationConfig;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.schema.JsonSchema;
import com.flurry.org.codehaus.jackson.schema.SchemaAware;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

@JacksonStdImpl
public class JsonValueSerializer extends SerializerBase implements ResolvableSerializer, SchemaAware {
  protected final Method _accessorMethod;
  
  protected boolean _forceTypeInformation;
  
  protected final BeanProperty _property;
  
  protected JsonSerializer _valueSerializer;
  
  public JsonValueSerializer(Method paramMethod, JsonSerializer paramJsonSerializer, BeanProperty paramBeanProperty) {
    super(Object.class);
    this._accessorMethod = paramMethod;
    this._valueSerializer = paramJsonSerializer;
    this._property = paramBeanProperty;
  }
  
  public JsonNode getSchema(SerializerProvider paramSerializerProvider, Type paramType) {
    return (this._valueSerializer instanceof SchemaAware) ? ((SchemaAware)this._valueSerializer).getSchema(paramSerializerProvider, null) : JsonSchema.getDefaultSchemaNode();
  }
  
  protected boolean isNaturalTypeWithStdHandling(JavaType paramJavaType, JsonSerializer paramJsonSerializer) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #4
    //   3: aload_1
    //   4: invokevirtual getRawClass : ()Ljava/lang/Class;
    //   7: astore #5
    //   9: aload_1
    //   10: invokevirtual isPrimitive : ()Z
    //   13: ifeq -> 45
    //   16: aload #5
    //   18: getstatic java/lang/Integer.TYPE : Ljava/lang/Class;
    //   21: if_acmpeq -> 76
    //   24: aload #5
    //   26: getstatic java/lang/Boolean.TYPE : Ljava/lang/Class;
    //   29: if_acmpeq -> 76
    //   32: aload #5
    //   34: getstatic java/lang/Double.TYPE : Ljava/lang/Class;
    //   37: if_acmpeq -> 76
    //   40: iload #4
    //   42: istore_3
    //   43: iload_3
    //   44: ireturn
    //   45: aload #5
    //   47: ldc java/lang/String
    //   49: if_acmpeq -> 76
    //   52: aload #5
    //   54: ldc java/lang/Integer
    //   56: if_acmpeq -> 76
    //   59: aload #5
    //   61: ldc java/lang/Boolean
    //   63: if_acmpeq -> 76
    //   66: iload #4
    //   68: istore_3
    //   69: aload #5
    //   71: ldc java/lang/Double
    //   73: if_acmpne -> 43
    //   76: iload #4
    //   78: istore_3
    //   79: aload_2
    //   80: invokevirtual getClass : ()Ljava/lang/Class;
    //   83: ldc com/flurry/org/codehaus/jackson/map/annotate/JacksonStdImpl
    //   85: invokevirtual getAnnotation : (Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
    //   88: ifnull -> 43
    //   91: iconst_1
    //   92: istore_3
    //   93: goto -> 43
  }
  
  public void resolve(SerializerProvider paramSerializerProvider) {
    if (this._valueSerializer == null && (paramSerializerProvider.isEnabled(SerializationConfig.Feature.USE_STATIC_TYPING) || Modifier.isFinal(this._accessorMethod.getReturnType().getModifiers()))) {
      JavaType javaType = paramSerializerProvider.constructType(this._accessorMethod.getGenericReturnType());
      this._valueSerializer = paramSerializerProvider.findTypedValueSerializer(javaType, false, this._property);
      this._forceTypeInformation = isNaturalTypeWithStdHandling(javaType, this._valueSerializer);
    } 
  }
  
  public void serialize(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    try {
      Object object = this._accessorMethod.invoke(paramObject, new Object[0]);
      if (object == null) {
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        return;
      } 
      JsonSerializer jsonSerializer2 = this._valueSerializer;
      JsonSerializer jsonSerializer1 = jsonSerializer2;
      if (jsonSerializer2 == null)
        jsonSerializer1 = paramSerializerProvider.findTypedValueSerializer(object.getClass(), true, this._property); 
      jsonSerializer1.serialize(object, paramJsonGenerator, paramSerializerProvider);
      return;
    } catch (IOException iOException) {
      throw iOException;
    } catch (Exception null) {
      Throwable throwable;
      while (throwable instanceof java.lang.reflect.InvocationTargetException && throwable.getCause() != null)
        throwable = throwable.getCause(); 
      if (throwable instanceof Error)
        throw (Error)throwable; 
      throw JsonMappingException.wrapWithPath(throwable, iOException, this._accessorMethod.getName() + "()");
    } 
  }
  
  public void serializeWithType(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer) {
    try {
      Object object = this._accessorMethod.invoke(paramObject, new Object[0]);
      if (object == null) {
        paramSerializerProvider.defaultSerializeNull(paramJsonGenerator);
        return;
      } 
      JsonSerializer jsonSerializer = this._valueSerializer;
      if (jsonSerializer != null) {
        if (this._forceTypeInformation)
          paramTypeSerializer.writeTypePrefixForScalar(paramObject, paramJsonGenerator); 
        jsonSerializer.serializeWithType(object, paramJsonGenerator, paramSerializerProvider, paramTypeSerializer);
        if (this._forceTypeInformation)
          paramTypeSerializer.writeTypeSuffixForScalar(paramObject, paramJsonGenerator); 
        return;
      } 
      paramSerializerProvider.findTypedValueSerializer(object.getClass(), true, this._property).serialize(object, paramJsonGenerator, paramSerializerProvider);
      return;
    } catch (IOException iOException) {
      throw iOException;
    } catch (Exception null) {
      Throwable throwable;
      while (throwable instanceof java.lang.reflect.InvocationTargetException && throwable.getCause() != null)
        throwable = throwable.getCause(); 
      if (throwable instanceof Error)
        throw (Error)throwable; 
      throw JsonMappingException.wrapWithPath(throwable, iOException, this._accessorMethod.getName() + "()");
    } 
  }
  
  public String toString() {
    return "(@JsonValue serializer for method " + this._accessorMethod.getDeclaringClass() + "#" + this._accessorMethod.getName() + ")";
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\JsonValueSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */