package com.tapjoy;

import android.util.Log;

public class aj {
  private static boolean a = false;
  
  public static void a(String paramString1, String paramString2) {
    if (a)
      if (paramString2.length() > 4096) {
        for (byte b = 0; b <= paramString2.length() / 4096; b++) {
          int j = (b + 1) * 4096;
          int i = j;
          if (j > paramString2.length())
            i = paramString2.length(); 
          Log.i(paramString1, paramString2.substring(b * 4096, i));
        } 
      } else {
        Log.i(paramString1, paramString2);
      }  
  }
  
  public static void a(boolean paramBoolean) {
    Log.i("TapjoyLog", "enableLogging: " + paramBoolean);
    a = paramBoolean;
  }
  
  public static void b(String paramString1, String paramString2) {
    if (a)
      Log.e(paramString1, paramString2); 
  }
  
  public static void c(String paramString1, String paramString2) {
    if (a)
      Log.w(paramString1, paramString2); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\tapjoy\aj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */