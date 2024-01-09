package com.flurry.org.codehaus.jackson.map.deser.impl;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedParameter;
import com.flurry.org.codehaus.jackson.map.util.Annotations;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.annotation.Annotation;

public class CreatorProperty extends SettableBeanProperty {
  protected final AnnotatedParameter _annotated;
  
  protected final Object _injectableValueId;
  
  protected CreatorProperty(CreatorProperty paramCreatorProperty, JsonDeserializer paramJsonDeserializer) {
    super(paramCreatorProperty, paramJsonDeserializer);
    this._annotated = paramCreatorProperty._annotated;
    this._injectableValueId = paramCreatorProperty._injectableValueId;
  }
  
  public CreatorProperty(String paramString, JavaType paramJavaType, TypeDeserializer paramTypeDeserializer, Annotations paramAnnotations, AnnotatedParameter paramAnnotatedParameter, int paramInt, Object paramObject) {
    super(paramString, paramJavaType, paramTypeDeserializer, paramAnnotations);
    this._annotated = paramAnnotatedParameter;
    this._propertyIndex = paramInt;
    this._injectableValueId = paramObject;
  }
  
  public void deserializeAndSet(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject) {
    set(paramObject, deserialize(paramJsonParser, paramDeserializationContext));
  }
  
  public Object findInjectableValue(DeserializationContext paramDeserializationContext, Object paramObject) {
    if (this._injectableValueId == null)
      throw new IllegalStateException("Property '" + getName() + "' (type " + getClass().getName() + ") has no injectable value id configured"); 
    return paramDeserializationContext.findInjectableValue(this._injectableValueId, (BeanProperty)this, paramObject);
  }
  
  public Annotation getAnnotation(Class paramClass) {
    return (this._annotated == null) ? null : this._annotated.getAnnotation(paramClass);
  }
  
  public Object getInjectableValueId() {
    return this._injectableValueId;
  }
  
  public AnnotatedMember getMember() {
    return (AnnotatedMember)this._annotated;
  }
  
  public void inject(DeserializationContext paramDeserializationContext, Object paramObject) {
    set(paramObject, findInjectableValue(paramDeserializationContext, paramObject));
  }
  
  public void set(Object paramObject1, Object paramObject2) {}
  
  public CreatorProperty withValueDeserializer(JsonDeserializer paramJsonDeserializer) {
    return new CreatorProperty(this, paramJsonDeserializer);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\impl\CreatorProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */