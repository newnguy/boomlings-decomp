package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

class o$a extends View {
  final o a;
  
  public o$a(o paramo, Context paramContext) {
    super(paramContext);
    setFocusable(false);
  }
  
  public void a() {
    int i;
    int j = -872415232;
    if (o.a(this.a)) {
      i = -2013265920;
    } else {
      i = -872415232;
    } 
    if (!o.a(this.a))
      j = -2013265920; 
    GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[] { j, i });
    gradientDrawable.setGradientType(1);
    gradientDrawable.setGradientRadius(Math.min(getWidth(), getHeight()) / 2.0F);
    gradientDrawable.setGradientCenter(getWidth() / 2.0F, getHeight() / 2.0F);
    setBackgroundDrawable((Drawable)gradientDrawable);
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    a();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\o$a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */