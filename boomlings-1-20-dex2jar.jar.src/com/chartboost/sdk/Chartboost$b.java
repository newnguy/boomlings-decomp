package com.chartboost.sdk;

import com.chartboost.sdk.impl.a;

class Chartboost$b implements Runnable {
  final Chartboost a;
  
  private String b;
  
  public Chartboost$b(Chartboost paramChartboost, String paramString) {
    this.b = paramString;
  }
  
  public void run() {
    if (this.a.hasCachedInterstitial(this.b)) {
      Chartboost.a(this.a, (a)Chartboost.b(this.a).get(this.b));
      return;
    } 
    Chartboost.a(this.a, this.b, false);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\Chartboost$b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */