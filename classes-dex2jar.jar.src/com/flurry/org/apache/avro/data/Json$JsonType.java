package com.flurry.org.apache.avro.data;

enum Json$JsonType {
  ARRAY, BOOLEAN, DOUBLE, LONG, NULL, OBJECT, STRING;
  
  private static final Json$JsonType[] $VALUES;
  
  static {
    DOUBLE = new Json$JsonType("DOUBLE", 1);
    STRING = new Json$JsonType("STRING", 2);
    BOOLEAN = new Json$JsonType("BOOLEAN", 3);
    NULL = new Json$JsonType("NULL", 4);
    ARRAY = new Json$JsonType("ARRAY", 5);
    OBJECT = new Json$JsonType("OBJECT", 6);
    $VALUES = new Json$JsonType[] { LONG, DOUBLE, STRING, BOOLEAN, NULL, ARRAY, OBJECT };
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\data\Json$JsonType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */