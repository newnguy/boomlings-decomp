package com.flurry.org.codehaus.jackson.map.deser.impl;

import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.DeserializationContext;
import com.flurry.org.codehaus.jackson.map.deser.SettableBeanProperty;
import com.flurry.org.codehaus.jackson.util.TokenBuffer;
import java.util.HashMap;

public class ExternalTypeHandler {
  private final HashMap _nameToPropertyIndex;
  
  private final ExternalTypeHandler$ExtTypedProperty[] _properties;
  
  private final TokenBuffer[] _tokens;
  
  private final String[] _typeIds;
  
  protected ExternalTypeHandler(ExternalTypeHandler paramExternalTypeHandler) {
    this._properties = paramExternalTypeHandler._properties;
    this._nameToPropertyIndex = paramExternalTypeHandler._nameToPropertyIndex;
    int i = this._properties.length;
    this._typeIds = new String[i];
    this._tokens = new TokenBuffer[i];
  }
  
  protected ExternalTypeHandler(ExternalTypeHandler$ExtTypedProperty[] paramArrayOfExternalTypeHandler$ExtTypedProperty, HashMap paramHashMap, String[] paramArrayOfString, TokenBuffer[] paramArrayOfTokenBuffer) {
    this._properties = paramArrayOfExternalTypeHandler$ExtTypedProperty;
    this._nameToPropertyIndex = paramHashMap;
    this._typeIds = paramArrayOfString;
    this._tokens = paramArrayOfTokenBuffer;
  }
  
  protected final void _deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject, int paramInt) {
    TokenBuffer tokenBuffer = new TokenBuffer(paramJsonParser.getCodec());
    tokenBuffer.writeStartArray();
    tokenBuffer.writeString(this._typeIds[paramInt]);
    JsonParser jsonParser = this._tokens[paramInt].asParser(paramJsonParser);
    jsonParser.nextToken();
    tokenBuffer.copyCurrentStructure(jsonParser);
    tokenBuffer.writeEndArray();
    paramJsonParser = tokenBuffer.asParser(paramJsonParser);
    paramJsonParser.nextToken();
    this._properties[paramInt].getProperty().deserializeAndSet(paramJsonParser, paramDeserializationContext, paramObject);
  }
  
  public Object complete(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, Object paramObject) {
    byte b = 0;
    int i = this._properties.length;
    while (b < i) {
      if (this._typeIds[b] == null) {
        if (this._tokens[b] != null)
          throw paramDeserializationContext.mappingException("Missing external type id property '" + this._properties[b].getTypePropertyName()); 
      } else {
        SettableBeanProperty settableBeanProperty;
        if (this._tokens[b] == null) {
          settableBeanProperty = this._properties[b].getProperty();
          throw paramDeserializationContext.mappingException("Missing property '" + settableBeanProperty.getName() + "' for external type id '" + this._properties[b].getTypePropertyName());
        } 
        _deserialize((JsonParser)settableBeanProperty, paramDeserializationContext, paramObject, b);
      } 
      b++;
    } 
    return paramObject;
  }
  
  public boolean handleToken(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext, String paramString, Object paramObject) {
    boolean bool1 = false;
    boolean bool2 = false;
    Integer integer = (Integer)this._nameToPropertyIndex.get(paramString);
    if (integer != null) {
      boolean bool;
      int i = integer.intValue();
      if (this._properties[i].hasTypePropertyName(paramString)) {
        this._typeIds[i] = paramJsonParser.getText();
        paramJsonParser.skipChildren();
        if (paramObject != null && this._tokens[i] != null) {
          bool = true;
        } else {
          bool = false;
        } 
      } else {
        TokenBuffer tokenBuffer = new TokenBuffer(paramJsonParser.getCodec());
        tokenBuffer.copyCurrentStructure(paramJsonParser);
        this._tokens[i] = tokenBuffer;
        bool = bool1;
        if (paramObject != null) {
          bool = bool1;
          if (this._typeIds[i] != null)
            bool = true; 
        } 
      } 
      if (bool) {
        _deserialize(paramJsonParser, paramDeserializationContext, paramObject, i);
        this._typeIds[i] = null;
        this._tokens[i] = null;
      } 
      bool2 = true;
    } 
    return bool2;
  }
  
  public ExternalTypeHandler start() {
    return new ExternalTypeHandler(this);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\deser\impl\ExternalTypeHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */