package com.flurry.org.codehaus.jackson.map.annotate;

public enum JsonSerialize$Inclusion {
  ALWAYS, NON_DEFAULT, NON_EMPTY, NON_NULL;
  
  private static final JsonSerialize$Inclusion[] $VALUES;
  
  static {
    NON_DEFAULT = new JsonSerialize$Inclusion("NON_DEFAULT", 2);
    NON_EMPTY = new JsonSerialize$Inclusion("NON_EMPTY", 3);
    $VALUES = new JsonSerialize$Inclusion[] { ALWAYS, NON_NULL, NON_DEFAULT, NON_EMPTY };
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\annotate\JsonSerialize$Inclusion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */