package com.flurry.org.codehaus.jackson.map;

public enum DeserializationConfig$Feature implements MapperConfig$ConfigFeature {
  ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,
  ACCEPT_SINGLE_VALUE_AS_ARRAY,
  AUTO_DETECT_CREATORS,
  AUTO_DETECT_FIELDS,
  AUTO_DETECT_SETTERS,
  CAN_OVERRIDE_ACCESS_MODIFIERS,
  FAIL_ON_NULL_FOR_PRIMITIVES,
  FAIL_ON_NUMBERS_FOR_ENUMS,
  FAIL_ON_UNKNOWN_PROPERTIES,
  READ_ENUMS_USING_TO_STRING,
  UNWRAP_ROOT_VALUE,
  USE_ANNOTATIONS(true),
  USE_BIG_DECIMAL_FOR_FLOATS(true),
  USE_BIG_INTEGER_FOR_INTS(true),
  USE_GETTERS_AS_SETTERS(true),
  USE_JAVA_ARRAY_FOR_JSON_ARRAY(true),
  WRAP_EXCEPTIONS(true);
  
  private static final DeserializationConfig$Feature[] $VALUES;
  
  final boolean _defaultState;
  
  static {
    AUTO_DETECT_SETTERS = new DeserializationConfig$Feature("AUTO_DETECT_SETTERS", 1, true);
    AUTO_DETECT_CREATORS = new DeserializationConfig$Feature("AUTO_DETECT_CREATORS", 2, true);
    AUTO_DETECT_FIELDS = new DeserializationConfig$Feature("AUTO_DETECT_FIELDS", 3, true);
    USE_GETTERS_AS_SETTERS = new DeserializationConfig$Feature("USE_GETTERS_AS_SETTERS", 4, true);
    CAN_OVERRIDE_ACCESS_MODIFIERS = new DeserializationConfig$Feature("CAN_OVERRIDE_ACCESS_MODIFIERS", 5, true);
    USE_BIG_DECIMAL_FOR_FLOATS = new DeserializationConfig$Feature("USE_BIG_DECIMAL_FOR_FLOATS", 6, false);
    USE_BIG_INTEGER_FOR_INTS = new DeserializationConfig$Feature("USE_BIG_INTEGER_FOR_INTS", 7, false);
    USE_JAVA_ARRAY_FOR_JSON_ARRAY = new DeserializationConfig$Feature("USE_JAVA_ARRAY_FOR_JSON_ARRAY", 8, false);
    READ_ENUMS_USING_TO_STRING = new DeserializationConfig$Feature("READ_ENUMS_USING_TO_STRING", 9, false);
    FAIL_ON_UNKNOWN_PROPERTIES = new DeserializationConfig$Feature("FAIL_ON_UNKNOWN_PROPERTIES", 10, true);
    FAIL_ON_NULL_FOR_PRIMITIVES = new DeserializationConfig$Feature("FAIL_ON_NULL_FOR_PRIMITIVES", 11, false);
    FAIL_ON_NUMBERS_FOR_ENUMS = new DeserializationConfig$Feature("FAIL_ON_NUMBERS_FOR_ENUMS", 12, false);
    WRAP_EXCEPTIONS = new DeserializationConfig$Feature("WRAP_EXCEPTIONS", 13, true);
    ACCEPT_SINGLE_VALUE_AS_ARRAY = new DeserializationConfig$Feature("ACCEPT_SINGLE_VALUE_AS_ARRAY", 14, false);
    UNWRAP_ROOT_VALUE = new DeserializationConfig$Feature("UNWRAP_ROOT_VALUE", 15, false);
    ACCEPT_EMPTY_STRING_AS_NULL_OBJECT = new DeserializationConfig$Feature("ACCEPT_EMPTY_STRING_AS_NULL_OBJECT", 16, false);
    $VALUES = new DeserializationConfig$Feature[] { 
        USE_ANNOTATIONS, AUTO_DETECT_SETTERS, AUTO_DETECT_CREATORS, AUTO_DETECT_FIELDS, USE_GETTERS_AS_SETTERS, CAN_OVERRIDE_ACCESS_MODIFIERS, USE_BIG_DECIMAL_FOR_FLOATS, USE_BIG_INTEGER_FOR_INTS, USE_JAVA_ARRAY_FOR_JSON_ARRAY, READ_ENUMS_USING_TO_STRING, 
        FAIL_ON_UNKNOWN_PROPERTIES, FAIL_ON_NULL_FOR_PRIMITIVES, FAIL_ON_NUMBERS_FOR_ENUMS, WRAP_EXCEPTIONS, ACCEPT_SINGLE_VALUE_AS_ARRAY, UNWRAP_ROOT_VALUE, ACCEPT_EMPTY_STRING_AS_NULL_OBJECT };
  }
  
  DeserializationConfig$Feature(boolean paramBoolean) {
    this._defaultState = paramBoolean;
  }
  
  public boolean enabledByDefault() {
    return this._defaultState;
  }
  
  public int getMask() {
    return 1 << ordinal();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\DeserializationConfig$Feature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */