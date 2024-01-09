package com.robtopx.boomlings;

import android.content.DialogInterface;
import android.content.SharedPreferences;

/* loaded from: classes.dex */
class c implements DialogInterface.OnClickListener {
    private final /* synthetic */ SharedPreferences.Editor a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(SharedPreferences.Editor editor) {
        this.a = editor;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.a != null) {
            this.a.putBoolean("dontshowagain", true);
            this.a.commit();
        }
        dialogInterface.cancel();
    }
}
