package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedField;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.util.Annotations;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public final class SettableBeanProperty$FieldProperty extends SettableBeanProperty {
  protected final AnnotatedField _annotated;
  
  protected final Field _field;
  
  protected SettableBeanProperty$FieldProperty(SettableBeanProperty$FieldProperty paramSettableBeanProperty$FieldProperty, JsonDeserializer paramJsonDeserializer) {
    super(paramSettableBeanProperty$FieldProperty, paramJsonDeserializer);
    this._annotated = paramSettableBeanProperty$FieldProperty._annotated;
    this._field = paramSettableBeanProperty$FieldProperty._field;
  }
  
  public SettableBeanProperty$FieldProperty(String paramString, JavaType paramJavaType, TypeDeserializer paramTypeDeserializer, Annotations paramAnnotations, AnnotatedField paramAnnotatedField) {
    super(paramString, paramJavaType, paramTypeDeserializer, paramAnnotations);
    this._annotated = paramAnnotatedField;
    this._field = paramAnnotatedField.getAnnotated();
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
      this._field.set(paramObject1, paramObject2);
    } catch (Exception exception) {
      _throwAsIOE(exception, paramObject2);
    } 
  }
  
  public SettableBeanProperty$FieldProperty withValueDeserializer(JsonDeserializer paramJsonDeserializer) {
    return new SettableBeanProperty$FieldProperty(this, paramJsonDeserializer);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\SettableBeanProperty$FieldProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */