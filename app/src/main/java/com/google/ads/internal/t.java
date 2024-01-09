package com.google.ads.internal;

import android.webkit.WebView;

/* loaded from: classes.dex */
public class t implements Runnable {
    final /* synthetic */ c a;
    private final String b;
    private final String c;
    private final WebView d;

    public t(c cVar, WebView webView, String str, String str2) {
        this.a = cVar;
        this.d = webView;
        this.b = str;
        this.c = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.c != null) {
            this.d.loadDataWithBaseURL(this.b, this.c, "text/html", "utf-8", null);
        } else {
            this.d.loadUrl(this.b);
        }
    }
}
