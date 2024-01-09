package com.flurry.org.codehaus.jackson;

public interface SerializableString {
  char[] asQuotedChars();
  
  byte[] asQuotedUTF8();
  
  byte[] asUnquotedUTF8();
  
  int charLength();
  
  String getValue();
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\SerializableString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */