package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedField;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedParameter;

public abstract class PropertyNamingStrategy {
  public static final PropertyNamingStrategy CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES = new PropertyNamingStrategy$LowerCaseWithUnderscoresStrategy();
  
  public String nameForConstructorParameter(MapperConfig paramMapperConfig, AnnotatedParameter paramAnnotatedParameter, String paramString) {
    return paramString;
  }
  
  public String nameForField(MapperConfig paramMapperConfig, AnnotatedField paramAnnotatedField, String paramString) {
    return paramString;
  }
  
  public String nameForGetterMethod(MapperConfig paramMapperConfig, AnnotatedMethod paramAnnotatedMethod, String paramString) {
    return paramString;
  }
  
  public String nameForSetterMethod(MapperConfig paramMapperConfig, AnnotatedMethod paramAnnotatedMethod, String paramString) {
    return paramString;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\PropertyNamingStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */