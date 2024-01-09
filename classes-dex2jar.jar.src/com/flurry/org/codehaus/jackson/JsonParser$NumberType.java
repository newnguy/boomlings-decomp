package com.flurry.org.codehaus.jackson;

public enum JsonParser$NumberType {
  BIG_DECIMAL, BIG_INTEGER, DOUBLE, FLOAT, INT, LONG;
  
  private static final JsonParser$NumberType[] $VALUES;
  
  static {
    BIG_INTEGER = new JsonParser$NumberType("BIG_INTEGER", 2);
    FLOAT = new JsonParser$NumberType("FLOAT", 3);
    DOUBLE = new JsonParser$NumberType("DOUBLE", 4);
    BIG_DECIMAL = new JsonParser$NumberType("BIG_DECIMAL", 5);
    $VALUES = new JsonParser$NumberType[] { INT, LONG, BIG_INTEGER, FLOAT, DOUBLE, BIG_DECIMAL };
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\JsonParser$NumberType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */