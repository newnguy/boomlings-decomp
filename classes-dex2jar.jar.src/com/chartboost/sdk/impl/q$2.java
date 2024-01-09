package com.chartboost.sdk.impl;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

class q$2 implements View.OnTouchListener {
  final q a;
  
  q$2(q paramq) {}
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent) {
    try {
      paramView = ((Activity)q.a(this.a).getContext()).getCurrentFocus();
      if (paramView != null)
        ((InputMethodManager)q.a(this.a).getContext().getSystemService("input_method")).hideSoftInputFromWindow(paramView.getApplicationWindowToken(), 0); 
    } catch (Exception exception) {}
    return false;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\chartboost\sdk\impl\q$2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */