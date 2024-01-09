package com.google.ads.util;

import android.content.Context;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.google.ads.internal.c;
import com.google.ads.internal.d;
import com.google.ads.internal.i;
import com.google.ads.l;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class g$b extends i {
  public g$b(d paramd, Map paramMap, boolean paramBoolean1, boolean paramBoolean2) {
    super(paramd, paramMap, paramBoolean1, paramBoolean2);
  }
  
  private static WebResourceResponse a(String paramString, Context paramContext) {
    HttpURLConnection httpURLConnection = (HttpURLConnection)(new URL(paramString)).openConnection();
    try {
      AdUtil.a(httpURLConnection, paramContext.getApplicationContext());
      httpURLConnection.connect();
      InputStreamReader inputStreamReader = new InputStreamReader();
      this(httpURLConnection.getInputStream());
      String str = AdUtil.a(inputStreamReader);
      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream();
      this(str.getBytes("UTF-8"));
      return new WebResourceResponse("application/javascript", "UTF-8", byteArrayInputStream);
    } finally {
      httpURLConnection.disconnect();
    } 
  }
  
  public WebResourceResponse shouldInterceptRequest(WebView paramWebView, String paramString) {
    try {
      File file = new File();
      this(paramString);
      if ("mraid.js".equalsIgnoreCase(file.getName())) {
        WebResourceResponse webResourceResponse;
        c c = this.a.j();
        if (c != null) {
          c.b(true);
        } else {
          this.a.a(true);
        } 
        l.a a = (l.a)((l)(this.a.h()).a.a()).a.a();
        if (!this.a.h().b()) {
          WebResourceResponse webResourceResponse1;
          if (this.b) {
            String str2 = (String)a.d.a();
            StringBuilder stringBuilder2 = new StringBuilder();
            this();
            b.a(stringBuilder2.append("shouldInterceptRequest(").append(str2).append(")").toString());
            webResourceResponse1 = a(str2, paramWebView.getContext());
            return webResourceResponse1;
          } 
          String str1 = (String)((l.a)webResourceResponse1).c.a();
          StringBuilder stringBuilder1 = new StringBuilder();
          this();
          b.a(stringBuilder1.append("shouldInterceptRequest(").append(str1).append(")").toString());
          webResourceResponse = a(str1, paramWebView.getContext());
          return webResourceResponse;
        } 
        String str = (String)((l.a)webResourceResponse).e.a();
        StringBuilder stringBuilder = new StringBuilder();
        this();
        b.a(stringBuilder.append("shouldInterceptRequest(").append(str).append(")").toString());
        return a(str, paramWebView.getContext());
      } 
    } catch (IOException iOException) {
      b.d("IOException fetching MRAID JS.", iOException);
    } catch (Throwable throwable) {
      b.b("An unknown error occurred fetching MRAID JS.", throwable);
    } 
    return super.shouldInterceptRequest(paramWebView, paramString);
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\google\ad\\util\g$b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */