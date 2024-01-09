package com.google.ads;

import android.webkit.WebView;
import com.google.ads.internal.c;
import com.google.ads.internal.d;
import com.google.ads.util.b;
import java.util.HashMap;

public class w implements n {
  public void a(d paramd, HashMap paramHashMap, WebView paramWebView) {
    c.d d1;
    String str1 = (String)paramHashMap.get("url");
    String str3 = (String)paramHashMap.get("type");
    String str2 = (String)paramHashMap.get("afma_notify_dt");
    boolean bool2 = "1".equals(paramHashMap.get("drt_include"));
    String str4 = (String)paramHashMap.get("request_scenario");
    boolean bool1 = "1".equals(paramHashMap.get("use_webview_loadurl"));
    if (c.d.d.e.equals(str4)) {
      d1 = c.d.d;
    } else if (c.d.c.e.equals(str4)) {
      d1 = c.d.c;
    } else if (c.d.a.e.equals(str4)) {
      d1 = c.d.a;
    } else {
      d1 = c.d.b;
    } 
    b.c("Received ad url: <url: \"" + str1 + "\" type: \"" + str3 + "\" afmaNotifyDt: \"" + str2 + "\" useWebViewLoadUrl: \"" + bool1 + "\">");
    c c = paramd.j();
    if (c != null) {
      c.c(bool2);
      c.a(d1);
      c.d(bool1);
      c.d(str1);
    } 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\w.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */