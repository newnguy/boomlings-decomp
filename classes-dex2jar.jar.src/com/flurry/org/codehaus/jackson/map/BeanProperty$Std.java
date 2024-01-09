package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.util.Annotations;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.annotation.Annotation;

public class BeanProperty$Std implements BeanProperty {
  protected final Annotations _contextAnnotations;
  
  protected final AnnotatedMember _member;
  
  protected final String _name;
  
  protected final JavaType _type;
  
  public BeanProperty$Std(String paramString, JavaType paramJavaType, Annotations paramAnnotations, AnnotatedMember paramAnnotatedMember) {
    this._name = paramString;
    this._type = paramJavaType;
    this._member = paramAnnotatedMember;
    this._contextAnnotations = paramAnnotations;
  }
  
  public Annotation getAnnotation(Class paramClass) {
    return this._member.getAnnotation(paramClass);
  }
  
  public Annotation getContextAnnotation(Class paramClass) {
    return (this._contextAnnotations == null) ? null : this._contextAnnotations.get(paramClass);
  }
  
  public AnnotatedMember getMember() {
    return this._member;
  }
  
  public String getName() {
    return this._name;
  }
  
  public JavaType getType() {
    return this._type;
  }
  
  public BeanProperty$Std withType(JavaType paramJavaType) {
    return new BeanProperty$Std(this._name, paramJavaType, this._contextAnnotations, this._member);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\BeanProperty$Std.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */