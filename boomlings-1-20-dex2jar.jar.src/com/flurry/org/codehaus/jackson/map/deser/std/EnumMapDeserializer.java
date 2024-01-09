package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.util.EnumResolver;
import java.util.EnumMap;

public class EnumMapDeserializer extends StdDeserializer {
  protected final Class _enumClass;
  
  protected final JsonDeserializer _keyDeserializer;
  
  protected final JsonDeserializer _valueDeserializer;
  
  @Deprecated
  public EnumMapDeserializer(EnumResolver paramEnumResolver, JsonDeserializer paramJsonDeserializer) {
    this(paramEnumResolver.getEnumClass(), new EnumDeserializer(paramEnumResolver), paramJsonDeserializer);
  }
  
  public EnumMapDeserializer(Class paramClass, JsonDeserializer paramJsonDeserializer1, JsonDeserializer paramJsonDeserializer2) {
    super(EnumMap.class);
    this._enumClass = paramClass;
    this._keyDeserializer = paramJsonDeserializer1;
    this._valueDeserializer = paramJsonDeserializer2;
  }
  
  private EnumMap constructMap() {
    return new EnumMap<Enum, Object>(this._enumClass);
  }
  
  public EnumMap deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (paramJsonParser.getCurrentToken() != JsonToken.START_OBJECT)
      throw paramDeserializationContext.mappingException(EnumMap.class); 
    EnumMap<Enum, Object> enumMap = constructMap();
    while (paramJsonParser.nextToken() != JsonToken.END_OBJECT) {
      Object object;
      Enum enum_ = (Enum)this._keyDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      if (enum_ == null)
        throw paramDeserializationContext.weirdStringException(this._enumClass, "value not one of declared Enum instance names"); 
      if (paramJsonParser.nextToken() == JsonToken.VALUE_NULL) {
        object = null;
      } else {
        object = this._valueDeserializer.deserialize(paramJsonParser, paramDeserializationContext);
      } 
      enumMap.put(enum_, object);
    } 
    return enumMap;
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer) {
    return paramTypeDeserializer.deserializeTypedFromObject(paramJsonParser, paramDeserializationContext);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\EnumMapDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */