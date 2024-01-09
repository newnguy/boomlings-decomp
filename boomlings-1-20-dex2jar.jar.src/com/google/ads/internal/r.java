package com.google.ads.internal;

import android.webkit.WebView;
import com.google.ads.AdRequest;

class r implements Runnable {
  private final d a;
  
  private final WebView b;
  
  private final f c;
  
  private final AdRequest.ErrorCode d;
  
  private final boolean e;
  
  public r(d paramd, WebView paramWebView, f paramf, AdRequest.ErrorCode paramErrorCode, boolean paramBoolean) {
    this.a = paramd;
    this.b = paramWebView;
    this.c = paramf;
    this.d = paramErrorCode;
    this.e = paramBoolean;
  }
  
  public void run() {
    if (this.b != null) {
      this.b.stopLoading();
      this.b.destroy();
    } 
    if (this.c != null)
      this.c.a(); 
    if (this.e) {
      AdWebView adWebView = this.a.k();
      adWebView.stopLoading();
      adWebView.setVisibility(8);
    } 
    this.a.a(this.d);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\internal\r.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */