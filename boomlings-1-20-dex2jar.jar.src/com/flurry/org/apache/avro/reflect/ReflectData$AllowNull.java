package com.flurry.org.apache.avro.reflect;

import com.flurry.org.apache.avro.Schema;
import java.lang.reflect.Field;
import java.util.Map;

public class ReflectData$AllowNull extends ReflectData {
  private static final ReflectData$AllowNull INSTANCE = new ReflectData$AllowNull();
  
  public static ReflectData$AllowNull get() {
    return INSTANCE;
  }
  
  protected Schema createFieldSchema(Field paramField, Map paramMap) {
    return makeNullable(super.createFieldSchema(paramField, paramMap));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\reflect\ReflectData$AllowNull.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */