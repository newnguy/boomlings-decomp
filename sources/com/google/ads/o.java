package com.google.ads;

import android.webkit.WebView;
import java.util.HashMap;

/* loaded from: classes.dex */
public class o implements n {
    @Override // com.google.ads.n
    public void a(com.google.ads.internal.d dVar, HashMap hashMap, WebView webView) {
        String str = (String) hashMap.get("name");
        if (str == null) {
            com.google.ads.util.b.b("Error: App event with no name parameter.");
        } else {
            dVar.a(str, (String) hashMap.get("info"));
        }
    }
}
