package com.google.ads;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import com.google.ads.g;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationInterstitialAdapter;
import java.util.HashMap;

/* loaded from: classes.dex */
public class h {
    final com.google.ads.internal.h a;
    private final f b;
    private boolean c;
    private boolean d;
    private g.a e;
    private final e f;
    private MediationAdapter g;
    private boolean h;
    private boolean i;
    private View j;
    private final Handler k;
    private final String l;
    private final AdRequest m;
    private final HashMap n;

    public h(e eVar, com.google.ads.internal.h hVar, f fVar, String str, AdRequest adRequest, HashMap hashMap) {
        com.google.ads.util.a.b(TextUtils.isEmpty(str));
        this.f = eVar;
        this.a = hVar;
        this.b = fVar;
        this.l = str;
        this.m = adRequest;
        this.n = hashMap;
        this.c = false;
        this.d = false;
        this.e = null;
        this.g = null;
        this.h = false;
        this.i = false;
        this.j = null;
        this.k = new Handler(Looper.getMainLooper());
    }

    public f a() {
        return this.b;
    }

    public synchronized void a(Activity activity) {
        com.google.ads.util.a.b(this.h, "startLoadAdTask has already been called.");
        this.h = true;
        this.k.post(new aw(this, activity, this.l, this.m, this.n));
    }

    public synchronized void a(View view) {
        this.j = view;
    }

    public synchronized void a(MediationAdapter mediationAdapter) {
        this.g = mediationAdapter;
    }

    public synchronized void a(boolean z, g.a aVar) {
        this.d = z;
        this.c = true;
        this.e = aVar;
        notify();
    }

    public synchronized void b() {
        com.google.ads.util.a.a(this.h, "destroy() called but startLoadAdTask has not been called.");
        this.k.post(new au(this));
    }

    public synchronized boolean c() {
        return this.c;
    }

    public synchronized boolean d() {
        com.google.ads.util.a.a(this.c, "isLoadAdTaskSuccessful() called when isLoadAdTaskDone() is false.");
        return this.d;
    }

    public synchronized g.a e() {
        return this.e == null ? g.a.TIMEOUT : this.e;
    }

    public synchronized View f() {
        com.google.ads.util.a.a(this.c, "getAdView() called when isLoadAdTaskDone() is false.");
        return this.j;
    }

    public synchronized void g() {
        com.google.ads.util.a.a(this.a.a());
        try {
            this.k.post(new av(this, (MediationInterstitialAdapter) this.g));
        } catch (ClassCastException e) {
            com.google.ads.util.b.b("In Ambassador.show(): ambassador.adapter does not implement the MediationInterstitialAdapter interface.", e);
        }
    }

    public synchronized String h() {
        return this.g != null ? this.g.getClass().getName() : "\"adapter was not created.\"";
    }

    public synchronized MediationAdapter i() {
        return this.g;
    }

    public e j() {
        return this.f;
    }

    public synchronized void k() {
        this.i = true;
    }

    public synchronized boolean l() {
        return this.i;
    }
}
