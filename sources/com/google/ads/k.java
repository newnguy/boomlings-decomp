package com.google.ads;

import android.webkit.WebView;

/* loaded from: classes.dex */
class k implements Runnable {
    private final boolean a;
    private final WebView b;

    public k(WebView webView, boolean z) {
        this.b = webView;
        this.a = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        ag.a(this.b, this.a);
    }
}
