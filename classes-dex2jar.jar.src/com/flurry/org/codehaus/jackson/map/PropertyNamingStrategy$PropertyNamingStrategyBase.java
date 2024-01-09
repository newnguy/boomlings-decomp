package com.flurry.org.codehaus.jackson.map;

import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedField;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedParameter;

public abstract class PropertyNamingStrategy$PropertyNamingStrategyBase extends PropertyNamingStrategy {
  public String nameForConstructorParameter(MapperConfig paramMapperConfig, AnnotatedParameter paramAnnotatedParameter, String paramString) {
    return translate(paramString);
  }
  
  public String nameForField(MapperConfig paramMapperConfig, AnnotatedField paramAnnotatedField, String paramString) {
    return translate(paramString);
  }
  
  public String nameForGetterMethod(MapperConfig paramMapperConfig, AnnotatedMethod paramAnnotatedMethod, String paramString) {
    return translate(paramString);
  }
  
  public String nameForSetterMethod(MapperConfig paramMapperConfig, AnnotatedMethod paramAnnotatedMethod, String paramString) {
    return translate(paramString);
  }
  
  public abstract String translate(String paramString);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\PropertyNamingStrategy$PropertyNamingStrategyBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */