package com.flurry.org.codehaus.jackson.format;

public interface InputAccessor {
  boolean hasMoreBytes();
  
  byte nextByte();
  
  void reset();
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\format\InputAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */