package com.tapjoy;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

/* loaded from: classes.dex */
public class TapjoyDailyRewardAdWebView extends Activity {
    private ProgressBar c;
    private WebView b = null;
    private String d = "";
    final String a = "Daily Reward";

    public void a() {
        finish();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.b != null) {
            new r(this).execute(new Void[0]);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.d = getIntent().getExtras().getString("RE_ENGAGEMENT_HTML_DATA");
        super.onCreate(bundle);
        requestWindowFeature(1);
        RelativeLayout relativeLayout = new RelativeLayout(this);
        this.b = new WebView(this);
        this.b.setWebViewClient(new s(this));
        this.b.getSettings().setJavaScriptEnabled(true);
        this.c = new ProgressBar(this, null, 16842874);
        this.c.setVisibility(0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        this.c.setLayoutParams(layoutParams);
        relativeLayout.addView(this.b, -1, -1);
        relativeLayout.addView(this.c);
        setContentView(relativeLayout);
        this.b.loadDataWithBaseURL("https://ws.tapjoyads.com/", this.d, "text/html", "utf-8", null);
        aj.a("Daily Reward", "Opening Daily Reward ad = [" + this.d + "]");
    }
}
