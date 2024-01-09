package net.robotmedia.billing;

import android.os.Bundle;

/* loaded from: classes.dex */
public class h extends d {
    private String[] a;

    public h(String str, int i, String[] strArr) {
        super(str, i);
        this.a = strArr;
    }

    @Override // net.robotmedia.billing.d
    protected void a(Bundle bundle) {
        bundle.putStringArray("NOTIFY_IDS", this.a);
    }

    @Override // net.robotmedia.billing.d
    public String c() {
        return "GET_PURCHASE_INFORMATION";
    }

    @Override // net.robotmedia.billing.d
    public boolean d() {
        return true;
    }
}
