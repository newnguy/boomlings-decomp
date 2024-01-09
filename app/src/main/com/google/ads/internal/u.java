package com.google.ads.internal;

import android.webkit.WebView;
import com.google.ads.AdSize;
import java.util.LinkedList;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class u implements Runnable {
    private final d a;
    private final WebView b;
    private final LinkedList c;
    private final int d;
    private final boolean e;
    private final String f;
    private final AdSize g;

    public u(d dVar, WebView webView, LinkedList linkedList, int i, boolean z, String str, AdSize adSize) {
        this.a = dVar;
        this.b = webView;
        this.c = linkedList;
        this.d = i;
        this.e = z;
        this.f = str;
        this.g = adSize;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.b != null) {
            this.b.stopLoading();
            this.b.destroy();
        }
        this.a.a(this.c);
        this.a.a(this.d);
        this.a.a(this.e);
        this.a.a(this.f);
        if (this.g != null) {
            ((h) this.a.h().k.a()).b(this.g);
            this.a.k().setAdSize(this.g);
        }
        this.a.B();
    }
}
