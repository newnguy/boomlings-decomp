package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.DeserializerProvider;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.ResolvableDeserializer;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDeserializer extends StdScalarDeserializer implements ResolvableDeserializer {
  protected final BeanProperty _property;
  
  protected final JavaType _referencedType;
  
  protected JsonDeserializer _valueDeserializer;
  
  public AtomicReferenceDeserializer(JavaType paramJavaType, BeanProperty paramBeanProperty) {
    super(AtomicReference.class);
    this._referencedType = paramJavaType;
    this._property = paramBeanProperty;
  }
  
  public AtomicReference deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    return new AtomicReference(this._valueDeserializer.deserialize(paramJsonParser, paramDeserializationContext));
  }
  
  public void resolve(DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider) {
    this._valueDeserializer = paramDeserializerProvider.findValueDeserializer(paramDeserializationConfig, this._referencedType, this._property);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\AtomicReferenceDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */