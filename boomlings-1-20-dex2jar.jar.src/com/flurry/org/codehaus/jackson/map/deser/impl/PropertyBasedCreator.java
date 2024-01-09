package com.flurry.org.codehaus.jackson.map.deser.impl;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.JsonDeserializer;
import com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty;
import com.flurry.org.codehaus.jackson.map.deser.ValueInstantiator;
import com.flurry.org.codehaus.jackson.map.util.ClassUtil;
import java.util.Collection;
import java.util.HashMap;

public final class PropertyBasedCreator {
  protected Object[] _defaultValues;
  
  protected final HashMap _properties;
  
  protected final SettableBeanProperty[] _propertiesWithInjectables;
  
  protected final ValueInstantiator _valueInstantiator;
  
  public PropertyBasedCreator(ValueInstantiator paramValueInstantiator) {
    SettableBeanProperty[] arrayOfSettableBeanProperty1;
    this._valueInstantiator = paramValueInstantiator;
    this._properties = new HashMap<Object, Object>();
    SettableBeanProperty[] arrayOfSettableBeanProperty2 = paramValueInstantiator.getFromObjectArguments();
    int i = arrayOfSettableBeanProperty2.length;
    byte b = 0;
    paramValueInstantiator = null;
    while (b < i) {
      SettableBeanProperty[] arrayOfSettableBeanProperty;
      SettableBeanProperty settableBeanProperty = arrayOfSettableBeanProperty2[b];
      this._properties.put(settableBeanProperty.getName(), settableBeanProperty);
      Object[] arrayOfObject1 = arrayOfObject;
      if (settableBeanProperty.getType().isPrimitive()) {
        arrayOfObject1 = arrayOfObject;
        if (arrayOfObject == null)
          arrayOfObject1 = new Object[i]; 
        arrayOfObject1[b] = ClassUtil.defaultValue(settableBeanProperty.getType().getRawClass());
      } 
      ValueInstantiator valueInstantiator = paramValueInstantiator;
      if (settableBeanProperty.getInjectableValueId() != null) {
        valueInstantiator = paramValueInstantiator;
        if (paramValueInstantiator == null)
          arrayOfSettableBeanProperty = new SettableBeanProperty[i]; 
        arrayOfSettableBeanProperty[b] = settableBeanProperty;
      } 
      b++;
      arrayOfSettableBeanProperty1 = arrayOfSettableBeanProperty;
      arrayOfObject = arrayOfObject1;
    } 
    this._defaultValues = arrayOfObject;
    this._propertiesWithInjectables = arrayOfSettableBeanProperty1;
  }
  
  public void assignDeserializer(SettableBeanProperty paramSettableBeanProperty, JsonDeserializer paramJsonDeserializer) {
    paramSettableBeanProperty = paramSettableBeanProperty.withValueDeserializer(paramJsonDeserializer);
    this._properties.put(paramSettableBeanProperty.getName(), paramSettableBeanProperty);
    Object object = paramJsonDeserializer.getNullValue();
    if (object != null) {
      if (this._defaultValues == null)
        this._defaultValues = new Object[this._properties.size()]; 
      this._defaultValues[paramSettableBeanProperty.getPropertyIndex()] = object;
    } 
  }
  
  public Object build(PropertyValueBuffer paramPropertyValueBuffer) {
    Object object = this._valueInstantiator.createFromObjectWith(paramPropertyValueBuffer.getParameters(this._defaultValues));
    for (PropertyValue propertyValue = paramPropertyValueBuffer.buffered(); propertyValue != null; propertyValue = propertyValue.next)
      propertyValue.assign(object); 
    return object;
  }
  
  public SettableBeanProperty findCreatorProperty(String paramString) {
    return (SettableBeanProperty)this._properties.get(paramString);
  }
  
  public Collection getCreatorProperties() {
    return this._properties.values();
  }
  
  public PropertyValueBuffer startBuilding(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {
    PropertyValueBuffer propertyValueBuffer = new PropertyValueBuffer(paramJsonParser, paramDeserializationContext, this._properties.size());
    if (this._propertiesWithInjectables != null)
      propertyValueBuffer.inject(this._propertiesWithInjectables); 
    return propertyValueBuffer;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\impl\PropertyBasedCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */