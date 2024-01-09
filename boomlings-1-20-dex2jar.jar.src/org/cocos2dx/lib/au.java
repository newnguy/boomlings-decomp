package org.cocos2dx.lib;

import java.util.Comparator;

class au implements Comparator {
  final as a;
  
  au(as paramas) {}
  
  public int a(String[] paramArrayOfString1, String[] paramArrayOfString2) {
    String str4 = paramArrayOfString1[2];
    String str3 = paramArrayOfString2[2];
    String str1 = str4;
    if (str4 == null)
      str1 = "0"; 
    String str2 = str3;
    if (str3 == null)
      str2 = "0"; 
    long l = Long.parseLong(str1);
    return (int)(Long.parseLong(str2) - l);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\org\cocos2dx\lib\au.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */