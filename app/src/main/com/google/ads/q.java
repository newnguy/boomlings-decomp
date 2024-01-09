package com.google.ads;

import android.net.Uri;
import android.webkit.WebView;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes.dex */
public class q extends u {
    @Override // com.google.ads.u, com.google.ads.n
    public void a(com.google.ads.internal.d dVar, HashMap hashMap, WebView webView) {
        Uri parse;
        String host;
        String[] split;
        String str = (String) hashMap.get("u");
        if (str == null) {
            com.google.ads.util.b.e("Could not get URL from click gmsg.");
            return;
        }
        com.google.ads.internal.g m = dVar.m();
        if (m != null && (host = (parse = Uri.parse(str)).getHost()) != null && host.toLowerCase(Locale.US).endsWith(".admob.com")) {
            String str2 = null;
            String path = parse.getPath();
            if (path != null) {
                if (path.split("/").length >= 4) {
                    str2 = split[2] + "/" + split[3];
                }
            }
            m.a(str2);
        }
        super.a(dVar, hashMap, webView);
    }
}
