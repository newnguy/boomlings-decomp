package com.flurry.android;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/* loaded from: classes.dex */
final class b extends WebViewClient {
    private b() {
    }

    public /* synthetic */ b(bd bdVar) {
        this();
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        webView.loadUrl(str);
        return true;
    }
}
