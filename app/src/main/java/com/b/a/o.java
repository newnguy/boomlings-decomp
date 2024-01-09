package com.b.a;

import android.view.View;

/* loaded from: classes.dex */
public class o implements View.OnClickListener {
    final /* synthetic */ n a;

    public o(n nVar) {
        this.a = nVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        i iVar;
        iVar = this.a.e;
        iVar.onCancel();
        this.a.dismiss();
    }
}
