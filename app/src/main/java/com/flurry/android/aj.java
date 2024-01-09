package com.flurry.android;

import android.content.Context;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public abstract class aj {
    protected String a;

    public aj(AdUnit adUnit) {
        if (adUnit != null) {
            this.a = adUnit.a().toString();
        }
    }

    public abstract void a(Context context, ViewGroup viewGroup);
}
