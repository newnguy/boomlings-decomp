package com.google.ads.util;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class n implements DialogInterface.OnCancelListener {
    final /* synthetic */ JsPromptResult a;

    public n(JsPromptResult jsPromptResult) {
        this.a = jsPromptResult;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        this.a.cancel();
    }
}
