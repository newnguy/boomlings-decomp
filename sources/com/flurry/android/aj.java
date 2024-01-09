package com.flurry.android;

import android.content.Context;
import android.view.ViewGroup;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class aj {
    protected String a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public aj(AdUnit adUnit) {
        if (adUnit != null) {
            this.a = adUnit.a().toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void a(Context context, ViewGroup viewGroup);
}
