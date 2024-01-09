package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.flurry.org.codehaus.jackson.map.util.Annotations;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public final class SettableBeanProperty$SetterlessProperty extends SettableBeanProperty {
  protected final AnnotatedMethod _annotated;
  
  protected final Method _getter;
  
  protected SettableBeanProperty$SetterlessProperty(SettableBeanProperty$SetterlessProperty paramSettableBeanProperty$SetterlessProperty, JsonDeserializer paramJsonDeserializer) {
    super(paramSettableBeanProperty$SetterlessProperty, paramJsonDeserializer);
    this._annotated = paramSettableBeanProperty$SetterlessProperty._annotated;
    this._getter = paramSettableBeanProperty$SetterlessProperty._getter;
  }
  
  public SettableBeanProperty$SetterlessProperty(String paramString, JavaType paramJavaType, TypeDeserializer paramTypeDeserializer, Annotations paramAnnotations, AnnotatedMethod paramAnnotatedMethod) {
    super(paramString, paramJavaType, paramTypeDeserializer, paramAnnotations);
    this._annotated = paramAnnotatedMethod;
    this._getter = paramAnnotatedMethod.getAnnotated();
  }
  
  public final void deserializeAndSet(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject) {
    if (paramJsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
      try {
        paramObject = this._getter.invoke(paramObject, new Object[0]);
        if (paramObject == null)
          throw new JsonMappingException("Problem deserializing 'setterless' property '" + getName() + "': get method returned null"); 
      } catch (Exception exception) {
        _throwAsIOE(exception);
        return;
      } 
      this._valueDeserializer.deserialize((JsonParser)exception, paramDeserializationContext, paramObject);
    } 
  }
  
  public Annotation getAnnotation(Class paramClass) {
    return this._annotated.getAnnotation(paramClass);
  }
  
  public AnnotatedMember getMember() {
    return (AnnotatedMember)this._annotated;
  }
  
  public final void set(Object paramObject1, Object paramObject2) {
    throw new UnsupportedOperationException("Should never call 'set' on setterless property");
  }
  
  public SettableBeanProperty$SetterlessProperty withValueDeserializer(JsonDeserializer paramJsonDeserializer) {
    return new SettableBeanProperty$SetterlessProperty(this, paramJsonDeserializer);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\SettableBeanProperty$SetterlessProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */