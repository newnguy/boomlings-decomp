package com.tapjoy;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

class s extends WebViewClient {
  final TapjoyDailyRewardAdWebView a;
  
  private s(TapjoyDailyRewardAdWebView paramTapjoyDailyRewardAdWebView) {}
  
  public void onPageFinished(WebView paramWebView, String paramString) {
    TapjoyDailyRewardAdWebView.b(this.a).setVisibility(8);
  }
  
  public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap) {
    TapjoyDailyRewardAdWebView.b(this.a).setVisibility(0);
    TapjoyDailyRewardAdWebView.b(this.a).bringToFront();
  }
  
  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString) {
    aj.a("Daily Reward", "URL = [" + paramString + "]");
    if (paramString.startsWith("http://ok")) {
      aj.a("Daily Reward", "dismiss");
      TapjoyDailyRewardAdWebView.c(this.a);
    } 
    return true;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\tapjoy\s.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */