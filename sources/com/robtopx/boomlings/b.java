package com.robtopx.boomlings;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class b implements DialogInterface.OnClickListener {
    private final /* synthetic */ SharedPreferences.Editor a;
    private final /* synthetic */ Context b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(SharedPreferences.Editor editor, Context context) {
        this.a = editor;
        this.b = context;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.a != null) {
            this.a.putBoolean("dontshowagain", true);
            this.a.commit();
        }
        a.d(this.b);
    }
}
