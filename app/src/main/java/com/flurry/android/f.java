package com.flurry.android;

import android.content.Context;

/* loaded from: classes.dex */
public final class f extends an {
    private /* synthetic */ Context a;
    private /* synthetic */ bo b;

    public f(bo boVar, Context context) {
        this.b = boVar;
        this.a = context;
    }

    @Override // com.flurry.android.an
    public final void a() {
        this.b.b(this.b.a("", ac.c(this.a), ac.d(this.a), true, (FlurryAdSize) null));
    }
}
