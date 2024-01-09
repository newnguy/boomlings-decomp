package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.OrientationEventListener;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBOrientation;

class s$1 extends OrientationEventListener {
  final s a;
  
  s$1(s params, Context paramContext, int paramInt) {
    super(paramContext, paramInt);
  }
  
  public void onOrientationChanged(int paramInt) {
    CBOrientation.Difference difference = Chartboost.sharedChartboost().getForcedOrientationDifference();
    if (s.a(this.a) != difference) {
      s.a(this.a, difference);
      ((s$a)s.b(this.a)).a();
      this.a.invalidate();
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\s$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */