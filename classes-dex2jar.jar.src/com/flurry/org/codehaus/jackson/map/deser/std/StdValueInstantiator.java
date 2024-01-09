package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty;
import com.flurry.org.codehaus.jackson.map.deser.ValueInstantiator;
import com.flurry.org.codehaus.jackson.map.deser.impl.CreatorProperty;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedWithParams;
import com.flurry.org.codehaus.jackson.type.JavaType;

public class StdValueInstantiator extends ValueInstantiator {
  protected final boolean _cfgEmptyStringsAsObjects;
  
  protected CreatorProperty[] _constructorArguments;
  
  protected AnnotatedWithParams _defaultCreator;
  
  protected AnnotatedWithParams _delegateCreator;
  
  protected JavaType _delegateType;
  
  protected AnnotatedWithParams _fromBooleanCreator;
  
  protected AnnotatedWithParams _fromDoubleCreator;
  
  protected AnnotatedWithParams _fromIntCreator;
  
  protected AnnotatedWithParams _fromLongCreator;
  
  protected AnnotatedWithParams _fromStringCreator;
  
  protected final String _valueTypeDesc;
  
  protected AnnotatedWithParams _withArgsCreator;
  
  public StdValueInstantiator(DeserializationConfig paramDeserializationConfig, JavaType paramJavaType) {
    String str;
    boolean bool;
    if (paramDeserializationConfig == null) {
      bool = false;
    } else {
      bool = paramDeserializationConfig.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    } 
    this._cfgEmptyStringsAsObjects = bool;
    if (paramJavaType == null) {
      str = "UNKNOWN TYPE";
    } else {
      str = paramJavaType.toString();
    } 
    this._valueTypeDesc = str;
  }
  
  public StdValueInstantiator(DeserializationConfig paramDeserializationConfig, Class paramClass) {
    String str;
    boolean bool;
    if (paramDeserializationConfig == null) {
      bool = false;
    } else {
      bool = paramDeserializationConfig.isEnabled(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    } 
    this._cfgEmptyStringsAsObjects = bool;
    if (paramClass == null) {
      str = "UNKNOWN TYPE";
    } else {
      str = paramClass.getName();
    } 
    this._valueTypeDesc = str;
  }
  
  protected StdValueInstantiator(StdValueInstantiator paramStdValueInstantiator) {
    this._cfgEmptyStringsAsObjects = paramStdValueInstantiator._cfgEmptyStringsAsObjects;
    this._valueTypeDesc = paramStdValueInstantiator._valueTypeDesc;
    this._defaultCreator = paramStdValueInstantiator._defaultCreator;
    this._constructorArguments = paramStdValueInstantiator._constructorArguments;
    this._withArgsCreator = paramStdValueInstantiator._withArgsCreator;
    this._delegateType = paramStdValueInstantiator._delegateType;
    this._delegateCreator = paramStdValueInstantiator._delegateCreator;
    this._fromStringCreator = paramStdValueInstantiator._fromStringCreator;
    this._fromIntCreator = paramStdValueInstantiator._fromIntCreator;
    this._fromLongCreator = paramStdValueInstantiator._fromLongCreator;
    this._fromDoubleCreator = paramStdValueInstantiator._fromDoubleCreator;
    this._fromBooleanCreator = paramStdValueInstantiator._fromBooleanCreator;
  }
  
  protected Object _createFromStringFallbacks(String paramString) {
    if (this._fromBooleanCreator != null) {
      String str = paramString.trim();
      if ("true".equals(str))
        return createFromBoolean(true); 
      if ("false".equals(str))
        return createFromBoolean(false); 
    } 
    if (this._cfgEmptyStringsAsObjects && paramString.length() == 0)
      return null; 
    throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " from JSON String; no single-String constructor/factory method");
  }
  
  public boolean canCreateFromBoolean() {
    return (this._fromBooleanCreator != null);
  }
  
  public boolean canCreateFromDouble() {
    return (this._fromDoubleCreator != null);
  }
  
  public boolean canCreateFromInt() {
    return (this._fromIntCreator != null);
  }
  
  public boolean canCreateFromLong() {
    return (this._fromLongCreator != null);
  }
  
  public boolean canCreateFromObjectWith() {
    return (this._withArgsCreator != null);
  }
  
  public boolean canCreateFromString() {
    return (this._fromStringCreator != null);
  }
  
  public boolean canCreateUsingDefault() {
    return (this._defaultCreator != null);
  }
  
  public void configureFromBooleanCreator(AnnotatedWithParams paramAnnotatedWithParams) {
    this._fromBooleanCreator = paramAnnotatedWithParams;
  }
  
  public void configureFromDoubleCreator(AnnotatedWithParams paramAnnotatedWithParams) {
    this._fromDoubleCreator = paramAnnotatedWithParams;
  }
  
  public void configureFromIntCreator(AnnotatedWithParams paramAnnotatedWithParams) {
    this._fromIntCreator = paramAnnotatedWithParams;
  }
  
  public void configureFromLongCreator(AnnotatedWithParams paramAnnotatedWithParams) {
    this._fromLongCreator = paramAnnotatedWithParams;
  }
  
  public void configureFromObjectSettings(AnnotatedWithParams paramAnnotatedWithParams1, AnnotatedWithParams paramAnnotatedWithParams2, JavaType paramJavaType, AnnotatedWithParams paramAnnotatedWithParams3, CreatorProperty[] paramArrayOfCreatorProperty) {
    this._defaultCreator = paramAnnotatedWithParams1;
    this._delegateCreator = paramAnnotatedWithParams2;
    this._delegateType = paramJavaType;
    this._withArgsCreator = paramAnnotatedWithParams3;
    this._constructorArguments = paramArrayOfCreatorProperty;
  }
  
  public void configureFromStringCreator(AnnotatedWithParams paramAnnotatedWithParams) {
    this._fromStringCreator = paramAnnotatedWithParams;
  }
  
  public Object createFromBoolean(boolean paramBoolean) {
    try {
      if (this._fromBooleanCreator != null)
        return this._fromBooleanCreator.call1(Boolean.valueOf(paramBoolean)); 
    } catch (Exception exception) {
      throw wrapException(exception);
    } 
    throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " from JSON boolean value; no single-boolean/Boolean-arg constructor/factory method");
  }
  
  public Object createFromDouble(double paramDouble) {
    try {
      if (this._fromDoubleCreator != null)
        return this._fromDoubleCreator.call1(Double.valueOf(paramDouble)); 
    } catch (Exception exception) {
      throw wrapException(exception);
    } 
    throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " from JSON floating-point number; no one-double/Double-arg constructor/factory method");
  }
  
  public Object createFromInt(int paramInt) {
    try {
      if (this._fromIntCreator != null)
        return this._fromIntCreator.call1(Integer.valueOf(paramInt)); 
      if (this._fromLongCreator != null)
        return this._fromLongCreator.call1(Long.valueOf(paramInt)); 
    } catch (Exception exception) {
      throw wrapException(exception);
    } 
    throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " from JSON integral number; no single-int-arg constructor/factory method");
  }
  
  public Object createFromLong(long paramLong) {
    try {
      if (this._fromLongCreator != null)
        return this._fromLongCreator.call1(Long.valueOf(paramLong)); 
    } catch (Exception exception) {
      throw wrapException(exception);
    } 
    throw new JsonMappingException("Can not instantiate value of type " + getValueTypeDesc() + " from JSON long integral number; no single-long-arg constructor/factory method");
  }
  
  public Object createFromObjectWith(Object[] paramArrayOfObject) {
    if (this._withArgsCreator == null)
      throw new IllegalStateException("No with-args constructor for " + getValueTypeDesc()); 
    try {
      return this._withArgsCreator.call(paramArrayOfObject);
    } catch (ExceptionInInitializerError exceptionInInitializerError) {
      throw wrapException(exceptionInInitializerError);
    } catch (Exception exception) {
      throw wrapException(exception);
    } 
  }
  
  public Object createFromString(String paramString) {
    if (this._fromStringCreator != null)
      try {
        return this._fromStringCreator.call1(paramString);
      } catch (Exception exception) {
        throw wrapException(exception);
      }  
    return _createFromStringFallbacks((String)exception);
  }
  
  public Object createUsingDefault() {
    if (this._defaultCreator == null)
      throw new IllegalStateException("No default constructor for " + getValueTypeDesc()); 
    try {
      return this._defaultCreator.call();
    } catch (ExceptionInInitializerError exceptionInInitializerError) {
      throw wrapException(exceptionInInitializerError);
    } catch (Exception exception) {
      throw wrapException(exception);
    } 
  }
  
  public Object createUsingDelegate(Object paramObject) {
    if (this._delegateCreator == null)
      throw new IllegalStateException("No delegate constructor for " + getValueTypeDesc()); 
    try {
      return this._delegateCreator.call1(paramObject);
    } catch (ExceptionInInitializerError exceptionInInitializerError) {
      throw wrapException(exceptionInInitializerError);
    } catch (Exception exception) {
      throw wrapException(exception);
    } 
  }
  
  public AnnotatedWithParams getDefaultCreator() {
    return this._defaultCreator;
  }
  
  public AnnotatedWithParams getDelegateCreator() {
    return this._delegateCreator;
  }
  
  public JavaType getDelegateType() {
    return this._delegateType;
  }
  
  public SettableBeanProperty[] getFromObjectArguments() {
    return (SettableBeanProperty[])this._constructorArguments;
  }
  
  public String getValueTypeDesc() {
    return this._valueTypeDesc;
  }
  
  public AnnotatedWithParams getWithArgsCreator() {
    return this._withArgsCreator;
  }
  
  protected JsonMappingException wrapException(Throwable paramThrowable) {
    while (paramThrowable.getCause() != null)
      paramThrowable = paramThrowable.getCause(); 
    return new JsonMappingException("Instantiation of " + getValueTypeDesc() + " value failed: " + paramThrowable.getMessage(), paramThrowable);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdValueInstantiator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */