package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.SerializerProvider;

final class FilteredBeanPropertyWriter$SingleView extends BeanPropertyWriter {
  protected final BeanPropertyWriter _delegate;
  
  protected final Class _view;
  
  protected FilteredBeanPropertyWriter$SingleView(BeanPropertyWriter paramBeanPropertyWriter, Class paramClass) {
    super(paramBeanPropertyWriter);
    this._delegate = paramBeanPropertyWriter;
    this._view = paramClass;
  }
  
  public void serializeAsField(Object paramObject, JsonGenerator paramJsonGenerator, SerializerProvider paramSerializerProvider) {
    Class<?> clazz = paramSerializerProvider.getSerializationView();
    if (clazz == null || this._view.isAssignableFrom(clazz))
      this._delegate.serializeAsField(paramObject, paramJsonGenerator, paramSerializerProvider); 
  }
  
  public BeanPropertyWriter withSerializer(JsonSerializer paramJsonSerializer) {
    return new FilteredBeanPropertyWriter$SingleView(this._delegate.withSerializer(paramJsonSerializer), this._view);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\FilteredBeanPropertyWriter$SingleView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */