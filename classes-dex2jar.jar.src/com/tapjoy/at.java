package com.tapjoy;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

class at extends WebViewClient {
  final TapjoyVideoView a;
  
  at(TapjoyVideoView paramTapjoyVideoView) {}
  
  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString) {
    aj.a("VIDEO", "URL = [" + paramString + "]");
    if (paramString.contains("offer_wall")) {
      aj.a("VIDEO", "back to offers");
      this.a.finish();
      return true;
    } 
    if (paramString.contains("tjvideo")) {
      aj.a("VIDEO", "replay");
      TapjoyVideoView.a(this.a);
      return true;
    } 
    if (paramString.contains("ws.tapjoyads.com")) {
      aj.a("VIDEO", "Open redirecting URL = [" + paramString + "]");
      paramWebView.loadUrl(paramString);
      return true;
    } 
    aj.a("VIDEO", "Opening URL in new browser = [" + paramString + "]");
    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    this.a.startActivity(intent);
    return true;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\tapjoy\at.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */