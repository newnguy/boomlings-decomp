package com.chartboost.sdk;

import android.app.Activity;

class Chartboost$a implements Runnable {
  final Chartboost a;
  
  private int b;
  
  private int c;
  
  private int d;
  
  private Chartboost$a(Chartboost paramChartboost) {
    int i;
    if (Chartboost.f(paramChartboost) == null) {
      i = -1;
    } else {
      i = Chartboost.f(paramChartboost).hashCode();
    } 
    this.b = i;
    if (Chartboost.d(paramChartboost) == null) {
      i = -1;
    } else {
      i = Chartboost.d(paramChartboost).hashCode();
    } 
    this.c = i;
    if (Chartboost.g(paramChartboost) == null) {
      i = b;
    } else {
      i = Chartboost.g(paramChartboost).hashCode();
    } 
    this.d = i;
  }
  
  public void run() {
    if (Chartboost.d(this.a) != null && Chartboost.d(this.a).hashCode() == this.c)
      Chartboost.a(this.a, (Activity)null); 
    if (Chartboost.f(this.a) != null && Chartboost.f(this.a).hashCode() == this.b)
      Chartboost.a(this.a, (CBImpressionActivity)null); 
    if (Chartboost.g(this.a) != null && Chartboost.g(this.a).hashCode() == this.d)
      Chartboost.a(this.a, (ChartboostDelegate)null); 
    this.a.clearImageCache();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\Chartboost$a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */