package com.google.ads.util;

import android.content.DialogInterface;
import android.webkit.JsResult;

/* loaded from: classes.dex */
final class k implements DialogInterface.OnCancelListener {
    final /* synthetic */ JsResult a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(JsResult jsResult) {
        this.a = jsResult;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        this.a.cancel();
    }
}
