package com.flurry.org.codehaus.jackson.annotate;

import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

public enum JsonAutoDetect$Visibility {
  ANY, DEFAULT, NONE, NON_PRIVATE, PROTECTED_AND_PUBLIC, PUBLIC_ONLY;
  
  private static final JsonAutoDetect$Visibility[] $VALUES;
  
  static {
    NONE = new JsonAutoDetect$Visibility("NONE", 4);
    DEFAULT = new JsonAutoDetect$Visibility("DEFAULT", 5);
    $VALUES = new JsonAutoDetect$Visibility[] { ANY, NON_PRIVATE, PROTECTED_AND_PUBLIC, PUBLIC_ONLY, NONE, DEFAULT };
  }
  
  public boolean isVisible(Member paramMember) {
    boolean bool2 = true;
    boolean bool1 = bool2;
    switch (JsonAutoDetect$1.$SwitchMap$org$codehaus$jackson$annotate$JsonAutoDetect$Visibility[ordinal()]) {
      default:
        bool1 = false;
      case 1:
        return bool1;
      case 2:
        bool1 = false;
      case 3:
        bool1 = bool2;
        if (Modifier.isPrivate(paramMember.getModifiers()))
          bool1 = false; 
      case 4:
        bool1 = bool2;
        if (!Modifier.isProtected(paramMember.getModifiers()))
          break; 
      case 5:
        break;
    } 
    bool1 = Modifier.isPublic(paramMember.getModifiers());
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\flurry\org\codehaus\jackson\annotate\JsonAutoDetect$Visibility.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */