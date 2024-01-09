package com.google.ads;

import android.webkit.WebView;
import com.google.ads.internal.AdWebView;
import java.util.HashMap;

/* loaded from: classes.dex */
public class t implements n {
    private static final com.google.ads.internal.a a = (com.google.ads.internal.a) com.google.ads.internal.a.a.b();

    @Override // com.google.ads.n
    public void a(com.google.ads.internal.d dVar, HashMap hashMap, WebView webView) {
        String str = (String) hashMap.get("js");
        if (str == null) {
            com.google.ads.util.b.b("Could not get the JS to evaluate.");
        } else if (!(webView instanceof AdWebView)) {
            com.google.ads.util.b.b("Trying to evaluate JS in a WebView that isn't an AdWebView");
        } else {
            AdActivity d = ((AdWebView) webView).d();
            if (d == null) {
                com.google.ads.util.b.b("Could not get the AdActivity from the AdWebView.");
                return;
            }
            AdWebView b = d.b();
            if (b == null) {
                com.google.ads.util.b.b("Could not get the opening WebView.");
            } else {
                a.a(b, str);
            }
        }
    }
}
