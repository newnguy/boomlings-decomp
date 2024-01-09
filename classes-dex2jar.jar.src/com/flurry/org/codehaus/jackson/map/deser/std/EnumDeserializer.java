package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.annotate.JsonCachable;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import com.flurry.org.codehaus.jackson.map.util.EnumResolver;

@JsonCachable
public class EnumDeserializer extends StdScalarDeserializer {
  protected final EnumResolver _resolver;
  
  public EnumDeserializer(EnumResolver paramEnumResolver) {
    super(Enum.class);
    this._resolver = paramEnumResolver;
  }
  
  public static JsonDeserializer deserializerForCreator(DeserializationConfig paramDeserializationConfig, Class paramClass, AnnotatedMethod paramAnnotatedMethod) {
    if (paramAnnotatedMethod.getParameterType(0) != String.class)
      throw new IllegalArgumentException("Parameter #0 type for factory method (" + paramAnnotatedMethod + ") not suitable, must be java.lang.String"); 
    if (paramDeserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS))
      ClassUtil.checkAndFixAccess(paramAnnotatedMethod.getMember()); 
    return new EnumDeserializer$FactoryBasedDeserializer(paramClass, paramAnnotatedMethod);
  }
  
  public Enum deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    Enum enum_1;
    Enum enum_2;
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken == JsonToken.VALUE_STRING || jsonToken == JsonToken.FIELD_NAME) {
      String str = paramJsonParser.getText();
      enum_2 = this._resolver.findEnum(str);
      enum_1 = enum_2;
      if (enum_2 == null)
        throw paramDeserializationContext.weirdStringException(this._resolver.getEnumClass(), "value not one of declared Enum instance names"); 
    } else if (enum_2 == JsonToken.VALUE_NUMBER_INT) {
      if (paramDeserializationContext.isEnabled(DeserializationConfig.Feature.FAIL_ON_NUMBERS_FOR_ENUMS))
        throw paramDeserializationContext.mappingException("Not allowed to deserialize Enum value out of JSON number (disable DeserializationConfig.Feature.FAIL_ON_NUMBERS_FOR_ENUMS to allow)"); 
      int i = enum_1.getIntValue();
      enum_2 = this._resolver.getEnum(i);
      enum_1 = enum_2;
      if (enum_2 == null)
        throw paramDeserializationContext.weirdNumberException(this._resolver.getEnumClass(), "index value outside legal index range [0.." + this._resolver.lastValidIndex() + "]"); 
    } else {
      throw paramDeserializationContext.mappingException(this._resolver.getEnumClass());
    } 
    return enum_1;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\EnumDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */