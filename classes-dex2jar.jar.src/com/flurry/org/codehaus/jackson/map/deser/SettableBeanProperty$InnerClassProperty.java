package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

public final class SettableBeanProperty$InnerClassProperty extends SettableBeanProperty {
  protected final Constructor _creator;
  
  protected final SettableBeanProperty _delegate;
  
  protected SettableBeanProperty$InnerClassProperty(SettableBeanProperty$InnerClassProperty paramSettableBeanProperty$InnerClassProperty, JsonDeserializer paramJsonDeserializer) {
    super(paramSettableBeanProperty$InnerClassProperty, paramJsonDeserializer);
    this._delegate = paramSettableBeanProperty$InnerClassProperty._delegate.withValueDeserializer(paramJsonDeserializer);
    this._creator = paramSettableBeanProperty$InnerClassProperty._creator;
  }
  
  public SettableBeanProperty$InnerClassProperty(SettableBeanProperty paramSettableBeanProperty, Constructor paramConstructor) {
    super(paramSettableBeanProperty);
    this._delegate = paramSettableBeanProperty;
    this._creator = paramConstructor;
  }
  
  public void deserializeAndSet(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject) {
    Object object;
    JsonParser jsonParser1 = null;
    JsonParser jsonParser2 = null;
    if (paramJsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
      if (this._nullProvider == null) {
        paramJsonParser = jsonParser2;
      } else {
        object = this._nullProvider.nullValue(paramDeserializationContext);
      } 
    } else if (this._valueTypeDeserializer != null) {
      object = this._valueDeserializer.deserializeWithType((JsonParser)object, paramDeserializationContext, this._valueTypeDeserializer);
    } else {
      try {
        jsonParser2 = this._creator.newInstance(new Object[] { paramObject });
        jsonParser1 = jsonParser2;
      } catch (Exception exception) {
        ClassUtil.unwrapAndThrowAsIAE(exception, "Failed to instantiate class " + this._creator.getDeclaringClass().getName() + ", problem: " + exception.getMessage());
      } 
      this._valueDeserializer.deserialize((JsonParser)object, paramDeserializationContext, jsonParser1);
      object = jsonParser1;
    } 
    set(paramObject, object);
  }
  
  public Annotation getAnnotation(Class paramClass) {
    return this._delegate.getAnnotation(paramClass);
  }
  
  public AnnotatedMember getMember() {
    return this._delegate.getMember();
  }
  
  public final void set(Object paramObject1, Object paramObject2) {
    this._delegate.set(paramObject1, paramObject2);
  }
  
  public SettableBeanProperty$InnerClassProperty withValueDeserializer(JsonDeserializer paramJsonDeserializer) {
    return new SettableBeanProperty$InnerClassProperty(this, paramJsonDeserializer);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\SettableBeanProperty$InnerClassProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */