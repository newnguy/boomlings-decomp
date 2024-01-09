package com.google.ads.internal;

import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.ads.AdActivity;
import com.google.ads.util.AdUtil;
import com.google.ads.util.g;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class i extends WebViewClient {
    private static final a c = (a) a.a.b();
    protected d a;
    private final Map d;
    private final boolean e;
    private boolean f;
    protected boolean b = false;
    private boolean g = false;
    private boolean h = false;

    public i(d dVar, Map map, boolean z, boolean z2) {
        this.a = dVar;
        this.d = map;
        this.e = z;
        this.f = z2;
    }

    public static i a(d dVar, Map map, boolean z, boolean z2) {
        return AdUtil.a >= 11 ? new g.b(dVar, map, z, z2) : new i(dVar, map, z, z2);
    }

    public void a(boolean z) {
        this.b = z;
    }

    public void b(boolean z) {
        this.f = z;
    }

    public void c(boolean z) {
        this.g = z;
    }

    public void d(boolean z) {
        this.h = z;
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        if (this.g) {
            c j = this.a.j();
            if (j != null) {
                j.c();
            } else {
                com.google.ads.util.b.a("adLoader was null while trying to setFinishedLoadingHtml().");
            }
            this.g = false;
        }
        if (this.h) {
            c.a(webView);
            this.h = false;
        }
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        boolean z = true;
        try {
            com.google.ads.util.b.a("shouldOverrideUrlLoading(\"" + str + "\")");
            Uri parse = Uri.parse(str);
            if (c.a(parse)) {
                c.a(this.a, this.d, parse, webView);
            } else if (this.f) {
                if (AdUtil.a(parse)) {
                    z = super.shouldOverrideUrlLoading(webView, str);
                } else {
                    HashMap hashMap = new HashMap();
                    hashMap.put("u", str);
                    AdActivity.a(this.a, new e("intent", hashMap));
                }
            } else if (this.e) {
                HashMap hashMap2 = new HashMap();
                hashMap2.put("u", parse.toString());
                AdActivity.a(this.a, new e("intent", hashMap2));
            } else {
                com.google.ads.util.b.e("URL is not a GMSG and can't handle URL: " + str);
            }
        } catch (Throwable th) {
            com.google.ads.util.b.b("An unknown error occurred in shouldOverrideUrlLoading.", th);
        }
        return z;
    }
}
