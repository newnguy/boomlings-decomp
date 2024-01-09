package com.google.ads.util;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;

/* loaded from: classes.dex */
final class n implements DialogInterface.OnCancelListener {
    final /* synthetic */ JsPromptResult a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(JsPromptResult jsPromptResult) {
        this.a = jsPromptResult;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        this.a.cancel();
    }
}
