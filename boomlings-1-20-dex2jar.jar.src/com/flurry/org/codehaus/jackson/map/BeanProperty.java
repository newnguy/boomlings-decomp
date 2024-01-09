package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMember;
import com.flurry.org.codehaus.jackson.map.util.Named;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.annotation.Annotation;

public interface BeanProperty extends Named {
  Annotation getAnnotation(Class paramClass);
  
  Annotation getContextAnnotation(Class paramClass);
  
  AnnotatedMember getMember();
  
  String getName();
  
  JavaType getType();
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\BeanProperty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */