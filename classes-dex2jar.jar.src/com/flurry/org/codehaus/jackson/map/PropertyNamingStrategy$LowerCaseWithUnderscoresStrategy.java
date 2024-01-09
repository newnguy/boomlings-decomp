package com.flurry.org.codehaus.jackson.map;

public class PropertyNamingStrategy$LowerCaseWithUnderscoresStrategy extends PropertyNamingStrategy$PropertyNamingStrategyBase {
  public String translate(String paramString) {
    if (paramString != null) {
      int k = paramString.length();
      StringBuilder stringBuilder = new StringBuilder(k * 2);
      byte b = 0;
      int j = 0;
      int i = 0;
      while (true) {
        if (b < k) {
          char c = paramString.charAt(b);
          if (b > 0 || c != '_') {
            if (Character.isUpperCase(c)) {
              int n = i;
              if (!j) {
                n = i;
                if (i) {
                  n = i;
                  if (stringBuilder.charAt(i - 1) != '_') {
                    stringBuilder.append('_');
                    n = i + 1;
                  } 
                } 
              } 
              c = Character.toLowerCase(c);
              i = 1;
              j = n;
            } else {
              j = i;
              i = 0;
            } 
            stringBuilder.append(c);
            j++;
          } else {
            int n = j;
            j = i;
            i = n;
          } 
          b++;
          int m = i;
          i = j;
          j = m;
          continue;
        } 
        if (i > 0)
          paramString = stringBuilder.toString(); 
        return paramString;
      } 
    } 
    return paramString;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\org\codehaus\jackson\map\PropertyNamingStrategy$LowerCaseWithUnderscoresStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */