package org.cocos2dx.lib;

import android.content.DialogInterface;

/* loaded from: classes.dex */
class v implements DialogInterface.OnClickListener {
    final /* synthetic */ u a;

    public v(u uVar) {
        this.a = uVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        JniToCpp.openSupportMail();
    }
}
