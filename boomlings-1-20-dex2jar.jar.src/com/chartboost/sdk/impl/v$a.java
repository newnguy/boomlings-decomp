package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import com.chartboost.sdk.b;

public class v$a extends b.b {
  public WebView c;
  
  final v d;
  
  public v$a(v paramv, Context paramContext, String paramString) {
    super(paramv, paramContext);
    setFocusable(false);
    this.c = new v$b(paramv, paramContext);
    this.c.setWebViewClient(new v$c(paramv, paramv));
    addView((View)this.c);
    this.c.loadDataWithBaseURL("file:///android_asset/", paramString, "text/html", "utf-8", null);
  }
  
  protected void a(int paramInt1, int paramInt2) {}
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\chartboost\sdk\impl\v$a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */