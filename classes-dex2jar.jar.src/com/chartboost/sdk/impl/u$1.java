package com.chartboost.sdk.impl;

import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBOrientation;

class u$1 implements Runnable {
  final u a;
  
  u$1(u paramu) {}
  
  public void run() {
    int i;
    CBOrientation.Difference difference = Chartboost.sharedChartboost().getForcedOrientationDifference();
    float f = (this.a.getContext().getResources().getDisplayMetrics()).density;
    u u1 = this.a;
    u.a(u1, u.a(u1) + 1.0F * f);
    if (difference.isOdd()) {
      i = this.a.getWidth();
    } else {
      i = this.a.getHeight();
    } 
    f = i - f * 9.0F;
    if (u.a(this.a) > f) {
      u u2 = this.a;
      u.a(u2, u.a(u2) - f * 2.0F);
    } 
    if (this.a.getWindowVisibility() == 0)
      this.a.invalidate(); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\imp\\u$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */