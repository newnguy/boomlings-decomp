package com.flurry.org.codehaus.jackson.impl;

import com.flurry.org.codehaus.jackson.JsonStreamContext;

public class JsonWriteContext extends JsonStreamContext {
  public static final int STATUS_EXPECT_NAME = 5;
  
  public static final int STATUS_EXPECT_VALUE = 4;
  
  public static final int STATUS_OK_AFTER_COLON = 2;
  
  public static final int STATUS_OK_AFTER_COMMA = 1;
  
  public static final int STATUS_OK_AFTER_SPACE = 3;
  
  public static final int STATUS_OK_AS_IS = 0;
  
  protected JsonWriteContext _child = null;
  
  protected String _currentName;
  
  protected final JsonWriteContext _parent;
  
  protected JsonWriteContext(int paramInt, JsonWriteContext paramJsonWriteContext) {
    this._type = paramInt;
    this._parent = paramJsonWriteContext;
    this._index = -1;
  }
  
  public static JsonWriteContext createRootContext() {
    return new JsonWriteContext(0, null);
  }
  
  private final JsonWriteContext reset(int paramInt) {
    this._type = paramInt;
    this._index = -1;
    this._currentName = null;
    return this;
  }
  
  protected final void appendDesc(StringBuilder paramStringBuilder) {
    if (this._type == 2) {
      paramStringBuilder.append('{');
      if (this._currentName != null) {
        paramStringBuilder.append('"');
        paramStringBuilder.append(this._currentName);
        paramStringBuilder.append('"');
      } else {
        paramStringBuilder.append('?');
      } 
      paramStringBuilder.append('}');
      return;
    } 
    if (this._type == 1) {
      paramStringBuilder.append('[');
      paramStringBuilder.append(getCurrentIndex());
      paramStringBuilder.append(']');
      return;
    } 
    paramStringBuilder.append("/");
  }
  
  public final JsonWriteContext createChildArrayContext() {
    null = this._child;
    if (null == null) {
      null = new JsonWriteContext(1, this);
      this._child = null;
      return null;
    } 
    return null.reset(1);
  }
  
  public final JsonWriteContext createChildObjectContext() {
    null = this._child;
    if (null == null) {
      null = new JsonWriteContext(2, this);
      this._child = null;
      return null;
    } 
    return null.reset(2);
  }
  
  public final String getCurrentName() {
    return this._currentName;
  }
  
  public final JsonWriteContext getParent() {
    return this._parent;
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder(64);
    appendDesc(stringBuilder);
    return stringBuilder.toString();
  }
  
  public final int writeFieldName(String paramString) {
    byte b = 4;
    null = b;
    if (this._type == 2) {
      if (this._currentName != null)
        return b; 
    } else {
      return null;
    } 
    this._currentName = paramString;
    return (this._index < 0) ? 0 : 1;
  }
  
  public final int writeValue() {
    byte b = 0;
    if (this._type == 2) {
      if (this._currentName == null)
        return 5; 
      this._currentName = null;
      this._index++;
      return 2;
    } 
    if (this._type == 1) {
      int i = this._index;
      this._index++;
      if (i >= 0)
        b = 1; 
      return b;
    } 
    this._index++;
    if (this._index != 0)
      b = 3; 
    return b;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\impl\JsonWriteContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */