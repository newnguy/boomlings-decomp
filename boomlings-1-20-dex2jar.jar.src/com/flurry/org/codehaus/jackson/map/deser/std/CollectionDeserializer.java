package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.DeserializerProvider;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.ResolvableDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.deser.ValueInstantiator;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedWithParams;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Constructor;
import java.util.Collection;

@JacksonStdImpl
public class CollectionDeserializer extends ContainerDeserializerBase implements ResolvableDeserializer {
  protected final JavaType _collectionType;
  
  protected JsonDeserializer _delegateDeserializer;
  
  protected final JsonDeserializer _valueDeserializer;
  
  protected final ValueInstantiator _valueInstantiator;
  
  protected final TypeDeserializer _valueTypeDeserializer;
  
  protected CollectionDeserializer(CollectionDeserializer paramCollectionDeserializer) {
    super(paramCollectionDeserializer._valueClass);
    this._collectionType = paramCollectionDeserializer._collectionType;
    this._valueDeserializer = paramCollectionDeserializer._valueDeserializer;
    this._valueTypeDeserializer = paramCollectionDeserializer._valueTypeDeserializer;
    this._valueInstantiator = paramCollectionDeserializer._valueInstantiator;
    this._delegateDeserializer = paramCollectionDeserializer._delegateDeserializer;
  }
  
  public CollectionDeserializer(JavaType paramJavaType, JsonDeserializer paramJsonDeserializer, TypeDeserializer paramTypeDeserializer, ValueInstantiator paramValueInstantiator) {
    super(paramJavaType.getRawClass());
    this._collectionType = paramJavaType;
    this._valueDeserializer = paramJsonDeserializer;
    this._valueTypeDeserializer = paramTypeDeserializer;
    this._valueInstantiator = paramValueInstantiator;
  }
  
  @Deprecated
  protected CollectionDeserializer(JavaType paramJavaType, JsonDeserializer paramJsonDeserializer, TypeDeserializer paramTypeDeserializer, Constructor paramConstructor) {
    super(paramJavaType.getRawClass());
    this._collectionType = paramJavaType;
    this._valueDeserializer = paramJsonDeserializer;
    this._valueTypeDeserializer = paramTypeDeserializer;
    StdValueInstantiator stdValueInstantiator = new StdValueInstantiator(null, paramJavaType);
    if (paramConstructor != null)
      stdValueInstantiator.configureFromObjectSettings((AnnotatedWithParams)new AnnotatedConstructor(paramConstructor, null, null), null, null, null, null); 
    this._valueInstantiator = stdValueInstantiator;
  }
  
  private final Collection handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Collection<JsonParser> paramCollection) {
    if (!paramDeserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
      throw paramDeserializationContext.mappingException(this._collectionType.getRawClass()); 
    JsonDeserializer jsonDeserializer = this._valueDeserializer;
    TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
      paramJsonParser = null;
      paramCollection.add(paramJsonParser);
      return paramCollection;
    } 
    if (typeDeserializer == null) {
      object = jsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      paramCollection.add(object);
      return paramCollection;
    } 
    Object object = jsonDeserializer.deserializeWithType((JsonParser)object, paramDeserializationContext, typeDeserializer);
    paramCollection.add(object);
    return paramCollection;
  }
  
  public Collection deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (this._delegateDeserializer != null)
      return (Collection)this._valueInstantiator.createUsingDelegate(this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext)); 
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_STRING) {
      String str = paramJsonParser.getText();
      if (str.length() == 0)
        return (Collection)this._valueInstantiator.createFromString(str); 
    } 
    return deserialize(paramJsonParser, paramDeserializationContext, (Collection)this._valueInstantiator.createUsingDefault());
  }
  
  public Collection deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Collection<Object> paramCollection) {
    if (!paramJsonParser.isExpectedStartArrayToken())
      return handleNonArray(paramJsonParser, paramDeserializationContext, paramCollection); 
    JsonDeserializer jsonDeserializer = this._valueDeserializer;
    TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
    while (true) {
      JsonToken jsonToken = paramJsonParser.nextToken();
      Object object = paramCollection;
      if (jsonToken != JsonToken.END_ARRAY) {
        if (jsonToken == JsonToken.VALUE_NULL) {
          object = null;
        } else if (typeDeserializer == null) {
          object = jsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
        } else {
          object = jsonDeserializer.deserializeWithType(paramJsonParser, paramDeserializationContext, typeDeserializer);
        } 
        paramCollection.add(object);
        continue;
      } 
      return (Collection)object;
    } 
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer) {
    return paramTypeDeserializer.deserializeTypedFromArray(paramJsonParser, paramDeserializationContext);
  }
  
  public JsonDeserializer getContentDeserializer() {
    return this._valueDeserializer;
  }
  
  public JavaType getContentType() {
    return this._collectionType.getContentType();
  }
  
  public void resolve(DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider) {
    if (this._valueInstantiator.canCreateUsingDelegate()) {
      JavaType javaType = this._valueInstantiator.getDelegateType();
      if (javaType == null)
        throw new IllegalArgumentException("Invalid delegate-creator definition for " + this._collectionType + ": value instantiator (" + this._valueInstantiator.getClass().getName() + ") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'"); 
      this._delegateDeserializer = findDeserializer(paramDeserializationConfig, paramDeserializerProvider, javaType, (BeanProperty)new BeanProperty.Std(null, javaType, null, (AnnotatedMember)this._valueInstantiator.getDelegateCreator()));
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\CollectionDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */