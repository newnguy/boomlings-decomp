package com.chartboost.sdk;

class Chartboost$c implements Runnable {
  final Chartboost a;
  
  private Chartboost$c(Chartboost paramChartboost) {}
  
  public void run() {
    if (Chartboost.c(this.a) != null) {
      Chartboost.b(this.a, Chartboost.c(this.a));
      return;
    } 
    Chartboost.a(this.a, false);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\Chartboost$c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */