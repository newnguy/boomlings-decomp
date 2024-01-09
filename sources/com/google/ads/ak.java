package com.google.ads;

import android.content.DialogInterface;
import java.util.HashMap;

/* loaded from: classes.dex */
class ak implements DialogInterface.OnClickListener {
    private com.google.ads.internal.d a;

    public ak(com.google.ads.internal.d dVar) {
        this.a = dVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("u", "market://details?id=com.google.android.apps.plus");
        AdActivity.a(this.a, new com.google.ads.internal.e("intent", hashMap));
    }
}
