package com.tapjoy;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

/* loaded from: classes.dex */
public class TapjoyFullScreenAdWebView extends Activity {
    private ProgressBar c;
    private String e;
    private WebView b = null;
    private Dialog d = null;
    private boolean f = false;
    final String a = "Full Screen Ad";

    public void a() {
        aj.a("Full Screen Ad", "Showing offers");
        Intent intent = new Intent(this, TJCOffersWebView.class);
        intent.putExtra("USER_ID", h.e());
        intent.putExtra("URL_PARAMS", h.c());
        startActivity(intent);
    }

    public void b() {
        finish();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.b != null) {
            new ae(this).execute(new Void[0]);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.e = getIntent().getExtras().getString("FULLSCREEN_HTML_DATA");
        super.onCreate(bundle);
        requestWindowFeature(1);
        RelativeLayout relativeLayout = new RelativeLayout(this);
        this.b = new WebView(this);
        this.b.setWebViewClient(new af(this));
        this.b.getSettings().setJavaScriptEnabled(true);
        this.c = new ProgressBar(this, null, 16842874);
        this.c.setVisibility(0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        this.c.setLayoutParams(layoutParams);
        relativeLayout.addView(this.b, -1, -1);
        relativeLayout.addView(this.c);
        setContentView(relativeLayout);
        this.b.loadDataWithBaseURL("https://ws.tapjoyads.com/", this.e, "text/html", "utf-8", null);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.f && h.a() != null) {
            aj.a("Full Screen Ad", "call connect");
            h.a().b();
        }
        this.f = true;
    }
}
