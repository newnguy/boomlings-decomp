package com.robtopx.boomlings;

import android.app.AlertDialog;
import android.content.Context;

class f implements Runnable {
  private final String a;
  
  private final String b;
  
  private final String c;
  
  f(String paramString1, String paramString2, String paramString3) {}
  
  public void run() {
    AlertDialog.Builder builder = new AlertDialog.Builder((Context)Boomlings.access$0());
    builder.setTitle(this.a);
    builder.setMessage(this.b).setCancelable(false).setPositiveButton(this.c, null);
    builder.create().show();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\robtopx\boomlings\f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */