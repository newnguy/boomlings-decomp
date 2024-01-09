package com.tapjoy;

public class j implements Runnable {
  final h a;
  
  public j(h paramh) {}
  
  public void run() {
    aj.a("TapjoyConnect", "starting connect call...");
    String str = h.c();
    ai ai = h.k().a("https://ws.tapjoyads.com/connect?", str);
    if (ai != null && ai.a == 200) {
      if (h.f(ai.c)) {
        aj.a("TapjoyConnect", "Successfully connected to tapjoy site.");
        if (h.l() != null)
          h.l().a(); 
      } else if (h.l() != null) {
        h.l().b();
      } 
      if (h.m().length() > 0) {
        String str2 = h.d() + "&" + "package_names" + "=" + h.m() + "&";
        long l = System.currentTimeMillis() / 1000L;
        String str1 = h.a(l, h.m());
        str2 = str2 + "timestamp=" + l + "&";
        str1 = str2 + "verifier=" + str1;
        ai ai1 = h.k().a("https://ws.tapjoyads.com/apps_installed?", str1);
        if (ai1 != null && ai1.a == 200)
          aj.a("TapjoyConnect", "Successfully pinged sdkless api."); 
      } 
      return;
    } 
    if (h.l() != null)
      h.l().b(); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\tapjoy\j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */