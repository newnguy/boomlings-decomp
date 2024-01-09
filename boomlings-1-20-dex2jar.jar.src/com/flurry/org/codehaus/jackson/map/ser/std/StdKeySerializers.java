package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.type.JavaType;
import java.util.Calendar;
import java.util.Date;

public class StdKeySerializers {
  protected static final JsonSerializer DEFAULT_KEY_SERIALIZER = new StdKeySerializer();
  
  protected static final JsonSerializer DEFAULT_STRING_SERIALIZER = new StdKeySerializers$StringKeySerializer();
  
  public static JsonSerializer getStdKeySerializer(JavaType paramJavaType) {
    if (paramJavaType == null)
      return DEFAULT_KEY_SERIALIZER; 
    Class<String> clazz = paramJavaType.getRawClass();
    return (clazz == String.class) ? DEFAULT_STRING_SERIALIZER : ((clazz == Object.class) ? DEFAULT_KEY_SERIALIZER : (Date.class.isAssignableFrom(clazz) ? StdKeySerializers$DateKeySerializer.instance : (Calendar.class.isAssignableFrom(clazz) ? StdKeySerializers$CalendarKeySerializer.instance : DEFAULT_KEY_SERIALIZER)));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\StdKeySerializers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */