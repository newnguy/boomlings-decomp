package com.google.ads;

import android.app.Activity;

/* loaded from: classes.dex */
public class InterstitialAd implements Ad {
    private com.google.ads.internal.d a;

    public InterstitialAd(Activity activity, String str) {
        this(activity, str, false);
    }

    public InterstitialAd(Activity activity, String str, boolean z) {
        this.a = new com.google.ads.internal.d(this, activity, null, str, null, z);
    }

    public void a() {
        this.a.y();
    }

    public void a(AdListener adListener) {
        this.a.h().m.a(adListener);
    }

    public void a(AdRequest adRequest) {
        this.a.a(adRequest);
    }

    public void b() {
        this.a.z();
    }
}
