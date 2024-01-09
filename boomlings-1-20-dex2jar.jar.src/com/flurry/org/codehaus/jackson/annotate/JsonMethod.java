package com.flurry.org.codehaus.jackson.annotate;

public enum JsonMethod {
  ALL, CREATOR, FIELD, GETTER, IS_GETTER, NONE, SETTER;
  
  private static final JsonMethod[] $VALUES;
  
  static {
    CREATOR = new JsonMethod("CREATOR", 2);
    FIELD = new JsonMethod("FIELD", 3);
    IS_GETTER = new JsonMethod("IS_GETTER", 4);
    NONE = new JsonMethod("NONE", 5);
    ALL = new JsonMethod("ALL", 6);
    $VALUES = new JsonMethod[] { GETTER, SETTER, CREATOR, FIELD, IS_GETTER, NONE, ALL };
  }
  
  public boolean creatorEnabled() {
    return (this == CREATOR || this == ALL);
  }
  
  public boolean fieldEnabled() {
    return (this == FIELD || this == ALL);
  }
  
  public boolean getterEnabled() {
    return (this == GETTER || this == ALL);
  }
  
  public boolean isGetterEnabled() {
    return (this == IS_GETTER || this == ALL);
  }
  
  public boolean setterEnabled() {
    return (this == SETTER || this == ALL);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\annotate\JsonMethod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */