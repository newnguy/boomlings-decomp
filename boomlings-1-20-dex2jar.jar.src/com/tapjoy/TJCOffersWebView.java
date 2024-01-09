package com.tapjoy;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class TJCOffersWebView extends Activity {
  final String a = "Offers";
  
  private WebView b = null;
  
  private String c = null;
  
  private ProgressBar d;
  
  private Dialog e = null;
  
  private String f = "";
  
  private String g = "";
  
  private String h = "";
  
  private boolean i = false;
  
  private boolean j = false;
  
  protected void onCreate(Bundle paramBundle) {
    Bundle bundle = getIntent().getExtras();
    if (bundle != null) {
      if (bundle.getString("DISPLAY_AD_URL") != null) {
        this.i = true;
        this.c = bundle.getString("DISPLAY_AD_URL");
      } else {
        this.i = false;
        this.g = bundle.getString("URL_PARAMS");
        this.h = bundle.getString("USER_ID");
        if (this.h == null)
          this.h = h.e(); 
        this.g += "&publisher_user_id=" + this.h;
        if (h.f().length() > 0)
          this.g += "&" + h.f(); 
        aj.a("Offers", "urlParams: [" + this.g + "]");
        this.c = "https://ws.tapjoyads.com/get_offers/webpage?" + this.g;
      } 
    } else {
      aj.b("Offers", "Tapjoy offers meta data initialization fail.");
    } 
    this.c = this.c.replaceAll(" ", "%20");
    this.f = h.h();
    aj.a("Offers", "clientPackage: [" + this.f + "]");
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    RelativeLayout relativeLayout = new RelativeLayout((Context)this);
    this.b = new WebView((Context)this);
    this.b.setWebViewClient(new e(this, null));
    this.b.getSettings().setJavaScriptEnabled(true);
    this.d = new ProgressBar((Context)this, null, 16842874);
    this.d.setVisibility(0);
    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
    layoutParams.addRule(13);
    this.d.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
    relativeLayout.addView((View)this.b, -1, -1);
    relativeLayout.addView((View)this.d);
    setContentView((View)relativeLayout);
    this.b.loadUrl(this.c);
    aj.a("Offers", "Opening URL = [" + this.c + "]");
  }
  
  protected void onDestroy() {
    super.onDestroy();
    if (this.b != null) {
      this.b.clearCache(true);
      this.b.destroyDrawingCache();
      this.b.destroy();
    } 
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
    if (paramInt == 4 && this.b.canGoBack()) {
      this.b.goBack();
      return true;
    } 
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onResume() {
    super.onResume();
    if (this.c != null && this.b != null)
      this.b.loadUrl(this.c); 
    if (this.j && h.a() != null) {
      aj.a("Offers", "call connect");
      h.a().b();
    } 
    this.j = true;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\boomlings-1-20-dex2jar.jar!\com\tapjoy\TJCOffersWebView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */