package com.flurry.org.codehaus.jackson.util;

import com.flurry.org.codehaus.jackson.JsonGenerator;
import com.flurry.org.codehaus.jackson.impl.Indenter;
import java.util.Arrays;

public class DefaultPrettyPrinter$Lf2SpacesIndenter implements Indenter {
  static final char[] SPACES;
  
  static final int SPACE_COUNT = 64;
  
  static final String SYSTEM_LINE_SEPARATOR;
  
  static {
    String str1 = null;
    try {
      String str = System.getProperty("line.separator");
      str1 = str;
    } catch (Throwable throwable) {}
    String str2 = str1;
    if (str1 == null)
      str2 = "\n"; 
    SYSTEM_LINE_SEPARATOR = str2;
    SPACES = new char[64];
    Arrays.fill(SPACES, ' ');
  }
  
  public boolean isInline() {
    return false;
  }
  
  public void writeIndentation(JsonGenerator paramJsonGenerator, int paramInt) {
    paramJsonGenerator.writeRaw(SYSTEM_LINE_SEPARATOR);
    for (paramInt += paramInt; paramInt > 64; paramInt -= SPACES.length)
      paramJsonGenerator.writeRaw(SPACES, 0, 64); 
    paramJsonGenerator.writeRaw(SPACES, 0, paramInt);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackso\\util\DefaultPrettyPrinter$Lf2SpacesIndenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */