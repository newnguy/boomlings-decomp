package com.flurry.org.codehaus.jackson.map.ser.std;

import com.flurry.org.codehaus.jackson.map.TypeSerializer;

public abstract class ContainerSerializerBase extends SerializerBase {
  protected ContainerSerializerBase(Class paramClass) {
    super(paramClass);
  }
  
  protected ContainerSerializerBase(Class paramClass, boolean paramBoolean) {
    super(paramClass, paramBoolean);
  }
  
  public abstract ContainerSerializerBase _withValueTypeSerializer(TypeSerializer paramTypeSerializer);
  
  public ContainerSerializerBase withValueTypeSerializer(TypeSerializer paramTypeSerializer) {
    return (paramTypeSerializer == null) ? this : _withValueTypeSerializer(paramTypeSerializer);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\std\ContainerSerializerBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */