package com.flurry.org.codehaus.jackson.map.ser.impl;

import com.flurry.org.codehaus.jackson.map.JsonSerializer;

final class PropertySerializerMap$Multi extends PropertySerializerMap {
  private static final int MAX_ENTRIES = 8;
  
  private final PropertySerializerMap$TypeAndSerializer[] _entries;
  
  public PropertySerializerMap$Multi(PropertySerializerMap$TypeAndSerializer[] paramArrayOfPropertySerializerMap$TypeAndSerializer) {
    this._entries = paramArrayOfPropertySerializerMap$TypeAndSerializer;
  }
  
  public PropertySerializerMap newWith(Class paramClass, JsonSerializer paramJsonSerializer) {
    int i = this._entries.length;
    if (i == 8)
      return this; 
    PropertySerializerMap$TypeAndSerializer[] arrayOfPropertySerializerMap$TypeAndSerializer = new PropertySerializerMap$TypeAndSerializer[i + 1];
    System.arraycopy(this._entries, 0, arrayOfPropertySerializerMap$TypeAndSerializer, 0, i);
    arrayOfPropertySerializerMap$TypeAndSerializer[i] = new PropertySerializerMap$TypeAndSerializer(paramClass, paramJsonSerializer);
    return new PropertySerializerMap$Multi(arrayOfPropertySerializerMap$TypeAndSerializer);
  }
  
  public JsonSerializer serializerFor(Class paramClass) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aload_0
    //   3: getfield _entries : [Lcom/flurry/org/codehaus/jackson/map/ser/impl/PropertySerializerMap$TypeAndSerializer;
    //   6: arraylength
    //   7: istore_3
    //   8: iload_2
    //   9: iload_3
    //   10: if_icmpge -> 44
    //   13: aload_0
    //   14: getfield _entries : [Lcom/flurry/org/codehaus/jackson/map/ser/impl/PropertySerializerMap$TypeAndSerializer;
    //   17: iload_2
    //   18: aaload
    //   19: astore #4
    //   21: aload #4
    //   23: getfield type : Ljava/lang/Class;
    //   26: aload_1
    //   27: if_acmpne -> 38
    //   30: aload #4
    //   32: getfield serializer : Lcom/flurry/org/codehaus/jackson/map/JsonSerializer;
    //   35: astore_1
    //   36: aload_1
    //   37: areturn
    //   38: iinc #2, 1
    //   41: goto -> 8
    //   44: aconst_null
    //   45: astore_1
    //   46: goto -> 36
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\impl\PropertySerializerMap$Multi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */