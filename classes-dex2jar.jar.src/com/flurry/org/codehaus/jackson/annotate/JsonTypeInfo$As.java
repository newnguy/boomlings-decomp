package com.flurry.org.codehaus.jackson.annotate;

public enum JsonTypeInfo$As {
  EXTERNAL_PROPERTY, PROPERTY, WRAPPER_ARRAY, WRAPPER_OBJECT;
  
  private static final JsonTypeInfo$As[] $VALUES;
  
  static {
    WRAPPER_ARRAY = new JsonTypeInfo$As("WRAPPER_ARRAY", 2);
    EXTERNAL_PROPERTY = new JsonTypeInfo$As("EXTERNAL_PROPERTY", 3);
    $VALUES = new JsonTypeInfo$As[] { PROPERTY, WRAPPER_OBJECT, WRAPPER_ARRAY, EXTERNAL_PROPERTY };
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\annotate\JsonTypeInfo$As.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */