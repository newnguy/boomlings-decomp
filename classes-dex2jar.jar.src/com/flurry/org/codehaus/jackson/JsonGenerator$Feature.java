package com.flurry.org.codehaus.jackson;

public enum JsonGenerator$Feature {
  AUTO_CLOSE_JSON_CONTENT,
  AUTO_CLOSE_TARGET(true),
  ESCAPE_NON_ASCII(true),
  FLUSH_PASSED_TO_STREAM(true),
  QUOTE_FIELD_NAMES(true),
  QUOTE_NON_NUMERIC_NUMBERS(true),
  WRITE_NUMBERS_AS_STRINGS(true);
  
  private static final JsonGenerator$Feature[] $VALUES;
  
  final boolean _defaultState;
  
  final int _mask;
  
  static {
    AUTO_CLOSE_JSON_CONTENT = new JsonGenerator$Feature("AUTO_CLOSE_JSON_CONTENT", 1, true);
    QUOTE_FIELD_NAMES = new JsonGenerator$Feature("QUOTE_FIELD_NAMES", 2, true);
    QUOTE_NON_NUMERIC_NUMBERS = new JsonGenerator$Feature("QUOTE_NON_NUMERIC_NUMBERS", 3, true);
    WRITE_NUMBERS_AS_STRINGS = new JsonGenerator$Feature("WRITE_NUMBERS_AS_STRINGS", 4, false);
    FLUSH_PASSED_TO_STREAM = new JsonGenerator$Feature("FLUSH_PASSED_TO_STREAM", 5, true);
    ESCAPE_NON_ASCII = new JsonGenerator$Feature("ESCAPE_NON_ASCII", 6, false);
    $VALUES = new JsonGenerator$Feature[] { AUTO_CLOSE_TARGET, AUTO_CLOSE_JSON_CONTENT, QUOTE_FIELD_NAMES, QUOTE_NON_NUMERIC_NUMBERS, WRITE_NUMBERS_AS_STRINGS, FLUSH_PASSED_TO_STREAM, ESCAPE_NON_ASCII };
  }
  
  JsonGenerator$Feature(boolean paramBoolean) {
    this._defaultState = paramBoolean;
    this._mask = 1 << ordinal();
  }
  
  public static int collectDefaults() {
    int i = 0;
    JsonGenerator$Feature[] arrayOfJsonGenerator$Feature = values();
    int j = arrayOfJsonGenerator$Feature.length;
    byte b = 0;
    while (b < j) {
      JsonGenerator$Feature jsonGenerator$Feature = arrayOfJsonGenerator$Feature[b];
      int k = i;
      if (jsonGenerator$Feature.enabledByDefault())
        k = i | jsonGenerator$Feature.getMask(); 
      b++;
      i = k;
    } 
    return i;
  }
  
  public boolean enabledByDefault() {
    return this._defaultState;
  }
  
  public int getMask() {
    return this._mask;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\JsonGenerator$Feature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */