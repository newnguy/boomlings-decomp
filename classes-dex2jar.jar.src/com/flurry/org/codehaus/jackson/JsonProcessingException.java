package com.flurry.org.codehaus.jackson;

import java.io.IOException;

public class JsonProcessingException extends IOException {
  static final long serialVersionUID = 123L;
  
  protected JsonLocation mLocation;
  
  protected JsonProcessingException(String paramString) {
    super(paramString);
  }
  
  protected JsonProcessingException(String paramString, JsonLocation paramJsonLocation) {
    this(paramString, paramJsonLocation, null);
  }
  
  protected JsonProcessingException(String paramString, JsonLocation paramJsonLocation, Throwable paramThrowable) {
    super(paramString);
    if (paramThrowable != null)
      initCause(paramThrowable); 
    this.mLocation = paramJsonLocation;
  }
  
  protected JsonProcessingException(String paramString, Throwable paramThrowable) {
    this(paramString, (JsonLocation)null, paramThrowable);
  }
  
  protected JsonProcessingException(Throwable paramThrowable) {
    this((String)null, (JsonLocation)null, paramThrowable);
  }
  
  public JsonLocation getLocation() {
    return this.mLocation;
  }
  
  public String getMessage() {
    String str2 = super.getMessage();
    String str1 = str2;
    if (str2 == null)
      str1 = "N/A"; 
    JsonLocation jsonLocation = getLocation();
    str2 = str1;
    if (jsonLocation != null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str1);
      stringBuilder.append('\n');
      stringBuilder.append(" at ");
      stringBuilder.append(jsonLocation.toString());
      str2 = stringBuilder.toString();
    } 
    return str2;
  }
  
  public String toString() {
    return getClass().getName() + ": " + getMessage();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\JsonProcessingException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */