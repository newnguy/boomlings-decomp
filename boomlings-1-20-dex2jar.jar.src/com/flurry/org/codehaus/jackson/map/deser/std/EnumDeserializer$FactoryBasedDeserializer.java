package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import java.lang.reflect.Method;

public class EnumDeserializer$FactoryBasedDeserializer extends StdScalarDeserializer {
  protected final Class _enumClass;
  
  protected final Method _factory;
  
  public EnumDeserializer$FactoryBasedDeserializer(Class paramClass, AnnotatedMethod paramAnnotatedMethod) {
    super(Enum.class);
    this._enumClass = paramClass;
    this._factory = paramAnnotatedMethod.getAnnotated();
  }
  
  public Object deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken != JsonToken.VALUE_STRING && jsonToken != JsonToken.FIELD_NAME)
      throw paramDeserializationContext.mappingException(this._enumClass); 
    String str = paramJsonParser.getText();
    try {
      object = this._factory.invoke(this._enumClass, new Object[] { str });
    } catch (Exception object) {
      ClassUtil.unwrapAndThrowAsIAE((Throwable)object);
      object = null;
    } 
    return object;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\EnumDeserializer$FactoryBasedDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */