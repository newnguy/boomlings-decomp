package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

public class o extends RelativeLayout {
  public o$a a;
  
  private boolean b;
  
  public o(Context paramContext) {
    super(paramContext);
    this.a = new o$a(this, paramContext);
    this.a.setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-1, -1));
    addView(this.a);
    setFocusable(false);
  }
  
  public void a() {
    a(this.a);
  }
  
  public void a(View paramView) {
    AlphaAnimation alphaAnimation = new AlphaAnimation(0.0F, 1.0F);
    alphaAnimation.setDuration(510L);
    alphaAnimation.setFillAfter(true);
    paramView.startAnimation((Animation)alphaAnimation);
  }
  
  public void a(boolean paramBoolean) {
    this.b = paramBoolean;
    this.a.a();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\o.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */