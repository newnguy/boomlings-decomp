package com.google.ads;

import android.webkit.WebView;
import java.util.HashMap;

/* loaded from: classes.dex */
public class y implements n {
    @Override // com.google.ads.n
    public void a(com.google.ads.internal.d dVar, HashMap hashMap, WebView webView) {
        com.google.ads.util.b.c("Received log message: <\"string\": \"" + ((String) hashMap.get("string")) + "\", \"afmaNotifyDt\": \"" + ((String) hashMap.get("afma_notify_dt")) + "\">");
    }
}
