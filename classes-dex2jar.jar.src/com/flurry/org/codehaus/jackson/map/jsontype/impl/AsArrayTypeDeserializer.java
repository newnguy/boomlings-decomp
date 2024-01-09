package com.flurry.org.codehaus.jackson.map.jsontype.impl;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.jsontype.TypeIdResolver;
import com.flurry.org.codehaus.jackson.type.JavaType;

public class AsArrayTypeDeserializer extends TypeDeserializerBase {
  @Deprecated
  public AsArrayTypeDeserializer(JavaType paramJavaType, TypeIdResolver paramTypeIdResolver, BeanProperty paramBeanProperty) {
    this(paramJavaType, paramTypeIdResolver, paramBeanProperty, (Class)null);
  }
  
  public AsArrayTypeDeserializer(JavaType paramJavaType, TypeIdResolver paramTypeIdResolver, BeanProperty paramBeanProperty, Class paramClass) {
    super(paramJavaType, paramTypeIdResolver, paramBeanProperty, paramClass);
  }
  
  private final Object _deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    boolean bool = paramJsonParser.isExpectedStartArrayToken();
    Object object = _findDeserializer(paramDeserializationContext, _locateTypeId(paramJsonParser, paramDeserializationContext)).deserialize(paramJsonParser, paramDeserializationContext);
    if (bool && paramJsonParser.nextToken() != JsonToken.END_ARRAY)
      throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.END_ARRAY, "expected closing END_ARRAY after type information and deserialized value"); 
    return object;
  }
  
  protected final String _locateTypeId(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (!paramJsonParser.isExpectedStartArrayToken()) {
      if (this._idResolver instanceof TypeIdResolverBase && this._defaultImpl != null)
        return ((TypeIdResolverBase)this._idResolver).idFromBaseType(); 
      throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.START_ARRAY, "need JSON Array to contain As.WRAPPER_ARRAY type information for class " + baseTypeName());
    } 
    if (paramJsonParser.nextToken() != JsonToken.VALUE_STRING) {
      if (this._idResolver instanceof TypeIdResolverBase && this._defaultImpl != null)
        return ((TypeIdResolverBase)this._idResolver).idFromBaseType(); 
      throw paramDeserializationContext.wrongTokenException(paramJsonParser, JsonToken.VALUE_STRING, "need JSON String that contains type id (for subtype of " + baseTypeName() + ")");
    } 
    String str = paramJsonParser.getText();
    paramJsonParser.nextToken();
    return str;
  }
  
  public Object deserializeTypedFromAny(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    return _deserialize(paramJsonParser, paramDeserializationContext);
  }
  
  public Object deserializeTypedFromArray(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    return _deserialize(paramJsonParser, paramDeserializationContext);
  }
  
  public Object deserializeTypedFromObject(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    return _deserialize(paramJsonParser, paramDeserializationContext);
  }
  
  public Object deserializeTypedFromScalar(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    return _deserialize(paramJsonParser, paramDeserializationContext);
  }
  
  public JsonTypeInfo.As getTypeInclusion() {
    return JsonTypeInfo.As.WRAPPER_ARRAY;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\jsontype\impl\AsArrayTypeDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */