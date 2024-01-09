package com.robtopx.boomlings;

import android.app.AlertDialog;
import android.content.DialogInterface;

/* loaded from: classes.dex */
public class f implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;

    public f(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    @Override // java.lang.Runnable
    public void run() {
        Boomlings boomlings;
        boomlings = Boomlings.me;
        AlertDialog.Builder builder = new AlertDialog.Builder(boomlings);
        builder.setTitle(this.a);
        builder.setMessage(this.b).setCancelable(false).setPositiveButton(this.c, (DialogInterface.OnClickListener) null);
        builder.create().show();
    }
}
