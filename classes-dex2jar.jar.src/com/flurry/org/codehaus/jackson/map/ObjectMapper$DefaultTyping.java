package com.flurry.org.codehaus.jackson.map;

public enum ObjectMapper$DefaultTyping {
  JAVA_LANG_OBJECT, NON_CONCRETE_AND_ARRAYS, NON_FINAL, OBJECT_AND_NON_CONCRETE;
  
  private static final ObjectMapper$DefaultTyping[] $VALUES;
  
  static {
    NON_CONCRETE_AND_ARRAYS = new ObjectMapper$DefaultTyping("NON_CONCRETE_AND_ARRAYS", 2);
    NON_FINAL = new ObjectMapper$DefaultTyping("NON_FINAL", 3);
    $VALUES = new ObjectMapper$DefaultTyping[] { JAVA_LANG_OBJECT, OBJECT_AND_NON_CONCRETE, NON_CONCRETE_AND_ARRAYS, NON_FINAL };
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\ObjectMapper$DefaultTyping.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */