package com.chartboost.sdk.impl;

import android.view.View;
import android.view.ViewTreeObserver;

class n$1 implements ViewTreeObserver.OnGlobalLayoutListener {
  private final View a;
  
  private final n$b b;
  
  private final a c;
  
  private final n$a d;
  
  private final Boolean e;
  
  n$1(View paramView, n$b paramn$b, a parama, n$a paramn$a, Boolean paramBoolean) {}
  
  public void onGlobalLayout() {
    this.a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
    n.a(this.b, this.c, this.d, this.e);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\n$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */