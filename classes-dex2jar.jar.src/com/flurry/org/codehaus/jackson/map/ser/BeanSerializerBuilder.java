package com.flurry.org.codehaus.jackson.map.ser;

import com.flurry.org.codehaus.jackson.map.JsonSerializer;
import com.flurry.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import java.util.List;

public class BeanSerializerBuilder {
  private static final BeanPropertyWriter[] NO_PROPERTIES = new BeanPropertyWriter[0];
  
  protected AnyGetterWriter _anyGetter;
  
  protected final BasicBeanDescription _beanDesc;
  
  protected Object _filterId;
  
  protected BeanPropertyWriter[] _filteredProperties;
  
  protected List _properties;
  
  public BeanSerializerBuilder(BasicBeanDescription paramBasicBeanDescription) {
    this._beanDesc = paramBasicBeanDescription;
  }
  
  protected BeanSerializerBuilder(BeanSerializerBuilder paramBeanSerializerBuilder) {
    this._beanDesc = paramBeanSerializerBuilder._beanDesc;
    this._properties = paramBeanSerializerBuilder._properties;
    this._filteredProperties = paramBeanSerializerBuilder._filteredProperties;
    this._anyGetter = paramBeanSerializerBuilder._anyGetter;
    this._filterId = paramBeanSerializerBuilder._filterId;
  }
  
  public JsonSerializer build() {
    BeanPropertyWriter[] arrayOfBeanPropertyWriter;
    if (this._properties == null || this._properties.isEmpty()) {
      if (this._anyGetter == null)
        return null; 
      arrayOfBeanPropertyWriter = NO_PROPERTIES;
    } else {
      arrayOfBeanPropertyWriter = (BeanPropertyWriter[])this._properties.toArray((Object[])new BeanPropertyWriter[this._properties.size()]);
    } 
    return (JsonSerializer)new BeanSerializer(this._beanDesc.getType(), arrayOfBeanPropertyWriter, this._filteredProperties, this._anyGetter, this._filterId);
  }
  
  public BeanSerializer createDummy() {
    return BeanSerializer.createDummy(this._beanDesc.getBeanClass());
  }
  
  public BasicBeanDescription getBeanDescription() {
    return this._beanDesc;
  }
  
  public BeanPropertyWriter[] getFilteredProperties() {
    return this._filteredProperties;
  }
  
  public List getProperties() {
    return this._properties;
  }
  
  public boolean hasProperties() {
    return (this._properties != null && this._properties.size() > 0);
  }
  
  public void setAnyGetter(AnyGetterWriter paramAnyGetterWriter) {
    this._anyGetter = paramAnyGetterWriter;
  }
  
  public void setFilterId(Object paramObject) {
    this._filterId = paramObject;
  }
  
  public void setFilteredProperties(BeanPropertyWriter[] paramArrayOfBeanPropertyWriter) {
    this._filteredProperties = paramArrayOfBeanPropertyWriter;
  }
  
  public void setProperties(List paramList) {
    this._properties = paramList;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\BeanSerializerBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */