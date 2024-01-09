package com.flurry.org.codehaus.jackson;

public enum JsonToken {
  END_ARRAY,
  END_OBJECT,
  FIELD_NAME,
  NOT_AVAILABLE(null),
  START_ARRAY(null),
  START_OBJECT("{"),
  VALUE_EMBEDDED_OBJECT("{"),
  VALUE_FALSE("{"),
  VALUE_NULL("{"),
  VALUE_NUMBER_FLOAT("{"),
  VALUE_NUMBER_INT("{"),
  VALUE_STRING("{"),
  VALUE_TRUE("{");
  
  private static final JsonToken[] $VALUES;
  
  final String _serialized;
  
  final byte[] _serializedBytes;
  
  final char[] _serializedChars;
  
  static {
    END_OBJECT = new JsonToken("END_OBJECT", 2, "}");
    START_ARRAY = new JsonToken("START_ARRAY", 3, "[");
    END_ARRAY = new JsonToken("END_ARRAY", 4, "]");
    FIELD_NAME = new JsonToken("FIELD_NAME", 5, null);
    VALUE_EMBEDDED_OBJECT = new JsonToken("VALUE_EMBEDDED_OBJECT", 6, null);
    VALUE_STRING = new JsonToken("VALUE_STRING", 7, null);
    VALUE_NUMBER_INT = new JsonToken("VALUE_NUMBER_INT", 8, null);
    VALUE_NUMBER_FLOAT = new JsonToken("VALUE_NUMBER_FLOAT", 9, null);
    VALUE_TRUE = new JsonToken("VALUE_TRUE", 10, "true");
    VALUE_FALSE = new JsonToken("VALUE_FALSE", 11, "false");
    VALUE_NULL = new JsonToken("VALUE_NULL", 12, "null");
    $VALUES = new JsonToken[] { 
        NOT_AVAILABLE, START_OBJECT, END_OBJECT, START_ARRAY, END_ARRAY, FIELD_NAME, VALUE_EMBEDDED_OBJECT, VALUE_STRING, VALUE_NUMBER_INT, VALUE_NUMBER_FLOAT, 
        VALUE_TRUE, VALUE_FALSE, VALUE_NULL };
  }
  
  JsonToken(String paramString1) {
    if (paramString1 == null) {
      this._serialized = null;
      this._serializedChars = null;
      this._serializedBytes = null;
      return;
    } 
    this._serialized = paramString1;
    this._serializedChars = paramString1.toCharArray();
    int i = this._serializedChars.length;
    this._serializedBytes = new byte[i];
    this$enum$index = 0;
    while (true) {
      if (this$enum$index < i) {
        this._serializedBytes[this$enum$index] = (byte)this._serializedChars[this$enum$index];
        this$enum$index++;
        continue;
      } 
      return;
    } 
  }
  
  public byte[] asByteArray() {
    return this._serializedBytes;
  }
  
  public char[] asCharArray() {
    return this._serializedChars;
  }
  
  public String asString() {
    return this._serialized;
  }
  
  public boolean isNumeric() {
    return (this == VALUE_NUMBER_INT || this == VALUE_NUMBER_FLOAT);
  }
  
  public boolean isScalarValue() {
    return (ordinal() >= VALUE_EMBEDDED_OBJECT.ordinal());
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\JsonToken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */