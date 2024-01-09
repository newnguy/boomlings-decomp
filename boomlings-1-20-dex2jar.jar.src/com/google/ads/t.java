package com.google.ads;

import android.webkit.WebView;
import com.google.ads.internal.AdWebView;
import com.google.ads.internal.a;
import com.google.ads.internal.d;
import com.google.ads.util.b;
import java.util.HashMap;

public class t implements n {
  private static final a a = (a)a.a.b();
  
  public void a(d paramd, HashMap paramHashMap, WebView paramWebView) {
    AdActivity adActivity;
    String str = (String)paramHashMap.get("js");
    if (str == null) {
      b.b("Could not get the JS to evaluate.");
      return;
    } 
    if (paramWebView instanceof AdWebView) {
      adActivity = ((AdWebView)paramWebView).d();
      if (adActivity == null) {
        b.b("Could not get the AdActivity from the AdWebView.");
        return;
      } 
    } else {
      b.b("Trying to evaluate JS in a WebView that isn't an AdWebView");
      return;
    } 
    AdWebView adWebView = adActivity.b();
    if (adWebView == null) {
      b.b("Could not get the opening WebView.");
      return;
    } 
    a.a((WebView)adWebView, str);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\t.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */