package com.flurry.org.codehaus.jackson.map.introspect;

import com.flurry.org.codehaus.jackson.annotate.JsonAutoDetect;
import com.flurry.org.codehaus.jackson.annotate.JsonMethod;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public interface VisibilityChecker {
  boolean isCreatorVisible(AnnotatedMember paramAnnotatedMember);
  
  boolean isCreatorVisible(Member paramMember);
  
  boolean isFieldVisible(AnnotatedField paramAnnotatedField);
  
  boolean isFieldVisible(Field paramField);
  
  boolean isGetterVisible(AnnotatedMethod paramAnnotatedMethod);
  
  boolean isGetterVisible(Method paramMethod);
  
  boolean isIsGetterVisible(AnnotatedMethod paramAnnotatedMethod);
  
  boolean isIsGetterVisible(Method paramMethod);
  
  boolean isSetterVisible(AnnotatedMethod paramAnnotatedMethod);
  
  boolean isSetterVisible(Method paramMethod);
  
  VisibilityChecker with(JsonAutoDetect.Visibility paramVisibility);
  
  VisibilityChecker with(JsonAutoDetect paramJsonAutoDetect);
  
  VisibilityChecker withCreatorVisibility(JsonAutoDetect.Visibility paramVisibility);
  
  VisibilityChecker withFieldVisibility(JsonAutoDetect.Visibility paramVisibility);
  
  VisibilityChecker withGetterVisibility(JsonAutoDetect.Visibility paramVisibility);
  
  VisibilityChecker withIsGetterVisibility(JsonAutoDetect.Visibility paramVisibility);
  
  VisibilityChecker withSetterVisibility(JsonAutoDetect.Visibility paramVisibility);
  
  VisibilityChecker withVisibility(JsonMethod paramJsonMethod, JsonAutoDetect.Visibility paramVisibility);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\introspect\VisibilityChecker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */