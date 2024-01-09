package com.tapjoy;

class ab implements Runnable {
  final aa a;
  
  ab(aa paramaa) {}
  
  public void run() {
    ai ai = aa.b().a("https://ws.tapjoyads.com/get_offers/featured.html?", aa.a);
    if (ai != null) {
      switch (ai.a) {
        default:
          if (aa.c() != null)
            aa.c().a("Error retrieving full screen ad data from the server."); 
          if (aa.d() != null)
            aa.d().getFullScreenAdResponseFailed(1); 
          return;
        case 200:
          break;
      } 
      aa.b(ai.c);
      if (aa.c() != null)
        aa.c().a((z)null); 
      if (aa.d() != null)
        aa.d().getFullScreenAdResponse(); 
      return;
    } 
    if (aa.c() != null)
      aa.c().a("Error retrieving full screen ad data from the server."); 
    if (aa.d() != null)
      aa.d().getFullScreenAdResponseFailed(2); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\tapjoy\ab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */