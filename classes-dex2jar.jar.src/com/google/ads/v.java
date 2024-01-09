package com.google.ads;

import android.webkit.WebView;
import com.google.ads.internal.c;
import com.google.ads.internal.d;
import com.google.ads.util.b;
import java.util.HashMap;

public class v implements n {
  public void a(d paramd, HashMap paramHashMap, WebView paramWebView) {
    String str2 = (String)paramHashMap.get("type");
    String str1 = (String)paramHashMap.get("errors");
    b.e("Invalid " + str2 + " request error: " + str1);
    c c = paramd.j();
    if (c != null)
      c.a(AdRequest$ErrorCode.a); 
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\v.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */