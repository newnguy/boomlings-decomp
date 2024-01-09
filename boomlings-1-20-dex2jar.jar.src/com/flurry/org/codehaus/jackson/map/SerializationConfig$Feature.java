package com.flurry.org.codehaus.jackson.map;

public enum SerializationConfig$Feature implements MapperConfig$ConfigFeature {
  AUTO_DETECT_FIELDS,
  AUTO_DETECT_GETTERS,
  AUTO_DETECT_IS_GETTERS,
  CAN_OVERRIDE_ACCESS_MODIFIERS,
  CLOSE_CLOSEABLE,
  DEFAULT_VIEW_INCLUSION,
  FAIL_ON_EMPTY_BEANS,
  FLUSH_AFTER_WRITE_VALUE,
  INDENT_OUTPUT,
  REQUIRE_SETTERS_FOR_GETTERS,
  SORT_PROPERTIES_ALPHABETICALLY,
  USE_ANNOTATIONS(true),
  USE_STATIC_TYPING(true),
  WRAP_EXCEPTIONS(true),
  WRAP_ROOT_VALUE(true),
  WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS(true),
  WRITE_DATES_AS_TIMESTAMPS(true),
  WRITE_DATE_KEYS_AS_TIMESTAMPS(true),
  WRITE_EMPTY_JSON_ARRAYS(true),
  WRITE_ENUMS_USING_INDEX(true),
  WRITE_ENUMS_USING_TO_STRING(true),
  WRITE_NULL_MAP_VALUES(true),
  WRITE_NULL_PROPERTIES(true);
  
  private static final SerializationConfig$Feature[] $VALUES;
  
  final boolean _defaultState;
  
  static {
    AUTO_DETECT_GETTERS = new SerializationConfig$Feature("AUTO_DETECT_GETTERS", 1, true);
    AUTO_DETECT_IS_GETTERS = new SerializationConfig$Feature("AUTO_DETECT_IS_GETTERS", 2, true);
    AUTO_DETECT_FIELDS = new SerializationConfig$Feature("AUTO_DETECT_FIELDS", 3, true);
    CAN_OVERRIDE_ACCESS_MODIFIERS = new SerializationConfig$Feature("CAN_OVERRIDE_ACCESS_MODIFIERS", 4, true);
    REQUIRE_SETTERS_FOR_GETTERS = new SerializationConfig$Feature("REQUIRE_SETTERS_FOR_GETTERS", 5, false);
    WRITE_NULL_PROPERTIES = new SerializationConfig$Feature("WRITE_NULL_PROPERTIES", 6, true);
    USE_STATIC_TYPING = new SerializationConfig$Feature("USE_STATIC_TYPING", 7, false);
    DEFAULT_VIEW_INCLUSION = new SerializationConfig$Feature("DEFAULT_VIEW_INCLUSION", 8, true);
    WRAP_ROOT_VALUE = new SerializationConfig$Feature("WRAP_ROOT_VALUE", 9, false);
    INDENT_OUTPUT = new SerializationConfig$Feature("INDENT_OUTPUT", 10, false);
    SORT_PROPERTIES_ALPHABETICALLY = new SerializationConfig$Feature("SORT_PROPERTIES_ALPHABETICALLY", 11, false);
    FAIL_ON_EMPTY_BEANS = new SerializationConfig$Feature("FAIL_ON_EMPTY_BEANS", 12, true);
    WRAP_EXCEPTIONS = new SerializationConfig$Feature("WRAP_EXCEPTIONS", 13, true);
    CLOSE_CLOSEABLE = new SerializationConfig$Feature("CLOSE_CLOSEABLE", 14, false);
    FLUSH_AFTER_WRITE_VALUE = new SerializationConfig$Feature("FLUSH_AFTER_WRITE_VALUE", 15, true);
    WRITE_DATES_AS_TIMESTAMPS = new SerializationConfig$Feature("WRITE_DATES_AS_TIMESTAMPS", 16, true);
    WRITE_DATE_KEYS_AS_TIMESTAMPS = new SerializationConfig$Feature("WRITE_DATE_KEYS_AS_TIMESTAMPS", 17, false);
    WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS = new SerializationConfig$Feature("WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS", 18, false);
    WRITE_ENUMS_USING_TO_STRING = new SerializationConfig$Feature("WRITE_ENUMS_USING_TO_STRING", 19, false);
    WRITE_ENUMS_USING_INDEX = new SerializationConfig$Feature("WRITE_ENUMS_USING_INDEX", 20, false);
    WRITE_NULL_MAP_VALUES = new SerializationConfig$Feature("WRITE_NULL_MAP_VALUES", 21, true);
    WRITE_EMPTY_JSON_ARRAYS = new SerializationConfig$Feature("WRITE_EMPTY_JSON_ARRAYS", 22, true);
    $VALUES = new SerializationConfig$Feature[] { 
        USE_ANNOTATIONS, AUTO_DETECT_GETTERS, AUTO_DETECT_IS_GETTERS, AUTO_DETECT_FIELDS, CAN_OVERRIDE_ACCESS_MODIFIERS, REQUIRE_SETTERS_FOR_GETTERS, WRITE_NULL_PROPERTIES, USE_STATIC_TYPING, DEFAULT_VIEW_INCLUSION, WRAP_ROOT_VALUE, 
        INDENT_OUTPUT, SORT_PROPERTIES_ALPHABETICALLY, FAIL_ON_EMPTY_BEANS, WRAP_EXCEPTIONS, CLOSE_CLOSEABLE, FLUSH_AFTER_WRITE_VALUE, WRITE_DATES_AS_TIMESTAMPS, WRITE_DATE_KEYS_AS_TIMESTAMPS, WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS, WRITE_ENUMS_USING_TO_STRING, 
        WRITE_ENUMS_USING_INDEX, WRITE_NULL_MAP_VALUES, WRITE_EMPTY_JSON_ARRAYS };
  }
  
  SerializationConfig$Feature(boolean paramBoolean) {
    this._defaultState = paramBoolean;
  }
  
  public boolean enabledByDefault() {
    return this._defaultState;
  }
  
  public int getMask() {
    return 1 << ordinal();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\SerializationConfig$Feature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */