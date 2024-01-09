package com.tapjoy;

import android.content.Context;

public class t {
  private static am a = null;
  
  private static String d;
  
  private static int e;
  
  private static int f;
  
  private Context b;
  
  private boolean c;
  
  public t(Context paramContext) {
    a("640x100");
    this.b = paramContext;
    a = new am();
  }
  
  public void a(String paramString) {
    d = paramString;
    if (paramString.equals("320x50")) {
      e = 320;
      f = 50;
      return;
    } 
    if (paramString.equals("640x100")) {
      e = 640;
      f = 100;
      return;
    } 
    if (paramString.equals("768x90")) {
      e = 768;
      f = 90;
    } 
  }
  
  public void a(boolean paramBoolean) {
    this.c = paramBoolean;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\tapjoy\t.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */