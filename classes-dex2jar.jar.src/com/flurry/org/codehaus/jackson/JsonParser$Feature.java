package com.flurry.org.codehaus.jackson;

public enum JsonParser$Feature {
  ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER,
  ALLOW_COMMENTS,
  ALLOW_NON_NUMERIC_NUMBERS,
  ALLOW_NUMERIC_LEADING_ZEROS,
  ALLOW_SINGLE_QUOTES,
  ALLOW_UNQUOTED_CONTROL_CHARS,
  ALLOW_UNQUOTED_FIELD_NAMES,
  AUTO_CLOSE_SOURCE(true),
  CANONICALIZE_FIELD_NAMES(true),
  INTERN_FIELD_NAMES(true);
  
  private static final JsonParser$Feature[] $VALUES;
  
  final boolean _defaultState;
  
  static {
    ALLOW_COMMENTS = new JsonParser$Feature("ALLOW_COMMENTS", 1, false);
    ALLOW_UNQUOTED_FIELD_NAMES = new JsonParser$Feature("ALLOW_UNQUOTED_FIELD_NAMES", 2, false);
    ALLOW_SINGLE_QUOTES = new JsonParser$Feature("ALLOW_SINGLE_QUOTES", 3, false);
    ALLOW_UNQUOTED_CONTROL_CHARS = new JsonParser$Feature("ALLOW_UNQUOTED_CONTROL_CHARS", 4, false);
    ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER = new JsonParser$Feature("ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER", 5, false);
    ALLOW_NUMERIC_LEADING_ZEROS = new JsonParser$Feature("ALLOW_NUMERIC_LEADING_ZEROS", 6, false);
    ALLOW_NON_NUMERIC_NUMBERS = new JsonParser$Feature("ALLOW_NON_NUMERIC_NUMBERS", 7, false);
    INTERN_FIELD_NAMES = new JsonParser$Feature("INTERN_FIELD_NAMES", 8, true);
    CANONICALIZE_FIELD_NAMES = new JsonParser$Feature("CANONICALIZE_FIELD_NAMES", 9, true);
    $VALUES = new JsonParser$Feature[] { AUTO_CLOSE_SOURCE, ALLOW_COMMENTS, ALLOW_UNQUOTED_FIELD_NAMES, ALLOW_SINGLE_QUOTES, ALLOW_UNQUOTED_CONTROL_CHARS, ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, ALLOW_NUMERIC_LEADING_ZEROS, ALLOW_NON_NUMERIC_NUMBERS, INTERN_FIELD_NAMES, CANONICALIZE_FIELD_NAMES };
  }
  
  JsonParser$Feature(boolean paramBoolean) {
    this._defaultState = paramBoolean;
  }
  
  public static int collectDefaults() {
    int i = 0;
    JsonParser$Feature[] arrayOfJsonParser$Feature = values();
    int j = arrayOfJsonParser$Feature.length;
    byte b = 0;
    while (b < j) {
      JsonParser$Feature jsonParser$Feature = arrayOfJsonParser$Feature[b];
      int k = i;
      if (jsonParser$Feature.enabledByDefault())
        k = i | jsonParser$Feature.getMask(); 
      b++;
      i = k;
    } 
    return i;
  }
  
  public boolean enabledByDefault() {
    return this._defaultState;
  }
  
  public boolean enabledIn(int paramInt) {
    return ((getMask() & paramInt) != 0);
  }
  
  public int getMask() {
    return 1 << ordinal();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\JsonParser$Feature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */