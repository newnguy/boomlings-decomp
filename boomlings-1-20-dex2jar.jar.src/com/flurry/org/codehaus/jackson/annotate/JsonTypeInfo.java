package com.flurry.org.codehaus.jackson.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
public @interface JsonTypeInfo {
  Class defaultImpl() default JsonTypeInfo$None.class;
  
  JsonTypeInfo$As include() default JsonTypeInfo$As.PROPERTY;
  
  String property() default "";
  
  JsonTypeInfo$Id use();
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\annotate\JsonTypeInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */