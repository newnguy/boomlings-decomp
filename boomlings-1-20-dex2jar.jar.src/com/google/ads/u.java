package com.google.ads;

import android.webkit.WebView;
import com.google.ads.internal.d;
import com.google.ads.util.b;
import java.util.HashMap;

public class u implements n {
  protected Runnable a(String paramString, WebView paramWebView) {
    return new ac(paramString, paramWebView.getContext().getApplicationContext());
  }
  
  public void a(d paramd, HashMap paramHashMap, WebView paramWebView) {
    String str = (String)paramHashMap.get("u");
    if (str == null) {
      b.e("Could not get URL from click gmsg.");
      return;
    } 
    (new Thread(a(str, paramWebView))).start();
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ad\\u.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */