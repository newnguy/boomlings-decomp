package com.google.ads;

/* loaded from: classes.dex */
public class ap implements Runnable {
    final /* synthetic */ e a;

    public ap(e eVar) {
        this.a = eVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.google.ads.internal.d dVar;
        dVar = this.a.a;
        dVar.t();
    }
}
