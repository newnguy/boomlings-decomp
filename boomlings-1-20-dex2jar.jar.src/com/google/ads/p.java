package com.google.ads;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.webkit.WebView;
import com.google.ads.internal.a;
import com.google.ads.internal.d;
import com.google.ads.util.b;
import java.util.HashMap;

public class p implements n {
  private static final a a = (a)a.a.b();
  
  public void a(d paramd, HashMap paramHashMap, WebView paramWebView) {
    String str = (String)paramHashMap.get("urls");
    if (str == null) {
      b.e("Could not get the urls param from canOpenURLs gmsg.");
      return;
    } 
    String[] arrayOfString = str.split(",");
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    PackageManager packageManager = paramWebView.getContext().getPackageManager();
    int i = arrayOfString.length;
    for (byte b = 0; b < i; b++) {
      String str1;
      boolean bool;
      String str2 = arrayOfString[b];
      String[] arrayOfString1 = str2.split(";", 2);
      String str3 = arrayOfString1[0];
      if (arrayOfString1.length >= 2) {
        str1 = arrayOfString1[1];
      } else {
        str1 = "android.intent.action.VIEW";
      } 
      if (packageManager.resolveActivity(new Intent(str1, Uri.parse(str3)), 65536) != null) {
        bool = true;
      } else {
        bool = false;
      } 
      hashMap.put(str2, Boolean.valueOf(bool));
    } 
    a.a(paramWebView, hashMap);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ads\p.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */