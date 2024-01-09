package com.google.ads.util;

import android.content.DialogInterface;
import android.webkit.JsResult;

/* loaded from: classes.dex */
final class l implements DialogInterface.OnClickListener {
    final /* synthetic */ JsResult a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(JsResult jsResult) {
        this.a = jsResult;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.cancel();
    }
}
