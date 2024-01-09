package com.flurry.org.codehaus.jackson.map.deser.impl;

import com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty;
import java.util.ArrayList;
import java.util.HashMap;

public class ExternalTypeHandler$Builder {
  private final HashMap _nameToPropertyIndex = new HashMap<Object, Object>();
  
  private final ArrayList _properties = new ArrayList();
  
  public void addExternal(SettableBeanProperty paramSettableBeanProperty, String paramString) {
    Integer integer = Integer.valueOf(this._properties.size());
    this._properties.add(new ExternalTypeHandler$ExtTypedProperty(paramSettableBeanProperty, paramString));
    this._nameToPropertyIndex.put(paramSettableBeanProperty.getName(), integer);
    this._nameToPropertyIndex.put(paramString, integer);
  }
  
  public ExternalTypeHandler build() {
    return new ExternalTypeHandler((ExternalTypeHandler$ExtTypedProperty[])this._properties.toArray((Object[])new ExternalTypeHandler$ExtTypedProperty[this._properties.size()]), this._nameToPropertyIndex, null, null);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\impl\ExternalTypeHandler$Builder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */