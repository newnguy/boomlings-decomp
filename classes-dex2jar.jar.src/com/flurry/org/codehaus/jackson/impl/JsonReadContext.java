package com.flurry.org.codehaus.jackson.impl;

import com.flurry.org.codehaus.jackson.JsonLocation;
import com.flurry.org.codehaus.jackson.JsonStreamContext;
import com.flurry.org.codehaus.jackson.util.CharTypes;

public final class JsonReadContext extends JsonStreamContext {
  protected JsonReadContext _child = null;
  
  protected int _columnNr;
  
  protected String _currentName;
  
  protected int _lineNr;
  
  protected final JsonReadContext _parent;
  
  public JsonReadContext(JsonReadContext paramJsonReadContext, int paramInt1, int paramInt2, int paramInt3) {
    this._type = paramInt1;
    this._parent = paramJsonReadContext;
    this._lineNr = paramInt2;
    this._columnNr = paramInt3;
    this._index = -1;
  }
  
  public static JsonReadContext createRootContext() {
    return new JsonReadContext(null, 0, 1, 0);
  }
  
  public static JsonReadContext createRootContext(int paramInt1, int paramInt2) {
    return new JsonReadContext(null, 0, paramInt1, paramInt2);
  }
  
  public final JsonReadContext createChildArrayContext(int paramInt1, int paramInt2) {
    JsonReadContext jsonReadContext = this._child;
    if (jsonReadContext == null) {
      jsonReadContext = new JsonReadContext(this, 1, paramInt1, paramInt2);
      this._child = jsonReadContext;
      return jsonReadContext;
    } 
    jsonReadContext.reset(1, paramInt1, paramInt2);
    return jsonReadContext;
  }
  
  public final JsonReadContext createChildObjectContext(int paramInt1, int paramInt2) {
    JsonReadContext jsonReadContext = this._child;
    if (jsonReadContext == null) {
      jsonReadContext = new JsonReadContext(this, 2, paramInt1, paramInt2);
      this._child = jsonReadContext;
      return jsonReadContext;
    } 
    jsonReadContext.reset(2, paramInt1, paramInt2);
    return jsonReadContext;
  }
  
  public final boolean expectComma() {
    int i = this._index + 1;
    this._index = i;
    return (this._type != 0 && i > 0);
  }
  
  public final String getCurrentName() {
    return this._currentName;
  }
  
  public final JsonReadContext getParent() {
    return this._parent;
  }
  
  public final JsonLocation getStartLocation(Object paramObject) {
    return new JsonLocation(paramObject, -1L, this._lineNr, this._columnNr);
  }
  
  protected final void reset(int paramInt1, int paramInt2, int paramInt3) {
    this._type = paramInt1;
    this._index = -1;
    this._lineNr = paramInt2;
    this._columnNr = paramInt3;
    this._currentName = null;
  }
  
  public void setCurrentName(String paramString) {
    this._currentName = paramString;
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder(64);
    switch (this._type) {
      default:
        return stringBuilder.toString();
      case 0:
        stringBuilder.append("/");
      case 1:
        stringBuilder.append('[');
        stringBuilder.append(getCurrentIndex());
        stringBuilder.append(']');
      case 2:
        break;
    } 
    stringBuilder.append('{');
    if (this._currentName != null) {
      stringBuilder.append('"');
      CharTypes.appendQuoted(stringBuilder, this._currentName);
      stringBuilder.append('"');
    } else {
      stringBuilder.append('?');
    } 
    stringBuilder.append('}');
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\impl\JsonReadContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */