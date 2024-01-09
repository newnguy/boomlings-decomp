package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;

class v$b extends WebView {
  final v a;
  
  public v$b(v paramv, Context paramContext) {
    super(paramContext);
    setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(-1, -1));
    setBackgroundColor(0);
    getSettings().setJavaScriptEnabled(true);
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
    if ((paramInt == 4 || paramInt == 3) && this.a.a != null)
      this.a.a.a(); 
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\v$b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */