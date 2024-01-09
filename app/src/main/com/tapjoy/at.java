package com.tapjoy;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class at extends WebViewClient {
    final /* synthetic */ TapjoyVideoView a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public at(TapjoyVideoView tapjoyVideoView) {
        this.a = tapjoyVideoView;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        aj.a("VIDEO", "URL = [" + str + "]");
        if (str.contains("offer_wall")) {
            aj.a("VIDEO", "back to offers");
            this.a.finish();
            return true;
        } else if (str.contains("tjvideo")) {
            aj.a("VIDEO", "replay");
            this.a.b();
            return true;
        } else if (str.contains("ws.tapjoyads.com")) {
            aj.a("VIDEO", "Open redirecting URL = [" + str + "]");
            webView.loadUrl(str);
            return true;
        } else {
            aj.a("VIDEO", "Opening URL in new browser = [" + str + "]");
            this.a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            return true;
        }
    }
}
