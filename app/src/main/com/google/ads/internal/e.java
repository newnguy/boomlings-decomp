package com.google.ads.internal;

import android.os.Bundle;
import java.io.Serializable;
import java.util.HashMap;

/* loaded from: classes.dex */
public class e {
    private String a;
    private HashMap b;

    public e(Bundle bundle) {
        this.a = bundle.getString("action");
        this.b = a(bundle.getSerializable("params"));
    }

    public e(String str) {
        this.a = str;
    }

    public e(String str, HashMap hashMap) {
        this(str);
        this.b = hashMap;
    }

    private HashMap a(Serializable serializable) {
        if (serializable instanceof HashMap) {
            return (HashMap) serializable;
        }
        return null;
    }

    public Bundle a() {
        Bundle bundle = new Bundle();
        bundle.putString("action", this.a);
        bundle.putSerializable("params", this.b);
        return bundle;
    }

    public String b() {
        return this.a;
    }

    public HashMap c() {
        return this.b;
    }
}
