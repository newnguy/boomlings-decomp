package com.google.ads;

import android.webkit.WebView;
import com.google.ads.internal.c;
import java.util.HashMap;

/* loaded from: classes.dex */
public class w implements n {
    @Override // com.google.ads.n
    public void a(com.google.ads.internal.d dVar, HashMap hashMap, WebView webView) {
        String str = (String) hashMap.get("url");
        String str2 = (String) hashMap.get("type");
        String str3 = (String) hashMap.get("afma_notify_dt");
        boolean equals = "1".equals(hashMap.get("drt_include"));
        String str4 = (String) hashMap.get("request_scenario");
        boolean equals2 = "1".equals(hashMap.get("use_webview_loadurl"));
        c.d dVar2 = c.d.OFFLINE_EMPTY.e.equals(str4) ? c.d.OFFLINE_EMPTY : c.d.OFFLINE_USING_BUFFERED_ADS.e.equals(str4) ? c.d.OFFLINE_USING_BUFFERED_ADS : c.d.ONLINE_USING_BUFFERED_ADS.e.equals(str4) ? c.d.ONLINE_USING_BUFFERED_ADS : c.d.ONLINE_SERVER_REQUEST;
        com.google.ads.util.b.c("Received ad url: <url: \"" + str + "\" type: \"" + str2 + "\" afmaNotifyDt: \"" + str3 + "\" useWebViewLoadUrl: \"" + equals2 + "\">");
        com.google.ads.internal.c j = dVar.j();
        if (j != null) {
            j.c(equals);
            j.a(dVar2);
            j.d(equals2);
            j.d(str);
        }
    }
}
