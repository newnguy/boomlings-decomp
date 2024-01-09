package com.flurry.org.codehaus.jackson.map.deser.impl;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty;
import com.flurry.org.codehaus.jackson.util.TokenBuffer;
import java.util.ArrayList;

public class UnwrappedPropertyHandler {
  protected final ArrayList _properties = new ArrayList();
  
  public void addProperty(SettableBeanProperty paramSettableBeanProperty) {
    this._properties.add(paramSettableBeanProperty);
  }
  
  public Object processUnwrapped(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, TokenBuffer paramTokenBuffer) {
    int i = this._properties.size();
    for (byte b = 0; b < i; b++) {
      SettableBeanProperty settableBeanProperty = this._properties.get(b);
      JsonParser jsonParser = paramTokenBuffer.asParser();
      jsonParser.nextToken();
      settableBeanProperty.deserializeAndSet(jsonParser, paramDeserializationContext, paramObject);
    } 
    return paramObject;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\impl\UnwrappedPropertyHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */