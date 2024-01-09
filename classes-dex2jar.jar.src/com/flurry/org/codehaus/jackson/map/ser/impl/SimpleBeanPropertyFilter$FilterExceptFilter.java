package com.flurry.org.codehaus.jackson.map.ser.impl;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;
import com.flurry.org.codehaus.jackson.map.ser.BeanPropertyWriter;
import java.util.Set;

public class SimpleBeanPropertyFilter$FilterExceptFilter extends SimpleBeanPropertyFilter {
  protected final Set _propertiesToInclude;
  
  public SimpleBeanPropertyFilter$FilterExceptFilter(Set paramSet) {
    this._propertiesToInclude = paramSet;
  }
  
  public void serializeAsField(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider, BeanPropertyWriter paramBeanPropertyWriter) {
    if (this._propertiesToInclude.contains(paramBeanPropertyWriter.getName()))
      paramBeanPropertyWriter.serializeAsField(paramObject, paramJsonGenerator, paramSerializerProvider); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\impl\SimpleBeanPropertyFilter$FilterExceptFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */