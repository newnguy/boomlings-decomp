package com.flurry.android;

import android.app.Dialog;
import android.content.DialogInterface;

/* loaded from: classes.dex */
public final class s implements DialogInterface.OnShowListener {
    private /* synthetic */ bf a;

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
