package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.flurry.org.codehaus.jackson.map.ser.std.EnumSerializer;
import com.flurry.org.codehaus.jackson.map.util.EnumValues;

@JacksonStdImpl
@Deprecated
public class EnumSerializer extends EnumSerializer {
  public EnumSerializer(EnumValues paramEnumValues) {
    super(paramEnumValues);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\EnumSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */