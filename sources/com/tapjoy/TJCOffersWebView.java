package com.tapjoy;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.flurry.org.codehaus.jackson.util.MinimalPrettyPrinter;

/* loaded from: classes.dex */
public class TJCOffersWebView extends Activity {
    private ProgressBar d;
    private WebView b = null;
    private String c = null;
    private Dialog e = null;
    private String f = "";
    private String g = "";
    private String h = "";
    final String a = "Offers";
    private boolean i = false;
    private boolean j = false;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            aj.b("Offers", "Tapjoy offers meta data initialization fail.");
        } else if (extras.getString("DISPLAY_AD_URL") != null) {
            this.i = true;
            this.c = extras.getString("DISPLAY_AD_URL");
        } else {
            this.i = false;
            this.g = extras.getString("URL_PARAMS");
            this.h = extras.getString("USER_ID");
            if (this.h == null) {
                this.h = h.e();
            }
            this.g += "&publisher_user_id=" + this.h;
            if (h.f().length() > 0) {
                this.g += "&" + h.f();
            }
            aj.a("Offers", "urlParams: [" + this.g + "]");
            this.c = "https://ws.tapjoyads.com/get_offers/webpage?" + this.g;
        }
        this.c = this.c.replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "%20");
        this.f = h.h();
        aj.a("Offers", "clientPackage: [" + this.f + "]");
        super.onCreate(bundle);
        requestWindowFeature(1);
        RelativeLayout relativeLayout = new RelativeLayout(this);
        this.b = new WebView(this);
        this.b.setWebViewClient(new e(this));
        this.b.getSettings().setJavaScriptEnabled(true);
        this.d = new ProgressBar(this, null, 16842874);
        this.d.setVisibility(0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        this.d.setLayoutParams(layoutParams);
        relativeLayout.addView(this.b, -1, -1);
        relativeLayout.addView(this.d);
        setContentView(relativeLayout);
        this.b.loadUrl(this.c);
        aj.a("Offers", "Opening URL = [" + this.c + "]");
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.b != null) {
            this.b.clearCache(true);
            this.b.destroyDrawingCache();
            this.b.destroy();
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && this.b.canGoBack()) {
            this.b.goBack();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.c != null && this.b != null) {
            this.b.loadUrl(this.c);
        }
        if (this.j && h.a() != null) {
            aj.a("Offers", "call connect");
            h.a().b();
        }
        this.j = true;
    }
}
