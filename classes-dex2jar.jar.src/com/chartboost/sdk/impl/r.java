package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Libraries.CBOrientation;

public class r extends LinearLayout implements s$a {
  private static int[] d;
  
  private TextView a;
  
  private t b;
  
  private u c;
  
  public r(Context paramContext) {
    super(paramContext);
    a(paramContext);
  }
  
  private void a(Context paramContext) {
    setGravity(17);
    this.a = new TextView(getContext());
    this.a.setTextColor(-1);
    this.a.setTextSize(2, 16.0F);
    this.a.setTypeface(null, 1);
    this.a.setText("Loading...");
    this.a.setGravity(17);
    this.b = new t(paramContext, (View)this.a);
    this.c = new u(getContext());
    addView((View)this.b);
    addView(this.c);
    a();
  }
  
  public void a() {
    removeView((View)this.b);
    removeView(this.c);
    float f = (getContext().getResources().getDisplayMetrics()).density;
    int i = Math.round(20.0F * f);
    CBOrientation.Difference difference = Chartboost.sharedChartboost().getForcedOrientationDifference();
    switch (b()[difference.ordinal()]) {
      default:
        setOrientation(1);
        layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(i, i, i, 0);
        addView((View)this.b, (ViewGroup.LayoutParams)layoutParams);
        layoutParams = new LinearLayout.LayoutParams(-1, Math.round(f * 32.0F));
        layoutParams.setMargins(i, i, i, i);
        addView(this.c, (ViewGroup.LayoutParams)layoutParams);
        return;
      case 2:
        setOrientation(0);
        layoutParams = new LinearLayout.LayoutParams(Math.round(f * 32.0F), -1);
        layoutParams.setMargins(i, i, 0, i);
        addView(this.c, (ViewGroup.LayoutParams)layoutParams);
        layoutParams = new LinearLayout.LayoutParams(-2, -1);
        layoutParams.setMargins(i, i, i, i);
        addView((View)this.b, (ViewGroup.LayoutParams)layoutParams);
        return;
      case 3:
        setOrientation(1);
        layoutParams = new LinearLayout.LayoutParams(-1, Math.round(f * 32.0F));
        layoutParams.setMargins(i, i, i, 0);
        addView(this.c, (ViewGroup.LayoutParams)layoutParams);
        layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(i, i, i, i);
        addView((View)this.b, (ViewGroup.LayoutParams)layoutParams);
        return;
      case 4:
        break;
    } 
    setOrientation(0);
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
    layoutParams.setMargins(i, i, 0, i);
    addView((View)this.b, (ViewGroup.LayoutParams)layoutParams);
    layoutParams = new LinearLayout.LayoutParams(Math.round(f * 32.0F), -1);
    layoutParams.setMargins(i, i, i, i);
    addView(this.c, (ViewGroup.LayoutParams)layoutParams);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\r.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */