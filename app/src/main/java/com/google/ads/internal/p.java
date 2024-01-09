package com.google.ads.internal;

import android.webkit.WebView;
import com.google.ads.AdSize;

/* loaded from: classes.dex */
public class p implements Runnable {
    final /* synthetic */ com.google.ads.c a;
    final /* synthetic */ c b;

    public p(c cVar, com.google.ads.c cVar2) {
        this.b = cVar;
        this.a = cVar2;
    }

    @Override // java.lang.Runnable
    public void run() {
        WebView webView;
        d dVar;
        String str;
        AdSize adSize;
        d dVar2;
        d dVar3;
        AdSize adSize2;
        WebView webView2;
        WebView webView3;
        webView = this.b.j;
        if (webView != null) {
            webView2 = this.b.j;
            webView2.stopLoading();
            webView3 = this.b.j;
            webView3.destroy();
        }
        dVar = this.b.h;
        str = this.b.m;
        dVar.a(str);
        adSize = this.b.n;
        if (adSize != null) {
            dVar3 = this.b.h;
            adSize2 = this.b.n;
            ((h) dVar3.h().k.a()).b(adSize2);
        }
        dVar2 = this.b.h;
        dVar2.a(this.a);
    }
}
