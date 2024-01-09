package com.flurry.org.codehaus.jackson.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface JsonAutoDetect {
  JsonAutoDetect$Visibility creatorVisibility() default JsonAutoDetect$Visibility.DEFAULT;
  
  JsonAutoDetect$Visibility fieldVisibility() default JsonAutoDetect$Visibility.DEFAULT;
  
  JsonAutoDetect$Visibility getterVisibility() default JsonAutoDetect$Visibility.DEFAULT;
  
  JsonAutoDetect$Visibility isGetterVisibility() default JsonAutoDetect$Visibility.DEFAULT;
  
  JsonAutoDetect$Visibility setterVisibility() default JsonAutoDetect$Visibility.DEFAULT;
  
  JsonMethod[] value() default {JsonMethod.ALL};
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\annotate\JsonAutoDetect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */