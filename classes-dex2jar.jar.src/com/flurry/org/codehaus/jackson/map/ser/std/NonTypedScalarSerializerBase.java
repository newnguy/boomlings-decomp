package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.TypeSerializer;

public abstract class NonTypedScalarSerializerBase extends ScalarSerializerBase {
  protected NonTypedScalarSerializerBase(Class paramClass) {
    super(paramClass);
  }
  
  public final void serializeWithType(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, TypeSerializer paramTypeSerializer) {
    serialize(paramObject, paramJsonGenerator, paramSerializerProvider);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\NonTypedScalarSerializerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */