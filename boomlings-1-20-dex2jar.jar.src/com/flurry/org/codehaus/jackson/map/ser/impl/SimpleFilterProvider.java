package com.flurry.org.codehaus.jackson.map.ser.impl;

import com.flurry.org.codehaus.jackson.map.ser.BeanPropertyFilter;
import com.flurry.org.codehaus.jackson.map.ser.FilterProvider;
import java.util.HashMap;
import java.util.Map;

public class SimpleFilterProvider extends FilterProvider {
  protected boolean _cfgFailOnUnknownId = true;
  
  protected BeanPropertyFilter _defaultFilter;
  
  protected final Map _filtersById;
  
  public SimpleFilterProvider() {
    this(new HashMap<Object, Object>());
  }
  
  public SimpleFilterProvider(Map paramMap) {
    this._filtersById = paramMap;
  }
  
  public SimpleFilterProvider addFilter(String paramString, BeanPropertyFilter paramBeanPropertyFilter) {
    this._filtersById.put(paramString, paramBeanPropertyFilter);
    return this;
  }
  
  public BeanPropertyFilter findFilter(Object paramObject) {
    BeanPropertyFilter beanPropertyFilter2 = (BeanPropertyFilter)this._filtersById.get(paramObject);
    BeanPropertyFilter beanPropertyFilter1 = beanPropertyFilter2;
    if (beanPropertyFilter2 == null) {
      beanPropertyFilter2 = this._defaultFilter;
      beanPropertyFilter1 = beanPropertyFilter2;
      if (beanPropertyFilter2 == null) {
        beanPropertyFilter1 = beanPropertyFilter2;
        if (this._cfgFailOnUnknownId)
          throw new IllegalArgumentException("No filter configured with id '" + paramObject + "' (type " + paramObject.getClass().getName() + ")"); 
      } 
    } 
    return beanPropertyFilter1;
  }
  
  public BeanPropertyFilter getDefaultFilter() {
    return this._defaultFilter;
  }
  
  public BeanPropertyFilter removeFilter(String paramString) {
    return (BeanPropertyFilter)this._filtersById.remove(paramString);
  }
  
  public SimpleFilterProvider setDefaultFilter(BeanPropertyFilter paramBeanPropertyFilter) {
    this._defaultFilter = paramBeanPropertyFilter;
    return this;
  }
  
  public SimpleFilterProvider setFailOnUnknownId(boolean paramBoolean) {
    this._cfgFailOnUnknownId = paramBoolean;
    return this;
  }
  
  public boolean willFailOnUnknownId() {
    return this._cfgFailOnUnknownId;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ser\impl\SimpleFilterProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */