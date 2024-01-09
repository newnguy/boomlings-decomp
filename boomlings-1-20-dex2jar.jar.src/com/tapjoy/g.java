package com.tapjoy;

import android.content.Context;
import android.util.Log;
import java.util.Hashtable;

public final class g {
  private static g a = null;
  
  private static a b = null;
  
  private static aa c = null;
  
  private static t d = null;
  
  private static ao e = null;
  
  private static w f = null;
  
  private static n g = null;
  
  private static Hashtable h = null;
  
  private g(Context paramContext, String paramString1, String paramString2, Hashtable paramHashtable, m paramm) {
    h.a(paramContext, paramString1, paramString2, paramHashtable, paramm);
  }
  
  public static g a() {
    if (a == null) {
      Log.e("TapjoyConnect", "----------------------------------------");
      Log.e("TapjoyConnect", "ERROR -- call requestTapjoyConnect before any other Tapjoy methods");
      Log.e("TapjoyConnect", "----------------------------------------");
    } 
    return a;
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2) {
    a(paramContext, paramString1, paramString2, h);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, Hashtable paramHashtable) {
    a(paramContext, paramString1, paramString2, paramHashtable, null);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, Hashtable paramHashtable, m paramm) {
    h.a("offers");
    a = new g(paramContext, paramString1, paramString2, paramHashtable, paramm);
    b = new a(paramContext);
    c = new aa(paramContext);
    d = new t(paramContext);
    e = new ao(paramContext);
    f = new w(paramContext);
    g = new n(paramContext);
    h = null;
  }
  
  public void a(int paramInt, al paramal) {
    b.a(paramInt, paramal);
  }
  
  public void a(ac paramac) {
    c.a(paramac);
  }
  
  public void a(ak paramak) {
    b.a(paramak);
  }
  
  public void a(p paramp) {
    g.a(paramp);
  }
  
  public void a(v paramv) {
    b.a(paramv);
  }
  
  public void a(boolean paramBoolean) {
    d.a(paramBoolean);
  }
  
  public void b() {
    b.a();
  }
  
  public void c() {
    c.a();
  }
  
  public void d() {
    g.a();
  }
  
  public void e() {
    f.a();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\tapjoy\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */