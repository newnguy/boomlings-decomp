package net.robotmedia.billing;

import android.os.Bundle;

/* loaded from: classes.dex */
public class e extends d {
    public e(String str, int i) {
        super(str, i);
    }

    @Override // net.robotmedia.billing.d
    protected void b(Bundle bundle) {
        a.a(e());
    }

    @Override // net.robotmedia.billing.d
    public String c() {
        return "CHECK_BILLING_SUPPORTED";
    }
}
