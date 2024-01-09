package net.robotmedia.billing;

import android.os.Bundle;

/* loaded from: classes.dex */
public class f extends d {
    public f(String str, int i) {
        super(str, i);
    }

    @Override // net.robotmedia.billing.d
    protected int a() {
        return 2;
    }

    @Override // net.robotmedia.billing.d
    protected void a(Bundle bundle) {
        bundle.putString("ITEM_TYPE", "subs");
    }

    @Override // net.robotmedia.billing.d
    protected void b(Bundle bundle) {
        a.b(e());
    }

    @Override // net.robotmedia.billing.d
    public String c() {
        return "CHECK_BILLING_SUPPORTED";
    }
}
