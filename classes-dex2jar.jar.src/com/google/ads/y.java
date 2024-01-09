package com.google.ads;

import android.webkit.WebView;
import com.google.ads.internal.d;
import com.google.ads.util.b;
import java.util.HashMap;

public class y implements n {
  public void a(d paramd, HashMap paramHashMap, WebView paramWebView) {
    String str1 = (String)paramHashMap.get("string");
    String str2 = (String)paramHashMap.get("afma_notify_dt");
    b.c("Received log message: <\"string\": \"" + str1 + "\", \"afmaNotifyDt\": \"" + str2 + "\">");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\y.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */