package com.google.ads;

import android.webkit.WebView;
import com.google.ads.internal.d;
import com.google.ads.util.b;
import java.util.HashMap;

public class o implements n {
  public void a(d paramd, HashMap paramHashMap, WebView paramWebView) {
    String str = (String)paramHashMap.get("name");
    if (str == null) {
      b.b("Error: App event with no name parameter.");
      return;
    } 
    paramd.a(str, (String)paramHashMap.get("info"));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\o.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */