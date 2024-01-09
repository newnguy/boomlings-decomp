package com.flurry.android;

import android.webkit.WebView;
import android.webkit.WebViewClient;

final class b extends WebViewClient {
  private b() {}
  
  public final boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString) {
    paramWebView.loadUrl(paramString);
    return true;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\flurry\android\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */