package com.google.ads.internal;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import com.google.ads.internal.j;

/* loaded from: classes.dex */
class x implements DialogInterface.OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ j.a b;

    public x(j.a aVar, String str) {
        this.b = aVar;
        this.a = str;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        Activity activity;
        activity = this.b.b;
        activity.startActivity(Intent.createChooser(new Intent("android.intent.action.SEND").setType("text/plain").putExtra("android.intent.extra.TEXT", this.a), "Share via"));
    }
}
