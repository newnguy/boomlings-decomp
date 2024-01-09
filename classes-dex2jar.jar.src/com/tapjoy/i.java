package com.tapjoy;

final class i implements Runnable {
  public void run() {
    aj.a("TapjoyConnect", "setUserID...");
    String str1 = h.c();
    String str2 = str1 + "&publisher_user_id=" + h.e();
    str1 = str2;
    if (!h.j().equals(""))
      str1 = str2 + "&" + h.j(); 
    str1 = h.k().b("https://ws.tapjoyads.com/set_publisher_user_id?", str1);
    if (str1 != null) {
      if (h.f(str1));
      aj.a("TapjoyConnect", "setUserID successful...");
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\tapjoy\i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */