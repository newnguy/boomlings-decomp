package com.flurry.android;

import android.content.DialogInterface;
import java.util.HashMap;

/* loaded from: classes.dex */
final class ar implements DialogInterface.OnClickListener {
    private /* synthetic */ bj a;
    private /* synthetic */ int b;
    private /* synthetic */ ap c;

    public ar(ap apVar, bj bjVar, int i) {
        this.c = apVar;
        this.a = bjVar;
        this.b = i;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        az azVar;
        az azVar2;
        HashMap hashMap = new HashMap();
        hashMap.put("sourceEvent", this.a.a);
        this.c.a("userCanceled", hashMap, this.c.c, this.c.b, this.c.d, this.b + 1);
        dialogInterface.dismiss();
        azVar = this.c.g;
        if (azVar == null || this.c.b() != 3) {
            return;
        }
        azVar2 = this.c.g;
        azVar2.start();
    }
}
