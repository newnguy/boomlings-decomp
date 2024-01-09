package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import java.lang.reflect.Member;

public abstract class AnnotatedMember extends Annotated {
  protected final AnnotationMap _annotations;
  
  protected AnnotatedMember(AnnotationMap paramAnnotationMap) {
    this._annotations = paramAnnotationMap;
  }
  
  public final void fixAccess() {
    ClassUtil.checkAndFixAccess(getMember());
  }
  
  protected AnnotationMap getAllAnnotations() {
    return this._annotations;
  }
  
  public abstract Class getDeclaringClass();
  
  public abstract Member getMember();
  
  public abstract void setValue(Object paramObject1, Object paramObject2);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\introspect\AnnotatedMember.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */