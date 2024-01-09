package com.flurry.android;

import android.content.DialogInterface;
import android.view.View;
import android.webkit.WebChromeClient;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class q implements DialogInterface.OnDismissListener {
    private /* synthetic */ bf a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public q(bf bfVar) {
        this.a = bfVar;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        View view;
        WebChromeClient webChromeClient;
        WebChromeClient webChromeClient2;
        view = this.a.a.j;
        if (view != null) {
            webChromeClient = this.a.a.i;
            if (webChromeClient != null) {
                webChromeClient2 = this.a.a.i;
                webChromeClient2.onHideCustomView();
            }
        }
    }
}
