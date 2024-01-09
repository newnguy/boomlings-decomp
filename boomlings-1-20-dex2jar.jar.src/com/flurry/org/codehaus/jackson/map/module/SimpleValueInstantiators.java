package com.flurry.org.codehaus.jackson.map.module;

import com.flurry.org.codehaus.jackson.map.BeanDescription;
import com.flurry.org.codehaus.jackson.map.DeserializationConfig;
import com.flurry.org.codehaus.jackson.map.deser.ValueInstantiator;
import com.flurry.org.codehaus.jackson.map.deser.ValueInstantiators;
import com.flurry.org.codehaus.jackson.map.type.ClassKey;
import java.util.HashMap;

public class SimpleValueInstantiators extends ValueInstantiators.Base {
  protected HashMap _classMappings = new HashMap<Object, Object>();
  
  public SimpleValueInstantiators addValueInstantiator(Class paramClass, ValueInstantiator paramValueInstantiator) {
    this._classMappings.put(new ClassKey(paramClass), paramValueInstantiator);
    return this;
  }
  
  public ValueInstantiator findValueInstantiator(DeserializationConfig paramDeserializationConfig, BeanDescription paramBeanDescription, ValueInstantiator paramValueInstantiator) {
    ValueInstantiator valueInstantiator = (ValueInstantiator)this._classMappings.get(new ClassKey(paramBeanDescription.getBeanClass()));
    if (valueInstantiator == null)
      valueInstantiator = paramValueInstantiator; 
    return valueInstantiator;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\module\SimpleValueInstantiators.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */