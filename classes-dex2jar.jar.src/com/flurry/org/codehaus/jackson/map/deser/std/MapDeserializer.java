package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.DeserializerProvider;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.KeyDeserializer;
import com.flurry.org.codehaus.jackson.map.ResolvableDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty;
import com.flurry.org.codehaus.jackson.map.deser.ValueInstantiator;
import com.flurry.org.codehaus.jackson.map.deser.impl.PropertyBasedCreator;
import com.flurry.org.codehaus.jackson.map.deser.impl.PropertyValueBuffer;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedWithParams;
import com.flurry.org.codehaus.jackson.map.util.ArrayBuilders;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Map;

@JacksonStdImpl
public class MapDeserializer extends ContainerDeserializerBase implements ResolvableDeserializer {
  protected JsonDeserializer _delegateDeserializer;
  
  protected final boolean _hasDefaultCreator;
  
  protected HashSet _ignorableProperties;
  
  protected final KeyDeserializer _keyDeserializer;
  
  protected final JavaType _mapType;
  
  protected PropertyBasedCreator _propertyBasedCreator;
  
  protected final JsonDeserializer _valueDeserializer;
  
  protected final ValueInstantiator _valueInstantiator;
  
  protected final TypeDeserializer _valueTypeDeserializer;
  
  protected MapDeserializer(MapDeserializer paramMapDeserializer) {
    super(paramMapDeserializer._valueClass);
    this._mapType = paramMapDeserializer._mapType;
    this._keyDeserializer = paramMapDeserializer._keyDeserializer;
    this._valueDeserializer = paramMapDeserializer._valueDeserializer;
    this._valueTypeDeserializer = paramMapDeserializer._valueTypeDeserializer;
    this._valueInstantiator = paramMapDeserializer._valueInstantiator;
    this._propertyBasedCreator = paramMapDeserializer._propertyBasedCreator;
    this._delegateDeserializer = paramMapDeserializer._delegateDeserializer;
    this._hasDefaultCreator = paramMapDeserializer._hasDefaultCreator;
    this._ignorableProperties = paramMapDeserializer._ignorableProperties;
  }
  
  public MapDeserializer(JavaType paramJavaType, ValueInstantiator paramValueInstantiator, KeyDeserializer paramKeyDeserializer, JsonDeserializer paramJsonDeserializer, TypeDeserializer paramTypeDeserializer) {
    super(Map.class);
    this._mapType = paramJavaType;
    this._keyDeserializer = paramKeyDeserializer;
    this._valueDeserializer = paramJsonDeserializer;
    this._valueTypeDeserializer = paramTypeDeserializer;
    this._valueInstantiator = paramValueInstantiator;
    if (paramValueInstantiator.canCreateFromObjectWith()) {
      this._propertyBasedCreator = new PropertyBasedCreator(paramValueInstantiator);
    } else {
      this._propertyBasedCreator = null;
    } 
    this._hasDefaultCreator = paramValueInstantiator.canCreateUsingDefault();
  }
  
  @Deprecated
  protected MapDeserializer(JavaType paramJavaType, Constructor paramConstructor, KeyDeserializer paramKeyDeserializer, JsonDeserializer paramJsonDeserializer, TypeDeserializer paramTypeDeserializer) {
    super(Map.class);
    boolean bool;
    this._mapType = paramJavaType;
    this._keyDeserializer = paramKeyDeserializer;
    this._valueDeserializer = paramJsonDeserializer;
    this._valueTypeDeserializer = paramTypeDeserializer;
    StdValueInstantiator stdValueInstantiator = new StdValueInstantiator(null, paramJavaType);
    if (paramConstructor != null)
      stdValueInstantiator.configureFromObjectSettings((AnnotatedWithParams)new AnnotatedConstructor(paramConstructor, null, null), null, null, null, null); 
    if (paramConstructor != null) {
      bool = true;
    } else {
      bool = false;
    } 
    this._hasDefaultCreator = bool;
    this._valueInstantiator = stdValueInstantiator;
  }
  
  public Map _deserializeUsingCreator(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    PropertyBasedCreator propertyBasedCreator = this._propertyBasedCreator;
    PropertyValueBuffer propertyValueBuffer = propertyBasedCreator.startBuilding(paramJsonParser, paramDeserializationContext);
    JsonToken jsonToken2 = paramJsonParser.getCurrentToken();
    JsonToken jsonToken1 = jsonToken2;
    if (jsonToken2 == JsonToken.START_OBJECT)
      jsonToken1 = paramJsonParser.nextToken(); 
    JsonDeserializer jsonDeserializer = this._valueDeserializer;
    TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
    while (jsonToken1 == JsonToken.FIELD_NAME) {
      String str = paramJsonParser.getCurrentName();
      jsonToken1 = paramJsonParser.nextToken();
      if (this._ignorableProperties != null && this._ignorableProperties.contains(str)) {
        paramJsonParser.skipChildren();
      } else {
        Object object1;
        SettableBeanProperty settableBeanProperty = propertyBasedCreator.findCreatorProperty(str);
        if (settableBeanProperty != null) {
          object1 = settableBeanProperty.deserialize(paramJsonParser, paramDeserializationContext);
          if (propertyValueBuffer.assignParameter(settableBeanProperty.getPropertyIndex(), object1)) {
            paramJsonParser.nextToken();
            try {
              object1 = propertyBasedCreator.build(propertyValueBuffer);
              _readAndBind(paramJsonParser, paramDeserializationContext, (Map)object1);
              object = object1;
            } catch (Exception null) {
              wrapAndThrow((Throwable)object, this._mapType.getRawClass());
              object = null;
            } 
            return (Map)object;
          } 
        } else {
          String str1 = object.getCurrentName();
          Object object2 = this._keyDeserializer.deserializeKey(str1, paramDeserializationContext);
          if (object1 == JsonToken.VALUE_NULL) {
            object1 = null;
          } else if (typeDeserializer == null) {
            object1 = jsonDeserializer.deserialize((JsonParser)object, paramDeserializationContext);
          } else {
            object1 = jsonDeserializer.deserializeWithType((JsonParser)object, paramDeserializationContext, typeDeserializer);
          } 
          propertyValueBuffer.bufferMapProperty(object2, object1);
        } 
      } 
      jsonToken1 = object.nextToken();
    } 
    try {
      object = propertyBasedCreator.build(propertyValueBuffer);
    } catch (Exception object) {
      wrapAndThrow((Throwable)object, this._mapType.getRawClass());
      object = null;
    } 
    return (Map)object;
  }
  
  protected final void _readAndBind(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Map<Object, Object> paramMap) {
    JsonToken jsonToken2 = paramJsonParser.getCurrentToken();
    JsonToken jsonToken1 = jsonToken2;
    if (jsonToken2 == JsonToken.START_OBJECT)
      jsonToken1 = paramJsonParser.nextToken(); 
    KeyDeserializer keyDeserializer = this._keyDeserializer;
    JsonDeserializer jsonDeserializer = this._valueDeserializer;
    TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
    while (jsonToken1 == JsonToken.FIELD_NAME) {
      String str = paramJsonParser.getCurrentName();
      Object object = keyDeserializer.deserializeKey(str, paramDeserializationContext);
      jsonToken1 = paramJsonParser.nextToken();
      if (this._ignorableProperties != null && this._ignorableProperties.contains(str)) {
        paramJsonParser.skipChildren();
      } else {
        Object object1;
        if (jsonToken1 == JsonToken.VALUE_NULL) {
          jsonToken1 = null;
        } else if (typeDeserializer == null) {
          object1 = jsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
        } else {
          object1 = jsonDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, typeDeserializer);
        } 
        paramMap.put(object, object1);
      } 
      jsonToken1 = paramJsonParser.nextToken();
    } 
  }
  
  public Map deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (this._propertyBasedCreator != null)
      return _deserializeUsingCreator(paramJsonParser, paramDeserializationContext); 
    if (this._delegateDeserializer != null)
      return (Map)this._valueInstantiator.createUsingDelegate(this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext)); 
    if (!this._hasDefaultCreator)
      throw paramDeserializationContext.instantiationException(getMapClass(), "No default constructor found"); 
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken != JsonToken.START_OBJECT && jsonToken != JsonToken.FIELD_NAME && jsonToken != JsonToken.END_OBJECT) {
      if (jsonToken == JsonToken.VALUE_STRING)
        return (Map)this._valueInstantiator.createFromString(paramJsonParser.getText()); 
      throw paramDeserializationContext.mappingException(getMapClass());
    } 
    Map map = (Map)this._valueInstantiator.createUsingDefault();
    _readAndBind(paramJsonParser, paramDeserializationContext, map);
    return map;
  }
  
  public Map deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Map paramMap) {
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken != JsonToken.START_OBJECT && jsonToken != JsonToken.FIELD_NAME)
      throw paramDeserializationContext.mappingException(getMapClass()); 
    _readAndBind(paramJsonParser, paramDeserializationContext, paramMap);
    return paramMap;
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer) {
    return paramTypeDeserializer.deserializeTypedFromObject(paramJsonParser, paramDeserializationContext);
  }
  
  public JsonDeserializer getContentDeserializer() {
    return this._valueDeserializer;
  }
  
  public JavaType getContentType() {
    return this._mapType.getContentType();
  }
  
  public final Class getMapClass() {
    return this._mapType.getRawClass();
  }
  
  public JavaType getValueType() {
    return this._mapType;
  }
  
  public void resolve(DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider) {
    if (this._valueInstantiator.canCreateUsingDelegate()) {
      JavaType javaType = this._valueInstantiator.getDelegateType();
      if (javaType == null)
        throw new IllegalArgumentException("Invalid delegate-creator definition for " + this._mapType + ": value instantiator (" + this._valueInstantiator.getClass().getName() + ") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'"); 
      this._delegateDeserializer = findDeserializer(paramDeserializationConfig, paramDeserializerProvider, javaType, (BeanProperty)new BeanProperty.Std(null, javaType, null, (AnnotatedMember)this._valueInstantiator.getDelegateCreator()));
    } 
    if (this._propertyBasedCreator != null)
      for (SettableBeanProperty settableBeanProperty : this._propertyBasedCreator.getCreatorProperties()) {
        if (!settableBeanProperty.hasValueDeserializer())
          this._propertyBasedCreator.assignDeserializer(settableBeanProperty, findDeserializer(paramDeserializationConfig, paramDeserializerProvider, settableBeanProperty.getType(), (BeanProperty)settableBeanProperty)); 
      }  
  }
  
  public void setIgnorableProperties(String[] paramArrayOfString) {
    HashSet hashSet;
    if (paramArrayOfString == null || paramArrayOfString.length == 0) {
      paramArrayOfString = null;
    } else {
      hashSet = ArrayBuilders.arrayToSet((Object[])paramArrayOfString);
    } 
    this._ignorableProperties = hashSet;
  }
  
  protected void wrapAndThrow(Throwable paramThrowable, Object paramObject) {
    while (paramThrowable instanceof java.lang.reflect.InvocationTargetException && paramThrowable.getCause() != null)
      paramThrowable = paramThrowable.getCause(); 
    if (paramThrowable instanceof Error)
      throw (Error)paramThrowable; 
    if (paramThrowable instanceof IOException && !(paramThrowable instanceof JsonMappingException))
      throw (IOException)paramThrowable; 
    throw JsonMappingException.wrapWithPath(paramThrowable, paramObject, null);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\MapDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */