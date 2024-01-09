package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.map.ser.std.SerializerBase;
import com.flurry.org.codehaus.jackson.type.JavaType;

@Deprecated
public abstract class SerializerBase extends SerializerBase {
  protected SerializerBase(JavaType paramJavaType) {
    super(paramJavaType);
  }
  
  protected SerializerBase(Class paramClass) {
    super(paramClass);
  }
  
  protected SerializerBase(Class paramClass, boolean paramBoolean) {
    super(paramClass, paramBoolean);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\SerializerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */