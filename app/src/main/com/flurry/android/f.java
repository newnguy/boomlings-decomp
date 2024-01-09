package com.flurry.android;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class f extends an {
    private /* synthetic */ Context a;
    private /* synthetic */ bo b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(bo boVar, Context context) {
        this.b = boVar;
        this.a = context;
    }

    @Override // com.flurry.android.an
    public final void a() {
        this.b.b(this.b.a("", ac.c(this.a), ac.d(this.a), true, (FlurryAdSize) null));
    }
}
