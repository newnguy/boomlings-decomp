package com.google.ads.util;

import android.content.DialogInterface;
import android.webkit.JsResult;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class l implements DialogInterface.OnClickListener {
    final /* synthetic */ JsResult a;

    public l(JsResult jsResult) {
        this.a = jsResult;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.cancel();
    }
}
