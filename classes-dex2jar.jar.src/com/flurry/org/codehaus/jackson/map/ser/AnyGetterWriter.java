package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.introspect.AnnotatedMethod;
import com.flurry.org.codehaus.jackson.map.ser.std.MapSerializer;
import java.lang.reflect.Method;
import java.util.Map;

public class AnyGetterWriter {
  protected final Method _anyGetter;
  
  protected final MapSerializer _serializer;
  
  public AnyGetterWriter(AnnotatedMethod paramAnnotatedMethod, MapSerializer paramMapSerializer) {
    this._anyGetter = paramAnnotatedMethod.getAnnotated();
    this._serializer = paramMapSerializer;
  }
  
  public void getAndSerialize(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    paramObject = this._anyGetter.invoke(paramObject, new Object[0]);
    if (paramObject != null) {
      if (!(paramObject instanceof Map))
        throw new JsonMappingException("Value returned by 'any-getter' (" + this._anyGetter.getName() + "()) not java.util.Map but " + paramObject.getClass().getName()); 
      this._serializer.serializeFields((Map)paramObject, paramJsonGenerator, paramSerializerProvider);
    } 
  }
  
  public void resolve(SerializerProvider paramSerializerProvider) {
    this._serializer.resolve(paramSerializerProvider);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\AnyGetterWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */