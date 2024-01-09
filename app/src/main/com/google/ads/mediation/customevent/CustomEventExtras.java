package com.google.ads.mediation.customevent;

import com.google.ads.mediation.NetworkExtras;
import java.util.HashMap;

/* loaded from: classes.dex */
public class CustomEventExtras implements NetworkExtras {
    private final HashMap a = new HashMap();

    public Object a(String str) {
        return this.a.get(str);
    }
}
