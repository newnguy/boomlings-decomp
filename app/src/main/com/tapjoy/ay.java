package com.tapjoy;

import android.content.DialogInterface;

/* loaded from: classes.dex */
class ay implements DialogInterface.OnClickListener {
    final /* synthetic */ TapjoyVideoView a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ay(TapjoyVideoView tapjoyVideoView) {
        this.a = tapjoyVideoView;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.finish();
    }
}
