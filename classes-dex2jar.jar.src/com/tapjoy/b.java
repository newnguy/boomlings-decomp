package com.tapjoy;

class b implements Runnable {
  final a a;
  
  b(a parama) {}
  
  public void run() {
    boolean bool = false;
    String str = h.c();
    str = str + "&publisher_user_id=" + h.e();
    str = (new am()).b("https://ws.tapjoyads.com/get_vg_store_items/user_account?", str);
    if (str != null)
      bool = a.a(this.a, str); 
    if (!bool)
      a.b().getUpdatePointsFailed("Failed to retrieve points from server"); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\tapjoy\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */