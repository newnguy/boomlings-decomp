package com.flurry.org.codehaus.jackson.annotate;

public enum JsonTypeInfo$Id {
  CLASS,
  CUSTOM,
  MINIMAL_CLASS,
  NAME,
  NONE(null);
  
  private static final JsonTypeInfo$Id[] $VALUES;
  
  private final String _defaultPropertyName;
  
  static {
    CLASS = new JsonTypeInfo$Id("CLASS", 1, "@class");
    MINIMAL_CLASS = new JsonTypeInfo$Id("MINIMAL_CLASS", 2, "@c");
    NAME = new JsonTypeInfo$Id("NAME", 3, "@type");
    CUSTOM = new JsonTypeInfo$Id("CUSTOM", 4, null);
    $VALUES = new JsonTypeInfo$Id[] { NONE, CLASS, MINIMAL_CLASS, NAME, CUSTOM };
  }
  
  JsonTypeInfo$Id(String paramString1) {
    this._defaultPropertyName = paramString1;
  }
  
  public String getDefaultPropertyName() {
    return this._defaultPropertyName;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\annotate\JsonTypeInfo$Id.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */