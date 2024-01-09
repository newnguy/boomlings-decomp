package com.flurry.org.codehaus.jackson.map.ser.impl;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.ser.BeanPropertyWriter;
import java.util.Set;

public class SimpleBeanPropertyFilter$SerializeExceptFilter extends SimpleBeanPropertyFilter {
  protected final Set _propertiesToExclude;
  
  public SimpleBeanPropertyFilter$SerializeExceptFilter(Set paramSet) {
    this._propertiesToExclude = paramSet;
  }
  
  public void serializeAsField(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, BeanPropertyWriter paramBeanPropertyWriter) {
    if (!this._propertiesToExclude.contains(paramBeanPropertyWriter.getName()))
      paramBeanPropertyWriter.serializeAsField(paramObject, paramJsonGenerator, paramSerializerProvider); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\impl\SimpleBeanPropertyFilter$SerializeExceptFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */