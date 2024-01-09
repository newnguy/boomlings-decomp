package com.flurry.org.codehaus.jackson.map;

import java.io.Serializable;

public class JsonMappingException$Reference implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected String _fieldName;
  
  protected Object _from;
  
  protected int _index = -1;
  
  protected JsonMappingException$Reference() {}
  
  public JsonMappingException$Reference(Object paramObject) {
    this._from = paramObject;
  }
  
  public JsonMappingException$Reference(Object paramObject, int paramInt) {
    this._from = paramObject;
    this._index = paramInt;
  }
  
  public JsonMappingException$Reference(Object paramObject, String paramString) {
    this._from = paramObject;
    if (paramString == null)
      throw new NullPointerException("Can not pass null fieldName"); 
    this._fieldName = paramString;
  }
  
  public String getFieldName() {
    return this._fieldName;
  }
  
  public Object getFrom() {
    return this._from;
  }
  
  public int getIndex() {
    return this._index;
  }
  
  public void setFieldName(String paramString) {
    this._fieldName = paramString;
  }
  
  public void setFrom(Object paramObject) {
    this._from = paramObject;
  }
  
  public void setIndex(int paramInt) {
    this._index = paramInt;
  }
  
  public String toString() {
    Class<?> clazz;
    StringBuilder stringBuilder = new StringBuilder();
    if (this._from instanceof Class) {
      clazz = (Class)this._from;
    } else {
      clazz = this._from.getClass();
    } 
    Package package_ = clazz.getPackage();
    if (package_ != null) {
      stringBuilder.append(package_.getName());
      stringBuilder.append('.');
    } 
    stringBuilder.append(clazz.getSimpleName());
    stringBuilder.append('[');
    if (this._fieldName != null) {
      stringBuilder.append('"');
      stringBuilder.append(this._fieldName);
      stringBuilder.append('"');
      stringBuilder.append(']');
      return stringBuilder.toString();
    } 
    if (this._index >= 0) {
      stringBuilder.append(this._index);
      stringBuilder.append(']');
      return stringBuilder.toString();
    } 
    stringBuilder.append('?');
    stringBuilder.append(']');
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\JsonMappingException$Reference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */