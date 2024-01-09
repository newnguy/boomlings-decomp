package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.flurry.org.codehaus.jackson.map.util.Annotations;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public final class SettableBeanProperty$MethodProperty extends SettableBeanProperty {
  protected final AnnotatedMethod _annotated;
  
  protected final Method _setter;
  
  protected SettableBeanProperty$MethodProperty(SettableBeanProperty$MethodProperty paramSettableBeanProperty$MethodProperty, JsonDeserializer paramJsonDeserializer) {
    super(paramSettableBeanProperty$MethodProperty, paramJsonDeserializer);
    this._annotated = paramSettableBeanProperty$MethodProperty._annotated;
    this._setter = paramSettableBeanProperty$MethodProperty._setter;
  }
  
  public SettableBeanProperty$MethodProperty(String paramString, JavaType paramJavaType, TypeDeserializer paramTypeDeserializer, Annotations paramAnnotations, AnnotatedMethod paramAnnotatedMethod) {
    super(paramString, paramJavaType, paramTypeDeserializer, paramAnnotations);
    this._annotated = paramAnnotatedMethod;
    this._setter = paramAnnotatedMethod.getAnnotated();
  }
  
  public void deserializeAndSet(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject) {
    set(paramObject, deserialize(paramJsonParser, paramDeserializationContext));
  }
  
  public Annotation getAnnotation(Class paramClass) {
    return this._annotated.getAnnotation(paramClass);
  }
  
  public AnnotatedMember getMember() {
    return (AnnotatedMember)this._annotated;
  }
  
  public final void set(Object paramObject1, Object paramObject2) {
    try {
      this._setter.invoke(paramObject1, new Object[] { paramObject2 });
    } catch (Exception exception) {
      _throwAsIOE(exception, paramObject2);
    } 
  }
  
  public SettableBeanProperty$MethodProperty withValueDeserializer(JsonDeserializer paramJsonDeserializer) {
    return new SettableBeanProperty$MethodProperty(this, paramJsonDeserializer);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\SettableBeanProperty$MethodProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */