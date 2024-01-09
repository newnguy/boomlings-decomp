package com.flurry.org.codehaus.jackson.map.deser.impl;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.BeanProperty;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.deser.SettableAnyProperty;
import com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty;

public final class PropertyValueBuffer {
  private PropertyValue _buffered;
  
  final DeserializationContext _context;
  
  final Object[] _creatorParameters;
  
  private int _paramsNeeded;
  
  final JsonParser _parser;
  
  public PropertyValueBuffer(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, int paramInt) {
    this._parser = paramJsonParser;
    this._context = paramDeserializationContext;
    this._paramsNeeded = paramInt;
    this._creatorParameters = new Object[paramInt];
  }
  
  public boolean assignParameter(int paramInt, Object paramObject) {
    this._creatorParameters[paramInt] = paramObject;
    paramInt = this._paramsNeeded - 1;
    this._paramsNeeded = paramInt;
    return (paramInt <= 0);
  }
  
  public void bufferAnyProperty(SettableAnyProperty paramSettableAnyProperty, String paramString, Object paramObject) {
    this._buffered = new PropertyValue$Any(this._buffered, paramObject, paramSettableAnyProperty, paramString);
  }
  
  public void bufferMapProperty(Object paramObject1, Object paramObject2) {
    this._buffered = new PropertyValue$Map(this._buffered, paramObject2, paramObject1);
  }
  
  public void bufferProperty(SettableBeanProperty paramSettableBeanProperty, Object paramObject) {
    this._buffered = new PropertyValue$Regular(this._buffered, paramObject, paramSettableBeanProperty);
  }
  
  protected PropertyValue buffered() {
    return this._buffered;
  }
  
  protected final Object[] getParameters(Object[] paramArrayOfObject) {
    if (paramArrayOfObject != null) {
      byte b = 0;
      int i = this._creatorParameters.length;
      while (b < i) {
        if (this._creatorParameters[b] == null) {
          Object object = paramArrayOfObject[b];
          if (object != null)
            this._creatorParameters[b] = object; 
        } 
        b++;
      } 
    } 
    return this._creatorParameters;
  }
  
  public void inject(SettableBeanProperty[] paramArrayOfSettableBeanProperty) {
    byte b = 0;
    int i = paramArrayOfSettableBeanProperty.length;
    while (b < i) {
      SettableBeanProperty settableBeanProperty = paramArrayOfSettableBeanProperty[b];
      if (settableBeanProperty != null)
        this._creatorParameters[b] = this._context.findInjectableValue(settableBeanProperty.getInjectableValueId(), (BeanProperty)settableBeanProperty, null); 
      b++;
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\impl\PropertyValueBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */