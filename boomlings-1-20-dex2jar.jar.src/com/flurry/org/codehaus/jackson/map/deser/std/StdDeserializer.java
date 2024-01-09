package com.flurry.org.codehaus.jackson.map.deser.std;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.JsonToken;
import com.flurry.org.codehaus.jackson.io.NumberInput;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.DeserializerProvider;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.TypeDeserializer;
import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.Date;

public abstract class StdDeserializer extends JsonDeserializer {
  protected final Class _valueClass;
  
  protected StdDeserializer(JavaType paramJavaType) {
    Class clazz;
    if (paramJavaType == null) {
      paramJavaType = null;
    } else {
      clazz = paramJavaType.getRawClass();
    } 
    this._valueClass = clazz;
  }
  
  protected StdDeserializer(Class paramClass) {
    this._valueClass = paramClass;
  }
  
  protected static final double parseDouble(String paramString) {
    return "2.2250738585072012e-308".equals(paramString) ? 2.2250738585072014E-308D : Double.parseDouble(paramString);
  }
  
  protected final Boolean _parseBoolean(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken == JsonToken.VALUE_TRUE)
      return Boolean.TRUE; 
    if (jsonToken == JsonToken.VALUE_FALSE)
      return Boolean.FALSE; 
    if (jsonToken == JsonToken.VALUE_NUMBER_INT)
      return (paramJsonParser.getNumberType() == JsonParser.NumberType.INT) ? ((paramJsonParser.getIntValue() == 0) ? Boolean.FALSE : Boolean.TRUE) : Boolean.valueOf(_parseBooleanFromNumber(paramJsonParser, paramDeserializationContext)); 
    if (jsonToken == JsonToken.VALUE_NULL)
      return (Boolean)getNullValue(); 
    if (jsonToken == JsonToken.VALUE_STRING) {
      String str = paramJsonParser.getText().trim();
      if ("true".equals(str))
        return Boolean.TRUE; 
      if ("false".equals(str))
        return Boolean.FALSE; 
      if (str.length() == 0)
        return (Boolean)getEmptyValue(); 
      throw paramDeserializationContext.weirdStringException(this._valueClass, "only \"true\" or \"false\" recognized");
    } 
    throw paramDeserializationContext.mappingException(this._valueClass, jsonToken);
  }
  
  protected final boolean _parseBooleanFromNumber(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    Boolean bool;
    if (paramJsonParser.getNumberType() == JsonParser.NumberType.LONG) {
      if (paramJsonParser.getLongValue() == 0L) {
        bool = Boolean.FALSE;
      } else {
        bool = Boolean.TRUE;
      } 
      return bool.booleanValue();
    } 
    String str = bool.getText();
    return ("0.0".equals(str) || "0".equals(str)) ? Boolean.FALSE.booleanValue() : Boolean.TRUE.booleanValue();
  }
  
  protected final boolean _parseBooleanPrimitive(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    boolean bool = true;
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken != JsonToken.VALUE_TRUE) {
      if (jsonToken == JsonToken.VALUE_FALSE)
        return false; 
      if (jsonToken == JsonToken.VALUE_NULL)
        return false; 
      if (jsonToken == JsonToken.VALUE_NUMBER_INT) {
        if (paramJsonParser.getNumberType() == JsonParser.NumberType.INT) {
          if (paramJsonParser.getIntValue() == 0)
            bool = false; 
          return bool;
        } 
        return _parseBooleanFromNumber(paramJsonParser, paramDeserializationContext);
      } 
      if (jsonToken == JsonToken.VALUE_STRING) {
        String str = paramJsonParser.getText().trim();
        if (!"true".equals(str)) {
          if ("false".equals(str) || str.length() == 0)
            return Boolean.FALSE.booleanValue(); 
          throw paramDeserializationContext.weirdStringException(this._valueClass, "only \"true\" or \"false\" recognized");
        } 
        return bool;
      } 
      throw paramDeserializationContext.mappingException(this._valueClass, jsonToken);
    } 
    return bool;
  }
  
  protected Byte _parseByte(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT)
      return Byte.valueOf(paramJsonParser.getByteValue()); 
    if (jsonToken == JsonToken.VALUE_STRING) {
      int i;
      String str = paramJsonParser.getText().trim();
      try {
        if (str.length() == 0)
          return (Byte)getEmptyValue(); 
        i = NumberInput.parseInt(str);
        if (i < -128 || i > 127)
          throw paramDeserializationContext.weirdStringException(this._valueClass, "overflow, value can not be represented as 8-bit value"); 
      } catch (IllegalArgumentException illegalArgumentException) {
        throw paramDeserializationContext.weirdStringException(this._valueClass, "not a valid Byte value");
      } 
      return Byte.valueOf((byte)i);
    } 
    if (jsonToken == JsonToken.VALUE_NULL)
      return (Byte)getNullValue(); 
    throw paramDeserializationContext.mappingException(this._valueClass, jsonToken);
  }
  
  protected Date _parseDate(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken == JsonToken.VALUE_NUMBER_INT)
      return new Date(paramJsonParser.getLongValue()); 
    if (jsonToken == JsonToken.VALUE_NULL)
      return (Date)getNullValue(); 
    if (jsonToken == JsonToken.VALUE_STRING) {
      try {
        String str = paramJsonParser.getText().trim();
        if (str.length() == 0)
          return (Date)getEmptyValue(); 
        Date date = paramDeserializationContext.parseDate(str);
      } catch (IllegalArgumentException illegalArgumentException) {
        throw paramDeserializationContext.weirdStringException(this._valueClass, "not a valid representation (error: " + illegalArgumentException.getMessage() + ")");
      } 
      return (Date)illegalArgumentException;
    } 
    throw paramDeserializationContext.mappingException(this._valueClass, jsonToken);
  }
  
  protected final Double _parseDouble(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT)
      return Double.valueOf(paramJsonParser.getDoubleValue()); 
    if (jsonToken == JsonToken.VALUE_STRING) {
      String str = paramJsonParser.getText().trim();
      if (str.length() == 0)
        return (Double)getEmptyValue(); 
      switch (str.charAt(0)) {
        default:
          try {
            double d = parseDouble(str);
            Double double_ = Double.valueOf(d);
          } catch (IllegalArgumentException illegalArgumentException) {
            throw paramDeserializationContext.weirdStringException(this._valueClass, "not a valid Double value");
          } 
          return (Double)illegalArgumentException;
        case 'I':
          if ("Infinity".equals(illegalArgumentException) || "INF".equals(illegalArgumentException))
            return Double.valueOf(Double.POSITIVE_INFINITY); 
        case 'N':
          if ("NaN".equals(illegalArgumentException))
            return Double.valueOf(Double.NaN); 
        case '-':
          break;
      } 
      if ("-Infinity".equals(illegalArgumentException) || "-INF".equals(illegalArgumentException))
        return Double.valueOf(Double.NEGATIVE_INFINITY); 
    } 
    if (jsonToken == JsonToken.VALUE_NULL)
      return (Double)getNullValue(); 
    throw paramDeserializationContext.mappingException(this._valueClass, jsonToken);
  }
  
  protected final double _parseDoublePrimitive(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    double d = 0.0D;
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT)
      return paramJsonParser.getDoubleValue(); 
    if (jsonToken == JsonToken.VALUE_STRING) {
      String str = paramJsonParser.getText().trim();
      if (str.length() != 0) {
        switch (str.charAt(0)) {
          default:
            try {
              d = parseDouble(str);
            } catch (IllegalArgumentException illegalArgumentException) {
              throw paramDeserializationContext.weirdStringException(this._valueClass, "not a valid double value");
            } 
            return d;
          case 'I':
            if ("Infinity".equals(illegalArgumentException) || "INF".equals(illegalArgumentException))
              return Double.POSITIVE_INFINITY; 
          case 'N':
            if ("NaN".equals(illegalArgumentException))
              return Double.NaN; 
          case '-':
            break;
        } 
        if ("-Infinity".equals(illegalArgumentException) || "-INF".equals(illegalArgumentException))
          return Double.NEGATIVE_INFINITY; 
      } 
      return d;
    } 
    if (jsonToken != JsonToken.VALUE_NULL)
      throw paramDeserializationContext.mappingException(this._valueClass, jsonToken); 
    return d;
  }
  
  protected final Float _parseFloat(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT)
      return Float.valueOf(paramJsonParser.getFloatValue()); 
    if (jsonToken == JsonToken.VALUE_STRING) {
      String str = paramJsonParser.getText().trim();
      if (str.length() == 0)
        return (Float)getEmptyValue(); 
      switch (str.charAt(0)) {
        default:
          try {
            float f = Float.parseFloat(str);
            Float float_ = Float.valueOf(f);
          } catch (IllegalArgumentException illegalArgumentException) {
            throw paramDeserializationContext.weirdStringException(this._valueClass, "not a valid Float value");
          } 
          return (Float)illegalArgumentException;
        case 'I':
          if ("Infinity".equals(illegalArgumentException) || "INF".equals(illegalArgumentException))
            return Float.valueOf(Float.POSITIVE_INFINITY); 
        case 'N':
          if ("NaN".equals(illegalArgumentException))
            return Float.valueOf(Float.NaN); 
        case '-':
          break;
      } 
      if ("-Infinity".equals(illegalArgumentException) || "-INF".equals(illegalArgumentException))
        return Float.valueOf(Float.NEGATIVE_INFINITY); 
    } 
    if (jsonToken == JsonToken.VALUE_NULL)
      return (Float)getNullValue(); 
    throw paramDeserializationContext.mappingException(this._valueClass, jsonToken);
  }
  
  protected final float _parseFloatPrimitive(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    float f = 0.0F;
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT)
      return paramJsonParser.getFloatValue(); 
    if (jsonToken == JsonToken.VALUE_STRING) {
      String str = paramJsonParser.getText().trim();
      if (str.length() != 0) {
        switch (str.charAt(0)) {
          default:
            try {
              f = Float.parseFloat(str);
            } catch (IllegalArgumentException illegalArgumentException) {
              throw paramDeserializationContext.weirdStringException(this._valueClass, "not a valid float value");
            } 
            return f;
          case 'I':
            if ("Infinity".equals(illegalArgumentException) || "INF".equals(illegalArgumentException))
              return Float.POSITIVE_INFINITY; 
          case 'N':
            if ("NaN".equals(illegalArgumentException))
              return Float.NaN; 
          case '-':
            break;
        } 
        if ("-Infinity".equals(illegalArgumentException) || "-INF".equals(illegalArgumentException))
          return Float.NEGATIVE_INFINITY; 
      } 
      return f;
    } 
    if (jsonToken != JsonToken.VALUE_NULL)
      throw paramDeserializationContext.mappingException(this._valueClass, jsonToken); 
    return f;
  }
  
  protected final int _parseIntPrimitive(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    Class clazz;
    int i = 0;
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT)
      return paramJsonParser.getIntValue(); 
    if (jsonToken == JsonToken.VALUE_STRING) {
      int j;
      String str = paramJsonParser.getText().trim();
      try {
        j = str.length();
        if (j > 9) {
          long l = Long.parseLong(str);
          if (l < -2147483648L || l > 2147483647L) {
            clazz = this._valueClass;
            StringBuilder stringBuilder = new StringBuilder();
            this();
            throw paramDeserializationContext.weirdStringException(clazz, stringBuilder.append("Overflow: numeric value (").append(str).append(") out of range of int (").append(-2147483648).append(" - ").append(2147483647).append(")").toString());
          } 
          return (int)l;
        } 
      } catch (IllegalArgumentException illegalArgumentException) {
        throw paramDeserializationContext.weirdStringException(this._valueClass, "not a valid int value");
      } 
      if (j != 0)
        i = NumberInput.parseInt((String)illegalArgumentException); 
      return i;
    } 
    if (clazz != JsonToken.VALUE_NULL)
      throw paramDeserializationContext.mappingException(this._valueClass, clazz); 
    return i;
  }
  
  protected final Integer _parseInteger(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    String str;
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT)
      return Integer.valueOf(paramJsonParser.getIntValue()); 
    if (jsonToken == JsonToken.VALUE_STRING) {
      str = paramJsonParser.getText().trim();
      try {
        i = str.length();
        if (i > 9) {
          long l = Long.parseLong(str);
          if (l < -2147483648L || l > 2147483647L) {
            Class clazz = this._valueClass;
            StringBuilder stringBuilder = new StringBuilder();
            this();
            throw paramDeserializationContext.weirdStringException(clazz, stringBuilder.append("Overflow: numeric value (").append(str).append(") out of range of Integer (").append(-2147483648).append(" - ").append(2147483647).append(")").toString());
          } 
          i = (int)l;
          return Integer.valueOf(i);
        } 
      } catch (IllegalArgumentException illegalArgumentException) {
        throw paramDeserializationContext.weirdStringException(this._valueClass, "not a valid Integer value");
      } 
      if (i == 0)
        return (Integer)getEmptyValue(); 
      int i = NumberInput.parseInt(str);
      return Integer.valueOf(i);
    } 
    if (str == JsonToken.VALUE_NULL)
      return (Integer)getNullValue(); 
    throw paramDeserializationContext.mappingException(this._valueClass, str);
  }
  
  protected final Long _parseLong(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT)
      return Long.valueOf(paramJsonParser.getLongValue()); 
    if (jsonToken == JsonToken.VALUE_STRING) {
      String str = paramJsonParser.getText().trim();
      if (str.length() == 0)
        return (Long)getEmptyValue(); 
      try {
        long l = NumberInput.parseLong(str);
        Long long_ = Long.valueOf(l);
      } catch (IllegalArgumentException illegalArgumentException) {
        throw paramDeserializationContext.weirdStringException(this._valueClass, "not a valid Long value");
      } 
      return (Long)illegalArgumentException;
    } 
    if (jsonToken == JsonToken.VALUE_NULL)
      return (Long)getNullValue(); 
    throw paramDeserializationContext.mappingException(this._valueClass, jsonToken);
  }
  
  protected final long _parseLongPrimitive(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    long l = 0L;
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT)
      return paramJsonParser.getLongValue(); 
    if (jsonToken == JsonToken.VALUE_STRING) {
      String str = paramJsonParser.getText().trim();
      if (str.length() != 0)
        try {
          l = NumberInput.parseLong(str);
        } catch (IllegalArgumentException illegalArgumentException) {
          throw paramDeserializationContext.weirdStringException(this._valueClass, "not a valid long value");
        }  
      return l;
    } 
    if (jsonToken != JsonToken.VALUE_NULL)
      throw paramDeserializationContext.mappingException(this._valueClass, jsonToken); 
    return l;
  }
  
  protected Short _parseShort(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    JsonToken jsonToken = paramJsonParser.getCurrentToken();
    if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT)
      return Short.valueOf(paramJsonParser.getShortValue()); 
    if (jsonToken == JsonToken.VALUE_STRING) {
      int i;
      String str = paramJsonParser.getText().trim();
      try {
        if (str.length() == 0)
          return (Short)getEmptyValue(); 
        i = NumberInput.parseInt(str);
        if (i < -32768 || i > 32767)
          throw paramDeserializationContext.weirdStringException(this._valueClass, "overflow, value can not be represented as 16-bit value"); 
      } catch (IllegalArgumentException illegalArgumentException) {
        throw paramDeserializationContext.weirdStringException(this._valueClass, "not a valid Short value");
      } 
      return Short.valueOf((short)i);
    } 
    if (jsonToken == JsonToken.VALUE_NULL)
      return (Short)getNullValue(); 
    throw paramDeserializationContext.mappingException(this._valueClass, jsonToken);
  }
  
  protected final short _parseShortPrimitive(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    int i = _parseIntPrimitive(paramJsonParser, paramDeserializationContext);
    if (i < -32768 || i > 32767)
      throw paramDeserializationContext.weirdStringException(this._valueClass, "overflow, value can not be represented as 16-bit value"); 
    return (short)i;
  }
  
  public Object deserializeWithType(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, TypeDeserializer paramTypeDeserializer) {
    return paramTypeDeserializer.deserializeTypedFromAny(paramJsonParser, paramDeserializationContext);
  }
  
  protected JsonDeserializer findDeserializer(DeserializationConfig paramDeserializationConfig, DeserializerProvider paramDeserializerProvider, JavaType paramJavaType, BeanProperty paramBeanProperty) {
    return paramDeserializerProvider.findValueDeserializer(paramDeserializationConfig, paramJavaType, paramBeanProperty);
  }
  
  public Class getValueClass() {
    return this._valueClass;
  }
  
  public JavaType getValueType() {
    return null;
  }
  
  protected void handleUnknownProperty(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, String paramString) {
    Object object = paramObject;
    if (paramObject == null)
      object = getValueClass(); 
    if (!paramDeserializationContext.handleUnknownProperty(paramJsonParser, this, object, paramString)) {
      reportUnknownProperty(paramDeserializationContext, object, paramString);
      paramJsonParser.skipChildren();
    } 
  }
  
  protected boolean isDefaultSerializer(JsonDeserializer paramJsonDeserializer) {
    return (paramJsonDeserializer != null && paramJsonDeserializer.getClass().getAnnotation(JacksonStdImpl.class) != null);
  }
  
  protected void reportUnknownProperty(DeserializationContext paramDeserializationContext, Object paramObject, String paramString) {
    if (paramDeserializationContext.isEnabled(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES))
      throw paramDeserializationContext.unknownFieldException(paramObject, paramString); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\std\StdDeserializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */