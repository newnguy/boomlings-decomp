package com.chartboost.sdk.impl;

import com.chartboost.sdk.Libraries.CBOrientation;

class h$a$2 implements Runnable {
  final h$a a;
  
  private final CBOrientation.Difference b;
  
  h$a$2(h$a paramh$a, CBOrientation.Difference paramDifference) {}
  
  public void run() {
    try {
      h$a.a(this.a, true);
      this.a.requestLayout();
      h$a.a(this.a).a().requestLayout();
      h$a.a(this.a).b().requestLayout();
      h$a.a(this.a, false);
      if (this.b == CBOrientation.Difference.ANGLE_180 || this.b == CBOrientation.Difference.ANGLE_90)
        h$a.a(this.a).c(); 
    } catch (Exception exception) {}
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\h$a$2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */