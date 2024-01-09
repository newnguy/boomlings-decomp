package com.flurry.org.codehaus.jackson.map.deser.impl;

import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.util.Annotations;
import com.flurry.org.codehaus.jackson.type.JavaType;

public class ValueInjector extends BeanProperty.Std {
  protected final Object _valueId;
  
  public ValueInjector(String paramString, JavaType paramJavaType, Annotations paramAnnotations, AnnotatedMember paramAnnotatedMember, Object paramObject) {
    super(paramString, paramJavaType, paramAnnotations, paramAnnotatedMember);
    this._valueId = paramObject;
  }
  
  public Object findValue(DeserializationContext paramDeserializationContext, Object paramObject) {
    return paramDeserializationContext.findInjectableValue(this._valueId, (BeanProperty)this, paramObject);
  }
  
  public void inject(DeserializationContext paramDeserializationContext, Object paramObject) {
    this._member.setValue(paramObject, findValue(paramDeserializationContext, paramObject));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\impl\ValueInjector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */