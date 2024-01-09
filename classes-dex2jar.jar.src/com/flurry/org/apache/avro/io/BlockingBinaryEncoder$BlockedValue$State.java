package com.flurry.org.apache.avro.io;

public enum BlockingBinaryEncoder$BlockedValue$State {
  OVERFLOW, REGULAR, ROOT;
  
  private static final BlockingBinaryEncoder$BlockedValue$State[] $VALUES;
  
  static {
    REGULAR = new BlockingBinaryEncoder$BlockedValue$State("REGULAR", 1);
    OVERFLOW = new BlockingBinaryEncoder$BlockedValue$State("OVERFLOW", 2);
    $VALUES = new BlockingBinaryEncoder$BlockedValue$State[] { ROOT, REGULAR, OVERFLOW };
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\apache\avro\io\BlockingBinaryEncoder$BlockedValue$State.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */