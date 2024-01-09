package net.robotmedia.billing;

import android.app.PendingIntent;
import android.os.Bundle;

/* loaded from: classes.dex */
public class i extends d {
    private String a;
    private String b;

    public i(String str, int i, String str2, String str3) {
        super(str, i);
        this.a = str2;
        this.b = str3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.robotmedia.billing.d
    public void a(Bundle bundle) {
        bundle.putString("ITEM_ID", this.a);
        if (this.b != null) {
            bundle.putString("DEVELOPER_PAYLOAD", this.b);
        }
    }

    @Override // net.robotmedia.billing.d
    public void a(k kVar) {
        super.a(kVar);
        a.a(this.a, kVar);
    }

    @Override // net.robotmedia.billing.d
    protected void b(Bundle bundle) {
        a.a(this.a, (PendingIntent) bundle.getParcelable("PURCHASE_INTENT"));
    }

    @Override // net.robotmedia.billing.d
    public String c() {
        return "REQUEST_PURCHASE";
    }
}
