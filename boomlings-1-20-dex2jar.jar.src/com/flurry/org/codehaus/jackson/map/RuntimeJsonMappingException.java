package com.flurry.org.codehaus.jackson.map;

public class RuntimeJsonMappingException extends RuntimeException {
  public RuntimeJsonMappingException(JsonMappingException paramJsonMappingException) {
    super((Throwable)paramJsonMappingException);
  }
  
  public RuntimeJsonMappingException(String paramString) {
    super(paramString);
  }
  
  public RuntimeJsonMappingException(String paramString, JsonMappingException paramJsonMappingException) {
    super(paramString, (Throwable)paramJsonMappingException);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\RuntimeJsonMappingException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */