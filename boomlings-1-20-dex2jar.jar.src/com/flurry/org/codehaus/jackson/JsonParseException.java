package com.flurry.org.codehaus.jackson;

public class JsonParseException extends JsonProcessingException {
  static final long serialVersionUID = 123L;
  
  public JsonParseException(String paramString, JsonLocation paramJsonLocation) {
    super(paramString, paramJsonLocation);
  }
  
  public JsonParseException(String paramString, JsonLocation paramJsonLocation, Throwable paramThrowable) {
    super(paramString, paramJsonLocation, paramThrowable);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\JsonParseException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */