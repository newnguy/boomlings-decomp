package com.b.a;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

class p extends WebViewClient {
  final n a;
  
  private p(n paramn) {}
  
  public void onPageFinished(WebView paramWebView, String paramString) {
    super.onPageFinished(paramWebView, paramString);
    n.b(this.a).dismiss();
    n.c(this.a).setBackgroundColor(0);
    n.d(this.a).setVisibility(0);
    n.e(this.a).setVisibility(0);
  }
  
  public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap) {
    s.a("Facebook-WebView", "Webview loading URL: " + paramString);
    super.onPageStarted(paramWebView, paramString, paramBitmap);
    n.b(this.a).show();
  }
  
  public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2) {
    super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
    n.a(this.a).onError(new e(paramString1, paramInt, paramString2));
    this.a.dismiss();
  }
  
  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString) {
    boolean bool = true;
    s.a("Facebook-WebView", "Redirect URL: " + paramString);
    if (paramString.startsWith("fbconnect://success")) {
      Bundle bundle = s.b(paramString);
      paramString = bundle.getString("error");
      String str = paramString;
      if (paramString == null)
        str = bundle.getString("error_type"); 
      if (str == null) {
        n.a(this.a).onComplete(bundle);
      } else if (str.equals("access_denied") || str.equals("OAuthAccessDeniedException")) {
        n.a(this.a).onCancel();
      } else {
        n.a(this.a).onFacebookError(new m(str));
      } 
      this.a.dismiss();
      return bool;
    } 
    if (paramString.startsWith("fbconnect://cancel")) {
      n.a(this.a).onCancel();
      this.a.dismiss();
      return bool;
    } 
    if (paramString.contains("touch"))
      return false; 
    this.a.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
    return bool;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\b\a\p.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */