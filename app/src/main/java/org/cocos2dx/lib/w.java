package org.cocos2dx.lib;

import android.content.DialogInterface;

/* loaded from: classes.dex */
class w implements DialogInterface.OnClickListener {
    final /* synthetic */ u a;

    public w(u uVar) {
        this.a = uVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
    }
}
