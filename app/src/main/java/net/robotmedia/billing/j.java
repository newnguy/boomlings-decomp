package net.robotmedia.billing;

import android.os.Bundle;

/* loaded from: classes.dex */
public class j extends i {
    public j(String str, int i, String str2, String str3) {
        super(str, i, str2, str3);
    }

    @Override // net.robotmedia.billing.d
    protected int a() {
        return 2;
    }

    @Override // net.robotmedia.billing.i, net.robotmedia.billing.d
    public void a(Bundle bundle) {
        super.a(bundle);
        bundle.putString("ITEM_TYPE", "subs");
    }
}
