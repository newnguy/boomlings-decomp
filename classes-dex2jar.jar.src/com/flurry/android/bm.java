package com.flurry.android;

import android.util.Log;

final class bm {
  private static boolean a = false;
  
  private static int b = 5;
  
  static int a(String paramString1, String paramString2) {
    return (a || b > 3) ? 0 : Log.d(paramString1, paramString2);
  }
  
  static int a(String paramString1, String paramString2, Throwable paramThrowable) {
    return (a || b > 3) ? 0 : Log.d(paramString1, paramString2, paramThrowable);
  }
  
  static void a() {
    a = true;
  }
  
  static void a(int paramInt) {
    b = paramInt;
  }
  
  static boolean a(String paramString) {
    return Log.isLoggable(paramString, 3);
  }
  
  static int b(String paramString1, String paramString2) {
    return (a || b > 6) ? 0 : Log.e(paramString1, paramString2);
  }
  
  static int b(String paramString1, String paramString2, Throwable paramThrowable) {
    return (a || b > 6) ? 0 : Log.e(paramString1, paramString2, paramThrowable);
  }
  
  static void b() {
    a = false;
  }
  
  static int c(String paramString1, String paramString2) {
    return (a || b > 4) ? 0 : Log.i(paramString1, paramString2);
  }
  
  static int d(String paramString1, String paramString2) {
    return (a || b > 5) ? 0 : Log.w(paramString1, paramString2);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\bm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */