package com.tapjoy;

import android.content.DialogInterface;

/* loaded from: classes.dex */
class f implements DialogInterface.OnClickListener {
    final /* synthetic */ e a;

    public f(e eVar) {
        this.a = eVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
