package com.flurry.org.codehaus.jackson.map.jsontype.impl;

import com.flurry.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeIdResolver;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.HashMap;

public abstract class TypeDeserializerBase extends TypeDeserializer {
  protected final JavaType _baseType;
  
  protected final JavaType _defaultImpl;
  
  protected JsonDeserializer _defaultImplDeserializer;
  
  protected final HashMap _deserializers;
  
  protected final TypeIdResolver _idResolver;
  
  protected final BeanProperty _property;
  
  @Deprecated
  protected TypeDeserializerBase(JavaType paramJavaType, TypeIdResolver paramTypeIdResolver, BeanProperty paramBeanProperty) {
    this(paramJavaType, paramTypeIdResolver, paramBeanProperty, null);
  }
  
  protected TypeDeserializerBase(JavaType paramJavaType, TypeIdResolver paramTypeIdResolver, BeanProperty paramBeanProperty, Class paramClass) {
    this._baseType = paramJavaType;
    this._idResolver = paramTypeIdResolver;
    this._property = paramBeanProperty;
    this._deserializers = new HashMap<Object, Object>();
    if (paramClass == null) {
      this._defaultImpl = null;
      return;
    } 
    this._defaultImpl = paramJavaType.forcedNarrowBy(paramClass);
  }
  
  protected final JsonDeserializer _findDefaultImplDeserializer(DeserializationContext paramDeserializationContext) {
    if (this._defaultImpl == null)
      return null; 
    JavaType javaType = this._defaultImpl;
    /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{com/flurry/org/codehaus/jackson/type/JavaType}, name=null} */
    try {
      if (this._defaultImplDeserializer == null)
        this._defaultImplDeserializer = paramDeserializationContext.getDeserializerProvider().findValueDeserializer(paramDeserializationContext.getConfig(), this._defaultImpl, this._property); 
      JsonDeserializer jsonDeserializer = this._defaultImplDeserializer;
      /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{com/flurry/org/codehaus/jackson/type/JavaType}, name=null} */
    } finally {}
    return (JsonDeserializer)paramDeserializationContext;
  }
  
  protected final JsonDeserializer _findDeserializer(DeserializationContext paramDeserializationContext, String paramString) {
    JsonDeserializer jsonDeserializer;
    synchronized (this._deserializers) {
      JsonDeserializer jsonDeserializer1 = (JsonDeserializer)this._deserializers.get(paramString);
      jsonDeserializer = jsonDeserializer1;
      if (jsonDeserializer1 == null) {
        JsonDeserializer jsonDeserializer2;
        JavaType javaType = this._idResolver.typeFromId(paramString);
        if (javaType == null) {
          if (this._defaultImpl == null)
            throw paramDeserializationContext.unknownTypeException(this._baseType, paramString); 
          jsonDeserializer2 = _findDefaultImplDeserializer(paramDeserializationContext);
        } else {
          JavaType javaType1 = javaType;
          if (this._baseType != null) {
            javaType1 = javaType;
            if (this._baseType.getClass() == javaType.getClass())
              javaType1 = this._baseType.narrowBy(javaType.getRawClass()); 
          } 
          jsonDeserializer2 = jsonDeserializer2.getDeserializerProvider().findValueDeserializer(jsonDeserializer2.getConfig(), javaType1, this._property);
        } 
        this._deserializers.put(paramString, jsonDeserializer2);
        jsonDeserializer = jsonDeserializer2;
      } 
    } 
    /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/lang/Object}, name=SYNTHETIC_LOCAL_VARIABLE_5} */
    return jsonDeserializer;
  }
  
  public String baseTypeName() {
    return this._baseType.getRawClass().getName();
  }
  
  public Class getDefaultImpl() {
    return (this._defaultImpl == null) ? null : this._defaultImpl.getRawClass();
  }
  
  public String getPropertyName() {
    return null;
  }
  
  public TypeIdResolver getTypeIdResolver() {
    return this._idResolver;
  }
  
  public abstract JsonTypeInfo.As getTypeInclusion();
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append('[').append(getClass().getName());
    stringBuilder.append("; base-type:").append(this._baseType);
    stringBuilder.append("; id-resolver: ").append(this._idResolver);
    stringBuilder.append(']');
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\jsontype\impl\TypeDeserializerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */