package com.flurry.org.codehaus.jackson;

public class JsonGenerationException extends JsonProcessingException {
  static final long serialVersionUID = 123L;
  
  public JsonGenerationException(String paramString) {
    super(paramString, (JsonLocation)null);
  }
  
  public JsonGenerationException(String paramString, Throwable paramThrowable) {
    super(paramString, (JsonLocation)null, paramThrowable);
  }
  
  public JsonGenerationException(Throwable paramThrowable) {
    super(paramThrowable);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\JsonGenerationException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */