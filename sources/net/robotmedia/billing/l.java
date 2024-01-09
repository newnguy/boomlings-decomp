package net.robotmedia.billing;

/* loaded from: classes.dex */
public class l extends d {
    public l(String str, int i) {
        super(str, i);
    }

    @Override // net.robotmedia.billing.d
    public void a(k kVar) {
        super.a(kVar);
        if (kVar == k.RESULT_OK) {
            a.a();
        }
    }

    @Override // net.robotmedia.billing.d
    public String c() {
        return "RESTORE_TRANSACTIONS";
    }

    @Override // net.robotmedia.billing.d
    public boolean d() {
        return true;
    }
}
