package com.flurry.android;

import android.content.DialogInterface;
import android.webkit.WebView;

/* loaded from: classes.dex */
final class au implements DialogInterface.OnDismissListener {
    private /* synthetic */ ap a;

    public au(ap apVar) {
        this.a = apVar;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        WebView webView;
        WebView webView2;
        webView = this.a.h;
        if (webView != null) {
            webView2 = this.a.h;
            webView2.loadUrl("javascript:if(window.mraid){window.mraid.close();};");
        }
    }
}
