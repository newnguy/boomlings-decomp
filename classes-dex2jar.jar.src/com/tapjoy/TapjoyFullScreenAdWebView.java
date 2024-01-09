package com.tapjoy;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class TapjoyFullScreenAdWebView extends Activity {
  final String a = "Full Screen Ad";
  
  private WebView b = null;
  
  private ProgressBar c;
  
  private Dialog d = null;
  
  private String e;
  
  private boolean f = false;
  
  private void a() {
    aj.a("Full Screen Ad", "Showing offers");
    Intent intent = new Intent((Context)this, TJCOffersWebView.class);
    intent.putExtra("USER_ID", h.e());
    intent.putExtra("URL_PARAMS", h.c());
    startActivity(intent);
  }
  
  private void b() {
    finish();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    super.onConfigurationChanged(paramConfiguration);
    if (this.b != null)
      (new ae(this, null)).execute((Object[])new Void[0]); 
  }
  
  protected void onCreate(Bundle paramBundle) {
    this.e = getIntent().getExtras().getString("FULLSCREEN_HTML_DATA");
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    RelativeLayout relativeLayout = new RelativeLayout((Context)this);
    this.b = new WebView((Context)this);
    this.b.setWebViewClient(new af(this, null));
    this.b.getSettings().setJavaScriptEnabled(true);
    this.c = new ProgressBar((Context)this, null, 16842874);
    this.c.setVisibility(0);
    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
    layoutParams.addRule(13);
    this.c.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
    relativeLayout.addView((View)this.b, -1, -1);
    relativeLayout.addView((View)this.c);
    setContentView((View)relativeLayout);
    this.b.loadDataWithBaseURL("https://ws.tapjoyads.com/", this.e, "text/html", "utf-8", null);
  }
  
  protected void onResume() {
    super.onResume();
    if (this.f && h.a() != null) {
      aj.a("Full Screen Ad", "call connect");
      h.a().b();
    } 
    this.f = true;
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\tapjoy\TapjoyFullScreenAdWebView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */