package com.flurry.org.apache.avro.io.parsing;

public enum Symbol$Kind {
  ALTERNATIVE, EXPLICIT_ACTION, IMPLICIT_ACTION, REPEATER, ROOT, SEQUENCE, TERMINAL;
  
  private static final Symbol$Kind[] $VALUES;
  
  static {
    ROOT = new Symbol$Kind("ROOT", 1);
    SEQUENCE = new Symbol$Kind("SEQUENCE", 2);
    REPEATER = new Symbol$Kind("REPEATER", 3);
    ALTERNATIVE = new Symbol$Kind("ALTERNATIVE", 4);
    IMPLICIT_ACTION = new Symbol$Kind("IMPLICIT_ACTION", 5);
    EXPLICIT_ACTION = new Symbol$Kind("EXPLICIT_ACTION", 6);
    $VALUES = new Symbol$Kind[] { TERMINAL, ROOT, SEQUENCE, REPEATER, ALTERNATIVE, IMPLICIT_ACTION, EXPLICIT_ACTION };
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\apache\avro\io\parsing\Symbol$Kind.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */