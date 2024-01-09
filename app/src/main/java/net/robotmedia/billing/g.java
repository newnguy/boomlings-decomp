package net.robotmedia.billing;

import android.os.Bundle;

/* loaded from: classes.dex */
public class g extends d {
    private String[] a;

    public g(String str, int i, String[] strArr) {
        super(str, i);
        this.a = strArr;
    }

    @Override // net.robotmedia.billing.d
    protected void a(Bundle bundle) {
        bundle.putStringArray("NOTIFY_IDS", this.a);
    }

    @Override // net.robotmedia.billing.d
    public String c() {
        return "CONFIRM_NOTIFICATIONS";
    }
}
