package net.robotmedia.billing;

import android.os.Bundle;
import android.util.Log;

/* loaded from: classes.dex */
public abstract class d {
    private String a;
    private int b;
    private boolean c;
    private long d;

    public d(String str, int i) {
        this.a = str;
        this.b = i;
    }

    protected int a() {
        return 1;
    }

    public long a(com.a.a.a.a aVar) {
        Bundle f = f();
        a(f);
        try {
            Bundle a = aVar.a(f);
            if (c(a)) {
                b(a);
                return a.getLong("REQUEST_ID", -1L);
            }
            return -1L;
        } catch (NullPointerException e) {
            Log.e(getClass().getSimpleName(), "Known IAB bug. See: http://code.google.com/p/marketbilling/issues/detail?id=25", e);
            return -1L;
        }
    }

    public void a(long j) {
        this.d = j;
    }

    protected void a(Bundle bundle) {
    }

    public void a(k kVar) {
    }

    public long b() {
        return this.d;
    }

    protected void b(Bundle bundle) {
    }

    public abstract String c();

    protected boolean c(Bundle bundle) {
        int i = bundle.getInt("RESPONSE_CODE");
        this.c = k.a(i);
        if (!this.c) {
            Log.w(getClass().getSimpleName(), "Error with response code " + k.b(i));
        }
        return this.c;
    }

    public boolean d() {
        return false;
    }

    public boolean e() {
        return this.c;
    }

    protected Bundle f() {
        Bundle bundle = new Bundle();
        bundle.putString("BILLING_REQUEST", c());
        bundle.putInt("API_VERSION", a());
        bundle.putString("PACKAGE_NAME", this.a);
        if (d()) {
            bundle.putLong("NONCE", this.d);
        }
        return bundle;
    }

    public int g() {
        return this.b;
    }
}
