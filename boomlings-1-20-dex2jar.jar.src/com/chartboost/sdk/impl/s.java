package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBOrientation;

public class s extends RelativeLayout {
  private View a;
  
  private o b;
  
  private OrientationEventListener c;
  
  private CBOrientation.Difference d = null;
  
  public s(Context paramContext, s$a params$a) {
    super(paramContext);
    this.a = (View)params$a;
    this.b = new o(paramContext);
    addView((View)this.b, (ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-1, -1));
    addView(this.a, (ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-1, -1));
    this.d = Chartboost.sharedChartboost().getForcedOrientationDifference();
    this.c = new s$1(this, paramContext, 3);
    this.c.enable();
    setOnTouchListener(new s$2(this));
  }
  
  public void a() {
    if (this.c != null)
      this.c.disable(); 
    this.c = null;
  }
  
  public o b() {
    return this.b;
  }
  
  public View c() {
    return this.a;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    return true;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\s.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */