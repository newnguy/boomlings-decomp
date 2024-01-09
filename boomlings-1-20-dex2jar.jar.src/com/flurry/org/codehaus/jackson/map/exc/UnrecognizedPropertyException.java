package com.flurry.org.codehaus.jackson.map.exc;

import com.flurry.org.codehaus.jackson.JsonLocation;
import com.flurry.org.codehaus.jackson.JsonParser;
import com.flurry.org.codehaus.jackson.map.JsonMappingException;

public class UnrecognizedPropertyException extends JsonMappingException {
  private static final long serialVersionUID = 1L;
  
  protected final Class _referringClass;
  
  protected final String _unrecognizedPropertyName;
  
  public UnrecognizedPropertyException(String paramString1, JsonLocation paramJsonLocation, Class paramClass, String paramString2) {
    super(paramString1, paramJsonLocation);
    this._referringClass = paramClass;
    this._unrecognizedPropertyName = paramString2;
  }
  
  public static UnrecognizedPropertyException from(JsonParser paramJsonParser, Object paramObject, String paramString) {
    if (paramObject == null)
      throw new IllegalArgumentException(); 
    if (paramObject instanceof Class) {
      Class clazz1 = (Class)paramObject;
      unrecognizedPropertyException = new UnrecognizedPropertyException("Unrecognized field \"" + paramString + "\" (Class " + clazz1.getName() + "), not marked as ignorable", paramJsonParser.getCurrentLocation(), clazz1, paramString);
      unrecognizedPropertyException.prependPath(paramObject, paramString);
      return unrecognizedPropertyException;
    } 
    Class<?> clazz = paramObject.getClass();
    UnrecognizedPropertyException unrecognizedPropertyException = new UnrecognizedPropertyException("Unrecognized field \"" + paramString + "\" (Class " + clazz.getName() + "), not marked as ignorable", unrecognizedPropertyException.getCurrentLocation(), clazz, paramString);
    unrecognizedPropertyException.prependPath(paramObject, paramString);
    return unrecognizedPropertyException;
  }
  
  public Class getReferringClass() {
    return this._referringClass;
  }
  
  public String getUnrecognizedPropertyName() {
    return this._unrecognizedPropertyName;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\exc\UnrecognizedPropertyException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */