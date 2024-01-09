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
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedWithParams;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.Collection;

@JacksonStdImpl
public final class StringCollectionDeserializer extends ContainerDeserializerBase implements ResolvableDeserializer {
  protected final JavaType _collectionType;
  
  protected JsonDeserializer _delegateDeserializer;
  
  protected final boolean _isDefaultDeserializer;
  
  protected final JsonDeserializer _valueDeserializer;
  
  protected final ValueInstantiator _valueInstantiator;
  
  protected StringCollectionDeserializer(StringCollectionDeserializer paramStringCollectionDeserializer) {
    super(paramStringCollectionDeserializer._valueClass);
    this._collectionType = paramStringCollectionDeserializer._collectionType;
    this._valueDeserializer = paramStringCollectionDeserializer._valueDeserializer;
    this._valueInstantiator = paramStringCollectionDeserializer._valueInstantiator;
    this._isDefaultDeserializer = paramStringCollectionDeserializer._isDefaultDeserializer;
  }
  
  public StringCollectionDeserializer(JavaType paramJavaType, JsonDeserializer paramJsonDeserializer, ValueInstantiator paramValueInstantiator) {
    super(paramJavaType.getRawClass());
    this._collectionType = paramJavaType;
    this._valueDeserializer = paramJsonDeserializer;
    this._valueInstantiator = paramValueInstantiator;
    this._isDefaultDeserializer = isDefaultSerializer(paramJsonDeserializer);
  }
  
  private Collection deserializeUsingCustom(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Collection<String> paramCollection) {
    JsonDeserializer jsonDeserializer = this._valueDeserializer;
    while (true) {
      JsonToken jsonToken = paramJsonParser.nextToken();
      if (jsonToken != JsonToken.END_ARRAY) {
        String str;
        if (jsonToken == JsonToken.VALUE_NULL) {
          jsonToken = null;
        } else {
          str = (String)jsonDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
        } 
        paramCollection.add(str);
        continue;
      } 
      return paramCollection;
    } 
  }
  
  private final Collection handleNonArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Collection<JsonParser> paramCollection) {
    if (!paramDeserializationContext.isEnabled(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY))
      throw paramDeserializationContext.mappingException(this._collectionType.getRawClass()); 
    JsonDeserializer jsonDeserializer = this._valueDeserializer;
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
      paramJsonParser = null;
      paramCollection.add(paramJsonParser);
      return paramCollection;
    } 
    if (jsonDeserializer == null) {
      str = paramJsonParser.getText();
      paramCollection.add(str);
      return paramCollection;
    } 
    String str = (String)jsonDeserializer.deserialize((JsonParser)str, paramDeserializationContext);
    paramCollection.add(str);
    return paramCollection;
  }
  
  public Collection deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    return (this._delegateDeserializer != null) ? (Collection)this._valueInstantiator.createUsingDelegate(this._delegateDeserializer.deserialize(paramJsonParser, paramDeserializationContext)) : deserialize(paramJsonParser, paramDeserializationContext, (Collection)this._valueInstantiator.createUsingDefault());
  }
  
  public Collection deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Collection<String> paramCollection) {
    if (!paramJsonParser.isExpectedStartArrayToken())
      return handleNonArray(paramJsonParser, paramDeserializationContext, paramCollection); 
    if (!this._isDefaultDeserializer)
      return deserializeUsingCustom(paramJsonParser, paramDeserializationContext, paramCollection); 
    while (true) {
      String str;
      JsonToken jsonToken = paramJsonParser.nextToken();
      Collection<String> collection = paramCollection;
      if (jsonToken != JsonToken.END_ARRAY) {
        if (jsonToken == JsonToken.VALUE_NULL) {
          collection = null;
        } else {
          str = paramJsonParser.getText();
        } 
        paramCollection.add(str);
        continue;
      } 
      return (Collection)str;
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
    AnnotatedWithParams annotatedWithParams = this._valueInstantiator.getDelegateCreator();
    if (annotatedWithParams != null) {
      JavaType javaType = this._valueInstantiator.getDelegateType();
      this._delegateDeserializer = findDeserializer(paramDeserializationConfig, paramDeserializerProvider, javaType, (BeanProperty)new BeanProperty.Std(null, javaType, null, (AnnotatedMember)annotatedWithParams));
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StringCollectionDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */