package com.flurry.android;

import android.app.Dialog;
import android.content.DialogInterface;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class s implements DialogInterface.OnShowListener {
    private /* synthetic */ bf a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public s(bf bfVar) {
        this.a = bfVar;
    }

    @Override // android.content.DialogInterface.OnShowListener
    public final void onShow(DialogInterface dialogInterface) {
        Dialog dialog;
        Dialog dialog2;
        dialog = this.a.a.o;
        if (dialog != null) {
            dialog2 = this.a.a.o;
            dialog2.hide();
        }
    }
}
