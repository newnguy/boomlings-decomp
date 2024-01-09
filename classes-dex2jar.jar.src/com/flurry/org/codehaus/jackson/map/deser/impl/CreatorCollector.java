package com.flurry.org.codehaus.jackson.map.deser.impl;

import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.deser.ValueInstantiator;
import com.flurry.org.codehaus.jackson.map.deser.std.StdValueInstantiator;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedWithParams;
import com.flurry.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Member;
import java.util.HashMap;

public class CreatorCollector {
  final BasicBeanDescription _beanDesc;
  
  protected AnnotatedWithParams _booleanCreator;
  
  final boolean _canFixAccess;
  
  protected AnnotatedConstructor _defaultConstructor;
  
  protected AnnotatedWithParams _delegateCreator;
  
  protected AnnotatedWithParams _doubleCreator;
  
  protected AnnotatedWithParams _intCreator;
  
  protected AnnotatedWithParams _longCreator;
  
  protected CreatorProperty[] _propertyBasedArgs = null;
  
  protected AnnotatedWithParams _propertyBasedCreator;
  
  protected AnnotatedWithParams _stringCreator;
  
  public CreatorCollector(BasicBeanDescription paramBasicBeanDescription, boolean paramBoolean) {
    this._beanDesc = paramBasicBeanDescription;
    this._canFixAccess = paramBoolean;
  }
  
  public void addBooleanCreator(AnnotatedWithParams paramAnnotatedWithParams) {
    this._booleanCreator = verifyNonDup(paramAnnotatedWithParams, this._booleanCreator, "boolean");
  }
  
  public void addDelegatingCreator(AnnotatedWithParams paramAnnotatedWithParams) {
    this._delegateCreator = verifyNonDup(paramAnnotatedWithParams, this._delegateCreator, "delegate");
  }
  
  public void addDoubleCreator(AnnotatedWithParams paramAnnotatedWithParams) {
    this._doubleCreator = verifyNonDup(paramAnnotatedWithParams, this._doubleCreator, "double");
  }
  
  public void addIntCreator(AnnotatedWithParams paramAnnotatedWithParams) {
    this._intCreator = verifyNonDup(paramAnnotatedWithParams, this._intCreator, "int");
  }
  
  public void addLongCreator(AnnotatedWithParams paramAnnotatedWithParams) {
    this._longCreator = verifyNonDup(paramAnnotatedWithParams, this._longCreator, "long");
  }
  
  public void addPropertyCreator(AnnotatedWithParams paramAnnotatedWithParams, CreatorProperty[] paramArrayOfCreatorProperty) {
    this._propertyBasedCreator = verifyNonDup(paramAnnotatedWithParams, this._propertyBasedCreator, "property-based");
    if (paramArrayOfCreatorProperty.length > 1) {
      HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
      int i = paramArrayOfCreatorProperty.length;
      for (byte b = 0; b < i; b++) {
        String str = paramArrayOfCreatorProperty[b].getName();
        Integer integer = (Integer)hashMap.put(str, Integer.valueOf(b));
        if (integer != null)
          throw new IllegalArgumentException("Duplicate creator property \"" + str + "\" (index " + integer + " vs " + b + ")"); 
      } 
    } 
    this._propertyBasedArgs = paramArrayOfCreatorProperty;
  }
  
  public void addStringCreator(AnnotatedWithParams paramAnnotatedWithParams) {
    this._stringCreator = verifyNonDup(paramAnnotatedWithParams, this._stringCreator, "String");
  }
  
  public ValueInstantiator constructValueInstantiator(DeserializationConfig paramDeserializationConfig) {
    StdValueInstantiator stdValueInstantiator = new StdValueInstantiator(paramDeserializationConfig, this._beanDesc.getType());
    if (this._delegateCreator == null) {
      paramDeserializationConfig = null;
      stdValueInstantiator.configureFromObjectSettings((AnnotatedWithParams)this._defaultConstructor, this._delegateCreator, (JavaType)paramDeserializationConfig, this._propertyBasedCreator, this._propertyBasedArgs);
      stdValueInstantiator.configureFromStringCreator(this._stringCreator);
      stdValueInstantiator.configureFromIntCreator(this._intCreator);
      stdValueInstantiator.configureFromLongCreator(this._longCreator);
      stdValueInstantiator.configureFromDoubleCreator(this._doubleCreator);
      stdValueInstantiator.configureFromBooleanCreator(this._booleanCreator);
      return (ValueInstantiator)stdValueInstantiator;
    } 
    JavaType javaType = this._beanDesc.bindingsForBeanType().resolveType(this._delegateCreator.getParameterType(0));
    stdValueInstantiator.configureFromObjectSettings((AnnotatedWithParams)this._defaultConstructor, this._delegateCreator, javaType, this._propertyBasedCreator, this._propertyBasedArgs);
    stdValueInstantiator.configureFromStringCreator(this._stringCreator);
    stdValueInstantiator.configureFromIntCreator(this._intCreator);
    stdValueInstantiator.configureFromLongCreator(this._longCreator);
    stdValueInstantiator.configureFromDoubleCreator(this._doubleCreator);
    stdValueInstantiator.configureFromBooleanCreator(this._booleanCreator);
    return (ValueInstantiator)stdValueInstantiator;
  }
  
  public void setDefaultConstructor(AnnotatedConstructor paramAnnotatedConstructor) {
    this._defaultConstructor = paramAnnotatedConstructor;
  }
  
  protected AnnotatedWithParams verifyNonDup(AnnotatedWithParams paramAnnotatedWithParams1, AnnotatedWithParams paramAnnotatedWithParams2, String paramString) {
    if (paramAnnotatedWithParams2 != null && paramAnnotatedWithParams2.getClass() == paramAnnotatedWithParams1.getClass())
      throw new IllegalArgumentException("Conflicting " + paramString + " creators: already had " + paramAnnotatedWithParams2 + ", encountered " + paramAnnotatedWithParams1); 
    if (this._canFixAccess)
      ClassUtil.checkAndFixAccess((Member)paramAnnotatedWithParams1.getAnnotated()); 
    return paramAnnotatedWithParams1;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\impl\CreatorCollector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */