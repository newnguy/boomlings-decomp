package com.tapjoy;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/* loaded from: classes.dex */
class s extends WebViewClient {
    final /* synthetic */ TapjoyDailyRewardAdWebView a;

    private s(TapjoyDailyRewardAdWebView tapjoyDailyRewardAdWebView) {
        this.a = tapjoyDailyRewardAdWebView;
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        ProgressBar progressBar;
        progressBar = this.a.c;
        progressBar.setVisibility(8);
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        ProgressBar progressBar;
        ProgressBar progressBar2;
        progressBar = this.a.c;
        progressBar.setVisibility(0);
        progressBar2 = this.a.c;
        progressBar2.bringToFront();
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        aj.a("Daily Reward", "URL = [" + str + "]");
        if (str.startsWith("http://ok")) {
            aj.a("Daily Reward", "dismiss");
            this.a.a();
            return true;
        }
        return true;
    }
}
