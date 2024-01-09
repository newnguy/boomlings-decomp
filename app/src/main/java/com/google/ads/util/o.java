package com.google.ads.util;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class o implements DialogInterface.OnClickListener {
    final /* synthetic */ JsPromptResult a;

    public o(JsPromptResult jsPromptResult) {
        this.a = jsPromptResult;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.cancel();
    }
}
