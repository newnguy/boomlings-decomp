package com.google.ads.util;

import android.text.TextUtils;
import android.util.Log;

public class a {
  private static boolean a = Log.isLoggable("GoogleAdsAssertion", 3);
  
  public static void a(Object paramObject) {
    boolean bool;
    if (paramObject == null) {
      bool = true;
    } else {
      bool = false;
    } 
    c(bool, "Assertion that an object is null failed.");
  }
  
  public static void a(Object paramObject1, Object paramObject2) {
    boolean bool;
    if (paramObject1 == paramObject2) {
      bool = true;
    } else {
      bool = false;
    } 
    c(bool, "Assertion that 'a' and 'b' refer to the same object failed.a: " + paramObject1 + ", b: " + paramObject2);
  }
  
  public static void a(String paramString) {
    boolean bool;
    if (!TextUtils.isEmpty(paramString)) {
      bool = true;
    } else {
      bool = false;
    } 
    c(bool, "Expected a non empty string, got: " + paramString);
  }
  
  public static void a(boolean paramBoolean) {
    c(paramBoolean, "Assertion failed.");
  }
  
  public static void a(boolean paramBoolean, String paramString) {
    c(paramBoolean, paramString);
  }
  
  public static void b(Object paramObject) {
    boolean bool;
    if (paramObject != null) {
      bool = true;
    } else {
      bool = false;
    } 
    c(bool, "Assertion that an object is not null failed.");
  }
  
  public static void b(boolean paramBoolean) {
    if (!paramBoolean) {
      paramBoolean = true;
    } else {
      paramBoolean = false;
    } 
    c(paramBoolean, "Assertion failed.");
  }
  
  public static void b(boolean paramBoolean, String paramString) {
    if (!paramBoolean) {
      paramBoolean = true;
    } else {
      paramBoolean = false;
    } 
    c(paramBoolean, paramString);
  }
  
  private static void c(boolean paramBoolean, String paramString) {
    if ((Log.isLoggable("GoogleAdsAssertion", 3) || a) && !paramBoolean) {
      a$a a$a = new a$a(paramString);
      Log.d("GoogleAdsAssertion", paramString, a$a);
      throw a$a;
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ad\\util\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */