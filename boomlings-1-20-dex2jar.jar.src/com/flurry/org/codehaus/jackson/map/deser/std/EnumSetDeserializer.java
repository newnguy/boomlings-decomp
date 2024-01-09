package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.util.EnumResolver;
import java.util.EnumSet;

public class EnumSetDeserializer extends StdDeserializer {
  protected final Class _enumClass;
  
  protected final JsonDeserializer _enumDeserializer;
  
  public EnumSetDeserializer(EnumResolver paramEnumResolver) {
    this(paramEnumResolver.getEnumClass(), new EnumDeserializer(paramEnumResolver));
  }
  
  public EnumSetDeserializer(Class paramClass, JsonDeserializer paramJsonDeserializer) {
    super(EnumSet.class);
    this._enumClass = paramClass;
    this._enumDeserializer = paramJsonDeserializer;
  }
  
  private EnumSet constructSet() {
    return EnumSet.noneOf(this._enumClass);
  }
  
  public EnumSet deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    if (!paramJsonParser.isExpectedStartArrayToken())
      throw paramDeserializationContext.mappingException(EnumSet.class); 
    EnumSet<Enum> enumSet = constructSet();
    while (true) {
      JsonToken jsonToken = paramJsonParser.nextToken();
      if (jsonToken != JsonToken.END_ARRAY) {
        if (jsonToken == JsonToken.VALUE_NULL)
          throw paramDeserializationContext.mappingException(this._enumClass); 
        enumSet.add((Enum)this._enumDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
        continue;
      } 
      return enumSet;
    } 
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer) {
    return paramTypeDeserializer.deserializeTypedFromArray(paramJsonParser, paramDeserializationContext);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\EnumSetDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */