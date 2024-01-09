package com.flurry.android;

import android.content.Context;
import android.os.Handler;

/* loaded from: classes.dex */
public final class a extends an {
    final /* synthetic */ Context a;
    final /* synthetic */ FlurryAgent b;
    private /* synthetic */ boolean c;

    public a(FlurryAgent flurryAgent, boolean z, Context context) {
        this.b = flurryAgent;
        this.c = z;
        this.a = context;
    }

    @Override // com.flurry.android.an
    public final void a() {
        boolean z;
        bo boVar;
        Handler handler;
        long j;
        this.b.g();
        this.b.m();
        if (!this.c) {
            handler = this.b.m;
            u uVar = new u(this);
            j = FlurryAgent.f;
            handler.postDelayed(uVar, j);
        }
        z = FlurryAgent.l;
        if (z) {
            bm.a("FlurryAgent", "Attempting to persist AdLogs");
            boVar = this.b.ab;
            boVar.k();
        }
    }
}
