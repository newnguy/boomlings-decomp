package com.google.ads;

/* loaded from: classes.dex */
public class an implements Runnable {
    final /* synthetic */ f a;
    final /* synthetic */ boolean b;
    final /* synthetic */ e c;

    public an(e eVar, f fVar, boolean z) {
        this.c = eVar;
        this.a = fVar;
        this.b = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.google.ads.internal.d dVar;
        dVar = this.c.a;
        dVar.a(this.a, this.b);
    }
}
