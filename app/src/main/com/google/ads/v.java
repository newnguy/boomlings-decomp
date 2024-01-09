package com.google.ads;

import android.webkit.WebView;
import com.google.ads.AdRequest;
import java.util.HashMap;

/* loaded from: classes.dex */
public class v implements n {
    @Override // com.google.ads.n
    public void a(com.google.ads.internal.d dVar, HashMap hashMap, WebView webView) {
        com.google.ads.util.b.e("Invalid " + ((String) hashMap.get("type")) + " request error: " + ((String) hashMap.get("errors")));
        com.google.ads.internal.c j = dVar.j();
        if (j != null) {
            j.a(AdRequest.ErrorCode.INVALID_REQUEST);
        }
    }
}
