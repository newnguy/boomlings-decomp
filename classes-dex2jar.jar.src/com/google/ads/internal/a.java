package com.google.ads.internal;

import android.net.Uri;
import android.webkit.WebView;
import com.google.ads.n;
import com.google.ads.util.AdUtil;
import com.google.ads.util.b;
import com.google.ads.util.f;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class a {
  public static final f a;
  
  public static final Map b;
  
  public static final Map c;
  
  private static final a d = new a();
  
  static {
    a = new n();
    b = Collections.unmodifiableMap(new o());
    c = Collections.unmodifiableMap(new m());
  }
  
  public String a(Uri paramUri, HashMap<String, String> paramHashMap) {
    Uri uri = null;
    if (c(paramUri)) {
      String str = paramUri.getHost();
      if (str == null) {
        b.e("An error occurred while parsing the AMSG parameters.");
        return (String)uri;
      } 
      if (str.equals("launch")) {
        paramHashMap.put("a", "intent");
        paramHashMap.put("u", paramHashMap.get("url"));
        paramHashMap.remove("url");
        return "/open";
      } 
      if (str.equals("closecanvas"))
        return "/close"; 
      if (str.equals("log"))
        return "/log"; 
      b.e("An error occurred while parsing the AMSG: " + paramUri.toString());
      return (String)uri;
    } 
    if (b(paramUri))
      return paramUri.getPath(); 
    b.e("Message was neither a GMSG nor an AMSG.");
    return (String)uri;
  }
  
  public void a(WebView paramWebView) {
    a(paramWebView, "onshow", "{'version': 'afma-sdk-a-v6.2.1'}");
  }
  
  public void a(WebView paramWebView, String paramString) {
    b.a("Sending JS to a WebView: " + paramString);
    paramWebView.loadUrl("javascript:" + paramString);
  }
  
  public void a(WebView paramWebView, String paramString1, String paramString2) {
    if (paramString2 != null) {
      a(paramWebView, "AFMA_ReceiveMessage" + "('" + paramString1 + "', " + paramString2 + ");");
      return;
    } 
    a(paramWebView, "AFMA_ReceiveMessage" + "('" + paramString1 + "');");
  }
  
  public void a(WebView paramWebView, Map paramMap) {
    a(paramWebView, "openableURLs", (new JSONObject(paramMap)).toString());
  }
  
  public void a(d paramd, Map paramMap, Uri paramUri, WebView paramWebView) {
    HashMap hashMap = AdUtil.b(paramUri);
    if (hashMap == null) {
      b.e("An error occurred while parsing the message parameters.");
      return;
    } 
    String str = a(paramUri, hashMap);
    if (str == null) {
      b.e("An error occurred while parsing the message.");
      return;
    } 
    n n = (n)paramMap.get(str);
    if (n == null) {
      b.e("No AdResponse found, <message: " + str + ">");
      return;
    } 
    n.a(paramd, hashMap, paramWebView);
  }
  
  public boolean a(Uri paramUri) {
    boolean bool = false;
    null = bool;
    if (paramUri != null) {
      if (!paramUri.isHierarchical())
        return bool; 
    } else {
      return null;
    } 
    if (!b(paramUri)) {
      null = bool;
      return c(paramUri) ? true : null;
    } 
    return true;
  }
  
  public void b(WebView paramWebView) {
    a(paramWebView, "onhide", null);
  }
  
  public boolean b(Uri paramUri) {
    boolean bool2 = false;
    String str2 = paramUri.getScheme();
    boolean bool1 = bool2;
    if (str2 != null) {
      if (!str2.equals("gmsg"))
        return bool2; 
    } else {
      return bool1;
    } 
    String str1 = paramUri.getAuthority();
    bool1 = bool2;
    if (str1 != null) {
      bool1 = bool2;
      if (str1.equals("mobileads.google.com"))
        bool1 = true; 
    } 
    return bool1;
  }
  
  public boolean c(Uri paramUri) {
    String str = paramUri.getScheme();
    return !(str == null || !str.equals("admob"));
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\google\ads\internal\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */