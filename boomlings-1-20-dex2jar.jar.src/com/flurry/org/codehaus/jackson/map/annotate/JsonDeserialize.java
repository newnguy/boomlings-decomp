package com.flurry.org.codehaus.jackson.map.annotate;

import com.flurry.org.codehaus.jackson.annotate.JacksonAnnotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER})
public @interface JsonDeserialize {
  Class as() default NoClass.class;
  
  Class contentAs() default NoClass.class;
  
  Class contentUsing() default com.flurry.org.codehaus.jackson.map.JsonDeserializer.None.class;
  
  Class keyAs() default NoClass.class;
  
  Class keyUsing() default com.flurry.org.codehaus.jackson.map.KeyDeserializer.None.class;
  
  Class using() default com.flurry.org.codehaus.jackson.map.JsonDeserializer.None.class;
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\annotate\JsonDeserialize.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */