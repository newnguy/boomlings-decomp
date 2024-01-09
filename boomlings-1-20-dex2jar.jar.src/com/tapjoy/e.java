package com.tapjoy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.Hashtable;

class e extends WebViewClient {
  final TJCOffersWebView a;
  
  private e(TJCOffersWebView paramTJCOffersWebView) {}
  
  public void onPageFinished(WebView paramWebView, String paramString) {
    TJCOffersWebView.a(this.a).setVisibility(8);
  }
  
  public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap) {
    TJCOffersWebView.a(this.a).setVisibility(0);
    TJCOffersWebView.a(this.a).bringToFront();
  }
  
  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString) {
    aj.a("Offers", "URL = [" + paramString + "]");
    if (paramString.startsWith("tjvideo://")) {
      Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();
      int i = paramString.indexOf("://");
      int j = "://".length();
      String str4 = "";
      String str2 = "";
      j = i + j;
      int k;
      for (k = 0; j < paramString.length() && j != -1; k = i) {
        String str6;
        String str7;
        char c = paramString.charAt(j);
        if (!k) {
          if (c == '=') {
            str7 = Uri.decode(str4);
            str6 = "";
            i = 1;
          } else {
            str6 = str4 + c;
            str7 = str2;
            i = k;
          } 
        } else {
          str7 = str2;
          str6 = str4;
          i = k;
          if (k == 1)
            if (c == '&') {
              str7 = Uri.decode(str4);
              str6 = "";
              aj.a("Offers", "k:v: " + str2 + ", " + str7);
              hashtable.put(str2, str7);
              i = 0;
              str7 = str2;
            } else {
              str6 = str4 + c;
              str7 = str2;
              i = k;
            }  
        } 
        j++;
        str2 = str7;
        str4 = str6;
      } 
      if (k == 1 && str4.length() > 0) {
        String str = Uri.decode(str4);
        aj.a("Offers", "k:v: " + str2 + ", " + str);
        hashtable.put(str2, str);
      } 
      str2 = (String)hashtable.get("video_id");
      String str1 = (String)hashtable.get("amount");
      String str3 = (String)hashtable.get("currency_name");
      paramString = (String)hashtable.get("click_url");
      str4 = (String)hashtable.get("video_complete_url");
      String str5 = (String)hashtable.get("video_url");
      aj.a("Offers", "videoID: " + str2);
      aj.a("Offers", "currencyAmount: " + str1);
      aj.a("Offers", "currencyName: " + str3);
      aj.a("Offers", "clickURL: " + paramString);
      aj.a("Offers", "webviewURL: " + str4);
      aj.a("Offers", "videoURL: " + str5);
      if (ao.a().a(str2, str3, str1, paramString, str4, str5)) {
        aj.a("Offers", "VIDEO");
        return true;
      } 
      aj.b("Offers", "Unable to play video: " + str2);
      TJCOffersWebView.a(this.a, (Dialog)(new AlertDialog.Builder((Context)this.a)).setTitle("").setMessage("Unable to play video.").setPositiveButton("OK", new f(this)).create());
      try {
        TJCOffersWebView.b(this.a).show();
      } catch (Exception exception) {
        aj.b("Offers", "e: " + exception.toString());
      } 
      return true;
    } 
    if (paramString.contains("ws.tapjoyads.com")) {
      aj.a("Offers", "Open redirecting URL = [" + paramString + "]");
      exception.loadUrl(paramString);
      return true;
    } 
    aj.a("Offers", "Opening URL in new browser = [" + paramString + "]");
    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    this.a.startActivity(intent);
    aj.a("Offers", "skipOfferWall: " + TJCOffersWebView.c(this.a));
    if (TJCOffersWebView.c(this.a))
      this.a.finish(); 
    return true;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\tapjoy\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */