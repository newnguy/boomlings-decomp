package com.google.ads.internal;

import android.webkit.WebView;
import com.google.ads.AdSize;
import java.util.LinkedList;

class u implements Runnable {
  private final d a;
  
  private final WebView b;
  
  private final LinkedList c;
  
  private final int d;
  
  private final boolean e;
  
  private final String f;
  
  private final AdSize g;
  
  public u(d paramd, WebView paramWebView, LinkedList paramLinkedList, int paramInt, boolean paramBoolean, String paramString, AdSize paramAdSize) {
    this.a = paramd;
    this.b = paramWebView;
    this.c = paramLinkedList;
    this.d = paramInt;
    this.e = paramBoolean;
    this.f = paramString;
    this.g = paramAdSize;
  }
  
  public void run() {
    if (this.b != null) {
      this.b.stopLoading();
      this.b.destroy();
    } 
    this.a.a(this.c);
    this.a.a(this.d);
    this.a.a(this.e);
    this.a.a(this.f);
    if (this.g != null) {
      ((h)(this.a.h()).k.a()).b(this.g);
      this.a.k().setAdSize(this.g);
    } 
    this.a.B();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\interna\\u.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */