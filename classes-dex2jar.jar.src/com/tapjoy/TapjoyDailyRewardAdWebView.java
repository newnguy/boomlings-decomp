package com.tapjoy;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class TapjoyDailyRewardAdWebView extends Activity {
  final String a = "Daily Reward";
  
  private WebView b = null;
  
  private ProgressBar c;
  
  private String d = "";
  
  private void a() {
    finish();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    super.onConfigurationChanged(paramConfiguration);
    if (this.b != null)
      (new r(this, null)).execute((Object[])new Void[0]); 
  }
  
  protected void onCreate(Bundle paramBundle) {
    this.d = getIntent().getExtras().getString("RE_ENGAGEMENT_HTML_DATA");
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    RelativeLayout relativeLayout = new RelativeLayout((Context)this);
    this.b = new WebView((Context)this);
    this.b.setWebViewClient(new s(this, null));
    this.b.getSettings().setJavaScriptEnabled(true);
    this.c = new ProgressBar((Context)this, null, 16842874);
    this.c.setVisibility(0);
    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
    layoutParams.addRule(13);
    this.c.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
    relativeLayout.addView((View)this.b, -1, -1);
    relativeLayout.addView((View)this.c);
    setContentView((View)relativeLayout);
    this.b.loadDataWithBaseURL("https://ws.tapjoyads.com/", this.d, "text/html", "utf-8", null);
    aj.a("Daily Reward", "Opening Daily Reward ad = [" + this.d + "]");
  }
}


/* Location:              C:\Users\walle\Downloads\boomlings-1-20 (2)\classes-dex2jar.jar!\com\tapjoy\TapjoyDailyRewardAdWebView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */