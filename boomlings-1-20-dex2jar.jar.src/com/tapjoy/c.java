package com.tapjoy;

class c implements Runnable {
  final a a;
  
  c(a parama) {}
  
  public void run() {
    boolean bool = false;
    String str = h.c();
    str = str + "&tap_points=" + this.a.a;
    str = str + "&publisher_user_id=" + h.e();
    str = (new am()).b("https://ws.tapjoyads.com/points/spend?", str);
    if (str != null)
      bool = a.b(this.a, str); 
    if (!bool)
      a.c().getSpendPointsResponseFailed("Failed to spend points."); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\tapjoy\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */