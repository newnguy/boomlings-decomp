package com.google.ads.internal;

import android.webkit.WebView;
import com.google.ads.AdRequest;

/* loaded from: classes.dex */
public class r implements Runnable {
    private final d a;
    private final WebView b;
    private final f c;
    private final AdRequest.ErrorCode d;
    private final boolean e;

    public r(d dVar, WebView webView, f fVar, AdRequest.ErrorCode errorCode, boolean z) {
        this.a = dVar;
        this.b = webView;
        this.c = fVar;
        this.d = errorCode;
        this.e = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.b != null) {
            this.b.stopLoading();
            this.b.destroy();
        }
        if (this.c != null) {
            this.c.a();
        }
        if (this.e) {
            AdWebView k = this.a.k();
            k.stopLoading();
            k.setVisibility(8);
        }
        this.a.a(this.d);
    }
}
