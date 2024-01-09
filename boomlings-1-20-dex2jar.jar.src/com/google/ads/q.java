package com.google.ads;

import android.net.Uri;
import android.webkit.WebView;
import com.google.ads.internal.d;
import com.google.ads.internal.g;
import com.google.ads.util.b;
import java.util.HashMap;
import java.util.Locale;

public class q extends u {
  public void a(d paramd, HashMap paramHashMap, WebView paramWebView) {
    String str = (String)paramHashMap.get("u");
    if (str == null) {
      b.e("Could not get URL from click gmsg.");
      return;
    } 
    g g = paramd.m();
    if (g != null) {
      Uri uri = Uri.parse(str);
      String str1 = uri.getHost();
      if (str1 != null && str1.toLowerCase(Locale.US).endsWith(".admob.com")) {
        str1 = null;
        String str3 = uri.getPath();
        String str2 = str1;
        if (str3 != null) {
          String[] arrayOfString = str3.split("/");
          str2 = str1;
          if (arrayOfString.length >= 4)
            str2 = arrayOfString[2] + "/" + arrayOfString[3]; 
        } 
        g.a(str2);
      } 
    } 
    super.a(paramd, paramHashMap, paramWebView);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\q.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */