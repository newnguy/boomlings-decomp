package com.google.ads;

import android.webkit.WebView;
import com.google.ads.internal.AdWebView;
import java.util.HashMap;

/* loaded from: classes.dex */
public class s implements n {
    @Override // com.google.ads.n
    public void a(com.google.ads.internal.d dVar, HashMap hashMap, WebView webView) {
        if (webView instanceof AdWebView) {
            ((AdWebView) webView).setCustomClose("1".equals(hashMap.get("custom_close")));
        } else {
            com.google.ads.util.b.b("Trying to set a custom close icon on a WebView that isn't an AdWebView");
        }
    }
}
