package com.flurry.android;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class h extends an {
    private /* synthetic */ Context a;
    private /* synthetic */ String b;
    private /* synthetic */ FlurryAdSize c;
    private /* synthetic */ bo d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(bo boVar, Context context, String str, FlurryAdSize flurryAdSize) {
        this.d = boVar;
        this.a = context;
        this.b = str;
        this.c = flurryAdSize;
    }

    @Override // com.flurry.android.an
    public final void a() {
        this.d.b(this.d.a(this.b, ac.c(this.a), ac.d(this.a), false, this.c));
    }
}
