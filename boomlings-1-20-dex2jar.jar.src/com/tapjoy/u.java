package com.tapjoy;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class u {
  private Context a;
  
  private Configuration b;
  
  private DisplayMetrics c;
  
  public u(Context paramContext) {
    this.a = paramContext;
    this.c = new DisplayMetrics();
    ((WindowManager)this.a.getSystemService("window")).getDefaultDisplay().getMetrics(this.c);
    this.b = this.a.getResources().getConfiguration();
  }
  
  public int a() {
    return this.c.densityDpi;
  }
  
  public int b() {
    return this.b.screenLayout & 0xF;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\tapjo\\u.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */