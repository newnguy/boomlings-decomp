package com.flurry.android;

import android.content.Context;
import android.content.Intent;

/* loaded from: classes.dex */
public final class c extends an {
    private /* synthetic */ String a;
    private /* synthetic */ Context b;
    private /* synthetic */ boolean c;
    private /* synthetic */ bo d;

    public c(bo boVar, String str, Context context, boolean z) {
        this.d = boVar;
        this.a = str;
        this.b = context;
        this.c = z;
    }

    @Override // com.flurry.android.an
    public final void a() {
        if (this.a == null) {
            bm.d(bo.a, "Unable to launch intent for: " + this.a);
        } else if (this.a.startsWith("market://")) {
            this.d.b(this.b, this.a);
        } else if (!this.a.startsWith("http")) {
            if (ac.a(this.b, this.a)) {
                return;
            }
            bm.d(bo.a, "Failed to launch intent for:" + this.a);
        } else {
            Intent intent = new Intent(this.b, FlurryFullscreenTakeoverActivity.class);
            intent.putExtra("url", this.a);
            if (this.c && ac.a(this.b, intent)) {
                this.b.startActivity(intent);
                return;
            }
            bm.b(bo.a, "Unable to launch FlurryFullscreenTakeoverActivity, falling back to browser. Fix by declaring this Activity in your AndroidManifest.xml");
            ac.a(this.b, this.a);
        }
    }
}
