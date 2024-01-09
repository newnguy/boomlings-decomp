package com.google.ads;

import android.webkit.WebView;

class k implements Runnable {
  private final boolean a;
  
  private final WebView b;
  
  public k(WebView paramWebView, boolean paramBoolean) {
    this.b = paramWebView;
    this.a = paramBoolean;
  }
  
  public void run() {
    ag.a(this.b, this.a);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\k.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */