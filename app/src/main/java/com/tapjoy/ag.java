package com.tapjoy;

import android.content.DialogInterface;

/* loaded from: classes.dex */
class ag implements DialogInterface.OnClickListener {
    final /* synthetic */ af a;

    public ag(af afVar) {
        this.a = afVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
