package com.flurry.org.codehaus.jackson.util;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.impl.Indenter;

public class DefaultPrettyPrinter$FixedSpaceIndenter implements Indenter {
  public boolean isInline() {
    return true;
  }
  
  public void writeIndentation(JsonGenerator paramJsonGenerator, int paramInt) {
    paramJsonGenerator.writeRaw(' ');
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackso\\util\DefaultPrettyPrinter$FixedSpaceIndenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */