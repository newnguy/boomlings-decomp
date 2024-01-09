package com.flurry.android;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class e extends an {
    private /* synthetic */ Context a;
    private /* synthetic */ boolean b;
    private /* synthetic */ FlurryAgent c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(FlurryAgent flurryAgent, Context context, boolean z) {
        this.c = flurryAgent;
        this.a = context;
        this.b = z;
    }

    @Override // com.flurry.android.an
    public final void a() {
        boolean z;
        z = this.c.t;
        if (!z) {
            this.c.b(this.a);
        }
        FlurryAgent.a(this.c, this.a, this.b);
    }
}
