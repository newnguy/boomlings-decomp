package com.google.ads.internal;

import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.ads.AdActivity;
import com.google.ads.util.AdUtil;
import com.google.ads.util.b;
import com.google.ads.util.g;
import java.util.HashMap;
import java.util.Map;

public class i extends WebViewClient {
  private static final a c = (a)a.a.b();
  
  protected d a;
  
  protected boolean b;
  
  private final Map d;
  
  private final boolean e;
  
  private boolean f;
  
  private boolean g;
  
  private boolean h;
  
  public i(d paramd, Map paramMap, boolean paramBoolean1, boolean paramBoolean2) {
    this.a = paramd;
    this.d = paramMap;
    this.e = paramBoolean1;
    this.f = paramBoolean2;
    this.b = false;
    this.g = false;
    this.h = false;
  }
  
  public static i a(d paramd, Map paramMap, boolean paramBoolean1, boolean paramBoolean2) {
    return (i)((AdUtil.a >= 11) ? new g.b(paramd, paramMap, paramBoolean1, paramBoolean2) : new i(paramd, paramMap, paramBoolean1, paramBoolean2));
  }
  
  public void a(boolean paramBoolean) {
    this.b = paramBoolean;
  }
  
  public void b(boolean paramBoolean) {
    this.f = paramBoolean;
  }
  
  public void c(boolean paramBoolean) {
    this.g = paramBoolean;
  }
  
  public void d(boolean paramBoolean) {
    this.h = paramBoolean;
  }
  
  public void onPageFinished(WebView paramWebView, String paramString) {
    if (this.g) {
      c c = this.a.j();
      if (c != null) {
        c.c();
      } else {
        b.a("adLoader was null while trying to setFinishedLoadingHtml().");
      } 
      this.g = false;
    } 
    if (this.h) {
      c.a(paramWebView);
      this.h = false;
    } 
  }
  
  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString) {
    boolean bool = true;
    try {
      StringBuilder stringBuilder1 = new StringBuilder();
      this();
      b.a(stringBuilder1.append("shouldOverrideUrlLoading(\"").append(paramString).append("\")").toString());
      Uri uri = Uri.parse(paramString);
      if (c.a(uri)) {
        c.a(this.a, this.d, uri, paramWebView);
        return bool;
      } 
      if (this.f) {
        if (AdUtil.a(uri))
          return super.shouldOverrideUrlLoading(paramWebView, paramString); 
        HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
        this();
        hashMap.put("u", paramString);
        d2 = this.a;
        e e = new e();
        this("intent", hashMap);
        AdActivity.a(d2, e);
        return bool;
      } 
    } catch (Throwable throwable) {
      b.b("An unknown error occurred in shouldOverrideUrlLoading.", throwable);
      return bool;
    } 
    if (this.e) {
      HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
      this();
      hashMap.put("u", d2.toString());
      d1 = this.a;
      e e = new e();
      this("intent", hashMap);
      AdActivity.a(d1, e);
      return bool;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    this();
    d d1;
    d d2;
    b.e(stringBuilder.append("URL is not a GMSG and can't handle URL: ").append((String)d1).toString());
    return bool;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\internal\i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */