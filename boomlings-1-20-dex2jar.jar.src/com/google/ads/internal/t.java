package com.google.ads.internal;

import android.webkit.WebView;

class t implements Runnable {
  final c a;
  
  private final String b;
  
  private final String c;
  
  private final WebView d;
  
  public t(c paramc, WebView paramWebView, String paramString1, String paramString2) {
    this.d = paramWebView;
    this.b = paramString1;
    this.c = paramString2;
  }
  
  public void run() {
    if (this.c != null) {
      this.d.loadDataWithBaseURL(this.b, this.c, "text/html", "utf-8", null);
      return;
    } 
    this.d.loadUrl(this.b);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\internal\t.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */