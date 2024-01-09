package com.chartboost.sdk.impl;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

class i$1 extends WebViewClient {
  final i a;
  
  i$1(i parami) {}
  
  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString) {
    if (paramString == null)
      return false; 
    if (paramString.contains("chartboost") && paramString.contains("click") && this.a.a != null)
      this.a.a.onClick((View)this.a); 
    return true;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\i$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */