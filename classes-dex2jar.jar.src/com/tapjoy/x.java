package com.tapjoy;

public class x implements Runnable {
  final w a;
  
  private String b;
  
  public x(w paramw, String paramString) {
    this.b = paramString;
  }
  
  public void run() {
    ai ai = w.b().a("https://ws.tapjoyads.com/user_events?", this.b, 1);
    if (ai != null) {
      switch (ai.a) {
        default:
          aj.b("Event", "Server/network error: " + ai.a);
          return;
        case 200:
          aj.a("Event", "Successfully sent Tapjoy event");
          return;
        case 400:
          break;
      } 
      aj.b("Event", "Error sending event: " + ai.c);
      return;
    } 
    aj.b("Event", "Server/network error");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\tapjoy\x.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */