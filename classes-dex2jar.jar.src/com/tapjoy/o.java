package com.tapjoy;

class o implements Runnable {
  final n a;
  
  o(n paramn) {}
  
  public void run() {
    ai ai = n.b().a("https://ws.tapjoyads.com/reengagement_rewards?", n.a);
    if (ai != null) {
      switch (ai.a) {
        default:
          return;
        case 200:
          n.a(ai.c);
          n.c().getDailyRewardAdResponse();
        case 204:
          break;
      } 
      n.c().getDailyRewardAdResponseFailed(1);
    } 
    n.c().getDailyRewardAdResponseFailed(2);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\tapjoy\o.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */