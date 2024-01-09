package com.flurry.org.apache.avro;

public enum Schema$Type {
  ARRAY, BOOLEAN, BYTES, DOUBLE, ENUM, FIXED, FLOAT, INT, LONG, MAP, NULL, RECORD, STRING, UNION;
  
  private static final Schema$Type[] $VALUES;
  
  private String name = name().toLowerCase();
  
  static {
    ENUM = new Schema$Type("ENUM", 1);
    ARRAY = new Schema$Type("ARRAY", 2);
    MAP = new Schema$Type("MAP", 3);
    UNION = new Schema$Type("UNION", 4);
    FIXED = new Schema$Type("FIXED", 5);
    STRING = new Schema$Type("STRING", 6);
    BYTES = new Schema$Type("BYTES", 7);
    INT = new Schema$Type("INT", 8);
    LONG = new Schema$Type("LONG", 9);
    FLOAT = new Schema$Type("FLOAT", 10);
    DOUBLE = new Schema$Type("DOUBLE", 11);
    BOOLEAN = new Schema$Type("BOOLEAN", 12);
    NULL = new Schema$Type("NULL", 13);
    $VALUES = new Schema$Type[] { 
        RECORD, ENUM, ARRAY, MAP, UNION, FIXED, STRING, BYTES, INT, LONG, 
        FLOAT, DOUBLE, BOOLEAN, NULL };
  }
  
  public String getName() {
    return this.name;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\Schema$Type.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */