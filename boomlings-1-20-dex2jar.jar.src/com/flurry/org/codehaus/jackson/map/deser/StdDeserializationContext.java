package com.flurry.org.codehaus.jackson.map.deser;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.DeserializationProblemHandler;
import com.flurry.org.codehaus.jackson.map.DeserializerProvider;
import com.flurry.org.codehaus.jackson.map.InjectableValues;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.exc.UnrecognizedPropertyException;
import com.flurry.org.codehaus.jackson.map.util.ArrayBuilders;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import com.flurry.org.codehaus.jackson.map.util.LinkedNode;
import com.flurry.org.codehaus.jackson.map.util.ObjectBuffer;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class StdDeserializationContext extends DeserializationContext {
  static final int MAX_ERROR_STR_LEN = 500;
  
  protected ArrayBuilders _arrayBuilders;
  
  protected DateFormat _dateFormat;
  
  protected final DeserializerProvider _deserProvider;
  
  protected final InjectableValues _injectableValues;
  
  protected ObjectBuffer _objectBuffer;
  
  protected JsonParser _parser;
  
  public StdDeserializationContext(DeserializationConfig paramDeserializationConfig, JsonParser paramJsonParser, DeserializerProvider paramDeserializerProvider, InjectableValues paramInjectableValues) {
    super(paramDeserializationConfig);
    this._parser = paramJsonParser;
    this._deserProvider = paramDeserializerProvider;
    this._injectableValues = paramInjectableValues;
  }
  
  protected String _calcName(Class paramClass) {
    return paramClass.isArray() ? (_calcName(paramClass.getComponentType()) + "[]") : paramClass.getName();
  }
  
  protected String _desc(String paramString) {
    String str = paramString;
    if (paramString.length() > 500)
      str = paramString.substring(0, 500) + "]...[" + paramString.substring(paramString.length() - 500); 
    return str;
  }
  
  protected String _valueDesc() {
    String str;
    try {
      str = _desc(this._parser.getText());
    } catch (Exception exception) {
      str = "[N/A]";
    } 
    return str;
  }
  
  public Calendar constructCalendar(Date paramDate) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(paramDate);
    return calendar;
  }
  
  protected String determineClassName(Object paramObject) {
    return ClassUtil.getClassDescription(paramObject);
  }
  
  public Object findInjectableValue(Object paramObject1, BeanProperty paramBeanProperty, Object paramObject2) {
    if (this._injectableValues == null)
      throw new IllegalStateException("No 'injectableValues' configured, can not inject value with id [" + paramObject1 + "]"); 
    return this._injectableValues.findInjectableValue(paramObject1, this, paramBeanProperty, paramObject2);
  }
  
  public final ArrayBuilders getArrayBuilders() {
    if (this._arrayBuilders == null)
      this._arrayBuilders = new ArrayBuilders(); 
    return this._arrayBuilders;
  }
  
  protected DateFormat getDateFormat() {
    if (this._dateFormat == null)
      this._dateFormat = (DateFormat)this._config.getDateFormat().clone(); 
    return this._dateFormat;
  }
  
  public DeserializerProvider getDeserializerProvider() {
    return this._deserProvider;
  }
  
  public JsonParser getParser() {
    return this._parser;
  }
  
  public boolean handleUnknownProperty(JsonParser paramJsonParser, JsonDeserializer paramJsonDeserializer, Object paramObject, String paramString) {
    LinkedNode linkedNode = this._config.getProblemHandlers();
    if (linkedNode != null) {
      JsonParser jsonParser = this._parser;
      this._parser = paramJsonParser;
      LinkedNode linkedNode1 = linkedNode;
      while (true) {
        if (linkedNode1 != null) {
          try {
            boolean bool = ((DeserializationProblemHandler)linkedNode1.value()).handleUnknownProperty(this, paramJsonDeserializer, paramObject, paramString);
            if (bool) {
              bool = true;
              return bool;
            } 
          } finally {
            this._parser = jsonParser;
          } 
          continue;
        } 
        this._parser = jsonParser;
        return false;
      } 
    } 
    return false;
  }
  
  public JsonMappingException instantiationException(Class paramClass, String paramString) {
    return JsonMappingException.from(this._parser, "Can not construct instance of " + paramClass.getName() + ", problem: " + paramString);
  }
  
  public JsonMappingException instantiationException(Class paramClass, Throwable paramThrowable) {
    return JsonMappingException.from(this._parser, "Can not construct instance of " + paramClass.getName() + ", problem: " + paramThrowable.getMessage(), paramThrowable);
  }
  
  public final ObjectBuffer leaseObjectBuffer() {
    ObjectBuffer objectBuffer = this._objectBuffer;
    if (objectBuffer == null)
      return new ObjectBuffer(); 
    this._objectBuffer = null;
    return objectBuffer;
  }
  
  public JsonMappingException mappingException(Class paramClass) {
    return mappingException(paramClass, this._parser.getCurrentToken());
  }
  
  public JsonMappingException mappingException(Class paramClass, JsonToken paramJsonToken) {
    String str = _calcName(paramClass);
    return JsonMappingException.from(this._parser, "Can not deserialize instance of " + str + " out of " + paramJsonToken + " token");
  }
  
  public Date parseDate(String paramString) {
    try {
      return getDateFormat().parse(paramString);
    } catch (ParseException parseException) {
      throw new IllegalArgumentException(parseException.getMessage());
    } 
  }
  
  public final void returnObjectBuffer(ObjectBuffer paramObjectBuffer) {
    if (this._objectBuffer == null || paramObjectBuffer.initialCapacity() >= this._objectBuffer.initialCapacity())
      this._objectBuffer = paramObjectBuffer; 
  }
  
  public JsonMappingException unknownFieldException(Object paramObject, String paramString) {
    return (JsonMappingException)UnrecognizedPropertyException.from(this._parser, paramObject, paramString);
  }
  
  public JsonMappingException unknownTypeException(JavaType paramJavaType, String paramString) {
    return JsonMappingException.from(this._parser, "Could not resolve type id '" + paramString + "' into a subtype of " + paramJavaType);
  }
  
  public JsonMappingException weirdKeyException(Class paramClass, String paramString1, String paramString2) {
    return JsonMappingException.from(this._parser, "Can not construct Map key of type " + paramClass.getName() + " from String \"" + _desc(paramString1) + "\": " + paramString2);
  }
  
  public JsonMappingException weirdNumberException(Class paramClass, String paramString) {
    return JsonMappingException.from(this._parser, "Can not construct instance of " + paramClass.getName() + " from number value (" + _valueDesc() + "): " + paramString);
  }
  
  public JsonMappingException weirdStringException(Class paramClass, String paramString) {
    return JsonMappingException.from(this._parser, "Can not construct instance of " + paramClass.getName() + " from String value '" + _valueDesc() + "': " + paramString);
  }
  
  public JsonMappingException wrongTokenException(JsonParser paramJsonParser, JsonToken paramJsonToken, String paramString) {
    return JsonMappingException.from(paramJsonParser, "Unexpected token (" + paramJsonParser.getCurrentToken() + "), expected " + paramJsonToken + ": " + paramString);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\StdDeserializationContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */