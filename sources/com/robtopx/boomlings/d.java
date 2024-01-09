package com.robtopx.boomlings;

import android.content.DialogInterface;
import android.content.SharedPreferences;

/* loaded from: classes.dex */
class d implements DialogInterface.OnClickListener {
    private final /* synthetic */ SharedPreferences.Editor a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(SharedPreferences.Editor editor) {
        this.a = editor;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.a != null) {
            this.a.putLong("launch_count", 2L);
            this.a.commit();
        }
        dialogInterface.cancel();
    }
}
