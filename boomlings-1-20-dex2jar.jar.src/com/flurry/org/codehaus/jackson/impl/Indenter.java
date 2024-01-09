package com.flurry.org.codehaus.jackson.impl;

import com.flurry.org.codehaus.jackson.JsonGenerator;

public interface Indenter {
  boolean isInline();
  
  void writeIndentation(JsonGenerator paramJsonGenerator, int paramInt);
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\impl\Indenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */