package com.google.ads;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import com.google.ads.util.i;

/* loaded from: classes.dex */
public class m extends com.google.ads.util.i {
    public final i.b a;
    public final i.b d;
    public final i.d e;
    public final i.b f;
    public final i.b g;
    public final i.b h;
    public final i.b i;
    public final i.b j;
    public final i.b k;
    public final i.c b = new i.c("currentAd", null);
    public final i.c c = new i.c("nextAd", null);
    public final i.c m = new i.c("adListener");
    public final i.c n = new i.c("appEventListener");
    public final i.c l = new i.c("adSizes", null);

    public m(l lVar, Ad ad, AdView adView, InterstitialAd interstitialAd, String str, Activity activity, Context context, ViewGroup viewGroup, com.google.ads.internal.h hVar) {
        this.a = new i.b("appState", lVar);
        this.h = new i.b("ad", ad);
        this.i = new i.b("adView", adView);
        this.k = new i.b("adType", hVar);
        this.d = new i.b("adUnitId", str);
        this.e = new i.d("activity", activity);
        this.j = new i.b("interstitialAd", interstitialAd);
        this.g = new i.b("bannerContainer", viewGroup);
        this.f = new i.b("applicationContext", context);
    }

    public boolean a() {
        return !b();
    }

    public boolean b() {
        return ((com.google.ads.internal.h) this.k.a()).a();
    }
}
