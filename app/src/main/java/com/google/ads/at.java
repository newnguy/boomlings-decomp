package com.google.ads;

import android.view.View;

/* loaded from: classes.dex */
public class at implements Runnable {
    final /* synthetic */ h a;
    final /* synthetic */ View b;
    final /* synthetic */ f c;
    final /* synthetic */ e d;

    public at(e eVar, h hVar, View view, f fVar) {
        this.d = eVar;
        this.a = hVar;
        this.b = view;
        this.c = fVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean e;
        com.google.ads.internal.d dVar;
        e = this.d.e(this.a);
        if (e) {
            com.google.ads.util.b.a("Trying to switch GWAdNetworkAmbassadors, but GWController().destroy() has been called. Destroying the new ambassador and terminating mediation.");
            return;
        }
        dVar = this.d.a;
        dVar.a(this.b, this.a, this.c, false);
    }
}
