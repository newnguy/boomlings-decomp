package com.google.ads.util;

import android.content.DialogInterface;
import android.webkit.JsResult;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class k implements DialogInterface.OnCancelListener {
    final /* synthetic */ JsResult a;

    public k(JsResult jsResult) {
        this.a = jsResult;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        this.a.cancel();
    }
}
