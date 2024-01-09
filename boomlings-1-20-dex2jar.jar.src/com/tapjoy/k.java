package com.tapjoy;

public class k implements Runnable {
  final h a;
  
  private String b;
  
  public k(h paramh, String paramString) {
    this.b = paramString;
  }
  
  public void run() {
    String str = h.k().b("https://ws.tapjoyads.com/connect?", this.b);
    if (str != null)
      h.a(this.a, str); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\tapjoy\k.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */