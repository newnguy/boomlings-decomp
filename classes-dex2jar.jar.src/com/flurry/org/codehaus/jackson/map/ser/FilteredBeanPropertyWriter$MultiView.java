package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;

final class FilteredBeanPropertyWriter$MultiView extends BeanPropertyWriter {
  protected final BeanPropertyWriter _delegate;
  
  protected final Class[] _views;
  
  protected FilteredBeanPropertyWriter$MultiView(BeanPropertyWriter paramBeanPropertyWriter, Class[] paramArrayOfClass) {
    super(paramBeanPropertyWriter);
    this._delegate = paramBeanPropertyWriter;
    this._views = paramArrayOfClass;
  }
  
  public void serializeAsField(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    // Byte code:
    //   0: aload_3
    //   1: invokevirtual getSerializationView : ()Ljava/lang/Class;
    //   4: astore #6
    //   6: aload #6
    //   8: ifnull -> 57
    //   11: iconst_0
    //   12: istore #4
    //   14: aload_0
    //   15: getfield _views : [Ljava/lang/Class;
    //   18: arraylength
    //   19: istore #5
    //   21: iload #4
    //   23: iload #5
    //   25: if_icmpge -> 43
    //   28: aload_0
    //   29: getfield _views : [Ljava/lang/Class;
    //   32: iload #4
    //   34: aaload
    //   35: aload #6
    //   37: invokevirtual isAssignableFrom : (Ljava/lang/Class;)Z
    //   40: ifeq -> 51
    //   43: iload #4
    //   45: iload #5
    //   47: if_icmpne -> 57
    //   50: return
    //   51: iinc #4, 1
    //   54: goto -> 21
    //   57: aload_0
    //   58: getfield _delegate : Lcom/flurry/org/codehaus/jackson/map/ser/BeanPropertyWriter;
    //   61: aload_1
    //   62: aload_2
    //   63: aload_3
    //   64: invokevirtual serializeAsField : (Ljava/lang/Object;Lcom/flurry/org/codehaus/jackson/JsonGenerator;Lcom/flurry/org/codehaus/jackson/map/SerializerProvider;)V
    //   67: goto -> 50
  }
  
  public BeanPropertyWriter withSerializer(JsonSerializer paramJsonSerializer) {
    return new FilteredBeanPropertyWriter$MultiView(this._delegate.withSerializer(paramJsonSerializer), this._views);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\FilteredBeanPropertyWriter$MultiView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */